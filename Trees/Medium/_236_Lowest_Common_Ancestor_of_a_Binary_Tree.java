/*
LeetCode Problem: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

Question: 236. Lowest Common Ancestor of a Binary Tree

Problem Statement: Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:

The number of nodes in the tree is in the range [2, 105].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q will exist in the tree.
*/

/* 
Approach :
1. Start recursion from the root node.
2. If the current node is null OR equal to p or q, return it (base case).
3. Recursively search the left and right subtrees for p and q.
4. If both left and right return non-null → current node is the Lowest Common Ancestor.
5. Otherwise, return whichever side is non-null (either left or right).
6. This works because:
   - If both p and q are found in different subtrees → root is LCA.
   - If both are in the same subtree → LCA is deeper inside that subtree.

Time Complexity: O(n), since every node is visited once.
Space Complexity: O(h), where h is the height of the tree (recursion stack).
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _236_Lowest_Common_Ancestor_of_a_Binary_Tree {
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

    // Method to find the lowest common ancestor of the two node in the tree
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case if root is null or equal to p or q
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If left and right both are not null
        if (left != null && right != null) {
            return root;
        }

        // If left is not null return left otherwise right
        return left != null ? left : right;
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

    // Method to find the Node of the tree
    private static TreeNode findNode(TreeNode root, int target) {
        // Target is -1 then return null
        if (root == null || target == Integer.MIN_VALUE) {
            return null;
        }

        // Initialize the variable for BFS
        Queue<TreeNode> queue = new LinkedList<>();

        // Add root of the tree to the queue
        queue.offer(root);

        // Logic to find the node
        while (!queue.isEmpty()) {
            // Get the node of the queue
            TreeNode node = queue.poll();

            // If target is equal to node value than return the value
            if (node.val == target) {
                return node;
            }

            // If the left node does not contain null then add to the queue
            if (node.left != null) {
                queue.offer(node.left);
            }

            // If the right node does not contain null then add to the queue
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // Return the null value if nothing found
        return null;
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

    // Main method to test lowestCommonAncestor
    public static void main(String[] args) {
        int[] values = { 3, 5, 1, 6, 2, 0, 8, -1, -1, 7, 4 };
        TreeNode root = makeTree(values);
        TreeNode p = findNode(root, 5);
        TreeNode q = findNode(root, 6);

        TreeNode result = lowestCommonAncestor(root, p, q);

        System.out.println("The lowest common ancestor of " + p.val + " and " + q.val + " is : " + result.val);
    }
}