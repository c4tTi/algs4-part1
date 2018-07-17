import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;


//needs to use an array to fulfill performance requirements
//resizing arrays - 
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    //size - number of items inserted in array.
    private int size;
    private int dequePointer;
    //capacity of the array : capacity > size.
    private int capacity;
    private Item[] myArrayHolder;
    
    private boolean shuffled;
    private boolean sampled;
    
    //create a generic array in constructor.
    
    public RandomizedQueue(){
        shuffled = false;
        size = 0;
        dequePointer = 0;
        capacity = 1;
        myArrayHolder = (Item[]) new Object[capacity];
        this.sampled = false;
    }                 // construct an empty randomized queue
    
    public boolean isEmpty(){return (this.size==this.dequePointer);}                 // is the randomized queue empty?
    public int size(){return this.size - this.dequePointer;}// return the number of items on the randomized queue
    
    public void enqueue(Item item)
    {
        if (item == null){throw new java.lang.IllegalArgumentException();}

        if (size == capacity && (size - dequePointer) > capacity/2) 
        {
            this.increaseCapacity();
        }
        else if (size == capacity)
        {
            this.placeArrayToFront((Item[]) new Object[capacity]);
        }
        this.myArrayHolder[size++] = item;
        this.shuffled = false;
    
    }           // add the item

    private void shuffle(){
        StdRandom.shuffle(myArrayHolder, dequePointer, size);
        this.shuffled = true;
        this.sampled = false;
    }
    
    private void increaseCapacity()
    {
             //StdOut.println("increaseCapacity() - called");   
             this.capacity *= 2;
             Item[] newArrayHolder = (Item[]) new Object[this.capacity];
             placeArrayToFront(newArrayHolder);
    }

        //placing the array back to the front
    private void decreaseCapacity(){
        //StdOut.println("decreaseCapacity() - called");
        this.capacity /= 2;
        Item[] newArrayHolder = (Item[]) new Object[this.capacity];
        placeArrayToFront(newArrayHolder);
    }
    
    
    private void placeArrayToFront(Item[] newArray){
        
        //StdOut.println("placeArrayToFront() - called");
        int count = 0;
        
        for(int i = this.dequePointer; i < this.size; i++){
            newArray[count++] = this.myArrayHolder[i];
        }
        this.myArrayHolder = newArray;
        this.size = this.size - this.dequePointer;
        this.dequePointer = 0;
        
    }

    private void printArray(){
        for (int i = 0; i < this.myArrayHolder.length; i ++){
            StdOut.println(this.myArrayHolder[i]);
        }
    }
    
    
    public Item dequeue()
    {
        if (isEmpty()){throw new java.util.NoSuchElementException();}
        //deque from front!? so you need to copy the array always back to the front?
        
        if ((this.size - this.dequePointer) < (capacity/4)){decreaseCapacity();}
        
        if (!this.shuffled){shuffle();}
        
        Item itemReturn = myArrayHolder[this.dequePointer];
        myArrayHolder[this.dequePointer++] = null;
        
        return itemReturn;
    }                    // remove and return a random item





    public Item sample()
    {
        
        if (isEmpty()){throw new java.util.NoSuchElementException();}
        
        if (shuffled && !sampled) 
        {
            this.sampled = true;
            return myArrayHolder[this.dequePointer];
        }
        
        else{
            return myArrayHolder[StdRandom.uniform(this.size - this.dequePointer) + this.dequePointer];
        }
    }                     // return a random item (but do not remove it)
    
    public Iterator<Item> iterator(){return new RandomizedQueueIterator();}         // return an independent iterator over items in random order
    
    private class RandomizedQueueIterator implements Iterator<Item>{
        //has to shuffle one more time.
        
        private int lo = dequePointer;
        private int hi = size;
        private Item[] array = (Item[]) new Object[this.hi - this.lo];
        private int count = 0;
        
        RandomizedQueueIterator(){
            for (int i = this.lo; i < this.hi; i ++){
                 array[this.count++] = myArrayHolder[i];
            }
            StdRandom.shuffle(array);
            
            this.lo = 0; 
            this.hi = count;
        }
        
        public boolean hasNext(){return this.lo != this.hi;}
        public void remove(){throw new java.lang.UnsupportedOperationException();}
        public Item next(){
            if(!hasNext()){throw new java.util.NoSuchElementException();}
            return array[this.lo++];
            
        }
        
    }
    
    public static void main(String[] args){
    StdOut.println("main ----");
    
    RandomizedQueue<String> rq2 = new RandomizedQueue<>();
    StdOut.println(rq2.isEmpty());
    //rq2.dequeue();
    rq2.enqueue("hello");
    rq2.printArray();
    StdOut.println(rq2.size);
    StdOut.println(rq2.dequePointer);
    StdOut.println(rq2.isEmpty());
    StdOut.println(rq2.dequeue());
    StdOut.println(rq2.isEmpty());
    rq2.printArray();
    
    rq2.enqueue("B");
    rq2.enqueue("A");
    rq2.enqueue("9");
        rq2.enqueue("1");
    rq2.enqueue("2");
    rq2.enqueue("3");
    
        rq2.enqueue("4");
    rq2.enqueue("5");
    rq2.enqueue("6");
    
        rq2.enqueue("8");
    rq2.enqueue("7");
    rq2.enqueue("10");
    Iterator myIt = rq2.iterator();
    Iterator myIt2 = rq2.iterator();
    
    while (myIt.hasNext()){
        StdOut.println(myIt.next() + "hihihi");
        while(myIt2.hasNext()){
            StdOut.println(myIt2.next() +" ---- -");
        }
    }
    
    
    /*
        for (String i : rq2){
            StdOut.println(i +" ahhh");
            for (String z : rq2){
            StdOut.println(z);
            }
        }
    */
    
    /*
    RandomizedQueue<String> rq = new RandomizedQueue<>();
    rq.printArray();
    rq.enqueue("hello");
    rq.printArray();
    rq.enqueue("how");
    rq.enqueue("are");
    rq.enqueue("you");
    rq.enqueue("today");
    rq.enqueue("?");
    rq.printArray();
    rq.enqueue("thank you");
    rq.enqueue("I'm");
    rq.enqueue("great");
    rq.printArray();
    
    
    for (int i = 0; i < 10; i++){
    StdOut.println(rq.sample());
    }
    
    StdOut.println(".-.....-----");
    
    for (int i = 0; i < 20; i++){
    StdOut.println(rq.isEmpty());
    StdOut.println("what=");
    StdOut.println(rq.dequeue());
    StdOut.println(rq.size);
    StdOut.println(rq.isEmpty());
    }
    rq.printArray();
    */
    
    
    
    }
}