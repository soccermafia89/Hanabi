/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ethier.alex.hanabi;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.util.HashSet;
import java.util.Set;

/**

 @author alex
 */
public class Card {

    private final int number;
    private final Color color;

    public Card(Color myColor, int myNumber) {
        number = myNumber;
        color = myColor;
    }

    public String printCard(String id) {
        return color + " " + number;
    }
}