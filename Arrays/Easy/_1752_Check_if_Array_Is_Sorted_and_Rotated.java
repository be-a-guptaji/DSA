/*
LeetCode Problem: https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/

Question: 1752. Check if Array Is Sorted and Rotated

Problem Statement: Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

There may be duplicates in the original array.

Note: An array A rotated by x positions results in an array B of the same length such that B[i] == A[(i+x) % A.length] for every valid index i.

Example 1:
Input: nums = [3,4,5,1,2]
Output: true
Explanation: [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 2 positions to begin on the element of value 3: [3,4,5,1,2].

Example 2:
Input: nums = [2,1,3,4]
Output: false
Explanation: There is no sorted array once rotated that can make nums.

Example 3:
Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
 */

/*
Approach: Count Order Breaks in Circular Array

Goal:
Check whether the array is sorted in non-decreasing order and possibly rotated.

Key Idea:
In a sorted and rotated array, there can be at most one place where
nums[i] > nums[i + 1] (considering circular order).

Algorithm:
1. Initialize a counter `count = 0`.
2. Traverse the array from index 0 to n − 1:
   - Compare nums[i] with nums[(i + 1) % n].
   - If nums[i] > nums[next]:
       • Increment count.
       • If count > 1, return false.
3. If the loop finishes with count ≤ 1, return true.

Why It Works:
- A fully sorted array has zero breaks.
- A rotated sorted array has exactly one break.
- More than one break means it cannot be sorted and rotated.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1752_Check_if_Array_Is_Sorted_and_Rotated {
    // Method to find if array is sorted and rotated
    public static boolean check(int[] nums) {
        // Initialize count for rotation and nums length
        int count = 0, N = nums.length;

        // Iterate over the nums array
        for (int i = 0; i < N; i++) {
            // If we found rotation then increment the count and if it is more than one then
            // return false
            if (nums[i] > nums[(i + 1) % N] && ++count > 1) {
                return false;
            }
        }

        // Return true in the end
        return true;
    }

    // Main method to test check
    public static void main(String[] args) {
        int[] nums = new int[] { 3, 4, 5, 1, 2 };

        boolean result = check(nums);

        System.out.println("The array is" + (result ? " " : " not ") + "sorted and rotated.");
    }
}
