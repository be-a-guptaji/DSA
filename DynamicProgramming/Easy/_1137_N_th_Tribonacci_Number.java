/*
LeetCode Problem: https://leetcode.com/problems/n-th-tribonacci-number/

Question: 1137. N-th Tribonacci Number

Problem Statement: The Tribonacci sequence Tn is defined as follows: 

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

Example 1:
Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4

Example 2:
Input: n = 25
Output: 1389537

Constraints:
0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
*/

/*
Approach: Static Precomputed Tribonacci DP Table
Goal:
- Return the nth Tribonacci number where T(0) = 0,
  T(1) = T(2) = 1, T(n) = T(n-1) + T(n-2) + T(n-3).
Core Idea:
- Precompute all 38 values once at class load time
  via a static initializer; each query is O(1).
Algorithm Steps:
1. dp[0] = 0, dp[1] = dp[2] = 1.
2. For i from 3 to 37: dp[i] = dp[i-1] + dp[i-2]
   + dp[i-3].
3. Return dp[n].
Time Complexity:
- O(1) per query; O(38) = O(1) precomputation.
Space Complexity:
- O(1) fixed 38-element array.
Result:
- Returns the nth Tribonacci number.
*/

package DynamicProgramming.Easy;

// Solution Class
class Solution {
  // Initialize the dp array of 37
  private static final int[] dp = new int[38];

  // Make the dp array
  static {
    // Set the default value of the dp array
    dp[0] = 0;
    dp[1] = dp[2] = 1;

    // Iterate over the dp array
    for (int i = 3; i < 38; i++) {
      dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    }
  }

  // Method to find the value of Tn
  public int tribonacci(int n) {
    // Return the dp[n]
    return dp[n];
  }
}

public class _1137_N_th_Tribonacci_Number {
  // Main method to test tribonacci
  public static void main(String[] args) {
    int n = 3;

    int result = new Solution().tribonacci(n);

    System.out.println("The value of Tn is : " + result);
  }
}