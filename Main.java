/**
 * This code sorts a 2d array, and find and remove duplicates. Also handles any exceptions
 * @authors: Dapinderdeep Kaur, Frankie Gonzalez, Michael Jimenez
 * @version 1.0
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main
{
	/**
	 * A method that find all duplicate numbers of a 2d-array
	 * @param values: inputs 2d array
	 * @param dupWriter: inputs a Printwriter to output data
	 * @return duplicates: returns array once dupliactes have been found
	 */
	public static int[] duplicateCheck(int [] [] values, PrintWriter dupWriter) {
		int duplicates [] = new int[100];
		int check;
		//counter for number of duplicates found
		int track = 0;
		//loops to assign value to check (value whose duplicates to look at)
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				check = values[i][j];
				for (int k = i; k < values.length; k++) {
					int start;
					//initializing start
					if(i == k) {
						start = j;
					}
					else {
						start = 0;
					}
					
                    // Loop through elements in the current row 'k' starting from 'start' index 
					for (int l = start; l < values[k].length ; l++) {
						if( !(i==k && j ==l) && check == values[k][l]) {

							boolean exist = false;
                            //iterating through all elements of duplicate
							for(int n = 0; n < track; n++) {
								if(duplicates[n] == check) {
									exist = true;
									break;
								}
							}
							
							//adding the value of check to array if duplicate don't exist already
							if(!exist) {
								duplicates[track] = check;
								track++;
								break;
							}
						}
					}
				}
			}
		}
        //prints duplicate info to output file
		for (int d = 0; d < track; d++) {

			int val = duplicates[d];
			dupWriter.print("Duplicates of " + val + " found at positions: ");
            // loop over entire array to find all positions of val
			for (int i = 0; i < values.length; i++) {
				for (int j = 0; j < values[i].length; j++) {
					if (values[i][j] == val) {
						dupWriter.printf("(%d, %d) ", i+1, j+1);
					}
				}
			}
			dupWriter.println();
		}
		
        //throws an exception if no duplicates were found
		if(track == 0) {
			throw new IllegalArgumentException("There is no duplicate number in the given array");
		}
		return duplicates;
	}

	/**
	 * A method that removes any duplicate numbers of a 2d-array
	 * @param mat: inputs 2d array
	 * @param dupholder: inputs array after going through duplicateCheck
	 * @param dupWriter: inputs a Printwriter to output data
	 */
	public static void removeduplicates(int [][] mat, int[] dupholder, PrintWriter dupWriter) {
		int [] dupcounter = new int [dupholder.length];
		dupWriter.println("\nHere is the matrix after removing all duplicate values:");
		//goes through entire array and checks if it is a known duplicate and increates count for it
		for (int row = 0; row < mat.length; row++) {
			for (int col = 0; col < mat[row].length; col++) {
				for (int dups = 0; dups < dupholder.length; dups++) {
					if (mat[row][col] == dupholder[dups]) {
						dupcounter[dups]++;
						//if it's not first appearance of element, "deletes them"
						if (dupcounter[dups] > 1) {
						     // taken as an assumed placeholder value (no such value exists in actual data)
							mat[row][col] = -9999;
						}
						break;
					}
				}
				//prints new matrix to output file
				if (mat[row][col] != -9999) {
					dupWriter.printf("%6d", mat[row][col]);
				}
			}
		}
	}

	/**
	 * A method that sorts the 2d array
	 * @param nums: inputs 2d array
	 * @param sortedWriter: inputs a Printwriter to output data
	 */
	public static void insertionSortToCsv(int [][] nums, PrintWriter sortedWriter)  {
		int totalNums = 0;
		//checks how many elements in array, disregarding empty ones
		for(int i = 0; i < nums.length; i++) {
			if (nums[i] != null) {
				totalNums += nums[i].length;
			}
		}
		//flattens 2d array into 1d array
		int [] arr1d = new int [totalNums];
		int track = 0;
		for(int i = 0; i < nums.length; i++) {
			for(int j = 0; j< nums[i].length; j++) {
				arr1d[track] = nums[i][j];
				track++;
			}
		}
        //goes through an insertion sort algorithm and prints in output file
		int n;
		int key;
		for(int j = 1; j < arr1d.length; j++) {

			key = arr1d[j];
			n = j-1;
			while( n >= 0 && arr1d[n] > key) {
				arr1d[n+1] = arr1d[n];
				n = n-1;
			}
			arr1d[n+1] = key;
		}
		for(int i = 0; i < arr1d.length; i++) {
			sortedWriter.print(arr1d[i] + " ");
		}
		sortedWriter.println();
	}


	/**
	* main method; sets up access to input file, sets up output files and performs methods
	* @param args: command line arguments
	*/
	public static void main(String[] args) throws FileNotFoundException {
	    //creates scanner and creates printwriters needed for code
		Scanner sc = null;
		PrintWriter sortedWriter = null;
		PrintWriter dupWriter = null;
		int[] duplicateholder = new int[100];
    
		try {
        File inputFile = new File("numbers.csv");

         if (!inputFile.exists()) {
                System.out.println("Error: Input file 'numbers.csv' does not exist.");
                sortedWriter = new PrintWriter(new File("sorted_num.csv"));
                dupWriter = new PrintWriter(new File("duplicated_num.txt"));
                sortedWriter.println("Error: Input file 'numbers.csv' does not exist.");
                dupWriter.println("Error: Input file 'numbers.csv' does not exist.");
                return;
            }
        
         sc = new Scanner(inputFile);
			

			int rowCount = 0;
			
			//count the number of lines in input array
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				if(!line.isEmpty()) {
					rowCount++;
				}
			}

			sc.close();

			sc = new Scanner(new File("numbers.csv"));
			int [][] arr = new int [rowCount][];

			int rowIndex = 0;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!line.isEmpty()) {

                    //replacing all commas with spaces
					line = line.replace(',', ' ');
					
					Scanner lineScanner = new Scanner(line);
                 	int colCount = 0;
					//count number of columns in a row
					while (lineScanner.hasNextInt()) {
						lineScanner.nextInt();
						colCount++;
					}

					lineScanner.close();

					arr[rowIndex] = new int[colCount];
					lineScanner = new Scanner(line);
					
					//storing the values in arr
					for (int i = 0; i < colCount; i++) {
						arr[rowIndex][i] = lineScanner.nextInt();
					}

					lineScanner.close();
					rowIndex++;
				}
			}
			sc.close();
			
			try{
			    sortedWriter = new PrintWriter("sorted_num.csv");
                dupWriter = new PrintWriter("duplicated_num.txt");
			}
            catch (FileNotFoundException e){
                throw new RuntimeException("Error: Could not create output files.");
            }
            
            //case when the input array is empty
			if (arr.length == 0) {
				sortedWriter.println("Error: input array is null. Cannot sort.");
			}
			else {
				insertionSortToCsv(arr, sortedWriter);
			}

			duplicateholder = duplicateCheck(arr, dupWriter);
			removeduplicates(arr, duplicateholder, dupWriter);

		}

		catch (IllegalArgumentException e) {
			dupWriter.println("Unexpected file error: " + e.getMessage());
		}
		
        //even if any errors occur, it will close printWriters and Scanner
		finally {
			if (sc != null) sc.close();
			if (sortedWriter != null) sortedWriter.close();
			if (dupWriter != null) dupWriter.close();
		}

	}
}