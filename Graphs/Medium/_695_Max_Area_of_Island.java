/*
LeetCode Problem: https://leetcode.com/problems/max-area-of-island/

Question: 695. Max Area of Island

Problem Statement: You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

Example 1:
Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

Example 2:
Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
*/

/*
Approach: DFS Flood-Fill to Compute Island Areas

We traverse the grid to find connected components of 1’s, where each component
represents an island. For each island, we perform DFS to calculate its area by
counting all connected land cells.

Algorithm:
1. Iterate over every cell in the grid.
2. When a cell contains 1:
   - Reset currentArea = 0.
   - Perform DFS starting from that cell.
   - DFS:
     • Return if out of bounds or on a water cell (0).
     • Mark the current cell as visited by setting it to 0.
     • Increment currentArea.
     • Explore all four directions (up, down, left, right).
3. After each DFS, update maximumAreaOfIsland with the largest recorded area.

Why This Works:
- DFS collects all connected land cells and counts them.
- Setting visited cells to 0 prevents revisiting.
- Each island is computed exactly once.

Time Complexity: O(m × n)  
Space Complexity: O(m × n) in worst case (recursion depth)
*/

package Graphs.Medium;

public class _695_Max_Area_of_Island {

    // Initialize the variable to find the current area of the island
    private static int currentArea;

    // Method to find the maximum area of an island in grid
    public static int maxAreaOfIsland(int[][] grid) {
        // Initialize the area for the maximum area of the island
        int maximumAreaOfIsland = 0;

        // Get the row and column of the grid
        int row = grid.length, column = grid[0].length;

        // Iterate over the grid to find the area of the island
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // If island is found do a dfs search
                if (grid[i][j] == 1) {
                    // Initialize current area to zero
                    currentArea = 0;

                    // Do a dfs search
                    dfs(grid, i, j);

                    // Update the maximum area of island
                    maximumAreaOfIsland = Math.max(maximumAreaOfIsland, currentArea);
                }
            }
        }

        // Return the maximum area of island
        return maximumAreaOfIsland;
    }

    // Helper method to find the area of island
    private static void dfs(int[][] grid, int row, int column) {
        // If the grid is out of bound or element is '0' then return
        if (row < 0 || column < 0 || row >= grid.length || column >= grid[0].length || grid[row][column] == 0) {
            return;
        }

        // Set the element to the zero
        grid[row][column] = 0;

        // Update the current area by one
        currentArea++;

        // Explore all 4 directions
        dfs(grid, row + 1, column); // down
        dfs(grid, row - 1, column); // up
        dfs(grid, row, column + 1); // right
        dfs(grid, row, column - 1); // left
    }

    // Main method to test maxAreaOfIsland
    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }
        };

        int result = maxAreaOfIsland(grid);

        System.out.print("The maximum area of an island in grid is : " + result);
    }
}
