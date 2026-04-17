/*
LeetCode Problem: https://leetcode.com/problems/unique-length-3-palindromic-subsequences/

Question: 1930. Unique Length-3 Palindromic Subsequences

Problem Statement: Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".

Example 1:
Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")

Example 2:
Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".

Example 3:
Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")

Constraints:
3 <= s.length <= 10^5
s consists of only lowercase English letters.
*/

/*
Approach: First/Last Occurrence + Prefix Frequency Matrix

Goal:
- Count distinct palindromic subsequences of length 3.

Core Idea:
- A palindrome of length 3 has form:
    x y x
- For each character x:
   - Find its first and last occurrence.
   - Any distinct character y between them forms a unique palindrome.
- Use a prefix frequency matrix to quickly check
  whether a character exists between two indices.

Algorithm Steps:
1. Record:
   - left[c]  = first occurrence of character c
   - right[c] = last occurrence of character c
2. Build prefix frequency matrix:
   - prefix[i][c] = occurrences of c before index i
3. For each possible outer character x:
   - If first and last occurrence are different:
       - Check every character y from 'a' to 'z'
       - If y appears between left[x] and right[x]:
           → count palindrome x y x
4. Return total count.

Time Complexity:
- O(n * 26)

Space Complexity:
- O(n * 26)

Result:
- Returns the number of unique length-3 palindromic subsequences.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the number of unique palindromes of length three that are a
  // subsequence of s
  public int countPalindromicSubsequence(String s) {
    // Convert the string into character array
    char[] str = s.toCharArray();

    // Initialize the prefix matrix
    int[][] prefix = new int[str.length + 1][26];

    // Initialize the left and right index set for the character
    int[] left = new int[26];
    int[] right = new int[26];

    // Initialize the index set to -1
    for (int i = 0; i < 26; i++) {
      left[i] = right[i] = -1;
    }

    // Fill the left, right and prefix index
    for (int i = 0; i < str.length; i++) {
      // Get the index of the character offset to zero
      int j = str[i] - 'a';

      // If left is not initialize then update the left index set
      if (left[j] == -1) {
        left[j] = i;
      }

      // Update the right index set
      right[j] = i;

      // Copy the last prefix
      for (int k = 0; k < 26; k++) {
        prefix[i + 1][k] = prefix[i][k];
      }

      // Update the frequency count of the current index
      prefix[i + 1][j]++;
    }

    // Initialize the result variable
    int res = 0;

    // Check for all the characters palindrome
    for (int ends = 0; ends < 26; ends++) {
      // If character is not present or only present onces then skip the iteration
      if (left[ends] == -1 || left[ends] == right[ends]) {
        continue;
      }

      // Get the left and right index of the character
      int l = left[ends], r = right[ends];

      // Update the result if the difference between them is greater than zero
      for (int mid = 0; mid < 26; mid++) {
        if (prefix[r][mid] - prefix[l + 1][mid] > 0) {
          res++;
        }
      }
    }

    // Return the result
    return res;
  }
}

public class _1930_Unique_Length_3_Palindromic_Subsequences {
  // Main method to test countPalindromicSubsequence
  public static void main(String[] args) {
    String s = "";

    int result = new Solution().countPalindromicSubsequence(s);

    System.out
        .println("The number of unique palindromes of length three that are a subsequence of " + s + " is : " + result);
  }
}
