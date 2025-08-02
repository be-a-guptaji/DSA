/*
LeetCode Problem: https://leetcode.com/problems/move-zeroes/

Question: 283. Move Zeroes

Problem Statement: Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]

Constraints:

1 <= nums.length <= 10^4
-2^31 <= nums[i] <= 2^31 - 1

Follow up: Could you minimize the total number of operations done?
*/

/*
Approach: This problem is efficiently solved by using a two-pointer technique, taking advantage of the fact that the array is sorted.

- We initialize a pointer `index = 1` which keeps track of the position to place the next unique element.
- We iterate through the array starting from index 1.
    - If the current element `nums[i]` is not equal to the previous element `nums[i - 1]`, it's unique.
    - We assign `nums[index] = nums[i]` and increment `index`.
- This effectively shifts all unique elements to the front of the array in-place.

After the loop, the first `index` elements in the array are the unique ones.

Time Complexity: O(n) — we iterate through the array once.
Space Complexity: O(1) — the algorithm uses constant extra space.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

public class _283_Move_Zeroes {
    // Method to remove duplicates in a sorted array
    public static void moveZeroes(int[] nums) {
        int insertPos = 0;

        // Move all non-zero elements to the front
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        // Fill the rest with zeros
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    // Main method to test moveZeroes
    public static void main(String[] args) {
        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };

        moveZeroes(nums);

        System.out.println("The array after moving all the zero's to the right is : " + Arrays.toString(nums));
    }
}
