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
   - Each pair of characters (for even-length palindromes)   
2. For each center (i, i) and (i, i+1), expand outwards as long as the characters match.
3. Every time a valid palindrome is found, increment the count.
4. A HashMap is used here (though not necessary for this problem) to cache and reuse the bounds of palindromes with the same center to avoid redundant calculations.
5. An auxiliary array `dp` is used where `dp[i]` stores the number of palindromes whose character indices sum to `i` (not standard but used in this implementation to accumulate counts).

Time Complexity: O(n^2), where `n` is the length of the string (due to expanding from each center).
Space Complexity: O(n), mainly for the `dp` array (HashMap could be O(n^2) in worst case).
*/

package DynamicProgramming.Medium;

import java.util.HashMap;

public class _647_Palindromic_Substrings {
  // Method to find the total number of palindromic substring
  public static int countSubstrings(String s) {
    // Intitialize the size to the length of the string
    int size = s.length();

    // Initialize the HashMap for storing the longest palandromic strings
    HashMap<Float, int[]> plandromicStringHashMap = new HashMap<>();

    // Initialize dp array for storing the number of sub string from that index
    int[] dp = new int[2 * size];

    // Find palindromic substring of size 0 to size of the string size
    for (int i = 0; i < size; i++) {
      expandFromTheCenter(s, i, i, plandromicStringHashMap, dp);
      expandFromTheCenter(s, i, i + 1, plandromicStringHashMap, dp);
    }

    // Initialize the maxSubstring variable for returning
    int maxSubstring = 0;

    // Accumulate the value of the dp array
    for (int value : dp) {
      maxSubstring += value;
    }

    // Retrun the maxSubstring
    return maxSubstring;
  }

  // Helper function to find the palandromic string
  private static void expandFromTheCenter(String s, int left, int right,
      HashMap<Float, int[]> plandromicStringHashMap, int[] dp) {
    // Get the mean of left and right
    float mean = (float) (left + right) / 2;

    // If mean is present in the plandromicStringHashMap then update the left and
    // right variable
    if (plandromicStringHashMap.containsKey(mean)) {
      int[] temp = plandromicStringHashMap.get(mean);
      left = temp[0];
      right = temp[1];
    }

    // Expand from the center to find the longest substring
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      // Update the plandromicStringHashMap
      plandromicStringHashMap.put((float) (left + right) / 2, new int[] { left, right });

      // Increment the value of the index in the dp array
      dp[left + right]++;

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
