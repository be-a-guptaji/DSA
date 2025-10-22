/*
LeetCode Problem: https://leetcode.com/problems/partition-equal-subset-sum/

Question: 416. Partition Equal Subset Sum

Problem Statement: You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:
Input: nums = [1], target = 1
Output: 1

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
*/

/*
Approach:
1. Calculate the total sum of the array.
2. If the total sum is odd, it's impossible to divide the array into two equal subsets.
3. Otherwise, use recursion + memoization (top-down DP) to check if a subset exists whose sum is equal to totalSum / 2.

Time Complexity: O(n * sum), where n = number of elements, sum = totalSum / 2.
Space Complexity: O(n * sum) for memoization.
*/

package DynamicProgramming.Medium;

public class _416_Partition_Equal_Subset_Sum {
  // Method to find if we can divide the array into two subset of equal sum or not
  public static boolean canPartition(int[] nums) {
    // Initialize sum variable to find the total sum of the array
    int sum = 0;

    // Iterate over the nums array to find the sum of all the numbers
    for (int num : nums) {
      sum += num;
    }

    // If sum of all the numbers is odd then return false
    if (sum % 2 == 1) {
      return false;
    }

    // Get the half of the sum
    sum /= 2;

    // Initialize a dp array for the memoization
    Boolean[][] dp = new Boolean[nums.length + 1][sum + 1];

    // Return the canDivide answer which is a recursive method
    return canDivide(nums, 0, nums.length, sum, dp);
  }

  // Helper method to find if we can divide the array into to equal subset or not
  private static boolean canDivide(int[] nums, int index, int length, int target, Boolean[][] dp) {
    // If index goes out of bound or target goes negative then return false
    if (index == length || target < 0) {
      return false;
    }

    // If target become zero then return true
    if (target == 0) {
      return true;
    }

    // If the dp[index][target] has true value then return true
    if (dp[index][target] != null) {
      return dp[index][target];
    }

    // Return the dp[index][target] which have the or of leave the current number
    // for subset or take the number for the subset
    return dp[index][target] = canDivide(nums, index + 1, length, target, dp)
        || canDivide(nums, index + 1, length, target - nums[index], dp);
  }

  // Main method to test canPartition
  public static void main(String[] args) {
    int[] nums = { 1, 2, 3, 4 };

    boolean result = canPartition(nums);

    System.out.println("We can " + (result ? "" : "not ") + "divide the array into two subset of equal sum.");
  }
}
