package ethier.alex.hanabi.actions;

import java.util.Set;

import ethier.alex.hanabi.deck.Color;

public class TellResponse implements PlayerResponse {
	
	PlayerResponseType playerResponseType;
	Color color;
	int number;
	boolean isColorInformation;
	boolean isNumberInformation;
        int playerPos;
	
	
	public TellResponse(int player, Color myColor) {
		playerResponseType = PlayerResponseType.TELL;
		color = myColor;
		isColorInformation = true;
		isNumberInformation = false;
                playerPos = player;
	}
	
	public TellResponse(int player, int myNumber) {
		playerResponseType = PlayerResponseType.TELL;
		number = myNumber;
		isColorInformation = false;
		isNumberInformation = true;
                playerPos = player;
	}

	@Override
	public PlayerResponseType getResponseType() {
		return playerResponseType;
	}
	
	public boolean isColorInformation() {
		return isColorInformation;
	}
	
	public boolean isNumberInformation() {
		return isNumberInformation;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getNumber() {
		return number;
	}
        
        public int getPlayerPos() {
            return playerPos;
        }
}
