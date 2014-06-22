/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi;

/**

 @author alex
 */
public interface Player {
    public void listen(GameResponse response, int playerPos);

    public void reveal(int[] cards, int number);

    public void reveal(int[] cards, Color color);
    
    public PlayerResponse play();
    
    public void setPosition(int position);
}
