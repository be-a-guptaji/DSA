/*
LeetCode Problem: https://leetcode.com/problems/sudoku-solver/

Question: 37. Sudoku Solver

Problem Statement: Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

Example 1:
Input: board = [
    ["5","3",".",".","7",".",".",".","."],
    ["6",".",".","1","9","5",".",".","."],
    [".","9","8",".",".",".",".","6","."],
    ["8",".",".",".","6",".",".",".","3"],
    ["4",".",".","8",".","3",".",".","1"],
    ["7",".",".",".","2",".",".",".","6"],
    [".","6",".",".",".",".","2","8","."],
    [".",".",".","4","1","9",".",".","5"],
    [".",".",".",".","8",".",".","7","9"]
]
Output: [
    ["5","3","4","6","7","8","9","1","2"],
    ["6","7","2","1","9","5","3","4","8"],
    ["1","9","8","3","4","2","5","6","7"],
    ["8","5","9","7","6","1","4","2","3"],
    ["4","2","6","8","5","3","7","9","1"],
    ["7","1","3","9","2","4","8","5","6"],
    ["9","6","1","5","3","7","2","8","4"],
    ["2","8","7","4","1","9","6","3","5"],
    ["3","4","5","2","8","6","1","7","9"]
]
Explanation: The input board is shown above and the only valid solution is shown below:

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 */

/*
Approach:
1. We are given a 9 x 9 Sudoku board that contains digits '1' to '9' and empty cells marked as '.'.
   The task is to fill all empty cells such that the Sudoku rules are satisfied:
      • Each row contains digits 1–9 with no repetition.
      • Each column contains digits 1–9 with no repetition.
      • Each 3x3 sub-box contains digits 1–9 with no repetition.

2. The problem is solved using **backtracking**, a recursive approach that explores possible digit placements
   and undoes invalid choices.

3. Algorithm Steps:
   • Traverse the board cell by cell.
   • When an empty cell ('.') is found:
       - Try placing digits from '1' to '9' one by one.
       - For each digit, use the `isValid()` method to check if it can be legally placed.
       - If valid:
           → Place the digit on the board.
           → Recursively call the solver to fill the next cells.
           → If the recursion succeeds, return true (solution found).
           → Otherwise, remove the digit (backtrack) and try the next one.
       - If no digit fits, return false to trigger backtracking to the previous cell.
   • Continue this process until the board is completely filled and valid.

4. The `isValid()` function performs the Sudoku constraint check:
   • Scans the entire row and column for duplicate digits.
   • Identifies the 3x3 box using:
       → boxRow = (row / 3) * 3
       → boxCol = (col / 3) * 3
   • Checks for the same digit inside that 3x3 box.

5. The recursion ends when:
   - The solver successfully fills all cells → return true (solved).
   - No valid number can be placed in a cell → return false (backtrack).

6. This ensures that:
   - Every possible valid configuration is explored.
   - Invalid states are pruned early using the validity check.
   - The board is solved in place without requiring extra space.

Time Complexity: O(9^(M)) → M is the number of empty cells, as each cell may try 9 possibilities.
Space Complexity: O(1) → The solution is performed in place, only recursion stack is used.
*/

package Backtracking.Hard;

import java.util.Arrays;

public class _37_Sudoku_Solver {
    // Method to solve the sudoku
    public static void solveSudoku(char[][] board) {
        // Call the recursive solve method
        solve(board);
    }

    // Helper method to solve the sudoku using backtracking
    private static boolean solve(char[][] board) {
        // Iterate over the complete board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                // If the cell is marked as '.' then try to fill it
                if (board[i][j] == '.') {

                    // Try to fill the cell with digits '1' to '9'
                    for (char digit = '1'; digit <= '9'; digit++) {

                        // Check if the digit is valid at the current position
                        if (isValid(i, j, digit, board)) {

                            // Place the digit in the cell
                            board[i][j] = digit;

                            // Recursively try to solve the rest of the board
                            if (solve(board)) {
                                return true;
                            }

                            // Backtrack if placing the digit doesn't lead to a solution
                            board[i][j] = '.';
                        }
                    }

                    // If no valid digit can be placed, return false to backtrack
                    return false;
                }
            }
        }

        // If all cells are filled correctly, return true
        return true;
    }

    // Helper method to check if the current digit placement is valid
    private static boolean isValid(int row, int col, char digit, char[][] board) {
        // Check for conflicts in the current row and column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == digit || board[i][col] == digit) {
                return false;
            }
        }

        // Get the starting index of the 3x3 sub-box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        // Check for conflicts inside the 3x3 sub-box
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRow + i][boxCol + j] == digit) {
                    return false;
                }
            }
        }

        // Return true if no conflicts are found
        return true;
    }

    // Main method to test solveSudoku
    public static void main(String[] args) {
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        solveSudoku(board);

        System.out.println("The solved sudoku is solved.");

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
