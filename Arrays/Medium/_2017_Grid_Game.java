/*
LeetCode Problem: https://leetcode.com/problems/grid-game/

Question: 2017. Grid Game

Problem Statement: You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number of points at position (r, c) on the matrix. Two robots are playing a game on this matrix.

Both robots initially start at (0, 0) and want to reach (1, n-1). Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).

At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from the cells on its path. For all cells (r, c) traversed on the path, grid[r][c] is set to 0. Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path. Note that their paths may intersect with one another.

The first robot wants to minimize the number of points collected by the second robot. In contrast, the second robot wants to maximize the number of points it collects. If both robots play optimally, return the number of points collected by the second robot.

Example 1:
Input: grid = [[2,5,4],[1,5,1]]
Output: 4
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 0 + 4 + 0 = 4 points.

Example 2:
Input: grid = [[3,3,1],[8,5,2]]
Output: 4
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 3 + 1 + 0 = 4 points.

Example 3:
Input: grid = [[1,3,1,15],[1,3,3,1]]
Output: 7
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 1 + 3 + 3 + 0 = 7 points.

Constraints:
grid.length == 2
n == grid[r].length
1 <= n <= 5 * 10^4
1 <= grid[r][c] <= 10^5
*/

/*
Approach: Prefix Sum + Minimax Strategy

Goal:
- Minimize the maximum number of points the second robot can collect.

Core Idea:
- The first robot chooses a turning column i.
- After that:
   - Remaining points on top row to the right of i
   - Remaining points on bottom row to the left of i
  are the only possible points for the second robot.
- The second robot will choose the larger of these two values.
- Minimize this maximum over all possible turning points.

Algorithm Steps:
1. Build prefix sums for both rows.
2. For each possible turning column i:
   - topRemaining =
       totalTop - prefixTop[i]
   - bottomRemaining =
       prefixBottom[i - 1] (or 0 if i == 0)
3. Second robot gets:
   - max(topRemaining, bottomRemaining)
4. Track the minimum across all i.
5. Return the minimum value.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns the minimum points the second robot can collect
  assuming both players play optimally.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the number of points collected by the second robot
  public long gridGame(int[][] grid) {
    // Get the number of colums in the grid
    int length = grid[0].length;

    // Initialize two array for the first and second row
    long[] prefix1 = new long[length];
    long[] prefix2 = new long[length];

    // Fill the first index of the prefix array
    prefix1[0] = grid[0][0];
    prefix2[0] = grid[1][0];

    // Fill the prefix sums
    for (int i = 1; i < length; i++) {
      prefix1[i] = grid[0][i] + prefix1[i - 1];
      prefix2[i] = grid[1][i] + prefix2[i - 1];
    }

    // Initialize the result variable
    long result = Long.MAX_VALUE;

    // Find the result for the second palyer
    for (int i = 0; i < length; i++) {
      result = Math.min(result, Math.max(prefix1[length - 1] - prefix1[i], i > 0 ? prefix2[i - 1] : 0));
    }

    // Return the result
    return result;
  }
}

public class _2017_Grid_Game {
  // Main method to test gridGame
  public static void main(String[] args) {
    int[][] grid = { { 3, 3, 1 }, { 8, 5, 2 } };

    long result = new Solution().gridGame(grid);

    System.out
        .println("The number of points collected by the second robot is : " + result);
  }
}
