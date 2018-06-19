//about 70 lines of code.
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
 
public class Percolation {
 
    private int openSitesCount;
    private final int index;
    //site that is needed to connect all upper sites to
    private final int virtualSiteUp;
    //site that is needed to connect all lower sites to
    private final int virtualSiteDown;
    
    private boolean[][] percolationTable;
    // has to be changed.
    private final WeightedQuickUnionUF wQUUF;
    
    
    //Constructor - initialize all sites to be blocked.
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException("percolation table index can not be < 1");
        
        //+2 for the initial open sites
        this.wQUUF = new WeightedQuickUnionUF (n*n+2);
        
        //StdOut.println(wQUUF.count());
        this.virtualSiteUp = n*n+1-1;
        this.virtualSiteDown = n*n+2-1;
        this.percolationTable = new boolean[n][n];
        this.index = n;
        this.openSitesCount = 0;
        
    }              // create n-by-n grid, with all sites blocked

    
    private void indexOutOfBoundsCheck(int row, int col){
        if (row <= 0 || row > this.index) throw new IllegalArgumentException("row index out of bounds");
        if (col <= 0 || col > this.index) throw new IllegalArgumentException("col index out of bounds");
    }
    
    public void open(int row, int col){
        //StdOut.println("trying to open : " + row + ", " + col);
        indexOutOfBoundsCheck(row,col);
        
        //Open the site:
        if (!isOpen(row, col)){
            
            int currentPosition = calcPosition(row, col);
            
            int rTable = row - 1;
            int cTable = col - 1;
            
            percolationTable[rTable][cTable] = true;
            
            //int currentPosition = calcPosition(rTable, cTable);
            //union has to be called!
            //check if field up is Open
            if (row == 1){
                wQUUF.union(currentPosition, virtualSiteUp);
            }
            else if(isOpen(row-1, col))
            {
                wQUUF.union(currentPosition, calcPosition(row-1, col));
            }
           
            //check if field down is OPen     
            if (row == this.index){
                
                wQUUF.union(currentPosition, virtualSiteDown);
            }
            else if (isOpen(row+1, col)){
                wQUUF.union(currentPosition, calcPosition(row+1, col));
            }    
                 
            //check if field right is Open
            if (col != this.index && isOpen(row, col+1)){
                wQUUF.union(currentPosition, calcPosition(row, col+1));
            }
            
            //check if field left is Open
            if (col != 1 && isOpen(row,col-1)){
                wQUUF.union(currentPosition, calcPosition(row, col-1));
            } 
            //StdOut.println("opened : " + row + ", " + col);
            increaseOpenSitesCount();
        }
        
    }    // open site (row, col) if it is not open already
    
    private int calcPosition(int row, int col){
         return (row-1) + ((col-1) * this.index);
    }
    
    public boolean isOpen(int row, int col){
        indexOutOfBoundsCheck(row,col);
        return this.percolationTable[row -1][col-1];
    }  // is site (row, col) open?
    
    
    public boolean isFull(int row, int col){
        indexOutOfBoundsCheck(row,col);
        return this.wQUUF.connected(calcPosition(row, col), virtualSiteUp);
    }  // is site (row, col) full?
    
    
    private void increaseOpenSitesCount(){
        this.openSitesCount++;
    }
    
    public int numberOfOpenSites(){
        return this.openSitesCount;
    }       // number of open sites
    
    
    public boolean percolates(){
        return this.wQUUF.connected(virtualSiteDown, virtualSiteUp);
    }              // does the system percolate?
        
    
 public static void main(String[] args) {
        
        StdOut.println("Hello, World!");
        int here = StdIn.readInt();
        StdOut.println("readed : " + here);
        Percolation p = new Percolation(here);
        
        
        StdOut.println("opening 1, 6");
        
        p.open(1,6);
        
        
    }

 
 //throwing errors:
// if (i <= 0 || i > n) throw new IndexOutOfBoundsException("row index i out of bounds");
 
}
  
