import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.ArrayList;
import java.util.List;


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
/*
        List<Integer> mL = new ArrayList<Integer>();
        for (int i = 0; i < 7; i ++){
        mL.add(1);
    }
        mL.add(4, 40);
        mL.add(5, 50);
        mL.add(3, 30);

        mL.add(23);

        for (Integer i: mL){
            System.out.println(i);
        }

        System.out.println("end");
*/

       bruteForce();
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
   
   
   private boolean comparePoints(Point v, Point w){
       int res = v.compareTo(w);
       if (res == 0 && v != w){ throw new java.lang.IllegalArgumentException();} //in case of repeated point
       if (res < 0) return true;
       else return false;
   
   }


   /*
   public static void main(String[] args) {
       String fileName = args[0];
    
        // This will reference one line at a time
        String line = null;
        
        boolean firstSet = false;
        int first = 0;
        int second = 0;

        Point[] points;
        int count = 0;
        

       try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);

        
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
   
   // also check that they are far away from eachother?
   private void bruteForce(){
        for (int i = 0; i < this.length; i++){
            checkNull(points[i]);

        }


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
                           //StdOut.println("new segment");
                           this.ls[this.lSCount] = new LineSegment(myPoint1, myPoint4); 
                           this.lSCount++;
                       }
                       }
                   }
                   }
               }
           }
       }
   }
}