/*
LeetCode Problem: https://leetcode.com/problems/redundant-connection/

Question: 684. Redundant Connection

Problem Statement: In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

Example 1:
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]

Example 2:
Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
 */

/*
Approach:
1. Initialize an empty adjacency list for the graph.
   - This will store all edges added so far.
2. Iterate through each edge in the input list:
   a. For each edge (src, dst), initialize adjacency lists if they don't exist.
   b. Check if adding this edge would create a cycle:
      - Use DFS starting from 'src' to see if 'dst' is already reachable.
      - Maintain a 'visited' array to avoid revisiting nodes.
      - If DFS returns true, this edge is redundant, so return it immediately.
   c. If no cycle is found, add the edge to the adjacency list in both directions
      (since the graph is undirected).
3. Repeat the process for all edges. The first edge that forms a cycle is the redundant one.

Time Complexity: O(n^2) in the worst case, where n is the number of edges, because for each edge we may traverse all previously added nodes in DFS.
Space Complexity: O(n + e) for the adjacency list, plus O(n) for the visited array during DFS.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _684_Redundant_Connection {
   // Method to find the redundant connection in an undirected graph
   public static int[] findRedundantConnection(int[][] edges) {
      // Number of nodes in the graph
      int n = edges.length;

      // Adjacency list for the graph
      Map<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();

      // Process each edge
      for (int[] edge : edges) {
         int src = edge[0], dst = edge[1];

         // Initialize adjacency lists if not present
         adjacencyList.putIfAbsent(src, new ArrayList<>());
         adjacencyList.putIfAbsent(dst, new ArrayList<>());

         // If both nodes already exist in the graph, check for a cycle
         boolean[] visited = new boolean[n + 1];
         if (!adjacencyList.get(src).isEmpty() && !adjacencyList.get(dst).isEmpty()
               && dfs(adjacencyList, src, dst, visited)) {
            return new int[] { src, dst };
         }

         // Add the edge to both nodes (undirected graph)
         adjacencyList.get(src).add(dst);
         adjacencyList.get(dst).add(src);
      }

      // Default return (should never reach here if input is valid)
      return new int[0];
   }

   // Helper DFS method to check if there is a path from src to dst
   private static boolean dfs(Map<Integer, ArrayList<Integer>> adjacencyList, int current, int target,
         boolean[] visited) {
      if (current == target)
         return true;

      visited[current] = true;
      for (int neighbor : adjacencyList.get(current)) {
         if (!visited[neighbor] && dfs(adjacencyList, neighbor, target, visited)) {
            return true;
         }
      }
      return false;
   }

   // Main method to test findRedundantConnection
   public static void main(String[] args) {
      int[][] edges = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } };

      int[] result = findRedundantConnection(edges);

      System.out.println("The redundant connection in the edges is : " + Arrays.toString(result));
   }
}