/*
LeetCode Problem: https://leetcode.com/problems/number-of-1-bits/

Question: 191. Number of 1 Bits

Problem Statement: Given a positive integer n, write a function that returns the number of set bits in its binary representation (also known as the Hamming weight).

Example 1:
Input: n = 11
Output: 3
Explanation:
The input binary string 1011 has a total of three set bits.

Example 2:
Input: n = 128
Output: 1
Explanation:
The input binary string 10000000 has a total of one set bit.

Example 3:
Input: n = 2147483645
Output: 30
Explanation:
The input binary string 1111111111111111111111111111101 has a total of thirty set bits.

Constraints:

1 <= n <= 2^31 - 1

Follow up: If this function is called many times, how would you optimize it?
*/

/*
Approach: Bitwise Counting

To compute the Hamming weight (number of set bits) of an integer, we repeatedly check
the least significant bit and then right-shift the number.

Algorithm:
- Initialize count = 0.
- While n is not zero:
  - Add (n & 1) to count; this extracts the lowest bit.
  - Right-shift n using unsigned shift (>>>), moving to the next bit.
- Continue until all bits have been processed.

Time Complexity: O(k), k = number of bits in the integer (typically 32).
Space Complexity: O(1)
*/

package Maths.Easy;

public class _191_Number_of_1_Bits {
    // Method find the hamming weight of the number
    public static int hammingWeight(int n) {
        // Initialzie the number for counting the ones
        int numberOfOnes = 0;

        // Iterate over the n unitl n is not equal to zero
        while (n != 0) {
            // Increment the value of the numberOfOnes if end bit is one
            numberOfOnes += (n & 1);

            // Right shift the n by one bit
            n >>>= 1;
        }

        // Return the numberOfOnes
        return numberOfOnes;
    }

    // Main method to test hammingWeight
    public static void main(String[] args) {
        int n = 11;

        int result = hammingWeight(n);

        System.out.println("The hamming weight of the number " + n + " is : " + result);
    }
}
