/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-right-side-view/

Question: 199. Binary Tree Right Side View

Problem Statement: Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Explanation:

Example 2:
Input: root = [1,2,3,4,null,null,null,5]
Output: [1,3,4,5]
Explanation:

Example 3:
Input: root = [1,null,3]
Output: [1,3]

Example 4:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

/* 
Approach:
1. Perform a level order traversal (BFS) using a queue.
2. For each level, process all nodes from left to right.
3. The last node encountered at every level is the one visible from the right side.
4. Add this last node's value to the result list.
5. Continue until all levels are processed.

Time Complexity  : O(n), where n is the number of nodes (each node is visited once).
Space Complexity : O(w), where w is the maximum width of the tree (worst case O(n)).
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _199_Binary_Tree_Right_Side_View {
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

    // Method to find the right side view of the tree
    public static List<Integer> rightSideView(TreeNode root) {
        // If tree is empty then return the null value
        if (root == null) {
            return new ArrayList<>();
        }

        // Intialize the queue for the BFS search
        Queue<TreeNode> queue = new LinkedList<>();

        // List of Integer for tracking the right side view of the tree
        List<Integer> result = new ArrayList<>();

        // Add the root to the queue and list
        queue.offer(root);

        // Start a while loop until the queue is empty
        while (!queue.isEmpty()) {
            // Initialize the size for the loop
            int size = queue.size();

            // Start a for loop for getting the right side view of each level
            for (int i = 0; i < size; i++) {
                // Get the first node of the queue
                TreeNode node = queue.poll();

                // If it's the last node at this level, add it to the result
                if (i == size - 1) {
                    result.add(node.val);
                }

                // If the left node does not contain null then add to the queue
                if (node.left != null) {
                    queue.offer(node.left);
                }

                // If the right node does not contain null then add to the queue
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        // Retrun the result
        return result;
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

    // Main method to test rightSideView
    public static void main(String[] args) {
        int[] root = { 1, 2, 3, -1, 5, -1, 4 };

        List<Integer> result = rightSideView(makeTree(root));

        System.out.println("The right side of view of the tree is : " + result);
    }
}