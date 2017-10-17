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
public class Trick {

    PlayingCard[] trick;
    boolean complete;
    int index;
    int points;
    PlayingCard winner;

    public Trick(int players) {
        trick = new PlayingCard[players];
        index = 0;
        points = 0;
    }

    public PlayingCard[] getTrick() {
        return trick;
    }

    public boolean isComplete() {
        return complete;
    }

    public PlayingCard getWinner() {
        return winner;
    }
    
    public PlayingCard lead(){
        return trick[0];
    }
    
    public boolean add(PlayingCard c){
        if(complete)
            throw new IllegalStateException();
        
        trick[index] = c;
        index++;
        points += c.getPoints();
        if(index == trick.length)
            complete = true;
        return determineWinner(c);
    }
    
    public int getPoints(){
        if(trick[0] == null)
            throw new IllegalStateException();
        
        return points;
    }
    
    public void clear(){
        while(index >= 0){
            trick[index] = null;
            index--;
        }
        complete = false;
    }
    
    public Trick newTrick(){
        return new Trick(trick.length);
    }
    
    private boolean determineWinner(PlayingCard c){
        if(winner == null || (winner.suit == c.suit && c.face == Face.ACE || winner.face.getValue() > c.getFace().getValue())){
            winner = c;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        if(index == 0)
            return "Empty Trick";
        
        StringBuilder value = new StringBuilder();
        for(int i = 0; i < index; i++){
            value.append(trick[i]);
            value.append(" ");
        }
        value.append("Worth: ");
        value.append(getPoints());
        return value.toString();
    }
}
