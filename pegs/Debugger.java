import java.applet.Applet;
import java.util.Date;
import java.awt.Frame;

public class Debugger
{
    public static void debug() {
        int width = 300;
        int height = 500;
        Applet applet = new PegSolver();
        
        String windowTitle = applet.getClass().getName();
        System.out.println(windowTitle + " created " + new Date());
        Frame frame = new Frame(windowTitle);
//         frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        frame.setSize(width, height + 20);
        applet.setSize(width, height);
        frame.add(applet);
        applet.init();      // simulate browser call(1)
        applet.start();      // simulate browser call(2)
        frame.setVisible(true);
    }  
    
    // disable the default constructor
    private Debugger() { }
}
