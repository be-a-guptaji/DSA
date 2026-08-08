/*
LeetCode Problem: https://leetcode.com/problems/counting-words-with-a-given-prefix/

Question: 2185. Counting Words With a Given Prefix

Problem Statement: You are given an array of strings words and a string pref.

Return the number of strings in words that contain pref as a prefix.

A prefix of a string s is any leading contiguous substring of s.

Example 1:
Input: words = ["pay","attention","practice","attend"], pref = "at"
Output: 2
Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".

Example 2:
Input: words = ["leetcode","win","loops","success"], pref = "code"
Output: 0
Explanation: There are no strings that contain "code" as a prefix.

Constraints:
    1 <= words.length <= 100
    1 <= words[i].length, pref.length <= 100
    words[i] and pref consist of lowercase English letters.
*/

/*
Approach: Linear Scan with Built-in Prefix Check
Goal:
- Count how many strings in words have pref as a
  prefix.
Algorithm Steps:
1. Initialize totalPrefixs = 0.
2. For each word in words:
   - If word.startsWith(pref), increment
     totalPrefixs.
3. Return totalPrefixs.
Time Complexity:
- O(n * m)
where n is the number of words and m is the length
of pref, since startsWith runs in O(m) per call.
Space Complexity:
- O(1)
Result:
- Returns the count of words that start with pref.
*/

package Trees.Easy;

// Solution Class
class Solution {
  // Method to find the number of strings in words that contain pref as a prefix
  public int prefixCount(String[] words, String pref) {
    // Initialize the totalPrefixs variable
    int totalPrefixs = 0;

    // Iterate over the words array
    for (String word : words) {
      if (word.startsWith(pref)) {
        totalPrefixs++;
      }
    }

    // Return the totalPrefixss
    return totalPrefixs;
  }
}

// Main Class
public class _2185_Counting_Words_With_a_Given_Prefix {
  // Main method to test prefixCount
  public static void main(String[] args) {
    String[] words = new String[] { "pay", "attention", "practice", "attend" };
    String pref = "at";

    int result = new Solution().prefixCount(words, pref);

    System.out.println("The number of strings in words that contain pref as a prefix is : " + result);
  }
}
