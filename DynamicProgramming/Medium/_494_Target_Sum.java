/*
LeetCode Problem: https://leetcode.com/problems/target-sum/

Question: 494. Target Sum

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
Approach: We use a recursive Depth-First Search (DFS) with memoization to count
the number of ways to assign '+' or '-' signs to elements in `nums` so that the
resulting sum equals the `target`.

1. At each index, we have two choices:
   - Add the current number to the sum
   - Subtract the current number from the sum
2. We recursively explore both choices and sum the number of valid ways.
3. Memoization is used to avoid redundant computations by caching results
   for each (index, currentSum) pair.
4. This approach reduces the exponential complexity of brute-force to a manageable one.

Time Complexity: O(n * range_of_possible_sums), where `n` is the length of `nums`.
Space Complexity: O(n * range_of_possible_sums) for the memoization cache.
*/

package DynamicProgramming.Medium;

import java.util.HashMap;

public class _494_Target_Sum {
  // Method to find the number of ways we can sum to the target
  public static int findTargetSumWays(int[] nums, int target) {
    // Initialize the Hash map for the memoization
    HashMap<String, Integer> memo = new HashMap<>();

    // Return the countWays answer which is a recursive method
    return countWays(nums, target, 0, 0, memo);
  }

  // Helper method to count the number of ways
  private static int countWays(int[] nums, int target, int index, int currentSum, HashMap<String, Integer> memo) {
    // If the index is equal to length then find the check if the current sum is
    // equal to the target or not if yes then return 1 else 0
    if (nums.length == index) {
      return currentSum == target ? 1 : 0;
    }

    // Use StringBuilder for creating the memoization key
    StringBuilder sb = new StringBuilder();

    sb.append(index).append(',').append(currentSum);

    String key = sb.toString();

    // If the key is present in the Hash map then return the value of the key
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    // Try adding the current number
    int add = countWays(nums, target, index + 1, currentSum + nums[index], memo);

    // Try subtracting the current number
    int subtract = countWays(nums, target, index + 1, currentSum - nums[index], memo);

    // Get the total sum
    int totalSum = add + subtract;

    // Add the key value pair to the Hash map
    memo.put(key, totalSum);

    // Return the sum of the add and subtract
    return totalSum;
  }

  // Main method to test findTargetSumWays
  public static void main(String[] args) {
    int[] nums = { 1, 1, 1, 1, 1 };
    int target = 3;

    int result = findTargetSumWays(nums, target);

    System.out.println("The number of ways we can sum to the target is : " + result);
  }
}
