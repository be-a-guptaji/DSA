/*
LeetCode Problem: https://leetcode.com/problems/regular-expression-matching/

Question: 10. Regular Expression Matching

Problem Statement: Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */

/*
Approach:
This problem is solved using recursion with memoization (top-down dynamic programming).
- We recursively check if the substring of `s` starting at index `i` matches the pattern substring of `p` starting at index `j`.
- The key idea is to handle the `*` operator correctly:
  - `*` can match zero occurrences of the preceding element (skip it),
  - or one or more occurrences (if the current characters match).
- We use a 2D Boolean memoization array `dp[i][j]` to store results of subproblems to avoid redundant computation.

Steps:
1. If both the string `s` and pattern `p` are fully matched, return true.
2. If the pattern is exhausted but the string is not, return false.
3. Check if the current characters match (either the same character or pattern has `.`).
4. If the next character in `p` is `*`, explore both:
   - Skipping the `*` pattern part.
   - Using `*` to match one or more characters (if current characters match).
5. Otherwise, move both indices forward if current characters match.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
where m = length of s, n = length of p
*/

package DynamicProgramming.Hard;

public class _10_Regular_Expression_Matching {
    // Method to find if the string matches the regex or not
    public static boolean isMatch(String s, String p) {
        // Get the length of both the string
        int slen = s.length(), plen = p.length();

        // Initialize the dp matrix for the memoization
        Boolean[][] dp = new Boolean[slen + 1][plen + 1];

        return dfs(s, p, slen, plen, 0, 0, dp);
    }

    // Helper method to do the DFS Traversal
    private static boolean dfs(String s, String p, int slen, int plen, int i, int j, Boolean[][] dp) {
        // Base case when both index go out of bound simultaneously then return true
        if (i >= slen && j >= plen) {
            return true;
        }

        // If j index go out of bound reutrn false
        if (j >= plen) {
            return false;
        }

        // If the indices are in the dp matrix are initialized then return the dp[i][j]
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        // Initialize a result variable
        boolean result;

        // Check if the index matches the charcters
        boolean match = i < slen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // If next character is '*' then use the star ones and do not use in other
        if (j + 1 < plen && p.charAt(j + 1) == '*') {
            result = dfs(s, p, slen, plen, i, j + 2, dp) || (match && dfs(s, p, slen, plen, i + 1, j, dp));
        } else {
            // If the character matches then move forward both of the index
            result = match && dfs(s, p, slen, plen, i + 1, j + 1, dp);
        }

        // If nothing happens return result and add the ans to the memoization
        dp[i][j] = result;

        return result;
    }

    // Main method to test isMatch
    public static void main(String[] args) {
        String s = "aab", p = "c*a*b*";

        boolean result = isMatch(s, p);

        System.out.println("The string " + p + (result ? "" : " not") + " matches the regex " + s + ".");
    }
}
