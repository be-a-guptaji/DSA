/*
LeetCode Problem: https://leetcode.com/problems/edit-distance/

Question: 72. Edit Distance

Problem Statement: Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
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

public class _72_Edit_Distance {
  // Method to find the minimum number of operations required to convert word1 to
  // word2
  public static int minDistance(String word1, String word2) {
    // Convert the words into the character array
    char[] str1 = word1.toCharArray();
    char[] str2 = word2.toCharArray();

    // Get the length of the character array
    int len1 = str1.length, len2 = str2.length;

    // Edge case if both of the strings are empty or both words are equal then
    // return 0
    if ((len1 == 0 && len2 == 0 || (word1.equals(word2)))) {
      return 0;
    }

    // Edge case if one of the strings are empty then return 0 max length between
    // both of the string
    if (len1 == 0 || len2 == 0) {
      return Math.max(len1, len2);
    }

    // Initialize the dp matrix for memoization
    int[][] dp = new int[len1 + 1][len2 + 1];

    // Initialize the base case of dp fill the last row and column with the number
    // of operations it would take to make that string

    // For row
    for (int i = 0; i <= len1; i++) {
      dp[i][len2] = len1 - i;
    }

    // For column
    for (int i = 0; i <= len2; i++) {
      dp[len1][i] = len2 - i;
    }

    // Fill the dp matrix in the bottom up manner
    for (int i = len1 - 1; i >= 0; i--) {
      for (int j = len2 - 1; j >= 0; j--) {
        // If the character i and j value is equal then fill the dp[i][j] to the
        // dp[i + 1][j + 1]
        if (str1[i] == str2[j]) {
          dp[i][j] = dp[i + 1][j + 1];
        }
        // If they are not equal then fill the dp[i][j] to the min of dp[i][j + 1], dp[i
        // + 1][j] and dp[i + 1][j + 1] + 1
        else {
          dp[i][j] = Math.min(dp[i][j + 1], Math.min(dp[i + 1][j], dp[i + 1][j + 1])) + 1;
        }
      }
    }
    
    // Return dp[0][0] as a result
    return dp[0][0];
  }

  // Main method to test minDistance
  public static void main(String[] args) {
    String word1 = "horse", word2 = "ros";

    int result = minDistance(word1, word2);

    System.out
        .println("The minimum number of operations required to convert " + word1 + " to " + word2 + " is : " + result);
  }
}
