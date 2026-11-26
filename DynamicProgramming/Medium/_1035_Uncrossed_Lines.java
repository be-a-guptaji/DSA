/*
LeetCode Problem: https://leetcode.com/problems/uncrossed-lines/

Question: 1035. Uncrossed Lines

Problem Statement: You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.

We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:

nums1[i] == nums2[j], and
the line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).

Return the maximum number of connecting lines we can draw in this way.

Example 1:
Input: nums1 = [1,4,2], nums2 = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.

Example 2:
Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
Output: 3

Example 3:
Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
Output: 2

Constraints:
1 <= nums1.length, nums2.length <= 500
1 <= nums1[i], nums2[j] <= 2000
*/

/*
Approach: Top-down Memoized DP (Longest Common Subsequence)
Goal:
- Find the maximum number of non-crossing lines
  connecting equal values between nums1 and nums2.
Core Idea:
- Non-crossing lines between equal values form a
  common subsequence, so the answer is the LCS of
  the two arrays.
- dfs(i, j) = best line count using nums1[i..] and
  nums2[j..].
- If nums1[i] == nums2[j], draw the line and move
  both pointers: 1 + dfs(i + 1, j + 1).
- Otherwise skip one element from either array:
  max(dfs(i + 1, j), dfs(i, j + 1)).
- Memoize each (i, j) pair so it is solved once.
Algorithm Steps:
1. If i == n1 or j == n2, return 0.
2. Return dp[i][j] if already computed.
3. Apply the match or skip transition above.
4. Cache and return dp[i][j].
Time Complexity:
- O(n1 * n2)
each state is computed once with O(1) work.
Space Complexity:
- O(n1 * n2)
dp matrix, plus O(n1 + n2) recursion depth.
Result:
- Returns the maximum number of uncrossed lines.
*/

package DynamicProgramming.Medium;

// Solution Class
class Solution {
  // Initialize the dp matrix
  private int[][] dp;

  // Method to find the maximum number of connecting lines we can draw in this way
  public int maxUncrossedLines(int[] nums1, int[] nums2) {
    // Initialize the dp matrix
    this.dp = new int[nums1.length][nums2.length];

    // Fill the dp array
    for (int i = 0; i < nums1.length; i++) {
      int[] row = this.dp[i];
      for (int j = 0; j < nums2.length; j++) {
        row[j] = Integer.MIN_VALUE;
      }
    }

    // Return the recursive call
    return this.dfs(nums1, nums2, 0, 0);
  }

  // Helper method for the dfs
  private int dfs(int[] nums1, int[] nums2, int index1, int index2) {
    // If this.dp[index1][index2] is seen then terminate the function
    if (index1 == nums1.length || index2 == nums2.length) {
      return 0;
    }

    if (this.dp[index1][index2] != Integer.MIN_VALUE) {
      return this.dp[index1][index2];
    }

    // Update the dp matrix
    if (nums1[index1] == nums2[index2]) {
      this.dp[index1][index2] = this.dfs(nums1, nums2, index1 + 1, index2 + 1) + 1;
    } else {
      this.dp[index1][index2] = Math.max(this.dfs(nums1, nums2, index1 + 1, index2),
          this.dfs(nums1, nums2, index1, index2 + 1));
    }

    // Return the dp value
    return this.dp[index1][index2] ;
  }
}

public class _1035_Uncrossed_Lines {
  // Main method to test maxUncrossedLines
  public static void main(String[] args) {
    int[] nums1 = new int[] { 2, 5, 1, 2, 5 };
    int[] nums2 = new int[] { 10, 5, 2, 1, 5, 2 };

    int result = new Solution().maxUncrossedLines(nums1, nums2);

    System.out.println("The maximum number of connecting lines we can draw in this way is : " + result);
  }
}