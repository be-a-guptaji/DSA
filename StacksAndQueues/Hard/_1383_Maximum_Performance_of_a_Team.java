/*
LeetCode Problem: https://leetcode.com/problems/maximum-performance-of-a-team/

Question: 1383. Maximum Performance of a Team

Problem Statement: You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.

Choose at most k different engineers out of the n engineers to form a team with the maximum performance.

The performance of a team is the sum of its engineers' speeds multiplied by the minimum efficiency among its engineers.

Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.

Example 1:
Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
Output: 60
Explanation: 
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.

Example 2:
Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
Output: 68
Explanation:
This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.

Example 3:
Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
Output: 72

Constraints:
    1 <= k <= n <= 10^5
    speed.length == n
    efficiency.length == n
    1 <= speed[i] <= 10^5
    1 <= efficiency[i] <= 10^8
*/

/*
Approach: Sort by Efficiency Descending with Sliding K-Window Min-Heap
Goal:
- Select at most k engineers to maximize team
  performance, defined as total speed multiplied
  by the minimum efficiency in the team.
Core Idea:
- For any team, the minimum efficiency determines
  the multiplier. Sorting engineers by efficiency
  descending means when processing engineer i,
  engineers[i][0] is guaranteed to be the minimum
  efficiency of any team drawn from indices 0..i.
- This reduces the problem to: for each engineer i
  as the mandatory minimum efficiency member, pick
  up to k-1 others from 0..i-1 with the highest
  speeds to maximize total speed.
- A min-heap of size k tracks the top-k speeds
  seen so far, evicting the smallest when the
  window exceeds k.
Algorithm Steps:
1. Build engineers[][] as {efficiency, speed} pairs
   and sort descending by efficiency.
2. Initialize a min-heap, totalSpeed = 0, result = 0.
3. For each engineer i in sorted order:
   a. If heap size == k, evict the minimum speed
      (poll from heap) and subtract it from
      totalSpeed.
   b. Add engineers[i][1] to totalSpeed and push
      it onto the heap.
   c. Update result = max(result, totalSpeed *
      engineers[i][0]), since engineers[i][0] is
      the minimum efficiency for any team ending
      here.
4. Return (int)(result % MOD).
Why It Works:
- Descending efficiency sort guarantees that the
  current engineer's efficiency is the minimum for
  any team formed from the engineers processed so
  far, making it the valid multiplier at each step.
- The min-heap maintains the top-k speeds among
  all engineers seen, ensuring totalSpeed is
  maximized for each candidate minimum efficiency.
- Every possible optimal team is evaluated: the
  engineer contributing the minimum efficiency will
  be the pivot when processed, and the heap holds
  the best up to k-1 accompanying speeds.
Time Complexity:
- O(n log n)
for sorting and O(n log k) for heap operations,
overall O(n log n).
Space Complexity:
- O(n)
for the engineers array and O(k) for the min-heap.
Result:
- Returns the maximum team performance modulo
  1e9 + 7.
*/

package StacksAndQueues.Hard;

import java.util.Arrays;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the maximum performance of this team
  public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    // Initialize the engineers matrix
    int[][] engineers = new int[n][2];

    // Fill the engineers array
    for (int i = 0; i < n; i++) {
      engineers[i] = new int[] { efficiency[i], speed[i] };
    }

    // Sort the array
    Arrays.sort(engineers, (a, b) -> b[0] - a[0]);

    // Initialize the minHeap for the speed
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // Initialize the totalSpeed and result variable
    long totalSpeed = 0;
    long result = 0;

    // Iterate over the engineers array
    for (int i = 0; i < n; i++) {
      // If minHeap size is equal to k then remove the value for the minHeap
      if (minHeap.size() == k) {
        totalSpeed -= minHeap.poll();
      }

      // Update the totalSpeed
      totalSpeed += engineers[i][1];

      // Add the speed value to the minHeap
      minHeap.offer(engineers[i][1]);

      // Update the result
      result = Math.max(result, totalSpeed * engineers[i][0]);
    }

    // Return the result
    return (int) result % 1_000_000_007;
  }
}

// Main Class
public class _1383_Maximum_Performance_of_a_Team {
  // Main method to test maxPerformance
  public static void main(String[] args) {
    int n = 6;
    int[] speed = new int[] { 2, 10, 3, 1, 5, 8 };
    int[] efficiency = new int[] { 5, 4, 3, 9, 7, 2 };
    int k = 2;

    int result = new Solution().maxPerformance(n, speed, efficiency, k);

    System.out.println("The maximum performance of this team is : " + result);
  }
}
