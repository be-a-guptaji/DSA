/*
LeetCode Problem: https://leetcode.com/problems/get-equal-substrings-within-budget/

Question: 1208. Get Equal Substrings Within Budget

Problem Statement: You are given two strings s and t of the same length and an integer maxCost.

You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| (i.e., the absolute difference between the ASCII values of the characters).

Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its corresponding substring from t, return 0.

Example 1:
Input: s = "abcd", t = "bcdf", maxCost = 3
Output: 3
Explanation: "abc" of s can change to "bcd".
That costs 3, so the maximum length is 3.

Example 2:
Input: s = "abcd", t = "cdef", maxCost = 3
Output: 1
Explanation: Each character in s costs 2 to change to character in t,  so the maximum length is 1.

Example 3:
Input: s = "abcd", t = "acde", maxCost = 0
Output: 1
Explanation: You cannot make any change, so the maximum length is 1.

Constraints:
1 <= s.length <= 10^5
t.length == s.length
0 <= maxCost <= 10^6
s and t consist of only lowercase English letters.
*/

/*
Approach: Sliding Window

Goal:
- Find the maximum length substring of s that can
  be transformed into the corresponding substring
  of t with total cost ≤ maxCost.

Cost Definition:
- Changing:
      s[i] -> t[i]
  costs:
      abs(s[i] - t[i])

Core Idea:
- Treat each position as contributing a cost.
- Find the longest contiguous window whose total
  cost does not exceed maxCost.
- Use a sliding window:
   - Expand right pointer.
   - Shrink from the left whenever the cost budget
     is exceeded.

Algorithm Steps:
1. Initialize:
      left = 0
      cost = 0
      maximumLength = 0
2. Expand the window using right:
      cost += abs(s[right] - t[right])
3. While cost > maxCost:
      cost -= abs(s[left] - t[left])
      left++
4. Window is now valid:
      update maximumLength
5. Continue until all characters are processed.
6. Return maximumLength.

Why It Works:
- All costs are non-negative.
- Once a window exceeds the budget,
  expanding it further cannot restore validity.
- Therefore sliding window maintains the longest
  valid substring efficiently.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the maximum length substring that can be
  transformed within the given cost budget.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the maximum length of a substring of s that can be changed to
  // be the same as the corresponding substring of t with a cost less than or
  // equal to maxCost
  public int equalSubstring(String s, String t, int maxCost) {
    // Convert s and t to character array
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();

    // Initialize the maximumLength variable
    int maximumLength = 0;

    // Iterate over the s and t to find the maximum legnth
    for (int cost = 0, left = 0, right = 0; right < str1.length; right++) {
      // Update the cost variable
      cost += Math.abs(str1[right] - str2[right]);

      // If cost is more than the maxCost then shrink the window
      while (cost > maxCost) {
        cost -= Math.abs(str1[left] - str2[left]);
        left++;
      }

      // If cost is less than or equal to the maxCost then update the maximumLength
      if (cost <= maxCost) {
        maximumLength = Math.max(maximumLength, right - left + 1);
      }
    }

    // Return the maximumLength
    return maximumLength;
  }
}

public class _1208_Get_Equal_Substrings_Within_Budget {
  // Main method to test equalSubstring
  public static void main(String[] args) {
    String s = "abcd";
    String t = "bcdf";
    int maxCost = 3;

    int result = new Solution().equalSubstring(s, t, maxCost);

    System.out.println("The maximum length of a substring of " + s
        + " that can be changed to be the same as the corresponding substring of " + t
        + " with a cost less than or equal to " + maxCost + " is : " + result);
  }
}
