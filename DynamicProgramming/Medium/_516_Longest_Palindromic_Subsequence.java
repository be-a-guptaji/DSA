/*
LeetCode Problem: https://leetcode.com/problems/longest-palindromic-subsequence/

Question: 516. Longest Palindromic Subsequence

Problem Statement: Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:
Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
*/

/*
Approach: We solve the Longest Palindromic Subsequence (LPS) problem by reducing it to a Longest Common Subsequence (LCS) problem.

1. A palindromic subsequence is a sequence that reads the same forward and backward.
2. The LPS of a string `s` is equal to the LCS of `s` and its reversed version.
3. So, we reverse the input string and compute the LCS between the original and reversed strings.
4. We use a 2D DP table `dp[n][n]`, where `dp[i][j]` represents the length of the LCS between:
   - the suffix of original string starting at index `i`
   - and the suffix of reversed string starting at index `j`
5. We fill the table in bottom-up fashion:
   - If `s1[i] == s2[j]`, then `dp[i][j] = 1 + dp[i+1][j+1]`
   - Else, `dp[i][j] = max(dp[i+1][j], dp[i][j+1])`
6. The result is stored in `dp[0][0]`, which gives the length of the longest palindromic subsequence.

Time Complexity: O(n^2), where `n` is the length of the string.
Space Complexity: O(n^2), due to the DP table.
*/

package DynamicProgramming.Medium;

public class _516_Longest_Palindromic_Subsequence {
  // Method to find the longest palindromic subsequence
  public static int longestPalindromeSubseq(String s) {
    // Get the length of both the text
    int len = s.length();

    // Convert the string into the character array
    char[] s1 = s.toCharArray();
    char[] s2 = s.toCharArray();

    // Reverse the char array in place
    int left = 0, right = len - 1;
    while (left < right) {
      char temp = s2[left];
      s2[left] = s2[right];
      s2[right] = temp;
      left++;
      right--;
    }

    // Initialize the dp matrix
    int[][] dp = new int[len + 1][len + 1];

    // Find the length of the longest palindromic subsequence in bottom up manner
    for (int i = len - 1; i >= 0; i--) {
      for (int j = len - 1; j >= 0; j--) {
        // If character match then get the digonal element + 1 else max of the right and
        // the bottom element
        if (s1[i] == s2[j]) {
          dp[i][j] = dp[i + 1][j + 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
        }
      }
    }

    // Retrun the first value of the matrix
    return dp[0][0];
  }

  // Main method to test longestPalindromeSubseq
  public static void main(String[] args) {
    String s = "bbbab";

    int result = longestPalindromeSubseq(s);

    System.out.println("The longest palindromic subsequence of " + s + " is : " + result);
  }
}
