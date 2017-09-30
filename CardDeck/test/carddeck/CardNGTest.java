/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson
 */
public class CardNGTest {
    
    public CardNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getFace method, of class Card.
     */
    @Test
    public void testGetFace() {
        System.out.println("getFace");
        Card instance = new Card(Face.JACK, Suit.CLUBS);
        Face expResult = Face.JACK;
        Face result = instance.getFace();
        assertEquals(result, expResult);
    }

    /**
     * Test of getPoints method, of class Card.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        Card instance = new Card(Face.JACK, Suit.CLUBS);
        int expResult = Face.JACK.getValue();
        int result = instance.getPoints();
        assertEquals(result, expResult);
    }

    /**
     * Test of getSuit method, of class Card.
     */
    @Test
    public void testGetSuit() {
        System.out.println("getSuit");
        Card instance = new Card(Face.JACK, Suit.CLUBS);
        Suit expResult = Suit.CLUBS;
        Suit result = instance.getSuit();
        assertEquals(result, expResult);
    }

    /**
     * Test of setFace method, of class Card.
     */
    @Test
    public void testSetFace() {
        System.out.println("setFace");
        Face face = Face.KING;
        Card instance = new Card(Face.JACK, Suit.CLUBS);
        instance.setFace(face);
        assertEquals(instance.getFace(), face);
    }

    /**
     * Test of setPoints method, of class Card.
     */
    @Test
    public void testSetPoints() {
        System.out.println("setPoints");
        int points = 50;
        Card instance = new Card(Face.JACK, Suit.CLUBS);;
        instance.setPoints(points);
        assertEquals(instance.getPoints(), points);
    }

    /**
     * Test of setSuit method, of class Card.
     */
    @Test
    public void testSetSuit() {
        System.out.println("setSuit");
        Suit suit = Suit.HEARTS;
        Card instance = new Card(Face.JACK, Suit.CLUBS);
        instance.setSuit(suit);
        assertEquals(instance.getSuit(), suit);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Card instance = new Card(Face.JACK, Suit.CLUBS);
        String expResult = "The Jack of Clubs";
        String result = instance.toString();
        assertEquals(result, expResult);
    }
    
}
