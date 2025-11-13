/*
LeetCode Problem: https://leetcode.com/problems/remove-invalid-parentheses/

Question: 301. Remove Invalid Parentheses

Problem Statement: Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Input: s = ")("
Output: [""]

Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
 */

/*
Approach:
1. We are given a string that may contain invalid '(' and ')'. The goal is to remove the minimum
   number of invalid parentheses so that the result contains only valid parentheses combinations.

2. To solve this, we first count:
     → extraOpen  = number of '(' that must be removed
     → extraClose = number of ')' that must be removed
   This count helps us know exactly how many removals to make.

3. Once we know how many to remove, we use a DFS backtracking approach:
   - At each character we have two choices:
       • Remove it   (only if it is '(' or ')' and removals are still available)
       • Keep it     (add it to the current expression)

4. While keeping characters:
   - Maintain a `balance` variable:
       • Increment for '('
       • Decrement for ')'
     If balance becomes negative, it means ')' are more than '(' → invalid state → prune.

5. The recursive DFS will explore all valid placements of parentheses while ensuring:
     • Minimum removals
     • Balanced parentheses
     • No duplicates (handled by avoiding consecutive removal of same type)

6. When the entire string is processed:
   - Only expressions with zero remaining removals AND balance == 0 are considered valid.
   - These valid expressions are added to the final list.

Time Complexity:
   Exponential in worst case due to DFS, but heavily pruned by removal counts and balance checks.

Space Complexity:
   O(N) for recursion stack + O(N) for building valid expressions.
*/

package Backtracking.Hard;

import java.util.ArrayList;
import java.util.List;

public class _301_Remove_Invalid_Parentheses {
   // List to store all the valid expressions
  private static List<String> validExpressions;

  // Method to remove minimum invalid parentheses and return valid expressions
  public static List<String> removeInvalidParentheses(String s) {
    // Initialize variables to count extra '(' and extra ')'
    int extraOpen = 0, extraClose = 0;

    // Count the number of misplaced open and close parentheses
    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        extraOpen++;
      } else if (ch == ')') {
        // If there's an unmatched '(', pair it
        if (extraOpen > 0) {
          extraOpen--;
        }
        // Otherwise, this is an extra ')'
        else {
          extraClose++;
        }
      }
    }

    // Initialize the validExpressions
    validExpressions = new ArrayList<>();

    // Start the recursive backtracking process to generate valid expressions
    // The new char[] will hold the final expression of correct length
    backtrack(s, 0, new char[s.length() - extraOpen - extraClose], 0, extraOpen, extraClose, 0);

    // Return the list of valid expressions
    return validExpressions;
  }

  // Helper method to generate valid expressions using DFS + backtracking
  private static void backtrack(String s, int index, char[] curr, int currIndex,
      int openRem, int closeRem, int balance) {

    // Base case: if the removals or balance go invalid then return
    if (openRem < 0 || closeRem < 0 || balance < 0) {
      return;
    }

    // If the complete string is processed
    if (index == s.length()) {
      // Valid only when no removals are left
      if (openRem == 0 && closeRem == 0) {
        validExpressions.add(new String(curr));
      }
      return;
    }

    char ch = s.charAt(index);

    // Case 1: Remove '('
    if (ch == '(' && openRem > 0 && (currIndex == 0 || curr[currIndex - 1] != '(')) {
      backtrack(s, index + 1, curr, currIndex, openRem - 1, closeRem, balance);
    }

    // Case 2: Remove ')'
    if (ch == ')' && closeRem > 0 && (currIndex == 0 || curr[currIndex - 1] != ')')) {
      backtrack(s, index + 1, curr, currIndex, openRem, closeRem - 1, balance);
    }

    // Case 3: Keep the current character
    if (currIndex < curr.length) {

      // Add character to the current expression
      curr[currIndex] = ch;

      // Update the balance if parenthesis is added
      if (ch == '(') {
        balance++;
      } else if (ch == ')') {
        balance--;
      }

      // Move ahead in the recursion
      backtrack(s, index + 1, curr, currIndex + 1, openRem, closeRem, balance);
    }
  }

    // Main method to test removeInvalidParentheses
    public static void main(String[] args) {
        String s = "()";

        List<String> result = removeInvalidParentheses(s);

        System.out.println(
                "The list of unique strings that are valid with the minimum number of removals of parentheses is : "
                        + result);
    }
}
