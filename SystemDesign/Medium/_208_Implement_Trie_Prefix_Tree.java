/*
LeetCode Problem: https://leetcode.com/problems/implement-trie-prefix-tree/

Question: 208. Implement Trie (Prefix Tree)

Problem Statement: A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

Example 1:
Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True

Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */

/*
Approach:
1. We are given three operations to implement using a Trie:
   • insert(word)
   • search(word)
   • startsWith(prefix)

2. To solve this, we use a Trie node that contains:
   • An array of size 26 to store the next possible characters ('a' to 'z').
   • A boolean flag `isEnd` that marks the end of a complete word.

3. Insert Operation:
   • Convert the input word into a character array.
   • For every character, compute its index as (ch - 'a').
   • If the next path for that character is null, create a new Trie node.
   • Move to that node and continue until all characters are processed.
   • Mark the last node as `isEnd = true`.

4. Search Operation:
   • Traverse the Trie according to each character in the input word.
   • If at any point the required child node is null, return false.
   • After traversal, return the value of `isEnd`, since search requires a full word.

5. startsWith Operation:
   • Traverse the Trie using every character of the prefix.
   • If at any point the required child node is null, return false.
   • If traversal completes successfully, return true because any prefix path is valid.

6. This ensures insertion, full-word search, and prefix search can all be done efficiently.

Time Complexity: O(L) for each operation, where L is the length of the input word.
Space Complexity: O(26 * total_nodes), depending on how many nodes get created.
*/

package SystemDesign.Medium;

import java.util.ArrayList;
import java.util.List;

public class _208_Implement_Trie_Prefix_Tree {
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    // Class to make the Trie
    private static class Trie {
        // Initialize the array for holding path for all the letters
        private final Trie[] letter;

        // Initialize the boolean vairable for marking the word end
        private boolean isEnd;

        public Trie() {
            // Initialize the letter for all alphabets that is size of 26
            this.letter = new Trie[26];

            // Set the isEnd variable to false
            this.isEnd = false;
        }

        public void insert(String word) {
            // Convert the string into the character array
            char[] characters = word.toCharArray();

            // Get the length of the characters
            int length = characters.length;

            // Get the index for the character
            int index = characters[0] - 'a';

            // Get the first letter of the character and get it form the letters array
            Trie alphabet = this.letter[index];

            // If alphabet is null then add the initialize a new Trie for the alphabet
            if (alphabet == null) {
                alphabet = this.letter[index] = new Trie();
            }

            // Iterate over the characters to insert the new characters at a time
            for (int i = 1; i < length; i++) {
                // If character is null then add a new Trie else move forward in the Trie
                if (alphabet.letter[characters[i] - 'a'] == null) {
                    alphabet = alphabet.letter[characters[i] - 'a'] = new Trie();
                } else {
                    alphabet = alphabet.letter[characters[i] - 'a'];
                }
            }

            // Set the alphabet isEnd variable to true
            alphabet.isEnd = true;
        }

        public boolean search(String word) {
            // Convert the string into the character array
            char[] characters = word.toCharArray();

            // Get the length of the characters
            int length = characters.length;

            // Get the index for the character
            int index = characters[0] - 'a';

            // Get the first letter of the character and get it form the letters array
            Trie alphabet = this.letter[index];

            // If alphabet is null then return false
            if (alphabet == null) {
                return false;
            }

            // Iterate over the characters to insert the new characters at a time
            for (int i = 1; i < length; i++) {
                // If character is null then return false else move forward in the Trie
                if (alphabet.letter[characters[i] - 'a'] == null) {
                    return false;
                } else {
                    alphabet = alphabet.letter[characters[i] - 'a'];
                }
            }

            // Return the alphabet isEnd variable at the end
            return alphabet.isEnd;
        }

        public boolean startsWith(String prefix) {
            // Convert the string into the character array
            char[] characters = prefix.toCharArray();

            // Get the length of the characters
            int length = characters.length;

            // Get the index for the character
            int index = characters[0] - 'a';

            // Get the first letter of the character and get it form the letters array
            Trie alphabet = this.letter[index];

            // If alphabet is null then return false
            if (alphabet == null) {
                return false;
            }

            // Iterate over the characters to insert the new characters at a time
            for (int i = 1; i < length; i++) {
                // If character is null then return false else move forward in the Trie
                if (alphabet.letter[characters[i] - 'a'] == null) {
                    return false;
                } else {
                    alphabet = alphabet.letter[characters[i] - 'a'];
                }
            }

            // Retrun true if we reach to the end succesfully
            return true;
        }
    }

    // Main method to test Trie
    public static void main(String[] args) {

        String[] operations = {
                "Trie", "insert", "search", "search", "startsWith", "insert", "search"
        };

        List<String[]> values = new ArrayList<>();
        values.add(new String[] {});
        values.add(new String[] { "apple" });
        values.add(new String[] { "apple" });
        values.add(new String[] { "app" });
        values.add(new String[] { "app" });
        values.add(new String[] { "app" });
        values.add(new String[] { "app" });

        // Create an instance of Trie
        Trie trie = new Trie();

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("Trie")) {
                trie = new Trie();
                System.out.println("null");
            }
            if (operation.equals("insert")) {
                trie.insert(values.get(i)[0]);
                System.out.println("null");
            }
            if (operation.equals("search")) {
                System.out.println(trie.search(values.get(i)[0]));
            }
            if (operation.equals("startsWith")) {
                System.out.println(trie.startsWith(values.get(i)[0]));
            }
        }
    }
}
