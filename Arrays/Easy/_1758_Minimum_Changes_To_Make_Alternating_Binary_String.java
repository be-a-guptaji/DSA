/*
LeetCode Problem: https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/

Question: 1758. Minimum Changes To Make Alternating Binary String

Problem Statement: You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once for each word in words).

Return the sum of lengths of all good strings in words.

Example 1:
Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

Example 2:
Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.

Constraints:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.
 */

/*
Approach: Compare Against Two Alternating Patterns

Goal:
Find the minimum number of operations required to make a binary string alternating.
An alternating string is either:
- "010101..." (starting with '0'), or
- "101010..." (starting with '1').

Key Idea:
Compute how many mismatches occur if the string is forced to match each pattern,
then take the minimum.

Algorithm:
1. Convert the string to a character array.
2. Initialize counters:
   - firstZero: mismatches assuming pattern starts with '0'.
   - firstOne: mismatches assuming pattern starts with '1'.
3. Check pattern starting with '0':
   - current = 0
   - For each character:
       • If bin != current, increment firstZero.
       • Flip current between 0 and 1.
4. Check pattern starting with '1':
   - current = 1
   - Repeat the same process, counting mismatches into firstOne.
5. Return min(firstZero, firstOne).

Why It Works:
- Any valid alternating string must match one of the two patterns.
- Counting mismatches gives the number of flips needed.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1758_Minimum_Changes_To_Make_Alternating_Binary_String {
    // Method to find the minimum number of operations needed to make s alternating
    public static int minOperations(String s) {
        // Convert the string into binary character array
        char[] binary = s.toCharArray();

        // Initialize the current, firstZero and firstOne
        int current = 0, firstZero = 0, firstOne = 0;

        // Iterate over the binary string with starting with zero
        for (char bin : binary) {
            // If binary is not equal to the current then increment the firstZero variable
            if (bin - '0' != current) {
                firstZero++;
            }

            // Alter the current variable
            current ^= 1;
        }

        // Change the current to one
        current = 1;

        // Iterate over the binary string with starting with one
        for (char bin : binary) {
            // If binary is not equal to the current then increment the firstOne variable
            if (bin - '0' != current) {
                firstOne++;
            }

            // Alter the current variable
            current ^= 1;
        }

        // Retrun the min of firstZero and firstOne
        return Math.min(firstZero, firstOne);
    }

    // Main method to test minOperations
    public static void main(String[] args) {
        String s = "1111";

        int result = minOperations(s);

        System.out.println("The minimum number of operations needed to make s alternating is : " + result);
    }
}
