/*
LeetCode Problem: https://leetcode.com/problems/maximum-subarray-min-product/

Question: 1856. Maximum Subarray Min-Product

Problem Statement: The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.

For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 10^9 + 7.

Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1,2,3,2]
Output: 14
Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
2 * (2+3+2) = 2 * 7 = 14.

Example 2:
Input: nums = [2,3,3,1,2]
Output: 18
Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
3 * (3+3) = 3 * 6 = 18.

Example 3:
Input: nums = [3,1,5,6,4,2]
Output: 60
Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
4 * (5+6+4) = 4 * 15 = 60.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^7
*/

/*
Approach: Monotonic Increasing Stack with Prefix Sums
Goal:
- Find the maximum min-product over all non-empty
  subarrays, where min-product = (min element) *
  (subarray sum), returned modulo 1e9 + 7.
Core Idea:
- For each element taken as the subarray minimum,
  the best subarray is the widest one in which it
  stays the minimum, since all values are positive.
- A monotonic increasing stack stores
  {leftmost start index, value}. When a smaller
  value arrives, popped entries have their right
  boundary fixed at i; their left boundary is the
  stored start index.
- Prefix sums give each range sum in O(1).
- The max is tracked in a long and the modulo is
  applied only once at the end, so comparisons stay
  exact.
Algorithm Steps:
1. Build prefixSum[n + 1].
2. For each i, set newIndex = i; while the stack top
   value > nums[i]:
   - Pop {index, value}.
   - result = max(result, value * (prefixSum[i] -
     prefixSum[index])).
   - newIndex = index (the new element inherits the
     popped start).
3. Push {newIndex, nums[i]}.
4. Pop remaining entries with right boundary n and
   update result with prefixSum[n] - prefixSum[index].
5. Return (int) (result % 1_000_000_007).
Time Complexity:
- O(n)
each element is pushed and popped at most once.
Space Complexity:
- O(n)
prefix sum array and stack.
Result:
- Returns the maximum min-product modulo 1e9 + 7.
*/

package DynamicProgramming.Medium;

// Solution Class
class Solution {
  // Method to find the maximum min-product of any non-empty subarray of nums
  public int maxSumMinProduct(int[] nums) {
    // Get the nums array length
    int length = nums.length;

    // Initialize the prefixSum array
    long[] prefixSum = new long[length + 1];

    // Fill the prefixSum array
    for (int i = 0; i < length; i++) {
      prefixSum[i + 1] = prefixSum[i] + nums[i];
    }

    // Initialize the stack matrix
    int[][] stack = new int[length][2];

    // Initialize the stackPointer
    int stackPointer = -1;

    // Initialize the result variable
    long result = Long.MIN_VALUE;

    // Iterate over the nums array
    for (int i = 0; i < length; i++) {
      // Initailize the newIndex
      int newIndex = i;

      // Check if new number can be put on the stack
      while (stackPointer != -1 && stack[stackPointer][1] > nums[i]) {
        // Get the last stack index and value
        int index = stack[stackPointer][0];
        int value = stack[stackPointer--][1];

        // Update the result variable
        result = Math.max(value * (prefixSum[i] - prefixSum[index]), result);

        // Update the newIndex
        newIndex = index;
      }

      // Add the new value to the stack
      stack[++stackPointer][0] = newIndex;
      stack[stackPointer][1] = nums[i];
    }

    // Get the remaining values from the stack
    while (stackPointer != -1) {
      // Get the last stack index and value
      int index = stack[stackPointer][0];
      int value = stack[stackPointer--][1];

      // Update the result variable
      result = Math.max(value * (prefixSum[length] - prefixSum[index]), result);
    }

    // Return the result
    return (int) (result % 1_000_000_007);
  }
}

public class _1856_Maximum_Subarray_Min_Product {
  // Main method to test maxSumMinProduct
  public static void main(String[] args) {
    int[] nums = new int[] { 2, 3, 3, 1, 2 };

    int result = new Solution().maxSumMinProduct(nums);

    System.out.println("The maximum min-product of any non-empty subarray of nums is : " + result);
  }
}