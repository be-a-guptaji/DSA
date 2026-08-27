/*
LeetCode Problem: https://leetcode.com/problems/minimize-deviation-in-array/

Question: 1675. Minimize Deviation in Array

Problem Statement: You are given an array nums of n positive integers.

You can perform two types of operations on any element of the array any number of times:

    If the element is even, divide it by 2.
        For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
    If the element is odd, multiply it by 2.
        For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].

The deviation of the array is the maximum difference between any two elements in the array.

Return the minimum deviation the array can have after performing some number of operations.

Example 1:
Input: nums = [1,2,3,4]
Output: 1
Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.

Example 2:
Input: nums = [4,1,5,20,3]
Output: 3
Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.

Example 3:
Input: nums = [2,10,8]
Output: 3

Constraints:
    n == nums.length
    2 <= n <= 5 * 10^4
    1 <= nums[i] <= 10^9
*/

/*
Approach: Normalize to Odd Base with Min-Heap and Tracked Maximum
Goal:
- Find the minimum possible deviation (max - min)
  achievable by repeatedly halving even numbers or
  doubling odd numbers across the array.
Core Idea:
- Doubling an odd number once makes it even, then
  it can only be halved. Halving an even number
  repeatedly until odd gives its minimum reachable
  value. This means every number has a fixed range:
  [odd_base, max_even_form].
- Normalize every number to its odd base (minimum
  reachable value) by halving repeatedly, while
  recording its maximum reachable value (either the
  original if even, or original * 2 if odd).
- Use a min-heap to always expand the current
  minimum element (multiply by 2), which is the
  only operation that can reduce deviation when
  the minimum is too small relative to the maximum.
- Track the running global maximum across all
  current heap values to compute deviation at each
  step.
- Stop when the minimum element has reached its
  maximum reachable value (cannot be doubled
  further), since further expansion is impossible.
Algorithm Steps:
1. For each nums[i]:
   a. Record temp = nums[i].
   b. Halve nums[i] until it is odd (find odd base).
   c. Push {odd_base, max(temp, nums[i] * 2)} into
      the min-heap, where the second value is the
      maximum reachable form.
   d. Update heapMax with odd_base.
2. While the heap still contains all n elements:
   a. Poll the minimum element {n, nMax}.
   b. Update minimumDeviation = min(minimumDeviation,
      heapMax - n).
   c. If n < nMax (can still be doubled):
      - Push {n * 2, nMax} back into the heap.
      - Update heapMax = max(heapMax, n * 2).
   d. Else: the minimum has reached its ceiling and
      cannot grow; the loop terminates because heap
      size drops below n.
3. Return minimumDeviation.
Why It Works:
- Normalizing to odd base gives each number its
  smallest possible value upfront; all subsequent
  operations only increase values via doubling.
- Greedily doubling the current minimum reduces the
  gap from below, which is the only productive
  move: increasing the minimum decreases deviation
  when the maximum is fixed.
- The heap size invariant (stop when size < n)
  catches when the minimum element is saturated and
  can no longer be doubled, meaning no further
  reduction is possible.
- heapMax is monotonically non-decreasing (doubling
  can only raise it), so tracking it incrementally
  is correct.
Time Complexity:
- O(n log n * log M)
where M is the maximum value in nums. Each element
is doubled at most log M times before reaching its
maximum form, and each heap operation costs O(log n).
Space Complexity:
- O(n)
for the min-heap storing one entry per element.
Result:
- Returns the minimum deviation achievable across
  all valid sequences of operations.
*/

package StacksAndQueues.Hard;

import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the minimum deviation the array can have after performing some
  // number of operations
  public int minimumDeviation(int[] nums) {
    // Initialize the minHeap and maxHeap
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

    // Initialize the heapMax variable
    int heapMax = 0;

    // Fill the heaps
    for (int i = 0; i < nums.length; i++) {
      // Hold the temp value for the the nums[i]
      int temp = nums[i];

      // Decrement the nums[i] if it is even
      while ((temp & 1) == 0) {
        temp >>= 1;
      }

      // Add the value to the min heap
      minHeap.offer(new int[] { temp, Math.max(temp << 1, nums[i]) });

      // Update the heapMax value
      heapMax = Math.max(heapMax, nums[i]);
    }

    // Initialize the minimumDeviation variable
    int minimumDeviation = Integer.MAX_VALUE;

    // Iterate untill heap size is equal to the nums.length
    while (minHeap.size() == nums.length) {
      // Get the minimum value for the heap
      int[] minElement = minHeap.poll();

      // Get the n and its max value
      int n = minElement[0], nMax = minElement[1];

      // Update the minimumDeviation
      minimumDeviation = Math.min(minimumDeviation, heapMax - n);

      // If n is less then the nMax then update the minHeap and the heapMax
      if (n < nMax) {
        // Add the value to the min heap
        minHeap.offer(new int[] { n * 2, nMax });

        // Update the heapMax value
        heapMax = Math.max(heapMax, n * 2);
      }
    }

    // Return the minimumDeviation
    return minimumDeviation;
  }
}

// Main Class
public class _1675_Minimize_Deviation_in_Array {
  // Main method to test minimumDeviation
  public static void main(String[] args) {
    int[] nums = new int[] { 4, 1, 5, 20, 3 };

    int result = new Solution().minimumDeviation(nums);

    System.out
        .println("The minimum deviation the array can have after performing some number of operations is : " + result);
  }
}
