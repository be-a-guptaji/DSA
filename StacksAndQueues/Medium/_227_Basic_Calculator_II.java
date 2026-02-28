/*
LeetCode Problem: https://leetcode.com/problems/basic-calculator-ii/

Question: 227. Basic Calculator II

Problem Statement: Given a string s which represents an expression, evaluate this expression and return its value. 

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2^31, 2^31 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "3+2*2"
Output: 7

Example 2:
Input: s = " 3/2 "
Output: 1

Example 3:
Input: s = " 3+5 / 2 "
Output: 5

Constraints:

1 <= s.length <= 3 * 10^5
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
The answer is guaranteed to fit in a 32-bit integer.
*/

/*
Approach: Single Pass Expression Evaluation (Operator Precedence Handling)

Goal:
- Evaluate a basic arithmetic expression containing
  +, -, *, / and spaces.

Core Idea:
- Traverse the string once.
- Build numbers digit by digit.
- Use:
    total → stores finalized results of + and -.
    prev  → stores last processed value (handles * and /).
- Apply previous operator when a new operator is encountered.

Algorithm Steps:
1. Initialize total = 0, prev = 0, num = 0.
2. Maintain operator = '+' (default).
3. Traverse characters (include a dummy '+' at end).
4. If digit → build num.
5. If operator encountered:
   - '+' → add prev to total, set prev = num.
   - '-' → add prev to total, set prev = -num.
   - '*' → prev = prev * num.
   - '/' → prev = prev / num.
6. Update operator and reset num.
7. Return total + prev.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Evaluated integer result respecting operator precedence.
*/

package StacksAndQueues.Medium;

public class _227_Basic_Calculator_II {
    // Method to find the evaluated result
    public static int calculate(String s) {
        // Convert the string into the character array
        char[] str = s.toCharArray();

        // Initialize the total, prev and nums variable
        int total = 0, prev = 0, num = 0;

        // Initialize the operator
        char operator = '+';

        // Initialize the length and the index variable
        int length = str.length, index = 0;

        // Itrate untill index is less then the length
        while (index <= length) {
            // Get the current character else '+'
            char ch = index < length ? str[index] : '+';

            // If we encounter ' ' then skip the iteration and increment the index
            if (ch == ' ') {
                index++;
                continue;
            }

            // Get number or preform the operation
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else {
                switch (operator) {
                    case '+' -> {
                        total += prev;
                        prev = num;
                    }
                    case '-' -> {
                        total += prev;
                        prev = -num;
                    }
                    case '*' -> {
                        prev = prev * num;
                    }
                    default -> {
                        prev = prev / num;
                    }
                }

                // Update the operator
                operator = ch;

                // Reset the nums variable
                num = 0;
            }

            // Increment the index
            index++;
        }

        // Return the total + prev
        return total + prev;
    }

    // Main method to test calculate
    public static void main(String[] args) {
        String s = "3+2*2";

        int result = calculate(s);

        System.out.println("The evaluate result is : " + result);
    }
}