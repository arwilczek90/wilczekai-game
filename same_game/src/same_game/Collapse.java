package same_game;

/**
 * Write a description of class Jump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collapse
extends jaima.search.SearchAction
{
    private int location;
    
    public Collapse(int location)
    {
    	this.location = location;
    }
    
    public double cost()
    {
        return 1;
    }
    
    public String toString()
    {
        return "Collapse at location: " + location;
    }
}
