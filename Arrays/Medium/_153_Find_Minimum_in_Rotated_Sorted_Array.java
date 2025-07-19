/*
LeetCode Problem: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

Question: 153. Find Minimum in Rotated Sorted Array

Problem Statement: Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 

Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.
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

public class _153_Find_Minimum_in_Rotated_Sorted_Array {
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

  // Main method to test findMin
  public static void main(String[] args) {
    int[] nums = { 4, 5, 6, 7, 0, 1, 2 };

    int result = findMin(nums);

    System.out.println("The index of the target in the array is : " + result);
  }
}
