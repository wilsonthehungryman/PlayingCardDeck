import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.Random;
// Using Circularily singly linked linked list
public class CardDeck implements Iterable<Card>
{
    private CardNode head, tail;
    private int size;
    
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
            if(suit != Suit.NONE){
                for(Face face : Face.values()){
                    if(face != Face.JOKER)
                        queue(new Card(face, suit));
                }
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
    
    public boolean isEmpty() { return size == 0; }
    
    public void queue(Card card){
        if(head == null){
            head = new CardNode(card, null);
            setTail(head);
        }else{
            setTail(new CardNode(card, null));
        }
        size++;
    }
    
    private void setHead(CardNode node){
        head = node;
        if(tail == null)
            tail = head;
        tail.setNext(head);
    }
    
    private CardNode removeHead(){
        if(head == null)
            throw new NoSuchElementException();
            
        CardNode oldHead = head;
        
        if(size == 1){
            tail = null;
            head = null;
        }else{
            head = head.getNext();
            tail.setNext(head);
        }
        size--;
        oldHead.setNext(null);
        return oldHead;
    }
    
    private CardNode getBeforeTailNode(){
        CardNode current = head;
        while(current.getNext() != tail)
            current = current.getNext();
        return current;
    }
    
    private void setTail(CardNode node){
        if(head == null){
            setHead(node);
            return;
        }
        if(tail == null)
            tail = head;
            
        tail.setNext(node);
        tail = node;
        tail.setNext(head);
    }
    
    private CardNode removeTail(){
        return removeTail(getBeforeTailNode());
    }
    
    private CardNode removeTail(CardNode beforeTailNode){
        CardNode oldTail = tail;
        tail = beforeTailNode;
        tail.setNext(head);
        size--;
        return oldTail;
    }
    
    private void appendNodeCustom(CardNode target, CardNode head, CardNode tail){
        if(head == null){
            head = target;
            tail = head;
            tail.setNext(head);
        }else{
            tail.setNext(target);
            tail = target;
            target.setNext(head);
        }
    }
    
    
    private CardNode remove(CardNode previous, CardNode target){
        if(target == null || previous == null)
            throw new NoSuchElementException();
        if(size == 1){
            target = head;
            head = null;
            tail = null;
            size--;
            return target;
        }
        
        // previous == tail
        if(target == head){
            head = target.getNext();
            previous.setNext(head);
        }else if(target == tail){
            removeTail(previous);
        }else{
            previous.setNext(target.getNext());
        }
        
        size--;
        target.setNext(null);
        return target;
    }
    
    public int getSize(){ return size; }
    
    // ------------------- Iterator ----------------------
    
    public Iterator<Card> iterator(){
        return new DeckIterator(head);
    }
    
    private static final class DeckIterator implements Iterator<Card>{
        private CardNode current;
        private CardNode start;
        
        public DeckIterator(CardNode head){
            this.current = head;
            this.start = head;
        }
        
        public boolean hasNext() { return current != start; }
        
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
    private class CardNode{
        Card card;
        CardNode next;
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
