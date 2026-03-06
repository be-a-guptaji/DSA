/*
LeetCode Problem: https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/

Question: 862. Shortest Subarray with Sum at Least K

Problem Statement: Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1], k = 1
Output: 1

Example 2:
Input: nums = [1,2], k = 4
Output: -1

Example 3:
Input: nums = [2,-1,2], k = 3
Output: 3

Constraints:

1 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5
1 <= k <= 10^9
 */

/*
Approach: Prefix Sum + Monotonic Deque

Goal:
- Find the length of the shortest non-empty subarray
  whose sum is at least k.

Core Idea:
- Use prefix sums to convert subarray sum queries into
  differences of prefix values.
- Maintain a monotonic increasing deque of prefix sums.
- This allows efficient removal of useless candidates
  and quick discovery of valid shortest subarrays.

Algorithm Steps:
1. Maintain a running prefix sum.
2. If currentSum >= k → update result.
3. While deque not empty and
   currentSum - deque.front.prefix >= k:
   - Update result with smaller length.
   - Remove front element.
4. Maintain monotonic property:
   - While deque.back.prefix >= currentSum → remove back.
5. Push (currentSum, index) into deque.
6. Return result if found, else -1.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Length of the shortest subarray with sum ≥ k,
  or -1 if no such subarray exists.
*/

package StacksAndQueues.Hard;

import java.util.Deque;
import java.util.LinkedList;

public class _862_Shortest_Subarray_with_Sum_at_Least_K {
    // Method to find the length of the shortest non-empty subarray of nums with a
    // sum of at least k
    public static int shortestSubarray(int[] nums, int k) {
        // Initialize the result variable
        int result = Integer.MAX_VALUE;

        // Initialize the size deque for holding the prefix sum
        Deque<long[]> deque = new LinkedList<>();

        // Initialize the current sum variable
        long currentSum = 0;

        // Iterate over the nums array
        for (int index = 0; index < nums.length; index++) {
            // Update the current sum
            currentSum += nums[index];

            // If current sum is atleast k then update the result variable
            if (currentSum >= k) {
                result = Math.min(result, index + 1);
            }

            // Try to minimize the window
            while (!deque.isEmpty() && currentSum - deque.peekFirst()[0] >= k) {
                // Remove from the left of the deque
                long[] temp = deque.pollFirst();

                // Update the result variable
                result = Math.min(result, index - (int) temp[1]);
            }

            // If current sum is less than the last prefix sum of the deque then pop the
            // last item from the deque
            while (!deque.isEmpty() && deque.peekLast()[0] >= currentSum) {
                // Pop from the deque
                deque.pollLast();
            }

            // Add the current item to the deque
            deque.offerLast(new long[] { currentSum, index });
        }

        // Return the result with the condition
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // Main method to test shortestSubarray
    public static void main(String[] args) {
        int[] nums = { 2, -1, 2 };
        int k = 3;

        int result = shortestSubarray(nums, k);

        System.out.println(
                "The length of the shortest non-empty subarray of nums with a sum of at least k is : " + result);
    }
}
