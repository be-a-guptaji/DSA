/*
LeetCode Problem: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

Question: 81. Search in Rotated Sorted Array II

Problem Statement: There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
nums is guaranteed to be rotated at some pivot.
-10^4 <= target <= 10^4

Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
*/

/*
Approach: Modified Binary Search (Handling Rotation and Duplicates)

Goal:
- Determine whether a target exists in a rotated sorted array
  that may contain duplicates.

Core Idea:
- At least one half (left or right) is sorted unless duplicates obscure it.
- Use binary search to identify the sorted half and check
  whether the target lies within it.
- When duplicates prevent identification (nums[left] == nums[mid]),
  increment left to shrink the search space.

Algorithm Steps:
1. Initialize left = 0 and right = n - 1.
2. While left <= right:
   - Compute mid.
   - If nums[mid] == target → return true.
3. If left half is sorted (nums[left] < nums[mid]):
   - If target lies in [nums[left], nums[mid)):
       → search left half.
   - Else:
       → search right half.
4. Else if right half is sorted (nums[left] > nums[mid]):
   - If target lies in (nums[mid], nums[right]]:
       → search right half.
   - Else:
       → search left half.
5. Else (nums[left] == nums[mid]):
   - Increment left to handle duplicates.
6. If not found, return false.

Time Complexity:
- Average: O(log n)
- Worst case: O(n) (due to duplicates)

Space Complexity:
- O(1)

Result:
- Returns true if target exists, otherwise false.
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
    // Method to determine if the target is in rotated sorted array or not
    public boolean search(int[] nums, int target) {
        // Initialize the left and right bound
        int left = 0, right = nums.length - 1;

        // Iterate over the array
        while (left <= right) {
            // Get the mid value
            int mid = left + (right - left) / 2;

            // If mid is equal to target then return true
            if (nums[mid] == target) {
                return true;
            }

            // Update the pointers accordingly
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                left++;
            }
        }

        // Return false in the end
        return false;
    }
}

public class _81_Search_in_Rotated_Sorted_Array_II {
    // Main method to test search
    public static void main(String[] args) {
        int[] nums = new int[] { 2, 5, 6, 0, 0, 1, 2 };
        int target = 0;

        boolean result = new Solution().search(nums, target);

        System.out.println("The target is" + (result ? " " : " not ") + "in rotated sorted array.");
    }
}