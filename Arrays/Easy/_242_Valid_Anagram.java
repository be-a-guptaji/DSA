/*
LeetCode Problem: https://leetcode.com/problems/valid-anagram/

Question: 242. Valid Anagram

Problem Statement: Given two strings s and t, return true if t is an anagram of s, and false otherwise.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:
1 <= s.length, t.length <= 5 * 10^4
s and t consist of lowercase English letters.

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/

/*
Approach: Check if lengths are equal. If not, return false immediately.
Convert strings to character arrays and sort them.
Compare sorted arrays character by character.
If all match, return true, else false.

Time Complexity: O(n), where n = length of the strings.
Space Complexity: O(1), fixed-size array of length 26.
*/

package Arrays.Easy;

public class _242_Valid_Anagram {
    // Method to check if two strings are anagrams
    public static boolean isAnagram(String s, String t) {
        // Check if lengths differ
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26]; // For letters 'a' to 'z'

        // Count chars in s and t simultaneously
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++; // Increment for s
            count[t.charAt(i) - 'a']--; // Decrement for t
        }

        // Check if all counts are zero
        for (int c : count) {
            if (c != 0) {
                return false; // Mismatch found return false
            }
        }

        // All counts matched
        return true;
    }

    // Main method to test isAnagram
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";

        boolean result = isAnagram(s, t);

        if (result) {
            System.out.println("The given strings are anagrams.");
        } else {
            System.out.println("The given strings are not anagrams.");
        }
    }
}
