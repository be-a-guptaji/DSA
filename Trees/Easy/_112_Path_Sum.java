/*
LeetCode Problem: https://leetcode.com/problems/path-sum/

Question: 112. Path Sum

Problem Statement: Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There are two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.

Example 3:
Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
*/

/*
Approach:
1. Edge case if root is null
   - If the tree is empty then return false as no path exists.
2. If the current node is a leaf
   - Check if targetSum == node value
   - If yes then return true as we found a valid root-to-leaf path.
3. Recursive step
   - Subtract the node value from targetSum
   - Call the function for the left child and right child with updated targetSum
   - If either returns true then path exists.
4. Return the final result
   - Return true if any valid path is found else return false.

Time Complexity: O(n)   (every node is visited once)
Space Complexity: O(h)  (recursive stack where h = height of tree)
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _112_Path_Sum {
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
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // Edge case if root is null
        if (root == null) {
            return false;
        }

        // If it is a leaf node, check if the sum matches
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Retrun the recursive function for finding the sum
        return (hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val));
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

    // Main method to test hasPathSum
    public static void main(String[] args) {
        int[] root = { 5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, -1, 1 };
        int targetSum = 22;

        boolean result = hasPathSum(makeTree(root), targetSum);

        if (result) {
            System.out.println("The tree has path from root to leaf which has target sum : " + targetSum);
        } else {
            System.out.println("The tree has no path from root to leaf which has target sum : " + targetSum);
        }
    }
}
