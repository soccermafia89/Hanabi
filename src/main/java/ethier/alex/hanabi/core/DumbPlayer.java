/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.core;

import ethier.alex.hanabi.actions.*;
import ethier.alex.hanabi.deck.Color;
import ethier.alex.hanabi.state.Board;
import ethier.alex.hanabi.state.Discard;
import ethier.alex.hanabi.state.InvisibleHand;
import ethier.alex.hanabi.state.VisibleHand;
import java.util.*;

/**

 @author alex
 */
public class DumbPlayer implements Player {

    private int deckSize;
    private int lives;
    private InvisibleHand hand;
    private int position;
    private Map<Integer, String> playerPositions;
    private String name;

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

        PlayerResponse returnResponse;

        if (deckSize > 0) {
            if (Math.random() > 0.33) {
                return discardCard();
            }
        }
        
        if(lives > 0) {
            if(Math.random() > 0.75) {
                return playCard();
            }
        }

        return null;
    }
    
    private TellResponse tellPlayer() {
        //TODO:
        
        return null;
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
        System.out.println("Playing Card at Position: " + availableCards.get(randPos));
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
        System.out.println("Discarding Card at Position: " + availableCards.get(randPos));
        return new DiscardResponse(availableCards.get(randPos));
    }

    @Override
    public void setPositions(int myPosition, Map<Integer, String> myPlayerPositions) {
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
                            Map<Integer, VisibleHand> playerHands,
                            InvisibleHand myHand,
                            int myDeckSize,
                            int timeCounters,
                            int myLives,
                            int timer) {

        deckSize = myDeckSize;
        lives = myLives;
        hand = myHand;
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
