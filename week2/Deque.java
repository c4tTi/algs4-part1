import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

// Implementation of a double - ended queue or dequeue!
public class Deque<Item> implements Iterable<Item> { 
    private int size;
    private Node first;
    private Node last;
    
    public Deque()
    {
        this.size = 0;
        first = new Node();
        first.item = null;
        first.before = null; //will always be null
        first.next = null;
        
        last = new Node();
        last.item = null;
        last.next = null; //will always be null
        last.before = null;
    }                           // construct an empty deque

    public boolean isEmpty(){return (size == 0);}                 // is the deque empty?   

    public int size(){return size;}      // return the number of items on the deque
    
    private void nullcheck(Item item){
        if (item == null){throw new java.lang.IllegalArgumentException();}
    }
    
    private void addFirstItem(Item item){
        first.item = item;
        last.item = item;
        first.next = null;
        last.before = null;
    }
    
    private Node deepCopyNode(Node node){
        Node copyNode = new Node();
        copyNode.item = node.item;
        copyNode.next = node.next;
        copyNode.before = node.before;
        return copyNode;
    }
    
    public void addFirst(Item item)
    {
        nullcheck(item);
        size++;
        if (size == 1){
            addFirstItem(item);            
        }
        else if (size == 2){
            first.item = item;
            //setting up next and before
            first.next = last;
            last.before = first;
        }
        else{
            Node oldNode = deepCopyNode(first);
            oldNode.next.before = oldNode;
            first.item = item;
            first.next = oldNode;
            oldNode.before = first;
        }    
    }          // add the item to the front
    
    public void addLast(Item item)
    {
        nullcheck(item);
        size++;
        if (size == 1){
            addFirstItem(item);            
        }
        else if (size == 2){
            last.item = item;
            //setting up next and before
            last.before = first;
            first.next = last;
        }
        else{
            Node oldNode = deepCopyNode(last);
            oldNode.before.next = oldNode;
            last.item = item;
            last.before = oldNode;
            oldNode.next = last;
        }    
    }           // add the item to the end
    
    public Item removeFirst()
    {
        if (size > 0){
            size --;
            if (size == 0){
                Item itemReturn = first.item;
                first.item = null;
                last.item = null;
                first.next = null;
                last.before = null;
                return itemReturn;
            }
            else if(size == 1){
                Item itemReturn = first.item;
                first.item = last.item;
                last.before = null; 
                first.next = null;
                return itemReturn;
            }
            else{
                Item itemReturn = first.item;
                Node nextFirst = first.next;
                first.next = null;
                first = nextFirst;
                first.before = null;
                return itemReturn;
            }
        }
        throw new java.util.NoSuchElementException();
    }                // remove and return the item from the front
    
    public Item removeLast()
    {
        if (size > 0){
            size --;
            if (size == 0){
                Item itemReturn = last.item;
                last.item = null;
                first.item = null;
                last.before = null;
                first.next = null;
                return itemReturn;
            }
            else if(size == 1){
                Item itemReturn = last.item;
                last.item = first.item;
                first.next = null;
                last.before = null;
                return itemReturn;
            }
            else{
                Item itemReturn = last.item;
                Node beforelast = last.before;
                last.before = null;
                last = beforelast;
                last.next = null;
                return itemReturn;
            }
        }
        throw new java.util.NoSuchElementException();
    }                 // remove and return the item from the end
    
       public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }         // return an iterator over items in order from front to end

    
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){return current != null && current.item != null;}
        public void remove(){throw new java.lang.UnsupportedOperationException();}
        public Item next(){
            //in case of no more items to return:
            if (current == null){throw new java.util.NoSuchElementException();}
            Item myItem = current.item;
            
            current = current.next;
            //if (hasNext()) {current = current.next;}
            return myItem;
        }
    }

    private class Node{
        Item item;
        Node next;
        Node before;
    }
    
    public static void main(String[] args){
        
        
        Deque<Integer> myDequeIteratorTest = new Deque<>();
        
        
        
        Iterator<Integer> myDequeIterator = myDequeIteratorTest.iterator();
        
        StdOut.println(myDequeIterator.hasNext());
        
        myDequeIteratorTest.addFirst(1337);
        myDequeIteratorTest.addFirst(1);
        myDequeIteratorTest.addFirst(2);
        myDequeIteratorTest.addFirst(3);
        
        /*
        StdOut.println(myDequeIterator.hasNext());
        
        while(myDequeIterator.hasNext()){
        StdOut.println(myDequeIterator.next());
        }
        */
        
        for (Integer it : myDequeIteratorTest){
            StdOut.println ("this is so cool " +it);
        }
        
        /*
        
        int y = 100;
        
        StdOut.println("----- addFirst, removeFirst ----");
        
        Deque<Integer> myIntegerDeque = new Deque<Integer>();
        for (int n = 0; n < y; n++){
            myIntegerDeque.addFirst(n);
        }
        
        while(!myIntegerDeque.isEmpty()){
            StdOut.print(myIntegerDeque.removeFirst() + ", ");
        }
       
        StdOut.println("----- addFirst, removeLast ----");
        
        Deque<Integer> myIntegerDeque2 = new Deque<Integer>();
        for (int n = 0; n < y; n++){
            myIntegerDeque2.addFirst(n);
        }
        
        while(!myIntegerDeque2.isEmpty()){
            StdOut.print(myIntegerDeque2.removeLast() + ", ");
        }
        
        StdOut.println("----- addLast , removeFirst ----");
        
        Deque<Integer> myIntegerDeque3 = new Deque<Integer>();
        for (int n = 0; n < y; n++){
            myIntegerDeque3.addLast(n);
        }
        
        while(!myIntegerDeque3.isEmpty()){
            StdOut.print(myIntegerDeque3.removeFirst() + ", ");
        }
        
        StdOut.println("----- addLast , removeLast ----");
        
        Deque<Integer> myIntegerDeque4 = new Deque<Integer>();
        for (int n = 0; n < y; n++){
            myIntegerDeque4.addLast(n);
        }
        
        while(!myIntegerDeque4.isEmpty()){
            StdOut.print(myIntegerDeque4.removeLast() + ", ");
        }
        
        StdOut.println("SAME Deque - addFirst, removeFirst ----");
        
        Deque<Integer> myIntegerDequeSame = new Deque<Integer>();
        for (int n = 0; n < y; n++){
            myIntegerDequeSame.addFirst(n);
        }
        
        while(!myIntegerDequeSame.isEmpty()){
            StdOut.print(myIntegerDequeSame.removeFirst() + ", ");
        }
       
        StdOut.println("----- addFirst, removeLast ----");
        
        for (int n = 0; n < y; n++){
            myIntegerDequeSame.addFirst(n);
        }
        
        while(!myIntegerDequeSame.isEmpty()){
            StdOut.print(myIntegerDequeSame.removeLast() + ", ");
        }
        
        StdOut.println("----- addLast , removeFirst ----");
        
        for (int n = 0; n < y; n++){
            myIntegerDequeSame.addLast(n);
        }
        
        while(!myIntegerDequeSame.isEmpty()){
            StdOut.print(myIntegerDequeSame.removeFirst() + ", ");
        }
        
        StdOut.println("----- addLast , removeLast ----");
        
        for (int n = 0; n < y; n++){
            myIntegerDequeSame.addLast(n);
        }
        
        while(!myIntegerDequeSame.isEmpty()){
            StdOut.print(myIntegerDequeSame.removeLast() + ", ");
        }
        */
        
    }   // unit testing (optional)
}