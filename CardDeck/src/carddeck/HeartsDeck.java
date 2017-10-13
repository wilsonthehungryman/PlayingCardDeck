/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

/**
 *
 * @author Wilson
 */
public class HeartsDeck extends CardDeck<PlayingCard> {
    private int players;
    private PlayingCardComparator comparator;

    public HeartsDeck(int players, PlayingCardComparator comparator) {
        super();
        this.players = players;
        this.comparator = comparator;
        orderingBy = comparator.getCardComparator();
    }

    public PlayingCardComparator getComparator() {
        return comparator;
    }

    public void setComparator(PlayingCardComparator comparator) {
        this.comparator = comparator;
    }
    
    public HeartsDeck(int players) {
        this(players, new PlayingCardComparator(Suit.values(), Face.values()));
    }
    
    public HeartsDeck(){
        this(4);
    }
    
    public void createDeck() {
        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                if (face != Face.JOKER) {
                    add(new PlayingCard(face, suit));
                }
            }
        }
        //TODO: logic to remove cards based on number of players (or adjust loop)
    }

    public int getPlayers() {
        return players;
    }
}
