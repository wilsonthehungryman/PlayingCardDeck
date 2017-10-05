/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Wilson
 */
public class CardDeck implements Iterable<Card> {

    CardNode head, tail;
    int size;

    public CardDeck() {
        head = null;
        tail = null;
        size = 0;
    }


    public void shuffle() {
        CardNode tmpHead = head, tmpTail = tail;
        int tmpSize = size;
        CardNode current = getAndRemoveRandomNode(tmpSize, tmpHead, tmpTail);

        // clears head, tail, size
        initialize();

        newHead(current);
        tmpSize--;
//            System.out.println(tmpSize + " " + current.toString());
        while (tmpSize > 0) {
            current = getAndRemoveRandomNode(tmpSize, tmpHead, tmpTail);
            //current = customRemove(tmpSize, getRandomNode(tmpSize, tmpHead), tmpHead, tmpTail);
//            System.out.println(tmpSize + " " + current.getCard().toString());
            if (tmpSize % 2 == 0) {
                newHead(current); //customRemove(getRandomNode(tmpSize, tmpHead, tmpTail), tmpHead, tmpTail));
            } else {
                newTail(current); //customRemove(getRandomNode(tmpSize, tmpHead, tmpTail), tmpHead, tmpTail));
            }
            tmpSize--;
        }
//        newHead(customRemove(tmpSize, tmpTail, tmpHead, tmpTail));
//        tmpSize--;
//            System.out.println(tmpSize + " " + current.getCard().toString());
//        while (tmpSize > 0) {
//            current = customRemove(tmpSize, tmpTail, tmpHead, tmpTail);
//            //System.out.println(tmpSize + " tmpT: " + tmpTail.getCard().toString() + " n: " + current.getCard().toString());
////            if (tmpSize % 2 == 0) {
//                newHead(current); //customRemove(getRandomNode(tmpSize, tmpHead, tmpTail), tmpHead, tmpTail));
////            } else {
////                newTail(current); //customRemove(getRandomNode(tmpSize, tmpHead, tmpTail), tmpHead, tmpTail));
////            }
//            tmpSize--;
//        }
    }
    
    CardNode getAndRemoveRandomNode(int size, CardNode head, CardNode tail){
        CardNode previous = getRandomNode(size, head);
        System.out.println("P: " + previous.card.toString() + " PN: " + previous.getNext().card.toString());
        CardNode result = customRemove(size, previous, head, tail);
        
        System.out.println("P: " + previous.card.toString() + " PN: " + result.card.toString());
        return result;
    }

    public Card getRandomCard() {
        return getRandomNode().getCard();
    }

    CardNode getRandomNode() {
        return getRandomNode(size, head);
    }

    CardNode getRandomNode(int size, CardNode head) {
        if (head == null) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            return head;
        }
        int n = new Random().nextInt(size * 2);
        CardNode current = head;
        while (n > 0) {
            current = current.getNext();
            if (current == null) {
                current = head;
            }
            n--;
        }
        return current;
    }

    // ------------------- Datastructure manipulation (get, add, remove, etc) -----------------
    public boolean contains(Card target) {
        return findByData(target) != null;
    }

    public Card get() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.getCard();
    }

    public Card getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        return tail.getCard();
    }

    public Card getAt(int index) {
        return findByIndex(index).getCard();
    }

    public void set(Card card) {
        if (head == null) {
            throw new NoSuchElementException();
        }

        head.setCard(card);
    }

    public void setLast(Card card) {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        tail.setCard(card);
    }

    public void setAt(Card card, int index) {
        findByIndex(index).setCard(card);
    }

    public Card remove() {
        return removeHead().getCard();
    }

    public Card removeLast() {
        return removeTail().getCard();
    }

    public Card removeAt(int index) {
        if (index == 0) {
            return remove();
        }
        if (index == size - 1) {
            return removeLast();
        }

        CardNode previous = findByIndex(index - 1);

        return removeAtMiddle(previous).getCard();
    }

    public void add(Card card) {
        newHead(new CardNode(card));
    }

    public void append(Card card) {
        newTail(new CardNode(card));
    }

    public void clear() {
        if (size == 0) {
            return;
        }
        // garbage collection
        tail.setNext(null);
        initialize();
    }

    private void initialize() {
        head = null;
        tail = null;
        size = 0;
    }

    void newHead(CardNode cardNode) {
        if (head == null) {
            firstElementAdded(cardNode);
            return;
        }
        cardNode.setNext(head);
        head = cardNode;
        tail.setNext(head);
        size++;
    }

    void newTail(CardNode cardNode) {
        if (head == null) {
            firstElementAdded(cardNode);
            return;
        }
        tail.setNext(cardNode);
        tail = cardNode;
        tail.setNext(head);
        size++;
    }

    CardNode removeHead() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        CardNode returnNode = head;
        head = head.getNext();
        tail.setNext(head);
        returnNode.setNext(null);

        size--;

        return returnNode;
    }

    CardNode removeTail() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        CardNode returnNode = tail;

        if (size == 1) {
            tail = null;
            head = null;
        } else {
            tail = getBeforeTailNode();
            tail.setNext(head);
        }

        size--;

        return returnNode;
    }

    CardNode removeAtMiddle(CardNode previous) {
        CardNode target = previous.getNext();
        previous.setNext(target.getNext());
        target.setNext(null);
        size--;
        return target;
    }

    // refactor with better name
    CardNode customRemove(int size, CardNode previous, CardNode head, CardNode tail) {
        if (size == 1) {
            head = null;
            tail = null;
            previous.setNext(null);
            return previous;
        }

        CardNode target = previous.getNext();
        // previous == tail
        if (target == head) {
            head = target.getNext();
            tail.setNext(head);
            //previous == 1 before tail
        } else if (target == tail) {
            tail = previous;
            tail.setNext(head);
        } else {
            previous.setNext(target.getNext());
        }
        return target;
    }

    void firstElementAdded(CardNode cardNode) {
        head = cardNode;
        tail = head;
        // Node now points to itself
        head.setNext(tail);
        size++;
    }

    CardNode findByIndex(int index) {
        if (index >= size || index < 0) {
            throw new NoSuchElementException("0 based index");
        }
        if (index == size - 1) {
            return tail;
        }

        CardNode current = head;
        index--;

        while (index >= 0) {
            current = current.getNext();
            index--;
        }

        return current;
    }

    CardNode findByData(Card target) {
        if (size == 0) {
            return null;
        }

        CardNode current = head;
        while (current != tail) {
            if (current.cardEquals(target)) {
                return current;
            }
            current = current.getNext();
        }
        if (tail.cardEquals(target)) {
            return tail;
        }

        return null;
    }

    CardNode getBeforeTailNode() {
        CardNode current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        return current;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    // ------------------- Iterator ----------------------
    @Override
    public Iterator<Card> iterator() {
        return new DeckIterator(head, size);
    }

    private static final class DeckIterator implements Iterator<Card> {

        private CardNode current;
        private int size;

        public DeckIterator(CardNode head, int size) {
            this.current = head;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Card next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            CardNode returnNode = current;
            current = current.getNext();
            size--;
            return returnNode.getCard();
        }
    }

    // --------------------- CardNode inner class -------------------
    // extend Card?
    class CardNode {

        Card card;
        CardNode next;

        public CardNode(Card card) {
            this(card, null);
        }

        public CardNode(Card card, CardNode next) {
            this.card = card;
            this.next = next;
        }

        public Card getCard() {
            return card;
        }

        public CardNode getNext() {
            return next;
        }

        public void setCard(Card card) {
            this.card = card;
        }

        public void setNext(CardNode next) {
            this.next = next;
        }

        @Override
        public int hashCode() {
            return card.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }

            if (getClass() != obj.getClass()) {
                return false;
            }
            final CardNode other = (CardNode) obj;
            if (!Objects.equals(this.card, other.card)) {
                return false;
            }
            return true;
        }

        public boolean cardEquals(Card card) {
            return this.card.equals(card);
        }

    }
}
