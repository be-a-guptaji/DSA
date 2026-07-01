/*
LeetCode Problem: https://leetcode.com/problems/range-sum-of-bst/

Question: 938. Range Sum of BST

Problem Statement: Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

Example 1:
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.

Example 2:
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.

Constraints:
    The number of nodes in the tree is in the range [1, 2 * 10^4].
    1 <= Node.val <= 10^5
    1 <= low <= high <= 10^5
    All Node.val are unique.
*/

/*
Approach: Recursive Depth-First Search with BST Pruning

Goal:
- Compute the sum of all node values in a Binary
  Search Tree (BST) that lie within the inclusive
  range [low, high].

Core Idea:
- Utilize the Binary Search Tree property to avoid
  traversing unnecessary subtrees.
- If the current node's value is greater than
  high, all nodes in its right subtree are also
  greater than high and can be skipped.
- If the current node's value is less than low,
  all nodes in its left subtree are also less than
  low and can be skipped.
- Only when the current node lies within the
  desired range are both subtrees explored.

Algorithm Steps:
1. If the current node is null, return 0.
2. If the current node's value is greater than
   high, recursively search only the left subtree.
3. If the current node's value is less than low,
   recursively search only the right subtree.
4. Otherwise:
   - Add the current node's value.
   - Recursively compute the range sum of the left
     subtree.
   - Recursively compute the range sum of the
     right subtree.
5. Return the total sum.

Why It Works:
- The BST property guarantees that:
  - All values in the left subtree are smaller
    than the current node.
  - All values in the right subtree are larger
    than the current node.
- This allows entire subtrees to be skipped when
  their values cannot fall within the required
  range.
- Every relevant node is visited exactly once,
  while unnecessary traversals are avoided.

Time Complexity:
- O(n) in the worst case.

Space Complexity:
- O(h)

where h is the height of the tree due to the
recursion stack.

Result:
- Returns the sum of all node values whose values
  lie within the inclusive range [low, high].
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the merged tree
  public int rangeSumBST(TreeNode root, int low, int high) {
    // If root is null then return 0
    if (root == null) {
      return 0;
    }

    // If the root.val is higher than the high recurse on left node
    if (root.val > high) {
      return this.rangeSumBST(root.left, low, high);
    }

    // If the root.val is lower than the high recurse on right node
    if (root.val < low) {
      return this.rangeSumBST(root.right, low, high);
    }

    // Return the sum of the range
    return root.val + this.rangeSumBST(root.left, low, high) + this.rangeSumBST(root.right, low, high);
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
public class _938_Range_Sum_of_BST {
  // Main method to test rangeSumBST
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 10, 5, 15, 3, 7, null, 18 });
    int low = 7;
    int high = 15;

    int result = new Solution().rangeSumBST(root, low, high);

    System.out.println("The merged tree is : " + result);
  }
}
