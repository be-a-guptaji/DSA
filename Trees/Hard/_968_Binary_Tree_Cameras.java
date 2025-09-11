/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-cameras/

Question: 968. Binary Tree Cameras

Problem Statement: You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

Return the minimum number of cameras needed to monitor all nodes of the tree.

Example 1:
Input: root = [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.

Example 2:
Input: root = [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Constraints:

The number of nodes in the tree is in the range [1, 1000].
Node.val == 0
*/

/*
Approach:
1. Use DFS (postorder) to assign one of three states to each node:
   - 0 → This node needs a camera.
   - 1 → This node has a camera.
   - 2 → This node is covered by a camera from its child.

2. For each node:
   - If any child is in state 0 → place a camera at this node (state = 1).
   - Else if any child is in state 1 → this node is covered (state = 2).
   - Else (both children are covered) → this node needs a camera (state = 0).

3. After DFS, if the root is still in state 0 → add one more camera.

Time Complexity: O(n)   (each node is processed once)
Space Complexity: O(h)  (recursion stack; O(n) worst case for skewed tree, O(log n) for balanced tree)
*/

package Trees.Hard;

import java.util.LinkedList;
import java.util.Queue;

public class _968_Binary_Tree_Cameras {
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

    // Private variable to store camera
    private static int cameras;

    // Method to minimum number of camera used to cover a binary tree
    public static int minCameraCover(TreeNode root) {
        // Reset the camera count to zero
        cameras = 0;

        // If root is not covered, we must place a camera
        if (dfs(root) == 0) {
            cameras++;
        }

        // Return the total number of cameras
        return cameras;
    }

    // Helper function to find the cameras
    private static int dfs(TreeNode node) {
        // Null nodes are considered covered
        if (node == null) {
            return 2;
        }

        // Find the left and right sub tree cameras
        int left = dfs(node.left);
        int right = dfs(node.right);

        // Case 1: If any child needs a camera, place a camera here
        if (left == 0 || right == 0) {
            cameras++;
            return 1;
        }

        // Case 2: If any child has a camera, this node is covered
        if (left == 1 || right == 1) {
            return 2;
        }

        // Case 3: If both children are covered, this node needs a camera
        return 0;
    }

    // Build tree from int[] (treat Integer.MIN_VALUE as null)
    public static TreeNode makeTree(int[] val) {
        if (val == null || val.length == 0) {
            return null;
        }

        // Convert int[] → Integer[] (so we can store nulls)
        Integer[] arr = new Integer[val.length];
        for (int i = 0; i < arr.length; i++) {
            // if val[i] == Integer.MIN_VALUE → treat as null
            arr[i] = (val[i] == Integer.MIN_VALUE) ? null : val[i];
        }

        if (arr[0] == null)
            return null; // root can't be null

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (current != null) {
                // Left child
                if (i < arr.length && arr[i] != null) {
                    current.left = new TreeNode(arr[i]);
                    queue.offer(current.left);
                }
                i++;

                // Right child
                if (i < arr.length && arr[i] != null) {
                    current.right = new TreeNode(arr[i]);
                    queue.offer(current.right);
                }
                i++;
            }
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

    // Main method to test minCameraCover
    public static void main(String[] args) {
        int[] root = { 0, 0, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, Integer.MIN_VALUE, 0 };

        int result = minCameraCover(makeTree(root));

        System.out.println("The minimum number of camera used to cover the binary tree is : " + result);
    }
}