import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args){
        int k;
        if (args.length > 0){
        k = Integer.parseInt(args[0]);
        }
        else {
        
        StdOut.println("no Input!");
        throw new java.lang.UnsupportedOperationException();
        }
        
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();
        
        for (int i = 0; i < k; i ++){
            rQ.enqueue(StdIn.readString());
        }
        
        while(!rQ.isEmpty()){
        StdOut.println(rQ.dequeue());
        }    
    }
}