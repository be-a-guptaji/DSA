/*
LeetCode Problem: https://leetcode.com/problems/house-robber-ii/

Question: 213. House Robber II

Problem Statement: You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:
Input: nums = [1,2,3]
Output: 3

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
*/

/*
Approach:
This solution handles the "House Robber II" problem, where the houses are arranged in a **circular manner**.
This changes the rules: **you cannot rob both the first and last houses**, since they are adjacent.

Key Idea:
- You cannot rob both the first and last house.
- So, split the problem into two scenarios:
   1. Rob houses from index 0 to n-2 (exclude last house)
   2. Rob houses from index 1 to n-1 (exclude first house)
- The final result is the **maximum** of these two scenarios.

Steps:
1. If there's only one house, return its value — you can safely rob it.
2. Otherwise, compute:
   - `rob1 = max money from houses 0 to n-2` (exclude last)
   - `rob2 = max money from houses 1 to n-1` (exclude first)
3. Return `Math.max(rob1, rob2)`

Helper Method (`house_robber`):
- Implements the regular House Robber DP using space optimization.
- At each house `i`, choose:
   - Rob this house: `prev2 + nums[i]`
   - Skip this house: `prev1`
   - Update `prev2` and `prev1` accordingly.

Time Complexity: O(n), where n is the number of houses — two passes through the array.
Space Complexity: O(1), uses constant space.
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

public class _213_House_Robber_II {
  // Method to find the maximum amount of money you can rob tonight without
  // alerting the police
  public static int rob(int[] nums) {
    // Initialize the size of the array
    int size = nums.length;

    // If the length is one return the first index
    if (size == 1) {
      return nums[0];
    }

    // Return the max of 0 to size - 1 and 1 to size
    return Math.max(house_robber(Arrays.copyOfRange(nums, 0, size - 1)),
        house_robber(Arrays.copyOfRange(nums, 1, size)));
  }

  // Helper method to find the maximum amount of money you can rob
  private static int house_robber(int[] nums) {
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
    int[] nums = { 200 };

    int result = rob(nums);

    System.out.println("The maximum amount of money you can rob tonight without alerting the police is : " + result);
  }
}
