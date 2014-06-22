/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi;

/**

 @author alex
 */

// How a player reacts on their turn.
public class PlayerResponse {
    
    public ResponseType responseType;
    int discardNumber;
    
    public PlayerResponse() {
        
    }
    
    public void setType(ResponseType myResponseType) {
        responseType = myResponseType;
    }
    
    public void setDiscardNumber(int myDiscardNumber) {
        discardNumber = myDiscardNumber;
    }
}
