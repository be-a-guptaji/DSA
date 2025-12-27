/*
LeetCode Problem: https://leetcode.com/problems/counting-bits/

Question: 338. Counting Bits

Problem Statement: Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

Example 1:
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10

Example 2:
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

Constraints:

0 <= n <= 10^5

Follow up:

It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
*/

/*
Approach: Dynamic Programming with Bit Manipulation

Goal:
For every number from 0 to n, compute the number of set bits (1’s) in its binary representation.

Key Insight:
For any integer i:
- i >> 1 removes the least significant bit.
- (i & 1) tells whether the last bit is 1 or 0.

DP Relation:
- countBits[i] = countBits[i >> 1] + (i & 1)

Algorithm:
1. Create an array numberOfOnes of size n + 1.
2. Initialize numberOfOnes[0] = 0.
3. For i from 1 to n:
   - numberOfOnes[i] = numberOfOnes[i >> 1] + (i & 1)
4. Return the array.

Why It Works:
- Each number reuses the result of a smaller number.
- Efficiently builds results in linear time.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package DynamicProgramming.Easy;

import java.util.Arrays;

public class _338_Counting_Bits {
    // Method to find the number of 1's in the binary representation
    public static int[] countBits(int n) {
        // Initialize the array to memoization all the possible cases
        int[] numberOfOnes = new int[n + 1];

        // Calculate all the the possible case from zero to n
        for (int i = 1; i <= n; i++) {
            numberOfOnes[i] = numberOfOnes[i >> 1] + (i & 1);
        }

        // Return the copy of the range zero to n + 1
        return numberOfOnes;
    }

    // Main method to test countBits
    public static void main(String[] args) {
        int n = 10;

        int[] result = countBits(n);

        System.out.println(
                "The number of 1's in the binary representation of 0 to " + n + " is : " + Arrays.toString(result));
    }
}
