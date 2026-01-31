/*
LeetCode Problem: https://leetcode.com/problems/sort-array-by-parity/

Question: 905. Sort Array By Parity

Problem Statement: Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.

Example 1:
Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Example 2:
Input: nums = [0]
Output: [0]

Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000
 */

/*
Approach: Two-Pointer Partitioning (In-Place)

Goal:
Rearrange the array so that all even numbers come first,
followed by all odd numbers. Order within groups does not matter.

Key Idea:
- Use two pointers:
  • left  → scans from the beginning
  • right → scans from the end
- Even numbers should stay on the left, odd numbers should move to the right.

Algorithm:
1. Initialize:
   left = 0
   right = nums.length - 1
2. While left < right:
   - If nums[left] is odd:
       • Swap nums[left] with nums[right]
       • Decrement right
   - Else (nums[left] is even):
       • Increment left
3. Continue until pointers cross.
4. Return the modified array.

Why It Works:
- Each swap pushes an odd number toward the right side.
- Even numbers naturally accumulate on the left.
- In-place swaps avoid extra memory usage.

Time Complexity:
- O(n) — each element is processed at most once.

Space Complexity:
- O(1) — in-place rearrangement, no extra space.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

public class _905_Sort_Array_By_Parity {
    // Method to move all the even integers at the beginning of the array followed
    // by all the odd integers
    public static int[] sortArrayByParity(int[] nums) {
        // Initialize two pointer left and right pointer
        int left = 0, right = nums.length - 1;

        // Iterate over the nums array untill the pointer crosses each other
        while (left < right) {
            if ((nums[left] & 1) == 1) {
                // Swap the variable
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                // Decrement the right pointer
                right--;
            } else {
                // Increment the left pointer
                left++;
            }
        }

        // Retrun nums in the end
        return nums;
    }

    // Main method to test sortArrayByParity
    public static void main(String[] args) {
        int[] nums = new int[] { 3, 1, 2, 4 };

        int[] result = sortArrayByParity(nums);

        System.out.println(
                "The array when move all the even integers at the beginning of the array followed by all the odd integers is : "
                        + Arrays.toString(result));
    }
}
