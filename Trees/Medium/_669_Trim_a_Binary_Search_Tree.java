/*
LeetCode Problem: https://leetcode.com/problems/trim-a-binary-search-tree/

Question: 669. Trim a Binary Search Tree

Problem Statement: Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.

Example 1:
Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]

Example 2:
Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]

Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    0 <= Node.val <= 10^4
    The value of each node in the tree is unique.
    root is guaranteed to be a valid binary search tree.
    0 <= low <= high <= 10^4
*/

/*
Approach: Recursive BST Pruning with Range Validation
Goal:
- Remove all nodes outside the range [low, high]
  from a BST while preserving the BST property.
Core Idea:
- BST ordering allows early subtree elimination:
  if a node's value exceeds high, its entire right
  subtree also exceeds high (discard the node, keep
  trimming left); if it falls below low, its entire
  left subtree also falls below low (discard the
  node, keep trimming right).
- Nodes within range are kept; their children are
  recursively trimmed.
Algorithm Steps:
1. If node is null, return null (base case).
2. If node.val > high:
   - The node and its right subtree are all out of
     range; recurse into the left subtree, which
     may still contain valid nodes.
3. If node.val < low:
   - The node and its left subtree are all out of
     range; recurse into the right subtree, which
     may still contain valid nodes.
4. Otherwise node.val is within [low, high]:
   - Recursively trim node.left and node.right.
   - Return the node with updated children.
Why It Works:
- BST invariant guarantees all nodes in the right
  subtree are greater than the current node and all
  nodes in the left subtree are less, enabling
  whole-subtree elimination without visiting every
  node in that subtree.
- Returning the trimmed subtree root directly
  re-links the tree correctly without needing a
  separate parent pointer update.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
visited at most once.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack. Worst case O(n) for a skewed tree.
Result:
- Returns the root of the trimmed BST containing
  only nodes with values within [low, high].
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the trimmed binary search tree
  public TreeNode trimBST(TreeNode root, int low, int high) {
    // If root is null then return null
    if (root == null) {
      return null;
    }

    // If root.val is greater then high then trim the tree
    if (root.val > high) {
      return this.trimBST(root.left, low, high);
    }

    // If root.val is less then low then trim the tree
    if (root.val < low) {
      return this.trimBST(root.right, low, high);
    }

    // Update the left and right subtree
    root.left = this.trimBST(root.left, low, high);
    root.right = this.trimBST(root.right, low, high);

    // Return root in the end
    return root;
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
public class _669_Trim_a_Binary_Search_Tree {
  // Main method to test trimBST
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 3, 0, 4, null, 2, null, null, 1 });
    int low = 1;
    int high = 3;

    TreeNode result = new Solution().trimBST(root, low, high);

    System.out.println("The trimmed binary search tree is : " + result);
  }
}
