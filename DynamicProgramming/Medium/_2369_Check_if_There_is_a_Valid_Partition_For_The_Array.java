/*
LeetCode Problem: https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/

Question: 2369. Check if There is a Valid Partition For The Array

Problem Statement: You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.

We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:

The subarray consists of exactly 2, equal elements. For example, the subarray [2,2] is good.
The subarray consists of exactly 3, equal elements. For example, the subarray [4,4,4] is good.
The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
Return true if the array has at least one valid partition. Otherwise, return false.

Example 1:
Input: nums = [4,4,4,5,6]
Output: true
Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
This partition is valid, so we return true.

Example 2:
Input: nums = [1,1,1,2]
Output: false
Explanation: There is no valid partition for this array.

Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
*/

/*
Approach: Top-down Memoized DP over Partition Start Index
Goal:
- Determine whether the array can be split into
  contiguous subarrays where each one is valid:
  2 equal elements, 3 equal elements, or 3
  consecutive increasing elements (difference 1).
Core Idea:
- dfs(index) = true if nums[index..n-1] can be
  fully partitioned into valid subarrays.
- At each index, try the three valid block shapes
  and recurse past the block; any success makes
  the state true.
- Memoize per index with a Boolean[] (null =
  uncomputed) so each suffix is resolved once.
Algorithm Steps:
1. If index == n, return true (fully partitioned).
2. Return dp[index] if already computed.
3. If nums[index] == nums[index + 1], try
   dfs(index + 2).
4. If three elements are equal, or form
   x, x + 1, x + 2, try dfs(index + 3).
5. Cache and return the OR of all attempts.
Time Complexity:
- O(n)
n states, each does O(1) work with at most 2
recursive calls.
Space Complexity:
- O(n)
dp array plus recursion stack depth.
Result:
- Returns true if a valid partition exists, false
  otherwise.
*/

package DynamicProgramming.Medium;

// Solution Class
class Solution {
  // Method to find if the array has at least one valid partition
  public boolean validPartition(int[] nums) {
    // Call the helper dfs method
    return this.dfs(0, nums, new Boolean[nums.length]);
  }

  // Helper method for the dfs
  private boolean dfs(int index, int[] nums, Boolean[] dp) {
    // If index is equal to the nums length then return true
    if (index == nums.length) {
      return true;
    }

    // If we have seen the dp array then return the value for the dp array
    if (dp[index] != null) {
      return dp[index];
    }

    // Initialize the result variable
    boolean result = false;

    // Check for the condtions
    if (index < nums.length - 1 && nums[index] == nums[index + 1]) {
      result |= this.dfs(index + 2, nums, dp);
    }

    if (index < nums.length - 2) {
      if ((nums[index] == nums[index + 1] && nums[index + 1] == nums[index + 2]) ||
          (nums[index] + 1 == nums[index + 1] && nums[index + 1] == nums[index + 2] - 1)) {
        result |= this.dfs(index + 3, nums, dp);
      }
    }

    // Return the result and add it to the dp array
    return dp[index] = result;
  }
}

public class _2369_Check_if_There_is_a_Valid_Partition_For_The_Array {
  // Main method to test validPartition
  public static void main(String[] args) {
    int[] nums = new int[] { 4, 4, 4, 5, 6 };

    boolean result = new Solution().validPartition(nums);

    System.out.println("The array has" + (result ? " a " : " not a ") + "valid partition.");
  }
}