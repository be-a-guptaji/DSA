/*
LeetCode Problem: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

Question: 34. Find First and Last Position of Element in Sorted Array

Problem Statement: Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]

Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non-decreasing array.
-10^9 <= target <= 10^9
*/

/*
Approach: This problem can be solved efficiently using **binary search** since the array is sorted.

   - We initialize two pointers: left = 0, right = nums.length - 1
   - We perform binary search to find the target:
   - If nums[mid] == target: return mid
   - If nums[mid] < target: search the right half
   - If nums[mid] > target: search the left half
   - If the target is not found, left will represent the index where the target should be inserted.

   This works because binary search helps us narrow down the position quickly in O(log n) time.
    
Time Complexity: O(log n) — because binary search divides the array in half each iteration
Space Complexity: O(1) — no additional space is used
    */

package BinarySearchAndSorting.Medium;

import java.util.Arrays;

public class _34_Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    // Method to find the index bound of the target
    public static int[] searchRange(int[] nums, int target) {
        // Make a result variable for returning the index
        int[] result = new int[] { -1, -1 };

        // Find the first position of the target
        result[0] = findFirstIndexOfTarget(nums, target);

        // If first index if not found then return the result
        if (result[0] == -1) {
            return result;
        }

        // Find the last position of the target
        result[1] = findLastIndexOfTarget(nums, target);

        // Return the result
        return result;
    }

    // Helper function to find the first index of the target
    public static int findFirstIndexOfTarget(int[] nums, int target) {
        // Initialize the left, right and positional variable
        int left = 0, right = nums.length - 1, firstPosition = -1;

        // Logic to find the first index
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                firstPosition = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Return the firstPosition
        return firstPosition;
    }

    // Helper function to find the last index of the target
    public static int findLastIndexOfTarget(int[] nums, int target) {
        // Initialize the left, right and positional variable
        int left = 0, right = nums.length - 1, lastPositon = -1;

        // Logic to find the last index
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                lastPositon = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Return the lastPositon
        return lastPositon;
    }

    // Main method to test searchRange
    public static void main(String[] args) {
        int[] nums = { 5, 7, 7, 8, 8, 10 };
        int target = 8;

        int[] result = searchRange(nums, target);

        System.out.println("The index at which the target can be found are : " + Arrays.toString(result));
    }
}
