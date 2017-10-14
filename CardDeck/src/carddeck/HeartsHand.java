/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Wilson
 * @param <C> The specific type of PlayingCard
 */
public class HeartsHand<C extends PlayingCard> implements Iterable<C> {

    C[] hand;
    int size;
    Comparator<C> order;

    public HeartsHand(C[] hand, Comparator<C> order) {
        this.hand = hand;
        this.order = order;
        size = 0;
        for(int i = 0; i < hand.length && hand[i] != null; i++){
            size++;
        }
    }

    public HeartsHand(C[] hand) {
        this(hand, (Comparator<C>)PlayingCardComparator.defaultCardComparator());
    }

    public HeartsHand(int size) {
        this((C[])new PlayingCard[size]);
    }

    public HeartsHand() {
        this((C[])new PlayingCard[13]);
    }

    public C[] getHand() {
        return hand;
    }

    public int getSize() {
        return size;
    }

    public void insert(C card) {
        int index = findInsertLocation(card);
        if (index == hand.length)
            // full hand, replace last card
            hand[index - 1] = card;
        else if (hand[index] != null) {
            shift(index, card);
            size++;
        } else
            hand[index] = card;
    }

    public C play(int index) {
        if (hand[index] == null)
            throw new NoSuchElementException();

        C returnValue = hand[index];
        shiftLeft(index);
        size--;
        return returnValue;
    }

    private int findInsertLocation(C card) {
        for (int i = 0; i < hand.length; i++)
            if (hand[i] == null || order.compare((C)hand[i], (C)card) < 0)
                return i;
        return hand.length - 1;
    }

    private void shift(int index, C previous) {
        for (int i = index; i < hand.length; i++) {
            C temp = hand[i];
            hand[i] = previous;
            previous = temp;
            if (temp == null)
                break;
        }
    }

    private void shiftLeft(int index) {
        C previous = null;
        for (int i = hand.length - 1; i >= index; i--) {
            C temp = hand[i];
            hand[i] = previous;
            previous = temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hand.length; i++)
            if (hand[i] != null) {
                s.append(hand[i].toString());
                s.append("\n");
            }
        return s.toString();
    }

    public String toStringDetailed() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hand.length; i++) {
            s.append(i);
            s.append(": ");
            if (hand[i] == null)
                s.append("null");
            else
                s.append(hand[i].toString());
            s.append("\n");
        }
        return s.toString();
    }

    public Iterator<C> iterator() {
        return new HandIterator<C>();
    }

    private class HandIterator<C> implements Iterator<C> {

        private int index;

        public HandIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < hand.length && hand[index] != null;
        }

        @Override
        public C next() {
            C returnValue = (C)hand[index];
            index++;
            return returnValue;
        }
    }

}
