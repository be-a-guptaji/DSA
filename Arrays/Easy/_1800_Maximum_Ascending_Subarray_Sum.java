/*
LeetCode Problem: https://leetcode.com/problems/maximum-ascending-subarray-sum/description/

Question: 1800. Maximum Ascending Subarray Sum

Problem Statement: Given an array of positive integers nums, return the maximum possible sum of an strictly increasing subarray in nums.

A subarray is defined as a contiguous sequence of numbers in an array.

Example 1:
Input: nums = [10,20,30,5,10,50]
Output: 65
Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.

Example 2:
Input: nums = [10,20,30,40,50]
Output: 150
Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.

Example 3:
Input: nums = [12,17,15,13,10,11,12]
Output: 33
Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
*/

/*
Approach: Single-Pass Accumulation for Strictly Increasing Subarrays

Goal:
Find the maximum possible sum of any strictly increasing contiguous subarray.

Key Idea:
Accumulate the sum while the sequence is strictly increasing.
When the increase breaks, reset the sum starting from the current element.

Algorithm:
1. Initialize:
   - currentSum = nums[0]
   - result = currentSum
2. Traverse the array from index 1:
   - If nums[i] > nums[i − 1]:
       • currentSum += nums[i]
   - Else:
       • Reset currentSum = nums[i]
   - Update result = max(result, currentSum)
3. Return result.

Why It Works:
- Each strictly increasing subarray is processed exactly once.
- Reset ensures only valid increasing sequences are counted.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

import java.util.Arrays;

public class _1800_Maximum_Ascending_Subarray_Sum {
    // Method to find the maximum possible sum of an strictly increasing subarray in
    // nums
    public static int maxAscendingSum(int[] nums) {
        // Initialize the maximum sum of the sub array and the result variable
        int maximumSubArray = nums[0], result = maximumSubArray;

        // Iterate over the nums array to find the max sum
        for (int i = 1; i < nums.length; i++) {
            // If last value is strictly less then the current value then add the current
            // value to the maximumSubArray
            if (nums[i - 1] < nums[i]) {
                maximumSubArray += nums[i];
            }
            // Reset max sub array if last value is less then or equal to the current value
            // and reinitialize the maximumSubArray with the current value
            else {
                maximumSubArray = nums[i];
            }

            // Update the result
            result = Math.max(result, maximumSubArray);
        }

        // Retrun the result
        return result;
    }

    // Main method to test maxAscendingSum
    public static void main(String[] args) {
        int[] nums = new int[] { 12, 17, 15, 13, 10, 11, 12 };

        int result = maxAscendingSum(nums);

        System.out.println("The maximum possible sum of an strictly increasing subarray in " + Arrays.toString(nums)
                + " is : " + result);
    }
}
