/*
LeetCode Problem: https://leetcode.com/problems/minimum-interval-to-include-each-query/

Question: 1851. Minimum Interval to Include Each Query

Problem Statement: You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.

You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.

Return an array containing the answers to the queries.

Example 1:
Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
Output: [3,3,1,4]
Explanation: The queries are processed as follows:
- Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
- Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
- Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
- Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.

Example 2:
Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
Output: [2,-1,4,6]
Explanation: The queries are processed as follows:
- Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
- Query = 19: None of the intervals contain 19. The answer is -1.
- Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
- Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.

Constraints:

1 <= intervals.length <= 10^5
1 <= queries.length <= 10^5
intervals[i].length == 2
1 <= lefti <= righti <= 10^7
1 <= queries[j] <= 10^7
*/

/*
Approach: Sort + Min Heap + Offline Query Processing

Goal:
For each query point, find the smallest interval that fully contains it.

Key Insight:
Processing queries in sorted order allows us to push only relevant intervals
into a min heap as we move from smaller to larger query values.

Steps:

1. Sort intervals by their start value.
2. Sort queries independently but process them in increasing order (offline queries).
3. Use a min heap where each entry = { intervalLength, intervalRightBound }.
   - The heap always keeps intervals that can still cover the current query.
   - Smallest interval length stays at the top.

4. For each sorted query:
   - Push all intervals whose start ≤ query into the heap.
   - Pop from the heap all intervals whose end < query (they can't cover the query).
   - The top of the heap now gives the smallest interval covering the query:
       → if heap empty → answer is -1
       → else → answer is heap.peek()[0]

5. Use a hashmap to store results because queries were sorted.
6. Build the final result array in the original query order.

Why It Works:
- Sorting ensures intervals enter the heap exactly when needed.
- The heap ensures fast retrieval of the shortest valid interval.
- Each interval is pushed and popped at most once → efficient overall.

Time Complexity:
- Sorting intervals: O(n log n)
- Sorting queries: O(m log m)
- Heap operations: O((n + m) log n)
Space Complexity: O(n + m)
*/

package Arrays.Hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class _1851_Minimum_Interval_to_Include_Each_Query {
    // Method to find an array containing the answers to the queries
    public static int[] minInterval(int[][] intervals, int[] queries) {
        // Sort the intervals on the basis of the left value
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Initialize the min heap to store the interval and right value of
        // the interval so that the tie breaker can occur
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Initialize the hash map to store the result of the quries in the relative
        // order
        HashMap<Integer, Integer> resultHashMap = new HashMap<>();

        // Initialize the index variable to get the correct index and get the query
        // length and the interval length
        int index = 0, queryLength = queries.length, intervalsLength = intervals.length;

        // Iterate over the sorted queries to find the minimum interval for that queries
        for (int query : Arrays.stream(queries).sorted().toArray()) {
            // Find the smallest interval in the intervals
            while (index < intervalsLength && intervals[index][0] <= query) {
                // Get the left and right of the interval
                int left = intervals[index][0];
                int right = intervals[index][1];

                // Add the interval and the right value to the min heap
                minHeap.offer(new int[] { right - left + 1, right });

                // Increment the index
                index++;
            }

            // Get the minimum interval from the min heap
            while (!minHeap.isEmpty() && minHeap.peek()[1] < query) {
                // Remove the top element from the min heap
                minHeap.poll();
            }

            // Add the interval to the hashmap if hashmap is empty then add the -1 to the
            // hashmap
            resultHashMap.put(query, minHeap.isEmpty() ? -1 : minHeap.peek()[0]);
        }

        // Initialize the answers array equal to the length of the queries array
        int[] answers = new int[queryLength];

        // Fill the answers array
        for (int i = 0; i < queryLength; i++) {
            answers[i] = resultHashMap.get(queries[i]);
        }

        // Return the answers array
        return answers;
    }

    // Main method to test the minInterval
    public static void main(String[] args) {
        int[][] intervals = new int[][] { { 1, 4 }, { 2, 4 }, { 3, 6 }, { 4, 4 } };
        int[] queries = new int[] { 2, 3, 4, 5 };

        int[] result = minInterval(intervals, queries);

        System.out.println("An array containing the answers to the queries is : " + Arrays.toString(result));
    }
}
