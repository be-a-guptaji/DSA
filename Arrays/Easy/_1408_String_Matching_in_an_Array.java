/*
LeetCode Problem: https://leetcode.com/problems/string-matching-in-an-array/

Question: 1408. String Matching in an Array

Problem Statement: Given an array of string words, return all strings in words that are a substring of another word. You can return the answer in any order.

Example 1:
Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.

Example 2:
Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".

Example 3:
Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
All the strings of words are unique.
 */

/*
Approach: Brute Force Substring Check

Goal:
Find all strings in the array that are a substring of another word in the same array.

Key Idea:
For each word, check whether it appears as a substring in any other word.

Algorithm:
1. Initialize an empty result list.
2. For each word at index i:
   - Compare it with every other word at index j (j ≠ i).
   - If words[j] contains words[i]:
       • Add words[i] to the result list.
       • Break to avoid duplicate additions.
3. Return the result list.

Why It Works:
- Directly leverages the built-in substring check.
- Ensures each qualifying word is added only once.

Time Complexity: O(n² × m)
- n = number of words
- m = average length of a word (substring check cost)

Space Complexity: O(1) extra space (excluding output list)
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1408_String_Matching_in_an_Array {
    // Method to find the strings in words that are a substring of another word
    public static List<String> stringMatching(String[] words) {
        // Initialize the result list of string to store the words
        ArrayList<String> result = new ArrayList<>();

        // Iterate over the words array for finding the substring
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                // Skip the word when i and j indices are same and word[i] is in word[j]
                if (i != j && words[j].contains(words[i])) {
                    // Add the result to the list
                    result.add(words[i]);

                    // Break out of the loop
                    break;
                }
            }
        }

        // Return the result list
        return result;
    }

    // Main method to test stringMatching
    public static void main(String[] args) {
        String[] words = new String[] { "mass", "as", "hero", "superhero" };

        List<String> result = stringMatching(words);

        System.out.println(
                "The strings in " + Arrays.toString(words) + " that are a substring of another word is : " + result);
    }
}
