/*
LeetCode Problem: https://leetcode.com/problems/critical-connections-in-a-network/

Question: 1192. Critical Connections in a Network

Problem Statement: There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

Example 2:
Input: n = 2, connections = [[0,1]]
Output: [[0,1]]

Constraints:

2 <= n <= 10^5
n - 1 <= connections.length <= 10^5
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.
 */

/*
Approach:
1. This problem is a classic use-case for **Tarjan’s Algorithm**:
   - The goal is to find all **critical connections (bridges)** in a network.
   - A bridge is an edge which, if removed, disconnects the graph.
2. Preprocessing - Build adjacency list:
   - Since the graph is undirected, add both (u → v) and (v → u) for each edge.
   - This ensures bidirectional traversal.
3. Use DFS with discovery & low times:
   - Maintain:
     - `time[]`: discovery time when a node is first visited.
     - `low[]`: the lowest discovery time reachable from this node (via tree or back edges).
     - `visited[]`: marks visited nodes.
   - A global timer `clock` assigns discovery times.
4. DFS traversal:
   - For each neighbor:
     - If neighbor == parent → skip.
     - If neighbor is not visited:
       → Recurse DFS(neighbor, node).
       → Update `low[node] = min(low[node], low[neighbor])`.
       → If `low[neighbor] > time[node]`, mark (node, neighbor) as a critical connection.
     - Else (back edge):
       → Update `low[node] = min(low[node], time[neighbor])`.
5. Visiting nodes:
   - Each node is visited exactly once.
   - Updates to `low[]` ensure back edges are accounted for.
6. Termination:
   - DFS explores all edges.
   - All edges where `low[neighbor] > time[node]` are returned as bridges.

Time Complexity: O(V + E), V = number of nodes, E = number of edges, Each edge and node is visited once in DFS.  

Space Complexity: O(V + E), For adjacency list, recursion stack, and arrays (`visited`, `time`, `low`).  
*/

package Graphs.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1192_Critical_Connections_in_a_Network {
   // Initialize a global time for the graph
   private static int clock = 1;

   // Method to find the critical connection in the graph
   public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
      // Initialize the adjacency list for each node
      ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(new ArrayList<>());

      // Make n adjacency list for all the nodes
      for (int i = 0; i < n; i++) {
         adjacencyList.add(new ArrayList<>());
      }

      // Make the adjacency list
      for (List<Integer> connection : connections) {
         // Get the first and second value of the connection
         int first = connection.get(0), second = connection.get(1);

         // Make the bidirectional graph
         adjacencyList.get(first).add(second);
         adjacencyList.get(second).add(first);
      }

      // Use Tarjan's Algorithm for finding all the critical connections
      int[] visited = new int[n], time = new int[n], low = new int[n];
      List<List<Integer>> bridges = new ArrayList<>();

      // Make the dfs call
      dfs(0, -1, adjacencyList, visited, time, low, bridges);

      // Retrun the bridges
      return bridges;
   }

   // Helper method to find the critical connections
   private static void dfs(int node, int parent,
         ArrayList<ArrayList<Integer>> adjacencyList, int[] visited, int[] time,
         int[] low, List<List<Integer>> bridges) {
      // Make the node visited
      visited[node] = 1;

      // Add time to low and time array for the node
      time[node] = low[node] = clock;

      // Increment the clock
      clock++;

      // Make the dfs call to all the adjacent nodes
      for (Integer neighbour : adjacencyList.get(node)) {
         // If parent and neighbour is same then skip the iteration
         if (neighbour == parent) {
            continue;
         }

         // If the node is unvisited then make a recursive dfs call
         if (visited[neighbour] == 0) {
            dfs(neighbour, node, adjacencyList, visited, time, low, bridges);

            // Fill the low array node with the minimum
            low[node] = Math.min(low[node], low[neighbour]);

            // If the low[node] time is more then the time[node] then it is a critical
            // connection
            if (low[neighbour] > time[node]) {
               bridges.add(Arrays.asList(neighbour, node));
            }
         } else {
            // Fill the low array node with the minimum
            low[node] = Math.min(low[node], low[neighbour]);
         }
      }
   }

   // Main method to test criticalConnections
   public static void main(String[] args) {
      int n = 4;
      List<List<Integer>> connections = new ArrayList<>();

      connections.add(Arrays.asList(0, 1));
      connections.add(Arrays.asList(1, 2));
      connections.add(Arrays.asList(2, 0));
      connections.add(Arrays.asList(1, 3));

      List<List<Integer>> result = criticalConnections(n, connections);

      System.out.println("The critical connections in the graph are : " + result);
   }
}