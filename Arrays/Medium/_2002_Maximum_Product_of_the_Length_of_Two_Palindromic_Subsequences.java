/*
LeetCode Problem: https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/

Question: 2002. Maximum Product of the Length of Two Palindromic Subsequences

Problem Statement: Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.

Return the maximum possible product of the lengths of the two palindromic subsequences.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.

Example 1:
example-1
Input: s = "leetcodecom"
Output: 9
Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
The product of their lengths is: 3 * 3 = 9.

Example 2:
Input: s = "bb"
Output: 1
Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
The product of their lengths is: 1 * 1 = 1.

Example 3:
Input: s = "accbcaxxcxx"
Output: 25
Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
The product of their lengths is: 5 * 5 = 25.

Constraints:
2 <= s.length <= 12
s consists of lowercase English letters only.
*/

/*
Approach: Bitmask Enumeration + Palindrome Validation

Goal:
- Find two disjoint palindromic subsequences whose length product is maximum.

Core Idea:
- Represent each subsequence using a bitmask.
- Enumerate all non-empty subsequences.
- Store only masks that form palindromes along with their lengths.
- Compare every pair of palindromic masks:
   - If masks do not overlap ((mask1 & mask2) == 0),
     update maximum product.

Algorithm Steps:
1. Enumerate all masks from 1 to (1 << n) - 1.
2. Build subsequence corresponding to each mask.
3. Check whether subsequence is palindromic:
   - If yes, store:
       mask → subsequence length
4. Iterate through all pairs of stored masks:
   - If masks are disjoint:
       result = max(result, len1 * len2)
5. Return result.

Time Complexity:
- O(2^n * n + p^2)
  where p = number of palindromic masks

Space Complexity:
- O(2^n)

Result:
- Returns the maximum product of lengths of two disjoint palindromic subsequences.
*/

package Arrays.Medium;

import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find the maximum possible product of the lengths of the two
  // palindromic subsequences
  public int maxProduct(String s) {
    // Get the length of the s
    int length = s.length();

    // Initialize the hash map for the mask value
    HashMap<Integer, Integer> palindromeMap = new HashMap<>();

    // Find the all palindromic subsequences
    for (int i = 1; i < (1 << length); i++) {
      // Initialize the string builder for the subsequences
      StringBuilder sb = new StringBuilder();

      // Find the current subsequence
      for (int j = 0; j < length; j++) {
        if ((i & (1 << j)) != 0) {
          sb.append(s.charAt(j));
        }
      }

      // If string is palindromic then add it to the hash map
      if (this.isPalindromicSubsequence(sb.toString())) {
        palindromeMap.put(i, sb.length());
      }
    }

    // Initialize the result variable
    int result = 0;

    // Find the max product of the substring
    for (int i : palindromeMap.keySet()) {
      for (int j : palindromeMap.keySet()) {
        if ((i & j) == 0) {
          result = Math.max(result, palindromeMap.get(i) * palindromeMap.get(j));
        }
      }
    }

    // Return the result
    return result;
  }

  // Helper method to find if the subsequence is palindromic or not
  private boolean isPalindromicSubsequence(String s) {
    // Initialize two pointer left and right
    int left = 0, right = s.length() - 1;

    // Check if the strings are palindromic or not
    while (left < right) {
      // If characters is mismatched then return false
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }

      // Update the pointers
      left++;
      right--;
    }

    // Return ture in the end
    return true;
  }
}

public class _2002_Maximum_Product_of_the_Length_of_Two_Palindromic_Subsequences {
  // Main method to test maxProduct
  public static void main(String[] args) {
    String s = "accbcaxxcxx";

    int result = new Solution().maxProduct(s);

    System.out
        .println("The maximum possible product of the lengths of the two palindromic subsequences of " + s + " is : "
            + result);
  }
}
