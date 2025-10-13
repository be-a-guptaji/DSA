/*
LeetCode Problem: https://leetcode.com/problems/maximum-product-subarray/

Question: 152. Maximum Product Subarray

Problem Statement: Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Constraints:

1 <= nums.length <= 2 * 10^4
-10 <= nums[i] <= 10
The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
*/

/*
Approach:
We solve the "Maximum Product Subarray" problem using a variation of Kadane’s Algorithm.

Key Idea:
- Unlike the standard maximum sum subarray problem, this problem needs to track both 
  the maximum and minimum product at each index, because a negative number can flip 
  the minimum into the maximum and vice versa.

Steps:
1. Initialize:
   - `maxSoFar` = nums[0] → the global maximum product found so far.
   - `maxEndingHere` = nums[0] → max product ending at current position.
   - `minEndingHere` = nums[0] → min product ending at current position.

2. Traverse the array from index 1:
   - Temporarily store `maxEndingHere` because we need it to calculate the new `minEndingHere`.
   - Update `maxEndingHere` as the max of:
       a. current number
       b. current number * previous `maxEndingHere`
       c. current number * previous `minEndingHere`
   - Similarly, update `minEndingHere` with the min of the same three.
   - Update `maxSoFar` with the max of itself and `maxEndingHere`.

3. Return `maxSoFar` as the result.

Time Complexity: O(n), Since we have traverse the array the only one time.
Space Complexity: O(1), No additional space is needed.
*/

package DynamicProgramming.Medium;

public class _152_Maximum_Product_Subarray {
  // Method to find the subarray which have the maximum product
  public static int maxProduct(int[] nums) {
    // Check the edge case
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // Initialize the maxSoFar, maxEndingHere and minEndingHere with the first index
    // of the array and size of the array
    int maxSoFar = nums[0], maxEndingHere = nums[0], minEndingHere = nums[0], size = nums.length;

    for (int i = 1; i < size; i++) {
      // Get the maxEndingHere
      int temp = maxEndingHere;

      // Update the maxEndingHere and the minEndingHere
      maxEndingHere = Math.max(nums[i], Math.max(maxEndingHere * nums[i], minEndingHere * nums[i]));
      minEndingHere = Math.min(nums[i], Math.min(temp * nums[i], minEndingHere * nums[i]));

      // Update the maxSoFar
      maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }

    // Return the maxSoFar
    return maxSoFar;
  }

  // Main method to test maxProduct
  public static void main(String[] args) {
    int[] nums = { -1, 4, -4, 5, -2, -1, -1, -2, -3 };

    int result = maxProduct(nums);

    System.out.println("The maximum product of the subarray is : " + result);
  }
}
