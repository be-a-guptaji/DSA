/*
LeetCode Problem: https://leetcode.com/problems/optimal-partition-of-string/

Question: 2405. Optimal Partition of String

Problem Statement: Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.

Return the minimum number of substrings in such a partition.

Note that each character should belong to exactly one substring in a partition.

Example 1:
Input: s = "abacaba"
Output: 4
Explanation:
Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
It can be shown that 4 is the minimum number of substrings needed.

Example 2:
Input: s = "ssssss"
Output: 6
Explanation:
The only valid partition is ("s","s","s","s","s","s").

Constraints:
1 <= s.length <= 10^5
s consists of only English lowercase letters.
*/

/*
Approach: Greedy + Character Set Tracking

Goal:
- Partition the string into the minimum number of substrings
  such that each substring contains unique characters only.

Core Idea:
- Extend the current substring greedily until a duplicate character appears.
- When a duplicate is encountered:
   - Start a new substring.
   - Reset character tracking.
- This guarantees the minimum number of partitions.

Algorithm Steps:
1. Initialize:
   - seen[26] to track characters in current substring.
   - numberOfSubstring = 1.
2. Traverse characters:
   - Convert character to index.
   - If already seen:
       → reset seen array
       → increment substring count
   - Mark current character as seen.
3. Return total substring count.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the minimum number of substrings
  with all unique characters.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the minimum number of substrings in such a partition
  public int partitionString(String s) {
    // Convert the string in to the character array
    char[] str = s.toCharArray();

    // Initialize the boolean array of seen
    boolean[] seen = new boolean[26];

    // Initialize the numberOfSubstring variable
    int numberOfSubstring = 1;

    // Iterate over the str to get the numberOfSubstring
    for (int i = 0; i < str.length; i++) {
      // Get the index offset of the character
      int index = str[i] - 'a';

      // If we already seen the character reset the boolean array and increment the
      // numberOfSubstring
      if (seen[index]) {
        seen = new boolean[26];
        numberOfSubstring++;
      }

      // Set the current index to true
      seen[index] = true;
    }

    // Return numberOfSubstring
    return numberOfSubstring;
  }
}

public class _2405_Optimal_Partition_of_String {
  // Main method to test partitionString
  public static void main(String[] args) {
    String s = "abacaba";

    int result = new Solution().partitionString(s);

    System.out.println("The minimum number of substrings in such a partition is : " + result);
  }
}
