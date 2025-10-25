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
Approach: We solve the Interleaving String problem using Dynamic Programming with space optimization.

1. We are given three strings: s1, s2, and s3.
2. The goal is to determine if s3 can be formed by interleaving s1 and s2.
   - Interleaving means maintaining the relative order of characters in s1 and s2.
3. We use a 1D boolean DP array `dp[n+1]`, where:
   - dp[j] = true if s3[0..i+j-1] can be formed by interleaving s1[0..i-1] and s2[0..j-1].
4. Base Case:
   - dp[0] = true (empty prefixes form empty s3)
   - dp[j] = dp[j-1] && s2[j-1] == s3[j-1] (initially filling first row for s1 prefix length 0)
5. Transition:
   - For each i from 1 to m:
     - dp[0] = dp[0] && s1[i-1] == s3[i-1] (first column)
     - For each j from 1 to n:
       - dp[j] = (dp[j] && s1[i-1] == s3[i+j-1]) || (dp[j-1] && s2[j-1] == s3[i+j-1])
       - This updates dp[j] in-place using values from the previous row and current row.
6. After filling the DP array, dp[n] contains the answer: true if s3 can be formed by interleaving s1 and s2, false otherwise.

Time Complexity: O(m*n)
Space Complexity: O(n) — optimized from O(m*n)
*/

package DynamicProgramming.Medium;

public class _97_Interleaving_String {
  // Method to find if we can make the string using other two string by inter
  // leaving
  public static boolean isInterleave(String s1, String s2, String s3) {
    // Initialize the length of the strings s1 and s2
    int m = s1.length(), n = s2.length(), l = s3.length();

    // If the length of the string is not matched to the addition of s1 and s2 then
    // return false
    if (m + n != l) {
      return false;
    }

    // Initialize the dp matrix for the memoization
    boolean[] dp = new boolean[n + 1];

    // Set the base value of the dp to true
    dp[0] = true;

    // Convert each string into the character array
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    char[] str3 = s3.toCharArray();

    // Fill the base case dp array
    for (int j = 1; j <= n; ++j) {
      dp[j] = dp[j - 1] && str2[j - 1] == str3[j - 1];
    }

    // Fill the dp array
    for (int i = 1; i <= m; ++i) {
      dp[0] = dp[0] && str1[i - 1] == str3[i - 1];
      for (int j = 1; j <= n; ++j) {
        dp[j] = (dp[j] && str1[i - 1] == str3[i + j - 1])
            || (dp[j - 1] && str2[j - 1] == str3[i + j - 1]);
      }
    }

    // Return the last value of dp as the result
    return dp[n];
  }

  // Main method to test isInterleave
  public static void main(String[] args) {
    String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";

    boolean result = isInterleave(s1, s2, s3);

    System.out.println("We can " + (result ? "" : "not ") + "form the string " + s3 + " by inter leaving of string "
        + s1 + " and " + s2 + ".");
  }
}
