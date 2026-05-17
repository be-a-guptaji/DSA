/*
LeetCode Problem: https://leetcode.com/problems/sort-characters-by-frequency/

Question: 451. Sort Characters By Frequency

Problem Statement: Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

Example 1:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

Constraints:
1 <= s.length <= 5 * 10^5
s consists of uppercase and lowercase English letters and digits.
*/

/*
Approach: Frequency Counting + Custom Sorting

Goal:
- Sort characters in the string by decreasing frequency.

Core Idea:
- Count frequency of each character.
- Store only characters with non-zero frequency.
- Sort by:
    1. Higher frequency first
    2. Smaller ASCII value for tie-breaking
- Rebuild the string using sorted frequencies.

Algorithm Steps:
1. Initialize frequency array.
2. Traverse string and count occurrences.
3. Store:
   [frequency, character]
   pairs in a list.
4. Sort list:
   - Descending frequency
   - Ascending character value for ties
5. Build result string by repeating each character
   according to its frequency.
6. Return result string.

Time Complexity:
- O(n + k log k)
  where:
    n = string length
    k = number of distinct characters

Space Complexity:
- O(k)

Result:
- Returns string sorted by character frequency.
*/

package Arrays.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Method to find the sorted string
  public String frequencySort(String s) {
    // Initialize the frequency array
    int[] frequency = new int[123];

    // Convert the string into the character array
    char[] str = s.toCharArray();

    // Fill the character array
    for (int i = 0; i < str.length; i++) {
      frequency[str[i]]++;
    }

    // Initialize the ArrayList for the frequency
    ArrayList<int[]> list = new ArrayList<>();

    // Fill the ArrayList whenever frequency is non zero
    for (int i = 0; i < 123; i++) {
      if (frequency[i] > 0) {
        list.add(new int[] { frequency[i], i });
      }
    }

    // Sort the ArrayList by the frequency first then by character
    list.sort((a, b) -> {
      if (a[0] == b[0]) {
        return a[1] - b[1];
      }
      return b[0] - a[0];
    });

    // Initialize the stringbuilder for the result
    StringBuilder result = new StringBuilder();

    // Iterate over the ArrayList to make the string
    for (int[] entry : list) {
      // Get the character and frequency from the entry
      int freq = entry[0];
      char ch = (char) entry[1];

      for (int i = 0; i < freq; i++) {
        result.append(ch);
      }
    }

    // Return the result
    return result.toString();
  }
}

public class _451_Sort_Characters_By_Frequency {
  // Main method to test sequentialDigits
  public static void main(String[] args) {
    String s = "Aabb";

    String result = new Solution().frequencySort(s);

    System.out.println("The sorted string is : " + result);
  }
}
