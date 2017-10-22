/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Wilson
 */
public class HeartsDeckNGTest {
    
    private HeartsDeck deck;
    private PlayingCard[] cardSet;
    
    public HeartsDeckNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        deck = new HeartsDeck();
        cardSet = new PlayingCard[52];
        int count = 0;
        for(Face f : Face.values())
            for(Suit s : Suit.values()){
                if(f != Face.JOKER){
                    cardSet[count] = new PlayingCard(f, s, 0);
                    if(s == Suit.HEARTS)
                        cardSet[count].setPoint(1);
                    else if(f == Face.QUEEN && s == Suit.SPADES)
                        cardSet[count].setPoint(13);
                    count++;
                }
            }
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getPlayers method, of class HeartsDeck.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        HeartsDeck instance = new HeartsDeck(3);
        int expResult = 3;
        int result = instance.getPlayers();
        assertEquals(result, expResult);
    }

    /**
     * Test of getComparator method, of class HeartsDeck.
     */
    @Test(enabled = false)
    public void testGetComparator() {
        System.out.println("getComparator");
        HeartsDeck instance = new HeartsDeck();
        PlayingCardComparator expResult = null;
        PlayingCardComparator result = instance.getComparator();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setComparator method, of class HeartsDeck.
     */
    @Test(enabled = false)
    public void testSetComparator() {
        System.out.println("setComparator");
        PlayingCardComparator comparator = null;
        HeartsDeck instance = new HeartsDeck();
        instance.setComparator(comparator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDeck method, of class HeartsDeck.
     */
    @Test
    public void testCreateDeck() {
        System.out.println("createDeck");
        HeartsDeck deck = new HeartsDeck();
        deck.createDeck();
        assertEquals(deck.size, cardSet.length);
        assertTrue(deck.contains(new PlayingCard(Face.QUEEN, Suit.SPADES, 13)));
        assertTrue(deck.contains(new PlayingCard(Face.QUEEN, Suit.HEARTS, 1)));
        assertTrue(deck.contains(new PlayingCard(Face.ACE, Suit.SPADES, 0)));
        for (PlayingCard card : cardSet)
            assertTrue(deck.contains(card));
    }

    /**
     * Test of deal method, of class HeartsDeck.
     */
    @Test
    public void testDeal() {
        System.out.println("deal");
        deck.createDeck();
        HeartsHand[] result = deck.deal();
        for(PlayingCard c : cardSet){
            boolean contains = false;
            for(HeartsHand h : result)
                if(h.hasCard(c))
                    contains = true;
            if(contains)
                contains = false;
            else
                fail(c.toString() + " has not been dealt");
        }
    }
    
    /**
     * Test of deal method, of class HeartsDeck.
     */
    @Test
    public void testShuffleThenDeal() {
        System.out.println("deal");
        deck.createDeck();
        deck.shuffle();
        HeartsHand[] result = deck.deal();
        for(PlayingCard c : cardSet){
            boolean contains = false;
            for(HeartsHand h : result)
                if(h.hasCard(c))
                    contains = true;
            if(contains)
                contains = false;
            else
                fail(c.toString() + " has not been dealt");
        }
    }
    
    @Test
    public void testShuffleContainsCards() {
        deck.createDeck();
        deck.shuffle();
        for (PlayingCard c : cardSet)
            if (!deck.contains(c))
                fail(c.toString() + " is missing from the deck");
    }

    @Test
    public void testMultipleShufflesContainsCards() {
        deck.createDeck();
        for (int i = 100; i >= 0; i--) {
            deck.shuffle();
            for (PlayingCard c : cardSet)
                if (!deck.contains(c))
                    fail(c.toString() + " is missing from the deck");
        }
    }

    @Test
    public void testShuffle() {
        deck.createDeck();
        deck.shuffle();
        assertEquals(deck.size, cardSet.length);
    }

//    /**
//     * Test of shuffleAndDeal method, of class HeartsDeck.
//     */
//    @Test
//    public void testShuffleAndDeal() {
//        System.out.println("shuffleAndDeal");
//        HeartsDeck instance = new HeartsDeck();
//        HeartsHand[] expResult = null;
//        HeartsHand[] result = instance.shuffleAndDeal();
//        assertEquals(result, expResult);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of copy method, of class HeartsDeck.
//     */
//    @Test
//    public void testCopy() {
//        System.out.println("copy");
//        HeartsDeck instance = new HeartsDeck();
//        HeartsDeck expResult = null;
//        HeartsDeck result = instance.copy();
//        assertEquals(result, expResult);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
