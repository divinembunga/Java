/**
 * IndexMinPQ Class
 * 
 * @author Divine Mbunga
 */
import java.util.NoSuchElementException;
import java.util.Iterator;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN;        // max number of elements on queue
    private int n;           // cardinality of elements
    private int[] pq;        // binary heap 
    private int[] qp;        // inverse of pq 
    private Key[] keys;      // priority of i

    /**
     * Initializes an empty indexed priority queue with indices.
     * 
     */
    public IndexMinPQ(int maxNumber) {
        if (maxNumber < 0) throw new IllegalArgumentException();
        this.maxN = maxNumber;
        n = 0;
        keys = (Key[]) new Comparable[maxNumber + 1];    
        pq   = new int[maxNumber + 1];
        qp   = new int[maxNumber + 1];                   
        for (int i = 0; i <= maxNumber; i++)
            qp[i] = -1;
    }

    /**
     * Returns true if this priority queue is empty.
     *
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     *
     * Returns true if a number is an index on the priority queue;
     *        
     */
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        return qp[i] != -1;
    }

    /**
     * Returns the number of keys in the priority queue.
     */
    public int size() {
        return n;
    }

    /**
     * Links key with index {@code i}.
     *
     */
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN){
        	throw new IllegalArgumentException();
        }
        if (contains(i)){
        	throw new IllegalArgumentException("Index is already exsits");
        }
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Returns an index associated with a minimum key.
     */
    public int minIndex() {
        if (n == 0){
        	throw new NoSuchElementException("Priority queue underflowed");
        }
        return pq[1];
    }

    /**
     * Returns a minimum key.
     */
    public Key minKey() {
        if (n == 0){
        	throw new NoSuchElementException("Priority queue underflowed");
        }
        return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflowed");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n+1];
        qp[min] = -1;        // delete
        keys[min] = null;    // assits with garbage collection
        pq[n+1] = -1;        
        return min;
    }

    /**
     * Returns the key associated with index {@code i}.
     */
    public Key keyOf(int i) {
        if (i < 0 || i >= maxN){
        	throw new IllegalArgumentException();
        }
        if (!contains(i)){
        	throw new NoSuchElementException("Index does not exist");
        }
        else return keys[i];
    }

    /**
     * Change the key associated with an index to the specified value.
     */
    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN){
        	throw new IllegalArgumentException();
        }
        if (!contains(i)){
        	throw new NoSuchElementException("Index does not exist ");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * Change the key associated with an index to the specified value
     */
    @Deprecated
    public void change(int i, Key key) {
        changeKey(i, key);
    }

    /**
     * Decrease the key associated with an index to the specified value.
     */
    public void decreaseKey(int i, Key key) {
        if (i < 0 || i >= maxN){
        	throw new IllegalArgumentException();
        }
        if (!contains(i)){
        	throw new NoSuchElementException("Index does not exist");
        }
        if (keys[i].compareTo(key) <= 0){
        	 throw new IllegalArgumentException("Error");
        }
           
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * Increase the key associated with index {@code i} to the specified value.
     *
     */
    public void increaseKey(int i, Key key) {
        if (i < 0 || i >= maxN){
        	throw new IllegalArgumentException();
        }
        if (!contains(i)){
        	throw new NoSuchElementException("Index does not exist");
        }
        if (keys[i].compareTo(key) >= 0){
        	throw new IllegalArgumentException("Error");
        }
            
        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * Remove the key associated with the index.

     */
    public void delete(int i) {
        if (i < 0 || i >= maxN){
        	throw new IllegalArgumentException();
        }
        if (!contains(i)){
        	throw new NoSuchElementException("Index does not exist");
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }


    /**
     * 
     * GENERAL FUNCTIONS
     * 
     */
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    /**
     * 
     * HEAP FUNCTIONS
     * 
     */
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    /**
     * 
     * ITERATORS
     * 
     */

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     *
     */
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // constructs  a new pq
        private IndexMinPQ<Key> copy;

        // add all elements to a copy of heap
        public HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext(){
        	return !copy.isEmpty();
        }
        
        public void remove(){ 
        	throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()){
            	throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }


    /**
     * Unit tests the class
     *
     */
    public static void main(String[] args) {
        // insert random strings
        String[] strings = { "hi", "today", "was", "the", "best", "of", "times", "and", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        // re-insert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }
}
