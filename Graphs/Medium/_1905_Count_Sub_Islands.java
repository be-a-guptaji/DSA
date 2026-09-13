/*
LeetCode Problem: https://leetcode.com/problems/count-sub-islands/

Question: 1905. Count Sub Islands

Problem Statement: You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

Example 1:
Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Example 2:
Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.

Constraints:
m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.
*/

/*
Approach: DFS Island Traversal with Grid1 Containment Check
Goal:
- Count islands in grid2 that are sub-islands:
  every land cell of the island in grid2 must also
  be a land cell in grid1.
Core Idea:
- An island in grid2 is a sub-island if and only if
  every cell in its connected component is also
  land in grid1.
- DFS explores each grid2 island fully, checking
  the grid1 containment condition at every cell.
- The result must use &= (not short-circuit &&=)
  to ensure the entire island is visited and marked
  even when a violation is found early, preventing
  re-traversal of the same cells.
Algorithm Steps:
1. For each unvisited land cell in grid2, call
   dfs(row, col) and increment count if it returns
   true.
2. In dfs(row, col):
   a. Return true if out of bounds, cell is water
      in grid2, or already visited (boundary/base
      case; no violation from this cell).
   b. Mark visit[row][col] = true.
   c. Initialize result = (grid1[row][col] == 1).
   d. Recurse in all four directions using &=
      to accumulate violations without short-
      circuiting (ensures full island traversal).
   e. Return result.
3. Return count.
Why It Works:
- Using &= instead of short-circuit && ensures DFS
  visits every cell of the island even after a
  violation is found, correctly marking all cells
  as visited so they are not recounted by the outer
  loop.
- Returning true at boundaries and water cells is
  correct: these cells impose no violation on their
  own; the violation check only applies to actual
  grid2 land cells.
- The outer loop only starts DFS from unvisited
  land cells, so each grid2 island is processed
  exactly once.
Time Complexity:
- O(m * n)
where m and n are the grid dimensions, since each
cell is visited at most once via the visit array.
Space Complexity:
- O(m * n)
for the visit array and recursive call stack in
the worst case of a fully connected island.
Result:
- Returns the number of grid2 islands that are
  fully contained within land cells of grid1.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Initialize the row, col and visit
  private int ROWS, COLS;
  private boolean[][] visit;

  // Method to find the number of islands in grid2 that are considered sub-islands
  public int countSubIslands(int[][] grid1, int[][] grid2) {
    // Set the row, col and visit
    this.ROWS = grid1.length;
    this.COLS = grid1[0].length;
    this.visit = new boolean[ROWS][COLS];

    // Initialize the count variable
    int count = 0;

    // Iterate over the grid
    for (int row = 0; row < this.ROWS; row++) {
      for (int col = 0; col < this.COLS; col++) {
        if (grid2[row][col] == 1 && !this.visit[row][col] && this.dfs(grid1, grid2, row, col)) {
          count++;
        }
      }
    }

    // Return count
    return count;
  }

  // Helper method for the dfs
  private boolean dfs(int[][] grid1, int[][] grid2, int row, int col) {
    // Check for boundary
    if (row < 0 || row >= this.ROWS || col < 0 || col >= this.COLS || grid2[row][col] == 0 || this.visit[row][col]) {
      return true;
    }

    // Set the visit to true
    this.visit[row][col] = true;

    // Set the result
    boolean result = grid1[row][col] == 1;

    // Call the recursive dfs method
    result &= this.dfs(grid1, grid2, row + 1, col);
    result &= this.dfs(grid1, grid2, row - 1, col);
    result &= this.dfs(grid1, grid2, row, col + 1);
    result &= this.dfs(grid1, grid2, row, col - 1);

    // Return the result
    return result;
  }
}

public class _1905_Count_Sub_Islands {
  // Main method to test countSubIslands
  public static void main(String[] args) {
    int[][] grid1 = new int[][] { { 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0 },
        { 1, 1, 0, 1, 1 } };
    int[][] grid2 = new int[][] { { 1, 1, 1, 0, 0 }, { 0, 0, 1, 1, 1 }, { 0, 1, 0, 0, 0 }, { 1, 0, 1, 1, 0 },
        { 0, 1, 0, 1, 0 } };

    int result = new Solution().countSubIslands(grid1, grid2);

    System.out.println("The number of islands in grid2 that are considered sub-islands is : " + result);
  }
}
