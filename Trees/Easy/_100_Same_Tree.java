/*
LeetCode Problem: https://leetcode.com/problems/same-tree/

Question: 100. Same Tree

Problem Statement: Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false

Constraints:

The number of nodes in both trees is in the range [0, 100].
-10^4 <= Node.val <= 10^4
*/

/*
Approach:
1. Handle edge cases
   - If both nodes are null, return true (both trees are empty).
   - If one is null and the other is not, return false (tree structure differs).
2. Use BFS with two queues
   - Initialize two queues, one for each tree.
   - Add the root nodes of both trees into their respective queues.
3. Traverse both trees level by level
   - While both queues are not empty:
       a) Poll one node from each queue.
       b) If both nodes are null, continue to next iteration.
       c) If one is null while the other is not, return false.
       d) If values of nodes differ, return false.
       e) Push left and right children of both nodes into their respective queues.
4. Final check
   - After traversal, if both queues are empty, return true.
   - Otherwise return false.

Time Complexity: O(n), n = number of nodes in the smaller tree, Each node is visited once.
Space Complexity: O(n), In the worst case, both queues can store O(n) nodes at the same time.
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _100_Same_Tree {
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

    // Method to check if the two trees are same
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // Edge case: both null
        if (p == null && q == null) {
            return true;
        }
        // Edge case: one null and other not
        if (p == null || q == null) {
            return false;
        }

        // Queues for BFS
        Queue<TreeNode> tree1 = new LinkedList<>();
        Queue<TreeNode> tree2 = new LinkedList<>();

        // Push the root of the tree
        tree1.offer(p);
        tree2.offer(q);

        // Traverse both trees simultaneously
        while (!tree1.isEmpty() && !tree2.isEmpty()) {
            TreeNode node1 = tree1.poll();
            TreeNode node2 = tree2.poll();

            if (node1 == null && node2 == null) {
                continue; // both are null, skip
            }
            if (node1 == null || node2 == null) {
                return false; // one null, one not
            }
            if (node1.val != node2.val) {
                return false; // different values
            }

            // Push children
            tree1.offer(node1.left);
            tree1.offer(node1.right);
            tree2.offer(node2.left);
            tree2.offer(node2.right);
        }

        // Both queues must be empty
        return tree1.isEmpty() && tree2.isEmpty();
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

    // Main method to test isSameTree
    public static void main(String[] args) {
        int[] p = { 1, 2, 3 }, q = { 1, 2, 3, 4 };

        boolean result = isSameTree(makeTree(p), makeTree(q));

        if (result) {
            System.out.println("The two trees are same.");
        } else {
            System.out.println("The two trees are not same.");
        }
    }
}
