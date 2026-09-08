/*
LeetCode Problem: https://leetcode.com/problems/verifying-an-alien-dictionary/

Question: 953. Verifying an Alien Dictionary

Problem Statement: In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/

/*
Approach: Custom Alphabet Mapping with Pairwise Word Comparison
Goal:
- Determine if the given list of words is sorted
  lexicographically according to the alien alphabet
  order.
Core Idea:
- Map each character to its rank in the alien
  alphabet via a lookup array, then compare
  adjacent word pairs character by character using
  these ranks instead of standard ASCII values.
- For each adjacent pair, the first character
  mismatch determines the order; if no mismatch
  exists and w1 is longer than w2, w1 cannot come
  before w2 (invalid order).
Algorithm Steps:
1. Build lexicographicalOrder[26] where
   lexicographicalOrder[c - 'a'] = i for the
   character at position i in order.
2. For each adjacent pair (w1, w2):
   a. For each character index j in w1:
      - If j == w2.length(), w1 is longer and
        shares a prefix with w2, return false.
      - If w1.charAt(j) != w2.charAt(j):
        - If rank(w1[j]) > rank(w2[j]), order is
          violated, return false.
        - Otherwise the pair is correctly ordered,
          break to the next pair.
3. Return true if all adjacent pairs pass.
Why It Works:
- The first differing character between two words
  fully determines their relative order; subsequent
  characters are irrelevant once a mismatch is
  found.
- The prefix exhaustion check (j == w2.length())
  correctly handles the case where w1 = "abc" and
  w2 = "ab", which is invalid regardless of the
  alien alphabet.
Time Complexity:
- O(n * m)
where n is the number of words and m is the average
word length, since each adjacent pair is compared
character by character.
Space Complexity:
- O(1)
excluding the fixed-size 26-element lookup array.
Result:
- Returns true if words are sorted in the alien
  lexicographic order, false otherwise.
*/

package Graphs.Easy;

// Solution Class
class Solution {
  // Method to find if the given words are sorted lexicographically in this alien
  // language
  public boolean isAlienSorted(String[] words, String order) {
    // Initialize the lexicographicalOrder for the order of letter
    int[] lexicographicalOrder = new int[26];

    // Fill the lexicographicalOrder from the order
    for (int i = 0; i < 26; i++) {
      lexicographicalOrder[order.charAt(i) - 'a'] = i;
    }

    // Iterate over the words array
    for (int i = 0; i < words.length - 1; i++) {
      // Initialize the words
      String w1 = words[i];
      String w2 = words[i + 1];

      // Initialize the length
      int length = w2.length();

      // Iterate over the words
      for (int j = 0; j < w1.length(); j++) {
        // If w2 is consumed then return false
        if (length == j) {
          return false;
        }

        // Initialize the character
        int a = w1.charAt(j) - 'a';
        int b = w2.charAt(j) - 'a';

        // If character are unmatched then
        if (a != b) {
          // If lexicographicalOrder is not in order then return false
          if (lexicographicalOrder[a] > lexicographicalOrder[b]) {
            return false;
          }

          break;
        }
      }
    }

    // Return the true in the end
    return true;
  }
}

public class _953_Verifying_an_Alien_Dictionary {
  // Main method to test isAlienSorted
  public static void main(String[] args) {
    String[] words = new String[] { "hello", "leetcode" };
    String order = "hlabcdefgijkmnopqrstuvwxyz";

    boolean result = new Solution().isAlienSorted(words, order);

    System.out
        .println("The given words are" + (result ? " " : " not ") + "sorted lexicographically in this alien language.");
  }
}
