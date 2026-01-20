/*
LeetCode Problem: https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/

Question: 1160. Find Words That Can Be Formed by Characters

Problem Statement: You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once for each word in words).

Return the sum of lengths of all good strings in words.

Example 1:
Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

Example 2:
Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.

Constraints:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.
 */

/*
Approach: Frequency Comparison per Word

Goal:
Compute the sum of lengths of all “good” strings in the array.
A word is good if it can be formed using characters from `chars`,
where each character can be used only as many times as it appears in `chars`.

Key Idea:
- Precompute character frequencies of `chars`.
- For each word, compute its own frequency and compare against `chars`.

Algorithm:
1. Build a frequency array `freqChars[26]` for the string `chars`.
2. Initialize totalLength = 0.
3. For each word in `words`:
   - Build a frequency array `freqWord[26]`.
   - Compare `freqWord` with `freqChars` for all characters:
       • If any freqWord[i] > freqChars[i], the word cannot be formed.
   - If the word can be formed, add its length to totalLength.
4. Return totalLength.

Why It Works:
- Frequency arrays ensure exact character availability checks.
- Each word is validated independently and efficiently.

Time Complexity: O(n × m)
- n = number of words
- m = average length of a word

Space Complexity: O(1)
- Fixed-size frequency arrays (26 characters)
*/

package Arrays.Easy;

public class _1160_Find_Words_That_Can_Be_Formed_by_Characters {
    // Method to find the sum of lengths of all good strings in words
    public static int countCharacters(String[] words, String chars) {
        // Initialize the sum of lengths of all good strings in words
        int goodStrings = 0;

        // Make a frequency array of the chars
        int[] frequency = new int[26];

        // Fill the frequency array
        for (char ch : chars.toCharArray()) {
            frequency[ch - 'a']++;
        }

        // Iterate over the words for finding the good words
        for (String word : words) {
            // Initiate the word frequency array
            int[] wordFrequency = new int[26];

            // Convert the word to the character array
            char[] str = word.toCharArray();

            // Fill the word frequency array
            for (char ch : str) {
                wordFrequency[ch - 'a']++;
            }

            // Initialize the boolean flag for checking if the word can be made or not
            boolean canMake = true;

            // Iterate over the frequency to check if the word is in frequency
            for (int i = 0; i < 26; i++) {
                // If word frequency is more then the frequency array then break the loop and
                // mark canMake to false
                if (wordFrequency[i] > frequency[i]) {
                    canMake = false;
                    break;
                }
            }

            // If can make is true then increase the length by length of the word
            if (canMake) {
                goodStrings += str.length;
            }
        }

        // Return the goodString
        return goodStrings;
    }

    // Main method to test countCharacters
    public static void main(String[] args) {
        String[] words = new String[] { "cat", "bt", "hat", "tree" };
        String chars = "atach";

        int result = countCharacters(words, chars);

        System.out.println("The sum of lengths of all good strings in words is : " + result);
    }
}
