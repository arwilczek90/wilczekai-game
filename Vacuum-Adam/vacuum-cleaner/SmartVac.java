package vacuum;

import java.util.Random;
import static vacuum.VAction.left; 
import static vacuum.VAction.right; 
import static vacuum.VAction.suck; 

/**
 * Write a description of class Cleaner here.
 * 
 * @author William H. Hooper 
 * @version 2007-01-11
 */
public class SmartVac
extends jaima.agent.Agent
{
	private House house;
    private VSensor sensor; 
    private VActuator motor;
    private Random gen;
    private String location;
    public SmartVac(
        jaima.agent.Environment e, 
        jaima.agent.Sensor[] s,
        jaima.agent.Actuator[] a)
    {
        super(e, s, a);
        house = (House) e;
        sensor = (VSensor) sensors[0];
        motor = (VActuator) actuators[0];
        gen = new Random();
       
    }
   
    public void act()
    {
    	
    	VPercept p = house.show(sensor);
    	
    	
    	if(p == null) return;
    	int guessWhatToDo = gen.nextInt(200000000);
    	
    	switch(p.location) 
    	{
    	case A: 
    		if(p.condition == p.condition.dirty)
    		{
    			house.execute(motor,suck);
    		
    		}
    			
    			if(p.condition == p.condition.clean)
    			{
    				jaima.util.VirtualTimer.delay(2);
    	    		if(p.condition == p.condition.dirty)
    	    		{
    	    			house.execute(motor,suck);
    	    		
    	    		}
    	    			if(p.condition == p.condition.clean)
    	    			{
    	    				house.execute(motor, right);
    	    			}
    			}
    		break;
    	case B:
    		if(p.condition == p.condition.dirty)
    		{
    			house.execute(motor,suck);
    		
    		}
    			if(p.condition == p.condition.clean)
    			{
    				jaima.util.VirtualTimer.delay(2);
    	    		if(p.condition == p.condition.dirty)
    	    		{
    	    			house.execute(motor,suck);
    	    		
    	    		}
    	    		if(p.condition == p.condition.clean)
    	    		{	//house.execute(motor,suck);
    	    			house.execute(motor, left);
    	    		}
    			}
    		break;
    	default:
    		jaima.util.VirtualTimer.delay(1);
    	}
    }
}
