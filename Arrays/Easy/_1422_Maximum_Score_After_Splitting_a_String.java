/*
LeetCode Problem: https://leetcode.com/problems/maximum-score-after-splitting-a-string/

Question: 1422. Maximum Score After Splitting a String

Problem Statement: Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).

The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.

Example 1:
Input: s = "011101"
Output: 5 
Explanation: 
All possible ways of splitting s into two non-empty substrings are:
left = "0" and right = "11101", score = 1 + 4 = 5 
left = "01" and right = "1101", score = 1 + 3 = 4 
left = "011" and right = "101", score = 1 + 2 = 3 
left = "0111" and right = "01", score = 1 + 1 = 2 
left = "01110" and right = "1", score = 2 + 1 = 3

Example 2:
Input: s = "00111"
Output: 5
Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5

Example 3:
Input: s = "1111"
Output: 3

Constraints:

2 <= s.length <= 500
The string s consists of characters '0' and '1' only.
 */

/*
Approach: Prefix Zeros + Suffix Ones

Goal:
Split the binary string into two non-empty substrings such that:
score = (number of '0's in left substring) + (number of '1's in right substring)
is maximized.

Key Idea:
- Count total number of '1's initially (all on the right side).
- Move the split point from left to right:
  • Track zeros accumulated on the left.
  • Track remaining ones on the right.
- Update the maximum score at each valid split.

Algorithm:
1. Count total number of '1's in the string → ones.
2. Initialize:
   - zero = 0 (zeros in left part)
   - result = 0
3. Iterate from index 0 to n − 2 (ensure both substrings are non-empty):
   - If s[i] == '1': decrement ones
   - Else: increment zero
   - Update result = max(result, zero + ones)
4. Return result.

Why It Works:
- Every split can be evaluated in O(1) time.
- Prefix zeros and suffix ones capture the score definition directly.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1422_Maximum_Score_After_Splitting_a_String {
    // Method to find the maximum score after splitting the string into two
    // non-empty substrings
    public static int maxScore(String s) {
        // Convert the string into the character array
        char[] binary = s.toCharArray();

        // Initialize the number of zeros and ones for the counting
        int zero = 0, ones = 0, result = 0;

        // Count the number of ones in the left sub array
        for (int i = 0; i < binary.length; i++) {
            // If value is equal to one then increment the ones
            if (binary[i] == '1') {
                ones++;
            }
        }

        // Get the maximum result
        for (int i = 0; i < binary.length - 1; i++) {
            // If value is equal to one then decrement the one else increment the zeros
            if (binary[i] == '1') {
                ones--;
            } else {
                zero++;
            }

            // Update the result variable
            result = Math.max(result, zero + ones);
        }

        // Return the result
        return result;
    }

    // Main method to test maxScore
    public static void main(String[] args) {
        String s = "leetcode exercises sound delightful";

        int result = maxScore(s);

        System.out.println("The maximum score after splitting the string into two non-empty substrings is : " + result);
    }
}
