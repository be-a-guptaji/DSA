/*
LeetCode Problem: https://leetcode.com/problems/is-subsequence/

Question: 392. Is Subsequence

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
Approach: Two-Pointer Linear Scan

Goal:
Check whether string s is a subsequence of string t.

Key Idea:
Traverse both strings in order and try to match characters of s within t.

Algorithm:
1. Initialize an index pointer for s at 0.
2. Traverse characters of t from left to right:
   - If the current character of t matches s[index],
     increment index.
   - If index reaches the length of s, all characters
     have been matched → return true.
3. After traversal, return true only if index == s.length().

Why It Works:
- Subsequence requires relative order, not contiguity.
- A single pass over t is sufficient.

Time Complexity: O(n)
- n = length of t

Space Complexity: O(1)
*/

package Arrays.Easy;

public class _392_Is_Subsequence {
    // Method to find if string s is a subsequence of t
    public static boolean isSubsequence(String s, String t) {
        // Convert the string s and t into the character array
        char[] str1 = s.toCharArray(), str2 = t.toCharArray();

        // Create a index to keep track of the subsequence
        int index = 0;

        // Iterate over the str2 array to find out the subsequence is present in the
        // string or not
        for (int i = 0; i < str2.length; i++) {
            // Eary break out if the index is equal to the length of the str1 and return
            // true
            if (index == str1.length) {
                return true;
            }

            // If the character is same then move forward with the index
            if (str1[index] == str2[i]) {
                index++;
            }
        }

        // If index length is same as the str1 length then return true else false
        return index == str1.length;
    }

    // Main method to test isSubsequence
    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";

        boolean result = isSubsequence(s, t);

        System.out.println("The string " + s + " is" + (result ? " " : " not ") + "a subsequence of " + t + ".");
    }
}
