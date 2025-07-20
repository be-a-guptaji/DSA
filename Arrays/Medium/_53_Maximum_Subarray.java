/*
LeetCode Problem: https://leetcode.com/problems/maximum-subarray/

Question: 53. Maximum Subarray

Problem Statement: Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

/*
Approach: In the given solution, the approach follows Kadane’s Algorithm to find the subarray with the largest sum in linear time. The method initializes two variables: maxSum, which tracks the maximum sum found so far, and sumOfSubArray, which keeps the running sum of the current subarray. Starting from the second element, the algorithm checks if the current running sum (sumOfSubArray) is positive and would increase by adding the current element. If so, it adds the element to the running sum; otherwise, it starts a new subarray from the current element. After each update, the algorithm compares sumOfSubArray with maxSum and updates maxSum if a higher sum is found. This ensures that the algorithm always keeps track of the best possible subarray sum seen so far. The solution efficiently handles all cases in a single pass through the array, achieving O(n) time complexity and O(1) space complexity.

Time Complexity:  O(n), where n = length of array.
Space Complexity: O(1).
 */

package Arrays.Medium;

public class _53_Maximum_Subarray {
    // Method to find the largest sum of the subarray
    public static int maxSubArray(int[] nums) {
        // Initialize maxSum to track the largest sum of the subarray
        int maxSum = nums[0], sumOfSubArray = maxSum;

        // Iterating over the array
        for (int i = 1; i < nums.length; i++) {
            if (sumOfSubArray + nums[i] > 0 && sumOfSubArray > 0) {
                sumOfSubArray += nums[i];
            } else {
                sumOfSubArray = nums[i];
            }

            // If the maxSum is less than the sumOfSubArray then update the maxSum
            if (maxSum < sumOfSubArray) {
                maxSum = sumOfSubArray;
            }
        }

        // Return the maxSum
        return maxSum;
    }

    // Main method to test maxSubArray
    public static void main(String[] args) {
        int[] nums = { -2, 3, 1, 3 };

        int result = maxSubArray(nums);

        System.out.println("The sum of largest sum of any subarray is : " + result);
    }
}
