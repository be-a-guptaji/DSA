/*
LeetCode Problem: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

Question: 1358. Number of Substrings Containing All Three Characters

Problem Statement: Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

Example 3:
Input: s = "abc"
Output: 1

Constraints:
3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
*/

/*
Approach: Sliding Window + Counting Valid Extensions

Goal:
- Count the number of substrings containing at least
  one 'a', one 'b', and one 'c'.

Core Idea:
- Maintain a sliding window that tracks the counts
  of 'a', 'b', and 'c'.
- Once the current window contains all three
  characters:
     frequency[a] > 0
     frequency[b] > 0
     frequency[c] > 0
- Every extension of this window to the right is
  also valid.
- Therefore, for a valid window ending at right:
     number of valid substrings =
         n - right

Algorithm Steps:
1. Maintain:
      left pointer
      frequency[3]
2. Expand the window using right:
      frequency[s[right]]++
3. While the window contains all three characters:
      - Add:
            n - right
        to the answer.
      - Remove s[left] from the window.
      - Increment left.
4. Continue until the string is processed.
5. Return the accumulated count.

Why It Works:
- When a window [left, right] contains all three
  characters, any substring:
      [left, right]
      [left, right+1]
      ...
      [left, n-1]
  also contains all three.
- Thus we can count multiple substrings in O(1)
  rather than enumerating them individually.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the number of substrings containing at
  least one occurrence of 'a', 'b', and 'c'.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the number of substrings containing at least one occurrence of
  // all these characters a, b and c
  public int numberOfSubstrings(String s) {
    // Convert the s to the character array
    char[] str = s.toCharArray();

    // Initialize the numberOfSubstrings variable
    int numberOfSubstrings = 0;

    // Initialize the frequency array for a, b and c
    int[] frequency = new int[3];

    // Iterate over the s to find the numberOfSubstrings
    for (int left = 0, right = 0; right < str.length; right++) {
      // Increment the frequency of the character
      frequency[str[right] - 'a']++;

      // If we have all the character then calculate the numberOfSubstrings
      while (frequency[0] != 0 && frequency[1] != 0 && frequency[2] != 0) {
        // Decrement the frequency of the character
        frequency[str[left] - 'a']--;

        // Increment the left pointer
        left++;

        // Increment the numberOfSubstrings
        numberOfSubstrings += str.length - right;
      }
    }

    // Return the numberOfSubstrings
    return numberOfSubstrings;
  }
}

public class _1358_Number_of_Substrings_Containing_All_Three_Characters {
  // Main method to test numberOfSubstrings
  public static void main(String[] args) {
    String s = "abcd";

    int result = new Solution().numberOfSubstrings(s);

    System.out
        .println("The number of substrings containing at least one occurrence of all these characters a, b and c is : "
            + result);
  }
}
