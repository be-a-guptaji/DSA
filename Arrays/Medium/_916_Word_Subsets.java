/*
LeetCode Problem: https://leetcode.com/problems/word-subsets/

Question: 916. Word Subsets

Problem Statement: You are given two string arrays words1 and words2.

A string b is a subset of string a if every letter in b occurs in a including multiplicity.

For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.

Return an array of all the universal strings in words1. You may return the answer in any order.

Example 1:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]

Example 2:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lc","eo"]
Output: ["leetcode"]

Example 3:
Input: words1 = ["acaac","cccbb","aacbb","caacc","bcbbb"], words2 = ["c","cc","b"]
Output: ["cccbb"]

Constraints:
1 <= words1.length, words2.length <= 10^4
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.
*/

/*
Approach: Frequency Aggregation + Superset Check

Goal:
- Find all words in words1 that are universal with respect to words2,
  i.e., each word in words1 must contain all characters required by words2.

Core Idea:
- Instead of checking each word in words2 separately,
  build a single aggregated frequency requirement:
    frequency[c] = max count of character c across all words2.
- A word in words1 is valid if it satisfies this frequency requirement.

Algorithm Steps:
1. Initialize frequency[26] = {0}.
2. For each word in words2:
   - Compute temp frequency.
   - Update global frequency:
       frequency[i] = max(frequency[i], temp[i])
3. For each word in words1:
   - Compute its frequency.
   - Check if for all characters:
       currentFrequency[i] ≥ frequency[i]
   - If yes → add to result.
4. Return result list.

Time Complexity:
- O(W1 * L1 + W2 * L2 + 26 * (W1 + W2))
  W1, W2 = number of words
  L1, L2 = average lengths

Space Complexity:
- O(1) (fixed size arrays)

Result:
- Returns all universal words from words1.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the number of subarrays filled with 0
  public List<String> wordSubsets(String[] words1, String[] words2) {
    // Initialize the character array for the frequency of the character
    int[] frequency = new int[26];

    // Fill the frequency array with the words2
    for (int i = 0; i < words2.length; i++) {
      // Get the current string
      String word = words2[i];

      // Initialize the character array for the frequency of the word
      int[] temp = new int[26];

      // Iterate over the word to fill the frequency array
      for (int j = 0; j < word.length(); j++) {
        temp[word.charAt(j) - 'a']++;
      }

      // Get the max frequency of each character
      for (int j = 0; j < 26; j++) {
        frequency[j] = Math.max(frequency[j], temp[j]);
      }
    }

    // Initialize the result list
    ArrayList<String> result = new ArrayList<>();

    // Iterate over the words1 to check if it is supre set of words2
    for (int i = 0; i < words1.length; i++) {
      // Get the current string
      String word = words1[i];

      // Initialize the character array for the current word frequency
      int[] currentFrequency = new int[26];

      // Iterate over the word to fill the frequency array
      for (int j = 0; j < word.length(); j++) {
        currentFrequency[word.charAt(j) - 'a']++;
      }

      // Initailize the boolean variable for the checking if the word is valid or not
      boolean isValidWord = true;

      // Iterate over the current frequecy to check if it has all the character of the
      // frequecy
      for (int j = 0; j < 26; j++) {
        // If frequency[i] is greater than the currentFrequency then skip the iteration
        if (currentFrequency[j] < frequency[j]) {
          isValidWord = false;
          break;
        }
      }

      // Add the current word to the result array if current word is valid
      if (isValidWord) {
        result.add(word);
      }
    }

    // Return the result in the end
    return result;
  }
}

public class _916_Word_Subsets {
  // Main method to test zeroFilledSubarray
  public static void main(String[] args) {
    String[] words1 = new String[] { "amazon", "apple", "facebook", "google", "leetcode" };
    String[] words2 = new String[] { "e", "o" };

    List<String> result = new Solution().wordSubsets(words1, words2);

    System.out.println("The Strings which contains the strings are : " + result);
  }
}
