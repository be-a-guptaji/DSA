/*
LeetCode Problem: https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/

Question: 1466. Reorder Routes to Make All Paths Lead to the City Zero

Problem Statement: There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

Example 1:
Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 2:
Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 3:
Input: n = 3, connections = [[1,0],[2,0]]
Output: 0

Constraints:
2 <= n <= 5 * 10^4
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
*/

/*
Approach: Sign-encoded Bidirectional DFS from Root
Goal:
- Count the minimum edges to reverse so all nodes
  can reach node 0, given a rooted directed tree
  originally rooted at 0.
Core Idea:
- Model the directed graph as an undirected one,
  but encode edge direction in the sign of the
  neighbor: positive neighbor = original directed
  edge away from root (needs reversal if traversed
  toward root); negative neighbor = original
  directed edge toward root (already correct
  direction).
- DFS from node 0; each time we traverse a
  positive-valued neighbor, the original edge
  points away from 0 and must be reversed.
Algorithm Steps:
1. Build adjacency list with sign encoding:
   - For edge (u, v): add +v to u's list (forward,
     costs 1 if traversed) and -u to v's list
     (backward, costs 0 if traversed).
2. Call dfs(0, -1, adjacencyList).
3. In dfs(node, parent, adjacencyList):
   a. For each neighbor nei in node's list:
      - Skip if abs(nei) == parent.
      - Recurse: changes += dfs(abs(nei), node, ...)
        + (nei > 0 ? 1 : 0).
4. Return total changes.
Why It Works:
- A positive neighbor means the original edge
  points away from node 0 (u -> v where u is
  closer to 0); traversing it during DFS means
  it needs to be reversed so traffic can flow
  toward 0.
- A negative neighbor means the original edge
  already points toward 0 (v -> u); no reversal
  needed.
- DFS from 0 visits every node exactly once,
  accumulating the reversal cost bottom-up.
Time Complexity:
- O(n)
since each node and edge is visited exactly once.
Space Complexity:
- O(n)
for the adjacency list and O(n) recursive call
stack in the worst case of a linear chain.
Result:
- Returns the minimum number of edge reversals so
  every node can reach node 0.
*/

package Graphs.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Method to find the minimum number of edges changed
  public int minReorder(int n, int[][] connections) {
    // Initialize the Arraylist of the Arraylist
    ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

    // Fill the adjacencyList
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    // Form the adjacencyList list
    for (int i = 0; i < connections.length; i++) {
      adjacencyList.get(connections[i][0]).add(connections[i][1]);
      adjacencyList.get(connections[i][1]).add(-connections[i][0]);
    }

    // Return the recursive dfs call
    return this.dfs(0, -1, adjacencyList);
  }

  // Helper method finding the minimum number of reorder
  private int dfs(int node, int parent, ArrayList<ArrayList<Integer>> adjacencyList) {
    // Initialize the change variable
    int changes = 0;

    // Iterate over the list
    for (int nei : adjacencyList.get(node)) {
      // If node and parent are same then skip it
      if (Math.abs(nei) == parent) {
        continue;
      }

      // Update the changes variable
      changes += dfs(Math.abs(nei), node, adjacencyList) + (nei > 0 ? 1 : 0);
    }

    // Return the changes
    return changes;
  }
}

public class _1466_Reorder_Routes_to_Make_All_Paths_Lead_to_the_City_Zero {
  // Main method to test minReorder
  public static void main(String[] args) {
    int n = 4;
    int[][] connections = new int[][] { { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 } };

    int result = new Solution().minReorder(n, connections);

    System.out.println("The minimum number of edges changed is : " + result);
  }
}
