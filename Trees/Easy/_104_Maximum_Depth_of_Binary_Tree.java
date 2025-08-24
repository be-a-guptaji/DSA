/*
LeetCode Problem: https://leetcode.com/problems/maximum-depth-of-binary-tree/

Question: 104. Maximum Depth of Binary Tree

Problem Statement: Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-100 <= Node.val <= 100
*/

/*
Approach:
1. Handle edge case
   - If the root is null, return 0 because the tree is empty.
2. Use recursion to compute depth
   - For each node, the depth is 1 (for the current node itself) 
     plus the maximum of the depth of its left and right subtrees.
3. Base case
   - If the node is null, return 0 since null does not contribute to depth.
4. Recursive case
   - Call the same function on left and right child of the current node.
   - Take the maximum of both results and add 1.
5. Return the final result
   - The recursion will bubble up and return the maximum depth of the tree.

Time Complexity: O(n)   (each node is visited exactly once)
Space Complexity: O(h)  (h is height of tree, recursion stack usage)
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _104_Maximum_Depth_of_Binary_Tree {
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

    // Method to check if the tree contain semmetry or not
    public static int maxDepth(TreeNode root) {
        // Edge case if root is null
        if (root == null) {
            return 0;
        }

        // Retrun the recursive function for finding the height
        return (1 + Math.max(maxDepth(root.left), maxDepth(root.right)));
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

    // Main method to test maxDepth
    public static void main(String[] args) {
        int[] root = { 3, 9, 20, -1, -1, 15, 7 };

        int result = maxDepth(makeTree(root));

        System.out.println("The depth of the tree is : " + result);
    }
}
