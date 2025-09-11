/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-maximum-path-sum/

Question: 124. Binary Tree Maximum Path Sum

Problem Statement: A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:

The number of nodes in the tree is in the range [1, 3 * 10^4].
-1000 <= Node.val <= 1000
*/

/*
Approach:
1. Initialize global variable maxSum = -∞ to track the maximum path sum.
2. Perform DFS (postorder traversal):
   - For each node, recursively compute max path sum from left and right subtrees.
   - If a subtree contributes a negative sum, treat it as 0 (by comparing with node.val later).
3. At each node:
   - totalSum = leftSum + node.val + rightSum   (path passing through the node including both sides).
   - singlePath = node.val + max(leftSum, rightSum)   (best path continuing upward).
   - Update maxSum = max(maxSum, node.val, totalSum, singlePath).
4. Return the best single path (node.val or node.val + max(leftSum, rightSum)) to the parent call.
5. After traversal, maxSum will hold the maximum path sum in the tree.

Time Complexity: O(n)   (each node visited once)
Space Complexity: O(h)  (recursion stack, h = height of tree; O(n) worst case for skewed tree)
*/

package Trees.Hard;

import java.util.LinkedList;
import java.util.Queue;

public class _124_Binary_Tree_Maximum_Path_Sum {
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

    // Private Variable for tracking the max sum
    private static int maxSum = Integer.MIN_VALUE;

    // Method to find the path with the maximum sum of the binary tree
    public static int maxPathSum(TreeNode root) {
        // Set max sum to minimum value
        maxSum = Integer.MIN_VALUE;

        // Find the max path sum
        findMaxSum(root);

        // Return the max sum
        return maxSum;
    }

    // Helper function to find the max sum
    private static int findMaxSum(TreeNode root) {
        // Edge case check
        if (root == null) {
            return 0;
        }

        // Find the max of left and right sum of the tree
        int leftSum = findMaxSum(root.left);
        int rightSum = findMaxSum(root.right);

        // Adding all nodes
        int totalSum = leftSum + root.val + rightSum;

        // Adding only one path sum with root
        int singlePath = Math.max(leftSum, rightSum) + root.val;

        // Find the maximum sum
        maxSum = Math.max(maxSum, Math.max(root.val, Math.max(totalSum, singlePath)));

        // Return the max of single path or the node
        return Math.max(root.val, singlePath);
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

    // Main method to test maxPathSum
    public static void main(String[] args) {
        int[] root = { -10, 9, 20, Integer.MIN_VALUE, Integer.MIN_VALUE, 15, 7 };

        int result = maxPathSum(makeTree(root));

        System.out.println("The maximum sum of a binary tree is: " + result);
    }
}