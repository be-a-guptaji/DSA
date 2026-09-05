/*
LeetCode Problem: https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/

Question: 2940. Find Building Where Alice and Bob Can Meet

Problem Statement: You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.

If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].

You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai while Bob is in building bi.

Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.

Example 1:
Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
Output: [2,5,-1,5,2]
Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2]. 
In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5]. 
In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
In the fifth query, Alice and Bob are already in the same building.  
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

Example 2:
Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
Output: [7,6,-1,4,6]
Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

Constraints:
    1 <= heights.length <= 5 * 10^4
    1 <= heights[i] <= 10^9
    1 <= queries.length <= 5 * 10^4
    queries[i] = [ai, bi]
    0 <= ai, bi <= heights.length - 1
*/

/*
Approach: Offline Query Processing with Right-Anchor Grouping and Min-Heap
Goal:
- For each query (a, b), find the leftmost building
  index >= max(a, b) where both Alice and Bob can
  meet, meaning the building height strictly exceeds
  both heights[a] and heights[b].
Core Idea:
- Normalize each query so left <= right. Two cases
  resolve immediately without scanning:
  1. left == right: they are already at the same
     building; answer is right.
  2. heights[right] > heights[left]: right itself
     is tall enough for both; answer is right.
- Remaining queries need the first index > right
  with height > heights[left] (since heights[right]
  <= heights[left], the meeting point must be
  strictly to the right and strictly taller than
  heights[left]).
- Group unresolved queries by their right anchor
  index. Sweep heights left to right; when reaching
  index i, activate all queries anchored at i, then
  resolve any query whose required height threshold
  (heights[left]) is exceeded by heights[i].
- A min-heap ordered by required height lets the
  sweep resolve all satisfiable queries at each
  index in O(log n) per resolution.
Algorithm Steps:
1. Normalize each query to (left, right) with
   left <= right; resolve trivial cases directly.
2. Group remaining queries by right index into a
   HashMap: right -> list of {heights[left], query
   index}.
3. Sweep i from 0 to heights.length - 1:
   a. If group contains i, push all its queries
      onto the min-heap (keyed by required height).
   b. While min-heap is non-empty and heights[i]
      > heap top's required height: pop and set
      result[query index] = i.
4. Return result.
Why It Works:
- Grouping by right anchor ensures queries only
  enter the heap when the sweep reaches their
  earliest valid candidate position.
- The min-heap surfaces the query with the lowest
  height requirement first; once heights[i] exceeds
  it, all queries with lower or equal thresholds
  are also satisfied and can be resolved in order.
- Unresolved queries remaining in the heap after
  the sweep have no valid building and keep their
  default value of -1.
Time Complexity:
- O((n + q) log q)
where n is the number of buildings and q is the
number of queries. Each query is pushed and popped
from the heap at most once, costing O(log q) each.
Space Complexity:
- O(n + q)
for the group map, heap, and result array.
Result:
- Returns result[i] as the leftmost valid meeting
  building index for query i, or -1 if none exists.
*/

package StacksQueuesAndHeaps.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

// Solution Class 
class Solution {
  // Method to find an array ans where ans[i] is the index of the leftmost
  // building where Alice and Bob can meet on the ith query
  public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
    // Initialize the result array
    int[] result = new int[queries.length];

    // Fill the arrays to -1
    Arrays.fill(result, -1);

    // Initialize the hashmap
    HashMap<Integer, ArrayList<int[]>> group = new HashMap<>();

    // Iterate over the queries
    for (int i = 0; i < queries.length; i++) {
      // Get the left and right query
      int left, right;

      // Set the left and right varaible
      if (queries[i][0] <= queries[i][1]) {
        left = queries[i][0];
        right = queries[i][1];
      } else {
        left = queries[i][1];
        right = queries[i][0];
      }

      // Fill the result or fill the group
      if (left == right || heights[right] > heights[left]) {
        result[i] = right;
      } else {
        group
            .computeIfAbsent(right, k -> new ArrayList<>())
            .add(new int[] { heights[left], i });
      }
    }

    // Initialize the minHeap for the height and the index
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(a[0], b[0]));

    // Iterate over the heights
    for (int i = 0; i < heights.length; i++) {
      // Add the value to the minHeap
      if (group.containsKey(i)) {
        for (int[] query : group.get(i)) {
          minHeap.offer(query);
        }
      }

      // Find the leftmost building that satisfies the query
      while (!minHeap.isEmpty() && heights[i] > minHeap.peek()[0]) {
        int[] query = minHeap.poll();

        // Set the result for the query
        result[query[1]] = i;
      }
    }

    // Return the result array
    return result;
  }
}

public class _2940_Find_Building_Where_Alice_and_Bob_Can_Meet {
  // Main method to test leftmostBuildingQueries
  public static void main(String[] args) {
    int[] heights = new int[] { 6, 4, 8, 5, 2, 7 };
    int[][] queries = new int[][] { { 0, 1 }, { 0, 3 }, { 2, 4 }, { 3, 4 }, { 2, 2 } };

    int[] result = new Solution().leftmostBuildingQueries(heights, queries);

    System.out.println(
        "An array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query is : "
            + Arrays.toString(result));
  }
}
