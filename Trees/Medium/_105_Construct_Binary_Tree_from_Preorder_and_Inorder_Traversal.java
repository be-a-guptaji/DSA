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
Approach :
1. Edge case check
   - If preorder or inorder array is empty, return null.
2. Preprocessing with HashMap
   - Store each value of inorder with its index in a HashMap for O(1) lookups.
3. Recursive function
   - Use preorder[preStart] as the root.
   - Find the root index in inorder using the HashMap.
   - Calculate number of nodes in the left subtree.
4. Divide and conquer
   - Recursively build the left subtree using:
     preorder[preStart+1 ... preStart+leftNodes], 
     inorder[inStart ... inRootIndex-1].
   - Recursively build the right subtree using:
     preorder[preStart+leftNodes+1 ... preEnd], 
     inorder[inRootIndex+1 ... inEnd].
5. Return the root
   - After recursively constructing left and right, return root.

Time Complexity: O(n), Each node is processed once, and HashMap lookup is O(1).  

Space Complexity: O(n), HashMap for inorder + recursion stack.
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

    // Method to build tree from preorder and inorder
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // Edge case check
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        // Map to store inorder value -> index for quick lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Use recursion with preorder index
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderMap);
    }

    // Helper function for recursive tree building
    private static TreeNode build(int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {

        // Base case
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // First element in preorder is the root
        TreeNode root = new TreeNode(preorder[preStart]);

        // Find the index of root in inorder
        int inRootIndex = inorderMap.get(root.val);

        // Number of nodes in left subtree
        int leftNodes = inRootIndex - inStart;

        // Build left and right subtrees recursively
        root.left = build(preorder, preStart + 1, preStart + leftNodes,
                inorder, inStart, inRootIndex - 1, inorderMap);

        root.right = build(preorder, preStart + leftNodes + 1, preEnd,
                inorder, inRootIndex + 1, inEnd, inorderMap);

        // Return the root of the tree
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