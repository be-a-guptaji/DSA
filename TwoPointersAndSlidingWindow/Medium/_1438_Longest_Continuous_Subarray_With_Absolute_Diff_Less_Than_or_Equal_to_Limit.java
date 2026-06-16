/*
LeetCode Problem: https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

Question: 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

Problem Statement: Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

Example 1:
Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.

Example 2:
Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:
Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
*/

/*
Approach: Sliding Window + Monotonic Deques

Goal:
- Find the length of the longest subarray such that:

      max(subarray) - min(subarray) <= limit

Core Idea:
- A sliding window can efficiently maintain a valid
  subarray.
- The challenge is obtaining the current minimum
  and maximum in O(1).

Use two monotonic deques:

1. minDeque (increasing order)
   - Front = current minimum

2. maxDeque (decreasing order)
   - Front = current maximum

As the window expands:
- Remove elements from the back that violate
  monotonicity.
- Insert the new element.

If:

      maxDeque.front - minDeque.front > limit

the window becomes invalid and must be shrunk
from the left.

Algorithm Steps:
1. Maintain:
      left pointer
      minDeque
      maxDeque
2. For each nums[right]:
   - Insert into minDeque while preserving
     increasing order.
   - Insert into maxDeque while preserving
     decreasing order.
3. While:

      maxDeque.front - minDeque.front > limit

   shrink the window:
   - Remove nums[left] from a deque if it is
     currently at that deque's front.
   - Increment left.
4. Update:

      result = max(result, right - left + 1)

5. Return result.

Deque Invariants:
-----------------
minDeque:
      front <= ... <= back

maxDeque:
      front >= ... >= back

Therefore:
      min = minDeque.front
      max = maxDeque.front

available in O(1).

Why It Works:
- The deques always contain candidates for the
  minimum and maximum of the current window.
- Each element enters and leaves each deque at
  most once.
- Thus all operations are amortized O(1).

Time Complexity:
- O(n)

Space Complexity:
- O(n)
  in the worst case for the deques

Result:
- Returns the length of the longest subarray
  whose maximum and minimum differ by at most
  limit.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.ArrayDeque;

// Solution Class
class Solution {
  // Method to find the size of the longest non-empty subarray such that the
  // absolute difference between any two elements of this subarray is less than or
  // equal to limit
  public int longestSubarray(int[] nums, int limit) {
    // Initialize the max and min heap
    ArrayDeque<Integer> minHeap = new ArrayDeque<>();
    ArrayDeque<Integer> maxHeap = new ArrayDeque<>();

    // Initialize the result variable
    int result = 0;

    // Iterate over the nums array
    for (int left = 0, right = 0; right < nums.length; right++) {
      // Get the current number
      int num = nums[right];

      // Update the minHeap
      while (!minHeap.isEmpty() && minHeap.peekLast() > num) {
        minHeap.pollLast();
      }

      // Update the maxHeap
      while (!maxHeap.isEmpty() && maxHeap.peekLast() < num) {
        maxHeap.pollLast();
      }

      // Add the num value to the max and min heap
      minHeap.offerLast(num);
      maxHeap.offerLast(num);

      // If maxHeap.peekFirst() - minHeap.peekFirst() is greater than limit then
      // update the heaps and left pointer
      while (maxHeap.peekFirst() - minHeap.peekFirst() > limit) {
        // Get the current left index number
        int number = nums[left];

        // If number is smallest then remove it from the minHeap
        if (number == minHeap.peekFirst()) {
          minHeap.pollFirst();
        }

        // If number is largest then remove it from the maxHeap
        if (number == maxHeap.peekFirst()) {
          maxHeap.pollFirst();
        }

        // Update the left pointer
        left++;
      }

      // Update the result
      result = Math.max(result, right - left + 1);
    }

    // Return the result variable
    return result;
  }
}

public class _1438_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_or_Equal_to_Limit {
  // Main method to test longestSubarray
  public static void main(String[] args) {
    int[] nums = new int[] { 8, 2, 4, 7 };
    int limit = 4;

    int result = new Solution().longestSubarray(nums, limit);

    System.out.println(
        "The size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit is : "
            + result);
  }
}
