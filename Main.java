

public class Main
{
    public static void main(String[] args){
        CardDeck deck = new CardDeck();
        deck.createDeck();
        
        int count = 25;
        while(count > 0){
            System.out.println(deck.getRandomCard().toString());
            count--;
        }
    }
}
