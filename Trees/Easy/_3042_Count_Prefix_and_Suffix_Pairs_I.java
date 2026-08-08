/*
LeetCode Problem: https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/

Question: 3042. Count Prefix and Suffix Pairs I

Problem Statement: You are given a 0-indexed string array words.

Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:

    isPrefixAndSuffix(str1, str2) returns true if str1 is both a and a of str2, and false otherwise.

For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa" and also a suffix, but isPrefixAndSuffix("abc", "abcd") is false.

Return an integer denoting the number of index pairs (i, j) such that i < j, and isPrefixAndSuffix(words[i], words[j]) is true.

Example 1:
Input: words = ["a","aba","ababa","aa"]
Output: 4
Explanation: In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix("a", "aba") is true.
i = 0 and j = 2 because isPrefixAndSuffix("a", "ababa") is true.
i = 0 and j = 3 because isPrefixAndSuffix("a", "aa") is true.
i = 1 and j = 2 because isPrefixAndSuffix("aba", "ababa") is true.
Therefore, the answer is 4.

Example 2:
Input: words = ["pa","papa","ma","mama"]
Output: 2
Explanation: In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix("pa", "papa") is true.
i = 2 and j = 3 because isPrefixAndSuffix("ma", "mama") is true.
Therefore, the answer is 2.  

Example 3:
Input: words = ["abab","ab"]
Output: 0
Explanation: In this example, the only valid index pair is i = 0 and j = 1, and isPrefixAndSuffix("abab", "ab") is false.
Therefore, the answer is 0.

Constraints:
    1 <= words.length <= 50
    1 <= words[i].length <= 10
    words[i] consists only of lowercase English letters.
*/

/*
Approach: Brute-force Pairwise Prefix-Suffix Check
Goal:
- Count all index pairs (i, j) where i < j and
  words[i] is both a prefix and suffix of words[j].
Core Idea:
- For each ordered pair (i, j) with i < j, check
  if words[j] starts with and ends with words[i]
  using built-in String methods.
Algorithm Steps:
1. Initialize pair = 0.
2. For each i from 0 to words.length - 1:
   - For each j from i + 1 to words.length - 1:
     - If words[j].startsWith(words[i]) &&
       words[j].endsWith(words[i]), increment pair.
3. Return pair.
Time Complexity:
- O(n^2 * m)
where n is the number of words and m is the average
word length, since startsWith and endsWith each
run in O(m) and are called for every pair.
Space Complexity:
- O(1)
no additional data structures are used.
Result:
- Returns the count of valid (i, j) prefix-suffix
  pairs.
*/

package Trees.Easy;

// Solution Class
class Solution {
  // Method to find an integer denoting the number of index pairs
  public int countPrefixSuffixPairs(String[] words) {
    // Initialize the pair variable
    int pair = 0;

    // Iterate over the words array
    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if (words[j].startsWith(words[i]) && words[j].endsWith(words[i])) {
          pair++;
        }
      }
    }

    // Return the pairs
    return pair;
  }
}

// Main Class
public class _3042_Count_Prefix_and_Suffix_Pairs_I {
  // Main method to test countPrefixSuffixPairs
  public static void main(String[] args) {
    String[] words = new String[] { "pa", "papa", "ma", "mama" };

    int result = new Solution().countPrefixSuffixPairs(words);

    System.out.println("An integer denoting the number of index pairs is : " + result);
  }
}
