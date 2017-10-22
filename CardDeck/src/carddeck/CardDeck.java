/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Comparator;
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
 * @param <C> The type of Card
 */
public class CardDeck<C extends Card> implements Iterable<C> {

    CardNode<C> head, tail;
    int size;
    Comparator orderingBy;

    /**
     * Create a new empty CardDeck
     */
    public CardDeck() {
        initialize();
        orderingBy = (c1, c2) -> c1.toString().compareTo(c2.toString());
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
        if (head == null)
            return;
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
    public boolean contains(C target) {
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
        DeckPointers pointers = getDeckPointers();
        CardNode<C> current;

        // clears head, tail, size
        initialize();

        while (pointers.size > 0) {
            pointers.previous = getRandomNode(pointers.size, pointers.head);
            detachedRemove(pointers);
            current = pointers.target;
            if (pointers.size % 2 == 0)
                newHead(current);
            else
                newTail(current);
        }
    }

    /**
     * Returns a random Card
     *
     * @return a random Card
     */
    public C getRandomCard() {
        return getRandomNode().getCard();
    }

    /**
     * @return a random CardNode<C>
     */
    CardNode<C> getRandomNode() {
        return getRandomNode(size, head);
    }

    /**
     * @param size Size of list
     * @param head The head of the list to use
     * @return a random CardNode<C>
     */
    CardNode<C> getRandomNode(int size, CardNode<C> head) {
        if (head == null)
            throw new NoSuchElementException();

        if (size == 1)
            return head;
        int n = new Random().nextInt(size * 2);
        CardNode<C> current = head;
        while (n > 0) {
            current = current.getNext();
            if (current == null)
                current = head;
            n--;
        }
        return current;
    }

    public void sort() {
        sort(orderingBy);
    }

    public void sort(Comparator comparator) {
        DeckPointers source = getDeckPointers();
        initialize();

        while (source.size > 0) {
            detachedRemoveHead(source);
            insert(source.target);
        }
    }

    // ------------------- Datastructure manipulation (get, add, remove, etc) -----------------
    // <editor-fold>
    /**
     * Get the first/top Card
     *
     * @return the first Card
     */
    public C get() {
        if (head == null)
            throw new NoSuchElementException();
        return head.getCard();
    }

    /**
     * Get the last/bottom Card
     *
     * @return the last Card
     */
    public C getLast() {
        if (tail == null)
            throw new NoSuchElementException();

        return tail.getCard();
    }

    /**
     * Get a Card at a specific index. Index is 0 based
     *
     * @param index The 0 based index of the card.
     * @return the found Card or null
     * @throws NoSuchElementException when given an invalid index.
     */
    public C getAt(int index) {
        return findByIndex(index).getCard();
    }
    
    /**
     * Get a Card at a specific index. Index is 0 based
     *
     * @param target The target card to look for.
     * @return the found Card or null
     */
    public C getBy(C target) {
        CardNode<C> result = findByData(target);
        if(result == null)
            return null;
        return result.getCard();
    }

    /**
     * Replace the first/top Card
     *
     * @param card The new Card to use
     * @throws NoSuchElementException if there is no first Card
     */
    public void set(C card) {
        if (head == null)
            throw new NoSuchElementException();

        head.setCard(card);
    }

    /**
     * Replace the last/bottom Card
     *
     * @param card The new Card to use
     * @throws NoSuchElementException if there is no last Card
     */
    public void setLast(C card) {
        if (tail == null)
            throw new NoSuchElementException();
        tail.setCard(card);
    }

    /**
     * Replace the Card at the specified 0 based index
     *
     * @param card The new Card
     * @param index The index
     * @throws NoSuchElementException if the index is out of range
     */
    public void setAt(C card, int index) {
        findByIndex(index).setCard(card);
    }
    
    /**
     * Replace the Card matching the target Card
     *
     * @param card The new Card
     * @param target The target Card
     */
    public void setBy(C card, C target) {
        CardNode<C> result = findByData(target);
        if(result != null)
            result.setCard(card);
    }

    /**
     * Remove the first/top Card in the deck
     *
     * @return The removed top Card
     * @throws NoSuchElementException if there is no first Card
     */
    public C remove() {
        return removeHead().getCard();
    }

    /**
     * Remove the last/bottom Card in the deck
     *
     * @return The removed bottom Card
     * @throws NoSuchElementException if there is no last Card
     */
    public C removeLast() {
        return removeTail().getCard();
    }

    /**
     * Remove the Card at the specified 0 based index
     *
     * @param index The 0 based index
     * @return The removed Card
     * @throws NoSuchElementException if the index is out of range
     */
    public C removeAt(int index) {
        if(index < 0 || index >= size)
            throw new NoSuchElementException();
        if (index == 0)
            return remove();
        if (index == size - 1)
            return removeLast();

        CardNode<C> previous = findByIndex(index - 1);

        return removeAtMiddle(previous).getCard();
    }
    
    /**
     * Remove the Card at the specified 0 based index
     *
     * @param target The target Card
     * @return The removed Card
     * @throws NoSuchElementException if there is no matching Card
     */
    public C removeBy(C target) {
        CardNode<C> previous = findByData(target);
        if(previous == null)
            throw new NoSuchElementException();
        
        if(previous == tail)
            return remove();
        if(previous.getNext() == tail)
            return removeLast();

        return removeAtMiddle(previous).getCard();
    }

    /**
     * Add a Card to the first/top position
     *
     * @param card The Card to add
     */
    public void add(C card) {
        newHead(new CardNode<C>(card));
    }

    /**
     * Add a card to the last/bottom position
     *
     * @param card The Card to add
     */
    public void append(C card) {
        newTail(new CardNode<C>(card));
    }

    public void insert(C card) {
        insert(new CardNode<C>(card));
    }

    void insert(CardNode<C> newNode) {
        if (size == 0) {
            firstElementAdded(newNode);
            return;
        }

        CardNode<C> previous = findInsertLocation(newNode.getCard());

        if (previous == null)
            newHead(newNode);
        else if (previous == tail)
            newTail(newNode);
        else
            addAtMiddle(previous, newNode);
    }

    /**
     * Creates a new head node/adds to the top
     *
     * @param cardNode The new node
     */
    void newHead(CardNode<C> cardNode) {
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
    void newTail(CardNode<C> cardNode) {
        if (head == null) {
            firstElementAdded(cardNode);
            return;
        }
        tail.setNext(cardNode);
        tail = cardNode;
        tail.setNext(head);
        size++;
    }

    CardNode<C> removeHead() {
        if (head == null)
            throw new NoSuchElementException();

        CardNode<C> returnNode = head;
        head = head.getNext();
        tail.setNext(head);
        returnNode.setNext(null);

        decrementSize();

        return returnNode;
    }

    CardNode<C> removeTail() {
        if (tail == null)
            throw new NoSuchElementException();

        CardNode<C> returnNode = tail;

        if (size == 1) {
            tail = null;
            head = null;
        } else {
            tail = getBeforeTailNode();
            tail.setNext(head);
        }

        decrementSize();

        return returnNode;
    }

    // Assumes there is a next node (removeTail || removeHead) should have been called
    CardNode<C> removeAtMiddle(CardNode<C> previous) {
        CardNode<C> target = previous.getNext();
        previous.setNext(target.getNext());
        target.setNext(null);
        decrementSize();
        return target;
    }

    void addAtMiddle(CardNode<C> previous, CardNode<C> newNode) {
        newNode.setNext(previous.getNext());
        previous.setNext(newNode);
        size++;
    }

    // Consider implementing detached methods for other operations,
    // would allow just calling those methods instead of putting it all here
    // Also can be void
    void detachedRemove(DeckPointers deck) {
        deck.target = deck.previous.getNext();
        if (deck.size == 1) {
            deck.head = null;
            deck.tail = null;
            deck.target.setNext(null);
            deck.size--;
        }

        // previous == tail
        if (deck.target == deck.head) {
            deck.head = deck.target.getNext();
            deck.tail.setNext(deck.head);
            //previous == 1 before tail
        } else if (deck.target == deck.tail) {
            deck.tail = deck.previous;
            deck.tail.setNext(deck.head);
        } else
            deck.previous.setNext(deck.target.getNext());
        deck.size--;
        deck.target.setNext(null);
    }

    void detachedRemoveHead(DeckPointers deck) {
        deck.target = deck.head;
        deck.head = deck.head.getNext();
        deck.tail.setNext(deck.head);
        deck.target.setNext(null);
        deck.size--;
    }
    
    void decrementSize(){
        size--;
        if(size == 0)
            clear();
    }

    /**
     * Handles the edge case of a node added to an empty list
     *
     * @param cardNode The first node
     */
    void firstElementAdded(CardNode<C> cardNode) {
        head = cardNode;
        tail = head;
        // Node now points to itself
        head.setNext(tail);
        size++;
    }

    CardNode<C> findInsertLocation(C card) {
        if (head == null)
            return null;

        CardNode<C> previous = head;
        CardNode<C> current = head.getNext();

        if (orderingBy.compare(previous.getCard(), card) >= 0)
            return null;

        while (previous != tail && orderingBy.compare(current.getCard(), card) < 0) {
            previous = current;
            current = current.getNext();
        }
        return previous;
    }

    CardNode<C> findByIndex(int index) {
        if (index >= size || index < 0)
            throw new NoSuchElementException();
        if (index == size - 1)
            return tail;

        CardNode<C> current = head;
        index--;

        while (index >= 0) {
            current = current.getNext();
            index--;
        }

        return current;
    }

    CardNode<C> findByData(C target) {
        if (size == 0)
            return null;

        CardNode<C> current = head;
        while (current != tail) {
            if (current.cardEquals(target))
                return current;
            current = current.getNext();
        }
        if (tail.cardEquals(target))
            return tail;

        return null;
    }

    CardNode<C> findPreviousByData(C target) {
        if (size == 0)
            return null;

        if(head.cardEquals(target))
            return tail;
        
        CardNode<C> previous = head;
        while (previous != tail) {
            if (previous.getNext().cardEquals(target))
                return previous;
            previous = previous.getNext();
        }

        return null;
    }
    
    CardNode<C> getBeforeTailNode() {
        CardNode<C> current = head;
        while (current.getNext() != tail)
            current = current.getNext();
        return current;
    }
    //</editor-fold>

    public CardDeck copy() {
        CardDeck<C> clone = new CardDeck();

        for (C card : this)
            clone.append((C) card.copy());

        return clone;
    }

    // ------------------- Iterator ----------------------
    //<editor-fold>
    /**
     * Creates an iterator to go through the deck (in sequential order)
     *
     * @return A CardDeck Iterator
     */
    @Override
    public Iterator<C> iterator() {
        return new DeckIterator(head, size);
    }

    private class DeckIterator implements Iterator<C> {

        private CardNode<C> current;
        private int size;

        public DeckIterator(CardNode<C> head, int size) {
            this.current = head;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public C next() {
            if (current == null)
                throw new NoSuchElementException();
            CardNode<C> returnNode = current;
            current = current.getNext();
            size--;
            return returnNode.getCard();
        }
    }
    //</editor-fold>

    // --------------------- CardNode<C> inner class -------------------
    //<editor-fold>
    /**
     * Represents a single card.
     */
    class CardNode<C> {

        C card;
        CardNode<C> next;

        public CardNode(C card) {
            this(card, null);
        }

        public CardNode(C card, CardNode<C> next) {
            this.card = card;
            this.next = next;
        }

        public C getCard() {
            return card;
        }

        public CardNode<C> getNext() {
            return next;
        }

        public void setCard(C card) {
            this.card = card;
        }

        public void setNext(CardNode<C> next) {
            this.next = next;
        }

        @Override
        public int hashCode() {
            return card.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;
            return Objects.equals(this.card, ((CardNode<C>) obj).card);
        }

        public boolean cardEquals(C card) {
            return this.card.equals(card);
        }

    }
    //</editor-fold>

    DeckPointers getDeckPointers() {
        return new DeckPointers(size, head, tail);
    }

    // ---------------------- Value Helper for Detached methods/decks ------------
    class DeckPointers {

        CardNode<C> target;
        CardNode<C> previous;
        CardNode<C> head;
        CardNode<C> tail;
        int size;

        DeckPointers(int size, CardNode<C> head, CardNode<C> tail) {
            this.size = size;
            this.head = head;
            this.tail = tail;
            target = null;
            previous = null;
        }
    }

}
