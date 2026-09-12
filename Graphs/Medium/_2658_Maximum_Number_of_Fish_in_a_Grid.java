/*
LeetCode Problem: https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/

Question: 2658. Maximum Number of Fish in a Grid

Problem Statement: You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

A land cell if grid[r][c] = 0, or
A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

Catch all the fish at cell (r, c), or
Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

Example 1:
Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
Output: 7
Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.

Example 2:
Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
Output: 1
Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish. 

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10
0 <= grid[i][j] <= 10
*/

/*
Approach: DFS Connected Component Fish Accumulation
Goal:
- Find the maximum total fish collectable from any
  single connected water region, using 4-directional
  connectivity.
Core Idea:
- Each connected group of water cells (grid[r][c]
  > 0) forms an independent region. The total fish
  in a region is the sum of all cell values within
  it.
- DFS from any unvisited water cell accumulates
  the total fish in its connected component,
  marking visited cells by zeroing them to prevent
  revisits.
- The answer is the maximum total across all
  components.
Algorithm Steps:
1. For each cell (row, col) with grid[row][col] > 0:
   - Call dfs(row, col) and update maxFish with the
     returned total.
2. In dfs(row, col):
   a. Return 0 if out of bounds or cell is 0 (land
      or already visited).
   b. Store grid[row][col] as totalFish and set
      grid[row][col] = 0 (mark visited).
   c. Return totalFish + dfs in all four directions.
3. Return maxFish.
Why It Works:
- Zeroing visited cells prevents double-counting
  and eliminates the need for a separate visited
  array.
- DFS naturally accumulates the sum of all
  reachable water cells in a single traversal,
  since every recursive call returns its cell's
  fish count before exploring further.
Time Complexity:
- O(m * n)
where m and n are the grid dimensions, since each
cell is visited at most once.
Space Complexity:
- O(m * n)
for the recursive call stack in the worst case of
a fully connected water grid.
Result:
- Returns the maximum fish collectable from any
  optimally chosen starting water cell.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Method to find the maximum number of fish the fisher can catch if he chooses
  // his starting cell optimally
  public int findMaxFish(int[][] grid) {
    // Initialize the maxFish variable
    int maxFish = 0;

    // Iterate over the grid
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        // If the cell is land then skip it
        if (grid[row][col] != 0) {
          // Call the recursive dfs method
          maxFish = Math.max(maxFish, this.dfs(grid, row, col));
        }
      }
    }

    // Return the maxFish
    return maxFish;
  }

  // Helper method for the dfs
  private int dfs(int[][] grid, int row, int col) {
    // If row and col is out of bound or land then return 0
    if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1 || grid[row][col] == 0) {
      return 0;
    }

    // Initialize the totalFish variable
    int totalFish = grid[row][col];

    // Set the grid cell to zero
    grid[row][col] = 0;

    // Call the recursive dfs call
    return this.dfs(grid, row + 1, col) + this.dfs(grid, row - 1, col) + this.dfs(grid, row, col + 1)
        + this.dfs(grid, row, col - 1) + totalFish;
  }
}

public class _2658_Maximum_Number_of_Fish_in_a_Grid {
  // Main method to test findMaxFish
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 0, 2, 1, 0 }, { 4, 0, 0, 3 }, { 1, 0, 0, 4 }, { 0, 3, 2, 0 } };

    int result = new Solution().findMaxFish(grid);

    System.out.println(
        "The maximum number of fish the fisher can catch if he chooses his starting cell optimally is : " + result);
  }
}
