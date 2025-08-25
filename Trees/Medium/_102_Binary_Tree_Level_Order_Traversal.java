/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-level-order-traversal/

Question: 102. Binary Tree Level Order Traversal

Problem Statement: Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
*/

/*
Approach:
1. Edge case check
   - If the root is null, return an empty list because there are no levels to traverse.
2. BFS traversal setup
   - Use a queue to process nodes level by level.
   - Use a list of list of integers to store the final level order result.
3. Traverse the tree
   - While the queue is not empty:
       a. Get the size of the current level (number of nodes at this level).
       b. Create a temporary list to hold the current level’s values.
       c. For each node at this level:
          - Poll the node from the queue.
          - Add its value to the temporary list.
          - If the node has a left child, add it to the queue.
          - If the node has a right child, add it to the queue.
       d. Add the temporary list to the result list.
4. Return the result
   - After processing all levels, return the list of lists containing all levels.

Time Complexity: O(n)   (each node is visited exactly once)
Space Complexity: O(n)  (queue + result list, in worst case storing all nodes)
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102_Binary_Tree_Level_Order_Traversal {
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

    // Method to traverse the tree by level order
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // Check the edge case
        if (root == null) {
            return new ArrayList<>();
        }

        // Intialize the queue for the BFS search
        Queue<TreeNode> queue = new LinkedList<>();

        // List of List of Integer for tracking the level order of the tree
        List<List<Integer>> list = new ArrayList<>();

        // Add the root to the queue and list
        queue.offer(root);

        // Traverse the queue until it's empty
        while (!queue.isEmpty()) {
            // Get the size of the queue
            int size = queue.size();

            // Create a list of integer for the level
            List<Integer> level = new ArrayList<>();

            // Get all the order of a level
            for (int i = 0; i < size; i++) {
                // Get the node from the queue
                TreeNode node = queue.poll();

                // Add the value to the list
                level.add(node.val);

                // If the left node dose not contain null then add to the queue
                if (node.left != null) {
                    queue.offer(node.left);
                }

                // If the right node dose not contain null then add to the queue
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // Add the list to the list of list
            list.add(level);
        }

        // Return the list
        return list;
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

    // Main method to test levelOrder
    public static void main(String[] args) {
        int[] root = { 3, 9, 20, -1, -1, 15, 7 };

        List<List<Integer>> result = levelOrder(makeTree(root));

        System.out.println("The level order traversal of the binary tree is : " + result);
    }
}