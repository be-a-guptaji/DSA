/*
LeetCode Problem: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Question: 114. Flatten Binary Tree to Linked List

Problem Statement: Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Example 1:
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [0]
Output: [0]

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
*/

/*
Approach:
1. Base case check:
   - If root is null → return null.
2. Recursively flatten the left subtree and right subtree.
   - leftTail = flatten(root.left)
   - rightTail = flatten(root.right)
3. Rewire connections if left subtree exists:
   - Attach original right subtree to the tail of left subtree.
   - Move left subtree to right (root.right = root.left).
   - Set root.left = null (since flatten requires only right pointers).
4. Return the tail node:
   - If rightTail exists → return rightTail.
   - Else if leftTail exists → return leftTail.
   - Else return root (leaf node).

Time Complexity: O(n), since every node is processed once.
Space Complexity: O(h), recursion stack where h is the height of the tree 
  (O(log n) for balanced tree, O(n) for skewed tree).
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _114_Flatten_Binary_Tree_to_Linked_List {
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

    // Method to flatten the tree
    public static void flatten(TreeNode root) {
        flattenTheTree(root); // Call the helper function
    }

    // Helper function to flatten the tree
    private static TreeNode flattenTheTree(TreeNode root) {
        // Check the edge case
        if (root == null)
            return null;

        // Flatten left and right subtrees
        TreeNode leftTail = flattenTheTree(root.left);
        TreeNode rightTail = flattenTheTree(root.right);

        // If left subtree exists, rewire connections
        if (leftTail != null) {
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        // Return the tail of the flattened tree
        if (rightTail != null) {
            return rightTail;
        }
        if (leftTail != null) {
            return leftTail;
        }

        // Return the root
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

    // Main method to test flatten
    public static void main(String[] args) {
        int[] root = { 1, 2, 5, 3, 4, -1, 6 };

        TreeNode node = makeTree(root);

        flatten(node);

        System.out.println("The tree is flatten succesfully.");

        while (node != null) {
            System.out.print(node.val + " ");
            node = node.right;
        }
        // int[] root = { 1, 2, 5, 3, 4, -1, 6 };

        // flatten( makeTree(root));

        // System.out.println("The tree is flatten succesfully.");
    }
}