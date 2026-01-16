/*
LeetCode Problem: https://leetcode.com/problems/word-pattern/

Question: 290. Word Pattern

Problem Statement: Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:

Each letter in pattern maps to exactly one unique word in s.
Each unique word in s maps to exactly one letter in pattern.
No two letters map to the same word, and no two words map to the same letter.

Example 1:
Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Explanation:
The bijection can be established as:
'a' maps to "dog".
'b' maps to "cat".

Example 2:
Input: pattern = "abba", s = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false

Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
 */

/*
Approach: Bidirectional Mapping (Pattern → Word)

Goal:
Check whether a string s follows the given pattern.
Each character in the pattern must map to exactly one word, and each word
must map to exactly one pattern character.

Key Idea:
- Maintain a mapping from pattern characters to words.
- Ensure no two different pattern characters map to the same word.

Algorithm:
1. Split the string s into words using space as a delimiter.
2. If the number of words does not match the pattern length → return false.
3. Use:
   - An array patternMap[26] to map pattern characters ('a'–'z') to words.
   - A HashSet seen to track words already mapped.
4. Traverse the pattern and words simultaneously:
   - Let ch be the current pattern character and word be the current word.
   - If patternMap[ch] is null:
       • If word is already in seen → return false.
       • Else map ch → word and add word to seen.
   - Else:
       • If patternMap[ch] ≠ word → return false.
5. If all characters match consistently, return true.

Why It Works:
- Ensures one-to-one correspondence between pattern characters and words.
- Prevents both conflicting mappings and duplicate word usage.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package Arrays.Easy;

import java.util.HashSet;

public class _290_Word_Pattern {
    // Method to find if string s matches the pattern
    public static boolean wordPattern(String pattern, String s) {
        // Split the string s by " "
        String[] words = s.split(" ");

        // Initialize the string array of size 26
        String[] patternMap = new String[26];

        // Initialize the hash set for checking if the word is already mapped or not
        HashSet<String> seen = new HashSet<>();

        // Convert the pattern string to pattern character array
        char[] patternArray = pattern.toCharArray();

        // If length of pattern and string s mismatch then return false
        if (patternArray.length != words.length) {
            return false;
        }

        // Iterate over the pattern for the pattern matching
        for (int i = 0; i < patternArray.length; i++) {
            // Get the index off set from the pattern array
            int index = patternArray[i] - 'a';

            // Get the word at the index i from the words array
            String word = words[i];

            // If index has null then update it to new pattern else match with the string of
            // words with its respective index
            if (patternMap[index] == null) {
                // If we already seen the word then return false else add to seen set and map to
                // the patternMap
                if (seen.contains(word)) {
                    return false;
                } else {
                    patternMap[index] = word;
                    seen.add(word);
                }
            } else {
                // If word and pattern map value are not equal then return false
                if (!patternMap[index].equals(word)) {
                    return false;
                }
            }
        }

        // Retrun true in the end
        return true;
    }

    // Main method to test wordPattern
    public static void main(String[] args) {
        String pattern = "abba", s = "dog cat cat dog";

        boolean result = wordPattern(pattern, s);

        System.out.println(
                "The string " + s + (result ? " follow " : " dose not follow ") + "the pattern " + pattern + ".");
    }
}
