/*
LeetCode Problem: https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/

Question: 1984. Minimum Difference Between Highest and Lowest of K Scores

Problem Statement: You are given a 0-indexed integer array nums, where nums[i] represents the score of the ith student. You are also given an integer k.

Pick the scores of any k students from the array so that the difference between the highest and the lowest of the k scores is minimized.

Return the minimum possible difference.

Example 1:
Input: nums = [90], k = 1
Output: 0
Explanation: There is one way to pick score(s) of one student:
- [90]. The difference between the highest and lowest score is 90 - 90 = 0.
The minimum possible difference is 0.

Example 2:
Input: nums = [9,4,1,7], k = 2
Output: 2
Explanation: There are six ways to pick score(s) of two students:
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 1 = 8.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 7 = 2.
- [9,4,1,7]. The difference between the highest and lowest score is 4 - 1 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 4 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 1 = 6.
The minimum possible difference is 2.

Constraints:
1 <= k <= nums.length <= 1000
0 <= nums[i] <= 10^5
*/

/*
Approach: Sorting + Fixed-Size Window

Goal:
- Select k elements such that the difference
  between maximum and minimum selected values
  is minimized.

Core Idea:
- After sorting:
   - The minimum difference for any group of size k
     must come from a contiguous window.
- For each window:
    difference =
      nums[right] - nums[left]

Algorithm Steps:
1. Sort nums array.
2. Initialize minimumDifference to MAX_VALUE.
3. Traverse all windows of size k:
   - Compute:
       nums[left + k - 1] - nums[left]
   - Update minimumDifference.
4. Edge case:
   - If k == 1, result becomes 0.
5. Return minimumDifference.

Time Complexity:
- O(n log n)
  due to sorting

Space Complexity:
- O(1)

Result:
- Returns minimum possible difference
  among any k selected elements.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the minimum possible difference
    public int minimumDifference(int[] nums, int k) {
        // Sort the array
        Arrays.sort(nums);

        // Initialize the minimumDifference variable
        int minimumDifference = Integer.MAX_VALUE;

        // Iterate over the nums value to find minimumDifference
        for (int left = 0; left < nums.length - k + 1; left++) {
            minimumDifference = Math.min(minimumDifference, nums[left + k - 1] - nums[left]);
        }

        // Return the minimumDifference
        return minimumDifference == Integer.MAX_VALUE ? 0 : minimumDifference;
    }
}

public class _1984_Minimum_Difference_Between_Highest_and_Lowest_of_K_Scores {
    // Main method to test the minimumDifference
    public static void main(String[] args) {
        int[] nums = new int[] {};
        int k = 3;

        int result = new Solution().minimumDifference(nums, k);

        System.out.println("The minimum possible difference is : " + result);
    }
}
