/*
LeetCode Problem: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

Question: 34. Find First and Last Position of Element in Sorted Array

Problem Statement: Given a sorted array of distinct integers and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:
Input: nums = [1,3,5,6], target = 7
Output: 4

Constraints:
1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums contains distinct values sorted in ascending order.
-10^4 <= target <= 10^4
*/

/*
Approach:
This problem can be solved efficiently using **binary search** since the array is sorted.

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

public class _35_Search_Insert_Position {
    // Method to find the index of the target in nums array or where it should be
    // inserted
    public static int searchInsert(int[] nums, int target) {
        // Initialize variable for tracking
        int left = 0, right = nums.length - 1;

        // Logic to find the index
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Target not found, return insertion point
        return left;
    }

    // Main method to test searchInsert
    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 6 };
        int target = 7;

        int result = searchInsert(nums, target);

        System.out.println("The index at which the target can be found or inserted is: " + result);
    }
}
