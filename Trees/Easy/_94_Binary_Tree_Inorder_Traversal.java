/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-inorder-traversal/

Question: 94. Binary Tree Inorder Traversal

Problem Statement: Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]
Explanation:

Example 2:
Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
Output: [4,2,6,5,7,1,3,9,8]
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
Approach: Iterative Inorder Traversal Using a Stack

Goal:
- Return the inorder traversal of a binary tree.
- Inorder traversal visits nodes in the order:
      Left -> Root -> Right

Core Idea:
- Use an explicit stack to simulate the recursive
  call stack.
- Continuously move to the leftmost node while
  pushing every visited node onto the stack.
- When no further left child exists, pop the top
  node, process it, and then move to its right
  subtree.

Algorithm Steps:
1. Initialize an empty stack.
2. While the current node is not null or the stack
   is not empty:
   - Traverse to the leftmost node, pushing every
     visited node onto the stack.
   - Pop the top node from the stack.
   - Add its value to the result list.
   - Move to its right child.
3. Repeat until both the current node is null and
   the stack is empty.
4. Return the result list.

Why It Works:
- Every node is pushed onto the stack exactly once
  before its left subtree is processed.
- A node is processed immediately after its left
  subtree has been completely traversed.
- Its right subtree is then explored using the
  same procedure.
- This exactly follows the inorder traversal order
  without using recursion.

Time Complexity:
- O(n)

Space Complexity:
- O(h)

where h is the height of the binary tree. In the
worst case, h = n for a skewed tree.

Result:
- Returns a list containing the inorder traversal
  of the binary tree.
*/

package Trees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the inorder traversal of its nodes' values
  public List<Integer> inorderTraversal(TreeNode root) {
    // Initialize the order array list for the result
    ArrayList<Integer> order = new ArrayList<>();

    // Initialize the array as a stack
    TreeNode[] stack = new TreeNode[8];

    // Initialize the index variable
    int index = -1;

    // Iterate over the root node
    while (root != null || index > -1) {
      // If root node is not null then push it into the stack to its left
      while (root != null) {
        stack[++index] = root;
        root = root.left;
      }

      // Get the element from top of the stack
      root = stack[index--];

      // Add the value to the order array list
      order.add(root.val);

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
public class _94_Binary_Tree_Inorder_Traversal {
  // Main method to test insertionSortList
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9 });

    List<Integer> result = new Solution().inorderTraversal(root);

    System.out.println("The inorder traversal of its nodes' values is : " + result);
  }
}
