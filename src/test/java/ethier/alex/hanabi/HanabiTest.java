package ethier.alex.hanabi;

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

//        Hanabi hanabi = new Hanabi();
//        Deck deck = new Deck();
//        while(deck.hasNext()) {
//            Card card = deck.next();
//            System.out.println("Next Card: " + card.printCard(id));
//        }

    }
}
