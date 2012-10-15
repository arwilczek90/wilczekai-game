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
public class Big 
extends junit.framework.TestCase
{
    // A small problem instance designed to ensure that 
    // the algorithms are thorough.
    // The the goal is unreachable from the initial state, 
    // but the algorithm should explore all reachable states
    // without generating a fault.
    private Problem big;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
         Node.reporting.add(Node.Reports.action); 
        Node.reporting.add(Node.Reports.pretty); 
         Node.reporting.add(Node.Reports.pathcost);
        Metrics.clearAll();
        Log.set("/dev/null");
        
        big = new Problem("RGRGRBBRRBRGGGR");
        
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
        assertTrue(Solver.test(big, strategy));
    }

    public void testRDFS() 
    {
        Strategy strategy = new RDFS();
        assertTrue(Solver.test(big, strategy));
    }

    public void testR2() 
    {
        Strategy strategy = new R2();
        assertTrue(Solver.test(big, strategy));
    }

    public void testDLS() 
    {
        Strategy strategy = new DLS(6);
        assertTrue(Solver.test(big, strategy));
    }

    public void testIDS() 
    {
        Strategy strategy = new IDS();
        assertTrue(Solver.test(big, strategy));
    }
    
    /**
     * should peform identically to 
     * simple recursive depth-first search
     */
    public void testTreeSQDepthFS() 
    {
        SearchQueue sq = new TreeSQ();
        Strategy strategy = new QDFS(sq);
        assertTrue(Solver.test(big, strategy));
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
        assertTrue(Solver.test(big, strategy));
    }
    
//    public void testBreadthFS() 
//    {
//        // change this to TreeSQ() if duplicate
//        // states are impossible
//        SearchQueue sq = new GraphSQ();
//        Strategy strategy = new BreadthFS();
//        assertTrue(Solver.test(big, strategy));
//    }
//    
    public void testGreedy() 
    {
        // change this to TreeSQ() if duplicate
        // states are impossible
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new Greedy(sq);
        assertTrue(Solver.test(big, strategy));
    }
    
    public void testUCost() 
    {
        // change this to TreeSQ() if duplicate
        // states are impossible
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new UCost(sq);
        assertTrue(Solver.test(big, strategy));
    }
    
    public void testAStar() 
    {
        // change this to TreeSQ() if duplicate
        // states are impossible
        SearchQueue sq = new GraphSQ();
        Strategy strategy = new AStar(sq);
        assertTrue(Solver.test(big, strategy));
    }
}
