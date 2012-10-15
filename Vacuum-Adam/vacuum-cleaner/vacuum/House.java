package vacuum;

import java.util.Map;
import vacuum.VPercept.Location;
import vacuum.VPercept.Condition; 
import vacuum.VAction; 

/**
 * House - a place that needs cleaning
 * 
 * @author William H. Hooper 
 * @version 2007-01-11
 */
public class House
extends jaima.agent.Environment
{
    private Map<Location, Condition> conditions;
    private Vacuum vacuum;
    
    public House(Map<Location, Condition> c, Vacuum v)
    {
        conditions = c;
        vacuum = v;
    }
    
    public VPercept show(jaima.agent.Sensor s)
    {
//        jaima.util.VirtualTimer.delay(0.1);
        Location l = vacuum.location;
        Condition c = conditions.get(l);
        jaima.util.Metrics.increment("show");
        return new VPercept(l, c);
    }
    
    public void execute(jaima.agent.Actuator t, jaima.agent.Action a)
    {
        VAction action = (VAction) a;
        jaima.util.Metrics.increment("" + action);
        switch(action)
        {
            case left: 
//                jaima.util.VirtualTimer.delay(0.3);
                vacuum.location = Location.A; break;
            case right: 
//                jaima.util.VirtualTimer.delay(0.3);
                vacuum.location = Location.B; break;
            case suck: 
//                jaima.util.VirtualTimer.delay(1.0);
                conditions.put(vacuum.location, Condition.clean);
        }
    }
}
