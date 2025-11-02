/*
LeetCode Problem: https://leetcode.com/problems/scramble-string/

Question: 87. Scramble String

Problem Statement: We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.

Example 1:
Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now, and the result string is "rgeat" which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.

Example 2:
Input: s1 = "abcde", s2 = "caebd"
Output: false

Example 3:
Input: s1 = "a", s2 = "a"
Output: true

Constraints:

s1.length == s2.length
1 <= s1.length <= 30
s1 and s2 consist of lowercase English letters.
 */

/*
Approach: Recursion + Memoization (Dynamic Programming)

Key Idea:
- We want to determine if one string can be transformed into another through a series of swaps of non-empty substrings.
- Recursively split the strings into two parts in every possible way and check two cases:
  1. Without swapping: both left and right parts match their respective parts.
  2. With swapping: left matches right and right matches left (swapped case).
- Use memoization (HashMap) to cache results of (s1, s2) combinations to avoid recomputation.

Steps:
1. Base cases:
   - If s1.equals(s2), return true.
   - If lengths differ or character frequencies differ, return false.
2. For each split index i from 1 to n-1:
   - Check both swapped and non-swapped combinations recursively.
   - If either returns true, cache and return true.
3. Store results in a HashMap to avoid reprocessing overlapping subproblems.

Time Complexity: O(n^4) in the worst case (due to substring operations and recursion).
Space Complexity: O(n^2) for memoization.
*/

package DynamicProgramming.Hard;

import java.util.Arrays;
import java.util.HashMap;

public class _87_Scramble_String {
    // Make a HashMap for the memoization
    private static HashMap<String, Boolean> memo;

    // Method to find if we can scramble a string s to get a string t
    public static boolean isScramble(String s1, String s2) {
        // Initialize the HashMap
        memo = new HashMap<>();

        // Return the recursive scramble method
        return scramble(s1, s2);
    }

    // Helper method to find the scramble string
    private static boolean scramble(String s1, String s2) {
        // Edge case if both string are equal then return true
        if (s1.equals(s2)) {
            return true;
        }

        // If length of both string is not same then return false
        if (s1.length() != s2.length()) {
            return false;
        }

        // Make a key for the memoization
        StringBuilder sb = new StringBuilder();
        sb.append(s1).append("_").append(s2);

        String key = sb.toString();

        // Check if the key is present in the chache or not
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Initialize a boolean variable for the result
        boolean result = false;

        // Get the length of the s1 string
        int length = s1.length();

        // Optimization: if sorted characters differ, it can’t be scramble
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        if (!Arrays.equals(a1, a2)) {
            memo.put(key, false);
            return false;
        }

        // Iterate over all the index of the string to split
        for (int i = 1; i < length; i++) { // start from 1 to avoid empty substrings
            boolean swapped = scramble(s1.substring(0, i), s2.substring(length - i))
                    && scramble(s1.substring(i), s2.substring(0, length - i));

            // If condition swapped is true then return true
            if (swapped) {
                result = true;
                break;
            }

            boolean notSwapped = scramble(s1.substring(0, i), s2.substring(0, i))
                    && scramble(s1.substring(i), s2.substring(i));

            // If condition notSwapped is true then return true
            if (notSwapped) {
                result = true;
                break;
            }
        }

        // Store the result in the chache
        memo.put(key, result);

        // Return the result
        return result;
    }

    // Main method to test isScramble
    public static void main(String[] args) {
        String s1 = "great", s2 = "rgeat";

        boolean result = isScramble(s1, s2);

        System.out.println(
                "We " + (result ? "" : "not ") + "can scramble a string " + s1 + "  to get a string " + s2 + ".");
    }
}
