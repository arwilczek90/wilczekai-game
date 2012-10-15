package same_game;
import java.util.*;
import jaima.search.*;

/**
 * Write a description of class PegBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TileBoard
extends jaima.search.State
implements Cloneable
{
    // true if there's a peg in the hole, 
    // false otherwise
    private char[] tiles;
    private ArrayList<Integer> removed;
    private int dimension;
    private Random gen;
    private String list;
    
    public TileBoard (String list)
    {
        super(list);
        removed = new ArrayList<Integer>();
        tiles = new char[list.length()];
        tiles = list.toCharArray();
        dimension = (int) Math.sqrt(list.length());
        gen = new Random();
        this.list = list;
        
    }
    
    public String toString()
    {
        return new String(tiles);
    }
    
    public TileBoard clone()
    {
        return new TileBoard(toString());
    }
    

    public void collapse(int selected) {
    	
//    	System.out.println("list: " + toString());
    	
    	char color = tiles[selected];
    	
    	if(color == 'X')
    		return;
    	
    	removed.add(selected);
    	tiles[selected] = 'X';
    	
    	int topIndex = selected - dimension;
    	
//    	System.out.println("topIndex: " + topIndex);
    	
    	if(topIndex > 0) {
    		char top = tiles[topIndex];

    		if(top == color) {
    			top = 'X';
    			removed.add(topIndex);
    			tiles[topIndex] = top;
    			collapse(topIndex);
    		}
    	}
    	
//    	System.out.println("bottom");
    	
    	int bottomIndex = selected + dimension;
    	
    	if(bottomIndex < tiles.length) {
    		char bottom = tiles[bottomIndex];

    		if(bottom == color) {
    			bottom = 'X';
    			removed.add(bottomIndex);
    			tiles[bottomIndex] = bottom;
    			collapse(bottomIndex);
    		}
    	}
    	
    	int leftIndex = selected - 1;
    	
    	if((leftIndex > 0) && ((leftIndex + 1) % dimension != 1)) {
    		char left = tiles[leftIndex];
    		
    		if(left == color) {
    			left = 'X';
    			
    			removed.add(leftIndex);
    			tiles[leftIndex] = left;
    			collapse(leftIndex);
    		}
    	}

//    	System.out.println("left");
    	
    	int rightIndex = selected + 1;
    	
    	
    	if((rightIndex < tiles.length) && ((rightIndex + 1) % dimension != 0)) {
    	
    		char right = tiles[rightIndex];

    		if(right == color) {
    			right = 'X';
    			removed.add(rightIndex);
    			tiles[rightIndex] = right;
    			collapse(rightIndex);
    		}
    	}
    	
//    	System.out.println("right");
    	
    	Collections.sort(removed);
    	
    	for(int x = removed.size() - 1; x >= 0; x--) {
    		
    		drop(removed.get(x));
    	}
    	
    	shift(tiles.length - 1);
    	
    	removed.clear();
    	
//    	System.out.println("base case");
    	
 }
    
public void drop(int location) {
	
	int aboveIndex = location - dimension;
	

//	System.out.println("aboveIndex: " + aboveIndex);
	
	if(aboveIndex >= 0 && aboveIndex < tiles.length) {
	
		char above = tiles[aboveIndex];

		tiles[location] = above;

		drop(above);
	}
	
}

public void shift(int location) {
	
	if(1 == (location % dimension))
		return;
	
	char current = tiles[location];
	
	if(current == 'X')
		shift(location- 1);
	else {
		int modifiedLocation = location;
		for(int x = 0; x < dimension; x++) {
			
			tiles[modifiedLocation-1] = tiles[modifiedLocation]; 
			tiles[modifiedLocation] = 'X';
			
			modifiedLocation -= dimension;
		}
		
		shift(location- 1);
	}
	
}

public boolean hasNeighbor(int location) {
	
	int topIndex = location - dimension;
	
	if(topIndex > 0) {
		char top = tiles[topIndex];

		if(top != 'X') 
			return true;
	}
	
	int bottomIndex = location + dimension;
	
	if(bottomIndex < tiles.length) {
		char bottom = tiles[bottomIndex];

		if(bottom != 'X') 
			return true;
	}
	
	int leftIndex = location - 1;
	
	if((leftIndex > 0) && ((leftIndex + 1) % dimension != 1)) {
		char left = tiles[leftIndex];
		
		if(left != 'X') 
			return true;
	}
	
	int rightIndex = location + 1;
	
	if((rightIndex < tiles.length) && ((rightIndex + 1) % dimension != 0)) {
	
		char right = tiles[rightIndex];

		if(right != 'X') 
			return true;
	}
	
	return false;
}
    
    public Map<SearchAction, State> successors()
    {
        Map<SearchAction, State> collapses 
            = new HashMap<SearchAction, State>();
//        
//        System.out.println("length: " + tiles.length);
//        System.out.println("list: " + list);
//        
        //int random = gen.nextInt(tiles.length); 
        
        for(int x = 0; x < tiles.length; x++) {
        	
        	if(hasNeighbor(x)) {
        		addSuccessor(collapses, x);
        	}
        	
        	
        }
        
       // System.out.println("random: " + random);
        /*
            
        // try possible horizontal jumps
        for (int i = 3; i <= holes.length - 3; i++) {
            int j = i + 1;
            int k = i + 2;
            if(row(i) != row(k))
                continue;
            addSuccessor(jumps, i, j, k);
        }
       
        // find / jumps
        for (int r = 0; r < row(holes.length) - 2; r++) {
            for(int c = 0; c <= r; c++) {
                int i = hole(r, c);
                int j = hole(r + 1, c);
                int k = hole(r + 2, c);
                addSuccessor(jumps, i, j, k);
            }
        }
       
        // find \ jumps
        for (int r = 0; r < row(holes.length) - 2; r++) {
            for(int c = 0; c <= r; c++) {
                int i = hole(r, c);
                int j = hole(r + 1, c + 1);
                int k = hole(r + 2, c + 2);
                addSuccessor(jumps, i, j, k);
            }
        }
        */
        return collapses;
        
       
    }
    
    public void addSuccessor(
        Map<SearchAction, State> collapses,
        int location)
    {
    	
    	Collapse collapse = new Collapse(location);
    	
    	TileBoard board = clone();
    	board.collapse(location);
    	collapses.put(collapse, board);
    	
    	/*
        // check the pegs
        if(!holes[j])
            return;
        if(holes[i] == holes[k])
            return;
            
            
            
        // success
        Jump jump;
        if(holes[i]) {
            jump = new Jump(i, k);
        } else {
            jump = new Jump(k, i);
        }
        TileBoard p = clone();
        p.jump(i, j, k);
        jumps.put(jump, p);
        
        */
    }
    
    public String prettyPrint()
    {
    	
        String old = toString();
        String nu = new String();
        for(int i = 0; i<= dimension; i++)
        {
        	
        	nu = nu + "\n" + old.substring(dimension*i, dimension * (i+1)); 
        }
        
        
        return nu + "\n";

    }
    
}