/*
LeetCode Problem: https://leetcode.com/problems/parsing-a-boolean-expression/description/

Question: 1106. Parsing A Boolean Expression

Problem Statement: A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:

't' that evaluates to true.
'f' that evaluates to false.
'!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
'&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
'|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
Given a string expression that represents a boolean expression, return the evaluation of that expression.

It is guaranteed that the given expression is valid and follows the given rules.

Example 1:
Input: expression = "&(|(f))"
Output: false
Explanation: 
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.

Example 2:
Input: expression = "|(f,f,f,t)"
Output: true
Explanation: The evaluation of (false OR false OR false OR true) is true.

Example 3:
Input: expression = "!(&(f,t))"
Output: true
Explanation: 
First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
Then, evaluate !(f) --> NOT false --> true. We return true.

Constraints:

1 <= expression.length <= 2 * 10^4
expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.
 */

/*
Approach: Recursive Descent Parsing

Goal:
- Evaluate a boolean expression containing:
  't', 'f', '!', '&', '|', parentheses, and commas.

Core Idea:
- Parse the expression recursively.
- Maintain a global index to traverse the character array.
- Each recursive call evaluates one sub-expression.

Algorithm Steps:
1. Convert the string to a character array.
2. Use a global index pointer to track parsing position.
3. If character is 't' or 'f' → return true/false.
4. If operator is '!':
   - Parse the inner expression.
   - Return its negation.
5. If operator is '&' or '|':
   - Evaluate all sub-expressions inside parentheses.
   - Combine results using AND or OR.
6. Skip commas between sub-expressions.
7. Stop when encountering ')'.

Time Complexity:
- O(n)

Space Complexity:
- O(n) due to recursion stack.

Result:
- Returns the evaluated boolean value of the expression.
*/

package StacksAndQueues.Hard;

// Solution Class
class Solution {
    // Initialize the character array for the expression
    private char[] exp;

    // Initialize the index variable
    private int index;

    // Method to find the evaluation of that expression
    public boolean parseBoolExpr(String expression) {
        // Convert the string into the character array
        this.exp = expression.toCharArray();

        // Initialize the index variable for the exp array
        this.index = 0;

        // Retrun the parse expression result
        return this.parse();
    }

    // Helper method to parse the expressions
    private boolean parse() {
        // Get the current character
        char ch = this.exp[this.index++];

        // Return true if ch is 't'
        if (ch == 't') {
            return true;
        }

        // Return false if ch is 'f'
        if (ch == 'f') {
            return false;
        }

        // Increment the index
        this.index++;

        if (ch == '!') {
            // Initialize a boolean variable
            boolean result;

            // Get the result of the nested expression
            result = this.parse();

            // Increment the index
            this.index++;

            // Return the result
            return !result;
        }

        // Initialize the boolean variable isAND for checking if the current character
        // is the '&' or not
        boolean isAND = ch == '&';

        // Initialize the boolean variable for the result
        boolean result = isAND;

        // Iterate over the exp array untill we encounter ')'
        while (this.exp[this.index] != ')') {
            // If isAND is true then AND the result with the sub expression else OR the result
            if (isAND) {
                result &= this.parse();
            } else {
                result |= this.parse();
            }

            // If we encounter ',' then increment the index
            if (this.exp[this.index] == ',') {
                this.index++;
            }
        }

        // Increment the index
        this.index++;

        // Return a the result
        return result;
    }
}

public class _1106_Parsing_A_Boolean_Expression {
    // Main method to test parseBoolExpr
    public static void main(String[] args) {
        String expression = "&(|(f))";

        boolean result = new Solution().parseBoolExpr(expression);

        System.out.println("The evaluation of that expression " + expression + " is : " + result);
    }
}
