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

		switch(p.condition)
		{
		case clean:
			switch(p.location) 
			{

			case A:



				jaima.util.VirtualTimer.delay(2);
				VPercept p1 = house.show(sensor);
				switch(p1.condition)
				{
				case clean:	 
					house.execute(motor, right);
					break;
				case dirty:
					house.execute(motor, suck);
					house.execute(motor, right);
					break;
				}
				break;
			case B:
				house.execute(motor, suck);
				jaima.util.VirtualTimer.delay(2);
				VPercept p3 = house.show(sensor);
				switch(p3.condition)
				{
				case clean:	 
					house.execute(motor, left);
					break;
				case dirty:
					house.execute(motor, suck);
					house.execute(motor, left);
					break;
				}
				break;
			default:
				jaima.util.VirtualTimer.delay(1);
			}
			break;
		case dirty:
			house.execute(motor, suck);
			switch(p.location) 
			{
			case A:



				jaima.util.VirtualTimer.delay(2);
				VPercept p1 = house.show(sensor);
				switch(p1.condition)
				{
				case clean:	 
					house.execute(motor, right);
					break;
				case dirty:
					house.execute(motor, suck);
					house.execute(motor, right);
					break;
				}
				break;
			case B:
				house.execute(motor, suck);
				jaima.util.VirtualTimer.delay(2);
				VPercept p3 = house.show(sensor);
				switch(p3.condition)
				{
				case clean:	 
					house.execute(motor, left);
					break;
				case dirty:
					house.execute(motor, suck);
					house.execute(motor, left);
					break;
				}

				break;
			default:
				jaima.util.VirtualTimer.delay(1);

			}

		}

	}}

