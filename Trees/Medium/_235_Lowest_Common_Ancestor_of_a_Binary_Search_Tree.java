/*
LeetCode Problem: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

Question: 235. Lowest Common Ancestor of a Binary Search Tree

Problem Statement: Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [2,1], p = 2, q = 1
Output: 2

Constraints:

The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q will exist in the BST.
*/

/*
Approach: Binary Search Tree Guided Traversal

In a BST, for any node:
- All values in the left subtree are smaller.
- All values in the right subtree are larger.

Using these properties, we can efficiently locate the Lowest Common Ancestor (LCA) of two nodes p and q.

Algorithm:
- Start at the root.
- If both p and q have values greater than the current node → move to current.right.
- If both p and q have values smaller than the current node → move to current.left.
- Otherwise, the nodes diverge at this point, meaning the current node is the LCA.

Why It Works:
- The LCA is the first node where p and q split into different directions.
- BST ordering ensures O(log n) traversal in a balanced tree.

Time Complexity:
- Average: O(log n)
- Worst case (skewed tree): O(n)

Space Complexity: O(1)
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    // Method to find the lowest common ancestor
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Get the root node
        TreeNode current = root;

        // Iterate over the tree untill ancestor is found
        while (current != null) {
            if (p.val > current.val && q.val > current.val) {// If current's value is less then both the node then shift
                                                             // the current node to current.right
                current = current.right;
            } else if (p.val < current.val && q.val < current.val) {// If current's value is greater then both the node
                                                                    // then shift the current node to current.left
                current = current.left;
            } else {// If split occur then return the current node
                return current;
            }
        }

        // Return the current
        return current;
    }

    // Build tree from Integer[] (supports null values, level-order construction)
    public static TreeNode[] makeTree(Integer[] val, int p, int q) {
        if (val == null || val.length == 0 || val[0] == null) {
            return null;
        }

        // Create the root from the first element
        TreeNode root = new TreeNode(val[0]), left = null, right = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Start from index 1 since 0th is the root
        int i = 1;

        // Iterate until all elements are processed
        while (i < val.length) {

            // Get the current node from the queue
            TreeNode current = queue.poll();

            // Assign the left child
            if (i < val.length && val[i] != null) {
                TreeNode node = new TreeNode(val[i]);

                if (p == val[i]) {
                    left = node;
                }

                current.left = node;
                queue.offer(current.left);
            }
            i++;

            // Assign the right child
            if (i < val.length && val[i] != null) {
                TreeNode node = new TreeNode(val[i]);

                if (q == val[i]) {
                    right = node;
                }

                current.right = node;
                queue.offer(current.right);
            }
            i++;
        }

        return new TreeNode[] { root, left, right };
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

    // Main method to test lowestCommonAncestor
    public static void main(String[] args) {
        Integer[] root = { 6, 2, 8, 0, 4, 7, 9, null, null, 3, 5 };
        int p = 2, q = 8;

        TreeNode[] val = makeTree(root, p, q);

        TreeNode result = lowestCommonAncestor(val[0], val[1], val[2]);

        System.out.println("The lowest common ancestor of " + p + " and " + q + " is : " + result.val);
    }
}