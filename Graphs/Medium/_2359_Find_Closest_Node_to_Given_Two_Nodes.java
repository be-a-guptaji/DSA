/*
LeetCode Problem: https://leetcode.com/problems/find-closest-node-to-given-two-nodes/

Question: 2359. Find Closest Node to Given Two Nodes

Problem Statement: You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.

You are also given two integers node1 and node2.

Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.

Note that edges may contain cycles.

Example 1:
Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
Output: 2
Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.

Example 2:
Input: edges = [1,2,-1], node1 = 0, node2 = 2
Output: 2
Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.

Constraints:
n == edges.length
2 <= n <= 105
-1 <= edges[i] < n
edges[i] != i
0 <= node1, node2 < n
*/

/*
Approach: Linear Chain DFS from Each Source with Min-Max Distance Scan
Goal:
- Find the node reachable from both node1 and node2
  that minimizes the maximum of the two travel
  distances, returning the smallest index on ties.
Core Idea:
- Since each node has at most one outgoing edge,
  the graph is a collection of simple chains and
  rho-shaped (handle + cycle) paths.
- DFS from each source follows the unique outgoing
  edge at each step, populating a distance array
  until a cycle or dead end is hit (detected by
  dist[nei] != -1).
- After both traversals, scan all nodes and pick
  the one reachable from both sources with the
  minimum max-distance; natural left-to-right scan
  order handles tie-breaking by smallest index
  automatically.
Algorithm Steps:
1. Initialize node1Dist[] and node2Dist[] with -1;
   set node1Dist[node1] = 0 and node2Dist[node2]
   = 0.
2. Call dfs(node1, edges, node1Dist) and
   dfs(node2, edges, node2Dist):
   - At each step follow edges[node] and assign
     dist[nei] = dist[node] + 1.
   - Stop if nei == -1 (dead end) or dist[nei] !=
     -1 (already visited, avoids cycle loops).
3. Scan all nodes i:
   - Skip if either distance is -1 (unreachable
     from one source).
   - Compute dist = max(node1Dist[i], node2Dist[i]).
   - Update result if dist < resDist (strict less
     than preserves the smallest index on ties).
4. Return result.
Why It Works:
- The at-most-one-outgoing-edge constraint means
  each DFS is a simple linear walk with no
  branching, making the traversal O(n) per source.
- Checking dist[nei] != -1 before recursing
  correctly handles cycles (rho shapes) by stopping
  when the chain rejoins a previously visited node.
- Strict less-than update in the scan ensures the
  first (smallest index) node with the minimum
  max-distance is always retained.
Time Complexity:
- O(n)
for two linear DFS traversals and one linear scan.
Space Complexity:
- O(n)
for the two distance arrays and O(n) call stack
depth in the worst case of a full-length chain.
Result:
- Returns the index of the optimal meeting node,
  or -1 if no node is reachable from both sources.
*/

package Graphs.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the maximum between the distance from node1 to that node, and
  // from node2 to that node is minimized
  public int closestMeetingNode(int[] edges, int node1, int node2) {
    // Initialize the length of the edges
    int n = edges.length;

    // Initialize the distance array
    int[] node1Dist = new int[n];
    int[] node2Dist = new int[n];

    // Fill array with -1
    Arrays.fill(node1Dist, -1);
    Arrays.fill(node2Dist, -1);

    // Set starting node to -1
    node1Dist[node1] = 0;
    node2Dist[node2] = 0;

    // Call the dfs on both the node
    this.dfs(node1, edges, node1Dist);
    this.dfs(node2, edges, node2Dist);

    // Initialize the result variable
    int result = -1, resDist = Integer.MAX_VALUE;

    // Iterate over the array
    for (int i = 0; i < n; i++) {
      // If no one is -1 then proceeds
      if (Math.min(node1Dist[i], node2Dist[i]) != -1) {
        // Get the max distance
        int dist = Math.max(node1Dist[i], node2Dist[i]);

        // Update the result if resDist is greater than the dist
        if (dist < resDist) {
          resDist = dist;
          result = i;
        }
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private void dfs(int node, int[] edges, int[] dist) {
    // Get the nei of the node
    int nei = edges[node];

    // Skip if nei is -1 and next is -1
    if (nei != -1 && dist[nei] == -1) {
      // Update the distance
      dist[nei] = dist[node] + 1;

      // Call the dfs
      this.dfs(nei, edges, dist);
    }
  }
}

public class _2359_Find_Closest_Node_to_Given_Two_Nodes {
  // Main method to test closestMeetingNode
  public static void main(String[] args) {
    int[] edges = new int[] { 2, 2, 3, -1 };
    int node1 = 0;
    int node2 = 1;

    int result = new Solution().closestMeetingNode(edges, node1, node2);

    System.out.println(
        "The maximum between the distance from node1 to that node, and from node2 to that node is minimized is : "
            + result);
  }
}
