package vacuum;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import vacuum.VPercept.Location; 
import vacuum.VPercept.Condition; 

/**
 * A House with an associated Vacuum,
 * and all the other simulation paraphernalia
 * 
 * @author William H. Hooper 
 * @version 2007-01-11
 */
public class Scenario
extends jaima.agent.Scenario
{
    private Location[] locations;
    private Map<Location, Condition> conditions;
    private Vacuum roomba;
    private House house;
    private SmartVac brain;
    private boolean isAlive;
    private double dirtyA, dirtyB;
    public enum DLevel { none, run, quit }
    private DLevel debugging;
    
    public Scenario()
    {
    	super();
        locations = Location.values();
        conditions = new HashMap<Location, Condition>();
        for(Location l : locations) {
            conditions.put(l, Condition.clean); 
        }
        roomba = new Vacuum();
        roomba .location = Location.A;
        house = new House(conditions, roomba);
        jaima.agent.Sensor[] s = { new VSensor() };
        jaima.agent.Actuator[] a = { new VActuator() };
        brain = new SmartVac(house, s, a);
        agents.add(brain);
        dirtyA = dirtyB = 0.2;
        debugging = DLevel.none;
    }
    
    public void run()
    {
        super.run();
        if(debugging == DLevel.run) {
            jaima.util.Log.record("Scenario running...");
            jaima.util.Metrics.set("dirtyA", dirtyA);
            jaima.util.Metrics.set("dirtyB", dirtyB);
        }
        Random gen = new Random();
        isAlive = true;
        while(isAlive) {
            jaima.util.VirtualTimer.delay(1 + gen.nextDouble() * 1);
            for (Location l : locations) {
                Condition condition = conditions.get(l);
                jaima.util.Metrics.increment("" + condition);
            }
            if(gen.nextDouble() < dirtyA) {
                conditions.put(Location.A, Condition.dirty);
            }
            if(gen.nextDouble() < dirtyB) {
                conditions.put(Location.B, Condition.dirty);
            }
        }
    }
    
    public void quit()
    {
        isAlive = false;
        while(this.isAlive()) {
            Thread.yield();
        }
        super.quit();
        if(debugging == DLevel.quit) {
            System.err.println(jaima.util.Metrics.getAll());
        }
    }
    
    public Map<Location, Condition> getConditions()
    {
        return conditions;
    }
    
    public Vacuum getVacuum()
    {
        return roomba;
    }
    
    public SmartVac getBrain()
    {
        return brain;
    }
    
    public void setTraffic(double a, double b)
    {
        dirtyA = a;
        dirtyB = b;
    }
    
    public void setDLevel(DLevel d)
    {
        debugging = d;
    }
    
    public static double vacuumPerformance()
    {        
        int clean = jaima.util.Metrics.getInt("clean");
        int dirty = jaima.util.Metrics.getInt("dirty");
        int left = jaima.util.Metrics.getInt("left");
        int right = jaima.util.Metrics.getInt("right");
        int show = jaima.util.Metrics.getInt("show");
        int suck = jaima.util.Metrics.getInt("suck");
        return (double) (
            15 * clean
            - 7 * suck
            - 3 * (left + right) 
            - show
            ) / (clean + dirty);
    }
}
