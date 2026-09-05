/*
LeetCode Problem: https://leetcode.com/problems/maximum-subsequence-score/

Question: 2542. Maximum Subsequence Score

Problem Statement: You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.

For chosen indices i0, i1, ..., ik - 1, your score is defined as:

    The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
    It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).

Return the maximum possible score.

A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.

Example 1:
Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
Output: 12
Explanation: 
The four possible subsequence scores are:
- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6. 
- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12. 
- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
Therefore, we return the max score, which is 12.

Example 2:
Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
Output: 30
Explanation: 
Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.

Constraints:
    n == nums1.length == nums2.length
    1 <= n <= 10^5
    0 <= nums1[i], nums2[j] <= 10^5
    1 <= k <= n
*/

/*
Approach: Sort by nums2 Descending with Sliding K-Window Min-Heap
Goal:
- Select exactly k indices to maximize
  (sum of selected nums1[i]) * (minimum of selected
  nums2[i]).
Core Idea:
- For any group of k indices, the minimum nums2
  value in the group is the multiplier. Sorting
  pairs by nums2 descending means that when
  processing index i, pairs[i][1] is guaranteed to
  be the minimum nums2 in any group drawn from
  indices 0..i.
- This lets the problem reduce to: for each i as
  the mandatory minimum nums2 element, find the
  k-1 other indices from 0..i-1 with the largest
  nums1 values to maximize the sum.
- A min-heap of size k tracks the top-k nums1
  values seen so far, evicting the smallest when
  the window exceeds k.
Algorithm Steps:
1. Build pairs array of (nums1[i], nums2[i]) and
   sort descending by nums2.
2. Initialize a min-heap, current sum = 0, and
   maxScore = 0.
3. For each pair (n1, n2) in sorted order:
   a. Add n1 to current and push n1 onto minHeap.
   b. If minHeap size exceeds k, poll the minimum
      from minHeap and subtract it from current
      (evict the smallest nums1 from the window).
   c. If minHeap size == k, update maxScore =
      max(maxScore, current * n2), since n2 is the
      minimum nums2 for any group ending here.
4. Return maxScore.
Why It Works:
- Descending sort on nums2 guarantees that the
  current pair's nums2 is the minimum of all pairs
  processed so far, making it the valid multiplier
  for any k-subset ending at this index.
- The min-heap maintains exactly the k largest
  nums1 values seen so far, maximizing the sum for
  each candidate minimum nums2.
- Every possible optimal group is considered:
  whichever index contributes the minimum nums2
  will be the pivot when processed, and the heap
  holds the best accompanying k-1 elements.
Time Complexity:
- O(n log n)
for sorting and O(n log k) for heap operations,
overall O(n log n) since log n dominates log k.
Space Complexity:
- O(n)
for the pairs array and O(k) for the min-heap.
Result:
- Returns the maximum achievable score across all
  valid k-index selections.
*/

package StacksQueuesAndHeaps.Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the maximum possible score
  public long maxScore(int[] nums1, int[] nums2, int k) {
    // Initialize the minHeap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // Initialize the length
    int length = nums1.length;

    // Initialize the arrya of parirs
    int[][] pairs = new int[length][2];

    // Fill the pairs array
    for (int i = 0; i < length; i++) {
      pairs[i][0] = nums1[i];
      pairs[i][1] = nums2[i];
    }

    // Sort the pairs array
    Arrays.sort(pairs, (a, b) -> Integer.compare(b[1], a[1]));

    // Initialize the max score and current varaible
    long maxScore = 0;
    long current = 0;

    // Iterate over the pairs array
    for (int i = 0; i < length; i++) {
      // Get the current nums1
      current += pairs[i][0];

      // Add the nums1 to the minHeap
      minHeap.offer(pairs[i][0]);

      // If minHeap size is greater than k then remove the element from minHeap
      if (minHeap.size() > k) {
        current -= minHeap.poll();
      }

      // Update the maxScore when minHeap size is equal to k
      if (minHeap.size() == k) {
        maxScore = Math.max(maxScore, current * pairs[i][1]);
      }
    }

    // Return the maxScore
    return maxScore;
  }
}

// Main Class
public class _2542_Maximum_Subsequence_Score {
  // Main method to test maxScore
  public static void main(String[] args) {
    int[] nums1 = new int[] { 2, 1, 3, 5, 6 };
    int[] nums2 = new int[] { 2, 1, 3, 5, 6 };
    int k = 5;

    long result = new Solution().maxScore(nums1, nums2, k);

    System.out.println("The maximum possible score is : " + result);
  }
}
