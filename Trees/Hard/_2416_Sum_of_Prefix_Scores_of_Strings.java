/*
LeetCode Problem: https://leetcode.com/problems/sum-of-prefix-scores-of-strings/

Question: 2416. Sum of Prefix Scores of Strings

Problem Statement: You are given an array words of size n consisting of non-empty strings.

We define the score of a string term as the number of strings words[i] such that term is a prefix of words[i].

    For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".

Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].

Note that a string is considered as a prefix of itself.

Example 1:
Input: words = ["abc","ab","bc","b"]
Output: [5,4,3,2]
Explanation: The answer for each string is the following:
- "abc" has 3 prefixes: "a", "ab", and "abc".
- There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
The total is answer[0] = 2 + 2 + 1 = 5.
- "ab" has 2 prefixes: "a" and "ab".
- There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
The total is answer[1] = 2 + 2 = 4.
- "bc" has 2 prefixes: "b" and "bc".
- There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
The total is answer[2] = 2 + 1 = 3.
- "b" has 1 prefix: "b".
- There are 2 strings with the prefix "b".
The total is answer[3] = 2.

Example 2:
Input: words = ["abcd"]
Output: [4]
Explanation:
"abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.

Constraints:
    1 <= words.length <= 1000
    1 <= words[i].length <= 1000
    words[i] consists of lowercase English letters.
*/

/*
Approach: Trie with Prefix Frequency Counting
Goal:
- For each word, compute the sum of scores of all
  its non-empty prefixes, where a prefix's score
  is the number of words in the array that contain
  that prefix.
- Return these sums as a result array.
Core Idea:
- Insert all words into a Trie, incrementing a
  counter at each node as words pass through it.
- Each node's counter represents exactly how many
  words share the prefix ending at that node.
- For any word, its prefix score sum is the
  accumulated node counters along its path in the
  Trie from root to its last character.
Algorithm Steps:
1. Build a Trie by inserting all words:
   - For each character in a word, create a child
     node if absent, move to it, and increment
     its totalWords counter.
   - totalWords at any node equals the number of
     inserted words that pass through that node,
     i.e., the number of words sharing the prefix
     up to that node.
2. For each word, compute its score via getScore:
   - Traverse the Trie along the word's characters.
   - Accumulate totalWords at each visited node.
   - The sum is the word's prefix score sum.
3. Store each word's score in result[i] and return.
Why It Works:
- Incrementing totalWords at every node on
  insertion directly encodes prefix frequency
  without a separate counting pass.
- Summing totalWords along a word's Trie path is
  equivalent to summing, for each prefix of the
  word, the number of words that contain that
  prefix, which matches the problem definition.
Time Complexity:
- O(m * k)
where m is the number of words and k is the average
word length, for both Trie construction and score
computation (each word traverses at most k nodes
twice: once during insert, once during scoring).
Space Complexity:
- O(m * k)
for the Trie nodes in the worst case where no two
words share any prefix.
Result:
- Returns an array where result[i] is the sum of
  prefix scores of words[i] across all words.
*/

package Trees.Hard;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find an array answer of size n where answer[i] is the sum of scores
  // of every non-empty prefix of words[i]
  public int[] sumPrefixScores(String[] words) {
    // Initialize the Trie node
    Trie trie = new Trie(words);

    // Initilaize the array of words length
    int[] result = new int[words.length];

    // Iterate over the words
    for (int i = 0; i < words.length; i++) {
      // Update the result variable
      result[i] = trie.getScore(words[i]);
    }

    // Return the result
    return result;
  }

  // Trie Class
  private class Trie {
    // Initialize the root node
    private final TrieNode root;

    // Initialize the constructor for the adding the words
    public Trie(String[] words) {
      // Initialize the TrieNode
      this.root = new TrieNode();

      // Iterate over the words
      for (String word : words) {
        // Add word to the Trie
        this.addWord(word);
      }
    }

    // Helper method to add the word
    private void addWord(String word) {
      // Initialize the current TrieNode
      TrieNode current = this.root;

      // Iterate over the words array
      for (char c : word.toCharArray()) {
        // If node is null then add the TrieNode to it
        if (current.children[c - 'a'] == null) {
          current.children[c - 'a'] = new TrieNode();
        }

        // Update the current TrieNode
        current = current.children[c - 'a'];

        // Increment the word count
        current.totalWords++;
      }
    }

    // Helper method to get the words
    private int getScore(String word) {
      // Initialize the current TrieNode
      TrieNode current = this.root;

      // Initialize the score varaible
      int score = 0;

      // Iterate over the words array
      for (char c : word.toCharArray()) {
        // Update the current TrieNode
        current = current.children[c - 'a'];

        // Update the score variable
        score += current.totalWords;
      }

      // Retrun the score
      return score;
    }
  }

  // TrieNode Class
  private class TrieNode {
    // Initialize the array of TrieNodes
    private final TrieNode[] children;
    private int totalWords;

    // Initialize the constructor for the adding the words
    public TrieNode() {
      // Initialize the children array
      this.children = new TrieNode[26];
      this.totalWords = 0;
    }
  }
}

// Main Class
public class _2416_Sum_of_Prefix_Scores_of_Strings {
  // Main method to test sumPrefixScores
  public static void main(String[] args) {
    String[] words = new String[] { "abc", "ab", "bc", "b" };

    int[] result = new Solution().sumPrefixScores(words);

    System.out.println(
        "An array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i] is : "
            + Arrays.toString(result));
  }
}
