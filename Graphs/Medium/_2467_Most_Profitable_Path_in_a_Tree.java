/*
LeetCode Problem: https://leetcode.com/problems/most-profitable-path-in-a-tree/

Question: 2467. Most Profitable Path in a Tree

Problem Statement: There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0. You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:

the price needed to open the gate at node i, if amount[i] is negative, or,
the cash reward obtained on opening the gate at node i, otherwise.
The game goes on as follows:

Initially, Alice is at node 0 and Bob is at node bob.
At every second, Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob moves towards node 0.
For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the reward. Note that:
If the gate is already open, no price will be required, nor will there be any cash reward.
If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there. In other words, if the price to open the gate is c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at the gate is c, both of them receive c / 2 each.
If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving. Note that these events are independent of each other.
Return the maximum net income Alice can have if she travels towards the optimal leaf node.

Example 1:
Input: edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
Output: 6
Explanation: 
The above diagram represents the given tree. The game goes as follows:
- Alice is initially on node 0, Bob on node 3. They open the gates of their respective nodes.
  Alice's net income is now -2.
- Both Alice and Bob move to node 1. 
  Since they reach here simultaneously, they open the gate together and share the reward.
  Alice's net income becomes -2 + (4 / 2) = 0.
- Alice moves on to node 3. Since Bob already opened its gate, Alice's income remains unchanged.
  Bob moves on to node 0, and stops moving.
- Alice moves on to node 4 and opens the gate there. Her net income becomes 0 + 6 = 6.
Now, neither Alice nor Bob can make any further moves, and the game ends.
It is not possible for Alice to get a higher net income.

Example 2:
Input: edges = [[0,1]], bob = 1, amount = [-7280,2350]
Output: -7280
Explanation: 
Alice follows the path 0->1 whereas Bob follows the path 1->0.
Thus, Alice opens the gate at node 0 only. Hence, her net income is -7280. 
 
Constraints:

2 <= n <= 10^5
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
edges represents a valid tree.
1 <= bob < n
amount.length == n
amount[i] is an even integer in the range [-10^4, 10^4].
*/

/*
Approach: Bob Path DFS + Alice BFS with Simultaneous Timing
Goal:
- Find Alice's maximum net income traveling from
  node 0 to any leaf, where Bob travels from a
  given node to node 0, and gates visited by both
  at the same time yield half income, while gates
  Bob reaches first yield zero income to Alice.
Core Idea:
- Bob always takes the unique path from his start
  to node 0 (tree has no cycles). Pre-compute the
  time Bob arrives at each node on his path via
  DFS; nodes not on Bob's path retain MIN_VALUE.
- BFS from node 0 for Alice, tracking cumulative
  profit at each node. At each node Alice visits,
  check if Bob arrives earlier (profit = 0),
  simultaneously (profit = amount/2), or later
  (profit = full amount).
- At leaf nodes (degree 1, excluding parent),
  update the global maximum profit.
Algorithm Steps:
1. Build undirected adjacency list.
2. DFS from Bob toward node 0, recording arrival
   times in bobTimes[] only along the valid path.
   Return true when node 0 is found, propagating
   times upward.
3. BFS from node 0 as Alice with state
   {node, time, parent, totalProfit}:
   a. For each unvisited neighbor, compute its
      profit contribution based on bobTimes
      comparison.
   b. Enqueue neighbor with updated totalProfit.
   c. If neighbor is a leaf (degree 1), update
      result.
4. Return result.
Why It Works:
- Bob's path is unique in a tree; DFS backtracking
  correctly marks only nodes on the actual root
  path.
- BFS processes Alice's nodes level by level
  (time-synchronized), enabling exact comparison
  with Bob's precomputed arrival times.
- Leaf detection via adjList[neighbor].size() == 1
  works because the root (node 0) has degree >= 1
  and is never re-enqueued as a neighbor.
Time Complexity:
- O(n) for DFS and BFS, each visiting every node
  and edge at most once.
Space Complexity:
- O(n) for the adjacency list, bobTimes array,
  and BFS queue.
Result:
- Returns the maximum net income Alice can achieve
  traveling to any leaf node optimally.
*/

package Graphs.Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the maximum net income Alice can have if she travels towards
  // the optimal leaf node
  public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
    // Initialize the total nodes
    int nodes = amount.length;

    // Initialize the adjList
    ArrayList<Integer>[] adjList = new ArrayList[nodes];

    // Initialize the array list
    for (int i = 0; i < nodes; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Make the adjList
    for (int[] edge : edges) {
      adjList[edge[0]].add(edge[1]);
      adjList[edge[1]].add(edge[0]);
    }

    // Initialize the bob times from bob nodes to zero
    int[] bobTimes = new int[nodes];

    // Fill the bobTimes to Integer.MIN_VALUE
    Arrays.fill(bobTimes, Integer.MIN_VALUE);

    // Fill the bob times
    this.dfs(bob, Integer.MIN_VALUE, adjList, bobTimes, 0);

    // Initialize the queue for the bfs
    Queue<int[]> queue = new ArrayDeque<>();

    // Add the array of value to the queue
    queue.offer(new int[] { 0, 0, Integer.MIN_VALUE, amount[0] }); // {node, time, parent, totalProfit}

    // Initialize the result variable
    int result = Integer.MIN_VALUE;

    // Initialize the neighbourProfit
    int neighbourProfit = 0;

    // Initialize the neighbourTime
    int neighbourTime = 0;

    // Iterate untill queue is not empty
    while (!queue.isEmpty()) {
      // Get the node from the queue
      int[] arr = queue.poll();

      // Get the node, time, parent and totalProfit
      int node = arr[0];
      int time = arr[1];
      int parent = arr[2];
      int totalProfit = arr[3];

      // Iterate over all the neighbours
      for (int neighbour : adjList[node]) {
        if (neighbour != parent) {
          neighbourProfit = amount[neighbour];
          neighbourTime = time + 1;

          // Update the time and profit if needed
          if (bobTimes[neighbour] != Integer.MIN_VALUE) {
            if (neighbourTime > bobTimes[neighbour]) {
              neighbourProfit = 0;
            } else if (neighbourTime == bobTimes[neighbour]) {
              neighbourProfit >>= 1;
            }
          }

          // Add the value to the queue
          queue.offer(new int[] { neighbour, neighbourTime, node, neighbourProfit + totalProfit });

          // Update the result if needed
          if (adjList[neighbour].size() == 1) {
            result = Math.max(result, neighbourProfit + totalProfit);
          }
        }
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private boolean dfs(int node, int parent, ArrayList<Integer>[] adjList, int[] bobTimes, int time) {
    // If node is zero then set the bobTimes[node] to time
    if (node == 0) {
      bobTimes[node] = time;
      return true;
    }

    // Iterate over all the neighbours
    for (int neighbour : adjList[node]) {
      if (neighbour != parent) {
        if (this.dfs(neighbour, node, adjList, bobTimes, time + 1)) {
          bobTimes[node] = time;
          return true;
        }
      }
    }

    // Return false in the end
    return false;
  }
}

public class _2467_Most_Profitable_Path_in_a_Tree {
  // Main method to test mostProfitablePath
  public static void main(String[] args) {
    int[][] edges = new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 3, 4 } };
    int bob = 3;
    int[] amount = new int[] { -2, 4, 2, -4, 6 };

    int result = new Solution().mostProfitablePath(edges, bob, amount);

    System.out
        .println("The maximum net income Alice can have if she travels towards the optimal leaf node is : " + result);
  }
}
