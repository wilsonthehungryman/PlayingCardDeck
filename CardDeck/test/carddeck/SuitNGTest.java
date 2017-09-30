/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson
 */
public class SuitNGTest {
    
    public SuitNGTest() {
    }

    /**
     * Test of values method, of class Suit.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Suit[] expResult = { Suit.SPADES, Suit.CLUBS, Suit.HEARTS, Suit.DIAMONDS };
        Suit[] result = Suit.values();
        assertEquals(result, expResult);
    }

    /**
     * Test of valueOf method, of class Suit.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "DIAMONDS";
        Suit expResult = Suit.DIAMONDS;
        Suit result = Suit.valueOf(name);
        assertEquals(result, expResult);
    }

    /**
     * Test of toString method, of class Suit.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Suit instance = Suit.SPADES;
        String expResult = "Spades";
        String result = instance.toString();
        assertEquals(result, expResult);
    }
    
}
