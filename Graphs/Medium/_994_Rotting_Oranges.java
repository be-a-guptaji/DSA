/*
LeetCode Problem: https://leetcode.com/problems/rotting-oranges/

Question: 994. Rotting Oranges

Problem Statement: You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */

/*
Approach:
1. The problem is essentially a multi-source BFS:
   - All initially rotten oranges act as starting points.
   - From each rotten orange, the rotting spreads outward level by level (minute by minute).
2. Steps:
   - Traverse the grid to find all initially rotten oranges (value = 2).
   - Push their coordinates into a queue (multi-source BFS setup).
   - Use BFS to propagate the rotting to all adjacent fresh oranges (value = 1).
   - Each BFS "level" corresponds to 1 unit of time (1 minute).
3. While performing BFS:
   - For each rotten orange, check its 4 neighbors (up, down, left, right).
   - If a neighbor is a fresh orange, rot it (mark as 2) and add it to the queue.
   - After processing one BFS level, increment the time counter.
4. After BFS finishes:
   - Scan the grid again.
   - If any fresh orange (value = 1) still remains, return -1 (not all oranges can rot).
   - Otherwise, return `time - 1` because the last BFS increment happens after the last orange rots.

Time Complexity: O(m * n), since each cell is processed at most once.  
Space Complexity: O(m * n) in the worst case for the BFS queue.
*/

package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _994_Rotting_Oranges {
    // Directions for traversal (up, down, left, right)
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // Method to determine how much time it take to rot all the oranges
    public static int orangesRotting(int[][] grid) {
        // Initialize rows and columns of the grid
        int rows = grid.length, columns = grid[0].length;

        // Initialize a Queue for the BFS Traversal of the graph
        Queue<int[]> queue = new LinkedList<>();

        // Traverse the graph and get all the rotten oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j }); // Offer the rotten orange cell to the queue
                }
            }
        }

        // Initialize a time for the returing
        int time = 0;

        // Traverse the graph untill the queue is empty
        while (!queue.isEmpty()) {
            // Initialize the size equal to the size of the queue
            int size = queue.size();

            // Process all the cells of the queue
            for (int i = 0; i < size; i++) {
                // Get the cell and make a BFS Traversal outward
                int[] currentCell = queue.poll();

                // Make recursive call in all direction
                for (int[] dir : directions) {
                    // Get the next cell to process
                    int[] nextCell = new int[] { currentCell[0] + dir[0], currentCell[1] + dir[1] };

                    // Check all the edge case
                    if (nextCell[0] < 0
                            || nextCell[1] < 0
                            || nextCell[0] >= rows
                            || nextCell[1] >= columns
                            || grid[nextCell[0]][nextCell[1]] != 1) {
                        continue;
                    }

                    // Offer the nextCell to the queue
                    queue.offer(nextCell);

                    // Mark the current cell to the 2 as well
                    grid[nextCell[0]][nextCell[1]] = 2;
                }
            }

            // Increase the time
            time++;
        }

        // Check if all the oranges are rotten
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1) {
                    return -1; // Return -1 as all oranges cannot be rotten
                }
            }
        }

        // Return the time
        return time == 0 ? 0 : time - 1;
    }

    // Main method to test orangesRotting
    public static void main(String[] args) {
        int[][] grid = {
                { 2, 1, 1 },
                { 1, 1, 0 },
                { 0, 1, 1 }
        };

        int result = orangesRotting(grid);

        System.out.println("All oranges can be rotten in : " + result);
    }
}