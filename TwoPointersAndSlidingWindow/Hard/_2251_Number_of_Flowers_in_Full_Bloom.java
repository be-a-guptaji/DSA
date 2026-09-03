/*
LeetCode Problem: https://leetcode.com/problems/number-of-flowers-in-full-bloom/

Question: 2251. Number of Flowers in Full Bloom

Problem Statement: You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.

Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.

Example 1:
Input: flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
Output: [1,2,2,2]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.

Example 2:
Input: flowers = [[1,10],[3,3]], people = [3,3,2]
Output: [2,2,1]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.

Constraints:
    1 <= flowers.length <= 5 * 10^4
    flowers[i].length == 2
    1 <= starti <= endi <= 10^9
    1 <= people.length <= 5 * 1064
    1 <= people[i] <= 10^9
*/

/*
Approach: Sort People and Flowers with Event-driven Min-Heap
Goal:
- For each person arriving at time t, count how
  many flowers are in full bloom at exactly time t
  (bloom start <= t <= bloom end).
Core Idea:
- A flower is blooming at time t if its start <= t
  and its end >= t.
- Sort people by arrival time to process them in
  order; sort flowers by start time to efficiently
  add flowers that have started blooming by each
  person's arrival.
- Use a min-heap of end times to track currently
  active flowers; evict flowers whose end time < t
  (already wilted) before counting.
- Since people are queried out of original order
  after sorting, pair each person with their
  original index to place answers correctly.
Algorithm Steps:
1. Pair each person with their original index in
   indexedPeople, then sort by arrival time.
2. Sort flowers by start time ascending.
3. Initialize an end-time min-heap and a flower
   pointer j = 0.
4. For each (person, index) in sorted order:
   a. Add all flowers with start <= person to the
      heap (push their end times); advance j.
   b. Evict all heap entries with end < person
      (flowers that have already wilted).
   c. result[index] = heap size (flowers currently
      in bloom for this person).
5. Return result.
Why It Works:
- Sorting people enables a single left-to-right
  sweep through flowers without backtracking,
  since each successive person arrives no earlier
  than the previous.
- The min-heap surfaces the earliest-ending active
  flower, making expired flower eviction O(log n)
  per eviction rather than a full scan.
- After adding all started flowers and removing all
  wilted ones, the heap size is exactly the count
  of flowers blooming at the current person's time.
Time Complexity:
- O((n + m) log m)
where n is the number of people and m is the number
of flowers. Sorting is O(m log m + n log n); each
flower is pushed and popped from the heap at most
once, costing O(log m) each.
Space Complexity:
- O(n + m)
for indexedPeople, the sorted flowers array, and
the min-heap.
Result:
- Returns an array where result[i] is the number
  of flowers in bloom when person i arrives.
*/

package TwoPointersAndSlidingWindow.Hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Method to find an integer array answer of size n, where answer[i] is the
  // number of flowers that are in full bloom when the ith person arrives
  public int[] fullBloomFlowers(int[][] flowers, int[] people) {
    // Initialize the length array
    int length = people.length;

    // Initialize the result variable array
    int[] result = new int[length];

    // Initialize the indexedPeople array
    int[][] indexedPeople = new int[length][2];

    // Fill the indexedPeople
    for (int i = 0; i < length; i++) {
      indexedPeople[i] = new int[] { people[i], i };
    }

    // Sort the indexedPeople and the flowers array
    Arrays.sort(indexedPeople, Comparator.comparingInt(a -> a[0]));
    Arrays.sort(flowers, Comparator.comparingInt(a -> a[0]));

    // Initialize the min heap for the ending time
    PriorityQueue<Integer> endHeap = new PriorityQueue<>();

    // Iterate over the indexedPeople
    for (int i = 0, j = 0, n = flowers.length; i < length; i++) {
      // Initialize the people and index variable
      int person = indexedPeople[i][0];
      int index = indexedPeople[i][1];

      // Fill the endHeap
      while (j < n && flowers[j][0] <= person) {
        endHeap.offer(flowers[j][1]);
        j++;
      }

      // Remove the time which is passed
      while (!endHeap.isEmpty() && endHeap.peek() < person) {
        endHeap.poll();
      }

      // Update the result array
      result[index] = endHeap.size();
    }

    // Return the result array
    return result;
  }
}

public class _2251_Number_of_Flowers_in_Full_Bloom {
  // Main method to test fullBloomFlowers
  public static void main(String[] args) {
    int[][] flowers = new int[][] { { 1, 6 }, { 3, 7 }, { 9, 12 }, { 4, 13 } };
    int[] people = new int[] { 2, 3, 7, 11 };

    int[] result = new Solution().fullBloomFlowers(flowers, people);

    System.out
        .println(
            "An integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives is : "
                + Arrays.toString(result));
  }
}
