/*
LeetCode Problem: https://leetcode.com/problems/permutation-in-string/

Question: 567. Permutation in String

Problem Statement: Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input: s1 = "ab", s2 = "eidboaoo"
Output: false 

Constraints:

1 <= s1.length, s2.length <= 10^4
s1 and s2 consist of lowercase English letters.
 */

 /*
 Approach:This problem is best solved using the **sliding window** technique with frequency counting.
 
 - We maintain two frequency arrays (size 26) to track:
  1. The count of characters in the string `s1`.
  2. The count of characters in the current sliding window of size `s1.length()` in `s2`.
 
 - We initialize both arrays and compare them.
 - Then we slide the window one character at a time by:
    - Adding the next character to the window
    - Removing the oldest character from the window
    - Comparing the two frequency arrays
 
 - If they match at any point, we return true.
 
 This approach ensures we efficiently check each window in linear time.
 
 Time Complexity: O(n)
 - where `n` is the length of `s2`. Each character is visited only once during the sliding window.
 - Array comparison (`Arrays.equals`) is O(26) per comparison, which is constant time — treated as O(1).
 
 Space Complexity: O(1)
 - We use two fixed-size arrays (size 26), regardless of the input size.
 */
package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _567_Permutation_in_String {

    // Method to find the inclusion of String s1 in String s2
    public static boolean checkInclusion(String s1, String s2) {
        // Initialize the length of both the strings
        int s1len = s1.length(), s2len = s2.length();

        // Early return if the source string is shorter than the pattern
        if (s1len > s2len) {
            return false;
        }

        // Convert both strings to character arrays for faster access
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        // Frequency arrays for characters in 's1' and current window in 's2'
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        // Initialize frequency counts for the pattern and the first window in 's2'
        for (int i = 0; i < s1len; i++) {
            s1Count[s1Chars[i] - 'a']++;
            windowCount[s2Chars[i] - 'a']++;
        }

        // Compare the initial window; if it is equal, then return true
        if (Arrays.equals(s1Count, windowCount)) {
            return true;
        }

        // Slide the window over 's2' one character at a time
        for (int i = s1len; i < s2len; i++) {
            // Add the new character to the window
            windowCount[s2Chars[i] - 'a']++;

            // Remove the leftmost character from the window
            windowCount[s2Chars[i - s1len] - 'a']--;

            // If the current window matches the pattern's character count, return true
            if (Arrays.equals(s1Count, windowCount)) {
                return true;
            }
        }

        // Return false because no window is matched
        return false;
    }

    // Main method to test checkInclusion
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";

        if (checkInclusion(s1, s2)) {
            System.out.println("The String s2 is included in the String s1");
        } else {
            System.out.println("The String s2 is not included in the String s1");
        }
    }
}
