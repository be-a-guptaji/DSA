/*
LeetCode Problem: https://leetcode.com/problems/diameter-of-binary-tree/

Question: 543. Diameter of Binary Tree

Problem Statement: Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
Input: root = [1,2]
Output: 1

Constraints:

The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100
*/

/*
Approach: Depth-First Search (Postorder Traversal)

The diameter of a binary tree is the length of the longest path between any two nodes.
This path may or may not pass through the root.

We use DFS to compute:
- The height of each subtree.
- The diameter at each node as: leftHeight + rightHeight.

Algorithm:
- If the current node is null → return 0.
- Recursively compute:
  leftHeight  = dfs(root.left)
  rightHeight = dfs(root.right)
- Update the global diameter using:
  diameter = max(diameter, leftHeight + rightHeight)
- Return the height of the current node:
  1 + max(leftHeight, rightHeight)

Why It Works:
- Every node is treated as a potential center of the longest path.
- The maximum sum of left and right subtree heights gives the diameter.

Time Complexity: O(n)  
Space Complexity: O(h)  (h = height of the tree due to recursion stack)
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

public class _543_Diameter_of_Binary_Tree {
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

    // Initialize the diameter variable to get the diameter of the binary tree
    private static int diameter = 0;

    // Method to find the length of the diameter of the tree
    public static int diameterOfBinaryTree(TreeNode root) {
        // Return the dfs recursive method to get the diameter of the binary tree
        dfs(root);

        // Return the diameter
        return diameter;
    }

    // Helper method to find the depth of the tree
    private static int dfs(TreeNode root) {
        // Edge case if root is null then return zero
        if (root == null) {
            return 0;
        }

        // Get the left and right depth of the tree
        int left = dfs(root.left);
        int right = dfs(root.right);

        // Update diameter at this node
        diameter = Math.max(diameter, left + right);

        // Retrun the max of height and left and right of the
        return 1 + Math.max(left, right);
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

    // Main method to test diameterOfBinaryTree
    public static void main(String[] args) {
        int[] root = { 1, 2, 3, 4, 5 };

        int result = diameterOfBinaryTree(makeTree(root));

        System.out.println("The length of the diameter of the tree is : " + result);
    }
}
