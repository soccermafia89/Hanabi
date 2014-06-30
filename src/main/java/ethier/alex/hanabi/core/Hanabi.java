/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.core;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ethier.alex.hanabi.actions.DiscardResponse;
import ethier.alex.hanabi.actions.PlayResponse;
import ethier.alex.hanabi.actions.PlayerDrawAction;
import ethier.alex.hanabi.actions.PlayerResponse;
import ethier.alex.hanabi.actions.PlayerResponseType;
import ethier.alex.hanabi.actions.TellResponse;
import ethier.alex.hanabi.deck.Card;
import ethier.alex.hanabi.deck.Color;
import ethier.alex.hanabi.deck.Deck;
import ethier.alex.hanabi.state.Board;
import ethier.alex.hanabi.state.Discard;
import ethier.alex.hanabi.state.InvisibleHand;
import ethier.alex.hanabi.state.VisibleHand;
import java.util.Arrays;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 @author alex
 */
public class Hanabi {
    
    private static Logger logger = Logger.getLogger(Hanabi.class);

    Board board;
    Deck deck;
    Discard discard;
    List<Player> players;
    VisibleHand[] visibleHands;
    InvisibleHand[] invisibleHands;
    int timeCounters;
    int lives;
    int timer;
    boolean gameWon;
    BiMap<Integer, String> playerPositions;

    public Hanabi(List<Player> myPlayers) {
        if(myPlayers.size() < 1) {
            throw new RuntimeException("Invalid number of players: " + myPlayers.size());
        }
        
        players = myPlayers;

        playerPositions = HashBiMap.create();
        for (int i = 0; i < players.size(); i++) {
            playerPositions.put(i, players.get(i).getName());
            
            logger.info("Player found: " + players.get(i).getName());
        }

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            player.setPositions(i, playerPositions);
        }

        deck = new Deck();
        board = new Board();
        discard = new Discard();

        logger.warn("CHECK LIFE COUNT AND TIME COUNTER COUNT");
        timeCounters = 10;
        lives = 3;
        timer = players.size();
        gameWon = false;

        invisibleHands = new InvisibleHand[players.size()];
        for (int i = 0; i < invisibleHands.length; i++) {
            invisibleHands[i] = new InvisibleHand();
        }

        visibleHands = new VisibleHand[players.size()];
        for (int i = 0; i < visibleHands.length; i++) {

            Card[] newHand = new Card[5];
            for (int j = 0; j < 5; j++) {
                newHand[j] = deck.next();
            }

            visibleHands[i] = new VisibleHand(newHand);
        }
    }

    public Board playGame() {
        int turn = 0;

        while (!gameOver()) {

            //Update the game state for each player.
            for (int i = 0; i < players.size(); i++) {

                Map<Integer, VisibleHand> playersVisibleHands = new HashMap<Integer, VisibleHand>();
                for (int j = 0; j < players.size(); j++) {
                    if (i != j) {
                        playersVisibleHands.put(j, visibleHands[j]);
                    }
                }

                Player player = players.get(i);
                player.updateState(board,
                                   discard,
                                   playersVisibleHands,
                                   invisibleHands[i],
                                   deck.size(),
                                   timeCounters,
                                   lives,
                                   timer);
            }

            int playerTurn = turn % players.size();

            Player player = players.get(playerTurn);
            PlayerResponse response = player.play();
            
            logger.info("Turn " + turn + " played by: " + player.getName());

            if (response.getResponseType() == PlayerResponseType.DISCARD) {
                DiscardResponse discardResponse = (DiscardResponse) response;
                this.handlePlayerDiscard(discardResponse, playerTurn);
            } else if (response.getResponseType() == PlayerResponseType.PLAY) {
                PlayResponse playResponse = (PlayResponse) response;
                this.handlePlayerPlay(playResponse, playerTurn);
            } else if (response.getResponseType() == PlayerResponseType.TELL) {
                TellResponse tellResponse = (TellResponse) response;
                this.handlePlayerTell(tellResponse, playerTurn);
            } else {
                throw new RuntimeException("Invalid state reached.");
            }
            
            turn++;
        }
        
        return board;
    }

    public void handlePlayerTell(TellResponse tellResponse, int playerTurn) {
        timeCounters--;
        if (timeCounters < 0) {
            throw new RuntimeException(
                    "Rule Broken: negative time counter reached.");
        }


        int playerPos = tellResponse.getPlayerPos();
        VisibleHand visibleHand = visibleHands[playerPos];
        InvisibleHand invisibleHand = invisibleHands[playerPos];

        if (tellResponse.isColorInformation()) {
            Color color = tellResponse.getColor();

            Set<Integer> matchedCards = visibleHand.queryColor(color);
            if (matchedCards.isEmpty()) {
                throw new RuntimeException("Player told another player about 0 cards.");
            }

            invisibleHand.update(matchedCards, color);

        } else if (tellResponse.isNumberInformation()) {

            int number = tellResponse.getNumber();

            Set<Integer> matchedCards = visibleHand.queryNumber(number);
            if (matchedCards.isEmpty()) {
                throw new RuntimeException("Player told another player about 0 cards.");
            }

            invisibleHand.update(matchedCards, number);

        } else {
            throw new RuntimeException("Invalid state reached for tell response.");
        }
    }

    public void handlePlayerPlay(PlayResponse playResponse, int playerTurn) {
        int cardPos = playResponse.getCardPos();
        Card playedCard = visibleHands[playerTurn].remove(cardPos);
        invisibleHands[playerTurn].removeCard(cardPos);

        boolean isPlayable = board.playCard(playedCard);

        if (!isPlayable) {
            lives--;
            discard.discard(playedCard);
        } else {

            if (playedCard.getNumber() == 5) {
                timeCounters++;
            }

            if (board.isComplete()) {
                gameWon = true;
            }
        }

        for (int i = 0; i < players.size(); i++) {
            if (i != playerTurn) {
                Player otherPlayer = players.get(i);
                otherPlayer.listen(playResponse, playerTurn);
            }
        }
    }

    public void handlePlayerDiscard(DiscardResponse discardResponse,
                                    int playerTurn) {
        logger.info("Handling player discard with position: " + discardResponse.getCardPosition());
        logger.info("Original card ages: " + Arrays.toString(invisibleHands[playerTurn].getCardAges()));
        
        timeCounters++;

        int cardPos = discardResponse.getCardPosition();
        Card discardCard = visibleHands[playerTurn].remove(cardPos);
        discard.discard(discardCard);
        invisibleHands[playerTurn].removeCard(cardPos);

        for (int i = 0; i < players.size(); i++) {
            if (i != playerTurn) {
                Player otherPlayer = players.get(i);
                otherPlayer.listen(discardResponse, playerTurn);
            }
        }

        if (deck.hasNext()) {
            Card newCard = deck.next();

            PlayerDrawAction drawAction = new PlayerDrawAction(newCard);

            for (int i = 0; i < players.size(); i++) {
                if (i != playerTurn) {
                    Player otherPlayer = players.get(i);
                    otherPlayer.listen(drawAction, playerTurn);
                }
            }

            visibleHands[playerTurn].addCard(newCard);
            invisibleHands[playerTurn].addCard();
        } else {
            logger.info("Deck is out, timer: " + timer);
            timer--;
        }
        
        logger.info("New card ages: " + Arrays.toString(invisibleHands[playerTurn].getCardAges()));
    }

    public boolean gameOver() {
        if (gameWon) {
            return true;
        }

        if (lives == 0 || timer == 0) {
            return true;
        } else {
            return false;
        }
    }
}
