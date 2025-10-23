/*
LeetCode Problem: https://leetcode.com/problems/perfect-squares/

Question: 279. Perfect Squares

Problem Statement: Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

Constraints:

1 <= n <= 10^4
*/

/*
Approach:
We use **Dynamic Programming (Bottom-Up Tabulation)** to find the least number 
of perfect square numbers that sum up to `n`.

1. Create a dp array of size 10001 (since n <= 10000). 
   `dp[i]` stores the minimum number of perfect squares that sum up to `i`.
2. Initialize `dp[0] = 0`, because zero numbers are needed to sum to 0.
3. For every number `i` from 1 to 10000:
     For every perfect square `j*j <= i`:
        dp[i] = min(dp[i], dp[i - j*j] + 1)
   This builds the solution bottom-up by combining smaller subproblems.
4. We compute the table once and reuse it for all future queries.

Time Complexity:  O(n * sqrt(n))
Space Complexity: O(n)
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

public class _279_Perfect_Squares {
  // Initialize the dp for the memoization of all the values for all the next runs
  private static final int[] dp = new int[10001];

  // Method to find the least number of perfect square numbers that sum to n
  public static int numSquares(int n) {
    // If dp[10000] is not equal to Integer.MAX_VALUE then skip the computation
    if (dp[10000] == 0) {
      // Fill the dp with the Integer.MAX_VALUE
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;

      for (int i = 1; i < 10001; i++) {
        for (int j = 1; j * j <= i; j++) {
          dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
      }
    }

    // Return the dp[n] as the result
    return dp[n];
  }

  // Main method to test numSquares
  public static void main(String[] args) {
    int n = 12;

    int result = numSquares(n);

    System.out.println("The least number of perfect square numbers that sum to " + n + " is : " + result);
  }
}
