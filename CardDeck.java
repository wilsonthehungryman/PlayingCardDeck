import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.Random;
// Using queue (with some stack methods)
public class CardDeck implements Iterable<Card>
{
    private CardNode top, tail;
    private int size;
    
    public CardDeck(){
        top = null;
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
    
    public void queue(Card card){
        if(top == null){
            top = new CardNode(card, null);
            tail = top;
        }else{
            CardNode newNode = new CardNode(card, null);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

        public Card getRandomCard(){
        return getRandomNode().getCard();
    }
    
    private CardNode getRandomNode(){
        if(top == null)
            throw new NoSuchElementException();
            
        if(size == 1)
            return top;
            
        int n = new Random().nextInt(size * 2);
        CardNode current = top;
        while(n > 0){
            current = current.getNext();
            if(current == null)
                current = top;
            n--;
        }
        return current;
    }
    
    public Iterator<Card> iterator(){
        return new DeckIterator(top);
    }
    
    private static final class DeckIterator implements Iterator<Card>{
        private CardNode current;
        
        public DeckIterator(CardNode top){
            this.current = top;
        }
        
        public boolean hasNext() { return current != null; }
        
        public Card next() {
            if(current == null)
                throw new NoSuchElementException();
            CardNode returnNode = current;
            current = current.getNext();
            return returnNode.getCard();
        }
    }
    
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
