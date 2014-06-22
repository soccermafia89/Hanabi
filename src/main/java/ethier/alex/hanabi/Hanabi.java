/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi;

import java.util.ArrayList;
import java.util.List;

/**

 @author alex
 */
public class Hanabi {

    Deck deck;
    List<Player> players;
    
    int lives;
    int timer = -1;

    public Hanabi() {
        deck = new Deck();

        players = new ArrayList<Player>();

        Player player1 = new DumbPlayer();
        player1.setPosition(0);

        DumbPlayer player2 = new DumbPlayer();
        player2.setPosition(1);
        players.add(player1);
        players.add(player2);
    }

    public void play() {
        int turn = 0;

        while (!gameOver()) {
            int playerTurn = turn % players.size();

            Player player = players.get(playerTurn);
            PlayerResponse response = player.play();

            if (response.responseType == ResponseType.DISCARD) {
            } else if (response.responseType == ResponseType.DISCARD) {
                if (deck.hasNext()) {
                    Card card = deck.next();

                    GameResponse gameResponse = new GameResponse();
                    for (int i = 0; i < players.size(); i++) {
                        if (i != playerTurn) {
                            Player otherPlayer = players.get(i);
                            otherPlayer.listen(gameResponse, playerTurn);
                        }

                    }
                } else {
                    
                }
            } else if (response.responseType == ResponseType.PLAY) {
            } else if (response.responseType == ResponseType.TELL) {
            } else {
                throw new RuntimeException("Invalid state reached.");
            }
        }
    }

    public boolean gameOver() {
        if(lives == 0 || timer == 0) {
            return true;
        } else {
            return false;
        }
    }
}
