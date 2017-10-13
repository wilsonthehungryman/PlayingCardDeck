/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Wilson
 */
public class HeartsHand implements Iterable<PlayingCard> {

    PlayingCard[] hand;
    Comparator<PlayingCard> order;

    public HeartsHand(PlayingCard[] hand, Comparator<PlayingCard> order) {
        this.hand = hand;
        this.order = order;
    }

    public HeartsHand(PlayingCard[] hand) {
        this(hand, PlayingCardComparator.defaultCardComparator());
    }

    public HeartsHand(int size) {
        this(new PlayingCard[size]);
    }

    public HeartsHand() {
        this(new PlayingCard[13]);
    }

    public void insert(PlayingCard card) {
        int index = findInsertLocation(card);
        if (index == hand.length)
            // full hand, replace last card
            hand[index - 1] = card;
        else if (hand[index] != null)
            shift(index, card);
        else
            hand[index] = card;
    }

    private int findInsertLocation(PlayingCard card) {
        for (int i = 0; i < hand.length; i++)
            if (hand[i] == null || order.compare(hand[i], card) < 0)
                return i;
        return hand.length - 1;
    }

    private void shift(int index, PlayingCard previous) {
        for (int i = index; i < hand.length; i++) {
            PlayingCard temp = hand[i];
            hand[i] = previous;
            previous = temp;
            if (temp == null)
                break;
        }
    }

    public Iterator<PlayingCard> iterator() {
        return new HandIterator();
    }

    private class HandIterator implements Iterator<PlayingCard> {

        private int index;

        public HandIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < hand.length && hand[index] != null;
        }

        @Override
        public PlayingCard next() {
            PlayingCard returnValue = hand[index];
            index++;
            return returnValue;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] != null) {
                s.append(hand[i].toString());
                s.append("\n");
            }
        }
        return s.toString();
    }

    public String toStringWithNulls() {
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
}
