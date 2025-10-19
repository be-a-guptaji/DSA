/*
LeetCode Problem: https://leetcode.com/problems/longest-palindromic-substring/

Question: 5. Longest Palindromic Substring

Problem Statement: Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
*/

/*
Approach:
This solution uses the "Expand Around Center" technique. The idea is that a palindrome mirrors around its center, and there are 2n - 1 possible centers in a string of length n (each character and the gap between characters). For each center, expand outward as long as the substring is a palindrome, and keep track of the longest one found.

Time Complexity: O(n^2), where n is the length of the input string. For each center (2n-1 in total), we might expand up to n times in the worst case.
Space Complexity: O(1), as we only use a constant amount of extra space (excluding the input and output).
*/

package Arrays.Medium;

public class _5_Longest_Palindromic_Substring {
    // Method to find the longest palindromic substring
    public static String longestPalindrome(String s) {
        int start = 0, end = 0;

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            // Update the start and end indices if a longer palindrome is found
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // Return the longest palindromic substring
        return s.substring(start, end + 1);
    }

    // Helper function to find the palandromic string
    private static int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // Return the length of the palindrome found
        return right - left - 1;
    }

    // Main method to test longestPalindrome
    public static void main(String[] args) {
        String s = "cbbd";

        String result = longestPalindrome(s);

        System.out.println("The length of the longest substring is: " + result);
    }
}
