import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Divine Mbunga
 *  @version 01/10/18 17:35:49
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1);
     *
     * Justification:
     *  This function checks if the head and tail pointers are null. 
     *  Therefore it is a constant time operation.
     */
    public boolean isEmpty()
    {
      return(head==null);
      
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     * Assume that the operations contained in the while loops are all constants, 
     * then every iteration of the while loop will cost Theta(1). In worst case however,
     * the while loops will iterate N times, N*Theta(1)= Theta(N).
     */
    public void insertBefore( int pos, T data ) 
    {
      //TODO
    	if(isEmpty()){
    		DLLNode newHead = new DLLNode(data,head,null);
    		head=newHead;
    		tail=newHead;
    		
    	}else if(pos <= 0){
    		DLLNode newFirst= new DLLNode(data,null,head);
    		head.prev=newFirst;
    		head= newFirst;
    		
    	}else if (pos>0 && pos<countElements()){ 
    		DLLNode tmp= head;
    		int i=0;
    		while(i < pos){
    			tmp= tmp.next;
    			i++;
    		}
    		DLLNode newNode= new DLLNode(data,null,null);
    		newNode.next=tmp;
    		newNode.prev= tmp.prev;
    		tmp.prev.next=newNode;
    		tmp.prev=newNode;
    	}else if(pos >= countElements()){
    		DLLNode newLast = new DLLNode(data,tail,null);
    		tail.next=newLast;
    		tail=newLast;
    	}
    	
      return;
    }
    
    public int countElements(){
    	int count =0;
    	DLLNode tmp = head;
    	while(tmp !=null){
    		tmp= tmp.next;
    		count++;
    	}
    	
    return count;	
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification: 
     *  Again assume all the operations in the while loop are constant time.
     *  In the worst case the node we are looking for will be at the end of 
     *  the elements -N therefore N*Theta(1)= Theta(N).
     *
     */
    public T get(int pos) 
    
    {
      //TODO
    	if(pos<0||isEmpty()){
    		return null;
    	}else if(pos>=countElements()){
    		return null;
    	}else{
    		DLLNode tmp=head;
        	for(int i=0; i<pos;i++ ){
        		tmp= tmp.next;
        	}
        	T dataAtPos=tmp.data;
        	return dataAtPos;
    	}
    	
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  We can see that this algorithm contains constants -theta(1) and a linear- the for loop
     *  (theta(N))order of growth.
     */
    public boolean deleteAt(int pos)  
    {
      //TODO
    	if(pos<0){
    		return false;
    	}else if(pos>countElements()){
    		return false;
    	
    	}else if(pos>0 && countElements()==1){
    		return false;
    		
    	}else if(pos==0){
    		if(isEmpty()){
    			return false;
    		}else{
    			while(head != null ){
    				if(head != null && head.next != null){
    					head= head.next;
        				head.prev=null;
        				return true;
    				}else{
    					head=null;
    					return true;
    				}
    				
    			}
    		
    		}
    		
    	}else if (pos >0 && pos<countElements()){
    		DLLNode tmp=head;
        	for(int i=0; i<pos;i++ ){
        		tmp= tmp.next;
        	}
        	
        	//changing prev and next pointers
        	DLLNode tmpP=tmp.prev;
        	DLLNode tmpN=tmp.next;
        	tmpP.next=tmp.next;
        	if(tmpN.prev !=null){
        		tmpN.prev=tmp.prev;
        	}else{
        		return false;
        	}
        	
     
        	return true;
        	
  
    	}else if(pos == countElements()){
    		DLLNode tmp =tail;
    	    System.out.println("tail is:"+tmp.data);
    	    DLLNode tmpP=tmp.prev;
        	DLLNode tmpN=tmp.next;
        	
        	tmpP.next=null;
        	tmp=null;
        	tail=tmpP;
        	
    	    
    	    return true;
    	}
    	return false;
      
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *   Again assume all the operations in the while loop are constant time.
     *  In the worst case the node we are looking to reverse last will be at the end of 
     *  the elements -N therefore N*Theta(1)= Theta(N).
     *
     */
    public void reverse() 
    {
      //TODO
    	DLLNode current=head;
    	DLLNode tmp=null;
    	
    	//swap next and prev in all nodes
    	while(current != null){
    		tmp=current.prev;
    		current.prev=current.next;
    		current.next=tmp;
    		current=current.prev;
    	}
    	
    	//checking for cases like empty list or list with 1 node
    	if(tmp != null){
    		head=tmp.prev;
    	}
    	
    	
    }
    
    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: Theta(N^2)
     *
     * Justification:
     *  
     *  
     */
     public void makeUnique() 
    {
    	 if(isEmpty()){
    		 return;
    	 }
    	 DLLNode currentNode=head;
    	 DLLNode tmp= null;
    	 DLLNode sameNode=null;
    	 while(currentNode!=null && currentNode.next!=null){
    		 tmp = currentNode;
    		 while(tmp.next !=null){
    			 if(currentNode.data == tmp.next.data){
        			 sameNode=tmp.next;
        			 tmp.next = tmp.next.next;
        		 }else{
        			 tmp =tmp.next;
        		 }
        		  
    		 }
    		 currentNode=currentNode.next;
    		
    	 }
    	
    	 
    }


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: 1
     *
     * Justification:
     *  We know that push has an order of growth of Theta(1) and we assume that the other functions-
     *  insertBefore 
     */
    public void push(T item) 
    {
     DLLNode node = new DLLNode(item,null,head);
     if(isEmpty()){
    	 head = node;
    	 tail = node;
    	 tail.next=null;
     }else{
    	 head.prev = node;
    	 head = node;
     }
   
    	
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  We assume the function only contains a constant order of growth as there are only declarative
     *  and expressions.
     */
    public T pop()  
    {
      //TODO
       
       if(isEmpty()){
    	   return null;
       }else{
    	   DLLNode tmp =head;
    	   T popData= tmp.data;
    	   head=head.next;
    	   return popData;
       }
		
    
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  We know that enqueue has an order of growth of Theta(1) and we assume that the other functions-
     *  insertBefore 
     */
    public void enqueue(T item) 
    {
      DLLNode node = new DLLNode(item,head,null);
      if(isEmpty()){
    	  head = node;
    	  tail = node;
    	  tail.prev = null;
      }else{
    	  tail.next = node;
    	  tail=node;
      }
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  This function contains a constant order of growth has it only contains declaratives.
     */
    public T dequeue() 
    {
    	DLLNode currentNode = head;
        if(isEmpty()){
      	  return null;
        }else{
      	  T item = currentNode.data;
      	  head = currentNode.next;
      	  return item;
        }
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



