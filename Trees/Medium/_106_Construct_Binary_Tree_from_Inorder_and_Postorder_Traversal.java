/*
LeetCode Problem: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

Question: 106. Construct Binary Tree from Inorder and Postorder Traversal

Problem Statement: Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: inorder = [-1], postorder = [-1]
Output: [-1]

Constraints:
    1 <= inorder.length <= 3000
    postorder.length == inorder.length
    -3000 <= inorder[i], postorder[i] <= 3000
    inorder and postorder consist of unique values.
    Each value of postorder also appears in inorder.
    inorder is guaranteed to be the inorder traversal of the tree.
    postorder is guaranteed to be the postorder traversal of the tree.
*/

/*
Approach: Reverse Post-order Traversal with Inorder Boundary Validation
Goal:
- Reconstruct a binary tree from its inorder and
  postorder traversal sequences.
- Return the root of the reconstructed tree.
Core Idea:
- The last element in postorder is always the root.
- In inorder, elements to the left of the root are
  in the left subtree; elements to the right are in
  the right subtree.
- Process postorder in reverse (from right to left),
  building nodes top-down, and track the inorder
  position from right to left as a boundary marker.
- A limit value marks the boundary: once the
  inorder index reaches a value equal to limit, all
  nodes in that recursive scope have been placed,
  signaling when to stop and backtrack.
Algorithm Steps:
1. Initialize inorderIndex = inorder.length - 1 and
   postorderIndex = postorder.length - 1 (both start
   at the rightmost positions).
2. Call build(Integer.MAX_VALUE, inorder, postorder)
   to begin reconstruction with no initial boundary.
3. In build(limit, inorder, postorder):
   a. If postorderIndex < 0, all postorder elements
      have been consumed, return null.
   b. If inorder[inorderIndex] == limit, the
      inorder boundary has been reached (no more
      nodes belong to this subtree), decrement
      inorderIndex and return null.
   c. Create a new TreeNode with value
      postorder[postorderIndex--], consuming the
      current postorder element (which is the root
      of this subtree).
   d. Recursively build the right subtree by calling
      build(root.val, inorder, postorder), passing
      root.val as the new limit (all nodes in the
      right subtree must have values less than
      root.val when scanning inorder from right).
   e. Recursively build the left subtree by calling
      build(limit, inorder, postorder), using the
      original limit (left subtree nodes are
      bounded by the original limit).
   f. Return the constructed root.
4. The initial call's returned node is the tree root.
Why It Works:
- Postorder's last element is always the subtree
  root; processing postorder in reverse means
  processing roots before children, enabling
  top-down construction.
- The inorder sequence partitions around each root:
  elements to the left (when scanning right-to-left)
  belong to the left subtree; elements to the right
  belong to the right subtree.
- The limit parameter enforces this partition
  implicitly: when inorderIndex reaches a value
  equal to limit, all inorder elements in the
  current range have been placed, so stop.
- Swapping left and right recursion order (right
  first) aligns with reverse postorder processing,
  since postorder visits left, then right, then
  root; reversing means the root is encountered
  first, then right subtree elements, then left.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
created exactly once and each index is decremented
a constant number of times.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack. Worst case O(n) for a skewed tree.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the index for the inorder and postorder
  private int inorderIndex;
  private int postorderIndex;

  // Method to find the binary tree
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    // Initialize the inorder and postorder index
    this.inorderIndex = inorder.length - 1;
    this.postorderIndex = postorder.length - 1;

    // Return the recursive build tree call
    return this.build(Integer.MAX_VALUE, inorder, postorder);
  }

  // Helper method to build the tree
  private TreeNode build(int limit, int[] inorder, int[] postorder) {
    // If postorderIndex is less than 0 then return null
    if (this.postorderIndex < 0) {
      return null;
    }

    // If postorderIndex is equal to limit then decrement the inorderIndex and
    // return null
    if (inorder[this.inorderIndex] == limit) {
      this.inorderIndex--;
      return null;
    }

    // Initialize the new root node
    TreeNode root = new TreeNode(postorder[this.postorderIndex--]);

    // Fill the left and the right value of the root
    root.right = this.build(root.val, inorder, postorder);
    root.left = this.build(limit, inorder, postorder);

    // Return the root
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
public class _106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
  // Main method to test buildTree
  public static void main(String[] args) {
    int[] inorder = new int[] { 9, 3, 15, 20, 7 };
    int[] postorder = new int[] { 9, 15, 7, 20, 3 };

    TreeNode result = new Solution().buildTree(inorder, postorder);

    System.out.println("The binary tree is : " + result);
  }
}
