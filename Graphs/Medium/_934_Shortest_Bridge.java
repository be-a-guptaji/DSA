/*
LeetCode Problem: https://leetcode.com/problems/shortest-bridge/

Question: 934. Shortest Bridge

Problem Statement: You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.

Example 1:
Input: grid = [[0,1],[1,0]]
Output: 1

Example 2:
Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2

Example 3:
Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1

Constraints:
n == grid.length == grid[i].length
2 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands in grid.
*/

/*
Approach: DFS Island Marking + Multi-source BFS Expansion
Goal:
- Find the minimum number of 0-cells to flip to
  connect the two islands in the binary grid.
Core Idea:
- The problem reduces to finding the shortest path
  between two connected components of 1-cells,
  where path length is measured in 0-cells crossed.
- Use DFS to fully identify the first island,
  marking all its cells visited and seeding them
  into a BFS queue simultaneously.
- Multi-source BFS expands outward from the entire
  first island boundary at once; the first time a
  cell belonging to the second island is reached,
  the current BFS level is the minimum bridge
  length.
Algorithm Steps:
1. Scan the grid to find any cell with value 1.
2. DFS from that cell to mark all connected 1-cells
   as visited and enqueue them as BFS sources.
3. BFS outward level by level:
   a. For each cell in the current frontier, expand
      into 4 neighbors.
   b. Skip out-of-bounds and already-visited cells.
   c. If a neighbor is 1 (second island), return
      the current level count as the answer.
   d. If a neighbor is 0, mark visited and enqueue
      for the next BFS level.
   e. Increment level after processing all cells in
      the current frontier.
4. Return -1 if unreachable (guaranteed not to
   happen per problem constraints).
Why It Works:
- DFS completely marks island 1 before BFS starts,
  ensuring BFS never mistakes island 1 cells for
  the target.
- Multi-source BFS from all island 1 boundary cells
  simultaneously finds the shortest path to island
  2 in O(n^2) without needing to identify a single
  source point.
- Level-by-level BFS guarantees the first encounter
  with island 2 is at minimum distance.
Time Complexity:
- O(n^2)
where n is the grid dimension, since each cell is
visited at most once by DFS and at most once by BFS.
Space Complexity:
- O(n^2)
for the visit array and BFS queue in the worst
case of a fully connected island.
Result:
- Returns the minimum number of 0-cells to flip
  to connect the two islands.
*/

package Graphs.Medium;

import java.util.ArrayDeque;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the LENGTH variable
  private int LENGTH;

  // Initialize the direction matrix
  private static int[][] DIRECTION = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

  // Method to find the smallest number of 0's you must flip to connect the two
  // islands
  public int shortestBridge(int[][] grid) {
    // Initialize the LENGTH variable
    this.LENGTH = grid.length;

    // Initialize the boolean matrix for the seen
    boolean[][] visit = new boolean[this.LENGTH][this.LENGTH];

    // Iterate over the grid
    for (int row = 0; row < this.LENGTH; row++) {
      for (int col = 0; col < this.LENGTH; col++) {
        // If we are on the cell which is 1 then do bfs and break out of the loop
        if (grid[row][col] == 1) {
          // Initialize the queue for the bfs
          Queue<int[]> queue = new ArrayDeque<>();

          // Call the recursive dfs method
          this.dfs(row, col, grid, visit, queue);

          // Call the bfs method
          return this.bfs(grid, visit, queue);
        }
      }
    }

    // Return -1 in the end
    return -1;
  }

  // Helper method for the bfs
  private int bfs(int[][] grid, boolean[][] visit, Queue<int[]> queue) {
    // Initialize the result variable
    int result = 0;

    // Iterate over the queue
    while (!queue.isEmpty()) {
      // Initialize the size variable
      int size = queue.size();

      // Iterate over the queue
      for (int i = 0; i < size; i++) {
        // Get the new cell
        int[] d = queue.poll();

        // Iterate over all direction
        for (int[] dir : DIRECTION) {
          // Initialize the row and col variable
          int row = d[0] + dir[0];
          int col = d[1] + dir[1];

          // Check if out of bound
          if (this.outOfBound(row, col) || visit[row][col]) {
            continue;
          }

          // If grid cell is 1 then return result
          if (grid[row][col] == 1) {
            return result;
          }

          // Set the visit to true
          visit[row][col] = true;

          // Add the value to the queue
          queue.offer(new int[] { row, col });
        }
      }

      // Increment the result variable
      result++;
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private void dfs(int row, int col, int[][] grid, boolean[][] visit, Queue<int[]> queue) {
    // Check if out of bound
    if (this.outOfBound(row, col) || grid[row][col] == 0 || visit[row][col]) {
      return;
    }

    // Set the visit to true
    visit[row][col] = true;

    // Add the value to the queue
    queue.offer(new int[] { row, col });

    // Iterate over all direction
    for (int[] dir : DIRECTION) {
      this.dfs(row + dir[0], col + dir[1], grid, visit, queue);
    }
  }

  // Helper method for the outOfBound
  private boolean outOfBound(int row, int col) {
    // If we are out of bound return ture else false
    if (row < 0 || row == this.LENGTH || col < 0 || col == this.LENGTH) {
      return true;
    }

    // Return false in the end
    return false;
  }
}

public class _934_Shortest_Bridge {
  // Main method to test checkMove
  public static void main(String[] args) {
    int[][] grid = new int[][] {
        { 1, 1, 1, 1, 1 },
        { 1, 0, 0, 0, 1 },
        { 1, 0, 1, 0, 1 },
        { 1, 0, 0, 0, 1 },
        { 1, 1, 1, 1, 1 }
    };

    int result = new Solution().shortestBridge(grid);

    System.out.println("The smallest number of 0's you must flip to connect the two islands is : " + result);
  }
}
