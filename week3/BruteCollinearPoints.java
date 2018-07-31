public class BruteCollinearPoints {
    
   private LineSegment[] ls;
   private int lSCount; 
   private int length;
   private Point[] points;
   
   public BruteCollinearPoints(Point[] points){
       if (points == null){throw new java.lang.IllegalArgumentException();}
   
       this.points = points;
       this.length = points.length;
       this.lSCount = 0;
       this.ls = new LineSegment[this.length];
       // TODO: also throw when any point of the array is null, or if the argument of the constructor contains a repeated point.
       
   }    // finds all line segments containing 4 points
   
   public           int numberOfSegments(){
       return lSCount;
   }        // the number of line segments
   public LineSegment[] segments(){
       return this.ls;
   }                // the line segments


   // also check that they are far away from eachother?
   private void bruteForce(){
       for (int i = 0; i < this.length; i ++){
           if (points[i] == null) throw new IllegalArgumentException();
           Point myPoint = points[i];
           for (int j = i+1; j < this.length; j++){
               if (points[j] == null) throw new IllegalArgumentException();
               double slopeToValue = myPoint.slopeTo(points[j]);
               
               for (int y = j+1; y < this.length; y++){
               if (points[y] == null) throw new IllegalArgumentException();
                   if(slopeToValue == myPoint.slopeTo(points[y])) for (int z = y+1; z < this.length; z++){
                       if (points[z] == null) throw new IllegalArgumentException();
                       if(slopeToValue == myPoint.slopeTo(points[z])) {
                           this.ls[this.lSCount++] = new LineSegment(points[i], points[y]); 
                       
                       }
                   }
               }
           }
       }
   }
}