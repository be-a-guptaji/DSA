/*
LeetCode Problem: https://leetcode.com/problems/search-in-rotated-sorted-array/

Question: 33. Search in Rotated Sorted Array

Problem Statement: There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1

Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-10^4 <= target <= 10^4
 */

/*
Approach:
The solution uses a modified binary search to locate the target in a rotated sorted array.

1. Initialize two pointers, `left` and `right`, to the start and end of the array.
2. While left <= right:
   - Calculate the middle index `mid`.
   - If nums[mid] == target, return mid.
   - Determine which half (left or right) is sorted:
     - If the left half is sorted and the target lies within it, adjust `right`.
     - If the right half is sorted and the target lies within it, adjust `left`.
3. If the target is not found, return -1.

This approach leverages the properties of sorted arrays to maintain a time complexity of O(log n).

Time Complexity: O(log n)
- Each iteration halves the search space, typical of binary search.

Space Complexity: O(1)
- Only constant extra space is used for variables.
*/

package Arrays.Medium;

public class _33_Search_in_Rotated_Sorted_Array {
  // Method to find the index of the element in the rotated sorted array
  public static int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      // Found the target
      if (nums[mid] == target) {
        return mid;
      }

      // Left half is sorted
      if (nums[left] <= nums[mid]) {
        if (nums[left] <= target && target < nums[mid]) {
          right = mid - 1; // Search left
        } else {
          left = mid + 1; // Search right
        }
      }
      // Right half is sorted
      else {
        if (nums[mid] < target && target <= nums[right]) {
          left = mid + 1; // Search right
        } else {
          right = mid - 1; // Search left
        }
      }
    }

    // Target not found
    return -1;
  }

  // Main method to test threeSum
  public static void main(String[] args) {
    int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
    int target = 0;

    int result = search(nums, target);

    System.out.println("The index of the target in the array is : " + result);
  }
}
