/*
LeetCode Problem: https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/

Question: 2486. Append Characters to String to Make Subsequence

Problem Statement: Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false

Constraints:

0 <= s.length <= 100
0 <= t.length <= 10^4
s and t consist only of lowercase English letters.

Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 */

/*
Approach: Two-Pointer Subsequence Matching

Goal:
Find the minimum number of characters that need to be appended to string s
so that t becomes a subsequence of s.

Key Idea:
We try to match as many characters of t as possible in s, in order.
Any remaining unmatched characters in t must be appended.

Algorithm:
1. Use two pointers:
   - index → points to current character in t.
   - Iterate through characters of s.
2. For each character in s:
   - If index reaches the length of t → all characters matched → return 0.
   - If s[i] == t[index], increment index.
3. After traversal:
   - index represents how many characters of t were matched.
   - Remaining characters to append = t.length − index.

Why It Works:
- Subsequence matching only requires order preservation.
- Matching greedily from left to right maximizes matched prefix of t.

Time Complexity: O(n)
- n = length of s

Space Complexity: O(1)
*/

package Arrays.Medium;

public class _2486_Append_Characters_to_String_to_Make_Subsequence {
    // Method to find the number of character to append to string to make it a
    // subsequence of another string
    public static int appendCharacters(String s, String t) {
        // Convert the string s and t into the character array
        char[] str1 = s.toCharArray(), str2 = t.toCharArray();

        // Create a index to keep track of the subsequence
        int index = 0;

        // Iterate over the str1 array to find out the length subsequence is present in
        // the string or not
        for (int i = 0; i < str1.length; i++) {
            // Eary break out if the index is equal to the length of the str2 and return 0
            if (index == str2.length) {
                return 0;
            }

            // If the character is same then move forward with the index
            if (str1[i] == str2[index]) {
                index++;
            }
        }

        // Return the difference of the index and the t length
        return str2.length - index;
    }

    // Main method to test appendCharacters
    public static void main(String[] args) {
        String s = "coaching", t = "coding";

        int result = appendCharacters(s, t);

        System.out.println("The make string " + s + " the subsequence of " + t + " we need to append the " + result
                + " characters.");
    }
}
