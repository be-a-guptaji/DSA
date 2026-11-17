/*
LeetCode Problem: https://leetcode.com/problems/integer-break/

Question: 343. Integer Break

Problem Statement: Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

Example 1:
Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.

Example 2:
Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.

Constraints:
2 <= n <= 58
*/

/*
Approach: Static Precomputed DP over Split Points
Goal:
- Break n into at least two positive integers that
  sum to n, maximizing their product.
Core Idea:
- dp[i] = best product obtainable from a part of
  size i, where the part may be left unsplit
  (dp[i] >= i) or split into two parts.
- Transition: dp[i] = max(i, max over j in
  [1, i / 2] of dp[j] * dp[i - j]).
- The "at least one split" rule only matters for
  n <= 3, where leaving a part whole beats
  splitting. Handle those directly with n - 1.
- Precompute dp for 1..58 once in a static block so
  each query is O(1).
Algorithm Steps:
1. dp[1] = 1.
2. For i from 2 to 58: dp[i] = i, then for each j
   from 1 to i / 2, dp[i] = max(dp[i],
   dp[j] * dp[i - j]).
3. For a query n: if n <= 3 return n - 1, else
   return dp[n].
Time Complexity:
- O(1) per query; O(58^2) = O(1) precomputation.
Space Complexity:
- O(1)
fixed 59-element array.
Result:
- Returns the maximum product from breaking n into
  at least two positive integers.
*/

package DynamicProgramming.Medium;

// Solution Class
class Solution {
  // Initialize the dp array for memoization
  private static final int[] dp = new int[59];

  // Static method for the precomputations
  static {
    // Initialize the base case
    dp[1] = 1;

    // Iterate over the numbers from 2 to 58
    for (int i = 2; i < 59; i++) {
      // Set the dp[i] to i
      dp[i] = i;

      // Divide the number into two parts
      for (int j = 1; j <= i / 2; j++) {
        // Set the dp[i] to the maximum of the computation
        dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
      }
    }
  }

  // Method to find the maximum product you can get
  public int integerBreak(int n) {
    // Handle the cases where at least one split is mandatory
    if (n <= 3) {
      return n - 1;
    }

    // Return the dp[n]
    return dp[n];
  }
}

public class _343_Integer_Break {
  // Main method to test maxSumMinProduct
  public static void main(String[] args) {
    int n = 10;

    int result = new Solution().integerBreak(n);

    System.out.println("The maximum product you can get is : " + result);
  }
}