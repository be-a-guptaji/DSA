/*
LeetCode Problem: https://leetcode.com/problems/validate-binary-search-tree/

Question: 98. Validate Binary Search Tree

Problem Statement: Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys strictly less than the node's key.
The right subtree of a node contains only nodes with keys strictly greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-2^31 <= Node.val <= 2^31 - 1
*/

/*
Approach:
1. Edge case check
   - If the root is null then return true because an empty tree is a valid BST.
2. Range-based validation
   - Each node must satisfy a valid range of values.
   - For the root, the initial range is (−∞, +∞).
   - For the left child, the range becomes (min, root.val).
   - For the right child, the range becomes (root.val, max).
3. Recursive check
   - At each node, check if node.val lies strictly between the given min and max.
   - If not, return false.
   - Recursively validate the left and right subtrees with updated ranges.
4. Final result
   - If all nodes satisfy their valid ranges, return true.
   - Otherwise, return false.

Time Complexity: O(n)   (each node is visited once)
Space Complexity: O(h)  (recursive stack where h = height of tree)
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _98_Validate_Binary_Search_Tree {
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

    // Method to check if the given tree is a valid BST or not
    public static boolean isValidBST(TreeNode root) {
        // Edge case check
        if (root == null) {
            return true;
        }

        // Call the helper function with the range of values
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Helper function for validating BST
    private static boolean validate(TreeNode node, long min, long max) {
        // If node is null, it is valid
        if (node == null) {
            return true;
        }

        // Node value must lie in the valid range
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Recursively check left and right subtrees
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    // Build tree from int[] (no nulls, complete binary tree)
    public static TreeNode makeTree(int[] val) {
        if (val == null || val.length == 0) {
            return null;
        }

        // Convert int[] → Integer[] (not strictly needed here, but keeps consistent
        // with logic)
        Integer[] arr = new Integer[val.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = val[i];
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (i < arr.length) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < arr.length) {
                current.right = new TreeNode(arr[i]);
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

    // Main method to test isValidBST
    public static void main(String[] args) {
        int[] root = { 5, 1, 6, 3, 7 };

        boolean result = isValidBST(makeTree(root));

        if (result) {
            System.out.println("The tree is a valid BST.");
        } else {
            System.out.println("The tree is a not valid BST.");
        }
    }
}