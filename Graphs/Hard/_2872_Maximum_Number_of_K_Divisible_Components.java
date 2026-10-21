/*
LeetCode Problem: https://leetcode.com/problems/maximum-number-of-k-divisible-components/

Question: 2872. Maximum Number of K-Divisible Components

Problem Statement: There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the ith node, and an integer k.

A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the resulting components all have values that are divisible by k, where the value of a connected component is the sum of the values of its nodes.

Return the maximum number of components in any valid split.

Example 1:
Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
Output: 2
Explanation: We remove the edge connecting node 1 with 2. The resulting split is valid because:
- The value of the component containing nodes 1 and 3 is values[1] + values[3] = 12.
- The value of the component containing nodes 0, 2, and 4 is values[0] + values[2] + values[4] = 6.
It can be shown that no other valid split has more than 2 connected components.

Example 2:
Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
Output: 3
Explanation: We remove the edge connecting node 0 with 2, and the edge connecting node 0 with 1. The resulting split is valid because:
- The value of the component containing node 0 is values[0] = 3.
- The value of the component containing nodes 2, 5, and 6 is values[2] + values[5] + values[6] = 9.
- The value of the component containing nodes 1, 3, and 4 is values[1] + values[3] + values[4] = 6.
It can be shown that no other valid split has more than 3 connected components.

Constraints:
1 <= n <= 3 * 10^4
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
values.length == n
0 <= values[i] <= 10^9
1 <= k <= 10^9
Sum of values is divisible by k.
The input is generated such that edges represents a valid tree.
*/

/*
Approach: Post-order DFS with Subtree Sum Divisibility Check
Goal:
- Find the maximum number of components the tree
  can be split into such that each component's
  node value sum is divisible by k.
Core Idea:
- A valid split cuts edges whose removal produces
  subtrees each with sum divisible by k.
- Post-order DFS computes the subtree sum mod k
  bottom-up. If a subtree's sum mod k == 0, that
  subtree can be detached as a valid component,
  contributing 0 to its parent's sum.
- Counting nodes where subtree sum mod k == 0
  gives the maximum component count.
Algorithm Steps:
1. Build undirected adjacency list.
2. Call dfs(0, -1, ...) rooted at node 0.
3. In dfs(current, parent, ...):
   a. Initialize total = values[current] % k.
   b. For each child (neighbor != parent), add
      dfs(child, ...) % k to total.
   c. If total % k == 0, increment result (this
      subtree forms a valid component).
   d. Return total % k to propagate upward.
4. Return result.
Why It Works:
- If a subtree's sum is divisible by k, cutting
  its root edge leaves both parts with sums
  divisible by k (the remainder propagated upward
  is 0, not affecting the parent's count).
- The modular accumulation keeps values bounded,
  preventing overflow on large trees.
- Counting every node with subtree sum mod k == 0
  correctly counts the maximum number of valid
  independent components.
Time Complexity:
- O(n) — each node visited once.
Space Complexity:
- O(n) for adjacency list and call stack.
Result:
- Returns the maximum number of k-divisible
  components achievable by splitting the tree.
*/

package Graphs.Hard;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Initialize the result variable
  private int result = 0;

  // Method to find the maximum number of components in any valid split
  public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
    // Reset the result variable
    this.result = 0;

    // Initialize the adjList
    ArrayList<Integer>[] adjList = new ArrayList[n];

    // Initialize the array list
    for (int i = 0; i < n; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Make the adjList
    for (int[] edge : edges) {
      adjList[edge[0]].add(edge[1]);
      adjList[edge[1]].add(edge[0]);
    }

    // Call the dfs method
    this.dfs(0, -1, values, k, adjList);

    // Return the result
    return this.result;
  }

  // Helper method for the dfs
  private long dfs(int current, int parent, int[] values, int k,
      ArrayList<Integer>[] adjList) {
    // Initialize the total variable
    long total = values[current] % k;

    // Iterate over the neighbours
    for (int child : adjList[current]) {
      if (child != parent) {
        total = (total + this.dfs(child, current, values, k, adjList)) % k;
      }
    }

    // If total is divisible by k then increment the result
    if (total == 0) {
      this.result++;
    }

    // Return the total variable
    return total;
  }
}

public class _2872_Maximum_Number_of_K_Divisible_Components {
  // Main method to test maxKDivisibleComponents
  public static void main(String[] args) {
    int n = 7;
    int[][] edges = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 } };
    int[] values = new int[] { 3, 0, 6, 1, 5, 2, 1 };
    int k = 3;

    int result = new Solution().maxKDivisibleComponents(n, edges, values, k);

    System.out.println("The maximum number of components in any valid split is : " + result);
  }
}
