/*
LeetCode Problem: https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/

Question: 3243. Shortest Distance After Road Addition Queries I

Problem Statement: You are given an integer n and a 2D integer array queries.

There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.

Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.

Example 1:
Input: n = 5, queries = [[2,4],[0,2],[0,4]]
Output: [3,2,1]
Explanation:
After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.

Example 2:
Input: n = 4, queries = [[0,3],[0,2]]
Output: [1,1]
Explanation:
After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
After the addition of the road from 0 to 2, the length of the shortest path remains 1.

Constraints:
3 <= n <= 500
1 <= queries.length <= 500
queries[i].length == 2
0 <= queries[i][0] < queries[i][1] < n
1 < queries[i][1] - queries[i][0]
There are no repeated roads among the queries.
*/

/*
Approach: Incremental Graph BFS After Each Query
Goal:
- After adding each query edge, find the shortest
  path (in hops) from node 0 to node n-1 in the
  updated directed graph.
Core Idea:
- Start with a linear chain 0->1->2->...->n-1.
  Each query adds a shortcut edge. BFS from node 0
  after each insertion gives the current shortest
  path length.
- BFS naturally counts levels (hops); stopping
  when n-1 is first reached gives the minimum.
Algorithm Steps:
1. Initialize adjLists with the linear chain.
2. For each query (u, v):
   a. Add edge u -> v to adjLists[u].
   b. BFS from node 0 level by level:
      - Track path = 0; increment after each level.
      - When n-1 is dequeued, record path and stop.
   c. Store result[i] = path.
3. Return result.
Time Complexity:
- O(q * (n + q)) where q is the number of queries;
  each BFS runs on a graph with n + (queries so
  far) edges.
Space Complexity:
- O(n + q) for the adjacency list and O(n) per BFS
  for the seen array and queue.
Result:
- Returns shortest path length from 0 to n-1 after
  each query.
*/

package Graphs.Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the length of the shortest path from city 0 to city n - 1
  // after processing the first i + 1 queries
  public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
    // Initialize the result array
    int[] result = new int[queries.length];

    // Initialize the array of arraylist
    ArrayList<Integer>[] adjLists = new ArrayList[n];

    // Initialize the adjLists all index
    for (int i = 0; i < n; i++) {
      adjLists[i] = new ArrayList<>();
    }

    // Add the neighbours of the adjLists
    for (int i = 0; i < n - 1; i++) {
      adjLists[i].add(i + 1);
    }

    // Iterate over the queries
    for (int i = 0; i < queries.length; i++) {
      // Add the new edge to the adjLists
      adjLists[queries[i][0]].add(queries[i][1]);

      // Initialize the pathLength variable
      int pathLength = 1;

      // Initialize the queue for the bfs
      Queue<Integer> queue = new ArrayDeque<>();

      // Initialize the booelan array seen
      boolean[] seen = new boolean[n];

      // Offer the zero to the queue
      queue.offer(0);

      // Set the 0 value to seen
      seen[0] = true;

      // Iterate till the queue is not empty
      while (!queue.isEmpty()) {
        // Iterate over the size of the queue
        int size = queue.size();

        for (int j = 0; j < size; j++) {
          // Get the node from the queue
          int node = queue.poll();

          // Set the node value if node is the last node
          if (node == n - 1) {
            result[i] = pathLength;
            break;
          }

          // Add the neighbour to the queue
          for (int neighbour : adjLists[node]) {
            if (!seen[neighbour]) {
              seen[neighbour] = true;
              queue.offer(neighbour);
            }
          }
        }

        // If seen[n - 1] is true then break out of the loop
        if (seen[n - 1]) {
          break;
        }

        // Increment the pathLength
        pathLength++;
      }

      // Set the result[i] to pathLength
      result[i] = pathLength;
    }

    // Return the result
    return result;
  }
}

public class _3243_Shortest_Distance_After_Road_Addition_Queries_I {
  // Main method to test shortestDistanceAfterQueries
  public static void main(String[] args) {
    int n = 5;
    int[][] queries = new int[][] { { 2, 4 }, { 0, 2 }, { 0, 4 } };

    int[] result = new Solution().shortestDistanceAfterQueries(n, queries);

    System.out.println(
        "The length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries is : "
            + Arrays.toString(result));
  }
}
