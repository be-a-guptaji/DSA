/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/

Question: 1557. Minimum Number of Vertices to Reach All Nodes

Problem Statement: Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.

Example 1:
Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
Output: [0,3]
Explanation: It's not possible to reach all the nodes from a single vertex. From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].

Example 2:
Input: n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
Output: [0,2,3]
Explanation: Notice that vertices 0, 3 and 2 are not reachable from any other node, so we must include them. Also any of these vertices can reach nodes 1 and 4.

Constraints:
2 <= n <= 10^5
1 <= edges.length <= min(10^5, n * (n - 1) / 2)
edges[i].length == 2
0 <= fromi, toi < n
All pairs (fromi, toi) are distinct.
*/

/*
Approach: In-degree Zero Node Collection
Goal:
- Find the smallest set of vertices from which all
  nodes in the DAG are reachable.
Core Idea:
- Any node with at least one incoming edge can be
  reached from its parent, so it need not be in
  the starting set. Only nodes with zero incoming
  edges (no parent) must be included, as they are
  unreachable from any other node.
Algorithm Steps:
1. Mark every node that appears as an edge
   destination in hasIncomingEdge[].
2. Collect all nodes where hasIncomingEdge[i] is
   false into result.
3. Return result.
Time Complexity:
- O(V + E) — one pass over edges, one over nodes.
Space Complexity:
- O(V) for the boolean array and result list.
Result:
- Returns all nodes with in-degree zero.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the vertices in any order
  public ArrayList<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
    // Initialize the boolean array for the edges
    boolean[] hasIncommingEdge = new boolean[n];

    // Iterate over the edges list
    for (List<Integer> edge : edges) {
      hasIncommingEdge[edge.get(1)] = true;
    }

    // Initialize the array list for the result
    ArrayList<Integer> result = new ArrayList<>();

    // Add value to the result array
    for (int i = 0; i < n; i++) {
      // If it has no incomming edge then add to the result arraylist
      if (!hasIncommingEdge[i]) {
        result.add(i);
      }
    }

    // Return the result
    return result;
  }
}

public class _1557_Minimum_Number_of_Vertices_to_Reach_All_Nodes {
  // Main method to test findSmallestSetOfVertices
  public static void main(String[] args) {
    int n = 5;
    List<List<Integer>> edges = new ArrayList<>();

    edges.add(new ArrayList<>(Arrays.asList(0, 1)));
    edges.add(new ArrayList<>(Arrays.asList(2, 1)));
    edges.add(new ArrayList<>(Arrays.asList(3, 1)));
    edges.add(new ArrayList<>(Arrays.asList(1, 4)));
    edges.add(new ArrayList<>(Arrays.asList(2, 4)));

    ArrayList<Integer> result = new Solution().findSmallestSetOfVertices(n, edges);

    System.out.println("The vertices in any order is : " + result);
  }
}
