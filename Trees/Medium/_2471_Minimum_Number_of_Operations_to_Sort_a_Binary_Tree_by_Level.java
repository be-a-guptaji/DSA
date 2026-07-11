/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/

Question: 2471. Minimum Number of Operations to Sort a Binary Tree by Level

Problem Statement: You are given the root of a binary tree with unique values.

In one operation, you can choose any two nodes at the same level and swap their values.

Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.

The level of a node is the number of edges along the path between it and the root node.

Example 1:
Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
Output: 3
Explanation:
- Swap 4 and 3. The 2nd level becomes [3,4].
- Swap 7 and 5. The 3rd level becomes [5,6,8,7].
- Swap 8 and 7. The 3rd level becomes [5,6,7,8].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.

Example 2:
Input: root = [1,3,2,7,6,5,4]
Output: 3
Explanation:
- Swap 3 and 2. The 2nd level becomes [2,3].
- Swap 7 and 4. The 3rd level becomes [4,6,5,7].
- Swap 6 and 5. The 3rd level becomes [4,5,6,7].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.

Example 3:
Input: root = [1,2,3,4,5,6]
Output: 0
Explanation: Each level is already sorted in increasing order so return 0.

Constraints:
    The number of nodes in the tree is in the range [1, 10^5].
    1 <= Node.val <= 10^5
    All the values of the tree are unique.
*/

/*
Approach: Level-wise Cycle Decomposition for Minimum Swaps
Goal:
- For each level of the binary tree, determine the
  minimum number of swaps needed to sort that
  level's node values in strictly increasing order.
- Sum these minimums across all levels.
- Node values only need to be considered logically;
  the tree structure and TreeNode.val do not need
  to be physically mutated.
Core Idea:
- The minimum number of swaps to sort any array
  equals the sum, over every cycle in the array's
  "permutation to sorted order," of (cycle length - 1).
- A cycle exists whenever element at position i does
  not belong at position i, and following "where each
  element should go" traces a closed loop of positions.
- BFS is used only to snapshot each level's values;
  no in-place value swapping on TreeNode objects is
  required to compute the answer.
Algorithm Steps:
1. Initialize a queue and offer the root.
2. Initialize totalSwaps to 0.
3. While the queue is not empty:
   a. Record the current level size.
   b. Poll exactly that many nodes, storing their
      values into an array vals[], and enqueue any
      non-null children encountered.
   c. Compute order[i] = original index of the
      element that belongs at sorted position i,
      by sorting indices 0..size-1 according to
      vals[].
   d. Decompose order[] into cycles using a
      visited[] array:
        - For each unvisited index i where
          order[i] != i, follow the chain
          i -> order[i] -> order[order[i]] -> ...
          marking each index visited and counting
          the chain length until returning to a
          visited index.
        - Add (chainLength - 1) to totalSwaps.
   e. Indices where order[i] == i or already
      visited require no swaps and are skipped.
4. Return totalSwaps after all levels are processed.
Why It Works:
- Any permutation can be uniquely decomposed into
  disjoint cycles.
- A single cycle of length k can be resolved into
  its sorted arrangement using exactly k - 1 swaps,
  and this is provably optimal: each swap can place
  at most one additional element into its correct
  final position within the cycle, except the last
  swap which fixes two at once.
- Summing (cycle length - 1) over all cycles in a
  level therefore gives the exact minimum swap count
  for that level.
- Levels are independent since the swap operation is
  only ever defined "within the same level," so the
  total is a simple sum across levels.
Time Complexity:
- O(n log n)
where n is the total number of nodes, dominated by
sorting the values within each level (sum of
k log k over all levels of size k, bounded by
n log n in the worst case of one dominating level).
Space Complexity:
- O(w)
where w is the maximum level width, for the vals[],
order[], and visited[] arrays, plus the BFS queue.
For a perfect binary tree, w = O(n) at the last level.
Result:
- Returns the total minimum number of same-level
  value swaps required to make every level's values
  strictly increasing, without altering the actual
  tree.
*/

package Trees.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the array of nums
  private final static int[] indices = new int[100001];

  // Method to find the minimum number of operations needed to make the values at
  // each level sorted in a strictly increasing order
  public int minimumOperations(TreeNode root) {
    // Initialize the queue for the BFS
    Queue<TreeNode> queue = new LinkedList<>();

    // Add the root to the queue
    queue.offer(root);

    // Initialize the minimumSwaps variable
    int minimumSwaps = 0;

    // Iterate over the queue untill it is not empty
    while (!queue.isEmpty()) {
      // Initialize the size of the level
      int size = queue.size();

      // Initialize the nodes array for the level nodes
      TreeNode[] nodes = new TreeNode[size];

      // Initialize the nodes sorted array for the level nodes
      int[] sortedNodesValue = new int[size];

      // Iterate over the level
      for (int index = 0; index < size; index++) {
        // Get the node from the queue
        TreeNode node = queue.poll();

        // Get the left and right child of the node
        TreeNode left = node.left;
        TreeNode right = node.right;

        // Add the values to the array
        nodes[index] = node;
        sortedNodesValue[index] = node.val;

        // Set the node.val to index
        indices[node.val] = index;

        // If left is not null then add the value to the queue
        if (left != null) {
          queue.offer(left);
        }

        // If right is not null then add the value to the queue
        if (right != null) {
          queue.offer(right);
        }
      }

      // Sort the sortedNodesValue
      Arrays.sort(sortedNodesValue);

      // Iterate over the level for sorting
      for (int index = 0; index < size; index++) {
        // If nodes value did not match to the sortedNodesValue then update the nodes
        if (nodes[index].val != sortedNodesValue[index]) {
          // Get the index of the sortedNodesValue[index]
          int i = indices[sortedNodesValue[index]];

          // Swap the indices map
          indices[nodes[index].val] = indices[nodes[index].val] ^ indices[nodes[i].val];
          indices[nodes[i].val] = indices[nodes[index].val] ^ indices[nodes[i].val];
          indices[nodes[index].val] = indices[nodes[index].val] ^ indices[nodes[i].val];

          // Swap the values
          nodes[index].val = nodes[index].val ^ nodes[i].val;
          nodes[i].val = nodes[index].val ^ nodes[i].val;
          nodes[index].val = nodes[index].val ^ nodes[i].val;

          // Increment the minimumSwaps variable
          minimumSwaps++;
        }
      }
    }

    // Return the minimumSwaps variable
    return minimumSwaps;
  }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

// Mock class for makeing the TreeNode Class
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode() {
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  // Helper method to make the binary tree from the array
  public static TreeNode makeTree(Integer[] arr) {
    if (arr == null || arr.length == 0 || arr[0] == null) {
      return null;
    }

    TreeNode root = new TreeNode(arr[0]);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    int i = 1;

    while (!queue.isEmpty() && i < arr.length) {
      TreeNode current = queue.poll();

      // Left child
      if (i < arr.length && arr[i] != null) {
        current.left = new TreeNode(arr[i]);
        queue.offer(current.left);
      }
      i++;

      // Right child
      if (i < arr.length && arr[i] != null) {
        current.right = new TreeNode(arr[i]);
        queue.offer(current.right);
      }
      i++;
    }

    return root;
  }
}

// Main Class
public class _2471_Minimum_Number_of_Operations_to_Sort_a_Binary_Tree_by_Level {
  // Main method to test minimumOperations
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 4, 3, 7, 6, 8, 5, null, null, null, null, 9, null, 10 });

    int result = new Solution

    ().minimumOperations(root);

    System.out.println(
        "The minimum number of operations needed to make the values at each level sorted in a strictly increasing order is : "
            + result);
  }
}
