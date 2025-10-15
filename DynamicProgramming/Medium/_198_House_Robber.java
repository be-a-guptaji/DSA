/*
LeetCode Problem: https://leetcode.com/problems/house-robber/

Question: 198. House Robber

Problem Statement: You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

/*
Approach:
We solve the "House Robber" problem using **Dynamic Programming** with **space optimization**.

Key Idea:
- You cannot rob two adjacent houses.
- At every house, you have two choices:
  a. Rob this house → add its value to the max loot from i-2 houses before.
  b. Skip this house → carry forward the loot from i-1 houses before.
- Choose the option with the maximum total loot.

Steps:
1. Edge Case Handling:
   - If `nums` is null or empty → return 0.
   - If `nums.length == 1` → return nums[0] (only one house to rob).

2. Initialize:
   - `prev2` = nums[0] → max money robbed from house 0 (i - 2).
   - `prev1` = max(nums[0], nums[1]) → max money from first two houses (i - 1).

3. Iterate through the array starting from index 2:
   - For each house `i`, calculate:
     `curr = max(prev1, prev2 + nums[i])`
     → Either rob this house (`prev2 + nums[i]`) or skip it (`prev1`).
   - Update `prev2` and `prev1` accordingly.

4. Return `prev1`, which holds the max loot at the end.

Time Complexity: O(n) — traverses the array once.
Space Complexity: O(1) — uses only two variables for tracking state.
*/

package DynamicProgramming.Medium;

public class _198_House_Robber {
  // Method to find the maximum amount of money you can rob tonight without
  // alerting the police
  public static int rob(int[] nums) {
    // Initialize the size of the array
    int size = nums.length;

    // If the length is one return the first index
    if (size == 1) {
      return nums[0];
    }

    // Initialize the dp variable
    int prev2 = nums[0];
    int prev1 = Math.max(nums[0], nums[1]);

    // Iterate over the array to get the maximum amount of money you can rob tonight
    for (int i = 2; i < size; i++) {
      int curr = Math.max(prev1, prev2 + nums[i]);
      prev2 = prev1;
      prev1 = curr;
    }

    // Return the prev1 value
    return prev1;
  }

  // Main method to test rob
  public static void main(String[] args) {
    int[] nums = { 2, 7, 9, 3, 1 };

    int result = rob(nums);

    System.out.println("The maximum amount of money you can rob tonight without alerting the police is : " + result);
  }
}
