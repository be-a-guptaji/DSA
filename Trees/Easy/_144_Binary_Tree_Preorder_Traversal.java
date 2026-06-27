/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-preorder-traversal/

Question: 144. Binary Tree Preorder Traversal

Problem Statement: Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]
Explanation:

Example 2:
Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
Output: [1,2,4,5,6,7,3,8,9]
Explanation:

Example 3:
Input: root = []
Output: []

Example 4:
Input: root = [1]
Output: [1]

Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*
Approach: Iterative Preorder Traversal Using a Stack

Goal:
- Return the preorder traversal of a binary tree.
- Preorder traversal visits nodes in the order:
      Root -> Left -> Right

Core Idea:
- Use an explicit stack to simulate the recursive
  traversal.
- Visit each node immediately when it is first
  encountered.
- Push the node onto the stack before moving to
  its left child.
- Once the left subtree has been fully traversed,
  backtrack using the stack and continue with the
  right subtree.

Algorithm Steps:
1. Initialize an empty stack.
2. While the current node is not null or the stack
   is not empty:
   - Traverse down the left subtree.
   - For each visited node:
     - Add its value to the result list.
     - Push it onto the stack.
     - Move to its left child.
   - Pop the top node from the stack.
   - Move to its right child.
3. Continue until both the current node is null
   and the stack is empty.
4. Return the result list.

Why It Works:
- Every node is visited before its children,
  satisfying the preorder traversal order.
- The stack preserves the path back to ancestor
  nodes after completing a left subtree.
- Each node is pushed and popped exactly once,
  ensuring an efficient traversal without
  recursion.

Time Complexity:
- O(n)

Space Complexity:
- O(h)

where h is the height of the binary tree. In the
worst case, h = n for a skewed tree.

Result:
- Returns a list containing the preorder
  traversal of the binary tree.
*/

package Trees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the preorder traversal of its nodes' values
  public List<Integer> preorderTraversal(TreeNode root) {
    // Initialize the order array list for the result
    ArrayList<Integer> order = new ArrayList<>();

    // Initialize the array as a stack
    TreeNode[] stack = new TreeNode[9];

    // Initialize the index variable
    int index = -1;

    // Iterate over the root node
    while (root != null || index > -1) {
      // If root node is not null then push it into the stack to its left
      while (root != null) {
        // Add the value to the order array list
        order.add(root.val);

        stack[++index] = root;
        root = root.left;
      }

      // Get the element from top of the stack
      root = stack[index--];

      // Update the root node
      root = root.right;
    }

    // Return order array list
    return order;
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
public class _144_Binary_Tree_Preorder_Traversal {
  // Main method to test preorderTraversal
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9 });

    List<Integer> result = new Solution().preorderTraversal(root);

    System.out.println("The preorder traversal of its nodes' values is : " + result);
  }
}
