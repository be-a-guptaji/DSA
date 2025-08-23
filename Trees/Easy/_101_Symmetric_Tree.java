/*
LeetCode Problem: https://leetcode.com/problems/symmetric-tree/

Question: 101. Symmetric Tree

Problem Statement: Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100

Follow up: Could you solve it both recursively and iteratively?
*/

/*
Approach:
1. Handle edge case
   - If root is null, return true because an empty tree is symmetric.
2. Use recursion to compare left and right subtrees
   - Define helper function isEqual(p, q):
       a) If both p and q are null → return true.
       b) If one is null and the other not → return false.
       c) If p.val != q.val → return false.
       d) Otherwise recursively check:
          - p.left with q.right
          - p.right with q.left
3. The main function
   - Calls isEqual(root.left, root.right).

Time Complexity: O(n), Each node is visited once.
Space Complexity: O(h)
   Due to recursion stack, where h = height of tree.  
   In worst case (skewed tree) O(n), in balanced tree O(log n).
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _101_Symmetric_Tree {
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
    public static boolean isSymmetric(TreeNode root) {
        // Edge case if root is null
        if (root == null) {
            return true;
        }

        // Retrun the recursive function
        return isEqual(root.left, root.right);
    }

    // Helper fuction for cheking the node value
    private static boolean isEqual(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false; // Values are different
        }

        if (p.val != q.val) {
            return false; // different values
        }

        // Traverse the more tree
        return (p.val == q.val && isEqual(p.left, q.right) && isEqual(p.right, q.left));
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

    // Main method to test isSymmetric
    public static void main(String[] args) {
        int[] root = { 1, 2, 2, 3, 4, 4, 3 };

        boolean result = isSymmetric(makeTree(root));

        if (result) {
            System.out.println("The two tree contain semmetry.");
        } else {
            System.out.println("The two trees does not contain semmentry.");
        }
    }
}
