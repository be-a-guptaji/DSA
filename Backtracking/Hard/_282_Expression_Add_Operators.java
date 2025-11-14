/*
LeetCode Problem: https://leetcode.com/problems/expression-add-operators/

Question: 282. Expression Add Operators

Problem Statement: Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.

Note that operands in the returned expressions should not contain leading zeros.

Note that a number can contain multiple digits.

Example 1:
Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.

Example 2:
Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.

Example 3:
Input: num = "3456237490", target = 9191
Output: []
Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.

Constraints:

1 <= num.length <= 10
num consists of only digits.
-2^31 <= target <= 2^31 - 1
 */

/*
Approach:
1. We are given a numeric string `num`, and we must insert operators '+', '-', and '*'
   between the digits so that the final expression evaluates to the given target value.

2. To solve this, we use **backtracking** to explore all possible ways of:
      • Splitting the digits into numbers
      • Placing an operator before each number (except the first one)

3. The algorithm processes the string from left to right and builds:
      → A running expression using a StringBuilder
      → The current evaluated value of the expression
      → The last number used (to correctly handle multiplication)
   This allows the expression to be evaluated incrementally without using eval().

4. Important rules in the recursion:
   • At each position `i`, form a number by taking digits[index...i].
   • Skip forming multi-digit numbers starting with '0' (leading zero rule).
   • If this is the first number, directly continue without adding an operator.
   • Otherwise, try placing:
        '+'  → Add the number to the expression
        '-'  → Subtract the number
        '*'  → Multiply with the previous number (special handling for precedence)

     For multiplication, update the evaluated value as:
         currentValue = currentValue - previousNumber + (previousNumber * num)
     since multiplication binds tighter than + and -.

5. Base Case:
   • When index reaches the end of the digits array:
       - If the current evaluated value equals target, store the expression.

6. This ensures:
   • All possible valid expressions are explored.
   • Operator precedence is handled correctly.
   • Numbers with leading zeros are eliminated.
   • No unnecessary string-building operations (due to efficient backtracking).

Time Complexity: O(N * 3^N)
   For each digit, up to 3 operators can be inserted, leading to exponential combinations.

Space Complexity: O(N)
   For recursion depth and the temporary expression builder.
*/

package Backtracking.Hard;

import java.util.ArrayList;
import java.util.List;

public class _282_Expression_Add_Operators {
  // Method to find the resultant expression that evaluates to the target value
  public static List<String> addOperators(String num, int target) {
    // Initialize the list of string to store all valid equations
    List<String> result = new ArrayList<>();

    // Make a recursive backtrack call
    backtrack(0, new StringBuilder(), result, target, 0, 0, num.toCharArray());

    // Return the result
    return result;
  }

  // Helper method to find all the equations that sum up to the target
  private static void backtrack(int index, StringBuilder sb, List<String> list,
      int target, long currentValue, long previousNumber,
      char[] digits) {
    // Base case when index reaches the end of the string
    if (index == digits.length) {
      // If the evaluated expression equals the target sum then add it to the list
      if (currentValue == target) {
        list.add(sb.toString());
      }
      return;
    }

    // Variable to form the current number from digits
    long num = 0;
    int lengthBeforeAppend = sb.length();

    // Iterate over the digits array to make all possible numbers at this position
    for (int i = index; i < digits.length; i++) {

      // Avoid numbers with leading zero
      if (i != index && digits[index] == '0') {
        return;
      }

      // Form the current number
      num = num * 10 + (digits[i] - '0');

      // If the StringBuilder is empty then this is the starting number (no operator
      // before it)
      if (sb.length() == 0) {
        sb.append(num);

        // Recursive call without any operator placed yet
        backtrack(i + 1, sb, list, target, num, num, digits);

        // Backtrack
        sb.setLength(lengthBeforeAppend);
      } else {

        // Use '+' operator
        sb.append('+').append(num);
        backtrack(i + 1, sb, list, target,
            currentValue + num, num, digits);
        sb.setLength(lengthBeforeAppend);

        // Use '-' operator
        sb.append('-').append(num);
        backtrack(i + 1, sb, list, target,
            currentValue - num, -num, digits);
        sb.setLength(lengthBeforeAppend);

        // Use '*' operator
        sb.append('*').append(num);
        backtrack(i + 1, sb, list, target,
            currentValue - previousNumber + (previousNumber * num),
            previousNumber * num, digits);
        sb.setLength(lengthBeforeAppend);
      }
    }
  }

  // Main method to test addOperators
  public static void main(String[] args) {
    String num = "123";
    int target = 6;

    List<String> result = addOperators(num, target);

    System.out.println("The resultant expression evaluates to the target " + target + " value is : " + result);
  }
}
