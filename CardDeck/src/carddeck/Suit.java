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
public enum Suit {
    SPADES(3), CLUBS(2), HEARTS(1), DIAMONDS(0);
    
    private final int value;
    private Suit(int value) { this.value = value; }
    public int getValue() { return value; }
    
    @Override
    public String toString(){
        String base = super.toString();
        return base.charAt(0) + base.substring(1).toLowerCase();
    }
}
