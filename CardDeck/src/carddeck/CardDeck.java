/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Wilson
 */
public class CardDeck implements Iterable<Card>
{
    CardNode head, tail;
    int size;
    
    public CardDeck(){
        head = null;
        tail = null;
        size = 0;
    }
    
    public void createDeck(){
        createDeck(true);
    }
    
    public void createDeck(boolean hasJokers){
        for(Suit suit : Suit.values()){
            for(Face face : Face.values()){
                if(face != Face.JOKER)
                    queue(new Card(face, suit));
            }
        }
        
        if(hasJokers){
            queue(new Card(Face.JOKER, Suit.HEARTS));
            queue(new Card(Face.JOKER, Suit.SPADES));
        }
    }
    
    
    
    public void shuffle(){
        CardNode current = getRandomNode();
        CardNode newTop = current;
        CardNode newTail = current;
        appendNodeCustom(remove(current, current.getNext()), newTop, newTail);
        int sizeCopy = size;
        
        while(size > 0){
            current = getRandomNode();
            System.out.println(current.getNext().getCard().toString());
            appendNodeCustom(remove(current, current.getNext()), newTop, newTail);
        }
        size = sizeCopy;
        head = newTop;
        tail = newTail;
        System.out.println(head.getCard().toString());
        System.out.println(tail.getCard().toString());
    }

    public Card getRandomCard(){
        return getRandomNode().getCard();
    }
    
    private CardNode getRandomNode(){
        if(head == null)
            throw new NoSuchElementException();
            
        if(size == 1)
            return head;
        if(size < 1)
            System.out.println();
        int n = new Random().nextInt(size * 2);
        CardNode current = head;
        while(n > 0){
            current = current.getNext();
            if(current == null)
                current = head;
            n--;
        }
        return current;
    }
    
    // ------------------- Datastructure manipulation (get, add, remove, etc) -----------------
    public Card get(){
        if(head == null)
            throw new NoSuchElementException();
        return head.getCard();
    }
    
    public Card getLast() {
        if(tail == null)
            throw new NoSuchElementException();
        
        return tail.getCard();
    }
    
    
    public void set(Card card){
        if(head == null)
            throw new NoSuchElementException();
        head.setCard(card);
    }
    
    public void setLast(Card card) {
        if(tail == null)
            throw new NoSuchElementException();
        tail.setCard(card);
    }
    
    
    public Card remove(){
        return removeHead().getCard();
    }
    
    public Card removeLast() {
        return removeTail().getCard();
    }
    
    
    public void add(Card card){
        newHead(new CardNode(card));
    }
    
    public void append(Card card){
        newTail(new CardNode(card));
    }
    
    private void newHead(CardNode cardNode){
        if(head == null){
            firstElementAdded(cardNode);
            return;
        }
        cardNode.setNext(head);
        head = cardNode;
        tail.setNext(head);
        size++;
    }
    
    private void newTail(CardNode cardNode){
        if(head == null){
            firstElementAdded(cardNode);
            return;
        }
        tail.setNext(cardNode);
        tail = cardNode;
        tail.setNext(head);
        size++;
    }
    
    private CardNode removeHead(){
        if(head == null)
            throw new NoSuchElementException();
        
        CardNode returnNode = head;
        head = head.getNext();
        tail.setNext(head);
        returnNode.setNext(null);
        
        size--;
        
        return returnNode;
    }
    
    private CardNode removeTail() {
        if(tail == null)
            throw new NoSuchElementException();
        
        CardNode returnNode = tail;
        
        if(size == 1){
            tail = null;
            head = null;
        }else{
            tail = getBeforeTailNode();
            tail.setNext(head);
        }
        
        size--;
        
        return returnNode;
    }
    
    
    private void firstElementAdded(CardNode cardNode){
        head = cardNode;
        tail = head;
        // Node now points to itself
        head.setNext(tail);
    }
    
    
    private CardNode getBeforeTailNode(){
        CardNode current = head;
        while(current.getNext() != tail)
            current = current.getNext();
        return current;
    }
    
    public boolean isEmpty() { return size == 0; }
    
    public int getSize(){ return size; }
    
    // ------------------- Iterator ----------------------
    
    @Override
    public Iterator<Card> iterator(){
        return new DeckIterator(head);
    }
    
    private static final class DeckIterator implements Iterator<Card>{
        private CardNode current;
        private final CardNode start;
        
        public DeckIterator(CardNode head){
            this.current = head;
            this.start = head;
        }
        
        @Override
        public boolean hasNext() { return current != start; }
        
        @Override
        public Card next() {
            if(current == null)
                throw new NoSuchElementException();
            CardNode returnNode = current;
            current = current.getNext();
            return returnNode.getCard();
        }
    }
    
    // --------------------- CardNode inner class -------------------
    
    // extend Card?
    class CardNode{
        Card card;
        CardNode next;
        
        public CardNode(Card card){
            this(card, null);
        }
        
        public CardNode(Card card, CardNode next){
            this.card = card;
            this.next = next;
        }
        
        public Card getCard() { return card; }
        public CardNode getNext() { return next; }
        
        public void setCard(Card card) { this.card = card; }
        public void setNext(CardNode next) { this.next = next; }
    }
}
