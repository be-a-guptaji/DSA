/*
LeetCode Problem: https://leetcode.com/problems/triangle/

Question: 120. Triangle

Problem Statement: Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

Example 2:
Input: triangle = [[-10]]
Output: -10

Constraints:
1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-10^4 <= triangle[i][j] <= 10^4

Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
*/

/*
Approach: Bottom-up 1D DP over Triangle Rows
Goal:
- Find the minimum path sum from the top to the
  bottom of the triangle, moving to adjacent
  indices (j or j + 1) on each step down.
Core Idea:
- Process rows from bottom to top, keeping a single
  array where dp[j] = minimum path sum from
  position j of the current row down to the base.
- At row i, the best continuation from position j
  is the smaller of dp[j] and dp[j + 1] (the two
  adjacent cells below), plus the current value.
- Overwriting dp in place is safe because dp[j]
  only depends on dp[j] and dp[j + 1] from the row
  below, and j is processed left to right.
Algorithm Steps:
1. Allocate dp[n + 1] initialized to 0 (acts as the
   virtual row below the base).
2. For i from n - 1 down to 0:
   - For j from 0 to i:
     - dp[j] = min(dp[j], dp[j + 1]) + row.get(j).
3. Return dp[0].
Time Complexity:
- O(n^2)
where n is the number of rows; the triangle has
n(n + 1) / 2 cells, each processed once.
Space Complexity:
- O(n)
for the dp array.
Result:
- Returns the minimum top-to-bottom path sum.
*/

package DynamicProgramming.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the minimum path sum from top to bottom
  public int minimumTotal(List<List<Integer>> triangle) {
    // Initialize the size of the triangle
    int n = triangle.size();

    // Initialize the dp array of size n
    int[] dp = new int[n + 1];

    // Iterate over the triangle
    for (int i = n - 1; i > -1; i--) {
      // Get the row of the triangle
      List<Integer> row = triangle.get(i);

      // Itearte over the row
      for (int j = 0; j <= i; j++) {
        // Update the dp array
        dp[j] = Math.min(dp[j], dp[j + 1]) + row.get(j);
      }
    }

    // Return the dp[0]
    return dp[0];
  }
}

public class _120_Triangle {
  // Main method to test minimumTotal
  public static void main(String[] args) {
    List<List<Integer>> triangle = new ArrayList<>();

    triangle.add(Arrays.asList(2));
    triangle.add(Arrays.asList(3, 4));
    triangle.add(Arrays.asList(6, 5, 7));
    triangle.add(Arrays.asList(4, 1, 8, 3));

    int result = new Solution().minimumTotal(triangle);

    System.out.println("The minimum path sum from top to bottom is : " + result);
  }
}