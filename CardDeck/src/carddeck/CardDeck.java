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
 * A CardDeck consists of cards. The cards are stored in a circularly singularly
 * linked list. Provides the core functions for manipulation, but is designed to
 * inherited by a specific kind of deck, which is responsible for tasks like
 * creating the deck and my use different cards.
 *
 * @version 1.0
 * @author Wilson
 */
public class CardDeck implements Iterable<Card> {

    CardNode head, tail;
    int size;

    /**
     * Create a new empty CardDeck
     */
    public CardDeck() {
        initialize();
    }

    /**
     * Clears private variables. Blank slate
     */
    private void initialize() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Clears all the Cards from the deck
     */
    public void clear() {
        if (size == 0) {
            return;
        }
        // garbage collection
        tail.setNext(null);
        initialize();
    }
    
    /**
     * Are there any Cards in the deck? True if empty
     *
     * @return True if empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if a Card is in the deck.
     *
     * @param target The Card to look for
     * @return True if the Card is in the deck, otherwise false
     */
    public boolean contains(Card target) {
        return findByData(target) != null;
    }

    /**
     * Get the number of Cards (1 based)
     *
     * @return The number of Cards
     */
    public int getSize() {
        return size;
    }

    public void shuffle() {
        DeckPointers pointers = new DeckPointers(size, head, tail);
        CardNode current;
        
        // clears head, tail, size
        initialize();
        
        
        while (pointers.size > 0) {
            pointers.previous = getRandomNode(pointers.size, pointers.head);
            detachedRemove(pointers);
            current = pointers.target;
            if (pointers.size % 2 == 0) {
                newHead(current); //customRemove(getRandomNode(tmpSize, tmpHead, tmpTail), tmpHead, tmpTail));
            } else {
                newTail(current); //customRemove(getRandomNode(tmpSize, tmpHead, tmpTail), tmpHead, tmpTail));
            }
        }
    }

    /**
     * Returns a random Card
     *
     * @return a random Card
     */
    public Card getRandomCard() {
        return getRandomNode().getCard();
    }

    /**
     * @return a random CardNode
     */
    CardNode getRandomNode() {
        return getRandomNode(size, head);
    }

    /**
     * @param size Size of list
     * @param head The head of the list to use
     * @return a random CardNode
     */
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
    // <editor-fold>
    
    /**
     * Get the first/top Card
     *
     * @return the first Card
     */
    public Card get() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.getCard();
    }

    /**
     * Get the last/bottom Card
     *
     * @return the last Card
     */
    public Card getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        return tail.getCard();
    }

    /**
     * Get a Card at a specific index. Index is 0 based
     *
     * @param index The 0 based index of the card.
     * @return the found Card or null
     * @throws NoSuchElementException when given an invalid index.
     */
    public Card getAt(int index) {
        return findByIndex(index).getCard();
    }

    /**
     * Replace the first/top Card
     *
     * @param card The new Card to use
     * @throws NoSuchElementException if there is no first Card
     */
    public void set(Card card) {
        if (head == null) {
            throw new NoSuchElementException();
        }

        head.setCard(card);
    }

    /**
     * Replace the last/bottom Card
     *
     * @param card The new Card to use
     * @throws NoSuchElementException if there is no last Card
     */
    public void setLast(Card card) {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        tail.setCard(card);
    }

    /**
     * Replace the Card at the specified 0 based index
     *
     * @param card The new Card
     * @param index The index
     * @throws NoSuchElementException if the index is out of range
     */
    public void setAt(Card card, int index) {
        findByIndex(index).setCard(card);
    }

    /**
     * Remove the first/top Card in the deck
     *
     * @return The removed top Card
     * @throws NoSuchElementException if there is no first Card
     */
    public Card remove() {
        return removeHead().getCard();
    }

    /**
     * Remove the last/bottom Card in the deck
     *
     * @return The removed bottom Card
     * @throws NoSuchElementException if there is no last Card
     */
    public Card removeLast() {
        return removeTail().getCard();
    }

    /**
     * Remove the Card at the specified 0 based index
     *
     * @param index The 0 based index
     * @return The removed Card
     */
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

    /**
     * Add a Card to the first/top position
     *
     * @param card The Card to add
     */
    public void add(Card card) {
        newHead(new CardNode(card));
    }

    /**
     * Add a card to the last/bottom position
     *
     * @param card The Card to add
     */
    public void append(Card card) {
        newTail(new CardNode(card));
    }


    /**
     * Creates a new head node/adds to the top
     *
     * @param cardNode The new node
     */
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

    /**
     * Creates a new tail node/add to the bottom
     *
     * @param cardNode The new node
     */
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

    // Assumes there is a next node (removeTail || removeHead) should have been called
    CardNode removeAtMiddle(CardNode previous) {
        CardNode target = previous.getNext();
        previous.setNext(target.getNext());
        target.setNext(null);
        size--;
        return target;
    }

    // Consider implementing detached methods for other operations,
    // would allow just calling those methods instead of putting it all here
    // Also can be void
    DeckPointers detachedRemove(DeckPointers deck) {
        deck.target = deck.previous.getNext();
        if (deck.size == 1) {
            deck.head = null;
            deck.tail = null;
            deck.target.setNext(null);
            deck.size--;
            return deck;
        }

        // previous == tail
        if (deck.target == deck.head) {
            deck.head = deck.target.getNext();
            deck.tail.setNext(deck.head);
            //previous == 1 before tail
        } else if (deck.target == deck.tail) {
            deck.tail = deck.previous;
            deck.tail.setNext(deck.head);
        } else {
            deck.previous.setNext(deck.target.getNext());
        }
        deck.size--;
        deck.target.setNext(null);
        return deck;
    }

    /**
     * Handles the edge case of a node added to an empty list
     *
     * @param cardNode The first node
     */
    void firstElementAdded(CardNode cardNode) {
        head = cardNode;
        tail = head;
        // Node now points to itself
        head.setNext(tail);
        size++;
    }

    CardNode findByIndex(int index) {
        if (index >= size || index < 0) {
            throw new NoSuchElementException();
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
    //</editor-fold>

    // ------------------- Iterator ----------------------
    //<editor-fold>
    /**
     * Creates an iterator to go through the deck (in sequential order)
     *
     * @return A CardDeck Iterator
     */
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
    //</editor-fold>

    // --------------------- CardNode inner class -------------------
    //<editor-fold>
    /**
     * Represents a single card.
     */
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
            return Objects.equals(this.card, ((CardNode) obj).card);
        }

        public boolean cardEquals(Card card) {
            return this.card.equals(card);
        }

    }
    //</editor-fold>
    
    // ---------------------- Value Helper for Detached methods/decks ------------
    class DeckPointers{
        CardNode target;
        CardNode previous;
        CardNode head;
        CardNode tail;
        int size;
        DeckPointers(int size, CardNode head, CardNode tail){
            this.size = size;
            this.head = head;
            this.tail = tail;
            target = null;
            previous = null;
        }
    }
}
