/*
LeetCode Problem: https://leetcode.com/problems/count-good-nodes-in-binary-tree/

Question: 1448. Count Good Nodes in Binary Tree

Problem Statement: Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

Example 1:
Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.

Example 2:
Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.

Example 3:
Input: root = [1]
Output: 1
Explanation: Root is considered as good.

Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
*/

/*
Approach: DFS with Tracking Maximum Value on Path

A node is considered "good" if, on the path from the root to that node, its value
is greater than or equal to every value encountered so far.

We use DFS to traverse the tree while keeping track of:
- maxNumberSeen: the maximum value from the root to the current node.

Algorithm:
- Start DFS from the root with initial maxNumberSeen = Integer.MIN_VALUE.
- At each node:
  - Compare node.val with maxNumberSeen.
  - If node.val >= maxNumberSeen → it is a good node → increment count.
  - Update maxNumberSeen = max(maxNumberSeen, node.val).
- Recursively process left and right children with the updated maxNumberSeen.

Why It Works:
- Each path maintains its own maximum value seen.
- DFS ensures all nodes are visited exactly once.

Time Complexity: O(n)
Space Complexity: O(h)   (h = tree height due to recursion)
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _1448_Count_Good_Nodes_in_Binary_Tree {
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

    // Initialize the variable to count good nodes
    private static int numberOfGoodNodes;

    // Method to find the number of good nodes in the binary tree
    public static int goodNodes(TreeNode root) {
        // Initialize the numberOfGoodNodes to the zero
        numberOfGoodNodes = 0;

        // Call the recursive the dfs method to find the number of good nodes
        dfs(root, Integer.MIN_VALUE);

        // Return the numberOfGoodNodes
        return numberOfGoodNodes;
    }

    // Helper method to find the good nodes in the binary tree
    private static void dfs(TreeNode root, int maxNumberSeen) {
        // Edge case if root is null then return
        if (root == null) {
            return;
        }

        // Update the maxNumberSeen
        maxNumberSeen = Math.max(maxNumberSeen, root.val);

        // If root val is greater than or equal to then update the good nodes number
        if (root.val >= maxNumberSeen) {
            numberOfGoodNodes++;
        }

        // Call the recursive dfs to left and right node 
        dfs(root.left, maxNumberSeen);
        dfs(root.right, maxNumberSeen);
    }

    // Build tree from Integer[] (supports null values, level-order construction)
    public static TreeNode makeTree(Integer[] val) {
        if (val == null || val.length == 0 || val[0] == null) {
            return null;
        }

        // Create the root from the first element
        TreeNode root = new TreeNode(val[0]);
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
                current.left = node;
                queue.offer(current.left);
            }
            i++;

            // Assign the right child
            if (i < val.length && val[i] != null) {
                TreeNode node = new TreeNode(val[i]);
                current.right = node;
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

    // Main method to test goodNodes
    public static void main(String[] args) {
        Integer[] root = { 3, 1, 4, 3, null, 1, 5 };

        int result = goodNodes(makeTree(root));

        System.out.println("The number of good nodes in the binary tree is : " + result);
    }
}