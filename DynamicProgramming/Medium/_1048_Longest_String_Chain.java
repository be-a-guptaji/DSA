/*
LeetCode Problem: https://leetcode.com/problems/longest-string-chain/

Question: 1048. Longest String Chain

Problem Statement: You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

Example 1:
Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

Example 2:
Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

Example 3:
Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
*/

/*
Approach:
This solution handles the "Longest String Chain" (Leetcode 1048) problem.

Key Idea:
- Use Dynamic Programming (DP) to find the longest possible chain of words where
  each word in the chain is formed by adding exactly one character to the previous word.
- Each word can extend the chain of a shorter predecessor if the shorter word can become
  the current word by inserting exactly one letter.

Steps:
1. Sort the array of words based on their lengths.
   - This ensures that all potential predecessor words appear before longer words.
2. Initialize a `dp` array of size `n` (where n = number of words) with all values as 1.
   - Each word is a valid chain of length 1 by itself.
3. For each word `i` in the sorted array:
   - Check all previous words `j` where `j < i`.
   - If `words[j]` can be a valid predecessor of `words[i]` 
     (i.e., `words[j].length() + 1 == words[i].length()` and differs by one character),
     then update:
       `dp[i] = max(dp[i], dp[j] + 1)`.
4. Track the maximum value in `dp` which represents the length of the longest string chain.
5. Return the maximum chain length.

Helper Function:
- A helper method `canBeSuccessor(String shorter, String longer)` is used to check if 
  `shorter` can be transformed into `longer` by adding exactly one character.

Time Complexity: O(n² * L), where n is the number of words and L is the average word length.
Space Complexity: O(n), due to the `dp` array.
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

public class _1048_Longest_String_Chain {
  // Method to find the longest string chain
  public static int longestStrChain(String[] words) {
    // Sort the array based on the length of the string
    Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

    // Initialize size of the nums array
    int size = words.length;

    // Initialize a dp array
    int[] dp = new int[size];

    // Iterate over the nums array
    for (int i = 0; i < size; i++) {
      // Get the current value
      String current = words[i];
      int strlen = current.length();

      // Traverse the array untill we find the element with is differ by only one
      // character
      for (int j = 0; j < i; j++) {
        // If we find the element then update the dp array
        if (words[j].length() + 1 == strlen && canBeSuccessor(words[j], current)) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    // Initialize the maxLengthOfLSC variable for the max length
    int maxLengthOfLSC = dp[0];

    // Find the max element from the dp array
    for (int i = 1; i < size; i++) {
      maxLengthOfLSC = Math.max(maxLengthOfLSC, dp[i]);
    }

    // Return the maxLengthOfLSC
    return maxLengthOfLSC + 1;
  }

  // Helper method to find if strings can be in chain or not
  private static boolean canBeSuccessor(String s1, String s2) {
    // Convert the strings into the character array
    char[] a = s1.toCharArray();
    char[] b = s2.toCharArray();

    // Intialize the length of the character a and the index of the b array
    int len1 = a.length, len2 = b.length, indexA = 0, indexB = 0;

    while (indexA < len1 && indexB < len2) {
      if (a[indexA] == b[indexB]) {
        indexA++;
        indexB++;
      } else {
        indexB++;
      }
    }

    // Return boolean based on the condition
    return (indexA == indexB || indexA == len1 && indexB == len2);
  }

  // Main method to test longestStrChain
  public static void main(String[] args) {
    String[] words = { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" };

    int result = longestStrChain(words);

    System.out.println("The longest string chain is : " + result);
  }
}
