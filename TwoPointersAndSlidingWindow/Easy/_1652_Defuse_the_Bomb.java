/*
LeetCode Problem: https://leetcode.com/problems/defuse-the-bomb/

Question: 1652. Defuse the Bomb

Problem Statement: You have a bomb to defuse, and your time is running out! Your informer will provide you with a circular array code of length of n and a key k.

To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.

If k > 0, replace the ith number with the sum of the next k numbers.
If k < 0, replace the ith number with the sum of the previous -k numbers.
If k == 0, replace the ith number with 0.
As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].

Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!

Example 1:
Input: code = [5,7,1,4], k = 3
Output: [12,10,16,13]
Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.

Example 2:
Input: code = [1,2,3,4], k = 0
Output: [0,0,0,0]
Explanation: When k is zero, the numbers are replaced by 0. 

Example 3:
Input: code = [2,4,9,3], k = -2
Output: [12,5,6,13]
Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.

Constraints:
n == code.length
1 <= n <= 100
1 <= code[i] <= 100
-(n - 1) <= k <= n - 1
*/

/*
Approach: Circular Sliding Window

Goal:
- Replace each element with:
   - Sum of next k elements if k > 0
   - Sum of previous |k| elements if k < 0
   - 0 if k == 0

Core Idea:
- Since the array is circular,
  use modular arithmetic for wrapping indices.
- Maintain a sliding window sum instead of
  recomputing sums repeatedly.

Algorithm Steps:
Case 1: k > 0
----------------
1. Build initial window:
     code[1 ... k]
2. result[0] = windowSum
3. Slide window:
     remove outgoing element
     add incoming circular element

Case 2: k < 0
----------------
1. Convert k to positive.
2. Build initial window from previous elements:
     code[n-k ... n-1]
3. result[0] = windowSum
4. Slide window similarly using circular indices.

Case 3: k == 0
----------------
- Return array of zeros.

Time Complexity:
- O(n)

Space Complexity:
- O(1)
  excluding output array

Result:
- Returns decrypted circular array.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the decrypted code to defuse the bomb
    public int[] decrypt(int[] code, int k) {
        // Get the length of the code
        int length = code.length;

        // Initialize the result array
        int[] result = new int[length];

        // Initialize the windowSum
        int windowSum = 0;

        // Make the window according to the k
        if (k > 0) {
            // Make the window sum
            for (int index = 1; index <= k; index++) {
                windowSum += code[index];
            }

            // Update the result[0] of the array
            result[0] = windowSum;

            // Fill the result array
            for (int index = 1; index < length; index++) {
                result[index] = windowSum = windowSum - code[index] + code[(index + k) % length];
            }
        } else if (k < 0) {
            // Update the k
            k *= -1;

            // Make the window sum
            for (int index = length - k; index < length; index++) {
                windowSum += code[index];
            }

            // Update the result[0] of the array
            result[0] = windowSum;

            // Fill the result array
            for (int index = 1; index < length; index++) {
                result[index] = windowSum = windowSum + code[index - 1]
                        - code[(length + index - k - 1) % length];
            }
        }

        // Return the result array
        return result;
    }
}

public class _1652_Defuse_the_Bomb {
    // Main method to test the decrypt
    public static void main(String[] args) {
        int[] code = new int[] { 2, 4, 9, 3 };
        int k = -2;

        int[] result = new Solution().decrypt(code, k);

        System.out.println("The decrypted code to defuse the bomb is : " + Arrays.toString(result));
    }
}
