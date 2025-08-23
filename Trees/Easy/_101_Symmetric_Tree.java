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
2. Use two queues for BFS traversal
   - Push root.left into tree1 and root.right into tree2.
3. Traverse both sides simultaneously
   - While both queues are not empty:
       a) Poll one node from each queue.
       b) If both are null, continue.
       c) If one is null and the other is not, return false.
       d) If values differ, return false.
       e) Push children in mirrored order:
          - tree1 → left first, then right.
          - tree2 → right first, then left.
4. Final check
   - After traversal, both queues must be empty for symmetry to hold.

Time Complexity: O(n), Each node is visited once.
Space Complexity: O(n), In worst case (perfect binary tree), queues can hold O(n/2) ≈ O(n) nodes.
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

        // Queues for BFS
        Queue<TreeNode> tree1 = new LinkedList<>();
        Queue<TreeNode> tree2 = new LinkedList<>();

        // Push the root of the tree
        tree1.offer(root.left);
        tree2.offer(root.right);

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
            tree2.offer(node2.right);
            tree2.offer(node2.left);
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
