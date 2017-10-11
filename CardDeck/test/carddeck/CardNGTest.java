/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson
 */
public class CardNGTest {
    private Card card;
    private final String cardName = "The Ace of Spades";
    private final int cardValue = 99;
    
    public CardNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        card = new Card(cardName, cardValue);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getName method, of class Card.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals(card.getName(), cardName);
    }

    /**
     * Test of getValue method, of class Card.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        assertEquals(card.getValue(), cardValue);
    }

    /**
     * Test of getPoints method, of class Card.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        assertEquals(card.getPoints(), cardValue);
    }

    /**
     * Test of setValue method, of class Card.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        card.setValue(11);
        assertEquals(card.getValue(), 11);
    }

    /**
     * Test of setPoint method, of class Card.
     */
    @Test
    public void testSetPoint() {
        System.out.println("setPoint");
        card.setPoint(22);
        assertEquals(card.getPoints(), 22);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals(card.toString(), cardName);
    }

    /**
     * Test of hashCode method, of class Card.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Card duplicate = new Card(cardName, cardValue);
        Card different = new Card("Somthing");
        assertEquals(card.hashCode(), card.hashCode());
        assertEquals(card.hashCode(), duplicate.hashCode());
        assertNotEquals(card.hashCode(), different.hashCode());
    }

    /**
     * Test of equals method, of class Card.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Object();
        Card duplicate = new Card(cardName, cardValue);
        Card different = new Card("Somthing");
        
        assertTrue(card.equals(card));
        assertTrue(card.equals(duplicate));
        assertTrue(duplicate.equals(card));
        assertFalse(card.equals(different));
        assertFalse(different.equals(card));
        assertFalse(card.equals(obj));
    }
    
}
