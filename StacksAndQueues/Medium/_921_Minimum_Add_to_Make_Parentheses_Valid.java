/*
LeetCode Problem: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

Question: 921. Minimum Add to Make Parentheses Valid

Problem Statement: A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

Example 1:
Input: s = "())"
Output: 1

Example 2:
Input: s = "((("
Output: 3

Constraints:

1 <= s.length <= 1000
s[i] is either '(' or ')'.
 */

/*
Approach: Stack-Based Parenthesis Matching

Goal:
- Determine the minimum number of parentheses needed
  to make the string valid (balanced).

Core Idea:
- Use a stack-like approach.
- Cancel out valid "()" pairs immediately.
- Remaining unmatched parentheses in the stack
  represent required additions.

Algorithm Steps:
1. Convert the string into a character array.
2. Create a stack array of the same length.
3. Maintain a stackPointer to simulate stack operations.
4. Traverse each character:
   - If top is '(' and current is ')', pop (matched pair).
   - Else, push current parenthesis.
5. Return stackPointer (number of unmatched parentheses).

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Minimum number of parentheses required to make the string valid.
*/

package StacksAndQueues.Medium;

public class _921_Minimum_Add_to_Make_Parentheses_Valid {
    // Method to find the resulting string after removing all digits
    public static int minAddToMakeValid(String s) {
        // Convert the string into the character array
        char[] parentheses = s.toCharArray();

        // Initialize the character array for the stack
        char[] stack = new char[parentheses.length];

        // Initialize the stack pointer
        int stackPointer = 0;

        // Iterate over the character array to remove all the digits
        for (char ch : parentheses) {
            if (stackPointer > 0 && stack[stackPointer - 1] == '(' && ch == ')') {
                stackPointer--;
            } else {
                stack[stackPointer++] = ch;
            }
        }

        // Return the stack pointer
        return stackPointer > 0 ? stackPointer : -stackPointer;
    }

    // Main method to test minAddToMakeValid
    public static void main(String[] args) {
        String s = "()))((";

        int result = minAddToMakeValid(s);

        System.out.println("The minimum number of moves required to make s valid is : " + result);
    }
}