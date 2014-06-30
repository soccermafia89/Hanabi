package ethier.alex.hanabi.state;

import java.util.HashSet;
import java.util.Set;

import ethier.alex.hanabi.actions.TellResponse;
import ethier.alex.hanabi.deck.Color;
import java.util.Arrays;
import org.apache.log4j.Logger;

// The hand of the player.  They can gain information about their own hand through other player's actions.
public class InvisibleHand {

    private static Logger logger = Logger.getLogger(InvisibleHand.class);
    Color[] colorIndex;
    int[] numberIndex;
    int[] cardAges; // A card with age -1 means no card.

    public InvisibleHand() {
        colorIndex = new Color[5];
        numberIndex = new int[5];
        cardAges = new int[5];

        for (int i = 0; i < numberIndex.length; i++) {
            numberIndex[i] = -1;
        }

        for (int i = 0; i < cardAges.length; i++) {
            cardAges[i] = (5 - 1) - i;
        }
    }

    // When a card is played or discarded, the information is removed from their hand.
    public void removeCard(int position) {
                
        colorIndex[position] = null;
        numberIndex[position] = -1;

        int discardCardAge = cardAges[position];
        for(int i=0; i < cardAges.length;i++) {
            int cardAge = cardAges[i];
            
            if(cardAge != -1 && cardAge < discardCardAge) {
                cardAges[i] = cardAge + 1;
            }
        }
        
        cardAges[position] = -1;
    }

    public void addCard() {
        
        for (int i = 0; i < cardAges.length; i++) {
            int cardAge = cardAges[i];

            if (cardAge == -1) {
                cardAges[i] = 0;
                break;
            }
        }
    }

    public int[] getCardAges() {
        return cardAges;
    }

    public Set<Integer> queryColor(Color color) {
        Set<Integer> cardPositions = new HashSet<Integer>();

        for (int i = 0; i < colorIndex.length; i++) {
            Color myColor = colorIndex[i];

            if (myColor != null && myColor == color) {
                cardPositions.add(i);
            }
        }

        return cardPositions;
    }

    public Set<Integer> queryNumber(int number) {
        Set<Integer> cardPositions = new HashSet<Integer>();

        for (int i = 0; i < numberIndex.length; i++) {
            int myNumber = numberIndex[i];

            if (myNumber != -1 && myNumber == number) {
                cardPositions.add(i);
            }
        }

        return cardPositions;
    }

    public Set<Integer> queryCard(Color color, int number) {
        Set<Integer> cardPositions = new HashSet<Integer>();

        for (int i = 0; i < numberIndex.length; i++) {
            int myNumber = numberIndex[i];
            Color myColor = colorIndex[i];

            if (myNumber != -1 && myColor != null && myNumber == number && myColor == color) {
                cardPositions.add(i);
            }
        }

        return cardPositions;
    }

    // A player may add additional inferenced information to their own hand
    public void update(Set<Integer> cardPositions, Color color) {

        for (int cardPosition : cardPositions) {
            colorIndex[cardPosition] = color;
        }
    }

    // A player may add additional inferenced information to their own hand
    public void update(Set<Integer> cardPositions, int number) {

        for (int cardPosition : cardPositions) {
            numberIndex[cardPosition] = number;
        }
    }

    // Update invisible hand based on game's TellResponse
    public void update(TellResponse tellResponse, Set<Integer> cardPositions) {

        if (tellResponse.isColorInformation()) {
            Color color = tellResponse.getColor();
            for (int cardPosition : cardPositions) {
                colorIndex[cardPosition] = color;
            }
        } else if (tellResponse.isNumberInformation()) {
            int number = tellResponse.getNumber();
            for (int cardPosition : cardPositions) {
                numberIndex[cardPosition] = number;
            }
        } else {
            throw new RuntimeException("Invalid state reached.");
        }
    }
}
