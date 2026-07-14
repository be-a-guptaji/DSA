/*
LeetCode Problem: https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/

Question: 1443. Minimum Time to Collect All Apples in a Tree

Problem Statement: Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

Example 1:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  

Example 2:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  

Example 3:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0

Constraints:
    1 <= n <= 10^5
    edges.length == n - 1
    edges[i].length == 2
    0 <= ai < bi <= n - 1
    hasApple.length == n
*/

/*
Approach: Post-order DFS with Subtree Apple Pruning
Goal:
- Find the minimum total time to start at node 0,
  walk edges (each costing 1 second per direction,
  2 seconds round trip) to collect every apple, and
  return to node 0.
Core Idea:
- Treat node 0 as root, converting the undirected
  graph into a rooted tree via DFS (tracking parent
  to avoid revisiting).
- For any subtree, the edge connecting it to its
  parent is only worth traversing (cost 2, for the
  round trip) if that subtree contains at least one
  apple, either directly on the child node or
  somewhere further down.
- This is decided bottom-up: a node's contribution
  is known only after all its children have reported
  whether their subtrees require a visit.
Algorithm Steps:
1. Build an adjacency list from the edge list.
2. Call dfs(0, -1, ...) to root the traversal at
   node 0 with no parent.
3. In dfs(current, parent, ...):
   a. Initialize time = 0.
   b. For each neighbor child of current:
      - Skip child if it equals parent (avoid
        going back up).
      - Recursively compute childTime = dfs(child,
        current, ...), the time needed to collect
        all apples within child's subtree.
      - If childTime > 0 (child's subtree required
        travel) OR hasApple.get(child) is true (the
        child itself holds an apple), add 2 +
        childTime to time, accounting for the round
        trip edge to child plus whatever time was
        spent inside that subtree.
   c. Return time, representing the total time
      needed to collect all apples reachable through
      current's children and return back to current.
4. The top-level call's return value is the answer,
   since it represents the full round trip time
   starting and ending at node 0.
Why It Works:
- A subtree's edge to its parent is included in the
  total exactly when it's necessary, i.e., when
  skipping it would mean missing an apple.
- Recursion naturally aggregates cost bottom-up: a
  parent only pays for a child edge if that child
  either has an apple or needs to pass through to
  a deeper apple.
- Since the tree has no cycles (rooted via parent
  tracking), each edge is considered exactly once
  in exactly one direction of necessity.
Time Complexity:
- O(n)
where n is the number of nodes, since each node and
edge is visited exactly once.
Space Complexity:
- O(n)
for the adjacency list plus O(h) recursive call
stack, where h is the tree height, worst case O(n)
for a skewed tree.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Solution Class 
class Solution {
  // Method to find the minimum time in seconds you have to spend to collect all
  // apples in the tree
  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    // Initialize the array of list of integer
    ArrayList<Integer>[] adj = new ArrayList[n];

    // Initialize the adj list for all the element
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }

    // Update the adj list for the edges
    for (int i = 0; i < edges.length; i++) {
      // Initialize the current edge
      int[] edge = edges[i];

      // Update the adj list
      adj[edge[0]].add(edge[1]);
      adj[edge[1]].add(edge[0]);
    }

    // Return the recursive call
    return this.dfs(0, -1, adj, hasApple);
  }

  // Helper method to find the minimum time
  private int dfs(int current, int parent, ArrayList<Integer>[] adj, List<Boolean> hasApple) {
    // Initialize the time variable
    int time = 0;

    // Iterate over the current adj list
    for (int child : adj[current]) {
      // If current is equal to parent then return
      if (child == parent) {
        continue;
      }

      // If get the childtime
      int childTime = this.dfs(child, current, adj, hasApple);

      // Update the time if needed
      if (childTime > 0 || hasApple.get(child)) {
        time += 2 + childTime;
      }
    }

    // Return the time variable
    return time;
  }
}

// Main Class
public class _1443_Minimum_Time_to_Collect_All_Apples_in_a_Tree {
  // Main method to test minTime
  public static void main(String[] args) {
    int n = 7;
    int[][] edges = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 4 }, { 1, 5 }, { 2, 3 }, { 2, 6 } };
    List<Boolean> hasApple = Arrays.asList(false, false, true, false, false, true, false);

    int result = new Solution().minTime(n, edges, hasApple);

    System.out
        .println("The minimum time in seconds you have to spend to collect all apples in the tree is : " + result);
  }
}
