import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

import jaima.util.*;

import vacuum.*;
import vacuum.VPercept.Location;
import vacuum.VPercept.Condition;
import static vacuum.VPercept.Location.*;
import static vacuum.VPercept.Condition.*;

/**
 * Vacuum Applet - displays the house environment and the vacuum
 * 
 * @author William H. Hooper 
 * @version 2007-01-11
 */
public class VacuumApplet 
extends Applet
implements ActionListener
{
    private static final long serialVersionUID = -8711051627841089827L;
    private Button start;
    private Button stop;
//    private Button reset;
    private TextField leftProbability;
    private TextField rightProbability;
    private Painter painter;
    private Image image;
    
    private Scenario scenario;
    private Vacuum vacuum;
    private Map<Location, Condition> conditions;
    
    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {
        start = new Button("Start");
        add(start);
        start.addActionListener(this);
        
//        reset = new Button("Reset");
//        add(reset);
//        reset.addActionListener(this);
        
        add(new Label("Left:"));
        
        leftProbability = new TextField("100");
        leftProbability.addActionListener(this);
        leftProbability.setText("30");
        add(leftProbability);
        
        add(new Label("%  "));
        
        add(new Label("Right:"));
        
        rightProbability = new TextField("100");
        rightProbability.addActionListener(this);
        rightProbability.setText("30");
        add(rightProbability);
        
        add(new Label("%"));
        
        stop = new Button("Stop");
        add(stop);
        stop.addActionListener(this);
        
        image = getImage(getCodeBase(), "CoolClips_hous0657.gif");
        setup();
    }
    
    private void setup()
    {
        jaima.util.VirtualTimer .startTimer();
        jaima.util.Log.set(System.out);
        jaima.util.Metrics.clearAll();
        scenario = new Scenario();
        conditions = scenario.getConditions();
        vacuum = scenario.getVacuum();
        painter = new Painter(this);
    }
    
    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
    	if(getPreferredSize() != null) {
    		setSize(new Dimension(500, 400));
    	}
        setBackground(white);
    }
    
    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
        scenario.quit();
        painter.quit();
    }
    
    /**
     * Executed whenever a button is clicked.
     *
     * @param e the event (i.e., button click) that triggers the action
     */
     public void actionPerformed(ActionEvent e)
    {
        Object clicked = e.getSource();
        
        if (clicked == start)
        {
        	if(painter.getState() == Thread.State.TERMINATED) {
        		setup();
        	}
        	if(painter.getState() != Thread.State.NEW) {
        		return;
        	}
//             scenario. setDLevel(run);
            double left = new Double (leftProbability.getText()) / 100.0;
            double right = new Double (rightProbability.getText()) / 100.0;
            scenario.setTraffic(left, right);
            scenario.start();
            painter.start();
        }
        
        if (clicked == stop)
        {
            scenario.quit();
            painter.quit();
        }
        
//        if (clicked == reset)
//        {
//            scenario.quit();
//            painter.quit();
//            setup();
//        }
        
        repaint();
    }
    
    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
    	// start layout
        int y = 50;
        
        // fill in dirt first
        g.setColor(darkGray);
        
        Condition a = conditions.get(A);
        if(a == dirty)
        {
            g.fillRect(100, y, 100, 100);
        }
        
        Condition b = conditions.get(B);
        if(b == dirty)
        {
            g.fillRect(300, y, 100, 100);
        }
        
        // draw room outlines
        g.setColor(black);
        g.drawRect(100, y, 100, 100);
        g.drawRect(300, y, 100, 100);
        
        // draw vacuum
        if (vacuum.location == A)
        {
            g.drawImage(image, 110, y+10, 80, 80, this);
        }
        
        // draw vacuum
        if (vacuum.location == B)
        {
            g.drawImage(image, 310, y+10, 80, 80, this);
        }
        
        y += 130;
        
        // display stats
        g.setColor(black);
        for (String line : Metrics.getAll().split("\n"))
        {
            g.drawString(line, 50, y);
            y += 18;
        }
        
        g.drawString("Performance: " 
            + Scenario. vacuumPerformance(),
            50, y);
        y += 36;
        
        g.drawString(
        	" Image from: http://dir.coolclips.com/Household/Cleaning_Materials"
            , 0, y);
        y += 20;
        
        g.drawString(
        	"      /Vacuum_Cleaners/Vacuums_hous0657.html"
            , 0, y);
    }
}
