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
