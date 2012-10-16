package com.game.pente;

/**
 * The test class TicTacTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class pentetest extends junit.framework.TestCase
{
    private com.game.pente.penteState board1;
    private jaima.game.strategies.Guess guess;
    private jaima.game.strategies.Ask ask;
    private jaima.game.strategies.MiniMax minimax;
    private jaima.game.strategies.AlphaBeta alphabeta;

    /**
     * Default constructor for test class TicTacTest
     */
    public pentetest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        board1 = new com.game.pente.penteState("xxxxxxxxxxxxWxxxxxxBxxxxx");
        guess = new jaima.game.strategies.Guess();
      //  ask = new jaima.game.strategies.Ask("Bill");
        minimax = new jaima.game.strategies.MiniMax(6);
        alphabeta = new jaima.game.strategies.AlphaBeta(8);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    public void testAsk() {
    	assertNotNull(ask.choose(board1));
    }
    
    public void testGuess() {
    	assertNotNull(guess.choose(board1));
    }
    
    public void testMiniMax() {
    	assertNotNull(minimax.choose(board1));
    }
    
    public void testAlphaBeta() {
    	assertNotNull(alphabeta.choose(board1));
    }
}

