package ethier.alex.hanabi.actions;

import ethier.alex.hanabi.deck.Card;

public class PlayResponse implements PlayerResponse {
	
	PlayerResponseType playerResponseType;
	int cardPos;
	
	public PlayResponse(int pos) {
		playerResponseType = PlayerResponseType.PLAY;
		cardPos = pos;
	}

	@Override
	public PlayerResponseType getResponseType() {
		return playerResponseType;
	}
	
	public int getCardPos() {
		return cardPos;
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
