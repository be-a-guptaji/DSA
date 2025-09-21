/*
LeetCode Problem: https://leetcode.com/problems/is-graph-bipartite/

Question: 785. Is Graph Bipartite?

Problem Statement: There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

Example 1:
Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.

Example 2:
Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.

Constraints:

graph.length == n
1 <= n <= 100
0 <= graph[u].length < n
0 <= graph[u][i] <= n - 1
graph[u] does not contain u.
All the values of graph[u] are unique.
If graph[u] contains v, then graph[v] contains u.
 */

/*
Approach:
1. A graph is bipartite if we can color its nodes using two colors such that no two adjacent nodes share the same color.
2. Initialize an array `colors[]` to store the color of each node (0 = uncolored, 1 = first color, -1 = second color).
3. Traverse all nodes to handle disconnected components:
   - If a node is uncolored, perform BFS starting from it.
4. During BFS:
   - Assign the starting node the first color.
   - For each dequeued node, assign its uncolored neighbors the opposite color.
   - If a neighbor is already colored and has the same color as the current node, the graph is not bipartite; return false.
5. If BFS finishes without conflicts for all nodes, the graph is bipartite; return true.

Time Complexity: O(V + E), where V = number of nodes, E = number of edges (each node and edge is processed once).  
Space Complexity: O(V) for the queue and the colors array.
*/

package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _785_Is_Graph_Bipartite {
    // Method to find if the graph is bipartited or not
    public static boolean isBipartite(int[][] graph) {
        // Initialize colors array with 0 (uncolored)
        int[] colors = new int[graph.length];

        // Loop through all nodes to handle disconnected components
        for (int i = 0; i < graph.length; i++) {
            // If node is already covered then skip the process
            if (colors[i] != 0) {
                continue;
            }

            // Initialize a queue for the BFS Traversal
            Queue<Integer> queue = new LinkedList<>();

            // Offer the node to the queue
            queue.offer(i);
            // Assign first color
            colors[i] = 1;

            // Traverse the graph untill the queue is empty
            while (!queue.isEmpty()) {
                // Get the node from the queue
                int node = queue.poll();
                for (int neighbor : graph[node]) {
                    // If neighbor has color 0 then color it opposite to the node
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -colors[node];
                        queue.offer(neighbor);
                    } else if (colors[neighbor] == colors[node]) {
                        // Same color detected, not bipartite
                        return false;
                    }
                }
            }
        }

        // If everything goes well return true
        return true;
    }

    // Main method to test isBipartite
    public static void main(String[] args) {
        int[][] graph = {
                { 1, 3 },
                { 0, 2 },
                { 1, 3 },
                { 0, 2 }
        };

        if (isBipartite(graph)) {
            System.out.println("The graph is a bipartited graph.");
        } else {
            System.out.println("The graph is not a bipartited graph.");
        }
    }
}