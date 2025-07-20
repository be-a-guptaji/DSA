/*
LeetCode Problem: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

Question: 153. Find Minimum in Rotated Sorted Array

Problem Statement:
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
- [4,5,6,7,0,1,2] if it was rotated 4 times.
- [0,1,2,4,5,6,7] if it was rotated 7 times (same as 0 times, i.e., full rotation).

Given a rotated sorted array of unique elements, return the minimum element.

You must write an algorithm that runs in O(log n) time.

Examples:
Input: nums = [3,4,5,1,2]
Output: 1

Input: nums = [4,5,6,7,0,1,2]
Output: 0

Input: nums = [11,13,15,17]
Output: 11

Constraints:
- 1 <= nums.length <= 5000
- -5000 <= nums[i] <= 5000
- All elements are unique.
- The array was originally sorted in ascending order, then rotated.
*/

/*
Approach:
We use a modified binary search to find the minimum element in a rotated sorted array.

1. Initialize two pointers: `left` at 0 and `right` at nums.length - 1.
2. While left < right:
   - Compute mid = left + (right - left) / 2
   - If nums[mid] > nums[right], this means the smallest value is in the right half (excluding mid), so set left = mid + 1
   - Else, the minimum must be in the left half (including mid), so set right = mid
3. Loop ends when left == right, which points to the smallest element.

Why this works:
- A fully sorted array (not rotated) will also work correctly.
- This approach effectively narrows the search space logarithmically using the sorted half.

Time Complexity: O(log n)
- Each iteration halves the search space (binary search).

Space Complexity: O(1)
- Constant space is used (no recursion or additional data structures).
*/

package Arrays.Medium;

public class _153_Find_Minimum_in_Rotated_Sorted_Array {
  // Method to find the minimum element in the rotated sorted array
  public static int findMin(int[] nums) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    // Retrun the minimum element
    return nums[left];
  }

  // Main method to test findMin s 
  public static void main(String[] args) {
    int[] nums = { 3, 4, 5, 1, 2, 0 };

    int result = findMin(nums);

    System.out.println("The minimum element of array is : " + result);
  }
}
