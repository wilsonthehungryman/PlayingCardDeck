/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson
 */
public class CardDeckNGTest {
    private Card[] cards;
    private CardDeck deck;
    
    public CardDeckNGTest() {
    }

    @BeforeMethod
    public void setUp(){
        cards = new Card[]{ new Card("1", 10), new Card("2"), new Card("4", 4), new Card("8", 80) };
        deck = new CardDeck();
    }

    private void populateDeck(){
        for(Card c : cards){
            deck.append(c);
        }
    }

    /**
     * Test of createDeck method, of class CardDeck.
     */
    @Test
    public void testCreateDeck_0args() {
        System.out.println("createDeck");
        CardDeck instance = new CardDeck();
        assertEquals(instance.size, 0);
        assertEquals(instance.head, null);
        assertEquals(instance.tail, null);
    }


    /**
     * Test of isEmpty method, of class CardDeck.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        CardDeck instance = new CardDeck();
        assertEquals(instance.isEmpty(), true);
        instance.add(cards[0]);
        assertEquals(instance.isEmpty(), false);
    }

    /**
     * Test of getSize method, of class CardDeck.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        CardDeck instance = new CardDeck();
        assertEquals(instance.getSize(), 0);
        instance.size = 20;
        assertEquals(instance.getSize(), 20);
    }


    /**
     * Test of contains method, of class CardDeck.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        populateDeck();
        assertFalse(deck.contains(new Card("random")));
        assertTrue(deck.contains(new Card(cards[0].getName(), cards[0].getValue())));
    }

    /**
     * Test of get method, of class CardDeck.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        populateDeck();
        assertEquals(deck.get(), cards[0]);
    }
    
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testGetException(){
        System.out.println("testGetException");
        deck.get();
    }

    /**
     * Test of getLast method, of class CardDeck.
     */
    @Test
    public void testGetLast() {
        System.out.println("getLast");
        populateDeck();
        assertEquals(deck.getLast(), cards[cards.length - 1]);
    }
    
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testGetLastException(){
        System.out.println("testGetLastException");
        deck.getLast();
    }

    /**
     * Test of getAt method, of class CardDeck.
     */
    @Test
    public void testGetAt() {
        System.out.println("getAt");
        populateDeck();
        assertEquals(deck.getAt(1), cards[1]);
    }
    
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testGetAtException(){
        System.out.println("testGetAtException");
        deck.getAt(-1);
    }
    

    /**
     * Test of set method, of class CardDeck.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        Card card = new Card("Random");
        populateDeck();
        deck.set(card);
        assertEquals(deck.get(), card);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testSetException(){
        System.out.println("testSetException");
        deck.set(cards[1]);
    }
    
    /**
     * Test of setLast method, of class CardDeck.
     */
    @Test
    public void testSetLast() {
        System.out.println("setLast");
        Card card = new Card("Random");
        populateDeck();
        deck.setLast(card);
        assertEquals(deck.getLast(), card);
    }
    
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testSetLastException(){
        System.out.println("testSetLastException");
        deck.setLast(cards[1]);
    }

    /**
     * Test of setAt method, of class CardDeck.
     */
    @Test
    public void testSetAt() {
        System.out.println("setAt");
        Card card = new Card("Random");
        int index = 2;
        populateDeck();
        deck.setAt(card, index);
        assertEquals(deck.getAt(index), card);
    }
    
    @Test(expectedExceptions = NoSuchElementException.class)
    public void testSetAtException(){
        System.out.println("testSetAtException");
        deck.setAt(cards[1], 0);
    }

    /**
     * Test of remove method, of class CardDeck.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        populateDeck();
        Card result = deck.remove();
        assertEquals(result, cards[0]);
        assertEquals(deck.get(), cards[1]);
    }

    /**
     * Test of removeLast method, of class CardDeck.
     */
    @Test
    public void testRemoveLast() {
        System.out.println("removeLast");
        populateDeck();
        Card result = deck.removeLast();
        assertEquals(result, cards[cards.length - 1]);
        assertEquals(deck.get(), cards[cards.length - 2]);
    }

    /**
     * Test of removeAt method, of class CardDeck.
     */
    @Test
    public void testRemoveAt() {
        System.out.println("removeAt");
        int index = 2;
        populateDeck();
        Card result = deck.removeAt(index);
        assertEquals(result, cards[index]);
        assertEquals(deck.get(), cards[index + 1]);
    }

    /**
     * Test of add method, of class CardDeck.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        deck.add(cards[0]);
        deck.add(cards[1]);
        assertEquals(deck.get(), cards[1]);
        assertEquals(deck.getLast(), cards[0]);
    }

    /**
     * Test of append method, of class CardDeck.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        deck.append(cards[0]);
        deck.append(cards[1]);
        assertEquals(deck.getLast(), cards[1]);
        assertEquals(deck.get(), cards[0]);
    }

    /**
     * Test of clear method, of class CardDeck.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        populateDeck();
        deck.clear();
        assertNull(deck.head);
        assertNull(deck.tail);
        assertEquals(deck.size, 0);
    }
    
    /**
     * Test of iterator method, of class CardDeck.
     */
    @Test(enabled=false)
    public void testIterator() {
        System.out.println("iterator");
        CardDeck instance = new CardDeck();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of shuffle method, of class CardDeck.
     */
    @Test(enabled=false)
    public void testShuffle() {
        System.out.println("shuffle");
        CardDeck instance = new CardDeck();
        instance.shuffle();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandomCard method, of class CardDeck.
     */
    @Test(enabled=false)
    public void testGetRandomCard() {
        System.out.println("getRandomCard");
        CardDeck instance = new CardDeck();
        Card expResult = null;
        Card result = instance.getRandomCard();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
