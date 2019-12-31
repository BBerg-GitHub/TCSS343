import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/** Brandi Berg
 * TCSS 343 - Fall 2019
 * Assignment3
 */

/** 2.3 Programming Quicksort: Best Case (middle pivot). 
 *  Using an array of random numbers implement a QuickSort algorithm to sort the contents of the array. 
 * @author Brandi Berg */
public class Q1BestCase {

	/**
	 * This method will select the pivot to be in the first index. 
	 * @param a interger array.
	 * @param l value found at first index.
	 * @param r value found at the last index.
	 * @return j the last value in the array.
	 */
	public int PivotMid(Comparable[] a, int l, int r) 	{

		int mid = l + (r - l) / 2;
		int i = l + 1;
		int j = r;

		Comparable pivot = a[l + (r - l) / 2];
		Comparable t = a[mid];

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

			if (i <= j) {

				swap(a, i, j);
				i++;
				j--;
			}
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

	public void QuickSortMid(Comparable[] a , int l, int r) {

		if (r > l ) {
			int p = PivotMid(a, l, r);
			QuickSortMid(a, l, p - 1);
			QuickSortMid(a, p + 1, r);
		}
	}

	public void QuickSortMid(Comparable[] a) {
		QuickSortMid(a, 0, a.length -1);
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
		Q1BestCase qsp = new Q1BestCase();
		qsp.QuickSortMid(temp);
		System.out.println("Print temp array after sorting: " + Arrays.toString(temp));
	}

}
