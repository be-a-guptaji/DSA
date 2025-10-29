/*
LeetCode Problem: https://leetcode.com/problems/wildcard-matching/

Question: 44. Wildcard Matching

Problem Statement: Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
 */

/*
Approach:
This problem is solved using recursion with memoization (top-down dynamic programming).

We recursively check if the substring of `s` (up to index `i`) matches the pattern `p` (up to index `j`).

Key rules:
1. If both string and pattern are fully matched (i < 0 && j < 0), return true.
2. If pattern is exhausted but string is not, return false.
3. If string is exhausted but pattern still has characters:
   - Only return true if all remaining pattern characters are '*'.
4. If characters match or pattern has '?', we move both indices one step backward.
5. If pattern has '*', it can:
   - Match zero characters → move pattern index backward (j - 1)
   - Match one or more characters → move string index backward (i - 1)
6. We memoize results in a 2D array `dp[i][j]` to avoid redundant computation.

Time Complexity: O(m * n)
Space Complexity: O(m * n)
where m = length of s, n = length of p
*/

package DynamicProgramming.Hard;

public class _44_Wildcard_Matching {
    // Method to find if the string matches the entire regex or not
    public static boolean isMatch(String s, String p) {
        // Get the length of both the string
        int slen = s.length(), plen = p.length();

        // Initialize the dp matrix for the memoization
        Boolean[][] dp = new Boolean[slen][plen];

        // Start DFS from the last index of both strings
        return dfs(s, p, slen - 1, plen - 1, dp);
    }

    // Helper method to do the DFS Traversal
    private static boolean dfs(String s, String p, int i, int j, Boolean[][] dp) {
        // Base case when both index go out of bound simultaneously then return true
        if (i < 0 && j < 0) {
            return true;
        }

        // Base case when pattern is exhausted but string is not
        if (j < 0 && i >= 0) {
            return false;
        }

        // Base case when string is exhausted but pattern still has characters
        if (i < 0 && j >= 0) {
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }

        // If the indices in the dp matrix are initialized then return the dp[i][j]
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        // If the characters match or pattern has '?', move both indices backward
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return dp[i][j] = dfs(s, p, i - 1, j - 1, dp);
        }

        // If the current pattern character is '*', it can match zero or more characters
        if (p.charAt(j) == '*') {
            // '*' can match one character (move i - 1) or zero characters (move j - 1)
            return dp[i][j] = dfs(s, p, i - 1, j, dp) || dfs(s, p, i, j - 1, dp);
        }

        // If characters do not match
        return dp[i][j] = false;
    }

    // Main method to test isMatch
    public static void main(String[] args) {
        String s = "aa", p = "*";

        boolean result = isMatch(s, p);

        System.out.println("The string " + p + (result ? "" : " not") + " matches the entire regex " + s + ".");
    }
}
