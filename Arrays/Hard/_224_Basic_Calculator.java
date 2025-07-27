/*
LeetCode Problem: https://leetcode.com/problems/basic-calculator/

Question: 224. Basic Calculator

Problem Statement: Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "1 + 1"
Output: 2

Example 2:
Input: s = " 2-1 + 2 "
Output: 3

Example 3:
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23

Constraints:

1 <= s.length <= 3 * 10^5
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
*/

/*
Approach: We use a **stack-based iterative approach** to simulate evaluation of the expression.

Key ideas:
1. Maintain a `result` which accumulates the running total.
2. Use a `sign` variable to track whether the current number is positive or negative.
3. Use a `stack` to store previous results and signs when we encounter `(`.
4. On encountering `)`, we complete the sub-expression by popping sign and previous result from the stack.
5. We handle multi-digit numbers by accumulating them as we iterate.
6. Spaces are skipped.

Steps:
- If current char is a digit → build the number.
- If '+' or '-' → finalize the previous number with its sign, update sign.
- If '(' → push result and sign to stack, reset result and sign.
- If ')' → compute subexpression and apply the sign and result from stack.

Time Complexity: O(n)
- Each character is visited once.

Space Complexity: O(n)
- Stack stores signs and results during nested expressions.

This approach avoids recursion and uses constant-time operations with each character.
*/

package Arrays.Hard;

import java.util.Stack;

public class _224_Basic_Calculator {
    // Method to find result form a valid expression written in string
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0; // running result
        int sign = 1; // current sign (+1 or -1)
        int num = 0; // current number being built

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // Build number character by character
                num = num * 10 + (ch - '0');
            } else if (ch == '+') {
                // Finalize the previous number
                result += sign * num;
                sign = 1;
                num = 0;
            } else if (ch == '-') {
                result += sign * num;
                sign = -1;
                num = 0;
            } else if (ch == '(') {
                // Push current result and sign to stack before new sub-expression
                stack.push(result);
                stack.push(sign);
                // Reset for new subexpression
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                // Complete current subexpression
                result += sign * num;
                num = 0;
                result *= stack.pop(); // Apply the sign before '('
                result += stack.pop(); // Add the result before '('
            }
            // Ignore spaces
        }

        // Add any leftover number after the last character
        result += sign * num;
        return result;
    }

    // Main method to test the calculate function
    public static void main(String[] args) {
        String s = "( 1 + ( 4+ 5+ 2) -3)+( 6+8)";

        int result = calculate(s);

        System.out.println("The result of the valid expression in string is : " + result);
    }
}
