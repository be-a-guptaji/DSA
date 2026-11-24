/*
LeetCode Problem: https://leetcode.com/problems/number-of-longest-increasing-subsequence/

Question: 673. Number of Longest Increasing Subsequence

Problem Statement: Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

Example 1:
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:
Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.

Constraints:
1 <= nums.length <= 2000
-10^6 <= nums[i] <= 10^6
The answer is guaranteed to fit inside a 32-bit integer.
*/

/*
Approach: Top-down Memoized DP on LIS Starting at Each Index
Goal:
- Count the number of longest strictly increasing
  subsequences in nums.
Core Idea:
- dp[i] = {length of the LIS starting at i, number
  of LIS of that length starting at i}.
- From index, look at every later i with
  nums[i] > nums[index]. The best length is
  1 + max(dp[i].length). The count is the sum of
  dp[i].count over all i achieving that max.
- With no valid successor, the pair is {1, 1}.
- Memoize each index so every state is solved once.
- Across all start indices, track the global longest
  length and accumulate counts of those equal to it.
Algorithm Steps:
1. For each index, call dfs(index) to fill dp[index].
2. In dfs(index):
   a. Return if dp[index] is already computed.
   b. Set maxLength = 1, maxCount = 1.
   c. For each i > index with nums[i] > nums[index]:
      - dfs(i).
      - If 1 + dp[i].length > maxLength, set
        maxLength and maxCount = dp[i].count.
      - Else if equal, maxCount += dp[i].count.
   d. Store {maxLength, maxCount}.
3. Scan all indices: if length > lenLIS, reset
   result to its count; if equal, add its count.
4. Return result.
Time Complexity:
- O(n^2)
n states, each scanning up to n indices.
Space Complexity:
- O(n)
dp array plus O(n) recursion depth.
Result:
- Returns the number of longest increasing
  subsequences.
*/

package DynamicProgramming.Medium;

// Solution Class
class Solution {
  // Method to find the number of longest increasing subsequences
  public int findNumberOfLIS(int[] nums) {
    // Initialize the size of the nums array
    int n = nums.length;

    // Initialize the dp array
    int[][] dp = new int[n][2];

    // Fill the matrix to Integer.MIN_VALUE
    for (int i = 0; i < n; i++) {
      dp[i][0] = dp[i][1] = Integer.MIN_VALUE;
    }

    // Initialize the longest lis and result variable
    int lenLIS = 0, result = 0;

    // Iterate over nums array
    for (int index = 0; index < n; index++) {
      // Call the helper dfs method
      this.dfs(nums, dp, index, n);

      // Initialize the result array
      int[] res = dp[index];

      // Update the lenLIS and result variable
      if (res[0] > lenLIS) {
        lenLIS = res[0];
        result = res[1];
      } else if (res[0] == lenLIS) {
        result += res[1];
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private void dfs(int[] nums, int[][] dp, int index, int n) {
    // If dp[index][0] != Integer.MIN_VALUE then terminate the method
    if (dp[index][0] != Integer.MIN_VALUE) {
      return;
    }

    // Initialize the max length and max count variable
    int maxLength = 1, maxCount = 1;

    // Iterate over the nums array
    for (int i = 0; i < n; i++) {
      // If nums[i] greater than nums[index] then do the computation
      if (i > index && nums[i] > nums[index]) {
        // Call the helper dfs method
        this.dfs(nums, dp, i, n);

        // Initialize the next array
        int[] next = dp[i];

        // Update the max length and max count variable
        if (next[0] + 1 > maxLength) {
          maxLength = 1 + next[0];
          maxCount = next[1];
        } else if (next[0] + 1 == maxLength) {
          maxCount += next[1];
        }
      }
    }

    // Update the dp[index]
    dp[index] = new int[] { maxLength, maxCount };
  }
}

public class _673_Number_of_Longest_Increasing_Subsequence {
  // Main method to test findNumberOfLIS
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 3, 5, 4, 7 };

    int result = new Solution().findNumberOfLIS(nums);

    System.out.println("The number of longest increasing subsequences is : " + result);
  }
}