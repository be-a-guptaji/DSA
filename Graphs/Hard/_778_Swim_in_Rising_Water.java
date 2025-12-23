/*
LeetCode Problem: https://leetcode.com/problems/swim-in-rising-water/

Question: 778. Swim in Rising Water

Problem Statement: You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.

You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

Example 1:
Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:
Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n^2
Each value grid[i][j] is unique.
 */

/*
Approach: Dijkstra / Minimum Maximum Path (Greedy with Min Heap)

Goal:
Find the minimum time required to swim from the top-left to the bottom-right cell.
At time t, you can enter cells with elevation ≤ t.
The total time of a path is the maximum elevation encountered along that path.

Key Insight:
This is a shortest-path problem where:
- The “cost” of a path is the maximum elevation along it.
- We want to minimize this maximum value.

Algorithm:
1. Use a min heap (priority queue) storing:
   { currentTime, row, column }
   where currentTime = max elevation seen so far on the path.

2. Start from (0, 0):
   - Initial time = grid[0][0]
   - Mark it visited.

3. While heap is not empty:
   - Extract the cell with the smallest currentTime.
   - If it is the bottom-right cell → return currentTime.

4. For each of the 4 directions:
   - If the next cell is inside the grid and not visited:
     • nextTime = max(currentTime, grid[nextCell])
     • Push { nextTime, nextRow, nextCol } into the heap.
     • Mark the cell as visited.

Why It Works:
- Similar to Dijkstra’s algorithm.
- We always expand the path with the minimum possible maximum elevation.
- Once a cell is popped from the heap, we have found the optimal time to reach it.

Time Complexity: O(n² log n)  
Space Complexity: O(n²)
*/

package Graphs.Hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _778_Swim_in_Rising_Water {
   // Method to determine the transformation length of the word
   public static int swimInWater(int[][] grid) {
      // Initialize the length of the grid
      int n = grid.length;

      // Initialize the min heap to get the lowest elevation from the grid
      PriorityQueue<Integer[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

      // Get all the directions
      int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

      // Store the first cell of the grid in the min heap
      minHeap.offer(new Integer[] { grid[0][0], 0, 0 });

      // Set the first cell of the grid to Integer.MIN_VALUE
      grid[0][0] = Integer.MIN_VALUE;

      // Iterate over the min heap untill it is not empty
      while (!minHeap.isEmpty()) {
         // Get the current value from the min heap
         Integer[] currentValues = minHeap.poll();

         // Get the max time and the row and column of the cell
         int time = currentValues[0], row = currentValues[1], column = currentValues[2];

         // If row and column is the bottom right cell of the grid then return the time
         if (row == n - 1 && column == n - 1) {
            return time;
         }

         // Search in all directions
         for (int[] dir : directions) {
            // Get the next column
            int neiR = row + dir[0], neiC = column + dir[1];

            // If indices are not out of bound and cell is not seen yet then add it to the
            // min heap with the max time of the time and the current cell
            if (neiR >= 0 && neiC >= 0 && neiR < n && neiC < n && grid[neiR][neiC] != Integer.MIN_VALUE) {
               // Add the value to the min heap
               minHeap.offer(new Integer[] { Math.max(time, grid[neiR][neiC]), neiR, neiC });

               // Mark the cell as the seen
               grid[neiR][neiC] = Integer.MIN_VALUE;
            }
         }
      }

      // Return the Integer.MIN_VALUE as code should not reach here
      return Integer.MIN_VALUE;
   }

   // Main method to test swimInWater
   public static void main(String[] args) {
      int[][] grid = new int[][] {
            { 0, 1, 2, 3, 4 },
            { 24, 23, 22, 21, 5 },
            { 12, 13, 14, 15, 16 },
            { 11, 17, 18, 19, 20 },
            { 10, 9, 8, 7, 6 }
      };

      int result = swimInWater(grid);

      System.out.println(
            "The minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0) is : "
                  + result);
   }
}