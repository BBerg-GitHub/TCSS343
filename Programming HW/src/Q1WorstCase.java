import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/** Brandi Berg
 * TCSS 343 - Fall 2019
 * Assignment3
 */

/** Using an array of random numbers implement a QuickSort algorithm to sort the contents of the array. 
 * @author Brandi Berg */
public class Q1WorstCase {

	/** 2.3 Programming QuickSort: Worst Case (first index = pivot). 
	 * This method will select the pivot to be in the first index. 
	 * @param a interger array.
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

	/** Driver of the program. 
	 * @param args.
	 */
	public static void main(String[] args) {

		//User input to initialize the length of the array. 
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the number of elements in your array: ");
		int n = sc.nextInt();
		sc.close();

		Integer[] temp = new Integer[n];
		for (int i = 0; i < temp.length; i++) {
			//Random object used to generate random numbers. 
			Random rand = new Random(); 
			temp[i] = rand.nextInt() % 10;		
		}

		//Testing output. 
		System.out.println("Print temp array: " + Arrays.toString(temp));
		Q1WorstCase qsp = new Q1WorstCase();
		qsp.QuickSort1st(temp);
		System.out.println("Print temp array after sorting: " + Arrays.toString(temp));
	}

}
