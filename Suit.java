
/**
 * Enumeration class Suit - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Suit
{
    NONE, SPADES, CLUBS, HEARTS, DIAMONDS;
    
    @Override
    public String toString(){
        String base = super.toString();
        return base.charAt(0) + base.substring(1).toLowerCase();
    }
}
