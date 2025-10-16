/*
LeetCode Problem: https://leetcode.com/problems/word-break/

Question: 139. Word Break

Problem Statement: Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/

/*
Approach:
This solution handles the "Word Break" problem.

Key Idea:
- Use Dynamic Programming (DP) to check whether a string `s` can be segmented
  into a space-separated sequence of one or more dictionary words.

Steps:
1. Create a boolean DP array `dp` of size `s.length() + 1`.
   - `dp[i]` means whether the substring `s[i:]` can be segmented into words in the dictionary.
2. Initialize the base case: `dp[s.length()] = true`.
   - An empty string is always "breakable".
3. Loop from the end of the string to the start (i = s.length() - 1 down to 0):
   - For each word in the dictionary:
     - If the substring starting at `i` with the length of the word matches the word,
       and `dp[i + word.length()]` is true, set `dp[i] = true` and break.
4. Return `dp[0]`, which indicates if the whole string can be broken using words in the dictionary.

Time Complexity: O(n * m), where, n = length of the string `s` and m = total number of words in the dictionary times average word length (due to substring matching)
Space Complexity: O(n), where n is the length of the string (for the DP array)
*/

package DynamicProgramming.Medium;

import java.util.ArrayList;
import java.util.List;

public class _139_Word_Break {
  // Method to find the word segment in the dictionary
  public static boolean wordBreak(String s, List<String> wordDict) {
    // Initialize the size of the string
    int size = s.length();

    // Initialize the dp array for the memoization
    boolean[] dp = new boolean[size + 1];

    // Initialize the base case for the dp set last index as the true
    dp[size] = true;

    // Iterate over the array to find the word in the array
    for (int i = size - 1; i >= 0; i--) {
      // Check if the word match in the list
      for (String str : wordDict) {
        // Get the size of the str with i
        int strSize = str.length() + i;

        // If length of the substring is less then str string then update the dp table
        if (strSize <= size && s.substring(i, strSize).equals(str)) {
          dp[i] = dp[strSize];
        }

        // If dp[i] is true then break
        if (dp[i]) {
          break;
        }
      }
    }

    // Return the dp[0] as the answer
    return dp[0];
  }

  // Main method to test wordBreak
  public static void main(String[] args) {
    String s = "catsandog";
    List<String> wordDict = new ArrayList<>();

    wordDict.add("cats");
    wordDict.add("dog");
    wordDict.add("sand");
    wordDict.add("and");
    wordDict.add("cat");

    boolean result = wordBreak(s, wordDict);

    System.out.println("The word " + s + " can " + (result ? "" : "not") + " be segmented into the word dictionary.");
  }
}
