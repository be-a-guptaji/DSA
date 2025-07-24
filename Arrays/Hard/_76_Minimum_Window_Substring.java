/*
LeetCode Problem: https://leetcode.com/problems/minimum-window-substring/

Question: 76. Minimum Window Substring

Problem Statement: Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:

m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

/*
Approach: This solution uses a sliding window strategy combined with two hash maps.
We first build a frequency map for all characters in the string t (targetMap).
Then, we use two pointers (left and right) to create a sliding window over string s.

As we move the right pointer, we expand the window and update character frequencies in a second map (windowMap).
Once the window contains all required characters with their respective frequencies,
we try to shrink it from the left to find the smallest possible window that satisfies the condition.

At every step where the window is valid, we update the result if the current window is smaller than the previous minimum.

Time Complexity: O(m + n), where m is the length of s and n is the length of t.
We traverse each character in s at most twice (once with the right pointer and once with the left),
and building the frequency map for t takes O(n) time.

Space Complexity: O(n), where n is the number of unique characters in t.
We use two hash maps to store frequencies for characters in t and the current window.
*/

package Arrays.Hard;

import java.util.HashMap;
import java.util.Map;

public class _76_Minimum_Window_Substring {
    // Method to find the minimum window substring that contains all characters of t
    public static String minWindow(String s, String t) {
        // If the length of s is less than t, return empty string as it's impossible to
        // satisfy the condition
        if (s.length() < t.length()) {
            return "";
        }

        // Create a map to store the frequency of each character in t
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // Initialize a window map to track character frequencies in the current window
        Map<Character, Integer> windowMap = new HashMap<>();

        // Initialize pointers for sliding window and tracking variables for minimum
        // window
        int left = 0, right = 0, matchCount = 0;
        int minLen = Integer.MAX_VALUE, minLeft = 0;

        // Start traversing the main string s using the right pointer
        while (right < s.length()) {
            char c = s.charAt(right);

            // Add the current character to the window map
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // If the character is part of the target and count matches, increase the match
            // count
            if (targetMap.containsKey(c) &&
                    windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                matchCount++;
            }

            // Try shrinking the window from the left side as long as all characters are
            // matched
            while (matchCount == targetMap.size()) {
                // Update minimum window if the current window is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                // Remove the leftmost character from the window
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);

                // If the character is in the target and its count in window is now less, reduce
                // match count
                if (targetMap.containsKey(leftChar) &&
                        windowMap.get(leftChar) < targetMap.get(leftChar)) {
                    matchCount--;
                }

                // Move the left pointer forward
                left++;
            }

            // Move the right pointer forward to expand the window
            right++;
        }

        // Return the minimum window substring if found, otherwise return an empty
        // string
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    // Main method to test the minWindow function
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = minWindow(s, t);
        System.out.println("The minimum window substring is: " + result);
    }
}
