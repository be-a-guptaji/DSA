/*
LeetCode Problem: https://leetcode.com/problems/squares-of-a-sorted-array/

Question: 977. Squares of a Sorted Array

Problem Statement: Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

Example 1:
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].

Example 2:
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]

Constraints:

1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums is sorted in non-decreasing order.

Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
*/

/*
Approach:
- The input array is sorted in non-decreasing order but may contain negative numbers.
- Negative numbers when squared may be larger than some positive numbers squared.
- To efficiently get a sorted array of squares in O(n) time, we:
  1. Find the index where non-negative numbers start (using binary search).
  2. Initialize two pointers:
     - Left pointer at the last negative number.
     - Right pointer at the first non-negative number.
  3. Merge the squares of these two "subarrays" (negative part reversed by absolute value, and positive part).
- This way, we merge two sorted sequences of squares into one sorted array.

Time Complexity: O(n) — each element is processed once.
Space Complexity: O(n) — for the output array.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

public class _977_Squares_of_a_Sorted_Array {
    // Method to form the sorted squared array
    public static int[] sortedSquares(int[] nums) {
        // Initialize variable for tracking
        int left = 0, right = nums.length - 1;
        int len = nums.length;
        int[] sortedSquaresArray = new int[len];

        // Binary search to find first non-negative number
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int firstPositive = left;
        left = firstPositive - 1; // Last negative index
        right = firstPositive; // First non-negative index

        int index = 0;

        // Merge squares from negatives and non-negatives
        while (left >= 0 && right < len) {
            if (Math.abs(nums[left]) <= nums[right]) {
                sortedSquaresArray[index++] = nums[left] * nums[left];
                left--;
            } else {
                sortedSquaresArray[index++] = nums[right] * nums[right];
                right++;
            }
        }

        // Extinguish the left list
        while (left >= 0) {
            sortedSquaresArray[index++] = nums[left] * nums[left];
            left--;
        }

        // Extinguish the right list
        while (right < len) {
            sortedSquaresArray[index++] = nums[right] * nums[right];
            right++;
        }

        // Return the new the squared sorted array
        return sortedSquaresArray;
    }

    // Main method to test sortedSquares
    public static void main(String[] args) {
        int[] nums = { -7, -3, 2, 3, 11 };

        int[] result = sortedSquares(nums);

        System.out.println("The squared sorted array is : " + Arrays.toString(result));
    }
}
