/*
LeetCode Problem: https://leetcode.com/problems/stream-of-characters/

Question: 1032. Stream of Characters

Problem Statement: Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.

For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.

Implement the StreamChecker class:

StreamChecker(String[] words) Initializes the object with the strings array words.
boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.

Example 1:
Input
["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
Output
[null, false, false, false, true, false, true, false, false, false, false, false, true]

Explanation
StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
streamChecker.query("a"); // return False
streamChecker.query("b"); // return False
streamChecker.query("c"); // return False
streamChecker.query("d"); // return True, because 'cd' is in the wordlist
streamChecker.query("e"); // return False
streamChecker.query("f"); // return True, because 'f' is in the wordlist
streamChecker.query("g"); // return False
streamChecker.query("h"); // return False
streamChecker.query("i"); // return False
streamChecker.query("j"); // return False
streamChecker.query("k"); // return False
streamChecker.query("l"); // return True, because 'kl' is in the wordlist

Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 200
words[i] consists of lowercase English letters.
letter is a lowercase English letter.
At most 4 * 10^4 calls will be made to query.
 */

/*
Approach:
1. We are given a list of words and a stream of incoming characters.
   After each character is queried, we must check if **any suffix**
   of the current stream forms a valid word from the given list.

2. To efficiently match suffixes, we use a **Trie (Prefix Tree)** but
   we store all words in **reversed order**.
   Example:
     Word = "cd"  → stored in trie as "dc"
     Word = "kl"  → stored in trie as "lk"

3. We maintain a **StringBuilder `stream`** that stores all queried
   characters in order.

4. For every new query character:
      • Append the character to `stream`
      • Start traversing the trie from the **latest character backward**
      • For each character in reverse:
           - If the path does not exist → stop and return false
           - If any node has `isEnd == true` → return true immediately

5. If we finish traversal and no word end is found, return false.

6. Why this works:
      • Reversed Trie lets us match **suffixes fast**
      • Stream allows us to keep track of all previous characters
      • Early stopping avoids unnecessary traversal

Time Complexity:
      • Worst case per query: O(L)
        where L = maximum word length

Space Complexity:
      • O(total characters in all words) for the Trie
      • O(total queries) for the stream
*/

package SystemDesign.Hard;

import java.util.ArrayList;
import java.util.List;

public class _1032_Stream_of_Characters {
    /**
     * Your StreamChecker object will be instantiated and called as such:
     * StreamChecker obj = new StreamChecker(words);
     * boolean param_1 = obj.query(letter);
     */

    // Class to make the StreamChecker
    private static class StreamChecker {
        // Initialize the trie root
        private final Trie root;

        // Initialize the StringBuilder to store the stream of characters
        private final StringBuilder stream;

        public StreamChecker(String[] words) {
            // Initialize the trie root
            this.root = new Trie();

            // Initialize the stream
            this.stream = new StringBuilder();

            // Add all the words in the trie in reversed order
            for (String word : words) {
                // Convert the word into the character array
                char[] str = word.toCharArray();

                // Get the trie root
                Trie base = this.root;

                // Add the word into the trie in the inverted (reverse) manner
                for (int i = str.length - 1; i >= 0; i--) {
                    // Get the character at index i
                    char ch = str[i];

                    // Get the relative index
                    int index = ch - 'a';

                    // If the next node is null then create a new Trie node
                    if (base.nextNode[index] == null) {
                        base.nextNode[index] = new Trie();
                    }

                    // Move the base pointer to the next node
                    base = base.nextNode[index];
                }

                // Mark the end of the word
                base.isEnd = true;
            }
        }

        public boolean query(char letter) {
            // Add the new character to the stream
            stream.append(letter);

            // Get the trie root
            Trie base = this.root;

            // Traverse the stream in reverse order
            for (int i = stream.length() - 1; i >= 0; i--) {
                // Get the character at index i
                char ch = stream.charAt(i);

                // Get the relative index
                int index = ch - 'a';

                // If no path exists in trie then return false
                if (base.nextNode[index] == null) {
                    return false;
                }

                // Move the base pointer to the next node
                base = base.nextNode[index];

                // If any word ends here then return true
                if (base.isEnd) {
                    return true;
                }
            }

            // Return false if no matching word is found
            return false;
        }

        // Helper class for the trie Data Structure
        private static class Trie {
            // Variable to store the next word node
            private final Trie[] nextNode;

            // Variable to mark the end of a word
            private boolean isEnd;

            public Trie() {
                // Initialize the nextNode array of size 26
                this.nextNode = new Trie[26];

                // Set the end marker as false
                this.isEnd = false;
            }
        }
    }

    // Main method to test StreamChecker
    public static void main(String[] args) {

        String[] operations = {
                "StreamChecker", "query", "query", "query", "query", "query", "query",
                "query", "query", "query", "query", "query", "query"
        };

        List<String[]> values = new ArrayList<>();

        values.add(new String[] { "cd", "f", "kl" }); // StreamChecker(["cd","f","kl"])
        values.add(new String[] { "a" }); // query("a")
        values.add(new String[] { "b" }); // query("b")
        values.add(new String[] { "c" }); // query("c")
        values.add(new String[] { "d" }); // query("d")
        values.add(new String[] { "e" }); // query("e")
        values.add(new String[] { "f" }); // query("f")
        values.add(new String[] { "g" }); // query("g")
        values.add(new String[] { "h" }); // query("h")
        values.add(new String[] { "i" }); // query("i")
        values.add(new String[] { "j" }); // query("j")
        values.add(new String[] { "k" }); // query("k")
        values.add(new String[] { "l" }); // query("l")

        // Create the StreamChecker object
        StreamChecker streamChecker = new StreamChecker(operations);

        // Loop through the operations and values arrays
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];

            if (operation.equals("StreamChecker")) {
                streamChecker = new StreamChecker(values.get(i));
                System.out.println("null");
            }

            if (operation.equals("query")) {
                System.out.println(streamChecker.query(values.get(i)[0].charAt(0)));
            }
        }
    }
}
