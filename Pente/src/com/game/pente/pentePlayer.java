package com.game.pente;
import jaima.game.*;

public class pentePlayer extends Player{

char stone;
    
    /**
     * Choose a mark
     * @param m W or B
     */
    public pentePlayer(char s)
    {
        stone = Character.toUpperCase(s);
    }
    
    public pentePlayer(String s)
    {
        stone = s.toUpperCase().charAt(0);
    }
    
    public String toString()
    {
        return stone + "";
    }
	
	
}
