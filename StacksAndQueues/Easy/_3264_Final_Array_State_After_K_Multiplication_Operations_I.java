/*
LeetCode Problem: https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/

Question: 3264. Final Array State After K Multiplication Operations I

Problem Statement: You are given an integer array nums, an integer k, and an integer multiplier.

You need to perform k operations on nums. In each operation:

    Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that appears first.
    Replace the selected minimum value x with x * multiplier.

Return an integer array denoting the final state of nums after performing all k operations.

Example 1:
Input: nums = [2,1,3,5,6], k = 5, multiplier = 2
Output: [8,4,6,5,6]
Explanation:
Operation	Result
After operation 1	[2, 2, 3, 5, 6]
After operation 2	[4, 2, 3, 5, 6]
After operation 3	[4, 4, 3, 5, 6]
After operation 4	[4, 4, 6, 5, 6]
After operation 5	[8, 4, 6, 5, 6]

Example 2:
Input: nums = [1,2], k = 3, multiplier = 4
Output: [16,8]
Explanation:
Operation	Result
After operation 1	[4, 2]
After operation 2	[4, 8]
After operation 3	[16, 8]

Constraints:
    1 <= nums.length <= 100
    1 <= nums[i] <= 100
    1 <= k <= 10
    1 <= multiplier <= 5
*/

/*
Approach: Min-Heap with Index Tracking for k Multiplications
Goal:
- Apply k operations where each operation multiplies
  the current minimum element by multiplier, then
  return the final state of the array.
Core Idea:
- A min-heap always provides O(log n) access to the
  current minimum, which is the target of each
  operation.
- Store (value, original_index) pairs in the heap
  so the final values can be placed back at their
  correct positions in the result array.
Algorithm Steps:
1. Build a min-heap of (value, index) pairs for all
   elements, ordered by value first and index second
   (index breaks ties to match problem semantics).
2. Repeat k times:
   - Poll the minimum (value, index) pair.
   - Push (value * multiplier, index) back.
3. Drain the heap into result[], placing each value
   at its original index.
4. Return result.
Time Complexity:
- O((n + k) log n)
where n is the number of elements. Heap
construction is O(n log n), and each of the k
operations costs O(log n).
Space Complexity:
- O(n)
for the min-heap and result array.
Result:
- Returns the array after all k multiplications
  have been applied to the minimum element at each
  step.
*/

package StacksAndQueues.Easy;

import java.util.Arrays;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find an integer array denoting the final state of nums after
  // performing all k operations
  public int[] getFinalState(int[] nums, int k, int multiplier) {
    // Initialize the result array
    int[] result = new int[nums.length];

    // Initialize the min heap
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
      if (a[0] != b[0]) {
        return Integer.compare(a[0], b[0]);
      }
      return Integer.compare(a[1], b[1]);
    });

    // Add the values to the min heap
    for (int i = 0; i < nums.length; i++) {
      minHeap.offer(new int[] { nums[i], i });
    }

    // Preform the k operations in the array
    for (int i = 0; i < k; i++) {
      // Get the best candidate
      int[] arr = minHeap.poll();

      // Add the new candidate
      minHeap.offer(new int[] { arr[0] * multiplier, arr[1] });
    }

    // Fill the result array
    for (int i = 0; i < nums.length; i++) {
      // Get the top value of the minHeap
      int[] arr = minHeap.poll();

      // Add the result to the correct index
      result[arr[1]] = arr[0];
    }

    // Return the result array
    return result;
  }
}

// Main Class
public class _3264_Final_Array_State_After_K_Multiplication_Operations_I {
  // Main method to test getFinalState
  public static void main(String[] args) {
    int[] nums = new int[] { 2, 1, 3, 5, 6 };
    int k = 5;
    int multiplier = 3;

    int[] result = new Solution().getFinalState(nums, k, multiplier);

    System.out.println("An integer array denoting the final state of nums after performing all k operations is : "
        + Arrays.toString(result));
  }
}
