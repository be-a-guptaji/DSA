/*
LeetCode Problem: https://leetcode.com/problems/minimum-string-length-after-removing-substrings/

Question: 2696. Minimum String Length After Removing Substrings

Problem Statement: You are given a string s consisting only of uppercase English letters.

You can apply some operations to this string where, in one operation, you can remove any occurrence of one of the substrings "AB" or "CD" from s.

Return the minimum possible length of the resulting string that you can obtain.

Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.

Example 1:
Input: s = "ABFCACDB"
Output: 2
Explanation: We can do the following operations:
- Remove the substring "ABFCACDB", so s = "FCACDB".
- Remove the substring "FCACDB", so s = "FCAB".
- Remove the substring "FCAB", so s = "FC".
So the resulting length of the string is 2.
It can be shown that it is the minimum length that we can obtain.

Example 2:
Input: s = "ACBBD"
Output: 5
Explanation: We cannot do any operations on the string so the length remains the same.

Constraints:

1 <= s.length <= 100
s consists only of uppercase English letters.
 */

/*
Approach: Stack Simulation (Greedy Pair Removal)

Goal:
- Compute the minimum possible length after removing all occurrences
  of "AB" and "CD" from the string.

Core Idea:
- Use a stack-like structure to process characters sequentially.
- Remove valid adjacent pairs immediately when formed.

Algorithm Steps:
1. Convert string to character array.
2. Maintain a stack using a character array and pointer.
3. Traverse each character:
   - If top + current forms "AB" or "CD", pop.
   - Else, push current character.
4. Return stack pointer as remaining length.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Minimum remaining length after all valid removals.
*/

package StacksAndQueues.Easy;

public class _2696_Minimum_String_Length_After_Removing_Substrings {
    // Method to find the minimum possible length of the resulting string that you
    // can obtain
    public static int minLength(String s) {
        // Convert the string into the character array
        char[] str = s.toCharArray();

        // Initialize the character array for the stack
        char[] stack = new char[str.length];

        // Initialize the stack pointer
        int stackPointer = 0;

        // Iterate over the character array to find the minimum length
        for (char ch : str) {
            if (stackPointer > 0 && ((stack[stackPointer - 1] == 'A' && ch == 'B') || (stack[stackPointer - 1] == 'C'
                    && ch == 'D'))) {
                stackPointer--;
            } else {
                stack[stackPointer++] = ch;
            }
        }

        // Return the stack pointer
        return stackPointer;
    }

    // Main method to test minLength
    public static void main(String[] args) {
        String s = "ABFCACDB";

        int result = minLength(s);

        System.out.println("The minimum possible length of the resulting string that you can obtain is : " + result);
    }
}