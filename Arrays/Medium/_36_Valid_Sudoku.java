/*
LeetCode Problem: https://leetcode.com/problems/valid-sudoku/

Question: 36. Valid Sudoku

Problem Statement: Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.

Example 1:
Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true

Example 2:
Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
*/

/*
Approach:
1. A Sudoku board is valid only if:
   • Every row contains digits 1–9 without repetition.
   • Every column contains digits 1–9 without repetition.
   • Every 3×3 sub-box contains digits 1–9 without repetition.

2. To validate the board, we check three things separately:
   • All 3×3 sub-boxes
   • All rows
   • All columns

3. For checking each 3×3 sub-box:
   • We loop `k` from 0 to 8 to represent each sub-box.
   • The starting row is calculated using:
        boxRow = (k / 3) * 3
   • The starting column is calculated using:
        boxCol = (k % 3) * 3
   • We scan the entire 3×3 region using two nested loops.
   • An integer array `set[9]` is used to mark whether a digit is already seen.
   • If any digit repeats, we immediately return false.

4. For checking each row:
   • We iterate through each row from 0 to 8.
   • Again, we use an integer array `set[9]` to track seen digits.
   • If any number repeats in a row, we return false.

5. For checking each column:
   • We iterate through each column from 0 to 8.
   • We use another `set[9]` array for tracking.
   • If any number repeats in a column, we return false.

6. If all sub-boxes, rows, and columns pass without duplicates:
   • The Sudoku board is valid, so we return true.

Time Complexity: O(1) because the board size is fixed at 9×9.
Space Complexity: O(1) because we only use fixed-size arrays of length 9.
*/

package Arrays.Medium;

public class _36_Valid_Sudoku {
  // Method to determine if the sudoku is valid or not
  public static boolean isValidSudoku(char[][] board) {
    // Check all 3x3 sub-boxes
    for (int k = 0; k < 9; k++) {

      // Make the array to track the numbers present in the current 3x3 box
      int[] set = new int[9];

      // Get the starting row index of the 3x3 sub-box
      int boxRow = (k / 3) * 3;

      // Get the starting column index of the 3x3 sub-box
      int boxCol = (k % 3) * 3;

      // Traverse the 3x3 sub-box
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {

          // Get the character at the current cell
          char ch = board[boxRow + i][boxCol + j];

          // Skip the iteration if the cell is empty
          if (ch == '.') {
            continue;
          }

          // Get the zero-based index of the number
          int index = ch - '1';

          // If the number is already present then return false
          if (set[index] > 0) {
            return false;
          } else {
            // Mark that number as visited
            set[index]++;
          }
        }
      }
    }

    // Check all rows
    for (int i = 0; i < 9; i++) {

      // Make the array to track the numbers present in the row
      int[] set = new int[9];

      for (int j = 0; j < 9; j++) {

        // Get the character at row i and column j
        char ch = board[i][j];

        // Skip if the cell is empty
        if (ch == '.') {
          continue;
        }

        // Get the zero-based index of the number
        int index = ch - '1';

        // If the number is already present then return false
        if (set[index] > 0) {
          return false;
        } else {
          // Mark that number as visited
          set[index]++;
        }
      }
    }

    // Check all columns
    for (int i = 0; i < 9; i++) {

      // Make the array to track the numbers present in the column
      int[] set = new int[9];

      for (int j = 0; j < 9; j++) {

        // Get the character at row j and column i
        char ch = board[j][i];

        // Skip if the cell is empty
        if (ch == '.') {
          continue;
        }

        // Get the zero-based index of the number
        int index = ch - '1';

        // If the number is already present then return false
        if (set[index] > 0) {
          return false;
        } else {
          // Mark that number as visited
          set[index]++;
        }
      }
    }

    // Return true if no duplicates are found
    return true;
  }

  // Main method to test dailyTemperatures
  public static void main(String[] args) {
    char[][] board = {
        { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };

    System.out.println("The given sudoku is " + (isValidSudoku(board) ? "a" : "not a") + " valid sudoku.");
  }
}
