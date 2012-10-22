
import jaima.util.Metrics;
import jaima.game.Player;
import jaima.game.strategies.*;
import com.game.pente.*;

/**
 * Write a description of class ticTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pente
{
    private Pente() { }
    
    public static void main(String[] args)
    {
        System.out.print("\f");
        Metrics.clearAll();
        
        Strategy[] strategies = {
//             new Guess(),
//            new Ask("Bill"),
            new MiniMax(3),
          new AlphaBeta(1),
//             new Guess(),
//            new Ask("Bill"),
//            new MiniMax(3),
//             new AlphaBeta(9),
        };
        
        penteState board = new penteState("XXXXXXXXWXXXWXBXXXXBXXXXX");
        int turn = 0;
        
        Player p = board.toMove();
        while(board.terminal() == false) {
            System.out.println(board.prettyPrint());
            Strategy s = strategies[turn];
            jaima.game.Move m = s.choose(board);
            String string = new String();
            string = s.toString();
            string += "'s move: ";
            string += m.prettyPrint();
            string += "\n";
            System.out.println(string);
           board.turn++;
            board = board.result(m);
            turn = 1 - turn;
        }
        System.out.println(board.prettyPrint());
        
        String announce = "Game result: tie";
        if(board.utility(p) > 0) {
            announce = "Winner is " + strategies[0];
        } else if(board.utility(p) < 0) {
            announce = "Winner is " + strategies[1];
        }
        System.out.println(announce);
        System.out.println(Metrics.getAll());
    }
}
