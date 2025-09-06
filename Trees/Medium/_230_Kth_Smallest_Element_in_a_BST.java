/*
LeetCode Problem: https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Question: 230. Kth Smallest Element in a BST

Problem Statement: Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3 

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
*/

/*
Approach:
1. Reset state
   - Reset the counter and result at the start of kthSmallest so repeated calls work correctly.
2. Use inorder traversal
   - Perform an inorder traversal (left → node → right) because inorder of a BST yields values in sorted order.
   - During traversal, increment a counter each time a node is visited.
3. Detect kth element
   - When counter == k, store the current node value in result and return (this prunes further work).
4. Return the result
   - After traversal (or early return when found), return the recorded result.

Time Complexity  : O(h + k) in typical cases
   - h = height of the tree (to reach the leftmost node)
   - k = number of nodes visited until the kth element is found
   - Worst case (k ~ n or skewed tree) → O(n)
Space Complexity : O(h)
   - recursion stack depth = O(h) (O(log n) for balanced BST, O(n) for skewed BST)
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _230_Kth_Smallest_Element_in_a_BST {
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

    // Variable for tracking result and count
    private static int count = 0;
    private static int result = -1;

    // Method to find the kth smallest element in the BST
    public static int kthSmallest(TreeNode root, int k) {
        // Reset state for each call
        count = 0;
        result = -1;

        // Call the inorder function
        inorder(root, k);

        // Return the result
        return result;
    }

    // Helper function to traverse the BST in inorder
    private static void inorder(TreeNode root, int k) {
        // If leaf node is hit
        if (root == null) {
            return;
        }

        // Traverse the left node
        inorder(root.left, k);

        // Increase the value of the count
        count++;

        // Return if count is equal to the k
        if (count == k) {
            result = root.val;
            return;
        }

        // Traverse the right node
        inorder(root.right, k);
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

    // Main method to test kthSmallest
    public static void main(String[] args) {
        int[] root = { 3, 1, 4, -1, 2 };
        int k = 1;

        int result = kthSmallest(makeTree(root), k);

        System.out.println("The " + k + "th smallest element in the BST is : " + result);
    }
}