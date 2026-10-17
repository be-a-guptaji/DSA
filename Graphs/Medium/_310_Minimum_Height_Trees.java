/*
LeetCode Problem: https://leetcode.com/problems/minimum-height-trees/

Question: 310. Minimum Height Trees

Problem Statement: A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Constraints:
1 <= n <= 2 * 10^4
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
*/

/*
Approach: Iterative Leaf Trimming to Find Tree Centroids
Goal:
- Find all roots that produce minimum height trees
  (MHTs); there are at most 2 such roots, always
  at the topological center of the tree.
Core Idea:
- The roots of MHTs are the centroid(s) of the
  tree: the node(s) remaining after repeatedly
  removing all leaf nodes layer by layer.
- This is analogous to topological sort on an
  undirected tree: leaves (degree 1) are peeled
  off in waves until 1 or 2 nodes remain.
- Those final nodes are the answer, since they
  minimize the maximum distance to any other node.
Algorithm Steps:
1. Handle n == 1 as a base case (single node,
   return [0]).
2. Build undirected adjacency list and degree[].
3. Enqueue all leaves (degree == 1).
4. While remainingNodes > 2:
   a. Remove the current leaf layer (size nodes).
   b. Decrement remainingNodes by size.
   c. For each removed leaf, decrement its
      neighbors' degrees; if a neighbor's degree
      drops to 1, enqueue it as the new leaf.
5. Remaining nodes in the queue are the centroids.
6. Return centroids.
Why It Works:
- A tree has exactly 1 or 2 centroids. Removing
  leaves iteratively converges to them because the
  centroid is the last node(s) to become a leaf,
  having the most balanced subtree sizes.
- Stopping at remainingNodes <= 2 prevents
  over-trimming past the centroid(s).
Time Complexity:
- O(n) — each node and edge is processed once.
Space Complexity:
- O(n) for the adjacency list, degree array, and
  queue.
Result:
- Returns a list of 1 or 2 MHT root labels.
*/

package Graphs.Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the adjList
  private ArrayList<Integer>[] adj;

  // Method to find a list of all MHTs' root labels
  public ArrayList<Integer> findMinHeightTrees(int n, int[][] edges) {
    // If n is one then return zero list
    if (n == 1) {
      return new ArrayList<>(Arrays.asList(0));
    }

    // Initialize the adj List
    this.adj = new ArrayList[n];

    // Initialize the ArrayList
    int[] degree = new int[n];

    for (int i = 0; i < n; i++) {
      this.adj[i] = new ArrayList<>();
    }

    // Make the adj List
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];

      this.adj[u].add(v);
      this.adj[v].add(u);

      degree[u]++;
      degree[v]++;
    }

    // Initialize the queue
    Queue<Integer> queue = new ArrayDeque<>();

    // Add all the leaf nodes
    for (int i = 0; i < n; i++) {
      if (degree[i] == 1) {
        queue.offer(i);
      }
    }

    // Initialize the remaining nodes
    int remainingNodes = n;

    // Remove the leaf nodes until at most two nodes remain
    while (remainingNodes > 2) {
      // Get the size of the queue
      int size = queue.size();

      // Update the remaining nodes
      remainingNodes -= size;

      // Iterate over the leaf nodes
      for (int i = 0; i < size; i++) {
        // Get the leaf node
        int leaf = queue.poll();

        // Iterate over the neighbor of the node
        for (int neighbor : this.adj[leaf]) {
          // Decrease the degree of the neighbor
          degree[neighbor]--;

          // If the neighbor becomes a leaf then add it to the queue
          if (degree[neighbor] == 1) {
            queue.offer(neighbor);
          }
        }
      }
    }

    // Initialize the centroids
    ArrayList<Integer> centroids = new ArrayList<>();

    // Add the remaining nodes to the centroids
    while (!queue.isEmpty()) {
      centroids.add(queue.poll());
    }

    // Return the centroids
    return centroids;
  }
}

public class _310_Minimum_Height_Trees {
  // Main method to test findMinHeightTrees
  public static void main(String[] args) {
    int n = 4;
    int[][] edges = new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } };

    ArrayList<Integer> result = new Solution().findMinHeightTrees(n, edges);

    System.out.println("A list of all MHTs' root labels is : " + result);
  }
}
