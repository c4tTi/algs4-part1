import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
//import java.util.ArrayList;
//import java.util.List;


public class FastCollinearPoints {

   private final Point[] points;

   public FastCollinearPoints(Point[] points){
        if (points == null){throw new java.lang.IllegalArgumentException();}
            for (int i = 0; i < points.length; i++){
                    checkNull(points[i]);
                }
                //how to use natural sort?
            StdOut.println("notsorted:");
            for(Point p : points){
                StdOut.println(p);
            }
            

            Arrays.sort(points);
            

            StdOut.println("natural sort:");
            for(Point p : points){
                StdOut.println(p);
            }


            StdOut.println("slope order sort");
            Point myPoint = points[0];
            Arrays.sort(points, myPoint.slopeOrder());
            for(Point p : points){
                StdOut.println(myPoint.slopeTo(p));
                StdOut.println(p);
            }
            //natural sort first to check for same points twice.
            //how to use it again?         
            this.points = points;
            
   }     // finds all line segments containing 4 or more points
   
   public int numberOfSegments(){
       return 0;
   }// the number of line segments
   
   public LineSegment[] segments(){
       return null;
   }// the line segments

//bottleneck operation is sorting.
   private void sortBySlope(){
       //for each point p
       //p.slopeOrder(); // returns comparator<point>    
   }

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