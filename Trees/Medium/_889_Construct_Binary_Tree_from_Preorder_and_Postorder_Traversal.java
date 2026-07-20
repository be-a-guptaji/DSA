/*
LeetCode Problem: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

Question: 889. Construct Binary Tree from Preorder and Postorder Traversal

Problem Statement: Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.

If there exist multiple answers, you can return any of them.

Example 1:
Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]

Example 2:
Input: preorder = [1], postorder = [1]
Output: [1]

Constraints:
    1 <= preorder.length <= 30
    1 <= preorder[i] <= preorder.length
    All the values of preorder are unique.
    postorder.length == preorder.length
    1 <= postorder[i] <= postorder.length
    All the values of postorder are unique.
    It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
*/

/*
Approach: Index-based Recursion with Postorder Lookup
Goal:
- Reconstruct a binary tree from its preorder and
  postorder traversal sequences.
- Return the root of the reconstructed tree.
Core Idea:
- Preorder visits root, then left subtree, then
  right subtree.
- Postorder visits left subtree, then right subtree,
  then root.
- The first element in any preorder range is the
  root of that subtree.
- The second element in preorder (if it exists) is
  the root of the left subtree.
- Find that left subtree root's position in
  postorder; all elements up to and including that
  position belong to the left subtree.
- Use a lookup map to find postorder positions in
  O(1) time.
Algorithm Steps:
1. Build a postorderLookup map: value -> index,
   to quickly find any element's position in
   postorder.
2. Call build(0, preorder.length - 1, 0, preorder,
   postorder) with the full range.
3. In build(i1, i2, j1, preorder, postorder):
   a. If i1 > i2, the range is empty, return null.
   b. Create root node from preorder[i1] (first
      element in the current preorder range is the
      root).
   c. If i1 == i2, this node is a leaf with no
      children, return it.
   d. Otherwise, extract the left subtree root:
      - leftVal = preorder[i1 + 1] (next element
        in preorder is the left subtree's root).
      - mid = postorderLookup[leftVal] (find
        leftVal's position in postorder).
      - leftSize = mid - j1 + 1 (count of nodes
        in the left subtree from postorder start
        j1 to mid inclusive).
   e. Build the left subtree:
      - build(i1 + 1, i1 + leftSize, j1, ...)
        (consume leftSize + 1 elements from
        preorder, starting after the root).
   f. Build the right subtree:
      - build(i1 + leftSize + 1, i2, mid + 1, ...)
        (remaining preorder elements; postorder
        starts after the left subtree at mid + 1).
   g. Return root.
4. The initial call's returned node is the tree
   root.
Why It Works:
- Preorder's first element is always the subtree
  root, allowing top-down construction.
- The second element (if it exists) is the left
  subtree's root, pinpointing the boundary between
  left and right subtrees.
- Finding that element in postorder immediately
  reveals how many nodes belong to the left subtree,
  since postorder ends with the root and groups the
  left subtree contiguously before the right.
- The lookup map avoids O(n) searches for each
  node, keeping overall complexity linear.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
created once and each index is examined a constant
number of times.
Space Complexity:
- O(n)
for the postorderLookup map plus O(h) for the
recursive call stack, where h is the tree height.
Result:
- Returns the root of the reconstructed binary tree.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the lookup map for the postorder
  private int[] postorderLookup;

  // Method to find the binary tree
  public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
    // Initialize the lookup map for the postorder
    this.postorderLookup = new int[postorder.length + 1];

    // Fill the lookup
    for (int i = 0; i < postorder.length; i++) {
      this.postorderLookup[postorder[i]] = i;
    }

    // Return the build method
    return this.build(0, preorder.length - 1, 0, preorder, postorder);
  }

  // Helper method to build the tree
  private TreeNode build(int i1, int i2, int j1, int[] preorder, int[] postorder) {
    // If pointers crosses each other then return null
    if (i1 > i2) {
      return null;
    }

    // Initialize the root node
    TreeNode root = new TreeNode(preorder[i1]);

    // If preorders pointers is not same then build the left subtree
    if (i1 != i2) {
      // Get the left root
      int leftVal = preorder[i1 + 1];

      // Get the mid value
      int mid = this.postorderLookup[leftVal];

      // Get the left size
      int leftSize = mid - j1 + 1;

      // Build the left subtree
      root.left = this.build(i1 + 1, i1 + leftSize, j1, preorder, postorder);

      // Build the right subtree
      root.right = this.build(i1 + leftSize + 1, i2, mid + 1, preorder, postorder);
    }

    // Return root
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
public class _889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal {
  // Main method to test constructFromPrePost
  public static void main(String[] args) {
    int[] preorder = new int[] { 1, 2, 4, 5, 3, 6, 7 };
    int[] postorder = new int[] { 4, 5, 2, 6, 7, 3, 1 };

    TreeNode result = new Solution().constructFromPrePost(preorder, postorder);

    System.out.println("The binary tree is : " + result);
  }
}
