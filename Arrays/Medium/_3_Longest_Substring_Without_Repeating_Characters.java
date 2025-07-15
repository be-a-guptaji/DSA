/*
LeetCode Problem: https://leetcode.com/problems/longest-substring-without-repeating-characters/

Question: 3. Longest Substring Without Repeating Characters

Problem Statement: Given a string s, find the length of the longest substring without duplicate characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:
0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.
*/

/*
Approach: This solution uses the sliding window technique with an array to track the last seen index of each character.
- The 'start' pointer marks the beginning of the current substring without duplicates.
- The 'end' pointer expands the window by iterating over the string.
- If the character at 'end' has appeared after or at 'start', we move 'start' right after the previous occurrence.
- Update last seen index for the character.
- Keep track of max substring length seen so far.

Time Complexity: O(n), where n = length of the string. Each character is processed at most twice.
Space Complexity: O(1), since the lastIndex array has a fixed size of 128 (ASCII).
*/

package Arrays.Medium;

public class _3_Longest_Substring_Without_Repeating_Characters {
    // Method to find the length of longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {
        // Check if the string is not empty
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // Initilize Variables
        int n = s.length();
        int[] lastIndex = new int[128]; // Array to store last seen index of each ASCII char
        int maxLength = 0; // Track max length of substring without duplicates
        int start = 0; // Start index of current window

        for (int i = 0; i < 128; i++) {
            lastIndex[i] = -1; // Initialize with -1 (meaning not seen yet)
        }

        for (int end = 0; end < n; end++) {
            char ch = s.charAt(end);
            // If current character was seen and is inside the current window, move start
            if (lastIndex[ch] >= start) {
                start = lastIndex[ch] + 1;
            }

            lastIndex[ch] = end; // Update last seen index for current character
            maxLength = Math.max(maxLength, end - start + 1); // Update max length if needed
        }

        // Return the maxlength
        return maxLength;
    }

    // Main method to test lengthOfLongestSubstring
    public static void main(String[] args) {
        String s = "abcabcbb";

        int result = lengthOfLongestSubstring(s);

        System.out.println("The length of the longest substring without repeating characters is: " + result);
    }
}
