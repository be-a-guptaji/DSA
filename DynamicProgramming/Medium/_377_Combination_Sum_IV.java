/*
LeetCode Problem: https://leetcode.com/problems/combination-sum-iv/

Question: 377. Combination Sum IV

Problem Statement: Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

Example 1:
Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.

Example 2:
Input: nums = [9], target = 3
Output: 0

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000

Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
*/

/*
Approach:
We use **Dynamic Programming with Memoization (Top-Down)** to count the total number of combinations that sum up to the target.

1. Create a dp array of size `target + 1` where `dp[i]` will store the number of combinations that add up to `i`.
2. Initialize `dp[0] = 1`, since there's one way to reach target 0 — by choosing nothing.
3. For each target value from 1 to the actual `target`, recursively calculate combinations using the given `nums` array.
4. For each number in `nums`, if `num <= target`, we add the number of combinations to form `target - num` to `dp[target]`.
5. Use memoization to avoid recomputation.

Time Complexity: O(n * target), where `n` is the length of `nums`. Each target subproblem is solved once, and for each, we iterate over `nums`.
Space Complexity: O(target) for the `dp` array used in memoization.
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

public class _377_Combination_Sum_IV {
  // Method to find the total combination for the target
  public static int combinationSum4(int[] nums, int target) {
    // Sort the nums array
    Arrays.sort(nums);

    // Initialize a dp array for the combination values
    int[] dp = new int[target + 1];

    // Fill the dp array with the -1
    Arrays.fill(dp, -1);

    // Set dp[0] to 1
    dp[0] = 1;

    // Call the findTargetCombinations method and return it
    return findTargetCombinations(nums, target, dp);
  }

  // Helper function to find the dp[target]
  private static int findTargetCombinations(int[] nums, int target, int[] dp) {
    // If target is negative return 0
    if (target < 0) {
      return 0;
    }

    // If dp[target] has some previous value then reuse it
    if (dp[target] != -1) {
      return dp[target];
    }

    // Get the size of the nums
    int size = nums.length;

    // For accumulating set dp[target] to 0
    dp[target] = 0;

    // Iterate over the nums array until the target is less then the target
    for (int i = 0; i < size; i++) {
      // If nums[i] is greater than the target then break out of the loop
      if (nums[i] > target) {
        break;
      }

      // Fill the dp[i] by using the recursion
      dp[target] += findTargetCombinations(nums, target - nums[i], dp);
    }

    // Retrun the dp[target]
    return dp[target];
  }

  // Main method to test combinationSum4
  public static void main(String[] args) {
    int[] nums = { 3, 2, 1 };
    int target = 4;

    int result = combinationSum4(nums, target);

    System.out.println("The total combination for the " + target + " is : " + result);
  }
}
