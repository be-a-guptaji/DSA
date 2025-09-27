/*
LeetCode Problem: https://leetcode.com/problems/word-ladder/

Question: 127. Word Ladder

Problem Statement: A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */

/*
Approach:
1. This problem is a classic use-case for **Breadth-First Search (BFS)**:
   - The goal is to find the **shortest transformation sequence** from `beginWord` to `endWord`.
   - Each valid transformation changes only **one letter** and must be a word in the given `wordList`.
2. Preprocessing - Build a "wildcard pattern" map:
   - For each word in the `wordList` (and `beginWord`), generate patterns like `h*t`, `ho*`, `*ot`, etc.
   - Each pattern maps to all words in the list that match it.
   - This allows O(1)-like access to potential neighbors in the word graph.
3. Use BFS for shortest path:
   - Start BFS from `beginWord`.
   - For each word dequeued, generate its wildcard patterns.
   - For each pattern, fetch all adjacent words (neighbors) from the `patternMap`.
   - If a neighbor matches the `endWord`, return the current transformation length.
   - Otherwise, add the neighbor to the queue and mark it as visited (by removing it from `wordSet`).
4. Visiting words:
   - To avoid cycles and redundant processing, we use a `HashSet` (`wordSet`) to track unvisited words.
   - Once a word is visited, it's removed from the set.
5. Termination:
   - BFS continues level-by-level (i.e., transformation-by-transformation).
   - The transformation length is incremented at each BFS level.
   - If `endWord` is never reached, return `0` (no transformation possible).

Time Complexity: O(N * L^2),  
- N = number of words in `wordList`  
- L = length of each word  
- We build patterns for each word (L patterns per word), and each pattern lookup may return multiple words.
Space Complexity: O(N * L),  
- For the `patternMap` storing multiple patterns per word  
- Plus O(N) space for the BFS queue and visited set
*/

package Graphs.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _127_Word_Ladder {
   // Method to determine the transformation length of the word
   public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
      // Convert wordList to a HashSet for faster lookup
      Set<String> wordSet = new HashSet<>(wordList);

      // Check if the endWord is in the word set
      if (!wordSet.contains(endWord)) {
         return 0;
      }

      // Add the begin word to the word list
      wordList.add(beginWord);

      // Create a Map reated to the pattern to the matching word
      HashMap<String, ArrayList<String>> patternMap = new HashMap<>();

      // Get the word list length
      int wordListLength = wordList.size();

      // Make the pattern and add to the map
      for (int i = 0; i < wordListLength; i++) {
         // Get the word form the list
         String word = wordList.get(i);

         // Get the word length
         int wordLength = word.length();

         // Make every pattern from the word and add it to the map
         for (int idx = 0; idx < wordLength; idx++) {
            // Make the pattern
            String pattern = word.substring(0, idx) + '*' + word.substring(idx + 1);

            // Add pattern to the pattern map
            patternMap.computeIfAbsent(pattern, _ -> new ArrayList<>()).add(word);
         }
      }

      // Initialize the transformation length to 1
      int transformationLength = 1;

      // Initialize a queue for the BFS Traversal
      Queue<String> queue = new LinkedList<>();

      // Add begin word to the queue
      queue.offer(beginWord);

      // Make the BFS Traversal for finding the transformation length
      while (!queue.isEmpty()) {
         // Get the size of the queue
         int size = queue.size();

         // Make every pattern from the word and add it to the map
         for (int i = 0; i < size; i++) {
            // Get the word from the queue
            String word = queue.poll();

            // If the word is equal to the end word then return the transformation length
            if (word.equals(endWord)) {
               return transformationLength;
            }

            // Get the word length
            int wordLength = word.length();

            // Make the BFS Traverse for the every pattern of the word
            for (int idx = 0; idx < wordLength; idx++) {
               // Make the pattern
               String pattern = word.substring(0, idx) + '*' + word.substring(idx + 1);

               // Get the pattern list form the pattern map
               ArrayList<String> list = patternMap.get(pattern);

               // Get the list size
               int listSize = list.size();

               // Traverse all the neighbour of the pattern
               for (int j = 0; j < listSize; j++) {
                  // Get the neighbour
                  String neighbour = list.get(j);

                  // If the word is already visited then skip the word
                  if (wordSet.contains(neighbour)) {
                     // Remove the neighbour form the word set
                     wordSet.remove(neighbour);

                     // Add the neighbour to the queue
                     queue.offer(neighbour);
                  }
               }
            }
         }

         // Increment the transformation length by one
         transformationLength++;
      }

      // It should never reach to this but in case it happen then return 0
      return 0;
   }

   // Main method to test ladderLength
   public static void main(String[] args) {
      String beginWord = "hit";
      String endWord = "cog";
      List<String> wordList = new ArrayList<>();

      String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };

      wordList.addAll(Arrays.asList(words));

      int result = ladderLength(beginWord, endWord, wordList);

      System.out.println("The transformation length of " + beginWord + " to " + endWord + " is : " + result);
   }
}