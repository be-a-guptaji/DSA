/*
LeetCode Problem: https://leetcode.com/problems/count-the-number-of-complete-components/

Question: 2685. Count the Number of Complete Components

Problem Statement: You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

Example 1:
Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.

Example 2:
Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.

Constraints:
1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.
*/

/*
Approach: DFS Component Discovery with Complete Graph Validation
Goal:
- Count connected components where every pair of
  nodes in the component is directly connected
  (complete subgraph / clique).
Core Idea:
- A component of size k is complete if and only if
  it has exactly k*(k-1)/2 edges (each node has
  degree k-1 within the component).
- DFS discovers the full connected component;
  then verify completeness by checking that every
  node's degree within the component equals
  componentSize - 1.
Algorithm Steps:
1. Build an adjacency list (not matrix) from edges.
2. For each unvisited node, DFS to collect all
   nodes in its connected component.
3. For the component, count total edges by summing
   degrees of all member nodes and dividing by 2
   (each edge counted twice).
4. A component of size k is complete if it has
   exactly k*(k-1)/2 edges; increment result.
5. Return result.
*/

package Graphs.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Method to find the number of complete connected components of the graph
  public int countCompleteComponents(int n, int[][] edges) {
    // Initialize the matrix of edges
    boolean[][] adjList = new boolean[n][n];

    // Fill the adjList
    for (int i = 0; i < edges.length; i++) {
      // Get the edge
      int[] edge = edges[i];

      // Fill the adjList
      adjList[edge[0]][edge[0]] = true;
      adjList[edge[1]][edge[1]] = true;
      adjList[edge[0]][edge[1]] = true;
      adjList[edge[1]][edge[0]] = true;
    }

    // Make the self edge
    for (int i = 0; i < n; i++) {
      adjList[i][i] = true;
    }

    // Initialize the seen array of edges
    boolean[] seen = new boolean[n];

    // Initialize the result variable
    int result = 0;

    // Iterate over the edges
    for (int i = 0; i < n; i++) {
      // If edge is not seen then call the isConnected method
      if (!seen[i]) {
        // Set the seen to true
        seen[i] = true;

        // Initialize the ArrayList for neighbour
        ArrayList<Integer> neighbour = new ArrayList<>();

        // Get the neighbour of i node
        boolean[] isNeighbour = adjList[i];

        // Fill the neighbour array list
        for (int j = 0; j < n; j++) {
          if (isNeighbour[j]) {
            neighbour.add(j);
            seen[j] = true;
          }
        }

        // Call the isConnected method
        if (this.isConnected(neighbour, adjList)) {
          // If component is conneted then increment the result
          result++;
        }
      }
    }

    // Return the result varaible
    return result;
  }

  // Helper method for finding the connected component
  private boolean isConnected(ArrayList<Integer> neighbour, boolean[][] adjList) {
    // Iterate over all the neighbour
    for (int i = 1; i < neighbour.size(); i++) {
      // If value are miss match then return false
      if (!isSame(adjList[neighbour.get(i - 1)], adjList[neighbour.get(i)])) {
        return false;
      }
    }

    // Return the true
    return true;
  }

  // Helper method to check if two array are same or not
  private boolean isSame(boolean[] arr1, boolean[] arr2) {
    // Iterate over the array
    for (int i = 0; i < arr1.length; i++) {
      // If value are miss match then return false
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }

    // Return the true
    return true;
  }
}

public class _2685_Count_the_Number_of_Complete_Components {
  // Main method to test countCompleteComponents
  public static void main(String[] args) {
    int n = 6;
    int[][] edges = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 3, 4 } };

    int result = new Solution().countCompleteComponents(n, edges);

    System.out.println("The number of complete connected components of the graph is : " + result);
  }
}
