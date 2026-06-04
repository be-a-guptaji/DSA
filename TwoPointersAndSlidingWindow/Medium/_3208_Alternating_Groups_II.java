/*
LeetCode Problem: https://leetcode.com/problems/alternating-groups-ii/

Question: 3208. Alternating Groups II

Problem Statement: There is a circle of red and blue tiles. You are given an array of integers colors and an integer k. The color of tile i is represented by colors[i]:

colors[i] == 0 means that tile i is red.
colors[i] == 1 means that tile i is blue.
An alternating group is every k contiguous tiles in the circle with alternating colors (each tile in the group except the first and last one has a different color from its left and right tiles).

Return the number of alternating groups.

Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.

Example 1:
Input: colors = [0,1,0,1,0], k = 3
Output: 3
Explanation:
Alternating groups:

Example 2:
Input: colors = [0,1,0,0,1,0,1], k = 6
Output: 2
Explanation:
Alternating groups:

Example 3:
Input: colors = [1,1,0,1], k = 4
Output: 0
Explanation:

Constraints:
3 <= colors.length <= 10^5
0 <= colors[i] <= 1
3 <= k <= colors.length
*/

/*
Approach: Circular Sliding Window

Goal:
- Count the number of circular subarrays of length k
  whose adjacent colors strictly alternate.

Core Idea:
- An alternating group is a window where every adjacent
  pair contains different colors.
- Treat the array as circular using modulo indexing.
- Maintain the longest valid alternating segment ending
  at the current position.
- If two adjacent colors are equal:
      reset the window start.
- Whenever the current alternating segment has length k,
  a valid group is found.

Algorithm Steps:
1. Let:
      left = start of current alternating segment
2. Traverse circularly from:
      right = 1
      to length + k - 2
3. Compare:
      colors[(right - 1) % n]
      colors[right % n]
4. If equal:
      left = right
5. If current window exceeds k:
      move left forward.
6. If window size becomes exactly k:
      increment answer.
7. Return total count.

Time Complexity:
- O(n + k)

Space Complexity:
- O(1)

Result:
- Returns the number of alternating groups
  of size exactly k in the circular array.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the number of alternating groups
  public int numberOfAlternatingGroups(int[] colors, int k) {
    // Initialize the length of the array
    final int length = colors.length;

    // Initialize the alternating group variable
    int alternatingGroups = 0;

    // Iterate over the colors array
    for (int left = 0, right = 1; right < length + k - 1; right++) {
      // If no alternating members are same then update left variable
      if (colors[(right - 1) % length] == colors[right % length]) {
        left = right;
      }

      // If window is bigger than k then update left variable
      if ((right - left + 1) > k) {
        left++;
      }

      // If window is equal to k then increment the alternatingGroups
      if ((right - left + 1) == k) {
        alternatingGroups++;
      }
    }

    // Return the alternatingGroups
    return alternatingGroups;
  }
}

public class _3208_Alternating_Groups_II {
  // Main method to test numberOfAlternatingGroups
  public static void main(String[] args) {
    int[] colors = new int[] { 0, 1, 0, 1, 0 };
    int k = 3;

    int result = new Solution().numberOfAlternatingGroups(colors, k);

    System.out.println("The number of alternating groups is : " + result);
  }
}
