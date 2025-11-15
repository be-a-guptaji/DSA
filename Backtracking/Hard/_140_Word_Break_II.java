/*
LeetCode Problem: https://leetcode.com/problems/word-break-ii/

Question: 140. Word Break II

Problem Statement: Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 10^5.
 */

/*
Approach:
1. The goal is to split the string into valid words present in the dictionary
   and return all possible valid sentences.

2. To solve this, we use a backtracking approach:
   - Starting from index 0, try to form every possible substring.
   - If the substring exists in the dictionary, recursively find valid sentences
     starting from the next index.

3. This produces overlapping subproblems, so we use memoization (HashMap):
   - If a result for a starting index has been computed once,
     store it and reuse it later to avoid repeated computation.

4. For each valid substring:
     • recursively get all sentences from the next index
     • combine the substring with each returned sentence part
     • add the complete sentence to the result list

5. When index reaches the end of the string, return a list containing
   an empty string to mark valid sentence termination.

Time Complexity: exponential in worst case, but improved heavily by memoization.
Space Complexity: O(n * k) for recursion + memo storage.
*/

package Backtracking.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class _140_Word_Break_II {
  // Method to find all possible sentences
  public static List<String> wordBreak(String s, List<String> wordDict) {
    // Initialize the HashSet for fast lookup
    HashSet<String> wordSet = new HashSet<>(wordDict);

    // Call the recursive backtrack method with memo
    return backtrack(0, s.length(), s, new HashMap<>(), wordSet);
  }

  // Helper method to find all valid sentences using backtracking + memoization
  private static List<String> backtrack(int index, int length, String s,
      HashMap<Integer, List<String>> memo,
      HashSet<String> wordSet) {

    // If answer for the index is already computed then return from memo
    if (memo.containsKey(index)) {
      return memo.get(index);
    }

    // Base case: when index reaches the end of the string
    if (index == length) {
      return new ArrayList<>(Arrays.asList(""));
    }

    // Initialize the list of sentences formed from this index
    List<String> sentences = new ArrayList<>();

    // Iterate over the string to get all valid substrings
    for (int i = index; i < length; i++) {

      // Get the substring from index to i
      String subString = s.substring(index, i + 1);

      // If substring is not present in the dictionary then skip the iteration
      if (!wordSet.contains(subString)) {
        continue;
      }

      // Recursive call to find sentences starting from i+1
      List<String> result = backtrack(i + 1, length, s, memo, wordSet);

      // Iterate over all returned sentence parts
      for (String str : result) {

        // Make the final sentence using current substring and returned part
        if (str.equals("")) {
          sentences.add(subString);
        } else {
          sentences.add(subString + " " + str);
        }
      }
    }

    // Store the computed result in memo before returning
    memo.put(index, sentences);

    // Return the list of sentences
    return sentences;
  }

  // Main method to test wordBreak
  public static void main(String[] args) {
    String num = "pineapplepenapple";
    List<String> wordDict = new ArrayList<>();
    String[] words = { "apple", "pen", "applepen", "pine", "pineapple" };

    Collections.addAll(wordDict, words);

    List<String> result = wordBreak(num, wordDict);

    System.out.println("The all possible sentences is : " + result);
  }
}
