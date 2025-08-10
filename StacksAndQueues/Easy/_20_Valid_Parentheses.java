/*
LeetCode Problem: https://leetcode.com/problems/valid-parentheses/

Question: 20. Valid Parentheses

Problem Statement: Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Example 5:
Input: s = "([)]"
Output: false

Constraints:

1 <= s.length <= 10^4
s consists of parentheses only '()[]{}'.
 */

 /*
Approach: We solve this problem using a **stack** to match brackets.

- Traverse each character of the string:
  - If it’s an opening bracket ('(', '{', '['), push it onto the stack.
  - If it’s a closing bracket:
      - Check if the stack is not empty and the top of the stack has the matching opening bracket.
      - If it matches, pop the stack.
      - If it doesn’t match or stack is empty, return false immediately.
- After traversing the string, if the stack is empty, return true; otherwise return false.

This approach ensures that brackets are matched correctly and in order.

Time Complexity: O(n) — We process each character once.
Space Complexity: O(n) — In the worst case, all characters could be opening brackets stored in the stack.
 */
package StacksAndQueues.Easy;

import java.util.Stack;

public class _20_Valid_Parentheses {

    // Method to find if given string is a valid parentheses or not
    public static boolean isValid(String s) {
        // Initialize the stack variable
        Stack<Character> stack = new Stack<>();
        char[] ch = s.toCharArray();
        int len = s.length();

        // Early exit if the length is odd
        if (len % 2 == 1) {
            return false;
        }

        // Logic to find the valid parentheses
        for (int i = 0; i < len; i++) {
            char item = ch[i];

            if (item == '(' || item == '{' || item == '[') {
                stack.push(item);
            } else if (item == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (item == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (item == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                return false;
            }
        }

        // Return the true if stack is empty else false
        return stack.isEmpty();
    }

    // Main method to test isValid
    public static void main(String[] args) {
        String s = "()[]{}";

        if (isValid(s)) {
            System.out.println("The given string " + s + " is a valid parentheses");
        } else {
            System.out.println("The given string " + s + " is not a valid parentheses");
        }

    }
}
