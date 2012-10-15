package jaima.game;

/**
 * Write a description of class Action here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Move
implements Comparable<Move>
{
    /**
     * You must define a string conversion that identifies
     * each distinct state.
     */
    public abstract String toString();
    
    /**
     * You may overload this method
     * to define a version for game display
     */
    public String prettyPrint()
    {
        return toString();
    }
    
    /**
     * You may define a more efficient comparison
     */
    public int compareTo(Move s)
    {
        return toString().compareTo(s.toString());
    }
}
