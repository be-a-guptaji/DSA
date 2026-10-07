/*
LeetCode Problem: https://leetcode.com/problems/regions-cut-by-slashes/

Question: 959. Regions Cut By Slashes

Problem Statement: An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.

Example 1:
Input: grid = [" /","/ "]
Output: 2

Example 2:
Input: grid = [" /","  "]
Output: 1

Example 3:
Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.

Constraints:
n == grid.length == grid[i].length
1 <= n <= 30
grid[i][j] is either '/', '\', or ' '.
*/

/*
Approach: 3x Upscaling with DFS Region Count
Goal:
- Count the number of regions formed by '/' and
  '\' characters in an n x n grid of strings.
Core Idea:
- Each cell is expanded into a 3x3 sub-grid.
  Slash characters are drawn as diagonal walls
  of true cells within the sub-grid, creating
  physical barriers between regions.
- After upscaling, counting connected components
  of false cells in the 3n x 3n boolean matrix
  gives the exact number of regions.
- '/' fills (0,2), (1,1), (2,0) of its 3x3 block.
- '\' fills (0,0), (1,1), (2,2) of its 3x3 block.
- ' ' leaves all 9 cells false (open).
Algorithm Steps:
1. Build a 3n x 3n boolean matrix, all false.
2. For each cell in the original grid, call
   fillMatrix to mark wall cells in the 3x3 block.
3. Count connected components of false cells via
   DFS, marking visited cells true to prevent
   re-entry.
4. Return the component count.
Why It Works:
- 3x upscaling gives enough resolution for slash
  diagonals to act as solid walls without merging
  adjacent open regions.
- DFS flood-fill on the upscaled grid correctly
  identifies regions separated by these walls.
Time Complexity:
- O(n^2) — the upscaled matrix has (3n)^2 = 9n^2
  cells, each visited at most once.
Space Complexity:
- O(n^2) for the matrix and call stack.
Result:
- Returns the number of regions in the grid.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Method to find the number of regions
  public int regionsBySlashes(String[] grid) {
    // Get the length of the matrix
    int length = grid.length;

    // Make the matrix
    boolean[][] matrix = new boolean[length * 3][length * 3];

    // Iterate over the grid
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < length; col++) {
        // Get the character
        char ch = grid[row].charAt(col);

        // If character is not ' ' then fill the matrix
        if (ch != ' ') {
          this.fillMatrix(row, col, matrix, ch);
        }
      }
    }

    // Initialize the result variable
    int result = 0;

    // Iterate over the grid for the regions
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix.length; col++) {
        // If cell is false call the dfs
        if (!matrix[row][col]) {
          // Increment the result
          result++;

          // Call the dfs method
          this.dfs(row, col, matrix);
        }
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private void dfs(int row, int col, boolean[][] matrix) {
    // If we are out of bound or seen the cell terminate the method
    if (row < 0 || col < 0 || row == matrix.length || col == matrix.length || matrix[row][col]) {
      return;
    }

    // Set the cell to seen true
    matrix[row][col] = true;

    // Call the dfs
    this.dfs(row + 1, col, matrix);
    this.dfs(row - 1, col, matrix);
    this.dfs(row, col + 1, matrix);
    this.dfs(row, col - 1, matrix);
  }

  // Helper method to fill the matrix
  private void fillMatrix(int row, int col, boolean[][] matrix, char ch) {
    // Get the transform row and col
    row *= 3;
    col *= 3;

    // Set the value to the true
    if (ch == '/') {
      matrix[row][col + 2] = true;
      matrix[row + 1][col + 1] = true;
      matrix[row + 2][col] = true;
    } else if (ch == '\\') {
      matrix[row][col] = true;
      matrix[row + 1][col + 1] = true;
      matrix[row + 2][col + 2] = true;
    }
  }
}

public class _959_Regions_Cut_By_Slashes {
  // Main method to test regionsBySlashes
  public static void main(String[] args) {
    String[] grid = new String[] { "/\\", "\\/" };

    int result = new Solution().regionsBySlashes(grid);

    System.out.println("The number of regions is : " + result);
  }
}
