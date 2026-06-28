/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-postorder-traversal/

Question: 145. Binary Tree Postorder Traversal

Problem Statement: Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]
Explanation:

Example 2:
Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
Output: [4,6,7,5,2,9,8,3,1]
Explanation:

Example 3:
Input: root = []
Output: []

Example 4:
Input: root = [1]
Output: [1]

Constraints:
    The number of the nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*
Approach: Iterative Postorder Traversal Using a Stack and Visited Flag

Goal:
- Return the postorder traversal of a binary tree.
- Postorder traversal visits nodes in the order:
      Left -> Right -> Root

Core Idea:
- Use an explicit stack to simulate the recursive
  traversal.
- Associate each node with a visited flag.
- When a node is encountered for the first time,
  push it back onto the stack as visited, followed
  by its right and left children.
- When the visited node is encountered again, both
  of its subtrees have already been processed, so
  its value can be added to the result.

Algorithm Steps:
1. Initialize a stack containing the root node
   marked as not visited.
2. While the stack is not empty:
   - Pop the top node and its visited flag.
   - If the node is null, continue.
   - If the node is already marked as visited,
     add its value to the result.
   - Otherwise:
     - Push the node back onto the stack marked
       as visited.
     - Push its right child marked as not visited.
     - Push its left child marked as not visited.
3. Continue until the stack becomes empty.
4. Return the result list.

Why It Works:
- Every node is processed twice.
- During the first visit, the node schedules
  itself to be processed after both of its
  children.
- Since the left child is pushed after the right
  child, it is processed first because of the
  stack's LIFO order.
- When the node is encountered again with its
  visited flag set, both its left and right
  subtrees have already been traversed, producing
  the required postorder sequence.

Time Complexity:
- O(n)

Space Complexity:
- O(h)

where h is the height of the binary tree. In the
worst case, h = n for a skewed tree.

Result:
- Returns a list containing the postorder
  traversal of the binary tree.
*/

package Trees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the postorder traversal of its nodes' values
  public List<Integer> postorderTraversal(TreeNode root) {
    // Initialize the stack size variable
    final int STACK_SIZE = 22;

    // Initialize the order array list for the result
    ArrayList<Integer> order = new ArrayList<>();

    // Initialize the array as a stack
    TreeNode[] stack = new TreeNode[STACK_SIZE];

    // Initialize the array of visited
    Boolean[] visited = new Boolean[STACK_SIZE];

    // Initialize the index variable
    int index = -1;

    // Add the value to the stack and visted array
    stack[++index] = root;
    visited[index] = false;

    // Iterate over the root node
    while (index > -1) {
      // Get the current node from the stack
      TreeNode current = stack[index];

      // Get the visited status of the current node
      boolean visit = visited[index--];

      // Update the stack and order list
      if (current != null) {
        if (visit) {
          order.add(current.val);
        } else {
          stack[++index] = current;
          visited[index] = true;
          stack[++index] = current.right;
          visited[index] = false;
          stack[++index] = current.left;
          visited[index] = false;
        }
      }
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
public class _145_Binary_Tree_Postorder_Traversal {
  // Main method to test postorderTraversal
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9 });

    List<Integer> result = new Solution().postorderTraversal(root);

    System.out.println("The postorder traversal of its nodes' values is : " + result);
  }
}
