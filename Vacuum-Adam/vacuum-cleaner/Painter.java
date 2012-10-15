import java.applet.*;

/**
 * a thread to display the state of the House
 * 
 * @author William H. Hooper 
 * @version 2007-01-11
 */
class Painter
extends Thread
{
    Applet applet;
    double delay;
    boolean running;
    
    public Painter(Applet a)
    {
        applet = a;
        delay = 0.1;
        running = false;
    }

    public void run()
    {
        running = true;
        while(running)
        {
            jaima.util.VirtualTimer.delay(delay);
            applet.repaint();
        }
    }
    
    public void quit()
    {
        running = false;
    }
}
