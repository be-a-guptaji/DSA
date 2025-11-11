/*
LeetCode Problem: https://leetcode.com/problems/n-queens/

Question: 51. N-Queens

Problem Statement: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

Example 1:
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

Example 2:
Input: n = 1
Output: [["Q"]]

Constraints:

1 <= n <= 9
 */

/*
Approach:
1. We are given an integer `n` representing the size of an `n x n` chessboard, and we must place `n` queens on the board 
   such that no two queens attack each other — meaning:
      • No two queens share the same row.
      • No two queens share the same column.
      • No two queens share the same diagonal.

2. This problem is solved using **backtracking** — exploring all possible queen placements row by row 
   while discarding invalid configurations early (pruning).

3. Key idea:
   - Each row can contain exactly one queen.
   - We maintain three boolean arrays to track whether a column or diagonal is already under attack:
       • `col[c]` → true if a queen already occupies column `c`.
       • `diag1[row - c + n]` → true if the main diagonal is attacked (top-left to bottom-right).
       • `diag2[row + c]` → true if the anti-diagonal is attacked (top-right to bottom-left).
   - Using these arrays allows O(1) time conflict checks instead of scanning the board.

4. Algorithm steps:
   • Start from the first row (row = 0).
   • Try placing a queen in each column of the current row.
       - If the column and both diagonals are safe (not true), place a queen (‘Q’).
       - Mark the corresponding column and diagonals as occupied.
       - Recursively move to the next row.
       - After returning, remove the queen and unmark the trackers (backtrack).
   • When all `n` rows are filled, the current board configuration represents a valid solution.

5. For invalid board sizes:
   - `n = 2` and `n = 3` have no valid solutions, so return an empty list.

6. The helper method `makeList(board)` converts the current board configuration 
   into a list of strings to store in the final result.

7. This ensures that:
   - Every unique valid queen placement is found.
   - Conflicts are efficiently avoided using O(1) lookups.
   - The recursion explores only feasible states.

Time Complexity: O(N!) → Each row can have up to N choices, but pruning reduces actual calls.
Space Complexity: O(N) → For recursion stack and column/diagonal tracking arrays.
*/

package Backtracking.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _51_N_Queens {
    // Method to find valid arrangement of the queens in n X n board
    public static List<List<String>> solveNQueens(int n) {
        // Initialize the list for the result and returning
        List<List<String>> result = new ArrayList<>();

        // Return empty list for the n == 2 and n == 3
        if (n == 2 || n == 3) {
            return result;
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
        backtrack(0, n, board, col, diag1, diag2, result);

        // Retrun the result
        return result;
    }

    // Helper method to find the arrangement of the queens
    private static void backtrack(int row, int n, char[][] board, boolean[] col, boolean[] diag1, boolean[] diag2,
            List<List<String>> list) {
        // If row goes out of bound then return
        if (row == n) {
            list.add(makeList(board));
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
            backtrack(row + 1, n, board, col, diag1, diag2, list);

            // Remove the queen form the cell for backtracking
            board[row][c] = '.';

            // Mark the column, diagonal and anti diagonal as false for backtracking
            col[c] = diag1[row - c + n] = diag2[row + c] = false;
        }
    }

    // Helper method to convert the board into list of string
    private static List<String> makeList(char[][] board) {
        // Make new list for the row
        List<String> list = new ArrayList<>();

        // Iterate over the board and add the row as a string
        for (char[] row : board) {
            list.add(new String(row));
        }

        // Return the list
        return list;
    }

    // Main method to test solveNQueens
    public static void main(String[] args) {
        int n = 4;

        List<List<String>> result = solveNQueens(n);

        System.out.println("The valid arrangement of queens in " + n + " X " + n + " board is : " + result);
    }
}
