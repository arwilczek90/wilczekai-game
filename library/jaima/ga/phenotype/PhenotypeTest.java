package jaima.ga.phenotype;

import java.util.List;
import java.util.Arrays;
import java.math.BigInteger;

/**
 * The test class PhenotypeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PhenotypeTest extends junit.framework.TestCase
{
    private RawIntPhenotype      maxIntPh1;
    private RawLongPhenotype     rawLongP1;
    private RawDoublePhenotype   rawDoubl1;
    private ASCIIStringPhenotype aSCIIStr1;
    private RangeDoublePhenotype rangeDou1;
    private CollectionBasedPhenotype<String> collecti1;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        maxIntPh1 = new RawIntPhenotype();
        rawLongP1 = new RawLongPhenotype();
        rawDoubl1 = new RawDoublePhenotype();
        aSCIIStr1 = new ASCIIStringPhenotype(5);
        rangeDou1 = new RangeDoublePhenotype(2.5, 10, .05);
        String[] labels = new String[] {"a", "b", "c"};
        List<String> list = Arrays.asList(labels);
        collecti1 = new CollectionBasedPhenotype<String>(list);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    public void test25()
    {
        BigInteger bigInteg1 = maxIntPh1.setValue(25);
        assertNotNull(bigInteg1);
        assertEquals(25, maxIntPh1.getValue(bigInteg1));
    }

    public void test2500000000()
    {
        BigInteger bigInteg1 = rawLongP1.setValue(2500000000L);
        assertNotNull(bigInteg1);
        assertEquals(2500000000L, rawLongP1.getValue(bigInteg1));
    }

    public void testRawDouble()
    {
        BigInteger bigInteg1 = rawDoubl1.setValue(2.5);
        assertNotNull(bigInteg1);
        assertEquals(2.5, rawDoubl1.getValue(bigInteg1));
        
        bigInteg1 = rawDoubl1.setValue(2.6);
        assertNotNull(bigInteg1);
        assertEquals(2.6, rawDoubl1.getValue(bigInteg1));
        
        bigInteg1 = rawDoubl1.setValue(10.0);
        assertNotNull(bigInteg1);
        assertEquals(10.0, rawDoubl1.getValue(bigInteg1));
    }

    public void testRangeDouble()
    {
        BigInteger bigInteg1 = rangeDou1.setValue(2.5);
        assertNotNull(bigInteg1);
        assertEquals(2.5, rangeDou1.getValue(bigInteg1));
        
        bigInteg1 = rangeDou1.setValue(2.6);
        assertNotNull(bigInteg1);
        assertEquals(2.6, rangeDou1.getValue(bigInteg1));
        
        bigInteg1 = rangeDou1.setValue(10.0);
        assertNotNull(bigInteg1);
        assertEquals(10.0, rangeDou1.getValue(bigInteg1));
    }

    public void testHello()
    {
        BigInteger bigInteg1 = aSCIIStr1.setValue("hello");
        assertNotNull(bigInteg1);
        assertEquals("hello", aSCIIStr1.getValue(bigInteg1));
    }
    
    public void testList()
    {
        BigInteger bigInteg1 = collecti1.setValue("a");
        assertNotNull(bigInteg1);
        assertEquals("a", collecti1.getValue(bigInteg1));
        
        bigInteg1 = collecti1.setValue("c");
        assertNotNull(bigInteg1);
        assertEquals("c", collecti1.getValue(bigInteg1));
    }
}

