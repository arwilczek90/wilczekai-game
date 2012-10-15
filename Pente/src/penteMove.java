import jaima.game.Move;
import jaima.game.Player;

public class penteMove extends Move{

	private Player turn;
    private int row;
    private int column;
    
    public penteMove(pentePlayer p, int r, int c)
    {
        super();
        turn = p;
        row = r;
        column = c;
    }
    
    public int row()
    {
        return row;
    }
    
    public int col()
    {
        return column;
    }
    
    public String toString()
    {
        return turn.toString() + row + column;
    }
    
    public String prettyPrint()
    {
        return "Place an " + turn 
            + " in row " + row
            + ", column " + column;
    }

}
