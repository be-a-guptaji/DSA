/*
LeetCode Problem: https://leetcode.com/problems/shortest-path-in-binary-matrix/

Question: 1091. Shortest Path in Binary Matrix

Problem Statement: Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:
Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1

Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/

/*
Approach: Level-order BFS with 8-directional Expansion
Goal:
- Find the shortest clear path (all 0-cells) from
  the top-left to the bottom-right corner of an
  n x n binary matrix, moving in 8 directions.
  Return -1 if no such path exists.
Core Idea:
- BFS from (0, 0) expands level by level; each
  level represents one additional step in the path.
- The first time (n-1, n-1) is reached, the
  current level count gives the shortest path
  length.
- Path length counts cells, not edges, so a single-
  cell grid returns 1 and the answer increments by
  2 when the destination is first found from an
  adjacent cell (current path count + 1 for the
  neighbor + 1 for the starting cell already
  counted).
Algorithm Steps:
1. Return -1 if grid[0][0] or grid[n-1][n-1] == 1.
2. Return 1 if n == 1 and grid[0][0] == 0.
3. Enqueue (0, 0), mark visited, initialize path=0.
4. BFS level by level:
   a. For each cell in the current level, expand
      into all 8 neighbors.
   b. Skip out-of-bounds, blocked (1), or visited
      cells.
   c. If neighbor is (n-1, n-1), return path + 2.
   d. Otherwise mark visited and enqueue.
   e. Increment path after each full level.
5. Return -1 if queue empties without reaching the
   destination.
Why It Works:
- Level-order BFS guarantees the first path found
  is the shortest.
- Marking cells visited on enqueue (not on dequeue)
  prevents the same cell from being enqueued
  multiple times, keeping complexity O(n^2).
- The path + 2 formula accounts for the starting
  cell (already at path + 1 steps into the grid)
  plus the destination cell itself.
Time Complexity:
- O(n^2)
since each of the n^2 cells is enqueued and
processed at most once.
Space Complexity:
- O(n^2)
for the visit array and BFS queue.
Result:
- Returns the length of the shortest clear path
  in cells, or -1 if no path exists.
*/

package Graphs.Medium;

import java.util.ArrayDeque;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the direction matrix
  private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 },
      { 1, -1 } };

  // Method to find the length of the shortest clear path in the matrix
  public int shortestPathBinaryMatrix(int[][] grid) {
    // Initialize the length of the grid
    final int length = grid.length;

    // If starting or ending path is -1 then return -1
    if (grid[0][0] == 1 || grid[length - 1][length - 1] == 1) {
      return -1;
    } else if (length == 1 && grid[0][0] == 0) {
      return 1;
    }

    // Initialize the path variable
    int path = 0;

    // Initialize the visit matrix
    boolean[][] visit = new boolean[length][length];

    // Initialize the queue
    Queue<int[]> queue = new ArrayDeque<>();

    // Add the first cell in the queue
    queue.offer(new int[] { 0, 0 });

    // Set the starting cell to seen
    visit[0][0] = true;

    // Iterate over the queue
    while (!queue.isEmpty()) {
      // Initialize the size variable
      int size = queue.size();

      // Iterate over the level
      for (int i = 0; i < size; i++) {
        // Get the cell co-ordinates from the queue
        int[] cell = queue.poll();

        // Iterate over all the direction
        for (int[] dir : directions) {
          // Get the row and col from the cell
          int row = cell[0] + dir[0];
          int col = cell[1] + dir[1];

          // If cell is valid then add to the queue
          if (row < 0 || col < 0 || row == length || col == length || grid[row][col] == 1 || visit[row][col]) {
            continue;
          }

          // If we are on the last cell then return the path
          if (row == length - 1 && col == length - 1) {
            return path + 2;
          }

          // Set the cell to seen
          visit[row][col] = true;

          // Add the value to the queue
          queue.offer(new int[] { row, col });
        }
      }

      // Increment the path varaible
      path++;
    }

    // Return -1 in the end
    return -1;
  }
}

public class _1091_Shortest_Path_in_Binary_Matrix {
  // Main method to test shortestPathBinaryMatrix
  public static void main(String[] args) {
    int[][] grid = new int[][] {
        { 0, 0, 0 },
        { 1, 1, 0 },
        { 1, 1, 0 }
    };

    int result = new Solution().shortestPathBinaryMatrix(grid);

    System.out.println("The length of the shortest clear path in the matrix is : " + result);
  }
}
