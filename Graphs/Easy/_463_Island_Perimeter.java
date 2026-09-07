/*
LeetCode Problem: https://leetcode.com/problems/island-perimeter/

Question: 463. Island Perimeter

Problem Statement: You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example 1:
Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.

Example 2:
Input: grid = [[1]]
Output: 4

Example 3:
Input: grid = [[1,0]]
Output: 4

Constraints:
row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
*/

/*
Approach: Cell-by-cell Edge Contribution Counting
Goal:
- Compute the perimeter of the island by counting
  exposed edges of land cells.
Core Idea:
- Each land cell contributes one perimeter edge
  for every side that is either on the grid
  boundary or adjacent to a water cell (grid == 0).
- Checking all four directions per land cell and
  summing the exposed edges gives the total
  perimeter without needing DFS or BFS.
Algorithm Steps:
1. For each cell (row, col) in the grid:
   a. If grid[row][col] == 1:
      - Add 1 for each of the four directions
        (up, down, left, right) where the neighbor
        is out of bounds or is a water cell.
2. Return totalPerimeter.
Time Complexity:
- O(m * n)
where m and n are the grid dimensions, since each
cell is visited once with O(1) work.
Space Complexity:
- O(1)
no additional data structures are used.
Result:
- Returns the total perimeter of the island.
*/

package Graphs.Easy;

// Solution Class
class Solution {
  // Method to find the perimeter of the island
  public int islandPerimeter(int[][] grid) {
    // Initialize the totalPerimeter variable
    int totalPerimeter = 0;

    // Initialize the width and height
    int width = grid.length;
    int height = grid[0].length;

    // Iterate over the grid
    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        // If grid cell is 1 then calculate the totalPerimeter
        if (grid[row][col] == 1) {
          totalPerimeter += (row + 1 >= width || grid[row + 1][col] == 0) ? 1 : 0;
          totalPerimeter += (col + 1 >= height || grid[row][col + 1] == 0) ? 1 : 0;
          totalPerimeter += (row - 1 < 0 || grid[row - 1][col] == 0) ? 1 : 0;
          totalPerimeter += (col - 1 < 0 || grid[row][col - 1] == 0) ? 1 : 0;
        }
      }
    }

    // Return the totalPerimeter
    return totalPerimeter;
  }
}

public class _463_Island_Perimeter {
  // Main method to test islandPerimeter
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };

    int result = new Solution().islandPerimeter(grid);

    System.out.println("The perimeter of the island is : " + result);
  }
}
