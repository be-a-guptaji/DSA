/*
LeetCode Problem: https://leetcode.com/problems/sum-root-to-leaf-numbers/

Question: 129. Sum Root to Leaf Numbers

Problem Statement: You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

Example 1:
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:
Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

Constraints:

The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
*/

/* 
Approach:
1. Start from the root node and initialize the number as 0.
2. At each step, update the number = number * 10 + root.val.
3. If the current node is a leaf (both left and right are null), return the number because it represents a complete root-to-leaf number.
4. Otherwise, recursively call for left and right children and return their sum.
5. The final result will be the total sum of all root-to-leaf numbers.

Time Complexity  : O(n), since every node is visited exactly once.
Space Complexity : O(h), where h is the height of the tree 
                   → O(log n) for a balanced tree, O(n) for a skewed tree.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _129_Sum_Root_to_Leaf_Numbers {
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

    // Method to sum of all the numbers from root to leaf node
    public static int sumNumbers(TreeNode root) {
        // Check the edge case
        if (root == null) {
            return 0;
        }

        // Return the sum
        return sumFromRootToLeafNode(root, 0); // Call the helper function
    }

    // Helper function to find the number sum
    private static int sumFromRootToLeafNode(TreeNode root, int number) {
        // Check the edge case
        if (root == null) {
            return 0;
        }

        // Modified the number
        number = number * 10 + root.val;

        // If it's a leaf node → return the formed number
        if (root.left == null && root.right == null) {
            return number;
        }

        // Return the recursive function
        return sumFromRootToLeafNode(root.left, number) + sumFromRootToLeafNode(root.right, number);
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

    // Main method to test sumNumbers
    public static void main(String[] args) {
        int[] root = { 4, 9, 0, 5, 1 };

        int result = sumNumbers(makeTree(root));

        System.out.println("The sum of the number is : " + result);
    }
}