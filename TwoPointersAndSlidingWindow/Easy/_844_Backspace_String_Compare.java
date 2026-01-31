/*
LeetCode Problem: https://leetcode.com/problems/backspace-string-compare/

Question: 844. Backspace String Compare

Problem Statement: Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

Example 1:
Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:
Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".

Example 3:
Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".

Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.

Follow up: Can you solve it in O(n) time and O(1) space?
*/

/*
Approach: Two Pointers from the End with Backspace Counters

Problem:
Compare two strings after simulating backspace operations ('#'),
where '#' deletes the previous valid character.

Key Insight:
Processing the strings from **right to left** avoids explicitly building
the final strings and simulating backspaces with stacks.

Core Idea:
- Traverse both strings from the end.
- Use counters to track how many characters should be skipped due to backspaces.
- Always move each pointer to the next valid (non-deleted) character.
- Compare valid characters one by one.

Algorithm:
1. Initialize two pointers:
   - indexS at the end of string `s`
   - indexT at the end of string `t`
2. Maintain two counters:
   - backspaceS for pending backspaces in `s`
   - backspaceT for pending backspaces in `t`
3. For each string:
   - If current character is '#', increment backspace counter.
   - If backspace counter > 0, skip the character and decrement the counter.
4. After finding the next valid characters:
   - If both characters exist and are equal → continue.
   - Otherwise, return true only if both strings are fully exhausted.
5. If the loop finishes, the strings are equivalent.

Why It Works:
- Backspaces only affect characters to their left.
- Traversing backward lets us "ignore" deleted characters efficiently.
- No extra data structures are required.

Time Complexity:
- O(n + m), where n and m are lengths of `s` and `t`.

Space Complexity:
- O(1), constant extra space.

This approach is optimal and more efficient than stack-based solutions.
*/

package TwoPointersAndSlidingWindow.Easy;

public class _844_Backspace_String_Compare {
    // Method to determine if two strings are equal after processing backspaces
    public static boolean backspaceCompare(String s, String t) {
        // Pointers starting from the end of both strings
        int indexS = s.length() - 1, indexT = t.length() - 1;

        // Counters to track pending backspaces in each string
        int backspaceS = 0, backspaceT = 0;

        // Loop until a definitive comparison result is reached
        while (true) {

            // Move indexS to the next valid character in s
            // Skip characters that are deleted by backspaces
            while (indexS >= 0 && (backspaceS > 0 || s.charAt(indexS) == '#')) {
                // Increment backspace count if '#' is found, otherwise consume a character
                backspaceS += s.charAt(indexS) == '#' ? 1 : -1;
                indexS--;
            }

            // Move indexT to the next valid character in t
            // Skip characters that are deleted by backspaces
            while (indexT >= 0 && (backspaceT > 0 || t.charAt(indexT) == '#')) {
                // Increment backspace count if '#' is found, otherwise consume a character
                backspaceT += t.charAt(indexT) == '#' ? 1 : -1;
                indexT--;
            }

            // If current valid characters do not match (or one string ends earlier)
            // return true only if both strings are fully processed
            if (!(indexS >= 0 && indexT >= 0 && s.charAt(indexS) == t.charAt(indexT))) {
                return indexS == -1 && indexT == -1;
            }

            // Move both pointers to compare the next characters
            indexS--;
            indexT--;
        }
    }

    // Main method to test backspaceCompare
    public static void main(String[] args) {
        String s = "ab##", t = "c#d#";

        boolean result = backspaceCompare(s, t);

        System.out.println("Both string are" + (result ? " " : " not ") + "equal.");
    }
}
