/*
LeetCode Problem: https://leetcode.com/problems/extra-characters-in-a-string/

Question: 2707. Extra Characters in a String

Problem Statement: You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.

Return the minimum number of extra characters left over if you break up s optimally.

Example 1:
Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
Output: 1
Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

Example 2:
Input: s = "sayhelloworld", dictionary = ["hello","world"]
Output: 3
Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.

Constraints:
    1 <= s.length <= 50
    1 <= dictionary.length <= 50
    1 <= dictionary[i].length <= 50
    dictionary[i] and s consists of only lowercase English letters
    dictionary contains distinct words
*/

/*
Approach: Trie-accelerated Top-down DP with Memoization
Goal:
- Partition string s using words from dictionary to
  minimize the number of characters left unmatched.
Core Idea:
- At each index, two choices exist: skip the
  current character (costs 1 extra character) or
  match a dictionary word starting at this index
  (costs 0 extra characters for matched portion).
- A Trie enables efficient multi-length prefix
  matching from any index in a single left-to-right
  scan, avoiding redundant substring lookups.
- Memoize results at each index to avoid
  recomputing overlapping subproblems.
Algorithm Steps:
1. Build a Trie from the dictionary.
2. Initialize dp[0..length] = -1 (uncomputed).
3. Call dfs(0, s, trie, dp).
4. In dfs(index, s, trie, dp):
   a. If index == s.length(), return 0 (no
      remaining characters).
   b. If dp[index] != -1, return the cached result.
   c. Initialize result = 1 + dfs(index + 1, ...)
      (skip current character, paying cost 1).
   d. Traverse the Trie from root starting at
      index:
      - For each j from index to s.length() - 1:
        - If the child for s.charAt(j) is null,
          no further matches possible, break.
        - Advance to the child node.
        - If current.isWord is true, a dictionary
          word ends at j; update result =
          Math.min(result, dfs(j + 1, ...)).
   e. Cache dp[index] = result and return.
5. Return the result from the initial call.
Why It Works:
- Skipping produces an upper bound; matching a
  dictionary word from index to j skips j - index
  + 1 characters at zero cost, potentially
  reducing extra characters significantly.
- The Trie collapses all dictionary words into a
  shared prefix structure, so all words starting
  with the same prefix are explored in one pass
  without separate substring comparisons.
- Memoization ensures each index is computed once,
  since the result at any index depends only on the
  remaining suffix, not on how the current index
  was reached.
Time Complexity:
- O(n^2 + m * k)
where n is the length of s (n^2 for all index
pairs explored via DFS), m is the number of
dictionary words, and k is the average word
length for Trie construction.
Space Complexity:
- O(m * k)
for the Trie, plus O(n) for the dp array and
recursive call stack.
Result:
- Returns the minimum number of extra (unmatched)
  characters remaining after optimally partitioning
  s using dictionary words.
*/

package Trees.Medium;

// Solution Class
class Solution {
  // Method to find the minimum number of extra characters left over if you break
  // up s optimally
  public int minExtraChar(String s, String[] dictionary) {
    // Initialize the Trie of dictionary array
    Trie trie = new Trie(dictionary);

    // Initialize the length of s
    int length = s.length();

    // Initialize the dp array
    int[] dp = new int[length + 1];

    // Fill the dp array with -1
    for (int i = 0; i < dp.length; i++) {
      dp[i] = -1;
    }

    // Return the dfs call
    return this.dfs(0, s, trie, dp);
  }

  // Helper method to preform the dfs
  private int dfs(int index, String s, Trie trie, int[] dp) {
    // If index is equal to s.length then return 0
    if (index == s.length()) {
      return 0;
    }

    // If we have cache in dp then return that
    if (dp[index] != -1) {
      return dp[index];
    }

    // Skip the current character
    int result = 1 + dfs(index + 1, s, trie, dp);

    // Get the current TrieNode
    TrieNode current = trie.root;

    // Iterate over the string
    for (int j = index; j < s.length(); j++) {
      // If it has current index is null then break out of the loop
      if (current.children[s.charAt(j) - 'a'] == null) {
        break;
      }

      // Update the current TrieNode
      current = current.children[s.charAt(j) - 'a'];

      // If current.isWord is true then update the result
      if (current.isWord) {
        {
          result = Math.min(result, dfs(j + 1, s, trie, dp));
        }
      }
    }

    // Update the dp cache
    dp[index] = result;

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
      }

      // Mark the word as true
      current.isWord = true;
    }
  }

  // TrieNode Class
  private class TrieNode {
    // Initialize the array of TrieNodes
    private final TrieNode[] children;
    private boolean isWord;

    // Initialize the constructor for the adding the words
    public TrieNode() {
      // Initialize the children array
      this.children = new TrieNode[26];
      this.isWord = false;
    }
  }
}

// Main Class
public class _2707_Extra_Characters_in_a_String {
  // Main method to test minExtraChar
  public static void main(String[] args) {
    String s = "";
    String[] dictionary = new String[] { "/a", "/a/b", "/c/d", "/c/d/e", "/c/f" };

    int result = new Solution().minExtraChar(s, dictionary);

    System.out.println("The minimum number of extra characters left over if you break up s optimally is : " + result);
  }
}
