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
public class Card {
    private Face face;
    private int points;
    private Suit suit;
    
    public Card(Face face, Suit suit, int points){
        this.face = face;
        this.points = points;
        this.suit = suit;
    }
    
    public Card(Face face, Suit suit){
        this(face, suit, face.getValue());
    }
    
    public Face getFace() { return face; }
    
    public int getPoints() { return points; }
    
    public Suit getSuit() { return suit; }
    
    public void setFace(Face face) { this.face = face; }

    public void setPoints(int points) { this.points = points; }

    public void setSuit(Suit suit) { this.suit = suit; }
    
    @Override
    public String toString(){
        return "The " + face.toString() + " of " + suit.toString();
    }
}
