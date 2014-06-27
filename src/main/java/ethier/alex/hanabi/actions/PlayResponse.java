package ethier.alex.hanabi.actions;

import ethier.alex.hanabi.deck.Card;

public class PlayResponse implements PlayerResponse {
	
	PlayerResponseType playerResponseType;
	Card playedCard;
	
	public PlayResponse(Card card) {
		playerResponseType = PlayerResponseType.PLAY;
		playedCard = card;
	}

	@Override
	public PlayerResponseType getResponseType() {
		return playerResponseType;
	}
	
	public Card getCard() {
		return playedCard;
	}
	
	
//    public PlayerResponseType responseType;
//    int discardNumber;
//    
//    public PlayerResponse() {
//        
//    }
//    
//    public void setType(PlayerResponseType myResponseType) {
//        responseType = myResponseType;
//    }
//    
//    public void setDiscardNumber(int myDiscardNumber) {
//        discardNumber = myDiscardNumber;
//    }
}
