/*
LeetCode Problem: https://leetcode.com/problems/number-of-enclaves/

Question: 1020. Number of Enclaves

Problem Statement: You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

Example 1:
Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

Example 2:
Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.
*/

/*
Approach: Boundary DFS Elimination then Interior Count
Goal:
- Count land cells (1s) that cannot reach the grid
  boundary through any sequence of 4-directional
  moves on land cells.
Core Idea:
- Any land cell connected to the boundary is not
  an enclave. Flood-fill all boundary-connected
  land components to 0 first, then count remaining
  1s (guaranteed enclaves).
Algorithm Steps:
1. DFS from every boundary cell (top/bottom rows,
   left/right cols); zero out all reachable land.
2. Count remaining 1s in the interior.
3. Return count.
Why It Works:
- Zeroing boundary-reachable cells in-place
  avoids a separate visited array.
- Any 1 remaining after the boundary sweep has
  no path to the boundary, making it a valid
  enclave by definition.
Time Complexity:
- O(m * n) — each cell visited at most once.
Space Complexity:
- O(m * n) for the call stack in the worst case.
Result:
- Returns the count of enclave land cells.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Initialize the ROWS and COLS variable
  private int ROWS;
  private int COLS;

  // Method to find the number of land cells in grid for which we cannot walk off
  // the boundary of the grid in any number of moves
  public int numEnclaves(int[][] grid) {
    // Initialize the rows and cols variable
    this.ROWS = grid.length;
    this.COLS = grid[0].length;

    // Iterate over the boundary
    for (int i = 0; i < this.COLS; i++) {
      this.dfs(0, i, grid);
      this.dfs(this.ROWS - 1, i, grid);
    }
    for (int i = 1; i < this.ROWS - 1; i++) {
      this.dfs(i, 0, grid);
      this.dfs(i, this.COLS - 1, grid);
    }

    // Initialize the result variable
    int result = 0;

    // Iterate over the grid and if find 1 then increment the result variable
    for (int row = 0; row < this.ROWS; row++) {
      for (int col = 0; col < this.COLS; col++) {
        if (grid[row][col] == 1) {
          result++;
        }
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private void dfs(int row, int col, int[][] grid) {
    // If we are out of boundary then terminate the method
    if (row < 0 || col < 0 || row == this.ROWS || col == this.COLS || grid[row][col] == 0) {
      return;
    }

    // Set the grid to 0
    grid[row][col] = 0;

    // Iterate over the all direction
    this.dfs(row + 1, col, grid);
    this.dfs(row - 1, col, grid);
    this.dfs(row, col + 1, grid);
    this.dfs(row, col - 1, grid);
  }
}

public class _1020_Number_of_Enclaves {
  // Main method to test numEnclaves
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 1, 2, 2 }, { 1, 3, 4 }, { 3, 4, 7 } };

    int result = new Solution().numEnclaves(grid);

    System.out.println(
        "The number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves is : "
            + result);
  }
}
