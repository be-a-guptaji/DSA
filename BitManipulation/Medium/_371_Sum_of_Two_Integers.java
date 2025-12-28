/*
LeetCode Problem: https://leetcode.com/problems/sum-of-two-integers/

Question: 371. Sum of Two Integers

Problem Statement: Given two integers a and b, return the sum of the two integers without using the operators + and -.

Example 1:
Input: a = 1, b = 2
Output: 3

Example 2:
Input: a = 2, b = 3
Output: 5

Constraints:

-1000 <= a, b <= 1000
*/

/*
Approach: Bit Manipulation (Binary Addition)

Goal:
Compute the sum of two integers without using the '+' or '-' operators.

Key Observations:
- XOR (^) adds two bits without considering carry.
- AND (&) followed by left shift gives the carry bits.

Algorithm:
1. While there is a carry (b ≠ 0):
   - carry = (a & b) << 1   // compute carry bits
   - a = a ^ b             // sum without carry
   - b = carry             // propagate carry
2. When no carry remains, a holds the final sum.

Why It Works:
- This mimics binary addition at the bit level.
- Repeats until all carry bits are resolved.

Time Complexity: O(1)  (bounded by number of bits, typically 32)
Space Complexity: O(1)
*/

package BitManipulation.Medium;

public class _371_Sum_of_Two_Integers {
    // Method find the sum two number without using '+' operator
    public static int getSum(int a, int b) {
        // Iterate over the b untill it is not equal to zero
        while (b != 0) {
            // Get the carry
            int carry = (a & b) << 1;

            // Get xor of a and b in a
            a ^= b;

            // Set the b to the carry
            b = carry;
        }

        // Return a
        return a;
    }

    // Main method to test getSum
    public static void main(String[] args) {
        int a = 23, b = 28;

        int result = getSum(a, b);

        System.out.println("The sum of " + a + " and " + b + " without using '+' operator is : " + result);
    }
}
