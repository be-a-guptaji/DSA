/*
LeetCode Problem: https://leetcode.com/problems/word-search-ii/

Question: 212. Word Search II

Problem Statement: Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 10^4
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
 */

/*
Approach:
1. This problem is a great use-case for **Trie (Prefix Tree)** combined with **Depth-First Search (DFS)**:
   - The board is a 2D grid of characters.
   - The goal is to find all words from the given list that can be constructed by navigating adjacent cells (up/down/left/right).
   - Each cell can be used only once per word.
   - A naive solution would check every word against every board path, which is inefficient.
   - Using a **Trie** allows us to stop early during DFS when no word starts with the current prefix.
2. Trie Construction (Preprocessing Step):
   - We build a Trie (prefix tree) from the list of words.
   - Each node in the Trie represents a character.
   - Each complete path from the root to a node marked `isEndOfWord = true` represents a complete word from the list.
   - This structure helps in pruning paths early during DFS traversal — we only explore further if the prefix exists in the Trie.
3. DFS Search on the Board:
   - For each cell `(i, j)` on the board:
     - We check if the character at `(i, j)` is the starting letter of any word in the Trie.
     - If yes, we start a DFS from that cell.
   - During DFS:
     - We explore in four directions (up, down, left, right).
     - We track visited cells using a `boolean[][] visited` array to prevent revisiting a cell in the same path.
     - At each step, we append the current character to a string (`str`) and move to the corresponding child node in the Trie.
     - If the current Trie node is marked as `isEndOfWord`, we add the current string to the result set.
4. Backtracking:
   - After exploring all paths from a cell, we backtrack by marking it as unvisited.
   - This allows the cell to be reused in other paths starting from different locations.
5. Result Collection:
   - We use a `Set<String>` to collect valid words found during DFS traversal to avoid duplicates.
   - After completing DFS for the whole board, we iterate through the original word list and check which of them are in the result set.
   - This ensures we return only those words from the input list, in the required format.

Time Complexity: O(N * L + M * 4^L)
- N = number of words in the word list
- L = maximum length of a word
- M = total number of cells in the board
- Building the Trie takes O(N * L)
- DFS from each cell could take up to O(4^L) in the worst case (exponential with word length),
  but is pruned significantly due to the Trie structure
Space Complexity: O(N * L + M)
- O(N * L) for storing the Trie
- O(M) for the visited matrix (`boolean[][] visited`)
- Additional O(L) stack space used by the recursion for each DFS path
*/

package Graphs.Hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _212_Word_Search_II {
   // Method to find which word is contain by the board
   public static List<String> findWords(char[][] board, String[] words) {
      // Initialize the rows and columns for the board
      int rows = board.length, columns = board[0].length;

      // Initialize the sets for visited and words
      Set<String> word = new HashSet<>();

      // Make the trie Data Structure for the word list
      Trie prefixTree = buildPrefixTree(words);

      // Initialize a 2D array for visited tracking
      boolean[][] visited = new boolean[rows][columns];

      // Make a DFS call for the words to find
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            // Find the offset of the index
            int idx = board[i][j] - 'a';

            // Make the new node to pass on
            Trie node = prefixTree.nextNode[idx];

            if (node != null) {
               dfs(i, j, rows, columns, board, node, word, visited, "" + board[i][j]);
            }
         }
      }

      // Initialize a list of string for returning the array
      List<String> wordsFound = new ArrayList<>();

      // Filter out all the words which are found in the word set
      for (String w : words) {
         // If the w is in the word set then add it to the list of word found
         if (word.contains(w)) {
            wordsFound.add(w);
         }
      }

      // Return the word found
      return wordsFound;
   }

   // Helper method to travel the word DFS
   private static void dfs(int i, int j, int rows, int columns, char[][] board, Trie root, Set<String> word,
         boolean[][] visited, String str) {
      // Edge Cases
      if (i < 0 || j < 0 || i >= rows || j >= columns || visited[i][j]) {
         return;
      }

      // Add the cell to the visited set
      visited[i][j] = true;

      // If the word is end of word add it to the word set
      if (root.isEndOfWord) {
         word.add(str);
      }

      // Make the recursion in all directions (Up, Right, Left, Down)
      int[] dx = { -1, 0, 0, 1 };
      int[] dy = { 0, 1, -1, 0 };

      for (int d = 0; d < 4; d++) {
         int newRow = i + dx[d];
         int newCol = j + dy[d];

         // Check the edge cases
         if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns && !visited[newRow][newCol]) {
            // Get the character form the board
            char nextChar = board[newRow][newCol];
            
            // Get the next node form the prefix tree
            Trie nextNode = root.nextNode[nextChar - 'a'];

            // If next node is null then do not do further recursion
            if (nextNode != null) {
               dfs(newRow, newCol, rows, columns, board, nextNode, word, visited, str + nextChar);
            }
         }
      }

      // Remove the cell from the visited set for backtracking
      visited[i][j] = false;
   }

   // Helper class for the trie Data Structure
   private static class Trie {
      char value; // Variable to store the current value
      Trie[] nextNode; // Variable to store the next word node
      boolean isEndOfWord; // Variable to store the boolean if word is ended

      public Trie(char val, boolean isEndOfWord) {
         this.value = val;
         this.nextNode = new Trie[26];
         this.isEndOfWord = isEndOfWord;
      }
   }

   // Helper method to build the prefix tree
   private static Trie buildPrefixTree(String[] words) {
      // Initialize new starting node for the trie data structure
      Trie root = new Trie('0', false);

      // Iterate over all words
      for (String word : words) {
         // Make a dummy node for the traversing
         Trie node = root;

         // Traverse the whole word
         for (int i = 0; i < word.length(); i++) {
            // Get the character of the word
            char ch = word.charAt(i);

            // Find the offset of the index
            int idx = ch - 'a';

            // If the node is null then build the new node
            if (node.nextNode[idx] == null) {
               node.nextNode[idx] = new Trie(ch, false);
            }

            // Move the node to the next node
            node = node.nextNode[idx];

            // Always set this at the last char
            if (i == word.length() - 1) {
               node.isEndOfWord = true;
            }
         }
      }

      // Return the root
      return root;
   }

   // Main method to test findWords
   public static void main(String[] args) {
      char[][] board = {
            { 'o', 'a', 'a', 'n' },
            { 'e', 't', 'a', 'e' },
            { 'i', 'h', 'k', 'r' },
            { 'i', 'f', 'l', 'v' }
      };
      String[] words = { "oath", "pea", "eat", "rain" };

      List<String> result = findWords(board, words);

      System.out.println("The board contains the word : " + result);
   }
}