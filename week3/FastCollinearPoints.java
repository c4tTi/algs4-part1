import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;


public class FastCollinearPoints {

   private final Point[] points;
   private final int lSNumber;
   private boolean addTosLS;

   private ArrayList<LineSegment> sLS;

   private LineSegment[] lS; 

   public FastCollinearPoints(Point[] points){
        if (points == null){throw new java.lang.IllegalArgumentException();}
            for (int i = 0; i < points.length; i++){
                    checkNull(points[i]);
                }

            Arrays.sort(points);
            for (int i = 1; i < points.length; i++){
                if (points[i-1].compareTo(points[i]) == 0){throw new java.lang.IllegalArgumentException();}
            }

            sLS = new ArrayList<LineSegment>();

            //Natural Sort first! - then sort with slopeOrder or integrate the two within eachother?
            for (int i = 0; i < points.length; i++){

                //StdOut.println("-----");
                Arrays.sort(points);
                Point myPoint = points[i];
                Arrays.sort(points, myPoint.slopeOrder());
                    //StdOut.println("-----------");
                double mySlope = Double.MAX_VALUE;
                int count = 0; 
                for (int z= 1; z< points.length; z++){
                    
                    //StdOut.println(points[z]);
                   if (myPoint.compareTo(points[z]) == -1){
                        double tempSlope = myPoint.slopeTo(points[z]);
                        if (mySlope == tempSlope){
                            //StdOut.println("myslope: " + mySlope);
                            //StdOut.println("tempsloope: " + tempSlope);
                            count ++;
                            //StdOut.println(count + " " + myPoint);
                            if (count == 2){
                                addTosLS = true;
                                //StdOut.println(points[0].toString() + points[z-2].toString() + points[z-1].toString() + points[z].toString());
                                //sLS.add(new LineSegment(points[0],points[z]));
                                //StdOut.println("ls added");
                                count = 0;
                            }
                        }else {
                            if(addTosLS){
                                addTosLS = false;
                                sLS.add(new LineSegment(points[0], points[z-1]));

                            }
                            mySlope = tempSlope;
                            count = 0;
                        }
                    }
                    
                }    
                if(addTosLS){
                    addTosLS = false;
                    sLS.add(new LineSegment(points[0], points[points.length-1]));
                }
                
            }

            this.lSNumber = this.sLS.size();
            this.lS = new LineSegment[this.lSNumber];
            int index = 0;
            for (LineSegment ls : this.sLS){
                this.lS[index++] = ls;
                //StdOut.println(ls.toString());
            }
            this.points = points;
   }     // finds all line segments containing 4 or more points
   
   public int numberOfSegments(){
       return this.lSNumber;
   }// the number of line segments
   
   public LineSegment[] segments(){
       return this.lS;
   }// the line segments

   private void checkNull(Point point){
       if (point == null) throw new IllegalArgumentException();
   }


public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        StdOut.println("draw point");
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    
}


}