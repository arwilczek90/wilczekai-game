package same_game;
import java.util.*;
import jaima.search.*;
/**
 * The test class PegBoardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SameGameTest extends junit.framework.TestCase
{
	private TileBoard pegBoard1;
	private TileBoard pegBoard2;
	private Set<State> boards;
    /**
     * Default constructor for test class PegBoardTest
     */
    public SameGameTest()
    {
    }
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		pegBoard1 = new TileBoard("RGRG");
		//pegBoard1.jump(3, 1, 0);
		System.out.println(pegBoard1.prettyPrint());
		
		pegBoard2 = new TileBoard("RGRG");
		System.out.println(pegBoard2.prettyPrint());
		boards = new TreeSet<State>();
	}
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
	public void testAssertNotSame()
	{
	    assertNotSame(pegBoard1, pegBoard2);
	}
	public void testAssertEquals()
	{
	    assertEquals(pegBoard1, pegBoard2);
	}
	
	public void testTree()
	{
	    boards.add(pegBoard1);
	    boards.add(pegBoard2);
	    assertEquals(boards.size(), 1);
	}
}

