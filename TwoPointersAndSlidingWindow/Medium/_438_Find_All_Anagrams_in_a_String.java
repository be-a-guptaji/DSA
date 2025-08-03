/*
LeetCode Problem: https://leetcode.com/problems/find-all-anagrams-in-a-string/

Question: 438. Find All Anagrams in a String

Problem Statement: Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:

1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.
*/

/*
Approach: This problem is best solved using the **sliding window** technique with frequency counting.

- We maintain two frequency arrays (size 26) to track:
  1. The count of characters in the pattern string `p`.
  2. The count of characters in the current sliding window of length `p.length()` in `s`.

- We initialize both arrays and compare them.
- Then we slide the window one character at a time by:
    - Adding the next character to the window
    - Removing the oldest character from the window
    - Comparing the two frequency arrays

- If they match, we record the start index of the window as an anagram match.

This approach ensures we efficiently check each window in linear time.

Time Complexity: O(n)  
- where `n` is the length of `s`. Each character is visited only once during the sliding window.
- Array comparison (`Arrays.equals`) is O(26) per comparison which is constant time.

Space Complexity: O(1)  
- We use two fixed-size arrays (size 26), regardless of the input size.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _438_Find_All_Anagrams_in_a_String {
    // Method to find the index of the anagrams
    public static List<Integer> findAnagrams(String s, String p) {
        // Make the list for the result
        List<Integer> result = new ArrayList<>();

        // Initialize the lenght of both the string
        int slen = s.length(), plen = p.length();

        // Early return if the source string is shorter than the pattern
        if (slen < plen) {
            return result;
        }

        // Convert both strings to character arrays for faster access
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();

        // Frequency arrays for characters in 'p' and current window in 's'
        int[] pCount = new int[26];
        int[] windowCount = new int[26];

        // Initialize frequency counts for the pattern and the first window in 's'
        for (int i = 0; i < plen; i++) {
            pCount[pChars[i] - 'a']++;
            windowCount[sChars[i] - 'a']++;
        }

        // Compare the initial window
        if (Arrays.equals(pCount, windowCount)) {
            result.add(0);
        }

        // Slide the window over 's' one character at a time
        for (int i = plen; i < slen; i++) {
            // Add the new character to the window
            windowCount[sChars[i] - 'a']++;

            // Remove the leftmost character from the window
            windowCount[sChars[i - plen] - 'a']--;

            // If the current window matches the pattern's character count, add the start
            // index
            if (Arrays.equals(pCount, windowCount)) {
                result.add(i - plen + 1);
            }
        }

        // Return the result
        return result;
    }

    // Main method to test findAnagrams
    public static void main(String[] args) {
        String s = "abab", p = "ab";

        List<Integer> result = findAnagrams(s, p);

        System.out.println("The index of the anagrams are : " + result);
    }
}
