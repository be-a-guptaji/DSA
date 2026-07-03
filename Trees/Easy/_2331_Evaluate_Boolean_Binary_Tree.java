/*
LeetCode Problem: https://leetcode.com/problems/evaluate-boolean-binary-tree/

Question: 2331. Evaluate Boolean Binary Tree

Problem Statement: You are given the root of a full binary tree with the following properties:

    Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
    Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and 3 represents the boolean AND.

The evaluation of a node is as follows:

    If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
    Otherwise, evaluate the node's two children and apply the boolean operation of its value with the children's evaluations.

Return the boolean result of evaluating the root node.

A full binary tree is a binary tree where each node has either 0 or 2 children.

A leaf node is a node that has zero children.

Example 1:
Input: root = [2,1,3,null,null,0,1]
Output: true
Explanation: The above diagram illustrates the evaluation process.
The AND node evaluates to False AND True = False.
The OR node evaluates to True OR False = True.
The root node evaluates to True, so we return true.

Example 2:
Input: root = [0]
Output: false
Explanation: The root node is a leaf node and it evaluates to false, so we return false.

Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    0 <= Node.val <= 3
    Every node has either 0 or 2 children.
    Leaf nodes have a value of 0 or 1.
    Non-leaf nodes have a value of 2 or 3.
*/

/*
Approach: Recursive Depth-First Evaluation

Goal:
- Evaluate the boolean expression represented by a
  binary tree.
- Leaf nodes represent boolean values:
      0 -> false
      1 -> true
- Internal nodes represent operators:
      2 -> OR
      3 -> AND

Core Idea:
- Recursively evaluate the left and right
  subtrees.
- If the current node is an operator, combine the
  results of its children using the corresponding
  boolean operation.
- If the current node is a leaf, return its
  boolean value.

Algorithm Steps:
1. If the current node is an OR operator:
   - Recursively evaluate the left subtree.
   - Recursively evaluate the right subtree.
   - Return the logical OR of both results.
2. If the current node is an AND operator:
   - Recursively evaluate the left subtree.
   - Recursively evaluate the right subtree.
   - Return the logical AND of both results.
3. Otherwise, the current node is a leaf:
   - Return true if its value is 1.
   - Return false if its value is 0.

Why It Works:
- Every subtree represents a valid boolean
  expression.
- Recursively evaluating the children ensures
  their values are available before evaluating the
  parent operator.
- The recursion naturally evaluates the expression
  from the leaves toward the root, producing the
  correct final boolean value.

Time Complexity:
- O(n)

where n is the number of nodes in the tree.

Space Complexity:
- O(h)

where h is the height of the tree due to the
recursion stack.

Result:
- Returns the boolean value represented by the
  expression tree.
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the the boolean result of evaluating the root node
  public boolean evaluateTree(TreeNode root) {
    // Call the helper method
    return this.helper(root);
  }

  // Helper method to find the result
  private boolean helper(TreeNode root) {
    // Evaluate the value according to the operator
    if (root.val == 2) {
      return helper(root.left) || helper(root.right);
    } else if (root.val == 3) {
      return helper(root.left) && helper(root.right);
    }

    // Return value if its a value
    return root.val == 1;
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
public class _2331_Evaluate_Boolean_Binary_Tree {
  // Main method to test evaluateTree
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 2, 1, 3, null, null, 0, 1 });

    boolean result = new Solution().evaluateTree(root);

    System.out.println("The boolean result of evaluating the root node is : " + result);
  }
}
