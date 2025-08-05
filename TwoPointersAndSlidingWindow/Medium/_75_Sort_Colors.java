/*
LeetCode Problem: https://leetcode.com/problems/sort-colors/

Question: 75. Sort Colors

Problem Statement: Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.

Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */

 /*
Approach: Sliding Window

Key Idea:
- Maintain a window [left, right] such that the number of 0s in this window is at most k.
- Expand the window by moving `right`.
- If the number of 0s exceeds `k`, shrink the window from the left until it becomes valid again.
- At each step, keep track of the maximum window size encountered.

Time Complexity: O(n), where n = length of the array.
Space Complexity: O(1)
 */
package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _75_Sort_Colors {

    // Method to find the maximum consecutive ones by replacing k 0's
    public static void sortColors(int[] nums) {
        // Initialize the array for tracking the variable
        int low = 0, mid = 0, high = nums.length - 1;

        // Logic for sorting
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low++, mid++);
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high--);
            }
        }
    }

    // Helper funcition to swap the variable in place
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Main method to test sortColors
    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};

        sortColors(nums);

        System.out.println("The sorted colors are array is : " + Arrays.toString(nums));
    }
}
