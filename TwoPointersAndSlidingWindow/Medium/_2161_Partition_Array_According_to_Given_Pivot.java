/*
LeetCode Problem: https://leetcode.com/problems/partition-array-according-to-given-pivot/

Question: 2161. Partition Array According to Given Pivot

Problem Statement: You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:

Every element less than pivot appears before every element greater than pivot.
Every element equal to pivot appears in between the elements less than and greater than pivot.
The relative order of the elements less than pivot and the elements greater than pivot is maintained.
More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element. If i < j and both elements are smaller (or larger) than pivot, then pi < pj.
Return nums after the rearrangement.

Example 1:
Input: nums = [9,12,5,10,14,3,10], pivot = 10
Output: [9,5,3,10,10,12,14]
Explanation: 
The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.

Example 2:
Input: nums = [-3,4,3,2], pivot = 2
Output: [-3,2,4,3]
Explanation: 
The element -3 is less than the pivot so it is on the left side of the array.
The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.

Constraints:

1 <= nums.length <= 10^5
-10^6 <= nums[i] <= 10^6
pivot equals to an element of nums.
 */

/*
Approach: Two-Pointer Partition with Extra Array

Problem:
Rearrange the array so that:
- All elements < pivot come first
- All elements == pivot come next
- All elements > pivot come last
Relative order within each group is not required.

Core Idea:
- Use a new array to avoid in-place shifting complexity.
- Fill smaller elements from the left.
- Fill larger elements from the right.
- Fill remaining middle positions with the pivot value.

Algorithm:
1. Initialize a new array `newNums` of the same size as `nums`.
2. Use two pointers:
   - `start` → next position for elements < pivot.
   - `end`   → next position for elements > pivot.
3. Traverse the array:
   - If `nums[i] < pivot`, place it at `newNums[start++]`.
   - If `nums[length - i] > pivot`, place it at `newNums[end--]`.
4. After placement, all remaining indices between `start` and `end`
   correspond to elements equal to `pivot`.
5. Fill those indices with `pivot`.
6. Return the rearranged array.

Why It Works:
- Elements smaller than pivot are packed from the left.
- Elements greater than pivot are packed from the right.
- Remaining slots must belong to pivot values.

Time Complexity:
- O(n), single pass over the array.

Space Complexity:
- O(n), uses an auxiliary array.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _2161_Partition_Array_According_to_Given_Pivot {
  // Method to rearrange the nums according to the pivot
  public static int[] pivotArray(int[] nums, int pivot) {
    // Initialize the length of the nums array
    int length = nums.length - 1;

    // Initialize the array of the size of nums
    int[] newNums = new int[length + 1];

    // Intiailize two pointer for the start and end
    int start = 0, end = length;

    // Iterate over the nums array to partition the array
    for (int i = 0; i <= length; i++) {
      // If element is smaller than the pivot then add to the starting of the nums
      if (nums[i] < pivot) {
        newNums[start++] = nums[i];
      }

      // If element is greater than the pivot then add to the ending of the nums
      if (nums[length - i] > pivot) {
        newNums[end--] = nums[length - i];
      }
    }

    // Fill the gap with the pivot element
    for (int i = start; i <= end; i++) {
      newNums[i] = pivot;
    }

    // Return the newNums
    return newNums;
  }

  // Main method to test pivotArray
  public static void main(String[] args) {
    int[] nums = new int[] { 9, 12, 5, 10, 14, 3, 10 };
    int pivot = 10;

    int[] result = pivotArray(nums, pivot);

    System.out.println(
        "The rearrange array is : " + Arrays.toString(result));
  }
}
