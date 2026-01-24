/*
LeetCode Problem: https://leetcode.com/problems/largest-substring-between-two-equal-characters/

Question: 1624. Largest Substring Between Two Equal Characters

Problem Statement: Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "aa"
Output: 0
Explanation: The optimal substring here is an empty substring between the two 'a's.

Example 2:
Input: s = "abca"
Output: 2
Explanation: The optimal substring here is "bc".

Example 3:
Input: s = "cbzxy"
Output: -1
Explanation: There are no characters that appear twice in s.

Constraints:

1 <= s.length <= 300
s contains only lowercase English letters.
*/

/*
Approach: First and Last Occurrence Tracking

Goal:
Find the length of the longest substring that lies between two equal characters,
excluding the characters themselves. If no such pair exists, return −1.

Key Idea:
For each character, only the first and last occurrences matter.
The maximum distance between them determines the longest valid substring
for that character.

Algorithm:
1. Maintain two arrays of size 26:
   - first[i]: first index where character (i + 'a') appears.
   - last[i]: last index where character (i + 'a') appears.
   Initialize all values to −1.
2. Traverse the string:
   - If first[ch] is −1, set it to current index.
   - Otherwise, update last[ch] to current index.
3. Initialize result = −1.
4. For each character i from 0 to 25:
   - If last[i] != −1:
       • candidate length = last[i] − first[i] − 1
       • update result = max(result, candidate length)
5. Return result.

Why It Works:
- The longest substring between equal characters is always formed
  by their farthest-apart occurrences.
- Tracking only first and last indices avoids unnecessary comparisons.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

import java.util.Arrays;

public class _1624_Largest_Substring_Between_Two_Equal_Characters {
    // Method to find the length of the longest substring between two equal
    // characters, excluding the two characters
    public static int maxLengthBetweenEqualCharacters(String s) {
        // Initialize the frequency array for the character
        int[][] indices = new int[2][26];

        // Fill the indices with -1
        for (int[] index : indices) {
            Arrays.fill(index, -1);
        }

        // Convert the string into the character array
        char[] str = s.toCharArray();

        // Iterate over the string to fill the indices array
        for (int i = 0; i < str.length; i++) {
            // Get the index of the character
            int index = str[i] - 'a';

            // Update the starting and last index of the character
            if (indices[0][index] == -1) {
                indices[0][index] = i;
            } else {
                indices[1][index] = i;
            }
        }

        // Initialize the vairable for the longest substring between two equal character
        int longestSubstringBetweenEqualCharacters = -1;

        // Iterate over the indices to find the the longest substring between two equal
        // character
        for (int i = 0; i < 26; i++) {
            longestSubstringBetweenEqualCharacters = Math.max(longestSubstringBetweenEqualCharacters,
                    indices[1][i] - indices[0][i] - 1);
        }

        // Return the longest substring between two equal character
        return longestSubstringBetweenEqualCharacters;
    }

    // Main method to test maxLengthBetweenEqualCharacters
    public static void main(String[] args) {
        String s = "abca";

        int result = maxLengthBetweenEqualCharacters(s);

        System.out.println(
                "The length of the longest substring between two equal characters, excluding the two characters is : "
                        + result);
    }
}
