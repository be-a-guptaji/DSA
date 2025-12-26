/*
LeetCode Problem: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

Question: 329. Longest Increasing Path in a Matrix

Problem Statement: Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

Example 1:
Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Example 3:
Input: matrix = [[1]]
Output: 1

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 2^31 - 1
 */

/*
Approach: DFS + Memoization (Dynamic Programming on Graph)

Goal:
Find the length of the longest strictly increasing path in a matrix.
You may move up, down, left, or right.

Key Observations:
- Each cell is a node in a directed graph.
- There is a directed edge from cell A → B if B is adjacent and B > A.
- The graph is a DAG because values must strictly increase.

DP Definition:
- dp[i][j] = length of the longest increasing path starting from cell (i, j).

Algorithm:
1. Initialize a dp matrix with 0 (uncomputed).
2. For every cell (i, j):
   - Run DFS to compute the longest path starting from that cell.
3. In DFS:
   - Stop if out of bounds or current value ≤ previous value.
   - If dp[i][j] is already computed, return it.
   - Recursively explore all 4 directions.
   - dp[i][j] = 1 + max(path lengths from valid neighbors).
4. Track the global maximum across all starting cells.

Why It Works:
- DFS explores all increasing paths.
- Memoization ensures each cell is processed once.
- DAG property prevents infinite loops.

Time Complexity: O(m × n)  
Space Complexity: O(m × n)
*/

package DynamicProgramming.Hard;

public class _329_Longest_Increasing_Path_in_a_Matrix {
    // Method to find the longest increasing path in a matrix
    public static int longestIncreasingPath(int[][] matrix) {
        // Initialize the row and column of the matrix
        int row = matrix.length, column = matrix[0].length;

        // Initialize the dp matrix for the memoization
        int[][] dp = new int[row][column];

        // Initialize the maximumLength of the path
        int maximumLength = 0;

        // Iterate over the matrix to find the maximum length
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // Update the maximum length of the path in the matrix by recursive dfs call
                maximumLength = Math.max(maximumLength, dfs(matrix, dp, -1, i, j, row, column));
            }
        }

        // Return the maximumLength variable
        return maximumLength;
    }

    // Helper method to find the maximum length
    private static int dfs(int[][] matrix, int[][] dp, int previousNumber, int i, int j, int row,
            int column) {
        // If i and j is out of bound then return zero
        if (i == -1 || j == -1 || i == row || j == column) {
            return 0;
        }

        // Get the current number of the cell
        int number = matrix[i][j];

        // If the number is less then the previous number then return
        if (number <= previousNumber) {
            return 0;
        }

        // If we already processed then return the result
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        // Return and fill the dp matrix of all direction
        return dp[i][j] = Math.max(Math.max(dfs(matrix, dp, number, i + 1, j, row, column),
                dfs(matrix, dp, number, i - 1, j, row, column)) + 1,
                Math.max(dfs(matrix, dp, number, i, j + 1, row, column),
                        dfs(matrix, dp, number, i, j - 1, row, column)) + 1);
    }

    // Main method to test longestIncreasingPath
    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };

        int result = longestIncreasingPath(matrix);

        System.out.println("The longest increasing path in a matrix is : " + result);
    }
}
