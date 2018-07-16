import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


//needs to use an array to fulfill performance requirements
//resizing arrays - 
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    //size - number of items inserted in array.
    private int size;
    private Node first;
    //capacity of the array : capacity > size.
    private int capacity;
    private Item[] myArrayHolder;
    
    //create a generic array in constructor.
    
    public RandomizedQueue(){
        size = 0;
        capacity = 1;
        myArrayHolder = (Item[]) new Object[capacity];
    }                 // construct an empty randomized queue
    
    public boolean isEmpty(){return (size== 0);}                 // is the randomized queue empty?
    public int size(){return size;}// return the number of items on the randomized queue
    
    public void enqueue(Item item)
    {
        if (item == null){throw new java.lang.IllegalArgumentException();}
    }           // add the item
    
    public Item dequeue()
    {
        if (isEmpty()){throw new java.util.NoSuchElementException();}
        return null;
    }                    // remove and return a random item
    
    public Item sample()
    {
        if (isEmpty()){throw new java.util.NoSuchElementException();}
        return null;
    }                     // return a random item (but do not remove it)
    
    public Iterator<Item> iterator(){return new RandomizedQueueIterator();}         // return an independent iterator over items in random order
    
    private class RandomizedQueueIterator implements Iterator<Item>{
        private Node current = first;
        
        public boolean hasNext(){return true;}
        public void remove(){throw new java.lang.UnsupportedOperationException();}
        public Item next(){
            if(hasNext()){throw new java.util.NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
        
    }
    
    public static void main(String[] args){
    StdOut.println("main ----");
    }   // unit testing (optional)
}