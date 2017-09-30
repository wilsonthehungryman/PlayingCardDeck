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
    SPADES, CLUBS, HEARTS, DIAMONDS;
    
    @Override
    public String toString(){
        String base = super.toString();
        return base.charAt(0) + base.substring(1).toLowerCase();
    }
}
