 
import java.util.*;
import static java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Color.*;
import java.applet.*;

import problem.*;
import jaima.search.strategies.*;
import jaima.util.*;

/**
 * Class PegSolver - solve triangular peg puzzles
 * 
 * @author William H. Hooper
 * @version 2008-09-13
 */
public class PegSolver 
extends Applet
implements ActionListener
{
    // GUI stuff
    TextField input;
    TextArea output;
    Button prev, next;
    Button loadStart, move, loadGoal, solve;
    Object lastClick;
    
    // Search stuff
    Problem problem;
    Strategy strategy;
    Stack<Node> solution;
    int scroll, maxScroll, minScroll;
    double elapsed;
    String lastSetup;
    
    public void init()
    {
        setBackground(white);
        
        prev = new Button("^");
        prev.addActionListener(this);
        add(prev);
        
        solve = new Button(" Solve ");
        add(solve);
        
        next = new Button("v");
        next.addActionListener(this);
        add(next);
        
        input = new TextField(
//          "? ?? ??? ???? ????? ?????? ???????"
            "                                  ");
        input.setFont(new Font("Monospaced", 0, 12));
        add(input);
        
        loadStart = new Button("Setup");
        loadStart.addActionListener(this);
        add(loadStart);
        
        move = new Button("Move");
        move.addActionListener(this);
        add(move);
        
        loadGoal = new Button("Goal");
        loadGoal.addActionListener(this);
        add(loadGoal);
        
        output = new TextArea("", 4, 30);
        output.setFont(new Font("Monospaced", 0, 12));
        output.setEditable(false);
        add(output);

        problem = new Problem();
        strategy = new RDFS();
//         System.out.println("init()");
        solution = new Stack<Node>();
    }
    
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
        
        setUnsolved();
        lastSetup = "o xx xxx xxxx xxxxx";
        input.setText(lastSetup);
    }
    
    public void append(TextArea a, String s)
    {
        String t = a.getText();
        a.setText(t + s);
    }
    
    public void appendLn(TextArea a, String s)
    {
        String t = a.getText();
        if(t.equals(""))
            a.setText(s);
        else
            a.setText(t + "\n" + s);
    }
    
    private void setUnsolved() 
    {
        solve.setLabel(" Solve ");
        solve.addActionListener(this);
        prev.setVisible(false);
        next.setVisible(false);
        output.setText(
//          "111111111122222222223333333333"
            "Peg Puzzle Solver \n" +
            "Click 'Solve' to solve the \n" +
            "current puzzle, or \n" +
            "enter a puzzle description \n" +
            "and click 'Start' or 'Goal' to \n" +
            "design your own."
        );
        solution = new Stack<Node>();
//         input.setText(problem.initial() + "");
    }
    
    private void setSolved() 
    {
        solve.setLabel("Solved!");
        solve.removeActionListener(this);
        prev.setVisible(true);
        next.setVisible(true);
        output.setText(
//          "111111111122222222223333333333"
            stats() +
            "Click 'v' and '^' to review \n" +
            "the solution. \n" +
            "Change the puzzle description \n" +
            "and click 'Start' or 'Goal' to \n" +
            "try a different puzzle."
        );
        scroll = 0;
        maxScroll = solution.size() - 1;
        minScroll = 0;
    }
    
    private String stats()
    {
        return 
            "Positions searched: " +
            jaima.util.Metrics.get("Node count") + 
             "\n" +
            "     Time, seconds: " + elapsed + "\n"
            ;
    }
    
    private void setFailed() 
    {
        solve.setLabel("Failed!");
        solve.removeActionListener(this);
        prev.setVisible(false);
        next.setVisible(false);
        output.setText(
//          "111111111122222222223333333333"
            stats() +
            "Change the puzzle description \n" +
            "and click 'Start' or 'Goal' to \n" +
            "try a different puzzle."
        );
    }
    
    /**
     * Convert 'a' to 0, 'b' to 1, ...
     */
    private int c2i(char c) {
        return c - 'a';
    }
    
    private char i2c(int i) {
        return (char) (i + 'a');
    }
    
    public void actionPerformed(ActionEvent e)
    {
        lastClick = e.getSource();
        String setup = input.getText();
        
        if(lastClick == solve) {
            solve.setLabel("...");
            repaint();
            jaima.util.Metrics.set("Node count", 0);
            long start = System.nanoTime();
            solution = strategy.search(problem);
            long finish = System.nanoTime();
            elapsed = (finish - start) / 1E9;
            if(solution.size() > 0) {
                setSolved();
            } else {
                setFailed();
            }
        }
        
        if(lastClick == loadStart) {
            problem.setBoard(setup);
            lastSetup = setup;
            setUnsolved();
        }
        
        if(lastClick == loadGoal) {
            problem.setGoal(setup);
            setUnsolved();
        }
        
        if(lastClick == prev) {
            scroll = max(scroll - 1, 0);
        }
        
        if(lastClick == next) {
            scroll = min(scroll + 1, maxScroll);
        }
        
        if(lastClick == move) {
            String letters = setup.replaceAll("\\W", "");
            if(letters.length() % 2 == 0) {
                problem.setBoard(lastSetup);
                State p = (State) problem.initial();
                for(int n = 0; n < letters.length(); n += 2) {
                    int i = c2i(letters.charAt(n));
                    int k = c2i(letters.charAt(n + 1));
//                     append(output, "," + numbers[n] + "," + numbers[n+1]);
                    if(p.legal(i, k)) {
                        p.jump(i,k);
//                         append(output, i + " " + k);
                    } else {
                        output.setText("Illegal jump from " + 
                            i2c(i) + " to " + i2c(k)); 
                        break;
                    }
                }
//                 input.setText(p + "");
                input.setText(setup);
            } else {
                output.setText(
                    "Cannot find even pairs of numbers in \n\"" +
                    setup + "\"");
            }
        }
        
        repaint();
        VirtualTimer.delay(1);
        repaint();
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {        
        int width = getWidth();
        int height = getHeight();
        int margin = output.getY() + output.getHeight() + 20;
        double pHeight = (height - margin) / 3;
        
//         int labelX = 300;
//         g.drawString(output.getX() + "", 100, labelX += 20);
//         g.drawString(output.getHeight() + "", 100, labelX += 20);
//         g.drawString(margin + "", 100, labelX += 20);
//         g.drawString(pHeight + "", 100, labelX += 20);
        
        if(
//             solution != null && 
                solution.size() > 0) {
            PegDisplay[] displays = new PegDisplay[3];
            for(int i = 0; i < 3; i++) {
                int index = maxScroll - scroll - i;
                if(index < 0 || index >= solution.size())
                    continue;
                double top = margin + i * pHeight;
                Node node = solution.get(index);
                State p = (State) node.getState();
                displays[i] = new PegDisplay(p);
                displays[i].setBounds(0, top, width, pHeight);
                displays[i].paint(g);
                g.drawString(index + "", 20, (int) top);
            }
            return;
        }
        
        if(problem == null)
            return;
     
//         g.drawString(problem.initial() + "", 100, 200);
//         g.drawString(problem.goal() + "", 100, 220);
        PegDisplay p = new PegDisplay((State) problem.initial());
        p.setBounds(0, margin, width, pHeight);
        p.paint(g);
        
        if (problem.goal() != null) {
            PegDisplay q = new PegDisplay((State) problem.goal());
            q.setBounds(0, 
                margin + pHeight * 2, 
                width, pHeight);
            q.paint(g);
        }
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }
}
