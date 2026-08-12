/*
LeetCode Problem: https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii/

Question: 3045. Count Prefix and Suffix Pairs II

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
    1 <= words.length <= 105^
    1 <= words[i].length <= 105^
    words[i] consists only of lowercase English letters.
    The sum of the lengths of all words[i] does not exceed 5 * 105^.
*/

/*
Approach: Reverse Iteration with Paired-Character Trie
Goal:
- Count all index pairs (i, j) where i < j and
  words[i] is both a prefix and suffix of words[j].
Core Idea:
- words[i] is a prefix and suffix of words[j] if
  and only if for every position k in words[i],
  words[j].charAt(k) == words[i].charAt(k) (prefix
  condition) and words[j].charAt(length_j - 1 - k)
  == words[i].charAt(length_i - 1 - k) (suffix
  condition) simultaneously.
- Encode both conditions into a single Trie by
  keying each level on a character pair
  (front_char, back_char) derived from the same
  position in the word scanned simultaneously from
  both ends.
- Iterating words in reverse order and counting
  before inserting ensures only words[j] with j > i
  are already in the Trie when words[i] is queried.
Algorithm Steps:
1. Initialize an empty Trie and result = 0.
2. Iterate i from words.length - 1 down to 0:
   a. Query root.count(words[i]): walk the Trie
      using paired keys (words[i].charAt(k),
      words[i].charAt(length - 1 - k)) for k from
      0 to length - 1. Return the count stored at
      the terminal node (number of words already
      inserted that words[i] is a prefix-suffix of).
   b. Add result of count to result.
   c. Insert words[i] into the Trie via root.add:
      walk or create nodes using the same paired
      keys, incrementing each node's count.
3. Return result.
Why It Works:
- The paired key (front, back) at level k encodes
  that a word matching up to level k must share
  both the k-th character from the front and the
  k-th character from the back with the query word.
- A word inserted into the Trie reaches a terminal
  node only if all its paired character positions
  are matched, meaning any query word that fully
  traverses the same path is both a prefix and
  suffix of the inserted word.
- Reverse iteration with count-before-insert
  guarantees pair validity (i < j) without
  post-filtering.
Time Complexity:
- O(n * m)
where n is the number of words and m is the average
word length, since each word is inserted and
queried once with O(m) steps each.
Space Complexity:
- O(n * m)
for the Trie nodes in the worst case where no two
words share any paired-character prefix path.
Result:
- Returns the count of valid (i, j) pairs where
  words[i] is both a prefix and suffix of words[j].
*/

package Trees.Hard;

import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find an integer denoting the number of index pairs (i, j) such that
  // i < j, and isPrefixAndSuffix(words[i], words[j]) is true
  public long countPrefixSuffixPairs(String[] words) {
    // Initialize the result variable
    int result = 0;

    // Initialize the Trie node
    Trie root = new Trie();

    // Iterate over the words array in reverse order
    for (int i = words.length - 1; i >= 0; i--) {
      // Update the result variable
      result += root.count(words[i]);

      // Add the word to the trie node
      root.add(words[i]);
    }

    // Return the result
    return result;
  }

  // Trie Class
  private class Trie {
    // Initialize the root TrieNode
    private final TrieNode root;

    // Constructor for Trie
    public Trie() {
      // Initialize the root TrieNode
      this.root = new TrieNode();
    }

    // Helper method to add the word in the TrieNode
    private void add(String word) {
      // Initialize the current node
      TrieNode current = root;

      // Get the length of the word
      int length = word.length();

      // Iterate over the word characters
      for (int i = 0; i < length; i++) {
        // Generate a key of the word
        String key = "" + word.charAt(i) + word.charAt(length - 1 - i);

        // Add the key to the children if needed
        current.children.putIfAbsent(key, new TrieNode());

        // Get the new node of the children
        current = current.children.get(key);

        // Increment the count variable
        current.count++;
      }
    }

    // Helper method to count the prefix and suffix
    private int count(String word) {
      // Initialize the current node
      TrieNode current = root;

      // Get the length of the word
      int length = word.length();

      // Iterate over the word characters
      for (int i = 0; i < length; i++) {
        // Generate a key of the word
        String key = "" + word.charAt(i) + word.charAt(length - 1 - i);

        // If key is not in children map then return 0
        if (!current.children.containsKey(key)) {
          return 0;
        }

        // Get the new node of the children
        current = current.children.get(key);
      }

      // Return the count of the current node
      return current.count;
    }
  }

  // TrieNode Class
  private class TrieNode {
    // Initialize the hash map for the children
    private final HashMap<String, TrieNode> children;

    // Initialize the count variable
    private int count;

    // Constructor for TrieNode
    public TrieNode() {
      // Initialize the children hash map
      this.children = new HashMap<>();

      // Initialize the count variable
      this.count = 0;
    }
  }
}

// Main Class
public class _3045_Count_Prefix_and_Suffix_Pairs_II {
  // Main method to test countPrefixSuffixPairs
  public static void main(String[] args) {
    String[] words = new String[] { "abc", "ab", "bc", "b" };

    long result = new Solution().countPrefixSuffixPairs(words);

    System.out.println(
        "An integer denoting the number of index pairs (i, j) such that i < j, and isPrefixAndSuffix(words[i], words[j]) is true is : "
            + result);
  }
}
