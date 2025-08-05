/*
LeetCode Problem: https://leetcode.com/problems/minimum-size-subarray-sum/

Question: 209. Minimum Size Subarray Sum

Problem Statement: Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:
Input: target = 4, nums = [1,4,4]
Output: 1

Example 3:
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

Constraints:

1 <= target <= 10^9
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4

Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */

 /*
Approach:This solution uses the sliding window technique.

Key Idea:
- Start with two pointers: `left` and `right` that define the window.
- Expand the window by moving `right` and keep track of the sum of the current window.
- Once the current window sum becomes >= target:
  - Try to shrink the window from the left to find the minimum possible size.
  - Update the minimum length found so far.

Time Complexity: O(n), where n = length of the input array.
Space Complexity: O(1), since no extra space proportional to input size is used.
 */
package TwoPointersAndSlidingWindow.Medium;

public class _209_Minimum_Size_Subarray_Sum {

    // Method to find the minimum length of the subarray sum greater than or equal to the target
    public static int minSubArrayLen(int target, int[] nums) {
        // Initialize the variable
        int left = 0, minimumSubArrayLen = Integer.MAX_VALUE, currentSum = 0;

        // Logic to find the minimum subarray len
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink the window for getting the minimum length subarray
            while (currentSum >= target) {
                minimumSubArrayLen = Math.min(minimumSubArrayLen, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }

        // Return the minimum subarray length
        return minimumSubArrayLen == Integer.MAX_VALUE ? 0 : minimumSubArrayLen;
    }

    // Main method to test minSubArrayLen
    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};

        int result = minSubArrayLen(target, nums);

        System.out.println("The minimum length of the subarray sum greater than or equal to the target is " + result);
    }
}
