import vacuum.*;

/**
 * houseApp - an environment to test the vacuum package.
 * 
 * @author William H. Hooper 
 * @version 2007-01-11
 */
public class houseApp
{
    public static void main(String argv[])
    {
        double sum = 0;
        int count = 0;
        double[] frequency = { 0.1, 0.2, 0.4, 0.8 };
        int verbose = checkargs(argv, "v");
        
        for (double a : frequency)
            for (double b : frequency)
            {
                runOnce(a, b, 500, verbose);
                sum += vacuum.Scenario.vacuumPerformance();
                count++;
            }
        System.out.println("Average performance: " 
            + ("" + (sum / count)));
    }
    
    private static int checkargs(String argv[], String s)
    {
        int count = 0;
        String search = "-" + s;
        for(String a : argv) {
            while(a.contains(search)) {
                count++;
                search += s;
            }
        }
        return count;
    }
    
    private static void runOnce(double a, double b, int s, int verbose)
    {
        jaima.util.VirtualTimer.startTimer();
        jaima.util.VirtualTimer.setScale(1);
        jaima.util.Metrics.clearAll();
        
        Scenario scenario = new Scenario();
        scenario .setTraffic(a, b);
        
        scenario .start();
        jaima.util.VirtualTimer.delay(s);
        scenario .quit();
        if(verbose == 1) {
            System.out.println(
                "Performance: " + vacuum.Scenario.vacuumPerformance());
        }
        if(verbose >= 2) {
            System.out.println(jaima.util.Metrics.getAll()
            + "Performance: " + vacuum.Scenario.vacuumPerformance());
            System.out.println();
        }
    }
    
    public static String vacuumStats()
    {
        String summary = "";
        for (String key : jaima.util.Metrics.keySet())
        {
            summary += key + " " + jaima.util.Metrics.get(key) + "\n";
        }
        summary += ("Performance " + vacuum.Scenario.vacuumPerformance()  
            + "           ").substring(0, 20) + "\n";
        summary += "Elapsed time " + jaima.util.VirtualTimer.elapsed();
        return summary;
    }
}
