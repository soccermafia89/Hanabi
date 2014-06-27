package ethier.alex.hanabi.game.actions;

import ethier.alex.hanabi.deck.Card;

public class DiscardResponse implements PlayerResponse {
	
	PlayerResponseType playerResponseType;
	Card discardCard;
	
	public DiscardResponse(Card card) {
		playerResponseType = PlayerResponseType.DISCARD;
		discardCard = card;
	}

	@Override
	public PlayerResponseType getResponseType() {
		return playerResponseType;
	}
	
	public Card getCard() {
		return discardCard;
	}

}
