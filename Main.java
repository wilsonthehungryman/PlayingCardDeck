

public class Main
{
    public static void main(String[] args){
        CardDeck deck = new CardDeck();
        deck.createDeck();
        
        // deck.shuffle();
        // System.out.println(deck.getSize());
        for(Card c : deck){
            System.out.println(c.toString());
        }
    }
}
