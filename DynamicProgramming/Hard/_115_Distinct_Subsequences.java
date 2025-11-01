/*
LeetCode Problem: https://leetcode.com/problems/distinct-subsequences/

Question: 115. Distinct Subsequences

Problem Statement: Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit

Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */

/*
Approach:
This problem is solved using recursion with memoization (top-down dynamic programming).

We want to count the number of distinct subsequences of string `s` that equal string `t`.

Key ideas:
1. Let `dfs(i, j)` represent the number of distinct subsequences of `s[0...i]`
   that form `t[0...j]`.
2. We have two main cases:
   - If characters match (`s[i] == t[j]`):
        We can either:
        a) Include `s[i]` in the subsequence → dfs(i-1, j-1)
        b) Exclude `s[i]` → dfs(i-1, j)
        So, dfs(i, j) = dfs(i-1, j-1) + dfs(i-1, j)
   - If characters don’t match:
        We can only skip `s[i]` → dfs(i-1, j)
3. Base cases:
   - If `j < 0`: All characters of `t` are matched → return 1
   - If `i < 0`: `s` is exhausted but `t` is not → return 0
4. Use memoization (`dp[i][j]`) to store results of subproblems to avoid recomputation.

Recurrence relation:
    dfs(i, j) = dfs(i-1, j) + dfs(i-1, j-1)  if s[i] == t[j]
    dfs(i, j) = dfs(i-1, j)                  if s[i] != t[j]

Base cases:
    if j < 0 → return 1
    if i < 0 → return 0

Time Complexity: O(n * m)
Space Complexity: O(n * m)
where n = length of s, m = length of t
*/

package DynamicProgramming.Hard;

import java.util.Arrays;

public class _115_Distinct_Subsequences {
    // Method to find the number of distinct subsequences of s which equals t
    public static int numDistinct(String s, String t) {
        // Get the length of both the strings
        int slen = s.length(), tlen = t.length();

        // Initialize the dp matrix for memoization
        int[][] dp = new int[slen][tlen];

        // Fill the dp matrix with -1
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        // Return the dfs recursive function
        return dfs(s, t, slen - 1, tlen - 1, dp);
    }

    // Helper method to find the distinct ways
    private static int dfs(String s, String t, int i, int j, int[][] dp) {
        // If j is out of bound then return 1
        if (j == -1) {
            return 1;
        }

        // If i is out of bound then reutrn 0
        if (i == -1) {
            return 0;
        }

        // If we memoized it earlier then return the result
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // Initialize the result variable
        int result;

        // If both chracter match then do dfs on (i - 1,j - 1) and (i - 1,j) else do dfs
        // on (i - 1,j)
        if (s.charAt(i) == t.charAt(j)) {
            result = dfs(s, t, i - 1, j - 1, dp) + dfs(s, t, i - 1, j, dp);
        } else {
            result = dfs(s, t, i - 1, j, dp);
        }

        // Return the result and store it in the chache
        return dp[i][j] = result;
    }

    // Main method to test numDistinct
    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";

        int result = numDistinct(s, t);

        System.out.println("the number of distinct subsequences of " + s + " which equals " + t + " is : " + result);
    }
}
