package jaima.game;


/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player
{
    /**
     * Define a string conversion that identifies
     * each distinct player.
     */
    public abstract String toString();
    
    public boolean equals(Player p) {
        return toString().equals(p.toString());
    }
    
    /**
     * optionally, define a more informative description
     */
    public String prettyPrint() {
        return toString();
    }
}
