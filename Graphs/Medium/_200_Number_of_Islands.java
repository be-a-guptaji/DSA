/*
LeetCode Problem: https://leetcode.com/problems/number-of-islands/

Question: 200. Number of Islands

Problem Statement: Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */

/*
Approach:
1. Initialize a counter result = 0 to store the number of islands.
2. Traverse each cell of the grid:
   - If the current cell contains '1' (land), it means a new island is found.
   - Increment result by 1.
   - Call DFS to explore and mark the entire connected component of this island.
3. DFS Function:
   - If the cell is out of bounds or the value is '0', return (stop recursion).
   - Otherwise, set the current cell to '0' to mark it as visited.
   - Recursively call DFS on all four neighbors:
       - Up    (row - 1, col)
       - Down  (row + 1, col)
       - Left  (row, col - 1)
       - Right (row, col + 1)
4. This ensures that all land cells belonging to the same island are marked visited 
   before moving on.
5. Once the full grid has been scanned, return result as the number of islands.

Time Complexity: O(m * n), where m and n are the grid dimensions (each cell visited at most once).  
Space Complexity: O(m * n) in the worst case due to the recursion stack.
*/

package Graphs.Medium;

public class _200_Number_of_Islands {
    // Method to find the number of island in the grid
    public static int numIslands(char[][] grid) {
        // Initialize a variable for the result
        int result = 0;

        // Loop over all the grid and start a DFS on the '1's
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == '1') {
                    // Call the dfs fucntion to find the all consecutive '1's
                    dfs(grid, row, column);

                    // Increment the value of the result if the value is '1'
                    result++;
                }
            }
        }

        // Return the result
        return result;
    }

    // Helper Function to find the consecutive '1's
    private static void dfs(char[][] grid, int row, int column) {
        // If the grid is out of bound or element is '0' then return
        if (row < 0 || column < 0 || row >= grid.length || column >= grid[0].length || grid[row][column] == '0'){
            return;
        }

        // Set the element to the '0'
        grid[row][column] = '0';

        // Explore all 4 directions
        dfs(grid, row + 1, column); // down
        dfs(grid, row - 1, column); // up
        dfs(grid, row, column + 1); // right
        dfs(grid, row, column - 1); // left
    }

    // Main method to test numIslands
    public static void main(String[] args) {
        char[][] grid = {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' }
        };

        int result = numIslands(grid);

        System.out.print("The number of island in the grid is : " + result);
    }
}
