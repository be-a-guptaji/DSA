/*
LeetCode Problem: https://leetcode.com/problems/binary-search/

Question: 704. Binary Search

Problem Statement: Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Example 2:
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

Constraints:

1 <= nums.length <= 10^4
-10^4 < nums[i], target < 10^4
All the integers in nums are unique.
nums is sorted in ascending order.
*/

/*
Approach: Binary Search

Since the given array is sorted, we use Binary Search to efficiently locate the target.

We maintain two pointers:
1. left – start of the search space.
2. right – end of the search space.

At each step:
- Compute the middle index:
  mid = left + (right - left) / 2
- If nums[mid] == target → return mid.
- If nums[mid] < target → discard the left half by setting left = mid + 1.
- If nums[mid] > target → discard the right half by setting right = mid - 1.

The loop continues until left > right.

If the target is not found, return -1.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

package BinarySearchAndSorting.Easy;

public class _704_Binary_Search {
    // Method to find the index of the target
    public static int search(int[] nums, int target) {
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

        // Target not found, return -1
        return -1;
    }

    // Main method to test searchInsert
    public static void main(String[] args) {
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        int target = 9;

        int result = search(nums, target);

        System.out.println("The index at which the target is found is : " + result);
    }
}
