/*
LeetCode Problem: https://leetcode.com/problems/find-bottom-left-tree-value/

Question: 513. Find Bottom Left Tree Value

Problem Statement: Given the root of a binary tree, return the leftmost value in the last row of the tree.

Example 1:
Input: root = [2,1,3]
Output: 1

Example 2:
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7

Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -2^31 <= Node.val <= 2^31 - 1
*/

/*
Approach: Pre-order DFS with Depth Tracking
Goal:
- Find the leftmost value in the deepest level of
  the binary tree.
Core Idea:
- The leftmost node at the deepest level is the
  first node encountered at that depth in a
  pre-order (root, left, right) traversal.
- Track the maximum depth seen so far; whenever a
  node is visited at a depth strictly greater than
  the current maximum, it must be the leftmost node
  at that new deepest level.
- Update result only on strict depth increase, not
  equality, since the first node encountered at any
  new depth is always the leftmost at that depth.
Algorithm Steps:
1. Initialize maxDepth = 0 and result = 0.
2. Call dfs(root, 1) starting at depth 1.
3. In dfs(node, depth):
   a. If node is null, return.
   b. If depth > maxDepth:
      - Update result = node.val (this node is the
        leftmost at the new deepest level seen).
      - Update maxDepth = depth.
   c. Recurse dfs(node.left, depth + 1) before
      dfs(node.right, depth + 1), ensuring left
      nodes are always visited before right nodes
      at every depth.
4. Return result.
Why It Works:
- Pre-order traversal visits left subtrees before
  right subtrees at every level, so the first node
  encountered at any depth is always the leftmost
  node at that depth.
- Updating result only on strict depth increase
  ensures right subtree nodes at the same depth
  never overwrite an already-recorded leftmost node.
Time Complexity:
- O(n)
where n is the number of nodes, since every node is
visited exactly once.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack. Worst case O(n) for a skewed tree.
Result:
- Returns the value of the leftmost node at the
  deepest level of the tree.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the maxDepth variable
  private int maxDepth = 0;

  // Initialize the result variable
  private int result = 0;

  // Method to find the leftmost value in the last row of the tree
  public int findBottomLeftValue(TreeNode root) {
    // Call the recursive dfs call
    this.dfs(root, 1);

    // Return the result
    return result;
  }

  // Helper method to find the left most value
  private void dfs(TreeNode root, int depth) {
    // If root is null then return
    if (root == null) {
      return;
    }

    // If maxDepth is less then depth the update the result and maxDepth
    if (this.maxDepth < depth) {
      this.result = root.val;
      this.maxDepth = depth;
    }

    // Call the recursive method on the left and right child
    this.dfs(root.left, depth + 1);
    this.dfs(root.right, depth + 1);
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
public class _513_Find_Bottom_Left_Tree_Value {
  // Main method to test findBottomLeftValue
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, null, 5, 6, null, null, 7 });

    int result = new Solution().findBottomLeftValue(root);

    System.out.println("The leftmost value in the last row of the tree is : " + result);
  }
}
