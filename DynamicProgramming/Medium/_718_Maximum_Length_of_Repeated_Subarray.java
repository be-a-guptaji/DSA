/*
LeetCode Problem: https://leetcode.com/problems/maximum-length-of-repeated-subarray/

Question: 718. Maximum Length of Repeated Subarray

Problem Statement: Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

Example 1:
Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].

Example 2:
Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
Explanation: The repeated subarray with maximum length is [0,0,0,0,0].

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100
*/

/*
Approach: Use **Bottom-Up Dynamic Programming** to find the length of the longest common subarray (substring) between two integer arrays.

1. Create a 2D `dp` table of size (len1 + 1) x (len2 + 1), where `dp[i][j]` represents
   the length of the longest common subarray ending at `nums1[i]` and `nums2[j]`.
2. Iterate over both arrays from the end to the beginning:
   - If elements match at the current positions (`nums1[i] == nums2[j]`), then:
       dp[i][j] = dp[i + 1][j + 1] + 1
   - Else, leave `dp[i][j]` as 0 (no common subarray ends here)
3. While filling the `dp` table, track the maximum value found, which represents the
   length of the longest repeated subarray.

Time Complexity: O(m * n), where `m` and `n` are the lengths of the input arrays.
Space Complexity: O(m * n), for storing the DP table.
*/

package DynamicProgramming.Medium;

public class _718_Maximum_Length_of_Repeated_Subarray {
  // Method to find the maximum length of repeated subarray
  public static int findLength(int[] nums1, int[] nums2) {
    // Get the length of both the array
    int len1 = nums1.length, len2 = nums2.length;

    // Initialize the dp matrix
    int[][] dp = new int[len1 + 1][len2 + 1];

    // Initialize the maxLength variable to store the maximum length of the subarray
    int maxLength = 0;

    // Find the maximum length of repeated subarray in bottom up manner
    for (int i = len1 - 1; i >= 0; i--) {
      for (int j = len2 - 1; j >= 0; j--) {
        // If character match then get the digonal element + 1
        if (nums1[i] == nums2[j]) {
          dp[i][j] = dp[i + 1][j + 1] + 1;
          maxLength = Math.max(maxLength, dp[i][j]);
        }
      }
    }

    // Return the maxLength
    return maxLength;
  }

  // Main method to test findLength
  public static void main(String[] args) {
    int[] nums1 = { 1, 2, 3, 2, 1 }, nums2 = { 3, 2, 1, 4, 7 };

    int result = findLength(nums1, nums2);

    System.out.println("The maximum length of repeated subarray is : " + result);
  }
}
