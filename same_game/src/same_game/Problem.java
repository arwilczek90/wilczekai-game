package same_game;
import java.util.*;
import jaima.util.*;
import jaima.search.*;

/**
 * Write a description of class Problem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Problem
extends jaima.search.Problem
{
    private TileBoard board;
    private TileBoard goal;
    
    /**
     * Create the standard 15-hole puzzle.
     */
    public Problem()
    {
        this("");
    }
    
    /**
     * Create a pegboard 
     */
    public Problem(String s)
    {
    	
        board = new TileBoard(s);
    }
    
    public void setGoal(String s)
    {
        goal = new TileBoard(s);
    }
    
    public void setBoard(String s)
    {
        board = new TileBoard(s);
    }
    
    public State initial()
    {
        return (State) board;
    }
    
    public State goal()
    {
        return goal;
    }
    
    public boolean isGoal(State p)
    {
        boolean goal = true;
        
        String state = p.toString();
        
        for(int x = 0; x < state.length(); x++) {
        	
        	if(state.charAt(x) != 'X') {
        		goal = false;
        		break;
        	}
        }
        
        return goal;
    }
    
    public Map<SearchAction, State> successors(State p)
    {
        return ((TileBoard) p).successors();
    }
}
