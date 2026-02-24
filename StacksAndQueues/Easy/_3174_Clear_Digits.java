/*
LeetCode Problem: https://leetcode.com/problems/clear-digits/

Question: 3174. Clear Digits

Problem Statement: You are given a string s.

Your task is to remove all digits by doing this operation repeatedly:

Delete the first digit and the closest non-digit character to its left.
Return the resulting string after removing all digits.

Note that the operation cannot be performed on a digit that does not have any non-digit character to its left.

Example 1:
Input: s = "abc"
Output: "abc"
Explanation:
There is no digit in the string.

Example 2:
Input: s = "cb34"
Output: ""
Explanation:
First, we apply the operation on s[2], and s becomes "c4".
Then we apply the operation on s[1], and s becomes "".

Constraints:

1 <= s.length <= 100
s consists only of lowercase English letters and digits.
The input is generated such that it is possible to delete all digits.
 */

/*
Approach: Stack-Based Character Removal

Goal:
- Return the resulting string after removing each digit
  and the closest non-digit character before it.

Core Idea:
- Use a stack-like structure.
- Traverse characters one by one.
- If a digit is found → remove the previous character (pop).
- Otherwise, push the character onto the stack.

Algorithm Steps:
1. Convert the string into a character array.
2. Create a stack array of the same length.
3. Maintain a stackPointer to simulate stack operations.
4. Traverse each character:
   - If it is a digit → decrement stackPointer.
   - Else → push it into the stack.
5. Build and return a new string using stack[0 → stackPointer).

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Final string after removing digits and their preceding characters.
*/

package StacksAndQueues.Easy;

public class _3174_Clear_Digits {
    // Method to find the resulting string after removing all digits
    public static String clearDigits(String s) {
        // Convert the string into the character array
        char[] str = s.toCharArray();

        // Initialize the character array for the stack
        char[] stack = new char[str.length];

        // Initialize the stack pointer
        int stackPointer = 0;

        // Iterate over the character array to remove all the digits
        for (char ch : str) {
            if (Character.isDigit(ch)) {
                stackPointer--;
            } else {
                stack[stackPointer++] = ch;
            }
        }

        // Return the stack pointer
        return new String(stack, 0, stackPointer);
    }

    // Main method to test minLength
    public static void main(String[] args) {
        String s = "abc";

        String result = clearDigits(s);

        System.out.println("The resulting string after removing all digits is : " + result);
    }
}