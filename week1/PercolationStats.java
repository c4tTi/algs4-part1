import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    
    private final int index;
    private final int trials;
    private final double[] openSitesCount;
    private static final double CONF196 = 1.96;
    private double meanValue;
    private double stddevValue;
    private boolean meanBool;
    private boolean stddevBool;
    
    public PercolationStats(int n, int trials){
    if (n <= 0) throw new IllegalArgumentException("percolation table index can not be < 1");
    if (trials <= 0) throw new IllegalArgumentException("trials can not be < 1");
    
    this.index = n;
    this.trials = trials;
    this.openSitesCount = new double[this.trials];
    
    for (int i = 0; i < this.trials; i ++){
        Percolation p = new Percolation(this.index);
        this.openSitesCount[i] = startPercolationExperiment(p) / (double) (this.index*this.index);
        }
    //printOpenSites();
    }    // perform trials independent experiments on an n-by-n grid
    
    
    
    //Helper function
    /*
    private void printOpenSites(){
        StdOut.println("print ...");
        for (int i = 0; i < this.openSitesCount.length; i ++){
            StdOut.println(this.openSitesCount[i]);
        }
    }
    */
    
    private double startPercolationExperiment(Percolation p){
        while(!p.percolates()){
        p.open(StdRandom.uniform(this.index) +1, StdRandom.uniform(this.index)+1);
        }
        //StdOut.println(p.numberOfOpenSites());
        return (double) p.numberOfOpenSites();
    }
    
    public double mean(){
        this.meanValue =StdStats.mean(this.openSitesCount); 
        this.meanBool = true;
        return meanValue;
    }                          // sample mean of percolation threshold
    
    public double stddev(){
        this.stddevValue = StdStats.stddev(this.openSitesCount);
        this.stddevBool = true;
        return this.stddevValue; 
    }                        // sample standard deviation of percolation threshold
    
    public double confidenceLo(){
        if (!stddevBool) stddev();
        if (!meanBool) mean();
        return this.meanValue - CONF196 * stddevValue/ Math.sqrt((double) this.trials);
    }                  // low  endpoint of 95% confidence interval
    
    public double confidenceHi(){
        if (!stddevBool) stddev();
        if (!meanBool) mean();
        return this.meanValue + CONF196 * stddevValue/Math.sqrt((double) this.trials);
    }                  // high endpoint of 95% confidence interval

    public static void main(String[] args){
          if (args.length < 2) {
         System.out.println("enter percolation table index and number of trials when running this class");
      } else {
         PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));     
         ps.print();
      }
    }        // test client (described below)
    
    private void print(){
        StdOut.println("mean : " + mean());
        StdOut.println("stddev : " + stddev());
        StdOut.println("95 % confidence interval : [" + confidenceLo() + " , " +confidenceHi()+ "]");  
    }
    
}