/*
LeetCode Problem: https://leetcode.com/problems/n-queens-ii/

Question: 52. N-Queens II

Problem Statement: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example 1:
Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

Example 2:
Input: n = 1
Output: 1

Constraints:

1 <= n <= 9
 */

/*
Approach:
1. We are given an integer `n` and must count the total number of distinct ways to place `n` queens 
   on an `n x n` chessboard so that no two queens attack each other.

2. This problem is solved using **backtracking with pruning** — similar to the N-Queens arrangement problem,
   but instead of returning all valid boards, we simply count them.

3. Key Idea:
   - Each queen must occupy one unique row and one unique column.
   - We maintain three boolean arrays to track attacks:
       • `col[c]` → marks if a column already contains a queen.
       • `diag1[row - col + n]` → marks if a main diagonal (↘) is under attack.
       • `diag2[row + col]` → marks if an anti-diagonal (↙) is under attack.
   - Using these arrays allows O(1) checks for attacks instead of scanning the entire board.

4. Recursive Strategy:
   • Start placing queens from row 0.
   • For each column `c` in the current row:
       - If column and diagonals are safe (`false`), place a queen.
       - Mark that column and diagonals as occupied.
       - Recurse to the next row.
       - After recursion, remove the queen and unmark to explore other configurations (backtrack).
   • When all rows are filled (`row == n`), increment the global `totalArrangement` counter.

5. Optimization:
   - For `n = 2` or `n = 3`, there are no valid queen placements, 
     so directly return 0 to save computation.

6. The board matrix is optional — used only for clarity, not required for counting.

7. This approach ensures:
   - Every unique valid configuration is counted exactly once.
   - Invalid paths are pruned early using boolean arrays.
   - Space and time are efficiently managed.

Time Complexity: O(N!) — Each row has up to N choices, but pruning significantly reduces calls.
Space Complexity: O(N) — For recursion depth and boolean tracking arrays.
*/

package Backtracking.Hard;

import java.util.Arrays;

public class _52_N_Queens_II {
    // Make a variable to find the total arrangement of the queens
    private static int totalArrangement;

    // Method to find valid arrangement of the queens in n X n board
    public static int totalNQueens(int n) {
        // Initialize the total arrangement to zero
        totalArrangement = 0;

        // Return totalArrangement for the n == 2 and n == 3
        if (n == 2 || n == 3) {
            return totalArrangement;
        }

        // Initialize the board of n X n matrix
        char[][] board = new char[n][n];

        // Fill the board with '.'
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // Columns, diagonals, and anti-diagonals trackers
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // (row - col + n)
        boolean[] diag2 = new boolean[2 * n]; // (row + col)

        // Make a recursive backtrack call to find the arrangement of queens
        backtrack(0, n, board, col, diag1, diag2);

        // Retrun the totalArrangement
        return totalArrangement;
    }

    // Helper method to find the arrangement of the queens
    private static void backtrack(int row, int n, char[][] board, boolean[] col, boolean[] diag1, boolean[] diag2) {
        // If row goes out of bound then return
        if (row == n) {
            totalArrangement++;
            return;
        }

        // Try placing the queen in each column of the current row
        for (int c = 0; c < n; c++) {
            // If the column, diagonal and anti diagonal is marked as true then continue as
            // the one queen attacks another
            if (col[c] || diag1[row - c + n] || diag2[row + c]) {
                continue;
            }

            // Mark the board cell (row, c) as Q as queen is placed there
            board[row][c] = 'Q';

            // Mark the column, diagonal and anti diagonal as true
            col[c] = diag1[row - c + n] = diag2[row + c] = true;

            // Make a recursive back track call on the next row of the chess board
            backtrack(row + 1, n, board, col, diag1, diag2);

            // Remove the queen form the cell for backtracking
            board[row][c] = '.';

            // Mark the column, diagonal and anti diagonal as false for backtracking
            col[c] = diag1[row - c + n] = diag2[row + c] = false;
        }
    }

    // Main method to test totalNQueens
    public static void main(String[] args) {
        int n = 4;

        int result = totalNQueens(n);

        System.out.println("The valid arrangement of queens in " + n + " X " + n + " board is : " + result);
    }
}
