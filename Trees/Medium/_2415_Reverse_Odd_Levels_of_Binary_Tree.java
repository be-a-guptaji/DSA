/*
LeetCode Problem: https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/

Question: 2415. Reverse Odd Levels of Binary Tree

Problem Statement: Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

    For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].

Return the root of the reversed tree.

A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

The level of a node is the number of edges along the path between it and the root node.

Example 1:
Input: root = [2,3,5,8,13,21,34]
Output: [2,5,3,8,13,21,34]
Explanation: 
The tree has only one odd level.
The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.

Example 2:
Input: root = [7,13,11]
Output: [7,11,13]
Explanation: 
The nodes at level 1 are 13, 11, which are reversed and become 11, 13.

Example 3:
Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
Explanation: 
The odd levels have non-zero values.
The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.

Constraints:
    The number of nodes in the tree is in the range [1, 2^14].
    0 <= Node.val <= 10^5
    root is a perfect binary tree.
*/

/*
Approach: Symmetric DFS with Odd-Level Value Swapping

Goal:
- Reverse the node values at every odd level of a
  perfect binary tree.
- Preserve the original tree structure.
- Return the updated root.

Core Idea:
- Nodes at the same level can be processed in
  symmetric pairs.
- For two symmetric nodes:
   - The left child of the left node is symmetric
     to the right child of the right node.
   - The right child of the left node is symmetric
     to the left child of the right node.
- Start with root.left and root.right, which are
  the nodes at level 1.
- Swap their values when the current level is odd.
- Recursively process their symmetric children.
- Toggle the level parity after every recursive
  call.

Algorithm Steps:
1. Call the helper method with:
   - root.left as the left symmetric node.
   - root.right as the right symmetric node.
   - true because level 1 is an odd level.

2. In the helper method:
   - If either symmetric node is null, return.

3. If the current level is odd:
   - Store left.val in a temporary variable.
   - Assign right.val to left.val.
   - Assign the temporary value to right.val.

4. Process the outer symmetric pair:
   - Recursively call the helper with left.left
     and right.right.

5. Process the inner symmetric pair:
   - Recursively call the helper with left.right
     and right.left.

6. Toggle isOddLevel for both recursive calls.

7. After all symmetric node pairs are processed,
   return the original root.

Why It Works:
- A perfect binary tree has matching nodes at
  symmetric positions on every level.
- Passing left.left with right.right and left.right
  with right.left ensures that corresponding nodes
  are processed as mirror pairs.
- Swapping the values of every symmetric pair on
  an odd level reverses the entire sequence of node
  values at that level.
- Nodes on even levels are visited but their values
  are not changed.
- Only node values are swapped, so the tree
  structure remains unchanged.

Time Complexity:
- O(n)

where n is the number of nodes in the tree because
each node is visited once.

Space Complexity:
- O(h)

where h is the height of the tree due to the
recursive call stack.

For a perfect binary tree:
- h = O(log n)

Result:
- Returns the root of the tree after reversing all
  node values at every odd level.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the root of the reversed tree
  public TreeNode reverseOddLevels(TreeNode root) {
    // Call the swap variable
    this.swap(root.left, root.right, true);

    // Return the updated root in the end
    return root;
  }

  // Helper method to swap the odd level value
  private void swap(TreeNode left, TreeNode right, boolean isOddLevel) {
    // If left or right is null then return
    if (left == null || right == null) {
      return;
    }

    // If it is odd level then swap the values
    if (isOddLevel) {
      // Initialize the temp variable
      int temp = left.val;

      // Swap the values
      left.val = right.val;
      right.val = temp;
    }

    // Swap the values
    this.swap(left.left, right.right, !isOddLevel);
    this.swap(left.right, right.left, !isOddLevel);
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
public class _2415_Reverse_Odd_Levels_of_Binary_Tree {
  // Main method to test reverseOddLevels
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 2, 3, 5, 8, 13, 21, 34 });

    TreeNode result = new Solution().reverseOddLevels(root);

    System.out.println("The root of the reversed tree is : " + result);
  }
}
