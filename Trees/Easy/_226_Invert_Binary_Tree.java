/*
LeetCode Problem: https://leetcode.com/problems/invert-binary-tree/

Question: 226. Invert Binary Tree

Problem Statement: Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

/*
Approach:
1. Edge case check
   - If the root is null then simply return null.
2. Recursive swap
   - Store left child in a temporary variable.
   - Store right child in another variable.
   - Recursively call invertTree on right and left subtrees.
3. Swap pointers
   - Assign root.left = invertTree(right)
   - Assign root.right = invertTree(left)
4. Return the root
   - After swapping all nodes recursively, return the root node.

Time Complexity: O(n)   (every node is visited once)
Space Complexity: O(h)  (recursive stack where h = height of tree, O(log n) for balanced tree, O(n) for skewed tree)
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _226_Invert_Binary_Tree {
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

    // Method to invert the tree
    public static TreeNode invertTree(TreeNode root) {
        // Edge case check
        if (root == null) {
            return null;
        }

        // Get the left and right node of the tree
        TreeNode left = root.left;
        TreeNode right = root.right;

        // Swap the nodes
        root.left = invertTree(right);
        root.right = invertTree(left);

        // Return the root of the tree
        return root;
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

    // Main method to test invertTree
    public static void main(String[] args) {
        int[] root = { 4, 2, 7, 1, 3, 6, 9 };

        invertTree(makeTree(root));

        System.out.println("The tree is inverted succesfully.");
    }
}
