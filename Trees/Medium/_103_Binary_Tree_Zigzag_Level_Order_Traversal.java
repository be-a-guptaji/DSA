/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Question: 103. Binary Tree Zigzag Level Order Traversal

Problem Statement: Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
*/

/*
Approach:
1. Edge case check
   - If the root is null, return an empty list since no traversal is possible.
2. BFS traversal setup
   - Use a queue to process nodes level by level.
   - Use a list of list of integers to store the zigzag level order result.
   - Maintain a boolean variable `isEvenLevel` to decide where to insert elements.
   - Use a Deque for each level to support O(1) insertion at both ends.
3. Traverse the tree
   - While the queue is not empty:
       a. Get the size of the current level.
       b. Create a deque to store values for this level.
       c. For each node in the current level:
          - Poll the node from the queue.
          - If `isEvenLevel` is true, insert the node’s value at the front of the deque.
          - Otherwise, insert at the back.
          - Add left and right children to the queue if they exist.
       d. Convert the deque to a list and add it to the result.
       e. Flip the `isEvenLevel` flag for the next level.
4. Return the result
   - After processing all levels, return the zigzag level order list.

Time Complexity: O(n)   (each node is visited once, O(1) deque operations)
Space Complexity: O(n)  (queue + result list, storing all nodes in worst case)
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _103_Binary_Tree_Zigzag_Level_Order_Traversal {
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

    // Method to traverse the tree by zig zag level order using Deque
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Edge case check
        if (root == null) {
            return new ArrayList<>();
        }

        // Intialize the queue for the BFS search
        Queue<TreeNode> queue = new LinkedList<>();

        // List of List of Integer for tracking the level order of the tree
        List<List<Integer>> list = new ArrayList<>();

        // Variable to track the order
        boolean isEvenLevel = false;

        // Add the root to the queue and list
        queue.offer(root);

        // Traverse the queue until it's empty
        while (!queue.isEmpty()) {
            // Get the size of the queue
            int size = queue.size();

            // Use Deque for current level
            Deque<Integer> level = new LinkedList<>();

            // Get all the order of a level
            for (int i = 0; i < size; i++) {
                // Get the node from the queue
                TreeNode node = queue.poll();

                // Add the value to the list (front or back depending on level)
                if (isEvenLevel) {
                    level.addFirst(node.val); // reverse insert
                } else {
                    level.addLast(node.val); // normal insert
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

            // Add the deque as a list to the result
            list.add(new ArrayList<>(level));

            // Reverse the isEvenLevel boolean variable
            isEvenLevel = !isEvenLevel;
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

    // Main method to test zigzagLevelOrder
    public static void main(String[] args) {
        int[] root = { 3, 9, 20, -1, -1, 15, 7 };

        List<List<Integer>> result = zigzagLevelOrder(makeTree(root));

        System.out.println("The zig zag level order traversal of the binary tree is : " + result);
    }
}