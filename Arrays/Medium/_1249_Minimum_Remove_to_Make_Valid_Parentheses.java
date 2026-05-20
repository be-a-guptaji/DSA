/*
LeetCode Problem: https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

Question: 1249. Minimum Remove to Make Valid Parentheses

Problem Statement: Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Constraints:
1 <= s.length <= 10^5
s[i] is either '(' , ')', or lowercase English letter.
*/

/*
Approach: Stack-Based Invalid Parentheses Removal

Goal:
- Remove the minimum number of parentheses
  to make the string valid.

Core Idea:
- Track indices of unmatched '(' using a stack.
- For each ')':
   - If matching '(' exists → match them.
   - Else → mark ')' as invalid.
- After traversal:
   - Remaining indices in stack are unmatched '('.
- Remove all invalid characters.

Algorithm Steps:
1. Traverse the string:
   - If '(':
       push index to stack.
   - If ')':
       - If stack empty:
           mark ')' invalid.
       - Else:
           pop matching '('.
2. Mark all remaining stack indices as invalid.
3. Build final string excluding invalid characters.
4. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns a valid parentheses string after minimum removals.
*/

package Arrays.Medium;

// Solution Class
class Solution {
    // Method to find the valid resulting parentheses string
    public String minRemoveToMakeValid(String s) {
        // Convert the string into character string
        char[] str = s.toCharArray();

        // Initialize the stack for keeping track of the parentheses and the index
        int[] stack = new int[str.length];

        // Initialize the stack index
        int stackIndex = -1;

        // Iterate over the character array to make the valid string
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                // Update the stackIndex
                stackIndex++;

                // Update the stack
                stack[stackIndex] = i;
            } else if (str[i] == ')') {
                // Update the stack accordingly
                if (stackIndex < 0) {
                    str[i] = '#';
                } else {
                    // Update the stackIndex
                    stackIndex--;
                }
            }
        }

        // Iterate over the stack to remove the parentheses
        for (int i = 0; i <= stackIndex; i++) {
            str[stack[i]] = '#';
        }

        // Initialize the string builder to form the string
        StringBuilder result = new StringBuilder();

        // Iterate over the character array to form the valid parentheses string
        for (int i = 0; i < str.length; i++) {
            if (str[i] != '#') {
                result.append(str[i]);
            }
        }

        // Return the result by converting it to string
        return result.toString();
    }
}

public class _1249_Minimum_Remove_to_Make_Valid_Parentheses {
    // Main method to test minRemoveToMakeValid
    public static void main(String[] args) {
        String s = "lee(t(c)o)de)";

        String result = new Solution().minRemoveToMakeValid(s);

        System.out.println("The valid resulting parentheses string is : " + result);
    }
}
