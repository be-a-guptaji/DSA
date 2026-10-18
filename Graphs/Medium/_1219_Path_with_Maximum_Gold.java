/*
LeetCode Problem: https://leetcode.com/problems/path-with-maximum-gold/

Question: 1219. Path with Maximum Gold

Problem Statement: In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.

Example 1:
Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.

Example 2:
Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
*/

/*
Approach: Backtracking DFS with In-place Cell Zeroing
Goal:
- Find the maximum gold collectible by starting
  from any non-zero cell and moving in 4 directions
  without revisiting cells or entering zero cells.
Core Idea:
- Try starting DFS from every non-zero cell,
  collecting gold greedily along each path.
- Zero the current cell before recursing (marks
  visited) and restore it after (backtrack),
  enabling full path exploration without a
  separate visited array.
- At each step, take the maximum gold from all
  four directions plus the current cell's gold.
Algorithm Steps:
1. For each cell (row, col), call dfs(row, col).
2. In dfs(row, col):
   a. Return 0 if out of bounds or cell is 0.
   b. Store gold = grid[row][col]; zero the cell.
   c. result = max of all 4 directional DFS + gold.
   d. Restore grid[row][col] = gold (backtrack).
   e. Return result.
3. Track and return the global maximum.
Why It Works:
- Zeroing before recursion prevents revisiting the
  same cell within one path.
- Restoring after recursion allows other starting
  points or paths to use the same cell, correctly
  exploring all possible routes.
- The totalGold parameter is unused and can be
  removed; the accumulation happens implicitly via
  the return value chain.
Time Complexity:
- O(m * n * 4^(m*n)) worst case per start cell,
  bounded in practice by the number of non-zero
  cells and path constraints.
Space Complexity:
- O(m * n) for the recursive call stack depth.
Result:
- Returns the maximum gold collectible in any
  single path through the grid.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Initialize the ROWS and COLS variable
  private int ROWS;
  private int COLS;

  // Method to find the maximum amount of gold you can collect
  public int getMaximumGold(int[][] grid) {
    // Initialize the ROWS and COLS variable
    this.ROWS = grid.length;
    this.COLS = grid[0].length;

    // Initialize the result variable
    int result = 0;

    // Iterate over the grid
    for (int row = 0; row < this.ROWS; row++) {
      for (int col = 0; col < this.COLS; col++) {
        result = Math.max(result, this.dfs(grid, row, col, 0));
      }
    }

    // Return the result variable
    return result;
  }

  // Helper method for the dfs
  private int dfs(int[][] grid, int row, int col, int totalGold) {
    // Check if row and column is in bound
    if (row < 0 || col < 0 || row == this.ROWS || col == this.COLS) {
      return 0;
    }

    // Get the cell value
    int gold = grid[row][col];

    // If gold is 0 then return 0
    if (gold == 0) {
      return 0;
    }

    // Set the grid[row][col] to zero
    grid[row][col] = 0;

    // Iterate on all direction and get the result
    int result = Math.max(
        Math.max(this.dfs(grid, row + 1, col, totalGold), this.dfs(grid, row - 1, col, totalGold)),
        Math.max(this.dfs(grid, row, col + 1, totalGold), this.dfs(grid, row, col - 1, totalGold)))
        + gold;

    // Reset the gold in the cell
    grid[row][col] = gold;

    // Return the result variable
    return result;
  }
}

public class _1219_Path_with_Maximum_Gold {
  // Main method to test getMaximumGold
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 0, 6, 0 }, { 5, 8, 7 }, { 0, 9, 0 } };

    int result = new Solution().getMaximumGold(grid);

    System.out.println("The maximum amount of gold you can collect is : " + result);
  }
}
