package jaima.ga.phenotype;

import java.math.BigInteger;
import static java.lang.Math.*;

/**
 * Write a description of class RangeDouble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OldRangeDoublePhenotype
implements Phenotype
{
    double min, max, prec;
    int width, steps;
    
    /**
     * Constructor for objects of class RangeDoublePhenotype
     * @param pr the desired precision.  Values will actually be 
     * stored with precision slightly finer than pr.
     */
    public OldRangeDoublePhenotype(double mn, double mx, double pr)
    {
        min = min(mn, mx);
        max = max(mn, mx);
        double pre = pr;
        
        int ste = (int) ((max - min) / pre);
        width = (int) ceil(log(ste) / log(2));
        steps = (int) pow(2, width);
        prec = (max - min) / steps;
        
    }
    
    public int width() { return width; }
    public Class<?> returnClass() { return Double.class; }
    
    public Object getValue(BigInteger b)
    {
        int el = (b.intValue() % steps + steps) % steps;
        return (el * prec) + min;
    } 
    
    public BigInteger setValue(Object v) 
    { 
        double d = (Double) v;
        assert(min <= d && d <= max);
        int bits = (int) ((d - min) / prec);
        assert(bits <= steps);
        return new BigInteger("" + bits);
    }
}
