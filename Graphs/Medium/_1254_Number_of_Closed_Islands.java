/*
LeetCode Problem: https://leetcode.com/problems/number-of-closed-islands/

Question: 1254. Number of Closed Islands

Problem Statement: Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

Example 1:
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1

Example 3:
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2

Constraints:
1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
*/

/*
Approach: DFS Flood Fill with Border-touch Detection
Goal:
- Count closed islands: connected components of
  0-cells fully surrounded by 1-cells with no cell
  touching the grid boundary.
Core Idea:
- DFS from any unvisited 0-cell floods the entire
  connected component, marking visited cells as 2
  to prevent re-entry.
- A component is closed if every DFS path
  terminates at a 1-cell or a previously visited
  cell (value 2) and never hits the grid boundary.
- All four directions must be explored regardless
  of early boundary detection to fully mark the
  component as visited, preventing double-counting.
Algorithm Steps:
1. For each unvisited 0-cell (grid[r][c] == 0 and
   != 2), call dfs(r, c, grid).
2. In dfs(row, col, grid):
   a. If out of bounds, return false (boundary
      touched, not closed).
   b. If grid[row][col] == 1 or == 2, return true
      (wall or already visited, no violation here).
   c. Mark grid[row][col] = 2 (visited).
   d. Recurse in all 4 directions using &= false
      accumulation (non-short-circuit) to ensure
      the full component is marked even when a
      boundary is found.
   e. Return the accumulated result.
3. If dfs returns true, increment result.
4. Return result.
Why It Works:
- Marking cells 2 before recursing prevents
  infinite loops and ensures each cell is processed
  exactly once across all outer loop iterations.
- Collecting all four direction results without
  short-circuiting ensures the full island is
  marked visited even when one branch touches the
  boundary, preventing the remaining cells from
  being re-counted as a separate island.
- The outer loop condition (grid[r][c] == 0 && != 2) correctly skips already-processed cells.
Time Complexity:
- O(m * n)
where m and n are the grid dimensions, since each
cell is visited at most once.
Space Complexity:
- O(m * n)
for the recursive call stack in the worst case of
a fully connected 0-cell grid.
Result:
- Returns the count of closed islands.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Initialize the ROWS and COLS variable
  private int ROWS;
  private int COLS;

  // Method to find the number of closed islands
  public int closedIsland(int[][] grid) {
    // Initialize the ROWS and COLS variable
    this.ROWS = grid.length;
    this.COLS = grid[0].length;

    // Initialize the result variable
    int result = 0;

    // Iterate over the grid
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (grid[row][col] == 0 && grid[row][col] != 2) {
          if (this.dfs(row, col, grid)) {
            result++;
          }
        }
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private boolean dfs(int row, int col, int[][] grid) {
    // If we are out of the grid then return false
    if (row < 0 || col < 0 || row == this.ROWS || col == this.COLS) {
      return false;
    }

    // If grid[row][col] is not zero then return true
    if (grid[row][col] == 1 || grid[row][col] == 2) {
      return true;
    }

    // Set the grid[row][col] to two
    grid[row][col] = 2;

    // Initialize the result variable
    boolean result = true;

    // Iterate over all direction
    for (int[] direction : new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }) {
      if (!this.dfs(row + direction[0], col + direction[1], grid)) {
        result = false;
      }
    }

    // Return the result
    return result;
  }
}

public class _1254_Number_of_Closed_Islands {
  // Main method to test closedIsland
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 1, 2, 2 }, { 1, 3, 4 }, { 3, 4, 7 } };

    int result = new Solution().closedIsland(grid);

    System.out.println("The number of closed islands is : " + result);
  }
}
