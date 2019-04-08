import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author Divine Mbunga 
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    @Test
    public void testDeleteAt(){
    	DoublyLinkedList<Integer>testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
 		
		assertTrue("Checking deleteAt() for a value at the head of the list.",testDLL.deleteAt(0));
		
		assertTrue("Checking deleteAt() for a value in the middle of the list.",testDLL.deleteAt(2));

 		// test deleting a value when the list is empty
		testDLL = new DoublyLinkedList<Integer>();
		assertFalse("Checking deleteAt() when the list is empty.", testDLL.deleteAt(0));
        
		testDLL.insertBefore(0,1);
		assertFalse("Checking deleteAt() for a value at the ",testDLL.deleteAt(1));
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		assertTrue("Checking deleteAt() for a value .",testDLL.deleteAt(0));
    }
    
    
    @Test  
    public void testGet(){
    	DoublyLinkedList<Integer>testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
    	
        assertEquals("Checking get","1",testDLL.get(0).toString());
        assertEquals("Checking get","2",testDLL.get(1).toString());
        assertNull("Checking get",testDLL.get(4));
        testDLL = new DoublyLinkedList<Integer>();
        assertNull("checking get() when the list is empty",testDLL.get(0));
        testDLL.insertBefore(0,1);
        assertNull("checking get() when the list is empty",testDLL.get(-2));

        
    }
    
    @Test
    public void testReverse(){
    	DoublyLinkedList<Integer>testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        
        testDLL.reverse();
        assertEquals("Checking reverse to a list containing 4 elements","4,3,2,1",testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
     	testDLL.reverse();
     	assertEquals("Checking reverse() on an empty list.", "",testDLL.toString());
     	testDLL = new DoublyLinkedList<Integer>();
     	testDLL.insertBefore(0, 1);
        testDLL.reverse();
        assertEquals("Checking reverse() on a list with 1 element", "1", testDLL.toString());
        
    }
    
    @Test
    public void testMakeUnqiue(){
    	DoublyLinkedList<Integer>testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,4);
        testDLL.insertBefore(2,4);
        testDLL.insertBefore(3,3);
        
        testDLL.makeUnique();
        assertEquals("Checking makeUnique to a list containing 4 elements","1,4,3",testDLL.toString());
       
       
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,1);
        testDLL.insertBefore(4,1);
        testDLL.insertBefore(5,1);
        testDLL.insertBefore(6,1);

        testDLL.makeUnique();
        assertEquals("Checking makeUnique to a list containing 4 elements","2,1",testDLL.toString());
       
        
        
    }
    
    @Test
    public void testPushAndPop(){
    	DoublyLinkedList<Integer>testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(1);
		testDLL.push(2);
		testDLL.push(3);
		testDLL.push(4);
 	
		assertEquals("checking push","4,3,2,1",testDLL.toString());
		testDLL.pop();
		assertEquals("checking pop","3,2,1",testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		assertNull("Checking pop() when the list ('stack') is empty. Expected result = null", testDLL.pop());
    }
    
    
    
    @Test
    public void testEndqueueAndDequeue(){
    	DoublyLinkedList<Integer>testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(100);
    	
    	assertEquals("Checking dequeue","100",testDLL.dequeue().toString());
    	
    	
    	testDLL = new DoublyLinkedList<Integer>();
		// test dequeue() when the list is empty
		assertNull("Checking dequeue() when the list is empty.", testDLL.dequeue());
       
    }
    
    
   
    
    @Test
    public void testPop(){
    	DoublyLinkedList<Integer>testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(100);
    	testDLL.push(2);
    	
        
        
        assertEquals("Checking pop","2",testDLL.pop().toString());
        assertEquals("Checking pop","100",testDLL.pop().toString());
        
    }

}
