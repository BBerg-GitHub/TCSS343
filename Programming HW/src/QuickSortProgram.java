import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/** Brandi Berg
 * TCSS 343 - Fall 2019
 * Assignment3
 */

/** Write a program that...
 * a) creates two copiesa1st[]and amid[]of a random array of n integers(where n is a program input), 
 * b) sorts them by invoking respectively the Quicksort1st and QuicksortMid methods above, 
 * c) outputs the time needed for each of these two calls, 
 * d) then calls the same methods again on the sorted arrays, 
 * e) outputs the time needed for each of these two calls
 * Using an array of random numbers implement a QuickSort algorithm to sort the contents of the array. 
 * @author Brandi Berg */
public class QuickSortProgram {
	
	private static Integer[]amid;
	private static Integer[]a1st;
	private static long startTime;
	private static long endTime;

	/** This method will generate n random numbers to fill the arrays with. */
	public static void NumberGenerator(Integer[] a) {
		for (int i = 0; i < a.length; i++) {
			//Random object used to generate random numbers. 
			Random rand = new Random(); 
			a[i] = rand.nextInt(a.length) % 10;		
		}
	}
	
	/**
	 * This method will select the pivot to be in the first index. 
	 * @param a integer array.
	 * @param l value found at first index.
	 * @param r value found at the last index.
	 * @return j the last value in the array.
	 */
	public int Pivot1st(Comparable[] a, int l, int r) {
		int i = l;
		int j = r + 1;
		int pivot = l;

		while (true) {

			while (a[++i].compareTo(a[pivot]) < 0) {
				if (i == r) {
					break;
				}
			}
			
			while (a[pivot].compareTo(a[--j]) < 0) {
				if (j == l) {
					break;
				}
			}
			
			if (i >= j) {
				break;
			}
			swap(a, i, j);
		}
		swap(a, l, j);
		return j;
	}
	
	/**
	 * This method will select the pivot to be in the first index. 
	 * @param a integer array.
	 * @param l value found at first index.
	 * @param r value found at the last index.
	 * @return j the last value in the array.
	 */
	public int PivotMid(Comparable[] a, int l, int r) 	{

		int mid = l + (r - l) / 2;
		int i = l + 1;
		int j = r;

		Comparable pivot = a[l + (r - l) / 2];
		//Comparable t = a[mid];

		swap(a, mid, l);

		while (i <= j) {

			while (pivot.compareTo(a[i]) > 0) {

				i++;

				if (i > r) {
					break;
				}
			}

			while (pivot.compareTo(a[j]) < 0) {

				--j;
			}
				swap(a, i, j);
				i++;
				j--;
		}

		if (j < i) {

			swap(a, l, j);		
			return j;
		} else if (i == j) {
			swap(a, l, i - 1);
			return i - 1;
		}

		return j;
	}

	public static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public void QuickSort1st(Comparable[] a , int l, int r) {

		if (r > l ) {
			int p = Pivot1st(a, l, r);
			QuickSort1st(a, l, p - 1);
			QuickSort1st(a, p + 1, r);
		}
	}

	public void QuickSort1st(Comparable[] a) {
		QuickSort1st(a, 0, a.length -1);
	}
	
	/** This method will return the runtime for the a1st and amid arrays.  */
	public static long getTimeQuickSort1st() {
		
		long startTime = System.currentTimeMillis();
		long totalTime = 0;
		for(int i = 0; i <100000000; i++) {
			
			totalTime +=1;
		}

		long stopTime = System.currentTimeMillis();
		long runTime = stopTime - startTime;
		System.out.print(runTime);
		
		return runTime;		
	}

	/** Driver of the program. 
	 * @param args.
	 */
	public static void main(String[] args) {
		
		//User input to initialize the length of the array. 
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the number of elements in your array: " + "\n");
		int n = sc.nextInt();
		sc.close();

		//Fill the arrays with n generated random numbers. 
		a1st = new Integer[n];
		amid = new Integer[n];
		NumberGenerator(a1st);
		NumberGenerator(amid);
		
		//Testing output. 
		QuickSortProgram qsp = new QuickSortProgram();
		System.out.println("Print a1st array: " + Arrays.toString(a1st));
		qsp.QuickSort1st(a1st);
		//getTimeQuickSort1st();
		System.out.println("Print a1st array after sorting: " + Arrays.toString(a1st) + "\n");
		
		System.out.println("Print amid array: " + Arrays.toString(amid));
		qsp.QuickSort1st(amid);
		System.out.println("Print amid array after sorting: " + Arrays.toString(amid));
	}

}
