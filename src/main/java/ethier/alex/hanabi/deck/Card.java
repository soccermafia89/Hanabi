/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.deck;

/**
 * @author alex
 */
public class Card {

	private final int number;
	private final Color color;

	public Card(Color myColor, int myNumber) {
		number = myNumber;
		color = myColor;
	}

	public Color getColor() {
		return color;
	}

	public int getNumber() {
		return number;
	}

	public String toString() {
		return color + " " + number;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Card) {
			Card otherCard = (Card) object;

			if (color == otherCard.getColor() && number == otherCard.getNumber()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
