/*
LeetCode Problem: https://leetcode.com/problems/find-eventual-safe-states/

Question: 802. Find Eventual Safe States

Problem Statement: There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Example 1:
Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

Example 2:
Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.

Constraints:
n == graph.length
1 <= n <= 10^4
0 <= graph[i].length <= n
0 <= graph[i][j] <= n - 1
graph[i] is sorted in a strictly increasing order.
The graph may contain self-loops.
The number of edges in the graph will be in the range [1, 4 * 10^4].
*/

/*
Approach: DFS with Cycle Detection and Memoized Safety Marking
Goal:
- Find all safe nodes: nodes from which every path
  leads to a terminal node with no cycles reachable.
Core Idea:
- A node is safe if and only if all its neighbors
  are safe.
- Per-call visited[] detects back edges (cycles)
  within the current DFS path.
- Boolean[] seen caches results (null = uncomputed,
  true = safe, false = unsafe) to avoid redundant
  recomputation.
Algorithm Steps:
1. For each node i, call dfs(i, ...) and add i to
   result if it returns true.
2. In dfs(node, graph, seen, visited):
   a. If seen[node] != null, return cached result.
   b. If visited[node], cycle detected; return
      seen[node] = false.
   c. Mark visited[node] = true.
   d. For each neighbor, recurse with &=; break
      early if any neighbor is unsafe.
   e. Return seen[node] = canReachTerminal.
3. Return result.
Why It Works:
- visited[] is per-call so it only tracks the
  current DFS path, correctly identifying back
  edges without polluting results from earlier
  independent DFS calls.
- Memoization bounds total work to O(V + E) across
  all calls.
Time Complexity:
- O(V + E)
Space Complexity:
- O(V) for seen[], O(V) per call for visited[],
  O(V) call stack depth worst case.
Result:
- Returns sorted list of all safe node indices.
*/

package Graphs.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Method to find an array containing all the safe nodes of the graph
  public ArrayList<Integer> eventualSafeNodes(int[][] graph) {
    // Initialize the length of the graph
    int length = graph.length;

    // Initialize the boolean array for the seen set
    Boolean[] seen = new Boolean[length];

    // Iterate over the length of the graph node
    for (int i = 0; i < length; i++) {
      this.dfs(i, graph, seen, new boolean[length]);
    }

    // Initialize the Arraylist for the result
    ArrayList<Integer> result = new ArrayList<>();

    // Iterate over the seen array and add only those value which can reach terminal
    for (int i = 0; i < length; i++) {
      // If seen is true then return the result
      if (seen[i]) {
        result.add(i);
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private boolean dfs(int node, int[][] graph, Boolean[] seen, boolean[] visited) {
    // If we have seen the value then return the value
    if (seen[node] != null) {
      return seen[node];
    }

    // Update the visited
    if (visited[node]) {
      return seen[node] = false;
    } else {
      visited[node] = true;
    }

    // Initialize the canReachTerminal boolean variable
    boolean canReachTerminal = true;

    // Iterate over the neighbours
    for (int i = 0; i < graph[node].length; i++) {
      // Update the canReachTerminal
      canReachTerminal &= this.dfs(graph[node][i], graph, seen, visited);

      // Break if canReachTerminal is false
      if (!canReachTerminal) {
        break;
      }
    }

    // Return the seen[node]
    return seen[node] = canReachTerminal;
  }
}

public class _802_Find_Eventual_Safe_States {
  // Main method to test eventualSafeNodes
  public static void main(String[] args) {
    int[][] graph = new int[][] { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };

    ArrayList<Integer> result = new Solution().eventualSafeNodes(graph);

    System.out.println("An array containing all the safe nodes of the graph is : " + result);
  }
}
