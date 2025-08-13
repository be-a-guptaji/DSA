/*
LeetCode Problem: https://leetcode.com/problems/evaluate-reverse-polish-notation/

Question: 150. Evaluate Reverse Polish Notation

Problem Statement: You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.

Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6

Example 3:
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22 

Constraints:

1 <= tokens.length <= 10^4
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */

/*
Approach: We use a stack to evaluate the Reverse Polish Notation (RPN) expression.

- Traverse through each token in the input array:
    - If the token is an operator (+, -, *, /):
        - Pop the top two elements from the stack (be careful with order for '-' and '/').
        - Apply the operator.
        - Push the result back onto the stack.
    - Otherwise, the token is a number:
        - Parse it to an integer and push it onto the stack.
- After processing all tokens, the stack will have exactly one element, which is the final result.

Key details:
- Subtraction and division require correct operand order:
    - For subtraction: a - b where 'b' is the first popped value and 'a' is the second.
    - For division: a / b with the same order.
- Using Integer.parseInt() allows handling of negative numbers and multi-digit integers.

Time Complexity: O(n) — Each token is processed once.
Space Complexity: O(n) — In the worst case, all tokens are numbers and stored in the stack.
*/
package StacksAndQueues.Medium;

import java.util.Stack;

public class _150_Evaluate_Reverse_Polish_Notation {
    // Method to evaluate the reverse polish notation
    public static int evalRPN(String[] tokens) {
        // Initialize a stack for evaluation
        Stack<Integer> stack = new Stack<>();

        // Logic to evaluate the reverse polish notation
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    stack.push(stack.pop() + stack.pop());
                }
                case "-" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> {
                    stack.push(stack.pop() * stack.pop());
                }
                case "/" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                }
                default -> {
                    stack.push(Integer.parseInt(token));
                }
            }
        }

        // Return the last element of the stack
        return stack.pop();
    }

    // Main method to test evalRPN
    public static void main(String[] args) {
        String[] tokens = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };

        int result = evalRPN(tokens);

        System.out.println("The output of the evaluated reverse polish notation is : " + result);
    }
}
