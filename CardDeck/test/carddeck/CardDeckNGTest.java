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
    CardDeck.CardNode sampleNode, sampleNode2;

    public CardDeckNGTest() {
    }

    @BeforeMethod
    public void setUp() {
        cards = new Card[]{new Card("1", 10), new Card("2"), new Card("4", 4),
            new Card("8", 80)};
        deck = new CardDeck();
        sampleNode = deck.new CardNode(cards[0]);
        sampleNode2 = deck.new CardNode(cards[1]);
    }

    private void populateDeck() {
        deck.clear();
        for (Card c : cards)
            deck.append(c);
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
        assertTrue(deck.contains(new Card(cards[0].getName(),
                cards[0].getValue())));
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
    public void testGetException() {
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
    public void testGetLastException() {
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
    public void testGetAtException() {
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
    public void testSetException() {
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
    public void testSetLastException() {
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
    public void testSetAtException() {
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
        assertEquals(deck.getLast(), cards[cards.length - 2]);
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
        assertEquals(deck.getAt(index), cards[index + 1]);
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

    @Test
    public void testFirstElementAdded() {
        System.out.println("FirstElementAdded");
        deck.firstElementAdded(sampleNode);
        assertEquals(deck.size, 1);
        assertEquals(deck.head, sampleNode);
        assertEquals(deck.tail, sampleNode);
        assertEquals(sampleNode.getNext(), sampleNode);
    }

    @Test
    public void testNewHead() {
        System.out.println("NewHead");
        deck.newHead(sampleNode);
        assertEquals(deck.size, 1);
        assertEquals(deck.head, sampleNode);
        assertEquals(deck.tail, sampleNode);
        assertEquals(sampleNode.getNext(), sampleNode);

        deck.newHead(sampleNode2);
        assertEquals(deck.head, sampleNode2);
        assertEquals(deck.tail.getNext(), sampleNode2);
    }

    @Test
    public void testNewTail() {
        System.out.println("NewTail");
        deck.newTail(sampleNode);
        assertEquals(deck.size, 1);
        assertEquals(deck.head, sampleNode);
        assertEquals(deck.tail, sampleNode);
        assertEquals(sampleNode.getNext(), sampleNode);

        deck.newTail(sampleNode2);
        assertEquals(deck.tail, sampleNode2);
        assertEquals(deck.tail.getNext(), sampleNode);
    }

    @Test
    public void testRemoveHead() {
        System.out.println("RemoveHead");
        populateDeck();
        CardDeck.CardNode exp = deck.head;
        CardDeck.CardNode newHead = deck.head.getNext();
        int size = deck.size;
        assertEquals(deck.removeHead(), exp);
        assertEquals(deck.head, newHead);
        assertEquals(deck.tail.getNext(), newHead);
        assertEquals(deck.size, size - 1);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testRemoveHeadException() {
        System.out.println("RemoveHeadException");
        deck.removeHead();
    }

    @Test
    public void testRemoveTail() {
        System.out.println("RemoveTail");
        populateDeck();
        CardDeck.CardNode exp = deck.tail;
        int size = deck.size;
        CardDeck.CardNode newTail = deck.findByIndex(size - 2);
        assertEquals(deck.removeTail(), exp);
        assertEquals(deck.tail, newTail);
        assertEquals(deck.tail.getNext(), deck.head);
        assertEquals(deck.size, size - 1);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testRemoveTailException() {
        System.out.println("RemoveTailException");
        deck.removeTail();
    }

    @Test
    public void testRemoveAtMiddle() {
        System.out.println("RemoveAtMiddle");
        populateDeck();
        int index = 3;
        CardDeck.CardNode previous = deck.findByIndex(index - 1);
        CardDeck.CardNode target = previous.getNext();
        CardDeck.CardNode previousNewNext = target.getNext();
        assertEquals(deck.removeAtMiddle(previous), target);
        assertEquals(previous.getNext(), previousNewNext);
        assertEquals(deck.size, cards.length - 1);
    }

    @Test
    public void testFindByIndex() {
        System.out.println("FindByIndex");
        populateDeck();
        assertEquals(deck.findByIndex(0), deck.head);
        assertEquals(deck.findByIndex(deck.size - 1), deck.tail);
        assertEquals(deck.findByIndex(3).getCard(), cards[3]);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testFindByIndexExceptionNegative() {
        System.out.println("FindByIndexException");
        populateDeck();
        deck.findByIndex(-1);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testFindByIndexExceptionOutOfRange() {
        System.out.println("FindByIndexException");
        populateDeck();
        deck.findByIndex(cards.length);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testFindByIndexExceptionOutOfRangeEmptyList() {
        System.out.println("FindByIndexException");
        deck.findByIndex(0);
    }

    @Test
    public void testFindByData() {
        System.out.println("FindByData");
        assertNull(deck.findByData(cards[0]));
        populateDeck();

        assertEquals(deck.findByData(cards[2]).getCard(), cards[2]);
        assertEquals(deck.findByData(cards[0]), deck.head);
        assertEquals(deck.findByData(cards[cards.length - 1]), deck.tail);

        Card different = new Card("Somthing Clever");
        assertNull(deck.findByData(different));
    }

    @Test
    public void testGetBeforeTailNode() {
        System.out.println("GetBeforeTailNode");
        populateDeck();
        CardDeck.CardNode result = deck.getBeforeTailNode();
        assertEquals(result.getCard(), cards[cards.length - 2]);
        assertEquals(result.getNext(), deck.tail);
    }

    // ***************
    private void updateDeckPointers(CardDeck.DeckPointers d) {
        deck.head = d.head;
        deck.tail = d.tail;
        deck.size = d.size;
    }

    private CardDeck.DeckPointers createDeckPointers(CardDeck.CardNode previous) {
        CardDeck.DeckPointers d = deck.new DeckPointers(deck.size, deck.head,
                deck.tail);
        d.previous = previous;
        return d;
    }

    @Test
    public void testDetachedRemove() {
        System.out.println("DetachedRemove");
        populateDeck();
        int index = 1;

        CardDeck.CardNode previous = deck.findByIndex(index);
        CardDeck.CardNode expResult = previous.getNext();
        CardDeck.CardNode expNext = expResult.getNext();

        CardDeck.DeckPointers result = createDeckPointers(previous);
        deck.detachedRemove(result);
        updateDeckPointers(result);

        assertSame(result.target, expResult);
        assertSame(deck.tail.getNext(), deck.head);
        assertSame(deck.findByIndex(index + 1), expNext);
        assertSame(previous.getNext(), expNext);
        assertNull(expResult.getNext());

    }

    @Test
    public void testDetachedRemoveHeadCase() {
        System.out.println("DetachedRemoveHeadCase");
        populateDeck();

        CardDeck.CardNode expResult = deck.head;

        CardDeck.DeckPointers result = createDeckPointers(deck.tail);
        deck.detachedRemove(result);
        updateDeckPointers(result);

        assertSame(result.target, expResult);
        assertSame(deck.tail.getNext(), deck.head);
        assertNotSame(deck.head, expResult);
        assertNull(expResult.getNext());
    }

    @Test
    public void testDetachedRemoveTailCase() {
        System.out.println("DetachedRemoveTailCase");
        populateDeck();

        CardDeck.CardNode expResult = deck.tail;

        CardDeck.DeckPointers result = createDeckPointers(
                deck.getBeforeTailNode());
        deck.detachedRemove(result);
        updateDeckPointers(result);

        assertSame(result.target, expResult);
        assertSame(deck.tail.getNext(), deck.head);
        assertNotSame(deck.tail, expResult);
        assertNull(expResult.getNext());
    }

    @Test
    public void testDetachedRemoveSingleNode() {
        System.out.println("DetachedRemoveSingleNode");
        deck.add(cards[0]);

        CardDeck.CardNode expResult = deck.head;

        CardDeck.DeckPointers result = createDeckPointers(deck.head);
        deck.detachedRemove(result);
        updateDeckPointers(result);

        assertSame(result.target, expResult);
        assertNull(deck.head);
        assertNull(deck.tail);
        assertNull(expResult.getNext());
    }

    @Test
    public void testShuffleContainsCards() {
        int length = 52;
        Card[] manyCards = new Card[length];
        CardDeck<Card> largeDeck = new CardDeck();
        for (int i = 0; i < length; i++) {
            manyCards[i] = new Card(String.valueOf(i), i);
            largeDeck.add(manyCards[i]);
        }
        for (Card c : manyCards)
            if (!largeDeck.contains(c))
                fail(c.toString() + " is missing from the deck");
    }

    @Test
    public void testMultipleShufflesContainsCards() {
        int length = 52;
        Card[] manyCards = new Card[length];
        CardDeck<Card> largeDeck = new CardDeck();
        for (int i = 0; i < length; i++) {
            manyCards[i] = new Card(String.valueOf(i), i);
            largeDeck.add(manyCards[i]);
        }
        for (int i = 50; i >= 0; i--) {
            largeDeck.shuffle();
            for (Card c : manyCards)
                if (!largeDeck.contains(c))
                    fail(c.toString() + " is missing from the deck");
        }
    }

    @Test
    public void testShuffle() {
        int length = 52;
        Card[] manyCards = new Card[length];
        CardDeck<Card> largeDeck = new CardDeck();
        largeDeck.shuffle();
        for (int i = 0; i < length; i++) {
            manyCards[i] = new Card(String.valueOf(i), i);
            largeDeck.add(manyCards[i]);
        }
        assertEquals(largeDeck.size, length);
    }

    /**
     * Test of iterator method, of class CardDeck.
     */
    @Test(enabled = false)
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
     * Test of getRandomCard method, of class CardDeck.
     */
    @Test(enabled = false)
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
