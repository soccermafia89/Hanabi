package ethier.alex.hanabi.actions;

import java.util.Set;

import ethier.alex.hanabi.deck.Color;

public class TellResponse implements PlayerResponse {
	
	PlayerResponseType playerResponseType;
	Set<Integer> cardPositions;
	Color color;
	int number;
	boolean isColorInformation;
	boolean isNumberInformation;
	
	
	public TellResponse(int player, Set<Integer> myCardPositions, Color myColor) {
		playerResponseType = PlayerResponseType.TELL;
		color = myColor;
		isColorInformation = true;
		isNumberInformation = false;
		cardPositions = myCardPositions;
	}
	
	public TellResponse(int player, Set<Integer> myCardPositions, int myNumber) {
		playerResponseType = PlayerResponseType.TELL;
		number = myNumber;
		isColorInformation = false;
		isNumberInformation = true;
		cardPositions = myCardPositions;
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
	
	public Set<Integer> getCardPositions() {
		return cardPositions;
	}
}
