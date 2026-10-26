/*
LeetCode Problem: https://leetcode.com/problems/largest-color-value-in-a-directed-graph/

Question: 1857. Largest Color Value in a Directed Graph

Problem Statement: There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

Example 1:
Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

Example 2:
Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.

Constraints:
n == colors.length
m == edges.length
1 <= n <= 10^5
0 <= m <= 10^5
colors consists of lowercase English letters.
0 <= aj, bj < n
*/

/*
Approach: DFS with Cycle Detection and Color Frequency DP
Goal:
- Find the maximum frequency of any single color
  along any valid path in the directed graph, or
  return -1 if a cycle exists.
Core Idea:
- dp[node][c] = maximum count of color c on any
  path starting from node and going downstream.
- DFS computes dp bottom-up: at each node, merge
  children's dp rows by taking the max, then add 1
  for the current node's own color.
- Two visited markers are needed:
  - path[]: tracks the current DFS call stack for
    cycle detection (back edge = cycle).
  - seen[]: marks fully resolved nodes for
    memoization, skipping recomputation.
Algorithm Steps:
1. Build directed adjacency list from edges.
2. Initialize dp[n][26] = 0.
3. For each unvisited node, call dfs(node):
   a. If path[node] == true, cycle detected;
      return MAX_VALUE.
   b. If seen[node] == true, already resolved;
      return 0.
   c. Mark path[node] = seen[node] = true.
   d. Set dp[node][color(node)] = 1.
   e. For each neighbor, recurse; if MAX_VALUE
      returned, propagate immediately.
   f. Merge: dp[node][c] = max(dp[node][c],
      dp[nei][c] + (c == color(node) ? 1 : 0)).
   g. Unmark path[node] = false (backtrack).
4. Track global max across all dp[node][c].
5. Return result or -1 on cycle.
Why It Works:
- path[] detects back edges (cycles) by checking
  if a node is currently in the active DFS stack.
- seen[] enables memoization: once a node's dp row
  is fully computed, it is reused by other paths
  that reach it, preventing redundant traversal.
- The merge formula correctly counts the current
  node's color on top of each neighbor's path.
Time Complexity:
- O(V * 26 + E * 26) = O((V + E) * 26) = O(V + E)
  since 26 is constant; each node and edge is
  processed once with a 26-iteration inner loop.
Space Complexity:
- O(V * 26) for dp, O(V) for seen/path arrays and
  call stack.
Result:
- Returns the largest color value on any valid
  path, or -1 if the graph contains a cycle.
*/

package Graphs.Hard;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Initialize the node varaible
  private int nodes;

  // Initialize the adjList
  private ArrayList<Integer>[] adjList;

  // Initialize the seen array
  private boolean[] seen;

  // Initialize the path array
  private boolean[] path;

  // Initialize the dp matrix for the color
  private int[][] dp;

  // Method to find the largest color value of any valid path in the given graph
  public int largestPathValue(String colors, int[][] edges) {
    // Initialize the nodes variable
    this.nodes = colors.length();

    // Initialize the adjList
    this.adjList = new ArrayList[this.nodes];

    // Initialize the seen array
    this.seen = new boolean[this.nodes];

    // Initialize the path array
    this.path = new boolean[this.nodes];

    // Initialize the dp matrix for the color
    this.dp = new int[this.nodes][26];

    // Initialize the character array of colors
    char[] colours = colors.toCharArray();

    // Initialize the adjList
    for (int i = 0; i < this.nodes; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Make the adjList
    for (int i = 0; i < edges.length; i++) {
      adjList[edges[i][0]].add(edges[i][1]);
    }

    // Initialize the result variable
    int result = 0;

    // Iterate over the nodes
    for (int node = 0; node < this.nodes; node++) {
      // Get the dfs value of the nodes and colors
      if (this.dfs(node, colours) == Integer.MAX_VALUE) {
        return -1;
      }

      // Get max color of the node
      for (int color = 0; color < 26; color++) {
        result = Math.max(result, dp[node][color]);
      }
    }

    // Return the result variable
    return result;
  }

  // Helper method for the dfs
  private int dfs(int node, char[] colours) {
    // If path is seen then return Integer.MAX_VALUE
    if (this.path[node]) {
      return Integer.MAX_VALUE;
    }

    // If we have seen the value then reutrn 0
    if (this.seen[node]) {
      return 0;
    }

    // Set the seen and path node to true
    this.path[node] = true;
    this.seen[node] = true;

    // Initialize the colorIndex
    int colorIndex = colours[node] - 'a';

    // Set the dp[node][colorIndex] to 1
    this.dp[node][colorIndex] = 1;

    // Iterate over all the nodes neighbours
    for (int nei : this.adjList[node]) {
      // Make a dfs call on the neighbours
      if (this.dfs(nei, colours) == Integer.MAX_VALUE) {
        return Integer.MAX_VALUE;
      }

      // Fill the dp matrix
      for (int color = 0; color < 26; color++) {
        this.dp[node][color] = Math.max(this.dp[node][color], (color == colorIndex ? 1 : 0) + this.dp[nei][color]);
      }
    }

    // Set the path[node] to false
    this.path[node] = false;

    // Return 0 in the end
    return 0;
  }
}

public class _1857_Largest_Color_Value_in_a_Directed_Graph {
  // Main method to test largestPathValue
  public static void main(String[] args) {
    String colors = "abaca";
    int[][] edges = new int[][] { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 3, 4 } };

    int result = new Solution().largestPathValue(colors, edges);

    System.out.println("The largest color value of any valid path in the given graph is : " + result);
  }
}
