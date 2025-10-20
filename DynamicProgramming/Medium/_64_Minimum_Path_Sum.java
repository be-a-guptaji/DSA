/*
LeetCode Problem: https://leetcode.com/problems/minimum-path-sum/

Question: 64. Minimum Path Sum

Problem Statement: Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
*/

/*
Approach: We use **Bottom-Up Dynamic Programming** to solve the Minimum Path Sum problem.

1. The goal is to move from the top-left cell `(0,0)` to the bottom-right cell `(m-1,n-1)` of a 2D grid.
2. From each cell, we can only move either **right** or **down**.
3. We create a DP table `dp[m][n]` where each `dp[i][j]` stores the minimum path sum to reach cell `(i,j)` from the bottom-right corner.
4. Start filling from the bottom-right corner:
   - Base case: `dp[m-1][n-1] = grid[m-1][n-1]`
   - Fill the last row and last column (only one direction possible).
   - For all other cells: `dp[i][j] = grid[i][j] + min(dp[i+1][j], dp[i][j+1])`
5. The answer is in `dp[0][0]`, representing the minimum path sum from top-left to bottom-right.

Time Complexity: O(m × n), where m = rows, n = columns.
Space Complexity: O(m × n), for the DP table.
*/

package DynamicProgramming.Medium;

public class _64_Minimum_Path_Sum {
  // Method to find the minimum path sum from top-left to bottom-right
  public static int minPathSum(int[][] grid) {
    // Get the length of both the text
    int m = grid.length, n = grid[0].length;

    // Initialize the dp matrix
    int[][] dp = new int[m][n];

    // Base case bottom-right cell
    dp[m - 1][n - 1] = grid[m - 1][n - 1];

    // Fill last row of matrix
    for (int j = n - 2; j >= 0; j--) {
      dp[m - 1][j] = dp[m - 1][j + 1] + grid[m - 1][j];
    }

    // Fill last column of matrix
    for (int i = m - 2; i >= 0; i--) {
      dp[i][n - 1] = dp[i + 1][n - 1] + grid[i][n - 1];
    }

    // Find the minimum path sum from top-left to bottom-right in bottom up manner
    for (int i = m - 2; i >= 0; i--) {
      for (int j = n - 2; j >= 0; j--) {
        dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
      }
    }

    // Retrun the first value of the matrix
    return dp[0][0];
  }

  // Main method to test minPathSum
  public static void main(String[] args) {
    int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 } };

    int result = minPathSum(grid);

    System.out.println("The minimum path sum from top-left to bottom-right is : " + result);
  }
}
