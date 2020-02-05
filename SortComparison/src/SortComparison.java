import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.util.concurrent.TimeUnit;

// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author Divine Mbunga
 * @version HT 2019
 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static,
	 * thus it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {

		if (a.length == 0) {
			return null;
		}
		double tmp;
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					tmp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = tmp;

				}

			}
		}
		return a;

	}// end insertionsort

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {
		if (a.length == 0) {
			return null;
		}
		shuffleArray(a);//to avoid large run time
		quickSortRecursive(a, 0, a.length - 1);
		return a;

	}// end quicksort

	private static void quickSortRecursive(double[] a, int low, int high) {
		if (high <= low) {
			return;
		}
		int pivotIndex = partArray(a, low, high);
		quickSortRecursive(a, low, pivotIndex - 1);
		quickSortRecursive(a, pivotIndex + 1, high);

	}

	private static int partArray(double[] a, int low, int high) {
		int i = low;
		int j = high + 1;
		double pivotDouble = a[low];
		while (true) {
			while (a[++i] < pivotDouble) {
				if (i == high) {
					break;
				}
			}
			while (pivotDouble < a[--j]) {
				if (j == low) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			double tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;

		}
		a[low] = a[j];
		a[j] = pivotDouble;
		return j;
	}

	private static void shuffleArray(double[] a) {
		Random random = ThreadLocalRandom.current();
		for (int i = a.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			double tmp = a[index];
			a[index] = a[i];
			a[i] = tmp;
		}
	}

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	static double[] mergeSortIterative(double a[]) {
		if (a.length == 0) {
			return null;
		}
		double[] array = new double[a.length];
		for (int i = 1; i < a.length; i = i + i) {
			for (int j = 0; j < a.length - i; j += i + i) {
				mergeArray(a, array, j, j + i - 1, Math.min(j + i + i - 1, a.length - 1));
			}
		}

		return a;

	}// end mergesortIterative

	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */
	static double[] mergeSortRecursive(double a[]) {
		if (a.length == 0) {
			return null;
		}
		double[] array = new double[a.length];
		mergeSortRecursive(a, array, 0, a.length - 1);
		return a;

	}// end mergeSortRecursive

	private static void mergeSortRecursive(double[] a, double[] array, int low, int high) {
		if (high <= low) {
			return;
		}
		int middle = low + (high - low) / 2; // prevent integer overflow
		mergeSortRecursive(a, array, low, middle);
		mergeSortRecursive(a, array, middle + 1, high);
		mergeArray(a, array, low, middle, high);

	}

	private static void mergeArray(double[] a, double[] array, int low, int middle, int high) {
		for (int h = low; h <= high; h++) {
			array[h] = a[h];
		}
		int i = low;
		int j = middle + 1;
		for (int h = low; h <= high; h++) {
			if (i > middle) {
				a[h] = array[j++];
			} else if (j > high) {
				a[h] = array[i++];
			} else if (array[j] < array[i]) {
				a[h] = array[j++];
			} else {
				a[h] = array[i++];
			}
		}

	}

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static,
	 * thus it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		if (a.length == 0) {
			return null;
		}
		for (int i = 0; i < a.length - 1; i++) {
			int minNumberIndex = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[minNumberIndex]) {
					minNumberIndex = j;

				}
			}
			double tmp = a[minNumberIndex];
			a[minNumberIndex] = a[i];
			a[i] = tmp;
		}
		return a;

		

	}// end selectionsort

}
