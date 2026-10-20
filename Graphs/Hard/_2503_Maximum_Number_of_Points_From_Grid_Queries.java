/*
LeetCode Problem: https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/

Question: 2503. Maximum Number of Points From Grid Queries

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
Approach: Offline Query Processing with Dijkstra-style Min-Heap BFS
Goal:
- For each query value q, count how many cells are
  reachable from (0,0) via a path where every cell
  visited has a value strictly less than q.
Core Idea:
- Sort queries in ascending order and process them
  offline, reusing the BFS frontier across queries.
- A min-heap (priority queue ordered by cell value)
  expands the reachable region greedily: always
  process the smallest-valued unvisited cell next.
- For each query, drain all cells with value <
  query from the heap, accumulating the count.
  Since queries are sorted, the heap state carries
  over to the next query without restarting.
Algorithm Steps:
1. Sort query indices by query value ascending.
2. Seed the min-heap with {grid[0][0], 0, 0} and
   mark (0,0) as seen.
3. For each query in sorted order:
   a. Drain all heap entries with cell value <
      query: increment total and enqueue unseen
      4-directional neighbors (mark seen on enqueue
      to prevent duplicate entries).
   b. Store result[originalIndex] = total.
4. Return result in original query order.
Why It Works:
- Sorting queries and reusing heap state means each
  cell is enqueued and dequeued at most once across
  all queries, giving amortized O(1) per cell per
  query batch.
- The min-heap ensures cells are expanded in
  value order, so when the heap top exceeds the
  current query threshold, all reachable cells
  below that threshold have already been counted.
- Marking cells seen on enqueue (not dequeue)
  prevents duplicate heap entries and ensures
  accurate total counts.
Time Complexity:
- O((m*n + q) log(m*n))
where m*n is the grid size and q is the number of
queries. Each cell enters the heap once; each
query triggers heap operations bounded by cell
count.
Space Complexity:
- O(m*n) for the heap and seen matrix, O(q) for
  the sorted indices and result array.
Result:
- Returns result[i] = number of cells reachable
  from (0,0) with all path values < queries[i].
*/

package Graphs.Hard;

import java.util.Arrays;
import java.util.PriorityQueue;

// Solution Class
class Solution {
  // Initialize the directions value
  private static final int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

  // Method to find the maximum points for each query
  public int[] maxPoints(int[][] grid, int[] queries) {
    // Initialize the length variable
    final int N = queries.length;

    // Initialize the ROWS and COLS variable
    final int ROWS = grid.length;
    final int COLS = grid[0].length;

    // Initialize the result array
    int[] result = new int[N];

    // Initialize the indices array
    Integer[] indices = new Integer[N];

    // Initialize the indices value
    for (int i = 0; i < N; i++) {
      indices[i] = i;
    }

    // Sort the indices array based on the query values
    Arrays.sort(indices, (a, b) -> Integer.compare(queries[a], queries[b]));

    // Initialize the boolean matrix of seen
    boolean[][] seen = new boolean[ROWS][COLS];

    // Initialize the priority queue for the minimum cell value
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

    // Add the starting cell to the priority queue
    // Store the cell value, row, and column
    pq.offer(new int[] { grid[0][0], 0, 0 });

    // Mark the starting cell as seen
    seen[0][0] = true;

    // Initialize the total variable
    int total = 0;

    // Iterate over the sorted query indices
    for (int i = 0; i < N; i++) {
      // Initialize the index variable
      int index = indices[i];

      // Initialize the target variable
      int target = queries[index];

      // Process cells whose values are less than the target
      while (!pq.isEmpty() && pq.peek()[0] < target) {
        // Get the cell with the minimum value
        int[] cell = pq.poll();

        // Get the row and col
        int row = cell[1];
        int col = cell[2];

        // Increment the total variable
        total++;

        // Iterate over all the directions
        for (int k = 0; k < directions.length; k++) {
          // Get the newRow and newCol
          int newRow = row + directions[k][0];
          int newCol = col + directions[k][1];

          // Check if the new coordinates are within the grid
          if (newRow >= 0 && newCol >= 0 && newRow < ROWS && newCol < COLS) {
            // Check if the cell has not been seen
            if (!seen[newRow][newCol]) {
              // Mark the cell as seen
              seen[newRow][newCol] = true;

              // Add the cell value, row, and column to the queue
              pq.offer(new int[] { grid[newRow][newCol], newRow, newCol });
            }
          }
        }
      }

      // Store the total points for the current query
      result[index] = total;
    }

    // Return the result array
    return result;
  }
}

public class _2503_Maximum_Number_of_Points_From_Grid_Queries {
  // Main method to test maxPoints
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 1, 2, 3 }, { 2, 5, 7 }, { 3, 5, 1 } };
    int[] queries = new int[] { 5, 6, 2 };

    int[] result = new Solution().maxPoints(grid, queries);

    System.out.println("The maximum net income Alice can have if she travels towards the optimal leaf node is : "
        + Arrays.toString(result));
  }
}
