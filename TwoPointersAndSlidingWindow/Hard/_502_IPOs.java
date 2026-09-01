/*
LeetCode Problem: https://leetcode.com/problems/minimum-cost-to-hire-k-workers/

Question: 857. Minimum Cost to Hire K Workers

Problem Statement: There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

    Every worker in the paid group must be paid at least their minimum wage expectation.
    In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.

Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input: quality = [10,20,5], wage = [70,50,30], k = 2
Output: 105.00000
Explanation: We pay 70 to 0th worker and 35 to 2nd worker.

Example 2:
Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
Output: 30.66667
Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.

Constraints:
    n == quality.length == wage.length
    1 <= k <= n <= 10^4
    1 <= quality[i], wage[i] <= 10^4
*/

/*
Approach: Sort by Wage-to-Quality Ratio with Sliding K-Window Max-Heap
Goal:
- Hire exactly k workers to minimize total wage,
  where every hired worker must be paid at least
  their own wage and all workers in the group must
  be paid in proportion to their quality.
Core Idea:
- For any group of k workers, the total wage equals
  totalQuality * ratio_of_the_captain, where the
  captain is the worker with the highest
  wage/quality ratio in the group (since every
  worker must receive at least their minimum ratio,
  the captain's ratio sets the floor for all).
- Sorting workers by ratio ascending means when
  processing worker i, workers[i][0] is the maximum
  ratio in any group drawn from indices 0..i, making
  it the valid captain ratio for that group.
- A max-heap of size k on quality tracks the k
  lowest-quality workers seen so far, minimizing
  totalQuality for each candidate captain ratio.
Algorithm Steps:
1. Build workers[][] as {wage/quality, quality}
   pairs and sort ascending by ratio.
2. Initialize a max-heap, totalQuality = 0,
   result = Double.MAX_VALUE.
3. For each worker in sorted order:
   a. Add worker's quality to totalQuality and
      push it onto the max-heap.
   b. If heap size exceeds k, evict the largest
      quality (poll from max-heap) and subtract it
      from totalQuality.
   c. If heap size == k, update result =
      min(result, totalQuality * worker ratio),
      since worker ratio is the group's captain
      ratio.
4. Return result.
Why It Works:
- Ascending ratio sort guarantees the current
  worker has the highest ratio in any group
  formed from workers processed so far, correctly
  setting the captain's wage multiplier.
- The max-heap evicts the highest-quality worker
  when the window exceeds k, keeping totalQuality
  minimal for each candidate captain, which
  directly minimizes the cost formula.
- Every possible optimal group is evaluated: the
  worker contributing the captain ratio is always
  the pivot when processed, and the heap holds the
  best k qualities to pair with it.
Time Complexity:
- O(n log n)
for sorting and O(n log k) for heap operations,
overall O(n log n).
Space Complexity:
- O(n)
for the workers array and O(k) for the max-heap.
Result:
- Returns the minimum total wage to hire exactly
  k workers satisfying the proportional pay
  constraint.
*/

package TwoPointersAndSlidingWindow.Hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find the least amount of money needed to form a paid group
  // satisfying
  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    // Initialize the length variable
    int length = quality.length;

    // Initialize the result and totalQuality
    double result = Double.MAX_VALUE;
    double totalQuality = 0;

    // Initialzie the double array
    double[][] workers = new double[length][2];

    // Fill the workers array
    for (int i = 0; i < length; i++) {
      workers[i] = new double[] { (double) wage[i] / quality[i], (double) quality[i] };
    }

    // Sort the workers array on the ratio
    Arrays.sort(workers, Comparator.comparingDouble(a -> a[0]));

    // Initialize the maxHeap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    // Iterate over the worker array
    for (double[] worker : workers) {
      // Get the ratio and quality
      double ratio = worker[0];
      int q = (int) worker[1];

      // Add the quality to the maxHeap
      maxHeap.offer(q);

      // Update the total quality
      totalQuality += q;

      // If size of maxHeap is more than k then pop from the heap
      if (maxHeap.size() > k) {
        // Update the total quality
        totalQuality -= maxHeap.poll();
      }

      // If maxHeap is equal to size k then update the result
      if (maxHeap.size() == k) {
        result = Math.min(result, totalQuality * ratio);
      }
    }

    // Return the result
    return result;
  }
}

public class _502_IPOs {
  // Main method to test mincostToHireWorkers
  public static void main(String[] args) {
    int[] quality = new int[] { 3, 1, 10, 10, 1 };
    int[] wage = new int[] { 4, 8, 2, 2, 7 };
    int k = 3;

    double result = new Solution().mincostToHireWorkers(quality, wage, k);

    System.out.println("The least amount of money needed to form a paid group satisfying is : " + result);
  }
}
