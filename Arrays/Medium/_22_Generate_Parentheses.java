/*
LeetCode Problem: https://leetcode.com/problems/generate-parentheses/

Question: 22. Generate Parentheses

Problem Statement: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

Constraints:
1 <= n <= 8
*/

/*
Approach:
We use **backtracking** to generate all valid combinations of parentheses.

1. Start with an empty string and two counters: `open` and `close`.
2. At each step:
   - Add an opening parenthesis `(` if `open < n`.
   - Add a closing parenthesis `)` if `close < open`.
3. When both `open` and `close` equal `n`, we have a valid combination.
4. This recursively explores all valid sequences in a depth-first manner.

Time Complexity: O(2^n)
- At each step, we have two choices — add '(' or ')'. So the total recursive calls grow exponentially.

Space Complexity: O(n) for the recursion stack and O(2^n * n) for storing results.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

public class _22_Generate_Parentheses {
    // Method to generate well-formed parentheses
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // Helper method to perform backtracking
    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    // Main method to test generateParenthesis
    public static void main(String[] args) {
        int n = 3;
        List<String> combinations = generateParenthesis(n);
        System.out.println("Well-formed parentheses combinations for n = " + n + ":");
        System.out.println(combinations);
    }
}
