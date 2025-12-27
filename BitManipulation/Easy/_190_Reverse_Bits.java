/*
LeetCode Problem: https://leetcode.com/problems/reverse-bits/

Question: 190. Reverse Bits

Problem Statement: Reverse bits of a given 32 bits signed integer.

Example 1:
Input: n = 43261596
Output: 964176192
Explanation:
Integer	Binary
43261596	00000010100101000001111010011100
964176192	00111001011110000010100101000000

Example 2:
Input: n = 2147483644
Output: 1073741822
Explanation:
Integer	Binary
2147483644	01111111111111111111111111111100
1073741822	00111111111111111111111111111110

Constraints:

0 <= n <= 2^31 - 2
n is even.

Follow up: If this function is called many times, how would you optimize it?
*/

/*
Approach: Bit-by-Bit Reversal

Goal:
Reverse the bits of a 32-bit unsigned integer.

Key Idea:
Extract bits from the original number starting from the least significant bit
and build the reversed number by shifting left and appending each bit.

Algorithm:
1. Initialize:
   - reverseNumber = 0
   - index = 32 (number of bits)

2. While index > 0:
   - Left shift reverseNumber by 1 to make space.
   - Add the last bit of n using (n & 1).
   - Right shift n by 1.
   - Decrement index.

3. After 32 iterations, reverseNumber contains the bit-reversed value.

Why It Works:
- Each iteration moves one bit from n into reverseNumber in reverse order.
- Fixed 32-bit loop guarantees correctness.

Time Complexity: O(32) ≈ O(1)  
Space Complexity: O(1)
*/

package BitManipulation.Easy;

public class _190_Reverse_Bits {
    // Method find the reverse bits of the number
    public static int reverseBits(int n) {
        // Initialize the index for iterating over the byte
        int index = 32;

        // Initialize the reverse number for returning
        int reverseNumber = 0;

        // Iterate over the index untill it reaches zero
        while (index != 0) {
            // Left shift the reverse number by one bit
            reverseNumber = reverseNumber << 1;

            // Add the number if number is odd else add zero
            reverseNumber += (n & 1);

            // Right shift the n by one bit
            n >>= 1;

            // Decrement index by one
            index--;
        }

        // Return the numberOfOnes
        return reverseNumber;
    }

    // Main method to test reverseBits
    public static void main(String[] args) {
        int n = 2147483644;

        int result = reverseBits(n);

        System.out.println("The reverse bits of the number " + n + " is : " + result);
    }
}
