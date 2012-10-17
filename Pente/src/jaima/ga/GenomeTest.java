package jaima.ga;

import jaima.ga.phenotype.*;


/**
 * The test class GenomeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GenomeTest extends junit.framework.TestCase
{
    private Genome genome1;
    private Phenotype[] phenome1;
    
    private Genome genome2;
    private Phenotype[] phenome2;
    
    private Phenotype[] phmix;
    private Object[] valuemix;
    private Genome genmix;
    
    private Genome genome4;

    /**
     * Default constructor for test class GenomeTest
     */
    public GenomeTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        genome1 = new Genome(new java.math.BigInteger("25"));
        phenome1 = new Phenotype[] { new RawIntPhenotype() };
        
        phenome2 = new Phenotype[] { new ASCIIStringPhenotype(5) };
        genome2 = new Genome(phenome2[0].setValue("hello"));
        
        phmix = new Phenotype[] {
            new RawIntPhenotype(),
            new ASCIIStringPhenotype(5),
            new RawLongPhenotype(),
            new RawDoublePhenotype(),
        };
        
        valuemix = new Object[] {
            25,
            "hello",
            2500000000L,
            2.6,
        };
        
        genmix = new Genome(phmix, valuemix);

        genome4 = new Genome(100);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void testInt()
    {
        Object[] object1 = genome1.getPhenome(phenome1);
        assertNotNull(object1);
        int v = (Integer) object1[0];
        assertTrue(v == 25);
    }

    public void testString()
    {
        Object[] object1 = genome2.getPhenome(phenome2);
        assertNotNull(object1);
        String s = (String) object1[0];
        assertTrue(s.equals("hello"));
    }

    public void testMix()
    {
        Object[] object1 = genmix.getPhenome(phmix);
        assertNotNull(object1);
        int v = (Integer) object1[0];
        assertTrue(v == 25);
    }
    
    public void testMask()
    {
        genome4.showMask();
    }
    
//     public void testMask2()
//     {
//         genome4.showMask2();
//     }
}

