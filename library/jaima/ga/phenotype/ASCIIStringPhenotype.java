package jaima.ga.phenotype;

import java.math.BigInteger;
import static java.math.BigInteger.*;

/**
 * Write a description of class StringPhenotype here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ASCIIStringPhenotype
implements Phenotype
{
    private int length;
    
    public ASCIIStringPhenotype(int len)
    {
        length = len;
    }
    
    public int width() { return length * 7; }
    
    public Class<?> returnClass() 
    { return String.class; }
    
    public Object getValue(BigInteger b)
    {
        String s = "";
        BigInteger mask = new BigInteger("127"); // i.e. 111111
        
        for(int i = 0; i < length; i++) {
            int shift = i * 7;
            int v = b.shiftRight(shift).and(mask).intValue();
            char c = (char)(v % 96 + 32);
            s += c;
        }
        return s;
    } 
    
    public BigInteger setValue(Object v) 
    { 
        String s = (String) v;
        BigInteger b = ZERO;
        
        for(int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int shift = i * 7;
            int j = ((int)c - 32) % 96;
            BigInteger a = new BigInteger("" + j);
            b = b.or(a.shiftLeft(shift));
        }
        return b;
    }
}
