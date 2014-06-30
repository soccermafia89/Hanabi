package ethier.alex.hanabi.state;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import ethier.alex.hanabi.deck.Card;
import ethier.alex.hanabi.deck.Color;
import java.util.HashSet;
import java.util.Set;

// The hand of other players which is always visible.
public class VisibleHand {
	Multiset<Card> cardIndex;
	Card[] hand;
	
	public VisibleHand(Card[] initialHand) {
            
            cardIndex = HashMultiset.create();
            
		for(Card card : initialHand) {
			cardIndex.add(card);
		}
		
		hand = initialHand;
	}
	
	public Card remove(int position) {
		Card card = hand[position];
		hand[position] = null;
		cardIndex.remove(card);
		
		return card;
	}
	
	public void addCard(Card newCard) {
		for(int i=0; i < hand.length;i++) {
			if(hand[i] == null) {
				hand[i] = newCard;
			}
		}
		
		cardIndex.add(newCard);
	}
	
	public Card[] getHand() {
		return hand;
	}
	
	public int countInstances(Card card) {
		return cardIndex.count(card);
	}
	
	public boolean containsBoth(Card card) {
		return cardIndex.count(card) == 2;
	}
	
	public boolean contains(Card card) {
		return cardIndex.count(card) > 0;
	}
        
        public Set<Integer> queryColor(Color color) {
            Set<Integer> cardSet = new HashSet<Integer>();
            
            for(int i=0; i < hand.length; i++) {
                if(hand[i] != null) {
                    Card card = hand[i];
                    
                    if(card.getColor() == color) {
                        cardSet.add(i);
                    }
                }
            }
            
            return cardSet;
        }
        
        public Set<Integer> queryNumber(int number) {
            Set<Integer> cardSet = new HashSet<Integer>();
            
            for(int i=0; i < hand.length; i++) {
                if(hand[i] != null) {
                    Card card = hand[i];
                    
                    if(card.getNumber() == number) {
                        cardSet.add(i);
                    }
                }
            }
            
            return cardSet;
        }
}
