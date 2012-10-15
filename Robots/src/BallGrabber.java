
import lejos.nxt.*;
import lejos.util.Timer;



public class BallGrabber {
	static NXT nxt = new NXT();
	static NXTMotor m1 = new NXTMotor(MotorPort.B);
	static NXTMotor m2 = new NXTMotor(MotorPort.C);
	static NXTMotor m3 = new NXTMotor(MotorPort.A);
	static UltrasonicSensor us = new UltrasonicSensor(SensorPort.S3);
	static LightSensor ls = new LightSensor(SensorPort.S1);


	public static void main(String[] args)
	{

//		ls.setFloodlight(false);
		ls.setFloodlight(6);
		Motor.B.setSpeed(180);
		Motor.A.setSpeed(1080);
		Motor.A.resetTachoCount();
		Motor.C.setSpeed(180);
		boolean rballsfound = false;
		boolean bballsfound = false;
		while((!rballsfound && !bballsfound)||(rballsfound && !bballsfound) || (!rballsfound && bballsfound))
		{
			float range = us.getRange();
			String distance = new String();
			distance = "Distance = " + range;
			int color = ls.getNormalizedLightValue();
			LCD.drawString(distance, 0, 0);
			LCD.drawInt(color, 0, 1);
			while(range > 50.00)
			{
				Motor.B.backward();
				Motor.C.forward();
				range = us.getRange();
				distance = "Distance = " + range;
				color = ls.getNormalizedLightValue();
				LCD.drawString(distance, 0, 0);
				LCD.drawInt(color, 0, 1);

			}
			Motor.B.stop();
			Motor.C.stop();
		// 	Motor.C.rotate(-76);
			while (range > 10.00)
			{
				Motor.B.forward();
				Motor.C.forward();
				range = us.getRange();
				distance = "Distance = " + range;
				color = ls.getNormalizedLightValue();
				LCD.drawString(distance, 0, 0);
				LCD.drawInt(color, 0, 1);

			}
			Motor.B.stop();
			Motor.C.stop();

		
				Motor.A.rotate(180);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			distance = "Distance = " + range;
			color = ls.getNormalizedLightValue();
			LCD.drawString(distance, 0, 0);
			LCD.drawInt(color, 0, 1);
			System.out.println(color);

			if(color >= 380 && color <= 420 )
			{
				LCD.drawString("Red", 0, 3);
				Motor.B.backward();
				Motor.C.forward();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Motor.B.forward();
				
				Motor.A.rotate(-160);
				rballsfound = true;

			}
			else if(color > 260 && color <= 280)
			{
				LCD.drawString("Blue",0,4);

				

				Motor.B.backward();
				Motor.C.forward();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Motor.B.forward();
				
				Motor.A.rotate(-160);
				bballsfound = true;
			}
			else 
				Motor.A.rotate(-160);



			Motor.B.backward();
			Motor.C.forward();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			//		lcd.drawInt(distance,0,0);
		}		


	}}


