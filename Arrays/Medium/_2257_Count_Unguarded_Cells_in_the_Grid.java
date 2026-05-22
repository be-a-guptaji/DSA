/*
LeetCode Problem: https://leetcode.com/problems/count-unguarded-cells-in-the-grid/

Question: 2257. Count Unguarded Cells in the Grid

Problem Statement: You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.

A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.

Return the number of unoccupied cells that are not guarded.

Example 1:
Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
Output: 7
Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.

Example 2:
Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
Output: 4
Explanation: The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.

Constraints:
1 <= m, n <= 10^5
2 <= m * n <= 10^5
1 <= guards.length, walls.length <= 5 * 10^4
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique.
*/

/*
Approach: Grid Simulation + Directional Traversal

Goal:
- Count cells that are neither:
   - guarded
   - occupied by a guard
   - occupied by a wall

Core Idea:
- Represent the grid using states:
    0 → empty
    1 → guarded
    2 → guard
    3 → wall
- From each guard:
   - Expand in 4 directions until:
       - boundary reached
       - another guard encountered
       - wall encountered
- Mark traversed cells as guarded.

Algorithm Steps:
1. Initialize m × n matrix.
2. Mark:
   - walls as 3
   - guards as 2
3. For each guard:
   - Traverse in 4 directions:
       up, right, down, left
   - Continue marking cells as guarded
     until wall/guard encountered.
4. Traverse matrix:
   - Count cells with value 0.
5. Return count.

Time Complexity:
- O(m * n + g * (m + n))
  where g = number of guards

Space Complexity:
- O(m * n)

Result:
- Returns total number of unguarded cells.
*/

package Arrays.Medium;

// Solution Class
class Solution {
    // Method to find the number of unoccupied cells that are not guarded
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // Initialize the matrix of m X n
        int[][] matrix = new int[m][n];

        // Fill the walls position in the matrix
        for (int[] wall : walls) {
            // Change the value to the matrix to 3
            matrix[wall[0]][wall[1]] = 3;
        }

        // Iterate over the guards to make the matrix cell guarded
        for (int[] guard : guards) {
            // Change the value to the matrix to 2
            matrix[guard[0]][guard[1]] = 2;
        }

        // Initialize the directions array
        int[][] directions = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        // Iterate over the guards to make the matrix cell guarded in all directions
        for (int[] guard : guards) {
            // Get the row and col of the current guard
            int row = guard[0];
            int col = guard[1];

            // Iterate over the cells in all directions
            for (int[] direction : directions) {
                // Get the cell
                int r = row + direction[0];
                int c = col + direction[1];

                // Iterate over the direction
                while (r >= 0 && r < m && c >= 0 && c < n) {
                    // Stop at wall or another guard and break out of the loop
                    if (matrix[r][c] == 2 || matrix[r][c] == 3) {
                        break;
                    }

                    // Mark as guarded
                    matrix[r][c] = 1;

                    // Update the row and col
                    r += direction[0];
                    c += direction[1];
                }
            }
        }

        // Initialize the unguarded cell variable
        int ungaurdedCell = 0;

        // Get all the unguardedCell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If matrix[i][j] is 0 then increment the ungaurdedCell
                if (matrix[i][j] == 0) {
                    ungaurdedCell++;
                }
            }
        }

        // Return the ungaurdedCell
        return ungaurdedCell;
    }
}

public class _2257_Count_Unguarded_Cells_in_the_Grid {
    // Main method to test countUnguarded
    public static void main(String[] args) {
        int m = 4;
        int n = 6;
        int[][] guards = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 3 } };
        int[][] walls = new int[][] { { 0, 1 }, { 2, 2 }, { 1, 4 } };

        int result = new Solution().countUnguarded(m, n, guards, walls);

        System.out.println("The number of unoccupied cells that are not guarded is : " + result);
    }
}
