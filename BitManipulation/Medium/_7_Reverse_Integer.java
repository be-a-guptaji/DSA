/*
LeetCode Problem: https://leetcode.com/problems/reverse-integer/

Question: 7. Reverse Integer

Problem Statement: Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21

Constraints:

-2^31 <= x <= 2^31 - 1
*/

/*
Approach: Digit-by-Digit Reversal with Overflow Check

Goal:
Reverse the digits of a 32-bit signed integer.
If reversing causes overflow, return 0.

Key Idea:
Extract digits one by one from the original number and build the reversed number.
Before appending a digit, check whether the operation would overflow the integer range.

Algorithm:
1. Initialize result = 0.
2. While x != 0:
   - Extract the last digit: digit = x % 10.
   - Remove the last digit from x: x /= 10.
   - Before updating result:
       • Check if result > MAX/10 or
         (result == MAX/10 and digit > MAX%10) → overflow.
       • Check if result < MIN/10 or
         (result == MIN/10 and digit < MIN%10) → overflow.
     If overflow would occur, return 0.
   - Update result = result * 10 + digit.
3. Return result.

Why It Works:
- Reverses the integer safely without using extra space.
- Overflow is prevented by checking bounds before multiplication.

Time Complexity: O(d)
- d = number of digits in the integer (≤ 10).

Space Complexity: O(1)
*/

package BitManipulation.Medium;

public class _7_Reverse_Integer {
    // Method to reverse an integer with overflow handling
    public static int reverse(int x) {
        // Define integer limits
        final int MIN = -2147483648; // -2^31
        final int MAX = 2147483647; // 2^31 - 1

        // Initialize result variable
        int res = 0;

        // Iterate until the number becomes zero
        while (x != 0) {
            // Extract the last digit
            int digit = x % 10;

            // Remove the last digit from x
            x /= 10;

            // Check for positive overflow before multiplying by 10
            if (res > MAX / 10 || (res == MAX / 10 && digit > MAX % 10)) {
                return 0;
            }

            // Check for negative overflow before multiplying by 10
            if (res < MIN / 10 || (res == MIN / 10 && digit < MIN % 10)) {
                return 0;
            }

            // Append the digit to the result
            res = (res * 10) + digit;
        }

        // Return the reversed number
        return res;
    }

    // Main method to test reverse
    public static void main(String[] args) {
        int x = 28;

        int result = reverse(x);

        System.out.println("The reverse of number " + x + " is : " + result);
    }
}
