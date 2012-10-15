import java.awt.*;
import static java.awt.Color.*;
import problem.*;
import static java.lang.Math.*;

/**
 * Write a description of class PegDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PegDisplay
{
    private double x, y, width, height;
    State pegs;
    Color pegColor = red;
    
    /**
     * Constructor for objects of class PegDisplay
     */
    public PegDisplay(State p)
    {
        pegs = p;
        x = 100;
        y = 150;
        width = 250;
        height = 200;
    }
    
    public void setBounds(double x, double y, double w, double h)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }
    
    public void paint(Graphics g)
    {
        double vSize = min(height, width * sqrt(3) / 2);
        double hSize = height * 2 / sqrt(3);
        double vSpacing = vSize / (pegs.nRows() + 1);
        double hSpacing = vSpacing * 2 / sqrt(3);
        double pegSize = vSpacing * 0.7;
        double holeSize = pegSize * 0.6;
        double centerAdj = (pegSize - holeSize) / 2;
        
        for (int i = 0; i < pegs.nHoles(); i++) {
            double x = this.x + hSize / 2 + hSpacing * 
                (State.col(i) - State.row(i) / 2.0);
            double y = this.y + vSpacing * State.row(i);
            if(pegs.pegAt(i)) {
                g.setColor(pegColor);
                g.fillOval(
                    (int) x, (int) y,
                    (int) pegSize, (int) pegSize );
            } else {
                g.setColor(black);
                g.drawOval(
                    (int) (x + centerAdj),
                    (int) (y + centerAdj),
                    (int) holeSize, (int) holeSize );
            }
        }
    }
}
