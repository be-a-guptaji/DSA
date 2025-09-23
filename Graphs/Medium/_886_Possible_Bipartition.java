/*
LeetCode Problem: https://leetcode.com/problems/possible-bipartition/

Question: 886. Possible Bipartition

Problem Statement: We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

Example 1:
Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: The first group has [1,4], and the second group has [2,3].

Example 2:
Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.

Constraints:

1 <= n <= 2000
0 <= dislikes.length <= 10^4
dislikes[i].length == 2
1 <= ai < bi <= n
All the pairs of dislikes are unique.
 */

/*
Approach:
1. The problem is to check if we can divide people into 2 groups such that 
   no pair of people who dislike each other are in the same group. 
   This is equivalent to checking if the graph is bipartite.
2. Steps:
   - Represent the dislikes using an adjacency list (undirected graph).
   - Use a colors array:
        0 → unvisited
        1 → group A
       -1 → group B
   - Traverse all nodes (because the graph can be disconnected).
   - For each unvisited node, perform BFS (or DFS):
        - Assign it a color (say 1).
        - For each neighbor:
            • If not visited, assign opposite color and push to queue.
            • If already visited and has the same color as the current node,
              then bipartition is not possible → return false.
   - If traversal finishes without conflicts, return true.

Time Complexity: O(N + E),  
   where N = number of people (nodes), E = number of dislike relations (edges).  
   Each node and edge is processed at most once.
Space Complexity: O(N + E),  
   for adjacency list, color array, and BFS queue.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _886_Possible_Bipartition {
    // Method to determine if it is possible to partition a graph
    public static boolean possibleBipartition(int n, int[][] dislikes) {
        // Initialize adjacency list for the neighbors
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        // Add number of lists equal to the number of nodes
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build adjacency list according to the dislike relationships
        for (int[] relation : dislikes) {
            int person1 = relation[0] - 1; // convert to 0-based index
            int person2 = relation[1] - 1;

            // Undirected graph: add both relations
            adjacencyList.get(person1).add(person2);
            adjacencyList.get(person2).add(person1);
        }

        // Colors array: 0 = unvisited, 1 = group A, -1 = group B
        int[] colors = new int[n];

        // Traverse all nodes to handle disconnected graph components
        for (int i = 0; i < n; i++) {
            if (colors[i] != 0)
                continue; // already visited

            // BFS traversal
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1; // assign first color

            while (!queue.isEmpty()) {
                // Get the node from the queue
                int node = queue.poll();

                // Traverse all neighbors
                for (int neighbor : adjacencyList.get(node)) {
                    if (colors[neighbor] == 0) {
                        // Assign opposite color
                        colors[neighbor] = -colors[node];
                        queue.offer(neighbor);
                    } else if (colors[neighbor] == colors[node]) {
                        // Conflict detected → not bipartite
                        return false;
                    }
                }
            }
        }

        // If BFS finishes without conflict → bipartition is possible
        return true;
    }

    // Main method to test possibleBipartition
    public static void main(String[] args) {
        int n = 4;
        int[][] dislikes = { { 1, 2 }, { 1, 3 }, { 2, 4 } };

        if (possibleBipartition(n, dislikes)) {
            System.out.println("The graph can be bipartited graph.");
        } else {
            System.out.println("The graph can not be bipartited graph.");
        }
    }
}