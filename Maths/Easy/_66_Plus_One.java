/*
LeetCode Problem: https://leetcode.com/problems/plus-one/

Question: 66. Plus One

Problem Statement: You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

Example 1:
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

Example 2:
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].

Example 3:
Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].

Constraints:

1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.
*/

/*
Approach: Simulate Addition with Carry

Goal:
Given a number represented as an array of digits, return the digits after adding one.

Key Insight:
Addition starts from the least significant digit and may propagate a carry.

Algorithm:
1. Traverse the digits array from right to left.
2. If the current digit is less than 9:
   - Increment it by 1.
   - Return the array immediately (no carry propagation).
3. If the digit is 9:
   - Set it to 0 and continue to propagate the carry.
4. If all digits were 9:
   - Create a new array of size (n + 1).
   - Set the first digit to 1 and the rest remain 0.

Why It Works:
- Mimics elementary addition.
- Handles carry efficiently without extra loops.

Time Complexity: O(n)  
Space Complexity: O(n) in worst case (all digits are 9)
*/

package Maths.Easy;

import java.util.Arrays;

public class _66_Plus_One {
    // Method to find the array of digits after adding one
    public static int[] plusOne(int[] digits) {
        // Get the length of the array
        int len = digits.length;

        // Iterate over the digits
        for (int i = len - 1; i >= 0; i--) {
            // If digit[i] is less than 9 then reutn the digit after incrementing one
            if (digits[i] < 9) {
                // Increment the digit
                digits[i]++;

                // Retrun the digits
                return digits;
            }

            // Set the current digit index to zero
            digits[i] = 0;
        }

        // Initialize the new array with one more than the digits length
        int[] result = new int[len + 1];

        // Set the most significant bit to one
        result[0] = 1;

        // Return the result
        return result;
    }

    // Main method to test plusOne
    public static void main(String[] args) {
        int[] digits = new int[] { 4, 3, 2, 1 };

        int[] result = plusOne(digits);

        System.out.println("The resulting array of digits after adding one is : " + Arrays.toString(result));
    }
}
