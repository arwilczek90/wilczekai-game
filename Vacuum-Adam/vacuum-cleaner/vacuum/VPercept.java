package vacuum;

/**
 * Things the Vacuum can see.
 * 
 * @author William H. Hooper 
 * @version 2007-01-11
 */
public class VPercept
implements jaima.agent.Percept
{
    public enum Location { A, B }
    public final Location location;
    
    public enum Condition { clean, dirty }
    public final Condition condition;
    
    public VPercept(Location l, Condition c)
    {
        location = l;
        condition = c;
    }
}
