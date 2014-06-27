/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.core;

import ethier.alex.hanabi.deck.Color;
import ethier.alex.hanabi.game.actions.PlayerDrawAction;
import ethier.alex.hanabi.game.actions.PlayerResponse;

/**

 @author alex
 */
public interface Player {
    public void listen(PlayerDrawAction response, int playerPos);
    
    public void listen(PlayerResponse playerResponse, int playerPos);

    public void reveal(int[] cards, int number);

    public void reveal(int[] cards, Color color);
    
    public PlayerResponse play();
    
    public void setPosition(int position);
}
