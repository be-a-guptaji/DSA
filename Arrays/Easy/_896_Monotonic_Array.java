/*
LeetCode Problem: https://leetcode.com/problems/monotonic-array/

Question: 896. Monotonic Array

Problem Statement: An array is monotonic if it is either monotone increasing or monotone decreasing.

An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].

Given an integer array nums, return true if the given array is monotonic, or false otherwise.

Example 1:
Input: nums = [1,2,2,3]
Output: true

Example 2:
Input: nums = [6,5,4,4]
Output: true

Example 3:

Input: nums = [1,3,2]
Output: false

Constraints:

1 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5
 */

/*
Approach: Single-Direction Validation

Goal:
Check whether the array is monotonic.
An array is monotonic if it is either entirely non-increasing
or entirely non-decreasing.

Key Idea:
Determine the expected direction (increasing or decreasing)
by comparing the first and last elements, then validate it
with a single pass.

Algorithm:
1. Let n be the length of the array.
2. If nums[0] < nums[n − 1]:
   - The array should be non-decreasing.
   - Traverse from index 1 to n − 1:
       • If nums[i] < nums[i − 1], return false.
3. Otherwise:
   - The array should be non-increasing.
   - Traverse from index 1 to n − 1:
       • If nums[i] > nums[i − 1], return false.
4. If no violations are found, return true.

Why It Works:
- A monotonic array maintains a single ordering direction.
- One linear scan is sufficient to verify consistency.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _896_Monotonic_Array {
    // Method to find if array is monotonic array or not
    public static boolean isMonotonic(int[] nums) {
        // Get the length of the array
        int n = nums.length;

        // If end value is greater then the first value then the array is increasing
        // else decreasing
        if (nums[0] < nums[n - 1]) {
            for (int i = 1; i < n; i++) {
                if (nums[i] < nums[i - 1]) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < n; i++) {
                if (nums[i] > nums[i - 1]) {
                    return false;
                }
            }
        }

        // Return true in the end
        return true;
    }

    // Main method to test isMonotonic
    public static void main(String[] args) {
        int[] nums = new int[] { 3, 4, 5, 1, 2 };

        boolean result = isMonotonic(nums);

        System.out.println("The array is" + (result ? " " : " not ") + "a monotonic array.");
    }
}
