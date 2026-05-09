/*
LeetCode Problem: https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/

Question: 1685. Sum of Absolute Differences in a Sorted Array

Problem Statement: You are given an integer array nums sorted in non-decreasing order.

Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.

In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).

Example 1:
Input: nums = [2,3,5]
Output: [4,3,5]
Explanation: Assuming the arrays are 0-indexed, then
result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.

Example 2:
Input: nums = [1,4,6,8,10]
Output: [24,15,13,15,21]

Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] <= nums[i + 1] <= 10^4
*/

/*
Approach: Prefix Sum + Suffix Sum

Goal:
- Compute the sum of absolute differences between nums[i]
  and all other elements for every index.

Core Idea:
- Since nums is sorted:
   - Elements on the left contribute:
       nums[i] * countLeft - leftSum
   - Elements on the right contribute:
       rightSum - nums[i] * countRight
- Use prefix and suffix sums to compute these values efficiently.

Algorithm Steps:
1. Build prefixSum array:
   - prefixSum[i] = sum of nums[0..i]
2. Build suffixSum array:
   - suffixSum[i] = sum of nums[i..n-1]
3. For each index i:
   - leftContribution =
       i * nums[i] - prefixSum[i-1]
   - rightContribution =
       suffixSum[i+1] - (n-i-1) * nums[i]
4. result[i] =
   leftContribution + rightContribution
5. Return result array.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns array where result[i] is the sum of absolute
  differences between nums[i] and all other elements.
*/

package Arrays.Medium;


import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the summation of absolute differences between nums[i] and all
  // the other elements in the array
  public int[] getSumAbsoluteDifferences(int[] nums) {
    // Initialzie the length of the nums array
    int length = nums.length;

    // Initialize the prefixSum, suffixSum and the result array
    int[] prefixSum = new int[length];
    int[] suffixSum = new int[length];
    int[] result = new int[length];

    // Set the first and the last value of the prefixSum and the suffixSum
    prefixSum[0] = nums[0];
    suffixSum[length - 1] = nums[length - 1];

    // Fill the prefixSum and the suffixSum
    for (int i = 1; i < length; i++) {
      prefixSum[i] = prefixSum[i - 1] + nums[i];
      suffixSum[length - i - 1] = suffixSum[length - i] + nums[length - i - 1];
    }

    // Iterate over the nums array to find the absolute differences
    for (int i = 0; i < length; i++) {
      // Get the left and the right sum
      int leftSum = i > 0 ? i * nums[i] - prefixSum[i - 1] : 0;
      int rightSum = i < length - 1 ? suffixSum[i + 1] - (length - i - 1) * nums[i] : 0;

      // Update the result
      result[i] = leftSum + rightSum;
    }

    // Return the result
    return result;
  }
}

public class _1685_Sum_of_Absolute_Differences_in_a_Sorted_Array {
  // Main method to test getSumAbsoluteDifferences
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 4, 6, 8, 10 };

    int[] result = new Solution().getSumAbsoluteDifferences(nums);

    System.out
        .println("The summation of absolute differences between nums[i] and all the other elements in the array is : "
            + Arrays.toString(result));
  }
}
