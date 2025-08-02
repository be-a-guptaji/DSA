/*
LeetCode Problem: https://leetcode.com/problems/move-zeroes/

Problem: 283. Move Zeroes

Problem Statement:
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note: You must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]

Constraints:
- 1 <= nums.length <= 10^4
- -2^31 <= nums[i] <= 2^31 - 1

Follow up: Could you minimize the total number of operations done?
*/

/*
Approach: Two-pointer technique.

- Use a pointer `insertPos` to track the position to place the next non-zero element.
- Traverse the array:
    - When a non-zero element is found, assign it to `nums[insertPos]` and increment `insertPos`.
- After placing all non-zero elements, fill the rest of the array with zeroes starting from `insertPos`.

This approach ensures in-place modification with minimal operations.

Time Complexity: O(n) — One pass to move non-zero elements and one pass (worst-case) to fill zeroes.
Space Complexity: O(1) — No additional space used.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

public class _283_Move_Zeroes {
    public static void moveZeroes(int[] nums) {
        int insertPos = 0;

        // First pass: move non-zero elements forward
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }

        // Second pass: fill remaining positions with zeros
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    // Main method to test moveZeroes
    public static void main(String[] args) {
        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };

        moveZeroes(nums);

        System.out.println("The array after moving all the zero's to the right is: " + Arrays.toString(nums));
    }
}
