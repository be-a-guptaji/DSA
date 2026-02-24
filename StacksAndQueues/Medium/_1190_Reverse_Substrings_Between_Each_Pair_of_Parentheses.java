/*
LeetCode Problem: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/

Question: 1190. Reverse Substrings Between Each Pair of Parentheses

Problem Statement: You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

Example 1:
Input: s = "(abcd)"
Output: "dcba"

Example 2:
Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.

Example 3:
Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.

Constraints:

1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
 */

/*
Approach: Stack + StringBuilder (Nested Reversal)

Goal:
- Reverse substrings inside each pair of parentheses.
- Handle nested parentheses correctly.
- Remove parentheses in final result.

Core Idea:
- Use a stack to store previous string states.
- When encountering '(':
    Save current built string and start fresh.
- When encountering ')':
    Reverse current substring and append it
    to the previously saved string.

Algorithm Steps:

1. Initialize:
   - Stack<String> stack
   - StringBuilder curr = ""

2. Traverse each character:

   Case '(':
       - Push current string onto stack
       - Reset curr to empty

   Case ')':
       - Reverse curr
       - Pop previous string from stack
       - Append reversed curr to popped string
       - Assign back to curr

   Case letter:
       - Append to curr

3. Return curr.toString()

Why This Works:
- Stack preserves outer context for nested parentheses.
- Each closing parenthesis finalizes one nested segment.
- Reversal happens from innermost to outermost naturally.

Example:
Input: "(u(love)i)"

Process:
Push ""
Build "u"
Push "u"
Build "love"
Reverse → "evol"
Combine → "uevol"
Add "i" → "uevoli"
Reverse outer → "iloveu"

Output: "iloveu"

Time Complexity:
- O(n²) worst-case (due to repeated reversals)

Space Complexity:
- O(n)

Result:
- Returns string with all parentheses removed
  and substrings correctly reversed.
*/

package StacksAndQueues.Medium;

import java.util.Stack;

public class _1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses {
    // Method to find the string after reverse substrings between each pair of
    // parentheses
    public static String reverseParentheses(String s) {
        // Initialize the stack to hold the string
        Stack<String> stack = new Stack<>();

        // Initialize the current string builder
        StringBuilder curr = new StringBuilder();

        // Iterate over each character of the string
        for (char c : s.toCharArray()) {
            // Handle cases
            switch (c) {
                // If we encounter '(' then push the current string to the stack and initialize
                // the new string builder
                case '(' -> {
                    // Push the current string to the stack
                    stack.push(curr.toString());

                    // Initialize the new string builder
                    curr = new StringBuilder();
                }
                // If we encounter ')' then get the current string and reverse it and get the
                // string form the top of the stack and apped the current string to the string
                // builder
                case ')' -> {
                    // Initialize the temp variable to hold the reverse of the string
                    String temp = curr.reverse().toString();

                    // Get the string form the top of the stack to the current string builder
                    curr = new StringBuilder(stack.pop());

                    // Add the temp string to the current string builder
                    curr.append(temp);
                }
                // By default add the character to the current string builder
                default -> {
                    // Append the character to the current string builder
                    curr.append(c);
                }
            }
        }

        // Return the final reversed string
        return curr.toString();
    }

    // Main method to test reverseParentheses
    public static void main(String[] args) {
        String s = "(ed(et(oc))el)";

        String result = reverseParentheses(s);

        System.out.println("The string after reverse substrings between each pair of parentheses is : " + result);
    }
}
