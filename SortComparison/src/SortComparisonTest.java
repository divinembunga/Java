import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2019
 */

/*TABLE AND ANSWERS TO QUESTIONS:
 * -------------------------------------------------------------------------------------------
 *  nanoseconds  |    Insert    |     Quick    |    Merge R   |   Merge I    |   Select     |
 *-------------------------------------------------------------------------------------------
 *   10 Random   |5000959716 ns |5002053190 ns |5000766293 ns |5000011947 ns |5000366365 ns |
 *-------------------------------------------------------------------------------------------
 *  100 Random   |5000080214 ns |5001289103 ns |4999948800 ns |5000005120 ns |4999971556 ns | 
 *-------------------------------------------------------------------------------------------                 
 * 1000 Random   |5005944321 ns |5039576475 ns |5001364765 ns |5001278294 ns |5001710081 ns |
 *-------------------------------------------------------------------------------------------
 *1000 few unique|5006321495 ns |5004357370 ns |5001243592 ns |4999926044 ns |5003145957 ns |
 *-------------------------------------------------------------------------------------------
 *1000 near order|5006370909 ns |5003017815 ns |5001151760 ns |4999880533 ns |5005191681 ns |
 *-------------------------------------------------------------------------------------------
 *1000 reverse   |5006913140 ns |5002059806 ns |5001130071 ns |4999911822 ns |5005204766 ns |
 *-------------------------------------------------------------------------------------------
 *1000 sorted    |5005000535 ns |5003695504 ns |5001784605 ns |5000310045 ns |5007695363 ns |
 *-------------------------------------------------------------------------------------------
 *
 * a)Insertion sort- Say if the array was in reverse order the number at the very end of
 *   the array and to swap with every element to reach the beginning of the array and same 
 *   goes for all the other elements. If the order is close to ascending order the algorithm
 *   will run faster as not many swaps are needed.
 *   
 * b)Selection sort(according to table above)- the whole array has to be scanned to 
 *   find the minimum number and secondly confirm that it is the minimum number.
 *   
 * c)The algorithm that has difference in performance in time based on input size is 
 *   selection sort as no matter the circumstance the whole array has to be scanned. 
 *   
 * d)I noticed from my results that iterative merge sort works faster when the inputs are
 *   bigger and slower when the inputs are smaller. However with recursive merge sort the 
 *   algorithm works faster when the input is larger and slower when the input is small.
 *   
 * e)Merge sort Iterative is the fastest of the 7 input files from the table above.
 * */

			


@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	double[]testSort = new double[0];
    	
    	
    	//double[]testSortAns = new double[0];// another empty list
    	
    	//assertEquals(testSortAns,SortComparison.insertionSort(testSort));
    	assertNull(SortComparison.insertionSort(testSort));
    	assertNull(SortComparison.selectionSort(testSort));
    	assertNull(SortComparison.mergeSortIterative(testSort));
    	assertNull(SortComparison.mergeSortRecursive(testSort));
    	assertNull(SortComparison.quickSort(testSort));
    	
    }
    
     // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.

    /**
     * Check that the methods work for various types of arrays
     */
    @Test
    public void testSortArray()
    {
    	/*Array with 5 doubles*/
    	double[] array1 = {1.0,3.0,5.0,11.0,3.0,10.0};
    	double[] array1Ans = {1.0,3.0,3.0,5.0,10.0,11.0};
    	
    	assertArrayEquals(array1Ans,SortComparison.insertionSort(array1),1.0);
    	assertArrayEquals(array1Ans,SortComparison.selectionSort(array1),1.0);
    	assertArrayEquals(array1Ans,SortComparison.quickSort(array1),1.0);
    	assertArrayEquals(array1Ans,SortComparison.mergeSortIterative(array1),1.0);
    	assertArrayEquals(array1Ans,SortComparison.mergeSortRecursive(array1),1.0);
    	
    	/*Array with 1 double */
    	double[] array2 = {17.0};
    	double[] array2Ans = {17.0};
    	
    	assertArrayEquals(array2Ans,SortComparison.insertionSort(array2),17.0);
    	assertArrayEquals(array2Ans,SortComparison.selectionSort(array2),17.0);
    	assertArrayEquals(array2Ans,SortComparison.quickSort(array2),17.0);
    	assertArrayEquals(array2Ans,SortComparison.mergeSortIterative(array2),17.0);
    	assertArrayEquals(array2Ans,SortComparison.mergeSortRecursive(array2),17.0);
    	
    	/*Array with all same doubles*/
    	double[] array3 = {3.0,3.0,3.0,3.0,3.0,3.0};
    	double[] array3Ans = {3.0,3.0,3.0,3.0,3.0,3.0};
    	
    	assertArrayEquals(array3Ans,SortComparison.insertionSort(array3),3.0);
    	assertArrayEquals(array3Ans,SortComparison.selectionSort(array3),3.0);
    	assertArrayEquals(array3Ans,SortComparison.quickSort(array3),3.0);
    	assertArrayEquals(array3Ans,SortComparison.mergeSortIterative(array3),3.0);
    	assertArrayEquals(array3Ans,SortComparison.mergeSortRecursive(array3),3.0);
    			
    	/*Array in reverse order*/
    	double[] array4 = {30.0,25.0,20.0,15.0,5.0};
    	double[] array4Ans = {5.0,15.0,20.0,25.0,30.0};
    	
    	assertArrayEquals(array4Ans,SortComparison.insertionSort(array4),5.0);
    	assertArrayEquals(array4Ans,SortComparison.selectionSort(array4),5.0);
    	assertArrayEquals(array4Ans,SortComparison.quickSort(array4),5.0);
    	assertArrayEquals(array4Ans,SortComparison.mergeSortIterative(array4),5.0);
    	assertArrayEquals(array4Ans,SortComparison.mergeSortRecursive(array4),5.0);
    	/*Array with mostly the same numbers*/
    	
    	/*Alternating greater ->smaller array*/
    	
    	
    }



	
    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     * @throws InterruptedException 
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        //TODO: implement this method
    	int fileNumber = 0;
		int size =0;
		String fileName = null;
		while (fileNumber < 7) {
			switch(fileNumber){
			case 0:size=10;fileName="numbers10.txt";break;
			case 1:size =100;fileName="numbers100.txt";break;
			case 2:size=1000;fileName="numbers1000.txt";break;
			case 3:size=1000;fileName="numbers1000Duplicates.txt";break;
			case 4:size=1000;fileName="numbersNearlyOrdered1000.txt";break;
			case 5:size=1000;fileName="numbersReverse1000.txt";break;
			case 6:size=1000;fileName="numbersSorted1000.txt";break;
			default:break;
			
			}
			double[] arrayOfDoubles = new double[size];// max size
			double[] tmpArray = arrayOfDoubles;
			
			try {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				boolean endOfFile = false;
				int index = 0;
				while (index < 10) {
					
					for (int i = 0; i < arrayOfDoubles.length; i++) {
						String doubleData = bufferedReader.readLine();

						index++;

						arrayOfDoubles[i] = Double.parseDouble(doubleData);
					}
					
					//Printing out all the run times of each algorithm
					System.out.println(fileName.toUpperCase());
					long startTime = System.nanoTime();
					TimeUnit.SECONDS.sleep(5);
					tmpArray = SortComparison.insertionSort(arrayOfDoubles);
					long endTime = System.nanoTime();

					long executionTime = endTime - startTime;
					System.out.println("\nIn:Total exceution time (ns): " + executionTime);
					System.out.println("\nTotal exceution time(ms): " + (executionTime / 1000000));

					startTime = System.nanoTime();
					TimeUnit.SECONDS.sleep(5);
					tmpArray = SortComparison.mergeSortRecursive(arrayOfDoubles);
					endTime = System.nanoTime();

					executionTime = endTime - startTime;
					System.out.println("\nMR:Total exceution time (ns): " + executionTime);
					System.out.println("\nTotal exceution time(ms): " + (executionTime / 1000000));

					startTime = System.nanoTime();
					TimeUnit.SECONDS.sleep(5);
					tmpArray = SortComparison.mergeSortIterative(arrayOfDoubles);
					endTime = System.nanoTime();

					executionTime = endTime - startTime;
					System.out.println("\nMI:Total exceution time (ns): " + executionTime);
					System.out.println("\nTotal exceution time(ms): " + (executionTime / 1000000));

					startTime = System.nanoTime();
					TimeUnit.SECONDS.sleep(5);
					tmpArray = SortComparison.quickSort(arrayOfDoubles);
					endTime = System.nanoTime();

					executionTime = endTime - startTime;
					System.out.println("\nQ:Total exceution time (ns): " + executionTime);
					System.out.println("\nTotal exceution time(ms): " + (executionTime / 1000000));

					startTime = System.nanoTime();
					TimeUnit.SECONDS.sleep(5);
					tmpArray = SortComparison.selectionSort(arrayOfDoubles);
					endTime = System.nanoTime();

					executionTime = endTime - startTime;
					System.out.println("\nS:Total exceution time (ns): " + executionTime);
					System.out.println("\nTotal exceution time(ms): " + (executionTime / 1000000));

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		fileNumber++;
	}
}

