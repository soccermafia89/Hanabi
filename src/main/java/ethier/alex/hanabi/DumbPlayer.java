/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi;

/**

 @author alex
 */
public class DumbPlayer implements Player {
    
    public DumbPlayer() {
    }

    @Override
    public void listen(GameResponse response, int playerPos) {
        
    }

    @Override
    public void reveal(int[] cards, int number) {
    }

    @Override
    public void reveal(int[] cards, Color color) {
    }
    
    @Override
    public PlayerResponse play() {
        PlayerResponse response = new PlayerResponse();
        
        response.setType(ResponseType.DISCARD);
        int discardNumber = (int) (5*Math.random());
        response.setDiscardNumber(discardNumber);
        
        return response;
    }
    
    @Override
    public void setPosition(int position) {
        
    }
}
