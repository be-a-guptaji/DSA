/*
LeetCode Problem: https://leetcode.com/problems/longest-repeating-character-replacement/

Question: 424. Longest Repeating Character Replacement

Problem Statement: You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.

Constraints:

1 <= s.length <= 10^5
s consists of only uppercase English letters.
0 <= k <= s.length
 */

/*
Approach:
This solution uses the sliding window technique with a character frequency array.

Key Idea:
- We maintain a sliding window from `left` to `right`.
- Inside the window, we track the count of the most frequent character (`maxCount`).
- The minimum number of characters to change in the window is:
      (window size - maxCount)
- If this number is > k, it means we need to shrink the window from the left.
- Otherwise, we expand the window and update the maximum length.

Time Complexity: O(n), where n = length of the string.
Space Complexity: O(1)
*/
package TwoPointersAndSlidingWindow.Medium;

public class _424_Longest_Repeating_Character_Replacement {

    // Method to find the longest window of repeating character by replacing k characters
    public static int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();        // Convert string to character array
        int[] count = new int[26];             // Frequency of each uppercase character (A-Z)
        int maxCount = 0;                      // Max frequency of any character in the current window
        int left = 0;                          // Left pointer of the sliding window
        int maxLength = 0;

        // Logic for finding the longest window
        for (int right = 0; right < chars.length; right++) {
            count[chars[right] - 'A']++;
            maxCount = Math.max(maxCount, count[chars[right] - 'A']);

            // If more than k characters need to be replaced, shrink window from left
            while ((right - left + 1) - maxCount > k) {
                count[chars[left] - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        // Return the maxlength
        return maxLength;
    }

    // Main method to test characterReplacement
    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;

        int result = characterReplacement(s, k);

        System.out.println("The longest window of the repeating character by replacing " + k + " characters in the string is " + result);
    }
}
