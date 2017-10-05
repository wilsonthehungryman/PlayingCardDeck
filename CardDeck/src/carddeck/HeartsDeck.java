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
public class HeartsDeck extends CardDeck {
    private int players;

    public HeartsDeck(int players) {
        super();
        this.players = players;
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
//        if(players == 3)
    }

    public int getPlayers() {
        return players;
    }
}
