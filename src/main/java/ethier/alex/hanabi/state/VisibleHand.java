package ethier.alex.hanabi.state;

import com.google.common.collect.Multiset;

import ethier.alex.hanabi.deck.Card;

// The hand of other players which is always visible.
public class VisibleHand {
	Multiset<Card> cardIndex;
	Card[] hand;
	
	public VisibleHand(Card[] initialHand) {
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
}
