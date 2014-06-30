package ethier.alex.hanabi.test;

import ethier.alex.hanabi.core.Hanabi;
import ethier.alex.hanabi.core.Player;
import ethier.alex.hanabi.state.Board;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

/**

 @author alex
 */
public class HanabiTest {

    private static Logger logger = Logger.getLogger(HanabiTest.class);

    @BeforeClass
    public static void setUpClass() {
        BasicConfigurator.configure();
    }

    @Test
    public void testDeck() throws Exception {
        System.out.println("");
        System.out.println("");
        System.out.println("********************************************");
        System.out.println("********         Test Deck         *********");
        System.out.println("********************************************");
        System.out.println("");
        System.out.println("");
        
        DumbPlayer dumbPlayer1 = new DumbPlayer();
        dumbPlayer1.setName("testPlayer1");
        
        DumbPlayer dumbPlayer2 = new DumbPlayer();
        dumbPlayer2.setName("testPlayer2");
        
        List<Player> players = new ArrayList<Player>();
        players.add(dumbPlayer1);
        players.add(dumbPlayer2);

        Hanabi hanabi = new Hanabi(players);
        
        Board result = hanabi.playGame();
        
        int score = result.getScore();
        
        System.out.println("Score: " + score);

    }
}
