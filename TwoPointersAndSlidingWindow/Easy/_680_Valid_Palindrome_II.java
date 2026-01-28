/*
LeetCode Problem: https://leetcode.com/problems/valid-palindrome-ii/

Question: 680. Valid Palindrome II

Problem Statement: Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Example 1:
Input: s = "aba"
Output: true

Example 2:
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:
Input: s = "abc"
Output: false

Constraints:

1 <= s.length <= 10^5
s consists of lowercase English letters.
 */

/*
Approach: Two Pointers + One Deletion Check

Idea:
A palindrome reads the same forward and backward. We are allowed to delete
at most one character to make the string a palindrome.

Steps:
1. Use two pointers:
   - left starting at the beginning
   - right starting at the end
2. Move both pointers inward while characters match.
3. On the first mismatch:
   - Try skipping the left character (left + 1, right)
   - Try skipping the right character (left, right - 1)
   - If either substring is a palindrome, return true.
4. If no mismatch occurs, the string is already a palindrome.

Helper Method:
- isPalindrome(s, left, right) checks if a substring is a palindrome
  using the standard two-pointer technique.

Why it works:
- Only one deletion is allowed, so the first mismatch is the only place
  where a choice matters.
- After skipping one character, the rest must be strictly palindromic.

Time Complexity:
- O(n)
  (At most two full scans of the string)

Space Complexity:
- O(1)
  (No extra space used, only pointers)

This is the optimal solution.
*/

package TwoPointersAndSlidingWindow.Easy;

public class _680_Valid_Palindrome_II {
    // Method to determine if the s can be palindrome after deleting at most one
    // character from it
    public static boolean validPalindrome(String s) {
        // Initialize two pointer for the left and right end
        int left = 0, right = s.length() - 1;

        // Iterate over the str untill the left and right pointer does not cross each
        // other
        while (left < right) {
            // If mismatch occur then check all the valid palindrome
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }

            // Update the pointers
            left++;
            right--;
        }

        // Return true in the end
        return true;
    }

    // Helper method to find if string is palindrome or not
    private static boolean isPalindrome(String s, int left, int right) {
        // Iterate over the str untill the left and right pointer does not cross each
        // other
        while (left < right) {
            // If mismatch occur then return false
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            // Update the pointers
            left++;
            right--;
        }

        // Return true in the end
        return true;
    }

    // Main method to test validPalindrome
    public static void main(String[] args) {
        String s = "abca";

        boolean result = validPalindrome(s);

        System.out.println("The " + s + " can" + (result ? " " : " not ")
                + "be palindrome after deleting at most one character from it.");
    }
}
