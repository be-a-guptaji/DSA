/*
LeetCode Problem: https://leetcode.com/problems/shortest-path-with-alternating-colors/

Question: 1129. Shortest Path with Alternating Colors

Problem Statement: You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.

Example 1:
Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]

Example 2:
Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]

Constraints:
1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n
*/

/*
Approach: BFS on (node, lastColor) State with Alternating Edge Constraint
Goal:
- For each node, find the shortest path from node 0
  using strictly alternating red and blue edges.
  Return -1 if unreachable.
Core Idea:
- The state is (node, lastColorUsed) since the
  next valid edge color depends on the last edge
  traversed, not just the current node.
- Start BFS from node 0 twice simultaneously: once
  treating the first edge as red, once as blue,
  by seeding both (0, RED) and (0, BLUE) at
  distance 0.
- seen[color][node] prevents revisiting the same
  (node, color) state, bounding BFS to O(n + e).
Algorithm Steps:
1. Build two adjacency lists: adjList[0] for red
   edges, adjList[1] for blue edges.
2. Initialize result[] = MAX_VALUE, seen[2][n].
3. Enqueue {node=0, color=RED} and {node=0,
   color=BLUE}; mark both seen; set result[0] = 0.
4. BFS level by level:
   a. For each (node, color) in the current level:
      - The next edge must use (1 - color).
      - For each neighbor v in adjList[1-color][node]:
        - If not seen[1-color][v]:
          - Mark seen, update result[v] = min(
            result[v], distance), enqueue
            {v, 1-color}.
   b. Increment distance.
5. Replace remaining MAX_VALUE entries with -1.
6. Return result.
Why It Works:
- Seeding both colors at distance 0 from node 0
  covers paths starting with either color in a
  single pass.
- (node, color) state space ensures that a node
  reached via red and the same node reached via
  blue are treated independently, since they allow
  different next edges.
- result[v] is updated on first reach per state,
  which BFS guarantees is the shortest distance.
Time Complexity:
- O(n + e)
where n is the number of nodes and e is the total
number of edges, since each (node, color) state
is enqueued at most once.
Space Complexity:
- O(n + e)
for the adjacency lists, seen array, and queue.
Result:
- Returns shortest alternating-path distances from
  node 0 to all nodes, or -1 where unreachable.
*/

package Graphs.Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find an array answer of length n
  public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
    // Initialize the adj list for the array
    ArrayList<Integer>[][] adjList = new ArrayList[2][n];

    // Initialize the adj list
    for (int i = 0; i < n; i++) {
      adjList[0][i] = new ArrayList<>();
      adjList[1][i] = new ArrayList<>();
    }

    // Initialize the list variable
    ArrayList<Integer>[] list = adjList[0];

    // Fill the adj list
    for (int[] arr : redEdges) {
      list[arr[0]].add(arr[1]);
    }

    // Get the blue list
    list = adjList[1];

    // Fill the adj list
    for (int[] arr : blueEdges) {
      list[arr[0]].add(arr[1]);
    }

    // Initialize the result array
    int[] result = new int[n];

    // Fill the result array to infinity
    Arrays.fill(result, Integer.MAX_VALUE);

    // Initialize the seen array
    boolean[][] seen = new boolean[2][n];

    // Initialize the boolean for the edges
    boolean isRed = true;

    // Initialize the distance variable
    int distance = 0;

    // Initialize the queue for the bfs
    Queue<Integer> queue = new ArrayDeque<>();

    // Add 0 to the queue
    queue.offer(0);

    // Iterate over the queue
    while (!queue.isEmpty()) {
      // Initialize the size variable
      int size = queue.size();

      // Get the color
      int color = isRed ? 0 : 1;

      // Get the color list
      list = adjList[color];

      // Iterate over the level
      for (int i = 0; i < size; i++) {
        // Get the value from the queue
        int value = queue.poll();

        // If we have seen it then skip the iteration
        if (seen[color][value]) {
          continue;
        }

        // Set the seen value to true
        seen[color][value] = true;

        // Set the result variable
        result[value] = Math.min(result[value], distance);

        // Add the edges to the queue
        for (int v : list[value]) {
          queue.offer(v);
        }
      }

      // Flip the isRed variable
      isRed = !isRed;

      // Increment the distance varaible
      distance++;
    }

    // Initialize the seen array
    seen = new boolean[2][n];

    // Initialize the boolean for the edges
    isRed = false;

    // Initialize the distance variable
    distance = 0;

    // Add 0 to the queue
    queue.offer(0);

    // Iterate over the queue
    while (!queue.isEmpty()) {
      // Initialize the size variable
      int size = queue.size();

      // Get the color
      int color = isRed ? 0 : 1;

      // Get the color list
      list = adjList[color];

      // Iterate over the level
      for (int i = 0; i < size; i++) {
        // Get the value from the queue
        int value = queue.poll();

        // If we have seen it then skip the iteration
        if (seen[color][value]) {
          continue;
        }

        // Set the seen value to true
        seen[color][value] = true;

        // Set the result variable
        result[value] = Math.min(result[value], distance);

        // Add the edges to the queue
        for (int v : list[value]) {
          queue.offer(v);
        }
      }

      // Flip the isRed variable
      isRed = !isRed;

      // Increment the distance varaible
      distance++;
    }

    // Reset the Integer.MAX_VALUE
    for (int i = 0; i < n; i++) {
      if (result[i] == Integer.MAX_VALUE) {
        result[i] = -1;
      }
    }

    // Return the result
    return result;
  }
}

public class _1129_Shortest_Path_with_Alternating_Colors {
  // Main method to test shortestAlternatingPaths
  public static void main(String[] args) {
    int n = 3;
    int[][] redEdges = new int[][] { { 0, 1 }, { 1, 2 } };
    int[][] blueEdges = new int[][] {};

    int[] result = new Solution().shortestAlternatingPaths(n, redEdges, blueEdges);

    System.out.println("An array answer of length n is : " + Arrays.toString(result));
  }
}
