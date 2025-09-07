/*
LeetCode Problem: https://leetcode.com/problems/recover-binary-search-tree/

Question: 99. Recover Binary Search Tree

Problem Statement: You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Example 1:
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

Example 2:
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.

Constraints:

The number of nodes in the tree is in the range [2, 1000].
-2^31 <= Node.val <= 2^31 - 1

Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
*/

/*
Approach:
1. Initialize pointers:
   - first, second = null (to store swapped nodes)
   - prev = -∞ (to track the previous node in inorder traversal)
2. Perform inorder traversal (left → root → right).
3. Detect violations:
   - If prev.val > curr.val and first is not set → first = prev.
   - For every violation → second = curr.
4. Update prev = curr at each step.
5. After traversal, swap the values of first and second to restore BST.

Time Complexity: O(n)   (each node is visited once)
Space Complexity: O(h)  (recursion stack, h = height of tree; O(n) in worst case)
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _99_Recover_Binary_Search_Tree {
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

    // Private variable for storing the swapped nodes
    private static TreeNode first = null, second = null, prev = new TreeNode(Integer.MIN_VALUE);

    // Method to recover the binary search tree
    public static void recoverTree(TreeNode root) {
        inorder(root);

        // Swap back values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    // Helper function to find the the swaped nodes
    private static void inorder(TreeNode root) {
        // Edge case if root then return
        if (root == null) {
            return;
        }

        // Traverse the left sub tree
        inorder(root.left);
        
        // IF the first node is null then store the prev value in the first
        if (first == null && prev.val > root.val) {
            first = prev;
        }
        
        // IF the first node is not null then store the root value in the second
        if (first != null && prev.val > root.val) {
            second = root;
        }

        // Update the previous to root
        prev = root;
        
        // Traverse the right sub tree
        inorder(root.right);
    }

    // Build tree from int[] (treat Integer.MIN_VALUE as null)
    public static TreeNode makeTree(int[] val) {
        if (val == null || val.length == 0) {
            return null;
        }

        // Convert int[] → Integer[] (so we can store nulls)
        Integer[] arr = new Integer[val.length];
        for (int i = 0; i < arr.length; i++) {
            // if val[i] == Integer.MIN_VALUE → treat as null
            arr[i] = (val[i] == Integer.MIN_VALUE) ? null : val[i];
        }

        if (arr[0] == null)
            return null; // root can't be null

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (current != null) {
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

    // Main method to test recoverTree
    public static void main(String[] args) {
        int[] root = { 3, 1, 4, Integer.MIN_VALUE, Integer.MIN_VALUE, 2 };

        recoverTree(makeTree(root));

        System.out.println("The Binary Search Tree is succesfully recovered.");
    }
}