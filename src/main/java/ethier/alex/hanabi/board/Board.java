package ethier.alex.hanabi.board;

import ethier.alex.hanabi.deck.Card;
import ethier.alex.hanabi.deck.Color;

// Handles the state of played cards.
public class Board {
	
	private boolean[][] board; // True means the card is played.
	
	public Board() {
		
		int numColors = Color.values().length;
		int numNumbers = 5;
		
		board = new boolean[numColors][numNumbers];
		for(int i=0; i < numColors; i++) {
			for(int j=0; j < numNumbers; j++) {
				board[i][j] = false;
			}
		}
	}
	
	// Return true if card is played successfully, return false if card is unplayable.
	public boolean playCard(Card card) {
		int colorOrdinal = card.getColor().ordinal();
		int number = card.getNumber();
		
		if(number == 1 && board[colorOrdinal][0] == false) {
			board[colorOrdinal][0] = true;
			return true;
		} else if(board[colorOrdinal][number -1] == true && board[colorOrdinal][number] == false) {
			board[colorOrdinal][number] = true;
			return true;
		} else {
			return false;
		}
	}
	
	//return true if the board is complete
	public boolean isComplete() {
		for(int i=0; i < Color.values().length; i++) {
			for(int j=0; j < 5; j++) {
				if(board[i][j] == false) {
					return false;
				}
			}
		}
		
		return true;
	}
}
