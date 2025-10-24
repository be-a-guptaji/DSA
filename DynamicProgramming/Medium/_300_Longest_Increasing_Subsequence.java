/*
LeetCode Problem: https://leetcode.com/problems/longest-increasing-subsequence/

Question: 300. Longest Increasing Subsequence

Problem Statement: Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:

1 <= nums.length <= 2500
-10^4 <= nums[i] <= 10^4

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

/*
Approach:
This solution handles the "Longest Increasing Subsequence" (LIS) problem.

Key Idea:
- Use Dynamic Programming (DP) to build an array `dp` where `dp[i]` represents the 
  length of the LIS ending at index `i`.
- For each element `nums[i]`, check all previous elements `nums[j]` where `j < i`.
  - If `nums[j] < nums[i]`, then `nums[i]` can extend the LIS ending at `j`.
  - So, update `dp[i] = max(dp[i], dp[j] + 1)`.

Steps:
1. Initialize a `dp` array of length `n` (same as `nums.length`) with all 1s.
   - Each element is an increasing subsequence of length 1 by itself.
2. Loop through the array with index `i` from 0 to n-1.
   - For each `i`, loop through all indices `j` from 0 to `i-1`.
     - If `nums[j] < nums[i]`, update `dp[i] = max(dp[i], dp[j] + 1)`.
3. Return the maximum value in `dp`, which represents the length of the longest increasing subsequence.

Time Complexity: O(n^2), where n is the number of elements in the array.
Space Complexity: O(n), due to the `dp` array.
*/

package DynamicProgramming.Medium;

public class _300_Longest_Increasing_Subsequence {
  // Method to find the longest increasing subsequence
  public static int lengthOfLIS(int[] nums) {
    // Initialize size of the nums array
    int size = nums.length;

    // Initialize a dp array
    int[] dp = new int[size];

    // Iterate over the nums array
    for (int i = 0; i < size; i++) {
      // Get the current value
      int current = nums[i];

      // Traverse the array untill we find the strictly lesser element than the
      // current element
      for (int j = 0; j < i; j++) {
        // If we find the element then update the dp array
        if (nums[j] < current) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    // Initialize the maxLengthOfLIS variable for the max length
    int maxLengthOfLIS = dp[0];

    // Find the max element from the dp array
    for (int i = 1; i < size; i++) {
      maxLengthOfLIS = Math.max(maxLengthOfLIS, dp[i]);
    }

    // Return the maxLengthOfLIS
    return maxLengthOfLIS + 1;
  }

  // Main method to test lengthOfLIS
  public static void main(String[] args) {
    int[] nums = { 0, 1, 0, 3, 2, 3 };

    int result = lengthOfLIS(nums);

    System.out.println("The longest increasing subsequence is : " + result);
  }
}
