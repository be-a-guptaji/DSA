/*
LeetCode Problem: https://leetcode.com/problems/longest-happy-string/

Question: 1405. Longest Happy String

Problem Statement: A string s is called happy if it satisfies the following conditions:

    s only contains the letters 'a', 'b', and 'c'.
    s does not contain any of "aaa", "bbb", or "ccc" as a substring.
    s contains at most a occurrences of the letter 'a'.
    s contains at most b occurrences of the letter 'b'.
    s contains at most c occurrences of the letter 'c'.

Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.

Example 2:
Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.

Constraints:
    0 <= a, b, c <= 100
    a + b + c > 0
*/

/*
Approach: Greedy Max-Frequency Character Selection with Consecutive Blocking
Goal:
- Construct the longest possible string using at
  most a 'a's, b 'b's, and c 'c's such that no
  character appears three or more times consecutively.
Core Idea:
- At each step, greedily append the character with
  the highest remaining frequency, since consuming
  the most abundant character fastest minimizes
  waste.
- If the last two characters are the same, that
  character is temporarily blocked for the next
  step to prevent three consecutive occurrences.
- If no valid character can be placed (all
  remaining characters are blocked or exhausted),
  the string is complete.
Algorithm Steps:
1. Initialize count[3] = {a, b, c} and repeated = -1
   (no character blocked initially).
2. While true:
   a. Call getMax(count, repeated) to find the
      unblocked character with the highest remaining
      count.
   b. If getMax returns -1, no valid character
      exists, break.
   c. Append the selected character to result and
      decrement its count.
   d. If the last two characters in result are now
      equal, set repeated = selected character
      (block it next iteration).
   e. Otherwise set repeated = -1 (unblock all).
3. Return result as a string.
Why It Works:
- Greedy selection of the highest-frequency
  character maximizes total length: placing a less
  frequent character when the most frequent is
  available wastes an opportunity to consume the
  character most at risk of being stranded at the
  end.
- Blocking only after two consecutive occurrences
  (not one) allows the maximum allowed run of two
  before forcing a switch, preserving as many
  characters as possible.
- Since only three characters exist, blocking one
  still leaves up to two candidates, so a valid
  placement always exists unless all remaining
  counts are zero or only the blocked character has
  remaining count.
Time Complexity:
- O(a + b + c)
since each iteration appends one character and
getMax scans exactly 3 entries in O(1).
Space Complexity:
- O(a + b + c)
for the result StringBuilder.
Result:
- Returns the longest valid string satisfying the
  no-three-consecutive constraint, using as many
  of the given characters as possible.
*/

package StacksQueuesAndHeaps.Medium;

// Solution Class
class Solution {
  // Method to construct the longest possible happy string
  public String longestDiverseString(int a, int b, int c) {

    // Store the remaining frequency of 'a', 'b', and 'c'
    int[] count = { a, b, c };

    // Store the resulting happy string
    StringBuilder res = new StringBuilder();

    // Store the character that cannot be selected because it already appears twice
    int repeated = -1;

    // Continue until no valid character can be added
    while (true) {

      // Find the character with the maximum remaining frequency
      // while excluding the repeated character
      int maxChar = getMax(count, repeated);

      // Stop when no valid character is available
      if (maxChar == -1) {
        break;
      }

      // Append the selected character to the result
      res.append((char) (maxChar + 'a'));

      // Decrease the remaining frequency of the selected character
      count[maxChar]--;

      // Check whether the last two characters are the same
      if (res.length() > 1
          && res.charAt(res.length() - 1) == res.charAt(res.length() - 2)) {

        // Prevent this character from being selected in the next iteration
        repeated = maxChar;
      } else {

        // Allow all available characters to be selected
        repeated = -1;
      }
    }

    // Return the longest possible happy string
    return res.toString();
  }

  // Method to find the valid character with the maximum remaining frequency
  private int getMax(int[] count, int repeated) {

    // Store the selected character index and its frequency
    int idx = -1;
    int maxCnt = 0;

    // Check the frequencies of 'a', 'b', and 'c'
    for (int i = 0; i < 3; i++) {

      // Skip the blocked character and characters with no remaining frequency
      if (i == repeated || count[i] == 0) {
        continue;
      }

      // Update the selected character when a larger frequency is found
      if (maxCnt < count[i]) {
        maxCnt = count[i];
        idx = i;
      }
    }

    // Return the selected character index, or -1 if none is available
    return idx;
  }
}

// Main Class
public class _1405_Longest_Happy_String {
  // Main method to test longestDiverseString
  public static void main(String[] args) {
    int a = 1;
    int b = 1;
    int c = 7;

    String result = new Solution().longestDiverseString(a, b, c);

    System.out.println("The longest possible happy string is : " + result);
  }
}
