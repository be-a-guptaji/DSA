/*
LeetCode Problem: https://leetcode.com/problems/convert-bst-to-greater-tree/

Question: 538. Convert BST to Greater Tree

Problem Statement: Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

Example 2:
Input: root = [0,null,1]
Output: [1,null,1]

Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-10^4 <= Node.val <= 10^4
All the values in the tree are unique.
root is guaranteed to be a valid binary search tree.

Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
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

import java.util.LinkedList;
import java.util.Queue;

public class _538_Convert_BST_to_Greater_Tree {
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

    // Method to convert the BST to the Greater BST
    public static TreeNode convertBST(TreeNode root) {
        // Call the function to convert the BST
        makeGreaterBST(root, 0);

        // Return the root
        return root;
    }

    // Helper function to make the greater BST
    private static int makeGreaterBST(TreeNode root, int sum) {
        // Edge case check
        if (root == null) {
            return sum;
        }

        // Traverse right subtree first
        int right = makeGreaterBST(root.right, sum);

        // Update current node with the sum of greater nodes
        root.val += right;

        // Traverse left subtree with updated sum
        return makeGreaterBST(root.left, root.val);
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

    // Main method to test convertBST
    public static void main(String[] args) {
        int[] root = { 4, 1, 6, 0, 2, 5, 7, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 3,
                Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 8 };

        TreeNode result = convertBST(makeTree(root));

        System.out.println("The node of the tree is : " + result);
    }
}