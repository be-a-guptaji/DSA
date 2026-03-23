/*
LeetCode Problem: https://leetcode.com/problems/maximum-number-of-removable-characters/

Question: 1898. Maximum Number of Removable Characters

Problem Statement: You are given two strings s and p where p is a subsequence of s. You are also given a distinct 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).

You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters from s using the first k indices in removable, p is still a subsequence of s. More formally, you will mark the character at s[removable[i]] for each 0 <= i < k, then remove all marked characters and check if p is still a subsequence.

Return the maximum k you can choose such that p is still a subsequence of s after the removals.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

Example 1:
Input: s = "abcacb", p = "ab", removable = [3,1,0]
Output: 2
Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
"ab" is a subsequence of "accb".
If we remove the characters at indices 3, 1, and 0, "abcacb" becomes "ccb", and "ab" is no longer a subsequence.
Hence, the maximum k is 2.

Example 2:
Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
Output: 1
Explanation: After removing the character at index 3, "abcbddddd" becomes "abcddddd".
"abcd" is a subsequence of "abcddddd".

Example 3:
Input: s = "abcab", p = "abc", removable = [0,1,2,3,4]
Output: 0
Explanation: If you remove the first index in the array removable, "abc" is no longer a subsequence.

Constraints:

1 <= p.length <= s.length <= 10^5
0 <= removable.length < s.length
0 <= removable[i] < s.length
p is a subsequence of s.
s and p both consist of lowercase English letters.
The elements in removable are distinct.
 */

/*
Approach: Binary Search on Answer (Subsequence Validation)

Goal:
- Find the maximum k such that after removing the first k indices
  from removable, string p remains a subsequence of s.

Core Idea:
- The number of removable characters k is monotonic:
  if k works, all smaller values also work.
- Use binary search to find the maximum valid k.
- For each candidate k, simulate removals and check if p
  is still a subsequence of the modified string.

Algorithm Steps:
1. Initialize bounds:
   - left = 0
   - right = removable.length
2. Binary search on k:
   - mid = candidate number of removals.
3. Simulate removal:
   - Mark indices removable[0..mid] in a temp array.
4. Check subsequence:
   - Use two pointers to verify if p is subsequence of modified s.
5. If valid:
   - Increase k (left = mid + 1).
6. Else:
   - Decrease k (right = mid).
7. Return left as the maximum valid k.

Time Complexity:
- O((n + k) log k)
  n = length of s, k = removable.length

Space Complexity:
- O(n)

Result:
- Returns the maximum number of removable characters
  while preserving p as a subsequence of s.
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
  // Method to find the maximum k you can choose such that p is still a
  // subsequence of s after the removals
  public int maximumRemovals(String s, String p, int[] removable) {
    // Initialize the left and right bound
    int left = 0, right = removable.length;

    // Get the length of string s and p
    int n = s.length(), m = p.length();

    // Iterate over the bound
    while (left < right) {
      // Get the mid element
      int mid = (left + right) / 2;

      // Initialize the temp character array
      char[] temp = s.toCharArray();

      // Remove the character form the temp array
      for (int i = 0; i <= mid; i++) {
        temp[removable[i]] = '#';
      }

      // Update the bound
      if (this.isSubseq(temp, p, n, m)) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    // Return left
    return left;
  }

  // Helper method to check if the sub sequence is valid or not
  private boolean isSubseq(char[] s, String p, int n, int m) {
    // Initialize the index for both the array
    int index1 = 0, index2 = 0;

    // Find if the subsequence is valid
    while (index1 < n && index2 < m) {
      if (s[index1] == p.charAt(index2)) {
        index2++;
      }
      index1++;
    }

    // Return the condition
    return index2 == m;
  }
}

public class _1898_Maximum_Number_of_Removable_Characters {
  // Main method to test maximumRemovals
  public static void main(String[] args) {
    String s = "abcbddddd";
    String p = "abcd";
    int[] removable = new int[] { 3, 2, 1, 4, 5, 6 };

    int result = new Solution().maximumRemovals(s, p, removable);

    System.out.println(
        "The maximum k you can choose such that p is still a subsequence of s after the removals is : " + result);
  }
}
