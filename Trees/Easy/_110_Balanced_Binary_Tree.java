/*
LeetCode Problem: https://leetcode.com/problems/balanced-binary-tree/

Question: 110. Balanced Binary Tree

Problem Statement: Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false

Constraints:

The number of nodes in both trees is in the range [0, 100].
-10^4 <= Node.val <= 10^4
*/

/*
Approach: Postorder DFS with Early Pruning

A binary tree is height-balanced if for every node, the height difference between
its left and right subtrees is at most 1.

We use Depth-First Search to compute heights bottom-up while simultaneously checking
the balance condition.

Algorithm:
- If the current node is null → return 0.
- Recursively compute the left subtree height.
  - If it returns -1 → subtree is already unbalanced → propagate -1 upward.
- Recursively compute the right subtree height.
  - If it returns -1 → subtree is already unbalanced → propagate -1 upward.
- If |leftHeight - rightHeight| > 1 → return -1 (unbalanced).
- Otherwise → return 1 + max(leftHeight, rightHeight).

Final Check:
- If dfs(root) returns -1 → tree is unbalanced.
- Otherwise → tree is balanced.

Why It Works:
- Each node is visited once.
- Early stopping avoids unnecessary traversal once imbalance is detected.

Time Complexity: O(n)  
Space Complexity: O(h)  (h = height of the tree due to recursion stack)
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _110_Balanced_Binary_Tree {
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

    // Method to check if the two trees are same
    public static boolean isBalanced(TreeNode root) {
        // Edge case if root is null then return true
        if (root == null) {
            return true;
        }

        // If dfs returns -1, tree is unbalanced
        return dfs(root) != -1;
    }

    // Helper method to find the depth of the tree
    private static int dfs(TreeNode root) {
        // Edge case if root is null then return zero
        if (root == null) {
            return 0;
        }

        // Get the left and right subtree depth
        int left = dfs(root.left);
        if (left == -1) {
            return -1; // Early stop if unbalanced
        }
        int right = dfs(root.right);
        if (right == -1) {
            return -1; // Early stop if unbalanced
        }
        // If difference is more than one then return -1
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // Return the max of the left and right subtree depth and one to it
        return 1 + Math.max(left, right);
    }

    // Build tree from String[] (supports nulls, level-order traversal)
    public static TreeNode makeTree(String[] arr) {
        if (arr == null || arr.length == 0 || arr[0].equals("null")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < arr.length && !arr[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(current.left);
            }
            i++;

            // Right child
            if (i < arr.length && !arr[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // Mock class for makeing the TreeNode Class
    public static class TreeNode {
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
    }

    // Main method to test isBalanced
    public static void main(String[] args) {
        String[] root = { "1", "2", "2", "3", "3", "null", "null", "4", "4" };

        boolean result = isBalanced(makeTree(root));

        System.out.println("The tree is" + (result ? "" : " not") + " balanced.");
    }
}
