/*
LeetCode Problem: https://leetcode.com/problems/constrained-subsequence-sum/

Question: 1425. Constrained Subsequence Sum

Problem Statement: Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

Example 1:
Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].

Example 2:
Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.

Example 3:
Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].

Constraints:
    1 <= k <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
*/

/*
Approach: Sliding Window Maximum DP with Max-Heap
Goal:
- Find the maximum sum subsequence where any two
  consecutive chosen elements are at most k indices
  apart in the original array.
Core Idea:
- Define dp[i] as the maximum subsequence sum
  ending at index i.
- dp[i] = nums[i] + max(0, max(dp[i-k], ...,
  dp[i-1])), since we can either start a new
  subsequence at i or extend the best one within
  the last k positions.
- A max-heap keyed on (dp value, index) provides
  O(log n) access to the maximum dp value in the
  valid window [i-k, i-1], with lazy eviction of
  out-of-window entries.
Algorithm Steps:
1. Initialize result = nums[0] and push
   {nums[0], 0} onto the max-heap (dp[0] = nums[0]).
2. For each i from 1 to n-1:
   a. Evict heap entries whose index <= i - k - 1
      (outside the valid window) by popping while
      i - heap.peek()[1] > k.
   b. Compute currentMax = nums[i] + max(0,
      heap.peek()[0]): extend the best previous
      subsequence if it improves the sum, otherwise
      start fresh at nums[i].
   c. Update result = max(result, currentMax).
   d. Push {currentMax, i} onto the heap (record
      dp[i]).
3. Return result.
Why It Works:
- Taking max(0, heap.peek()[0]) handles the case
  where all previous dp values in the window are
  negative; in that case starting a new subsequence
  at i is better.
- The max-heap always exposes the largest dp value
  within the valid window after lazy eviction,
  giving the optimal extension point for index i.
- Lazy eviction (removing only when the top is
  out-of-window) avoids O(k) cleanup per step,
  keeping total evictions bounded by n.
Time Complexity:
- O(n log n)
since each element is pushed and popped from the
heap at most once, each costing O(log n).
Space Complexity:
- O(n)
for the max-heap storing up to n entries.
Result:
- Returns the maximum sum of a valid constrained
  subsequence.
*/

package TwoPointersAndSlidingWindow.Hard;

import java.util.PriorityQueue;

// Solution Class 
class Solution {
  // Method to find the maximum sum of a non-empty subsequence of that array such
  // that for every two consecutive integers in the subsequence
  public int constrainedSubsetSum(int[] nums, int k) {
    // Initialize the maxHeap for the nums
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    // Initialize the result variable
    int result = nums[0];

    // Add the nums[0] to the maxHeap
    maxHeap.offer(new int[] { nums[0], 0 });

    // Iterate over the nums array
    for (int i = 1; i < nums.length; i++) {
      // Remove the index which is far than k
      while (i - maxHeap.peek()[1] > k) {
        maxHeap.poll();
      }

      // Get the current max
      int currentMax = Math.max(nums[i], nums[i] + maxHeap.peek()[0]);

      // Update the result variable
      result = Math.max(result, currentMax);

      // Add the current max to the maxHeap
      maxHeap.offer(new int[] { currentMax, i });
    }

    // Return the result variable
    return result;
  }
}

public class _1425_Constrained_Subsequence_Sum {
  // Main method to test constrainedSubsetSum
  public static void main(String[] args) {
    int[] nums = new int[] { 10, 2, -10, 5, 20 };
    int k = 2;

    int result = new Solution().constrainedSubsetSum(nums, k);

    System.out.println(
        "The maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence is : "
            + result);
  }
}
