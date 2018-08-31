import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.List;


public class BruteCollinearPoints {
    
   private LineSegment[] ls;
   private int lSCount; 
   private int length;
   private final Point[] points;

   private List<LineSegment> aLS;

   

   public BruteCollinearPoints(Point[] points){
       if (points == null){throw new java.lang.IllegalArgumentException();}
       for (int i = 0; i < points.length; i++){
            checkNull(points[i]);

        }

   
       this.points = points;
       this.length = points.length;
       this.lSCount = 0;
        this.aLS = new ArrayList<>();
       
       
       bruteForce();
       // TODO: also throw when any point of the array is null, or if the argument of the constructor contains a repeated point.
       
   }   

   public int numberOfSegments(){
       return lSCount;
   }        // the number of line segments
   
   public LineSegment[] segments(){

        LineSegment[] ls = new LineSegment[this.lSCount];
        int count = 0;
        for(LineSegment s : aLS){
            ls[count++] = s;
        }
       return ls;
   }                // the line segments

   private void checkNull(Point point){
       if (point == null) throw new IllegalArgumentException();
   }
   
   private boolean comparePoints(Point v, Point w){
       int res = v.compareTo(w);
       if (res == 0 && v != w){ throw new java.lang.IllegalArgumentException();} //in case of repeated point
       if (res < 0) return true;
       else return false;
   }
   
   public static void main(String[] args) {
        String fileName = args[0];
        // This will reference one line at a time
        String line = null;
        
        boolean firstSet = false;
        int first = 0;
        int second = 0;

        Point[] points;
        int count = 0;

        In in = new In(fileName);
        String inp = in.readAll();
        String[] xy = inp.split("\\s+");

        int nPoints = Integer.parseInt(xy[0]);

        points = new Point[nPoints];

        int x = 0;
        int y = 0;
        for(int i = 1; i < xy.length; i++) {
                        
            if(!firstSet){
            x = Integer.parseInt(xy[i]);
            firstSet = true;
            }else{
            y = Integer.parseInt(xy[i]);
            
            points[count++] = new Point(x,y);
            firstSet = false;
            }
        }

        in.close();        

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
    // print and draw the line segments
    //FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : bcp.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();

   
   }

/*
       try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        
        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int length = Integer.parseInt(bufferedReader.readLine());
        points = new Point[length];

        while((line = bufferedReader.readLine()) != null) {
            String[] output = line.split("\\s+");

            for (String o : output){
                StdOut.println(o);

                if(o != null && !o.isEmpty()){
                    int i = Integer.parseInt(o);

                    if (!firstSet){
                        first = i;
                        firstSet = true;
                    }else{
                        second = i;
                        firstSet = false;
    
                        points[count++] = new Point(first,second);
                        StdOut.println("point created" + first + ", " + second );
                    }
                }
            }
        }

        for (Point p : points){
            StdOut.println(p.toString());
        }
        BruteCollinearPoints bCP = new BruteCollinearPoints(points);
        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
        StdOut.println(
            "Unable to open file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        StdOut.println(
            "Error reading file '" 
            + fileName + "'");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }
    finally{
        StdOut.println("hmmm");
    }

   }
   */
   
   private void bruteForce(){
       for (int i = 0; i < this.length; i ++){
           
           //StdOut.println("-1");
           Point myPoint1 = points[i];
           
           for (int j = 0; j < this.length; j++){
             
               //StdOut.println("--2");
               Point myPoint2 = points[j];
               
               double slopeToValue = myPoint1.slopeTo(myPoint2);


               if (comparePoints(myPoint1, myPoint2)){ //&& myPoint1 != myPoint2
               for (int y = 0; y < this.length; y++){
                   
                    //StdOut.println("---3");
                   Point myPoint3 = points[y];
                   
                   if(slopeToValue == myPoint1.slopeTo(myPoint3) && comparePoints(myPoint2, myPoint3)) // && myPoint2 != myPoint3 && myPoint1 != myPoint3
                       {for (int z = 0; z < this.length; z++){
                       
                       //StdOut.println("----4");
                       Point myPoint4 = points[z];
                       
                       if(slopeToValue == myPoint1.slopeTo(myPoint4) && comparePoints(myPoint3, myPoint4)) { // && myPoint3 != myPoint 4 && myPoint1 != myPoint4 && myPoint2 != myPoint4
                           //StdOut.println("new segment: point1 : " + myPoint1.toString() +" point2 : "+ myPoint2.toString() +" point 3 : "+ myPoint3.toString() +" point 4 : " + myPoint4.toString());
                            this.aLS.add(new LineSegment(myPoint1, myPoint4));
                           //this.ls[this.lSCount] = new LineSegment(myPoint1, myPoint4); 
                           this.lSCount++;
                       }
                       }
                   }
                   }
               }
           }
       }

 

        /*
       StdOut.println("number of segments: " + this.lSCount);
       for (LineSegment s : this.ls){
           StdOut.println(s.toString());

       }
        */
   }
}