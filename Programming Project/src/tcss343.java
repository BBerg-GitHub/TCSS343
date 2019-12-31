import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * TCSS 343 - Fall 2019
 * Programming Project
 * @author Brandi Berg
 * @author Hyelin Park
 *
 */
public class tcss343 {

	private static Integer infiniti = Integer.MAX_VALUE;

	/**
	 * The Driver of the program. 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		tcss343 t = new tcss343();
		int[][] array = t.readFile();


		double time2 = startTime();
		t.dynamicProgramming(array, 0, array.length);
		endTime(time2);
		
		double time = startTime();
		t.bruteForce(array);
		endTime(time);

		double time1 = startTime();
		System.out.println("\n" + "Divide and Conquer minimum cost: " + t.divideAndConquer(array, 0, array.length - 1));
		endTime(time1);		

//		Path fileLocation = Paths.get("input2.txt");
//		byte [] data = Files.readAllBytes(fileLocation);
//		System.out.println("Bytes: "+  Arrays.toString(data));
		//**MUST RUN BEFORE YOU CAN ACCESS THE TEST MATRICES.
		//t.createRandomTestMatrix();

	}
	
	private static void writeResultsToFile(String name, double totalTime) {
		
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("time.csv")));
			br.write(name);
			br.write("\n" + (totalTime));
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(" ");	
	}

	/**
	 * This method will divide the problem into sub problems.  
	 * Recursively call the sub problems. 
	 * @param cost 
	 * @param cost 2D array holding the values from the input file. 
	 * @return the cheapest path, and cost. 
	 */
	public int divideAndConquer(int[][] costArray, int i, int j) {

		// initialize the cheap
		int cheap = costArray[i][j];
		// if i is equal to j or i is greater than j, return 0;
		if(i >= j) {
			return cheap;
		}

		// if j is greater than i than the cheap cost is the costArray[i][j]
		else {
			// iterate the 2d costArray
			for(int temp = i+1; temp < j; temp++) {
				// store the minimum value
				int min = divideAndConquer(costArray, i, temp) 
						+ divideAndConquer(costArray, temp, j);

				// if the minimum value is less than the cheap, switch the values
				if(min < cheap) {
					cheap = min;
				}
			}
		}		

		return cheap;			
	}


	/** This method will create a file and write to it. 
	 * https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java*/
	private static void writeToFile(int [][] array, String fileName) throws IOException {

		BufferedWriter br = new BufferedWriter(new FileWriter(fileName));

		int a = array.length;
		int b = array[0].length;

		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				if (array[i][j] == -1) {
					br.append("NA\t");
				} else {
					br.append(Integer.toString(array[i][j]));
					if (j == b - 1) {
						br.append("\t");
					}
				}
				if (array[i][j] >= 0) {
					br.append("\t");
				}
			}
			br.newLine();
		}
		//		printTestArray(array);
		br.flush();
		br.close();
	}

	/** This method will create n x n matrix of random integers for the following values
	 * of n: 25, 50, 100, 200, 400, 800. */
	private static void createRandomTestMatrix() {

		int[][] randomTestArray;
		int[][] increasingRandomTestArray;

		try {
			randomTestArray = createTestingMatrix(1);	
			increasingRandomTestArray = createIncreasingTestingMatrix(1);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix1.txt");
			writeToFile(randomTestArray, "testMatrix1.txt");
			
			randomTestArray = createTestingMatrix(4);	
			increasingRandomTestArray = createIncreasingTestingMatrix(4);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix4.txt");
			writeToFile(randomTestArray, "testMatrix4.txt");

			randomTestArray = createTestingMatrix(25);	
			increasingRandomTestArray = createIncreasingTestingMatrix(25);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix25.txt");
			writeToFile(randomTestArray, "testMatrix25.txt");
			
			randomTestArray = createTestingMatrix(50);	
			increasingRandomTestArray = createIncreasingTestingMatrix(50);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix50.txt");
			writeToFile(randomTestArray, "testMatrix50.txt");

			randomTestArray = createTestingMatrix(100);	
			increasingRandomTestArray = createIncreasingTestingMatrix(100);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix100.txt");
			writeToFile(randomTestArray, "testMatrix100.txt");

			randomTestArray = createTestingMatrix(200);	
			increasingRandomTestArray = createIncreasingTestingMatrix(200);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix200.txt");
			writeToFile(randomTestArray, "testMatrix200.txt");

			randomTestArray = createTestingMatrix(400);	
			increasingRandomTestArray = createIncreasingTestingMatrix(400);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix400.txt");
			writeToFile(randomTestArray, "testMatrix400.txt");

			randomTestArray = createTestingMatrix(800);	
			increasingRandomTestArray = createIncreasingTestingMatrix(800);
			writeToFile(increasingRandomTestArray, "increasingTestMatrix800.txt");
			writeToFile(randomTestArray, "testMatrix800.txt");

		} catch (Exception e) {

		}

	}

	/**This method will print the testArray created from random numbers. */
	public static void printTestArray(int[][] testArray) {

		StringBuilder sb = new StringBuilder();

		for (int i =0; i < testArray.length; i++) {
			for (int j =0; j < testArray.length; j++) {

				if(testArray[i][j] != infiniti) {
					System.out.format("%-4d", testArray[i][j]);

				}else {
					System.out.print(" ");

				}
				System.out.print(" ");

			}
			System.out.println();

		}
	}

	/** This method will create a test matrix of size n. */
	private static int[][] createIncreasingTestingMatrix(int size){

		Random random = new Random();
		int value = 0;

		int[][] increasingTestArray = new int[size][size];

		for(int i = 0; i < size; i++) {

			for (int j = 0; j < size; j++) {

				if (i == j) {

					increasingTestArray[i][j] = 0;
				} 
				if (i > j) {
					increasingTestArray[i][j] = -1;
				} 
				if (i < j) {
					value = random.nextInt(10);

					if (value > 0) {
						increasingTestArray[i][j] = value + increasingTestArray[i][j - 1];
					}
					if (value == 0) {
						increasingTestArray[i][j] = value + increasingTestArray[i][j - 1] + 1;
					}
				}

			}
		}

		printTestArray(increasingTestArray);
		return increasingTestArray;
	}

	/** This method will create a test matrix of size n. */
	private static int[][] createTestingMatrix(int size){

		Random random = new Random();
		int value = 0;

		int[][] testArray = new int[size][size];

		for(int i = 0; i < size; i++) {

			for (int j = 0; j < size; j++) {

				if (i == j) {

					testArray[i][j] = 0;
				} 
				if (i > j) {
					testArray[i][j] = -1;
				} 
				if (i < j) {
					value = random.nextInt(10);
					if (value >= 1) {
						testArray[i][j] = value; 
					}
					if (value < 1) {
						value += random.nextInt(10) + 1;
						testArray[i][j] = value; 

					}
				}

			}
		}

		return testArray;
	}

	/** This method will get the current time. */
	private static double endTime(double start) {

		double end = System.nanoTime();
		double total = (end - start) / 1000000000.0;
		//double total = (end - start);

		System.out.println("start time: " + start);
		System.out.println("end time: " + end);
		System.out.println("Total time: " + total);
		return total;
	}

	/** This method will get the current time. */
	private static double startTime() {

		double start = System.nanoTime();
		//System.out.println(start);
		return start;
	}

	/** This method will evaluate all possibilities and select the sequence with the minimum cost.  */
	public int bruteForce(int[][] costArray) {

		// check every possible sequence and choose the cheapest

		// arraylist to store all possible path
		ArrayList<String> seq = new ArrayList<>();

		// arraylist to store all cost
		ArrayList<Integer> cost = new ArrayList<>();

		// the initial sequence 1 to n
		double initial = Math.pow(2,costArray.length-1 );

		// number of possible path
		double length = Math.pow(2,costArray.length-2 );
		// the initial sequence 1 to n
		int h = (int) (initial+1);


		// add the sequence into the arraylist
		// increment by 2
		for(int i = 0; i < length; i++) {
			seq.add(Integer.toBinaryString(h));
			h += 2;
		}

		// loop to get each cost of the sequence
		for(int i = 0; i < seq.size(); i++) {
			int temp = 0;
			int row = 0;
			for(int j = 0; j < seq.get(i).length(); j++) {

				// if the char from binary string is 1 and row is smaller than the length of the string
				if(seq.get(i).charAt(j) == '1'  && row < seq.get(i).length()) {
					// if the value is greater than 0
					if(costArray[row][j]>0) {
						// add up the cost
						temp += costArray[row][j];
					} if(j > row) { // if column is greater than row swap it
						int t = row;
						row = j;
						j = t;
					}

				} 

			}
			// add the cost into the arraylist
			cost.add(temp);
			row++;
		}
		//System.out.println("this is binary: "+ seq);
		//System.out.println("this is cost: " + cost);
		// get the cheapest cost from the list
		int cheap = Collections.min(cost);
		System.out.println("\n" + "Brute Force minimum cost: " + cheap);
		// get the index of the cheapest cost from the list
		int index = cost.indexOf(Collections.min(cost));
		//System.out.println("this is index: "+index);
		System.out.print("minimum cost sequence: ");

		// print the sequence of the path
		for(int i = 0; i < seq.get(index).length(); i++) {
			if(seq.get(index).charAt(i) == '1') {
				System.out.print(i + 1 + " ");

			}
		}
		System.out.println();
		return cheap;
	}

	/** */
	public int dynamicProgramming(int[][] costArray, int i, int j) {
		int[] mem = new int[j];
		ArrayList<Integer> seq = new ArrayList<>();
		int[] s = new int[j];

		for(int a = 0; a < j; a++) {
			int min = costArray[0][a];
			int path = 0;

			for(int b = 1; b < a; b++) {
				if(mem[b] + costArray[b][a] <min) {
					min = mem[b] + costArray[b][a] ;
					path = b;
				}	
			} 
			s[a] = path;
			mem[a] = min;

		}
		int post = j-1;
		for(int a = 0; a < j; a++) {
			if(!seq.contains(post+1)) {
				seq.add(post+1);
				post = s[post];
			}

		}
		System.out.println("\n" + "Dynamic Programming minimum cost: " + mem[j-1]);
		System.out.println("minimum cost sequence: "+ seq);
		//		System.out.println("this is mem: "+ Arrays.toString(mem));
		return mem[j-1];
	}

	/**This method will print the array created from the input file. */
	public void print2DArray(int[][] costArray) {

		StringBuilder sb = new StringBuilder();
		for (int i =0; i < costArray.length; i++) {
			for (int j =0; j < costArray.length; j++) {

				if(costArray[i][j] != infiniti) {
					System.out.format("%-4d", costArray[i][j]);
				}else {
					System.out.print(" ");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/** This method will place the contents of the input file into a 2D array. */
	public static void readLineInto2DArray(int[][] cost, int size, int counter, String line) {

		if (" ".equals(line)) {
			return;
		}
		String elements[] = line.split("\\s+");
		for (int index = 0; index < size; index++) {
			if ("NA".equals(elements[index])) {
				cost[counter][index] = -1;
			} else {
				cost[counter][index] = Integer.parseInt(elements[index]);
			}
		}
	}

	/** This method will read the specific file provided by user input. */
	public static int[][] readFile() throws FileNotFoundException {

		int size = 0;

		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the file name: ");
		String fileName = sc.nextLine();

		File file = new File(fileName);

		Scanner input = new Scanner(file);
		ArrayList<Integer> temp = new ArrayList<>();

		// check the very first line to count the number of columns
		if (!input.hasNextLine()) {
			System.err.println("Oops! No line at all");
			System.exit(0);
		}

		String line = input.nextLine();
		String elements[] = line.split("\\s+");
		size = elements.length;

		int counter = 0;
		int cost[][] = new int[size][size];
		readLineInto2DArray(cost, size, counter, line);

		while(input.hasNextLine()) {
			readLineInto2DArray(cost, size, ++counter, input.nextLine());
		}

		sc.close();
		input.close();
		return cost;
	}

}




