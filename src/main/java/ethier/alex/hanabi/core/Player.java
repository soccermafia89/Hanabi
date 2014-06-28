/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.core;

import java.util.Map;

import ethier.alex.hanabi.actions.PlayerDrawAction;
import ethier.alex.hanabi.actions.PlayerResponse;
import ethier.alex.hanabi.deck.Color;
import ethier.alex.hanabi.state.Board;
import ethier.alex.hanabi.state.Discard;
import ethier.alex.hanabi.state.InvisibleHand;
import ethier.alex.hanabi.state.VisibleHand;

/**

 @author alex
 */
public interface Player {
    public void listen(PlayerDrawAction response, int playerPos);
    
    public void listen(PlayerResponse playerResponse, int playerPos);

    public void reveal(int[] cards, int number);

    public void reveal(int[] cards, Color color);
    
    public PlayerResponse play();
    
    public void setName(String name);
    
    public String getName();
    
    public void setPositions(int position, Map<Integer, String> playerPositions);
    
    public void updateState(Board board, 
                            Discard discard, 
                            Map<Integer, VisibleHand> playerHands, 
                            InvisibleHand myHand, 
                            int deckSize,
                            int timeCounters, 
                            int lives, 
                            int timer);
}
