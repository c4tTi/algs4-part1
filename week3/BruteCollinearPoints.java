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

   private void checkNull(Point point){
       if (points == null) throw new IllegalArgumentException();
   }
   
   
   // also check that they are far away from eachother?
   private void bruteForce(){
       for (int i = 0; i < this.length; i ++){
           checkNull(points[i]);
           
           Point myPoint1 = points[i];
           
           for (int j = 0; j < this.length; j++){
               checkNull(points[j]);
               
               Point myPoint2 = points[j];
               double slopeToValue = myPoint1.slopeTo(myPoint2);
               
               if (myPoint1.compareTo( myPoint2)<0){
               for (int y = 0; y < this.length; y++){
                   checkNull(points[y]);
                   
                   Point myPoint3 = points[y];
                   
                   if(slopeToValue == myPoint1.slopeTo(myPoint3) && myPoint2.compareTo( myPoint3)<0)
                       {for (int z = 0; z < this.length; z++){
                       
                       checkNull(points[z]);
                       
                       Point myPoint4 = points[z];
                       
                       if(slopeToValue == myPoint1.slopeTo(myPoint4) && myPoint3.compareTo(myPoint4) < 0) {
                           this.ls[this.lSCount++] = new LineSegment(myPoint1, myPoint4); 
                       }
                       }
                   }
                   }
               }
           }
       }
   }
}