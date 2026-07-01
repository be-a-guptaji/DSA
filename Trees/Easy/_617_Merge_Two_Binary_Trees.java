/*
LeetCode Problem: https://leetcode.com/problems/merge-two-binary-trees/

Question: 617. Merge Two Binary Trees

Problem Statement: You are given two binary trees root1 and root2.

Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.

Return the merged tree.

Note: The merging process must start from the root nodes of both trees.

Example 1:
Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
Output: [3,4,5,5,4,null,7]

Example 2:
Input: root1 = [1], root2 = [1,2]
Output: [2,2]

Constraints:
    The number of nodes in both trees is in the range [0, 2000].
    -10^4 <= Node.val <= 10^4
*/

/*
Approach: Recursive Depth-First Search

Goal:
- Merge two binary trees into a single tree.
- If two nodes overlap, their values are summed.
- If only one node exists at a position, that node
  becomes part of the merged tree.

Core Idea:
- Traverse both trees simultaneously.
- At each pair of corresponding nodes:
  - If one node is null, return the other node.
  - Otherwise, add the values of both nodes.
- Recursively merge the left and right subtrees.
- Reuse the nodes of the first tree to construct
  the merged tree.

Algorithm Steps:
1. If the first node is null, return the second
   node.
2. If the second node is null, return the first
   node.
3. Add the value of the second node to the first
   node.
4. Recursively merge the left children.
5. Recursively merge the right children.
6. Return the updated first node.

Why It Works:
- Every pair of corresponding nodes is processed
  exactly once.
- When both nodes exist, their values are combined
  and their subtrees are recursively merged.
- When only one node exists, it is directly
  attached to the merged tree, preserving all
  existing nodes.
- The recursion naturally reconstructs the merged
  tree while maintaining the original structure.

Time Complexity:
- O(min(n, m))

where n and m are the number of nodes in the two
trees.

Space Complexity:
- O(h)

where h is the maximum height of the recursion
stack.

Result:
- Returns the root of the merged binary tree.
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the merged tree
  public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    // If root1 is null then return root2
    if (root1 == null) {
      return root2;
    }

    // If root2 is null then return root1
    if (root2 == null) {
      return root1;
    }

    // Update the root1.val
    root1.val += root2.val;

    // Call the recursive call on the left and right pointer of the root1
    root1.left = mergeTrees(root1.left, root2.left);
    root1.right = mergeTrees(root1.right, root2.right);

    // Return the root1
    return root1;
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
public class _617_Merge_Two_Binary_Trees {
  // Main method to test mergeTrees
  public static void main(String[] args) {
    TreeNode root1 = TreeNode.makeTree(new Integer[] { 1, 3, 2, 5 });
    TreeNode root2 = TreeNode.makeTree(new Integer[] { 2, 1, 3, null, 4, null, 7 });

    TreeNode result = new Solution().mergeTrees(root1, root2);

    System.out.println("The merged tree is : " + result);
  }
}
