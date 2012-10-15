

// import java.io.PrintStream;
package same_game;
import jaima.util.*;
import jaima.search.*;
import jaima.search.strategies.*;

/**
 * The test class Trivial.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Trivial 
extends junit.framework.TestCase
{
    // Problem instances designed to ensure that the state
    // and problem definitions are correct.
    
    // The initial state is a solution, 
    // to test goal detection.
    private Problem instant;
    
    // There is one sequence of actions from problem to solution.
    // Algorithms should find the one solution immediately.
    private Problem line;
    
    // There are no valid moves from the initial state.
    // Algorithms should fail gracefully.
    private Problem stuck;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        Node.reporting.add(Node.Reports.action); 
        Node.reporting.add(Node.Reports.pathcost);
        Metrics.clearAll();
        //Log.set("/dev/null");
        
        instant = new Problem("RRRR");
        line = new Problem("RRGBBGGGRR"); 
        stuck = new Problem("RGGR");
        
        VirtualTimer.startTimer();
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
        Metrics.set("Seconds elapsed: ", VirtualTimer.elapsed());
        System.out.println(Metrics.getAll());
    }
    
    public void testFlounderInstant() 
    {
        Strategy strategy = new Flounder();
        assertTrue(Solver.test(instant, strategy));
    }
    
    public void testFlounderLine() 
    {
        Strategy strategy = new Flounder();
        assertTrue(Solver.test(line, strategy));
    }
    
    public void testFlounderStuck() 
    {
        Strategy strategy = new Flounder();
        assertFalse(Solver.test(stuck, strategy));
    }
    
    public void testGQDFSInstant() 
    {
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new QDFS(sq);
        assertTrue(Solver.test(instant, strategy));
    }
    
    public void testGQDFSLine() 
    {
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new QDFS(sq);
        assertTrue(Solver.test(line, strategy));
    }
    
    public void testGQDFSStuck() 
    {
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new QDFS(sq);
        assertFalse(Solver.test(stuck, strategy));
    }
}
