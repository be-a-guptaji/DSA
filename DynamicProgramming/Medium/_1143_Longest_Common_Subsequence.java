/*
LeetCode Problem: https://leetcode.com/problems/longest-common-subsequence/

Question: 1143. Longest Common Subsequence

Problem Statement: Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
*/

/*
Approach: We use **Bottom-Up Dynamic Programming** to find the length of the Longest Common Subsequence (LCS) between two strings.

1. Convert both strings to character arrays for efficient access.
2. Create a 2D `dp` table of size (len1 + 1) x (len2 + 1), where `dp[i][j]` represents
   the length of the LCS between `text1[i:]` and `text2[j:]`.
3. Iterate from the bottom-right of the `dp` table to the top-left:
   - If characters match at current positions (`s1[i] == s2[j]`), then:
       dp[i][j] = 1 + dp[i+1][j+1]
   - Else, take the maximum from skipping one character from either string:
       dp[i][j] = max(dp[i+1][j], dp[i][j+1])
4. The final result is stored in `dp[0][0]`, which represents the LCS of the full strings.

Time Complexity: O(m * n), where `m` and `n` are the lengths of the two strings.
Space Complexity: O(m * n), for storing the DP table.
*/

package DynamicProgramming.Medium;

public class _1143_Longest_Common_Subsequence {
  // Method to find the longest common subsequence
  public static int longestCommonSubsequence(String text1, String text2) {
    // Get the length of both the text
    int len1 = text1.length(), len2 = text2.length();

    // Convert the string into the character array
    char[] s1 = text1.toCharArray();
    char[] s2 = text2.toCharArray();

    // Initialize the dp matrix
    int[][] dp = new int[len1 + 1][len2 + 1];

    // Find the length of the longest common subsequence in bottom up manner
    for (int i = len1 - 1; i >= 0; i--) {
      for (int j = len2 - 1; j >= 0; j--) {
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

  // Main method to test longestCommonSubsequence
  public static void main(String[] args) {
    String text1 = "abcde", text2 = "ace";

    int result = longestCommonSubsequence(text1, text2);

    System.out.println("The longest common subsequence of " + text1 + " and " + text2 + " is : " + result);
  }
}
