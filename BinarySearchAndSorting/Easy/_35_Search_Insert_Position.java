/*
LeetCode Problem: https://leetcode.com/problems/search-insert-position/

Question: 35. Search Insert Position

Problem Statement: Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

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
Approach: We solve this problem using **binary search** to minimize the number of API calls to `isBadVersion(version)`.

- Start with a search range of [1, n].
- Repeatedly check the middle version:
  - If it's bad, the first bad version must be at or before it (move the right pointer).
  - If it's good, the first bad version must be after it (move the left pointer).
- When left == right, we've found the first bad version.

This approach works efficiently even when n is large (up to 2^31 - 1).

Time Complexity: O(log n) — Binary search cuts the range in half each time.
Space Complexity: O(1) — Constant space is used.
 */

package BinarySearchAndSorting.Easy;

public class _35_Search_Insert_Position {
    // Method to find the index of the target in nums array
    public static int searchInsert(int[] nums, int target) {
        // Initialize variable for tracking
        int left = 0, right = nums.length - 1, mid;

        // Logic as binary search
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Return the value
        return left;
    }

    // Mocking the isBadVersion API
    public static boolean isBadVersion(int n) {
        return n >= 4;
    }

    // Main method to test searchInsert
    public static void main(String[] args) {
        int[] nums = [1,3,5,6];
        int target = 2;

        int result = searchInsert(nums,target);

        System.out.println("The number in the string is : " + result);
    }
}
