package ethier.alex.hanabi.actions;

public class DiscardResponse implements PlayerResponse {
	
	PlayerResponseType playerResponseType;
	int cardPosition;
	
	public DiscardResponse(int pos) {
		playerResponseType = PlayerResponseType.DISCARD;
		cardPosition = pos;
	}

	@Override
	public PlayerResponseType getResponseType() {
		return playerResponseType;
	}
	
	public int getCardPosition() {
            return cardPosition;
        }

}
