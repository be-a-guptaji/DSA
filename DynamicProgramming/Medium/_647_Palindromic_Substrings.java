/*
LeetCode Problem: https://leetcode.com/problems/palindromic-substrings/

Question: 647. Palindromic Substrings

Problem Statement: Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

Example 1:
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
*/

/*
Approach: We use the **Expand Around Center** technique to count all palindromic substrings in a given string.

1. For a string of length `n`, there are `2n - 1` possible centers for palindromic substrings:
   - Each character (for odd-length palindromes)
   - Each pair of adjacent characters (for even-length palindromes)
2. For each center (i, i) and (i, i+1), expand outwards while the characters on both sides are equal.
3. Every time a valid palindrome is found, increment the count.
4. This solution uses no extra memory beyond a few variables — it's space-efficient.

Time Complexity: O(n^2), where `n` is the length of the string.
Space Complexity: O(1), since we only use constant extra space.
*/

package DynamicProgramming.Medium;

public class _647_Palindromic_Substrings {
  // Intitialize the maxSubstring to get the number of substring
  private static int maxSubstring;

  // Method to find the total number of palindromic substring
  public static int countSubstrings(String s) {
    // Intitialize the size to the length of the string
    int size = s.length();

    // Initialize the maxSubstring variable for returning
    maxSubstring = 0;

    // Find palindromic substring of size 0 to size of the string size
    for (int i = 0; i < size; i++) {
      expandFromTheCenter(s, i, i);
      expandFromTheCenter(s, i, i + 1);
    }

    // Retrun the maxSubstring
    return maxSubstring;
  }

  // Helper function to find the palandromic string
  private static void expandFromTheCenter(String s, int left, int right) {
    // Expand from the center to find the longest substring
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      // Increment the value of the maxSubstring
      maxSubstring++;

      // Decrement the left pointer and increment the right pointer
      left--;
      right++;
    }
  }

  // Main method to test countSubstrings
  public static void main(String[] args) {
    String s = "aaa";

    int result = countSubstrings(s);

    System.out.println("The number of palindromic substring in " + s + " is : " + result);
  }
}
