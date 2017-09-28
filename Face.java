
/**
 * Enumeration class Face - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Face
{
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), JOKER(0);
    
    private final int value;
    private Face(int value) { this.value = value; }
    
    public int getValue() { return value; }
    
    @Override
    public String toString(){
        String base = super.toString();
        return base.charAt(0) + base.substring(1).toLowerCase();
    }
}
