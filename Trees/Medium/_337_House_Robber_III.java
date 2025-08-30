/*
LeetCode Problem: https://leetcode.com/problems/house-robber-iii/

Question: 337. House Robber III

Problem Statement: The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

Example 1:
Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
0 <= Node.val <= 10^4
*/

/* 
Approach :
1. For each node, calculate two possible profits:
   - notRob → maximum profit if we do NOT rob this node 
              (we can choose the best from robbing or not robbing children).
   - rob → maximum profit if we rob this node 
           (then we cannot rob its children).
2. Use DFS recursion to compute values for left and right subtrees.
   - notRob = max(left[0], left[1]) + max(right[0], right[1])
   - rob = node.val + left[0] + right[0]
3. For each node, return {notRob, rob} as an array.
4. At the root, the answer will be max(result[0], result[1]) 
   i.e., best between robbing root or not robbing root.

Time Complexity: O(n), each node is visited once.
Space Complexity: O(h), recursion stack where h = height of tree 
                  (O(log n) for balanced tree, O(n) for skewed tree).
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _337_House_Robber_III {
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

    // Method to find maximum profit in robbing houses in a binary tree
    public static int rob(TreeNode root) {
        // Get the result in the array
        int[] result = dfs(root);

        // Return the max of result
        return Math.max(result[0], result[1]);
    }

    // Helper function that returns an array
    // result[0] = max profit if we DO NOT rob this node
    // result[1] = max profit if we DO rob this node
    private static int[] dfs(TreeNode root) {
        // Base case
        if (root == null) {
            return new int[2];
        }

        // DFS calls for left and right children
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        // If we do NOT rob this node → we can choose max of robbing or not robbing
        // children
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // If we DO rob this node → we cannot rob children
        int rob = root.val + left[0] + right[0];

        // Return the array of result
        return new int[] { notRob, rob };
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

    // Main method to test rob
    public static void main(String[] args) {
        int[] root = { 3, 4, 5, 1, 3, -1, 1 };

        int result = rob(makeTree(root));

        System.out.println("The maximum number of profit of the robber is : " + result);
    }
}