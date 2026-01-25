/*
LeetCode Problem: https://leetcode.com/problems/find-common-characters/

Question: 1002. Find Common Characters

Problem Statement: Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.

Example 1:
Input: words = ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:
Input: words = ["cool","lock","cook"]
Output: ["c","o"]

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
 */

/*
Approach: Minimum Frequency Intersection

Goal:
Find all characters that appear in every string of the array.
Each character should appear in the result as many times as it
appears in all strings (minimum frequency).

Key Idea:
For each character, its count in the result is the minimum frequency
across all words.

Algorithm:
1. Initialize an array commonFrequency[26] with Integer.MAX_VALUE.
2. For each word:
   - Build a frequency array freq[26] for that word.
   - Update commonFrequency[i] = min(commonFrequency[i], freq[i]).
3. Initialize an empty result list.
4. For each character i from 0 to 25:
   - Add the character (i + 'a') to the result list
     commonFrequency[i] times.
5. Return the result list.

Why It Works:
- A character must exist in all words, so the limiting factor is
  the smallest count among them.
- Using fixed-size arrays keeps operations efficient.

Time Complexity: O(n × m)
- n = number of words
- m = average word length

Space Complexity: O(1)
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1002_Find_Common_Characters {
    // Method to find an array of all characters that show up in all strings within
    // the words
    public static List<String> commonChars(String[] words) {
        // Initialize the common frequency array for the characters
        int[] commonFrequency = new int[26];

        // Fill the common frequency array with Integer.MAX_VALUE
        Arrays.fill(commonFrequency, Integer.MAX_VALUE);

        // Iterate over the words array to find the the common characters
        for (String word : words) {
            // Initialize the frequency array for the frequency
            int[] frequency = new int[26];

            // Iterate over the word
            for (char ch : word.toCharArray()) {
                // Fill the frequency array
                frequency[ch - 'a']++;
            }

            // Iterate over the frequency array for the common frequency
            for (int i = 0; i < 26; i++) {
                // Fill the minimum frequency array with the minimum frequency of both
                commonFrequency[i] = Math.min(commonFrequency[i], frequency[i]);
            }
        }

        // Initialize the List of string for the result
        ArrayList<String> result = new ArrayList<>();

        // Iterate over the common frequency array for finding out the common character
        for (int i = 0; i < 26; i++) {
            // If common frequency of index i is not equal to zero then add to the result
            // list
            if (commonFrequency[i] != 0) {
                // Iterate while common frequency of index i is not equal to zero
                while (commonFrequency[i] != 0) {
                    // Add the string to the result list
                    result.add(Character.toString((char) (i + 'a')));

                    // Decrement the common frequency of index i
                    commonFrequency[i]--;
                }
            }
        }

        // Retrun the result
        return result;
    }

    // Main method to test commonChars
    public static void main(String[] args) {
        String[] words = new String[] { "bella", "label", "roller" };

        List<String> result = commonChars(words);

        System.out.println("An array of all characters that show up in all strings within the words is : " + result);
    }
}
