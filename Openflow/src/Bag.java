/**
 * Bag Class
 * 
 * @author Divine Mbunga
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;    // start the of bag
    private int n;               // number of elements bag conatins

    //linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Creates an empty bag.
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Returns true if this bag is empty.
     *
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the cardinality of items in this bag.
     *
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this bag.
     *
     */
    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }


    /**
     * Returns an iterator that iterates over the items in this bag in order.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }


    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { 
        	return current != null;   
        }
        public void remove(){ 
        	throw new UnsupportedOperationException(); 
        }

        public Item next() {
            if (!hasNext()){
            	throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    /**
     * Unit tests the class
     *
     */
    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("Cardinality of bag: " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }

}