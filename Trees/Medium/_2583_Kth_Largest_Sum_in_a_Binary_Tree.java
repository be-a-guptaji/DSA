/*
LeetCode Problem: https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/

Question: 2471. Minimum Number of Operations to Sort a Binary Tree by Level

Problem Statement: You are given the root of a binary tree and a positive integer k.

The level sum in the tree is the sum of the values of the nodes that are on the same level.

Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.

Note that two nodes are on the same level if they have the same distance from the root.

Example 1:
Input: root = [5,8,9,2,1,3,7,4,6], k = 2
Output: 13
Explanation: The level sums are the following:
- Level 1: 5.
- Level 2: 8 + 9 = 17.
- Level 3: 2 + 1 + 3 + 7 = 13.
- Level 4: 4 + 6 = 10.
The 2nd largest level sum is 13.

Example 2:
Input: root = [1,2,null,3], k = 1
Output: 3
Explanation: The largest level sum is 3.

Constraints:
    The number of nodes in the tree is n.
    2 <= n <= 10^5
    1 <= Node.val <= 10^6
    1 <= k <= n
*/

/*
Approach: Level-order Sum with Fixed-size Min-Heap
Goal:
- Compute the sum of node values at every level of
  the binary tree.
- Return the kth largest such level sum.
- Return -1 if the tree has fewer than k levels.
Core Idea:
- BFS naturally processes the tree one level at a
  time, making it straightforward to compute each
  level's sum in a single pass.
- To find the kth largest value among a stream of
  sums without storing and sorting all of them, use
  a min-heap capped at size k.
- A min-heap of size k always keeps the k largest
  values seen so far, with the smallest of those k
  sitting at the top.
- Any new sum only needs to be considered if it is
  larger than the current minimum among the top k.
Algorithm Steps:
1. Initialize a min-heap (PriorityQueue<Long>) to
   hold at most k level sums.
2. Initialize a queue for BFS and offer the root.
3. While the queue is not empty:
   a. Record the current level size.
   b. Initialize sum to 0.
   c. Poll exactly that many nodes:
      - Add each node's value to sum.
      - Enqueue any non-null left or right child.
   d. Update the min-heap:
      - If the heap has fewer than k elements,
        offer sum directly.
      - Else if sum is greater than the heap's
        current minimum (peek), remove the minimum
        and offer sum.
      - Otherwise, discard sum since it cannot be
        among the k largest.
4. After processing all levels:
   - If the heap contains exactly k elements, its
     top (peek) is the kth largest level sum.
   - Otherwise, fewer than k levels exist, so
     return -1.
Why It Works:
- The min-heap invariant guarantees that at any
  point, it holds exactly the k largest sums
  encountered so far (or fewer, if fewer than k
  levels have been processed).
- The smallest among these k values, sitting at the
  heap's root, is by definition the kth largest
  overall once all levels have been processed.
- Discarding sums smaller than or equal to the
  current minimum is safe because such a sum cannot
  be among the top k if the heap is already full
  with k larger (or equal) values.
- Using long for sums avoids overflow, since node
  values summed across a wide level can exceed the
  int range depending on constraints.
Time Complexity:
- O(n log k)
where n is the number of nodes in the tree. Each of
the up to n/1 level sums triggers at most one
heap insertion and possibly one removal, each
O(log k).
Space Complexity:
- O(w + k)
where w is the maximum level width, for the BFS
queue, and k for the min-heap. For a perfect binary
tree, w = O(n) at the last level.
Result:
- Returns the kth largest level sum as a long, or
  -1 if the tree has fewer than k levels.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the kth largest level sum in the tree
  public long kthLargestLevelSum(TreeNode root, int k) {
    // Initialize the minheap
    PriorityQueue<Long> minHeap = new PriorityQueue<>();

    // Initialize the queue for the BFS traversal
    Queue<TreeNode> queue = new LinkedList<>();

    // Add the root to the queue
    queue.offer(root);

    // Iterate over the queue untill it become empty
    while (!queue.isEmpty()) {
      // Initialize the size of the level
      int size = queue.size();

      // Initialize the sum variable
      long sum = 0L;

      // Iterate over the level of the tree
      for (int i = 0; i < size; i++) {
        // Get the node from the queue
        TreeNode node = queue.poll();

        // Get the left and right child of the node
        TreeNode left = node.left;
        TreeNode right = node.right;

        // Add the value to the sum
        sum += node.val;

        // If left is not null then add the value to the queue
        if (left != null) {
          queue.offer(left);
        }

        // If right is not null then add the value to the queue
        if (right != null) {
          queue.offer(right);
        }
      }

      // Update the minHeap accordingly
      if (minHeap.size() < k) {
        minHeap.offer(sum);
      } else if (minHeap.peek() < sum) {
        minHeap.poll();
        minHeap.offer(sum);
      }
    }

    // If minHeap size is equal to k then return the top element else -1
    return minHeap.size() == k ? minHeap.peek() : -1L;
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
public class _2583_Kth_Largest_Sum_in_a_Binary_Tree {
  // Main method to test kthLargestLevelSum
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 5, 8, 9, 2, 1, 3, 7, 4, 6 });
    int k = 2;

    long result = new Solution().kthLargestLevelSum(root, k);

    System.out.println("The kth largest level sum in the tree is : " + result);
  }
}
