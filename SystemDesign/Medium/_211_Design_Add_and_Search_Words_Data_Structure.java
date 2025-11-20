/*
LeetCode Problem: https://leetcode.com/problems/design-add-and-search-words-data-structure/

Question: 211. Design Add and Search Words Data Structure

Problem Statement: Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

Example:
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 10^4 calls will be made to addWord and search.
 */

/*
Approach:
Approach:
1. We are given a list of words to store and a search function that must support the
   wildcard character '.' which can match any letter. This requires a Trie-based solution.

2. A Trie node contains:
      • An array of size 26 (one for each lowercase letter).
      • A boolean flag `isEnd` that marks the end of a complete word.

3. To add a word:
      • Convert the word into a character array.
      • Traverse the Trie one character at a time.
      • For every character, move to its child Trie node. If the child does not exist,
        create a new Trie node.
      • After inserting all characters, mark the last node as a word-ending node.

4. To search a word (supports '.'):
      • Use DFS to traverse all possible paths.
      • For normal characters:
            – Move only to the matching child.
            – If child does not exist → return false.
      • For '.' character:
            – Explore all 26 children because '.' can match any character.
            – If any recursive call returns true → word exists.
      • If index reaches the end of the word, return true only if `isEnd` is true.

5. The DFS search ensures that wildcard handling remains efficient and avoids generating
   all possible permutations.

Time Complexity:
      • addWord: O(L), where L is the length of the word.
      • search:
            – Worst case: O(26^d * L), where d is the number of '.' characters.
            – Average case: O(L)

Space Complexity: O(26 * total Trie nodes), depending on total words inserted.

*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.List;

public class _211_Design_Add_and_Search_Words_Data_Structure {
    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */

    // Class to make the WordDictionary
    private static class WordDictionary {
        // Initialize the array for holding path for all the letters
        private final WordDictionary[] letter;

        // Initialize the boolean vairable for marking the word end
        private boolean isEnd;

        public WordDictionary() {
            // Initialize the letter for all alphabets that is size of 26
            this.letter = new WordDictionary[26];

            // Set the isEnd variable to false
            this.isEnd = false;
        }

        public void addWord(String word) {
            // Convert the string into the character array
            char[] characters = word.toCharArray();

            // Get the length of the characters
            int length = characters.length;

            // Get the index for the character
            int index = characters[0] - 'a';

            // Get the first letter of the character and get it form the letters array
            WordDictionary alphabet = this.letter[index];

            // If alphabet is null then add the initialize a new WordDictionary for the
            // alphabet
            if (alphabet == null) {
                alphabet = this.letter[index] = new WordDictionary();
            }

            // Iterate over the characters to insert the new characters at a time
            for (int i = 1; i < length; i++) {
                // If character is null then add a new WordDictionary else move forward in the
                // WordDictionary
                if (alphabet.letter[characters[i] - 'a'] == null) {
                    alphabet = alphabet.letter[characters[i] - 'a'] = new WordDictionary();
                } else {
                    alphabet = alphabet.letter[characters[i] - 'a'];
                }
            }

            // Set the alphabet isEnd variable to true
            alphabet.isEnd = true;
        }

        public boolean search(String word) {
            // Convert the string into character array
            char[] characters = word.toCharArray();

            // Call the DFS helper to find if the word exists in the dictionary
            return dfsSearch(0, characters, this);
        }

        // Helper DFS method to handle normal characters and '.'
        private boolean dfsSearch(int index, char[] characters, WordDictionary node) {
            // If node is null then return false
            if (node == null) {
                return false;
            }

            // Base case if index reaches the end of the word
            if (index == characters.length) {
                return node.isEnd;
            }

            // Get the current character
            char ch = characters[index];

            // If character is normal alphabet then move to its child
            if (ch != '.') {
                // Get the next child node
                WordDictionary nextNode = node.letter[ch - 'a'];

                // Continue DFS
                return dfsSearch(index + 1, characters, nextNode);
            }

            // If character is '.', then explore all possible children
            for (int i = 0; i < 26; i++) {
                // Check all possible paths
                if (dfsSearch(index + 1, characters, node.letter[i])) {
                    return true;
                }
            }

            // If no match found
            return false;
        }
    }

    // Main method to test WordDictionary
    public static void main(String[] args) {

        String[] operations = {
                "WordDictionary", "addWord", "addWord", "addWord", "search", "search", "search", "search"
        };

        List<String[]> values = new ArrayList<>();
        values.add(new String[] {});
        values.add(new String[] { "bad" });
        values.add(new String[] { "dad" });
        values.add(new String[] { "mad" });
        values.add(new String[] { "pad" });
        values.add(new String[] { "bad" });
        values.add(new String[] { ".ad" });
        values.add(new String[] { "b.." });

        // Create an instance of WordDictionary
        WordDictionary wordDictionary = new WordDictionary();

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("WordDictionary")) {
                wordDictionary = new WordDictionary();
                System.out.println("null");
            }
            if (operation.equals("addWord")) {
                wordDictionary.addWord(values.get(i)[0]);
                System.out.println("null");
            }
            if (operation.equals("search")) {
                System.out.println(wordDictionary.search(values.get(i)[0]));
            }
        }
    }
}
