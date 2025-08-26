/*
LeetCode Problem: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

Question: 105. Construct Binary Tree from Preorder and Inorder Traversal

Problem Statement: Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
*/

/*
Approach:
1. Reset preorder index pIndex to 0.
2. Build a HashMap mapping inorder values to indices for O(1) lookup.
3. Recursively construct the tree:
   - Use preorder[pIndex] as the root and increment pIndex.
   - Look up root position in inorder (inRootIndex).
   - Recursively build left subtree from inorder[inStart..inRootIndex-1].
   - Recursively build right subtree from inorder[inRootIndex+1..inEnd].
4. Return the constructed root.

Time Complexity: O(n)   (each node is processed once)
Space Complexity: O(n)  (HashMap + recursion stack)
*/

package Trees.Medium;

import java.util.HashMap;
import java.util.Map;

public class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
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

    // Private variable for tracking the value of the pIndex
    private static int pIndex = 0;

    // Method to build tree from preorder and inorder
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // Reset pIndex for multiple calls correctness
        pIndex = 0;

        // Edge case
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        // Create a map for the inorder array value -> index
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Start recursive build with full inorder range
        return build(preorder, 0, inorder.length - 1, inorderMap);
    }

    // Helper function to make the tree
    private static TreeNode build(int[] preorder, int inStart, int inEnd, Map<Integer, Integer> inorderMap) {
        // Base case: no nodes in this inorder range
        if (inStart > inEnd) {
            return null;
        }

        // Take next value from preorder as root
        int rootVal = preorder[pIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find root index in inorder using map
        int inRootIndex = inorderMap.get(rootVal);

        // Build left and right subtrees using inorder ranges
        root.left = build(preorder, inStart, inRootIndex - 1, inorderMap);
        root.right = build(preorder, inRootIndex + 1, inEnd, inorderMap);

        // Return the root
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

    // Main method to test buildTree
    public static void main(String[] args) {
        int[] preorder = { 3, 2, 7, 8, 1, 9, 5 };
        int[] inorder = { 7, 2, 8, 3, 9, 1, 5 };

        TreeNode result = buildTree(preorder, inorder);

        System.out.println("The node of the tree is : " + result);
    }
}