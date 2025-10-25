/*
LeetCode Problem: https://leetcode.com/problems/interleaving-string/

Question: 97. Interleaving String

Problem Statement: Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

Example 3:
Input: s1 = "", s2 = "", s3 = ""
Output: true

Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.
 

Follow up: Could you solve it using only O(s2.length) additional memory space?
*/

/*
Approach: We solve the Interleaving String problem using Dynamic Programming.

1. We are given three strings: s1, s2, and s3.
2. The goal is to determine if s3 can be formed by interleaving s1 and s2.
   - Interleaving means maintaining the relative order of characters in s1 and s2.
3. We use a 2D boolean DP table `dp[len1+1][len2+1]`, where:
   - dp[i][j] = true if s3[0..i+j-1] can be formed by interleaving s1[0..i-1] and s2[0..j-1].
4. Base Case:
   - dp[len1][len2] = true (empty suffixes form empty s3)
5. Transition:
   - If s1[i] == s3[i+j] and dp[i+1][j] is true → dp[i][j] = true
   - If s2[j] == s3[i+j] and dp[i][j+1] is true → dp[i][j] = true
6. We fill the DP table in reverse order (bottom-up).
7. The result is stored in dp[0][0].

Time Complexity: O(n*m)
Space Complexity: O(n*m)
*/

package DynamicProgramming.Medium;

public class _97_Interleaving_String {
  // Method to find if we can make the string using other two string by inter
  // leaving
  public static boolean isInterleave(String s1, String s2, String s3) {
    // Initialize the length of the strings s1 and s2
    int len1 = s1.length(), len2 = s2.length();

    // If the length of the string is not matched to the addition of s1 and s2 then
    // return false
    if (len1 + len2 != s3.length()) {
      return false;
    }

    // Convert each string into the character array
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    char[] str3 = s3.toCharArray();

    // Initialize the dp matrix for the memoization
    boolean[][] dp = new boolean[len1 + 1][len2 + 1];

    // Set the base value of the dp to true
    dp[len1][len2] = true;

    // Fill the dp array
    for (int i = len1; i >= 0; i--) {
      for (int j = len2; j >= 0; j--) {
        // If the index is not out of bound then do the computation
        if (i < len1 && str1[i] == str3[i + j] && dp[i + 1][j]) {
          dp[i][j] = true;
        }
        if (j < len2 && str2[j] == str3[i + j] && dp[i][j + 1]) {
          dp[i][j] = true;
        }
      }
    }

    // Return the first value of dp as the result
    return dp[0][0];
  }

  // Main method to test isInterleave
  public static void main(String[] args) {
    String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";

    boolean result = isInterleave(s1, s2, s3);

    System.out.println("We can " + (result ? "" : "not ") + "form the string " + s3 + " by inter leaving of string "
        + s1 + " and " + s2 + ".");
  }
}
