/*
LeetCode Problem: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

Question: 167. Two Sum II - Input Array Is Sorted

Problem Statement: Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element twice.

Your solution must use only constant extra space.

Example 1:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].

Example 2:
Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].

Example 3:
Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].

Constraints:

2 <= numbers.length <= 3 * 10^4
-1000 <= numbers[i] <= 1000
numbers is sorted in non-decreasing order.
-1000 <= target <= 1000
The tests are generated such that there is exactly one solution.
 */

/*
Approach: Binary Search on Sorted Array

Key Idea:
- The array is assumed to be sorted.
- For each element at index `i`, compute `newTarget = target - numbers[i]`.
- Perform binary search on the subarray from index `i + 1` to `n - 1` to find `newTarget`.
- If found, return the 1-based indices.

Time Complexity: O(n log n)
- Outer loop runs `n` times.
- Binary search runs in `log n` time.

Space Complexity: O(1)
- No extra space is used.
*/

package TwoPointersAndSlidingWindow.Medium;

public class _167_Two_Sum_II_Input_Array_Is_Sorted {
    // Method to find the 1-indexed indices that sum up to the target
    public static int[] twoSum(int[] numbers, int target) {
        // Get the length of the numbers array
        int len = numbers.length;

        // Iterate over the numbers array to find the target
        for (int i = 0; i < len; i++) {
            // Get the new target by subtracting the current index number
            int newTarget = target - numbers[i];

            // Initialize the left to index + 1 and right to len pointer for the binary
            // search
            int left = i + 1, right = len - 1;

            // Search the remaning part of the numbers array through binary search
            while (left <= right) {
                // Get the middle index for the sub array left and right
                int mid = left + (right - left) / 2;

                // If middle index is equal to the newTarget then break out of while loop
                if (numbers[mid] == newTarget) {
                    // Return the indices i and mid
                    return new int[] { i + 1, mid + 1 };
                }

                // If middle index is number is more than the newTarget then update the left
                // index else update the right index
                if (numbers[mid] > newTarget) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        // Return the empty index
        return new int[] {};
    }

    // Main method to test twoSum
    public static void main(String[] args) {
        int[] numbers = { 2, 7, 11, 15 };
        int target = 9;

        int[] result = twoSum(numbers, target);

        System.out.println(
                "The 1-indexed indices " + result[0] + " and " + result[1] + " add up to the target " + target);
    }
}
