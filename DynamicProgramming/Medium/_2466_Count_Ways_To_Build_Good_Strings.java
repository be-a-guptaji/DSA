/*
LeetCode Problem: https://leetcode.com/problems/count-ways-to-build-good-strings/

Question: 2466. Count Ways To Build Good Strings

Problem Statement: Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.

Example 1:
Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation: 
One possible valid good string is "011". 
It can be constructed as follows: "" -> "0" -> "01" -> "011". 
All binary strings from "000" to "111" are good strings in this example.

Example 2:
Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".

Constraints:
1 <= low <= high <= 10^5
1 <= zero, one <= low
*/

/*
Approach: Top-down Memoized DP over Current String Length
Goal:
- Count distinct binary strings with length in
  [low, high], built by appending "0" zero times or
  "1" one times per step.
Core Idea:
- dfs(length) = number of good strings obtainable
  from a current string of the given length,
  counting the string itself if length >= low.
- Transitions: append the zero block to reach
  length + zero, or append the one block to reach
  length + one.
- Prune when length > high (returns 0).
- Store every value modulo 1e9 + 7 so cached reads
  and parent sums stay consistent.
Algorithm Steps:
1. If length > high, return 0.
2. Return dp[length] if already computed.
3. result = (length >= low ? 1 : 0)
   + dfs(length + zero) + dfs(length + one).
4. dp[length] = result % MOD and return it.
Time Complexity:
- O(high)
each length in [0, high] is computed once with O(1)
work.
Space Complexity:
- O(high)
dp array plus recursion stack.
Result:
- Returns the number of good strings modulo
  1e9 + 7.
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the number of different good strings that can be constructed
  // satisfying these properties
  public int countGoodStrings(int low, int high, int zero, int one) {
    // Initialize the dp array
    long[] dp = new long[high + Math.max(zero, one) + 1];

    // Fill the dp array to min value
    Arrays.fill(dp, Long.MIN_VALUE);

    // Return the recursive dfs method
    return (int) this.dfs(low, high, zero, one, 0, dp);
  }

  // Helper method for the dfs
  private long dfs(int low, int high, int zero, int one, int length, long[] dp) {
    // If length is more than high then return 0
    if (length > high) {
      return 0;
    }

    // If we have seen dp[length] than return the dp[length]
    if (dp[length] != Long.MIN_VALUE) {
      return dp[length];
    } else if (dp[length] == Long.MIN_VALUE) {
      dp[length] = 0;
    }

    // If length is greater than low then update the dp[length]
    if (length >= low) {
      dp[length]++;
    }

    // Update the dp[length]
    dp[length] += this.dfs(low, high, zero, one, length + zero, dp) + this.dfs(low, high, zero, one, length + one, dp);

    // Return the dp[length] by moding it to 1_000_000_007
    return dp[length] % 1_000_000_007;
  }
}

public class _2466_Count_Ways_To_Build_Good_Strings {
  // Main method to test countGoodStrings
  public static void main(String[] args) {
    int low = 3;
    int high = 3;
    int zero = 1;
    int one = 1;

    int result = new Solution().countGoodStrings(low, high, zero, one);

    System.out.println(
        "The number of different good strings that can be constructed satisfying these properties is : " + result);
  }
}