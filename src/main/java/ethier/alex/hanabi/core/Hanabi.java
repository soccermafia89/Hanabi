/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.core;

import java.util.List;

import ethier.alex.hanabi.board.Board;
import ethier.alex.hanabi.board.Discard;
import ethier.alex.hanabi.deck.Card;
import ethier.alex.hanabi.deck.Deck;
import ethier.alex.hanabi.game.actions.DiscardResponse;
import ethier.alex.hanabi.game.actions.PlayResponse;
import ethier.alex.hanabi.game.actions.PlayerDrawAction;
import ethier.alex.hanabi.game.actions.PlayerResponse;
import ethier.alex.hanabi.game.actions.PlayerResponseType;

/**
 * @author alex
 */
public class Hanabi {

	Board board;
	Deck deck;
	Discard discard;
	List<Player> players;

	int lives;
	int timer;
	boolean gameWon;

	public Hanabi() {
		deck = new Deck();
		board = new Board();
		discard = new Discard();
		
		System.out.println("CHECK LIFE COUNT");
		lives = 3;
		timer = players.size();
		gameWon = false;
	}
	
	public void setPlayers(List<Player> myPlayers) {
		players = myPlayers;
		
		for(int i=0; i < players.size();i++) {
			Player player = players.get(i);
			player.setPosition(i);
		}
	}

	public void playGame() {
		int turn = 0;

		while (!gameOver()) {
			int playerTurn = turn % players.size();

			Player player = players.get(playerTurn);
			PlayerResponse response = player.play();

			if (response.getResponseType() == PlayerResponseType.DISCARD) {
				DiscardResponse discardResponse = (DiscardResponse) response;
				this.handlePlayerDiscard(discardResponse, playerTurn);
			} else if (response.getResponseType() == PlayerResponseType.PLAY) {
				PlayResponse playResponse = (PlayResponse) response;
				this.handlePlayerPlay(playResponse, playerTurn);
			} else if (response.getResponseType() == PlayerResponseType.TELL) {
			} else {
				throw new RuntimeException("Invalid state reached.");
			}
		}
	}
	
	public void handlePlayerPlay(PlayResponse playResponse, int playerTurn) {
		Card playedCard = playResponse.getCard();
		boolean isPlayable = board.playCard(playedCard);
		
		if(!isPlayable) {
			lives--;
			discard.discard(playedCard);
		} else if(board.isComplete()) {
			gameWon = true;
		}
		
		for (int i = 0; i < players.size(); i++) {
			if (i != playerTurn) {
				Player otherPlayer = players.get(i);
				otherPlayer.listen(playResponse, playerTurn);
			}
		}
	}

	public void handlePlayerDiscard(DiscardResponse discardResponse, int playerTurn) {
		discard.discard(discardResponse.getCard());
		
		for (int i = 0; i < players.size(); i++) {
			if (i != playerTurn) {
				Player otherPlayer = players.get(i);
				otherPlayer.listen(discardResponse, playerTurn);
			}
		}
		
		if (deck.hasNext()) {
			Card card = deck.next();

			PlayerDrawAction drawAction = new PlayerDrawAction(card);

			for (int i = 0; i < players.size(); i++) {
				if (i != playerTurn) {
					Player otherPlayer = players.get(i);
					otherPlayer.listen(drawAction, playerTurn);
				}
			}
		} else {
			timer--;
		}
	}

	public boolean gameOver() {
		if(gameWon) {
			return true;
		}
		
		if (lives == 0 || timer == 0) {
			return true;
		} else {
			return false;
		}
	}
}
