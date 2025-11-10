/*
LeetCode Problem: https://leetcode.com/problems/single-number/

Question: 136. Single Number

Problem Statement: Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1


Constraints:

1 <= nums.length <= 3 * 10^4
-3 * 10^4 <= nums[i] <= 3 * 10^4
Each element in the array appears twice except for one element which appears only once.
 */

/*
Approach:
1. We are given an integer array `nums` where every element appears twice except for one element 
   that appears only once. The goal is to find that single number.
2. The problem can be solved efficiently using the **bitwise XOR (^) operation**.
3. XOR properties:
   - a ^ a = 0  (same numbers cancel each other)
   - a ^ 0 = a  (XOR with zero returns the number itself)
   - XOR is commutative and associative, so the order of operations doesn’t matter.
4. Algorithm:
   • Initialize a variable `xor` with the first element of the array.
   • Traverse the rest of the array, performing XOR on each element with `xor`.
   • After processing all elements, all duplicate numbers will cancel each other out (become 0),
     leaving only the unique number.
5. Edge Case:
   - If the array length is 1, directly return that element as the unique number.

Time Complexity: O(N) → each element is processed once.
Space Complexity: O(1) → only one variable is used for XOR operations.
*/

package Arrays.Easy;

public class _136_Single_Number {
    // Method to find the single number in the array
    public static int singleNumber(int[] nums) {
        // Edge case if nums length is one
        if (nums.length == 1) {
            return nums[0];
        }

        // Initialize the number for the XOR operation
        int xor = nums[0];

        // Iterate over the nums array and xor it with every element
        for (int i = 1; i < nums.length; i++) {
            xor = xor ^ nums[i];
        }

        // Return the xor
        return xor;
    }

    // Main method to test singleNumber
    public static void main(String[] args) {
        int[] nums = { 4, 1, 2, 1, 2 };

        int result = singleNumber(nums);

        System.out.println("The unique number in the array is : " + result);
    }
}
