/*
LeetCode Problem: https://leetcode.com/problems/as-far-from-land-as-possible/

Question: 1162. As Far from Land as Possible

Problem Statement: Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

Example 1:
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/

/*
Approach: Two-pass DP for Multi-source Manhattan Distance
Goal:
- Find the sea cell (0) with the maximum Manhattan
  distance to its nearest land cell (1), or return
  -1 if no sea or no land exists.
Core Idea:
- Manhattan distance to the nearest land is
  computed without BFS via two directional passes:
  - Pass 1 (top-left to bottom-right): propagate
    minimum distances from up and left neighbors.
  - Pass 2 (bottom-right to top-left): propagate
    from down and right neighbors and track max.
- INF = N * N is a safe sentinel: the maximum
  possible Manhattan distance on an N x N grid is
  2 * (N - 1) < N * N, so INF is never a valid
  distance and adding 1 to it never overflows int.
Algorithm Steps:
1. Forward pass (r: 0..N-1, c: 0..N-1):
   - Skip land cells.
   - Initialize sea cells to INF.
   - Relax from (r-1, c) and (r, c-1) if in bounds.
2. Backward pass (r: N-1..0, c: N-1..0):
   - Skip land cells.
   - Relax from (r+1, c) and (r, c+1) if in bounds.
   - Update result = max(result, grid[r][c]).
3. Return -1 if result >= INF (all sea or all land);
   otherwise return result - 1 (land cells hold 1
   as their base, so true distance is stored + 1).
Time Complexity:
- O(n^2) — two full grid passes.
Space Complexity:
- O(1) — in-place on the grid.
Result:
- Returns the maximum nearest-land Manhattan
  distance among all sea cells, or -1 if invalid.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Method to find the Manhattan distance
  public int maxDistance(int[][] grid) {
    // Initialize the length
    final int N = grid.length;

    // Iterate over the grid
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        // If grid cell is one then continue
        if (grid[r][c] == 1) {
          continue;
        }

        // Set the value to the max value
        grid[r][c] = Integer.MAX_VALUE;

        // Update the grid cell
        if (r > 0) {
          grid[r][c] = Math.min(grid[r][c], grid[r - 1][c] + 1);
        }
        if (c > 0) {
          grid[r][c] = Math.min(grid[r][c], grid[r][c - 1] + 1);
        }
      }
    }

    // Initialize the result variable
    int result = 0;

    // Iterate over the grid
    for (int r = N - 1; r >= 0; r--) {
      for (int c = N - 1; c >= 0; c--) {
        // If grid cell is one then continue
        if (grid[r][c] == 1) {
          continue;
        }

        // Update the grid
        if (r < N - 1) {
          grid[r][c] = Math.min(grid[r][c], grid[r + 1][c] + 1);
        }
        if (c < N - 1) {
          grid[r][c] = Math.min(grid[r][c], grid[r][c + 1] + 1);
        }

        // Update the result
        result = Math.max(result, grid[r][c]);
      }
    }

    // Return the result
    return result < Integer.MAX_VALUE ? result - 1 : -1;
  }
}

public class _1162_As_Far_from_Land_as_Possible {
  // Main method to test maxDistance
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } };

    int result = new Solution().maxDistance(grid);

    System.out.println("The Manhattan distance is : " + result);
  }
}
