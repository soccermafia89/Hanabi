/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**

 @author alex
 */
public class Deck implements Iterator<Card> {

    private Iterator<Card> it;
    private final List myDeck;
    int count = 0;

    public Deck() {

        myDeck = new ArrayList<Card>();

        for (Color color : Color.values()) {
            myDeck.add(new Card(color, 1));
            myDeck.add(new Card(color, 1));
            myDeck.add(new Card(color, 1));
            
            myDeck.add(new Card(color, 2));
            myDeck.add(new Card(color, 2));
            
            myDeck.add(new Card(color, 3));
            myDeck.add(new Card(color, 3));
            
            myDeck.add(new Card(color, 4));
            myDeck.add(new Card(color, 4));
            
            myDeck.add(new Card(color, 5));
        }
        
        Collections.shuffle(myDeck);
        it = myDeck.iterator();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Card next() {
        return it.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
