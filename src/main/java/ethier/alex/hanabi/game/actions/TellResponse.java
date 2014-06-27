package ethier.alex.hanabi.game.actions;

public class TellResponse implements PlayerResponse {
	
	PlayerResponseType playerResponseType;
	
	public TellResponse() {
		playerResponseType = PlayerResponseType.TELL;
	}

	@Override
	public PlayerResponseType getResponseType() {
		return playerResponseType;
	}

}
