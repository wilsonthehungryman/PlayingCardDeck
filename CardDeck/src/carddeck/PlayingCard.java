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
public class PlayingCard extends Card {

    Suit suit;
    Face face;

    public PlayingCard(Face face, Suit suit) {
        this(face, suit, face.getValue());
    }

    public PlayingCard(Face face, Suit suit, int points) {
        super("The " + face.toString() + " of " + suit.toString(), points);
        this.suit = suit;
        this.face = face;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

}
