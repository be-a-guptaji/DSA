/*
LeetCode Problem: https://leetcode.com/problems/count-different-palindromic-subsequences/

Question: 730. Count Different Palindromic Subsequences

Problem Statement: Given a string s, return the number of different non-empty palindromic subsequences in s. Since the answer may be very large, return it modulo 10^9 + 7.

A subsequence of a string is obtained by deleting zero or more characters from the string.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences a1, a2, ... and b1, b2, ... are different if there is some i for which ai != bi.

Example 1:
Input: s = "bccb"
Output: 6
Explanation: The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.

Example 2:
Input: s = "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"
Output: 104860361
Explanation: There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.

Constraints:

1 <= s.length <= 1000
s[i] is either 'a', 'b', 'c', or 'd'.
 */

/*
Approach: Dynamic Programming (Bottom-Up)

Key Idea:
- We want to count all *distinct* non-empty palindromic subsequences in a given string.
- Use dynamic programming where dp[i][j] represents the number of distinct palindromic
  subsequences in the substring s[i...j].
- For each pair of indices (i, j):
  1. If s[i] != s[j], the result is the sum of counts from substrings (i+1, j) and (i, j-1),
     minus the overlap dp[i+1][j-1].
  2. If s[i] == s[j], then every palindrome in s[i+1...j-1] can form two new palindromes
     by adding s[i] and s[j] to both ends. 
     - Additionally, we handle duplicates of s[i] inside s[i+1...j-1] to avoid overcounting.

Steps:
1. Initialize dp[i][i] = 1 for all i (each single character is a palindrome).
2. Iterate over all possible substring lengths, from shorter to longer.
3. Apply recurrence relations:
   - If chars[i] != chars[j]:
       dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1]
   - If chars[i] == chars[j]:
       - Find next and previous occurrences of chars[i] inside the substring.
       - Adjust the count depending on whether the inner substring contains zero, one,
         or more occurrences of that character.
4. Apply modulo 1,000,000,007 to prevent overflow and ensure positive results.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

package DynamicProgramming.Hard;

public class _730_Count_Different_Palindromic_Subsequences {
    // Method to find the number of different non-empty palindromic subsequences in
    // string s
    public static int countPalindromicSubsequences(String s) {
        // Initialize the length of the string
        int length = s.length();

        // Convert the string into a character array for easy access
        char[] chars = s.toCharArray();

        // Initialize the dp matrix
        // dp[i][j] represents the number of distinct palindromic subsequences in
        // substring s[i...j]
        long[][] dp = new long[length][length];

        // Iterate over the string in reverse order for the starting index
        for (int i = length - 1; i >= 0; i--) {
            // Every single character is a palindrome of length 1
            dp[i][i] = 1;

            // Iterate over ending indices from i+1 to length-1
            for (int j = i + 1; j < length; j++) {
                // If characters at both ends do not match
                if (chars[i] != chars[j]) {
                    // Exclude either end and subtract the overlap
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                } else {
                    // If characters at both ends match
                    // Double the count of palindromic subsequences in the middle substring
                    dp[i][j] = dp[i + 1][j - 1] * 2;

                    // Initialize left and right pointers to find duplicates of chars[i] inside the
                    // substring
                    int left = i + 1, right = j - 1;

                    // Move left forward to the first occurrence of chars[i]
                    while (left <= right && chars[left] != chars[i]) {
                        left++;
                    }

                    // Move right backward to the last occurrence of chars[i]
                    while (left <= right && chars[right] != chars[i]) {
                        right--;
                    }

                    // Case 1: No matching characters of chars[i] inside the substring
                    if (left > right) {
                        dp[i][j] += 2;
                    }
                    // Case 2: One matching character inside (only one duplicate)
                    else if (left == right) {
                        dp[i][j] += 1;
                    }
                    // Case 3: More than one matching character inside
                    else {
                        dp[i][j] -= dp[left + 1][right - 1];
                    }
                }

                // Ensure the value stays positive and within modulo range
                dp[i][j] = (dp[i][j] + 1_000_000_007) % 1_000_000_007;
            }
        }

        // Return the number of distinct palindromic subsequences in the entire string
        return (int) dp[0][length - 1];
    }

    // Main method to test countPalindromicSubsequences
    public static void main(String[] args) {
        String s = "bccb";

        int result = countPalindromicSubsequences(s);

        System.out.println("The number of different non-empty palindromic subsequences in " + s + " is : " + result);
    }
}
