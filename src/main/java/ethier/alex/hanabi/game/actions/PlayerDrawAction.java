/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.game.actions;

import ethier.alex.hanabi.core.ActionType;
import ethier.alex.hanabi.deck.Card;

/**

 @author alex
 */

// Used to send messages to other players, may want to wrap PlayerResponse
public class PlayerDrawAction {
	
	private PlayerResponse playerResponse;
	private ActionType actionType;
	private Card drawCard;
	
    public PlayerDrawAction(Card myCard) {
    	actionType = ActionType.DRAW;
    	drawCard = myCard;
    }
    
    public void setPlayerResponse(PlayerResponse myPlayerResponse) {
    	playerResponse = myPlayerResponse;
    }
    
    public PlayerResponse getPlayerResponse() {
    	return playerResponse;
    }
    
//    public void setDrawCard(Card myCard) {
//    	drawCard = myCard;
//    }
    
    public Card getDrawCard() {
    	return drawCard;
    }
    
    public ActionType getActionType() {
    	return actionType;
    }
    
    
}
