/*
LeetCode Problem: https://leetcode.com/problems/subtree-of-another-tree/

Question: 572. Subtree of Another Tree

Problem Statement: Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

Example 1:
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-10^4 <= root.val <= 10^4
-10^4 <= subRoot.val <= 10^4
*/

/*
Approach:
1. Edge case check
   - If both root and subRoot are null then return true.
   - If one is null and the other not then return false.
2. Compare values
   - If the current node value in root matches subRoot value
     then call helper function isSameTree to check if both trees
     are identical in structure and values.
3. Recursive search
   - If not identical at the current node,
     recursively check in the left subtree of root.
   - If not found, recursively check in the right subtree of root.
4. Helper function isSameTree
   - If both nodes are null return true.
   - If one node is null return false.
   - If values are different return false.
   - Otherwise recursively check left with left and right with right.
5. Return the result
   - Return true if isSameTree passes at any node,
     otherwise return false.

Time Complexity: O(m * n) in worst case  
   (m = number of nodes in root, n = number of nodes in subRoot).
Space Complexity: O(h) recursion stack where h = height of the tree.
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _572_Subtree_of_Another_Tree {
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

    // Method to find if subRoot is a subtree of root
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Edge case: both are null
        if (root == null && subRoot == null) {
            return true;
        }

        // Edge case: one is null and other not
        if (root == null || subRoot == null) {
            return false;
        }

        // If the current nodes are the same, check if the trees match
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Otherwise, check in left or right subtree
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // Helper method to check if two trees are identical
    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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

    // Main method to test isSubtree
    public static void main(String[] args) {
        int[] root = { 3, 4, 5, 1, 2 };
        int[] subRoot = { 4, 1, 2 };

        boolean result = isSubtree(makeTree(root), makeTree(subRoot));

        if (result) {
            System.out.println("The tree contain the sub root.");
        } else {
            System.out.println("The tree does not contain the sub root.");
        }
    }
}
