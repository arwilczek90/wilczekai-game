package same_game;

// import java.io.PrintStream;
import jaima.util.*;
import jaima.search.*;
import jaima.search.strategies.*;

/**
 * The test class Trivial.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Hopeless 
extends junit.framework.TestCase
{
    // A small problem instance designed to ensure that 
    // the algorithms are thorough.
    // The the goal is unreachable from the initial state, 
    // but the algorithm should explore all reachable states
    // without generating a fault.
    private Problem hopeless;

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
        Log.set("/dev/null");
        
        hopeless = new Problem("o xx xxx xxxx");
        hopeless.setGoal("o oo ooo oooo");
        
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

    public void testFlounder() 
    {
        Strategy strategy = new Flounder();
        assertFalse(Solver.test(hopeless, strategy));
    }

    public void testRDFS() 
    {
        Strategy strategy = new RDFS();
        assertFalse(Solver.test(hopeless, strategy));
    }

    public void testR2() 
    {
        Strategy strategy = new R2();
        assertFalse(Solver.test(hopeless, strategy));
    }

    public void testDLS() 
    {
        Strategy strategy = new DLS(6);
        assertFalse(Solver.test(hopeless, strategy));
    }

    public void testIDS() 
    {
        Strategy strategy = new IDS();
        assertFalse(Solver.test(hopeless, strategy));
    }
    
    /**
     * should peform identically to 
     * simple recursive depth-first search
     */
    public void testTreeSQDepthFS() 
    {
        SearchQueue sq = new TreeSQ();
        Strategy strategy = new QDFS(sq);
        assertFalse(Solver.test(hopeless, strategy));
    }
    
    /**
     * should peform identically to 
     * recursive depth-first search
     * with loop-checking
     */
    public void testGraphSQDepthFS() 
    {
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new QDFS(sq);
        assertFalse(Solver.test(hopeless, strategy));
    }
    
//    public void testBreadthFS() 
//    {
//        // change this to TreeSQ() if duplicate
//        // states are impossible
//        SearchQueue sq = new GraphSQ();
//        Strategy strategy = new BFS(sq);
//        assertFalse(Solver.test(hopeless, strategy));
//    }
    
    public void testGreedy() 
    {
        // change this to TreeSQ() if duplicate
        // states are impossible
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new Greedy(sq);
        assertFalse(Solver.test(hopeless, strategy));
    }
    
    public void testUCost() 
    {
        // change this to TreeSQ() if duplicate
        // states are impossible
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new UCost(sq);
        assertFalse(Solver.test(hopeless, strategy));
    }
    
    public void testAStar() 
    {
        // change this to TreeSQ() if duplicate
        // states are impossible
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new AStar(sq);
        assertFalse(Solver.test(hopeless, strategy));
    }
}
