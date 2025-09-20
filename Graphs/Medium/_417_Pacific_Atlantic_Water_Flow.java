/*
LeetCode Problem: https://leetcode.com/problems/pacific-atlantic-water-flow/

Question: 417. Pacific Atlantic Water Flow

Problem Statement: There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

Example 1:
Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.

Example 2:
Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.

Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 10^5
 */

/*
Approach:
1. The problem is reversed: instead of checking if water from a cell can reach the oceans,
   we check from the oceans which cells water can flow into.
   - Water can only flow from higher/equal height to lower/equal height.
2. Create two boolean matrices:
   - pacific[r][c] = true if the cell (r, c) can reach the Pacific Ocean.
   - atlantic[r][c] = true if the cell (r, c) can reach the Atlantic Ocean.
3. Perform DFS/BFS starting from:
   - Pacific borders (top row and left column).
   - Atlantic borders (bottom row and right column).
   During traversal, move from the current cell to its neighbors if the neighbor's
   height >= current cell height (reverse flow condition).
4. After traversals, iterate through all cells:
   - If a cell is reachable by both oceans (pacific[r][c] && atlantic[r][c]),
     add it to the result list.
5. Return the list of all such cells.

Time Complexity:
- O(m * n), where m and n are the grid dimensions (each cell is visited at most twice).
Space Complexity:
- O(m * n) for the two visited arrays and recursion stack (DFS).
- Can be O(m * n) without recursion depth if BFS is used instead.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _417_Pacific_Atlantic_Water_Flow {
    // Directions for traversal (up, down, left, right)
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // Method to find cells which can reach both the pacific and atlaintic oceans
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Get the rows and columns of the grid
        int rows = heights.length;
        int cols = heights[0].length;

        // Visited arrays for pacific and atlantic
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Start DFS from Pacific Ocean borders (top and left)
        for (int i = 0; i < cols; i++) {
            dfs(heights, 0, i, pacific); // top row
            dfs(heights, rows - 1, i, atlantic); // bottom row
        }
        for (int i = 0; i < rows; i++) {
            dfs(heights, i, 0, pacific); // left column
            dfs(heights, i, cols - 1, atlantic); // right column
        }

        // Find intersection of cells reachable by both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        // Retrun the result
        return result;
    }

    // Helper function to find the covered cells
    private static void dfs(int[][] heights, int r, int c, boolean[][] visited) {
        // Get the rows and columns of the grid
        int rows = heights.length, cols = heights[0].length;

        // Mark the cell as visited
        visited[r][c] = true;

        // Make recursive call in all direction
        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Skip invalid or already visited cells
            if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) {
                continue;
            }
            if (visited[nr][nc]) {
                continue;
            }
            if (heights[nr][nc] < heights[r][c]) {
                continue;
            }

            // Make a recursive call
            dfs(heights, nr, nc, visited);
        }
    }

    // Main method to test pacificAtlantic
    public static void main(String[] args) {
        int[][] heights = {
                { 1, 2, 2, 3, 5 },
                { 3, 2, 3, 4, 4 },
                { 2, 4, 5, 3, 1 },
                { 6, 7, 1, 4, 5 },
                { 5, 1, 1, 2, 4 }
        };

        List<List<Integer>> result = pacificAtlantic(heights);

        System.out.print("The result of the following queries are : " + result);
    }
}