/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.test;

import com.google.common.collect.BiMap;
import ethier.alex.hanabi.actions.*;
import ethier.alex.hanabi.core.Player;
import ethier.alex.hanabi.deck.Color;
import ethier.alex.hanabi.state.Board;
import ethier.alex.hanabi.state.Discard;
import ethier.alex.hanabi.state.InvisibleHand;
import ethier.alex.hanabi.state.VisibleHand;
import java.util.*;
import org.apache.log4j.Logger;

/**

 @author alex
 */
public class DumbPlayer implements Player {
    
    private static Logger logger = Logger.getLogger(DumbPlayer.class);

    private int deckSize;
    private int lives;
    private InvisibleHand hand;
    private int position;
    private Map<Integer, String> playerPositions;
    private String name;
    private Map<Integer, VisibleHand> playerHands;
    private int timeCounters;

    public DumbPlayer() {
    }

    @Override
    public void listen(PlayerDrawAction response, int playerPos) {
        // Do nothing.
    }

    @Override
    public void reveal(int[] cards, int number) {
        // Do nothing.
    }

    @Override
    public void reveal(int[] cards, Color color) {
        // Do nothing.
    }

    @Override
    public PlayerResponse play() {

        if (deckSize > 0) {
            if (Math.random() < 0.55) {
                return discardCard();
            }
        }

        if (lives > 0) {
            if (Math.random() < 0.20) {
                return playCard();
            }
        }
        
        if(timeCounters > 0) {
            return tellPlayer();
        }

        return playCard();
    }

    private TellResponse tellPlayer() {

        int playerPos = position;
        while (playerPos == position) {
            playerPos = (int) (playerPositions.size() * Math.random());
        }

        VisibleHand visibleHand = playerHands.get(playerPos);

        int cardPos = (int) (Math.random() * 5);
        while (visibleHand.getHand()[cardPos] == null) {
            cardPos = (int) (Math.random() * 5);
        }
        
        

        double cardColorBool = Math.random();
        if (cardColorBool > 0.5) {
            Color color = visibleHand.getHand()[cardPos].getColor();
            return new TellResponse(playerPos, color);
        } else {
            int number = visibleHand.getHand()[cardPos].getNumber();
            return new TellResponse(playerPos, number);
        }
    }

    private PlayResponse playCard() {
        int[] cardAges = hand.getCardAges();
        List<Integer> availableCards = new ArrayList<Integer>();
        for (int i = 0; i < cardAges.length; i++) {
            if (cardAges[i] != -1) {
                availableCards.add(i);
            }
        }

        int randPos = (int) (Math.random() * availableCards.size());
        logger.info("Playing Card at Position: " + availableCards.get(randPos));
        return new PlayResponse(availableCards.get(randPos));
    }

    private DiscardResponse discardCard() {
        int[] cardAges = hand.getCardAges();
        List<Integer> availableCards = new ArrayList<Integer>();
        for (int i = 0; i < cardAges.length; i++) {
            if (cardAges[i] != -1) {
                availableCards.add(i);
            }
        }

        int randPos = (int) (Math.random() * availableCards.size());
        
        logger.info("Players card ages: " + Arrays.toString(cardAges));
        logger.info("Discarding Card at Position: " + availableCards.get(randPos));
        return new DiscardResponse(availableCards.get(randPos));
    }

    @Override
    public void setPositions(int myPosition, BiMap<Integer, String> myPlayerPositions) {
        // Do nothing.
        position = myPosition;
        playerPositions = myPlayerPositions;
    }

    @Override
    public void listen(PlayerResponse playerResponse, int playerPos) {
        // Do nothing
    }

    @Override
    public void updateState(Board board, Discard discard,
                            Map<Integer, VisibleHand> otherPlayerHands,
                            InvisibleHand myHand,
                            int myDeckSize,
                            int myTimeCounters,
                            int myLives,
                            int timer) {

        deckSize = myDeckSize;
        lives = myLives;        
        hand = myHand;
        playerHands = otherPlayerHands;
        timeCounters = myTimeCounters;
    }

    @Override
    public void setName(String myName) {
        name = myName;
    }

    @Override
    public String getName() {
        return name;
    }
}
