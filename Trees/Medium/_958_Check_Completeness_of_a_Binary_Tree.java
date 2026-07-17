/*
LeetCode Problem: https://leetcode.com/problems/check-completeness-of-a-binary-tree/

Question: 958. Check Completeness of a Binary Tree

Problem Statement: Given the root of a binary tree, determine if it is a complete binary tree.

In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

Constraints:
    The number of nodes in the tree is in the range [1, 100].
    1 <= Node.val <= 1000
*/

/*
Approach: Level-order BFS with Null-Sentinel Termination Check
Goal:
- Determine whether the binary tree is a complete
  binary tree (all levels fully filled except
  possibly the last level, which is filled left to
  right with no gaps).
Core Idea:
- In a complete binary tree, once a null child is
  encountered during level-order traversal, all
  remaining nodes in the queue must also be null.
- Any non-null node appearing after a null node
  indicates a gap or misalignment violating the
  completeness property.
- Enqueue both left and right children of every
  node (including nulls as queue entries), allowing
  null nodes to act as natural sentinels marking the
  boundary between complete and incomplete regions.
Algorithm Steps:
1. Initialize a queue for BFS and a boolean flag
   nullSeen = false to track whether any null has
   been encountered.
2. Enqueue the root node.
3. While the queue is not empty:
   a. Dequeue the current node.
   b. If the node is not null:
      - If nullSeen is already true, a non-null
        node has appeared after a null node, so
        return false immediately (tree is
        incomplete).
      - Otherwise, enqueue both node.left and
        node.right (whether they are null or not).
   c. If the node is null:
      - Set nullSeen = true and continue (do not
        enqueue further, as null has no children).
4. If the loop completes without returning false,
   the tree is complete, so return true.
Why It Works:
- A complete binary tree has the invariant that no
  non-null node can appear after the first null in
  a level-order sequence (when including nulls as
  queue entries).
- Enqueuing children even when they are null
  preserves this invariant: null entries act as
  fences, and attempting to enqueue non-null
  children of null nodes is prevented by the
  "if (node != null)" guard.
- The moment a non-null node is encountered after
  nullSeen becomes true, a gap has been detected,
  confirming incompleteness.
- If no such violation occurs by queue exhaustion,
  the tree satisfies completeness.
Time Complexity:
- O(n)
where n is the number of nodes, since each node
(and null slot) is enqueued and dequeued exactly
once.
Space Complexity:
- O(w)
where w is the maximum level width (queue size at
any point). For a perfect binary tree, w = O(n) at
the last level; for a complete tree constrained by
the completeness property, w is typically O(sqrt(n))
on average.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find if the tree is a complete binary tree
  public boolean isCompleteTree(TreeNode root) {
    // Initialize the queue for the BFS
    Queue<TreeNode> queue = new LinkedList<>();

    // Initialize the nullSeen boolean variable
    boolean nullSeen = false;

    // Add the root value to the queue
    queue.offer(root);

    // Iterate over the queue untill its empty
    while (!queue.isEmpty()) {
      // Get the node from the queue
      TreeNode node = queue.poll();

      // If node is not null then add the values to the queue
      if (node != null) {
        if (nullSeen) {
          return false;
        }
        queue.add(node.left);
        queue.add(node.right);
      } else {
        nullSeen = true;
      }
    }

    // Return true in the end
    return true;
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
public class _958_Check_Completeness_of_a_Binary_Tree {
  // Main method to test isCompleteTree
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, 5, 6 });

    boolean result = new Solution().isCompleteTree(root);

    System.out.println("The tree is" + (result ? " " : " not ") + "a complete binary tree.");
  }
}
