/*
 * Assignment03
 * Name:Ali Abdullah Ahmad
 * CWID: 20031246
 */

import java.util.Arrays;

public class Assignment03 {

  //METHOD FOR 1D ARRAY

  /**
     * Searches for a target element in a 1D array.
     *
     * @param arr    the array to search
     * @param target the element to search for
     * @return the index of the target element if found, otherwise -1
     */
  public static int search(int[] arr, int target) {
    for (int i = 0; i < arr.length ; i++) {
        if (target == arr[i]) {
          return i;
        }
      }
    return -1;
    }
  

    
  //METHOD FOR 2D ARRAY
  /**
   * Searches for a target element in a 2D array using a single loop.
   *
   * @param matrix the 2D array to search
   * @param target the element to search for
   * @return an array containing the row and column indices of the target if found;
   *         otherwise, an empty array
   */
  public static int[] search(int[][] matrix, int target) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    for (int i = 0; i < rows * cols; i++) {
        int row = i / cols;
        int col = i % cols;
        if (matrix[row][col] == target) {
            return new int[] { row, col }; // Return position as [row, column]
        }
    }
    
    return new int[] {-1,-1}; 
  }

  //METHOD FOR SUM OF COLUMN
  /**
   * Calculates the sum of each column in a 2D array.
   *
   * @param matrix the 2D array for which column sums are calculated
   * @return an array where each element represents the sum of a column
   */
  public static int[] sumColumn(int[][] matrix) {
    int[] columnSums = new int[matrix[0].length]; // Array to hold sums of each column
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        columnSums[j] += matrix[i][j];
      }
    }
    return columnSums;
  }

  //METHOS FOR SUM OF ROWS
  /**
   * Calculates the sum of each row in a 2D array.
   *
   * @param matrix the 2D array for which row sums are calculated
   * @return an array where each element represents the sum of a row
   */
  public static int[] sumRow(int[][] matrix) {
    int[] rowSums = new int[matrix.length]; // Array to hold sums of each row
      for (int i = 0; i < matrix.length; i++) {
        int sum = 0;
        for (int j = 0; j < matrix[i].length; j++) {
          sum += matrix[i][j];
        }
        rowSums[i] = sum;
      }
    return rowSums;
  }

  public static void main(String[] args) {
    
    //TEST CASES FOR 1D ARRAY
    int[] to_pass = {5,4,3,2,1};
    int targetID = 1;
    int index = search(to_pass, targetID);
    System.out.println("Index of " + targetID + " in array: " + index);
    int targetID2 = 6;
    int index2 = search(to_pass, targetID2);
    System.out.println("Index of " + targetID2 + " in array: " + index2);

    // Testing search in 2D array
      int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
      };
      int target2D = 9;
      int[] position = search(matrix, target2D);
      System.out.println("Position of " + target2D + " in matrix: " + Arrays.toString(position)); 

        
      int target2D2 = 0;
      int[] position2 = search(matrix, target2D2);
      System.out.println("Position of " + target2D2 + " in matrix: " + Arrays.toString(position2)); 

    // Testing sum of each column
    int[] columnSums = sumColumn(matrix);
    System.out.println("Sum of each column: " + Arrays.toString(columnSums)); // Expected: [12, 15, 18]

    // Testing sum of each row
    int[] rowSums = sumRow(matrix);
    System.out.println("Sum of each row: " +Arrays.toString(rowSums)); // Expected: [6, 15, 24]

  }
}