package ethier.alex.hanabi.state;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import ethier.alex.hanabi.deck.Card;

public class Discard {
	
	Multiset<Card> cardIndex;
	
	public Discard() {
		cardIndex = HashMultiset.create();
	}
	
	public void discard(Card card) {
		cardIndex.add(card);
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
