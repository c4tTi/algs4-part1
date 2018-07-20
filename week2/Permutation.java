import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args){
        int k;
        String fileName;
        if (args.length == 1){
        k = Integer.parseInt(args[0]);
        }
        else if (args.length > 1){
        fileName = args[0];
        k = Integer.parseInt(args[0]);
        }
        else {
        
        StdOut.println("no Input!");
        throw new java.lang.UnsupportedOperationException();
        }
        
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();
        
        while(!StdIn.isEmpty())
        {
            rQ.enqueue(StdIn.readString());
        }
        
        for (int i = 0; i < k; i ++){
            StdOut.println(rQ.dequeue());
        }
        
        
        /*
        while(!rQ.isEmpty()){
        StdOut.println(rQ.dequeue());
        } 
        */
    }
}