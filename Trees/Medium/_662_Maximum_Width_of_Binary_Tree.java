/*
LeetCode Problem: https://leetcode.com/problems/maximum-width-of-binary-tree/

Question: 662. Maximum Width of Binary Tree

Problem Statement: Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).

Example 2:
Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).

Example 3:
Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).

Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
*/

/*
Approach:
1. Edge case check
   - If root is null then return 0 (empty tree has width = 0).(No Need in this as per the given constraints)
2. Use BFS traversal with indexing system
   - Each node is paired with an index like in a complete binary tree.
   - Root has index 0.
   - For any node:
       left child → index = 2 * parentIndex
       right child → index = 2 * parentIndex + 1
3. BFS level order traversal
   - Use a queue storing (node, index).
   - For each level, record the index of the first node and the last node.
   - Width of this level = lastIndex - firstIndex + 1.
4. Track maximum width
   - At each level, update maxWidth = max(maxWidth, currentLevelWidth).
5. Return the result
   - After BFS traversal, maxWidth contains the maximum width of the tree.

Time Complexity: O(N), Each node is visited exactly once.  
Space Complexity: O(N), Queue stores up to O(N) nodes in the worst case (last level of tree).  
   - Additional Tuple object for each node.
*/

package Trees.Medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class _662_Maximum_Width_of_Binary_Tree {
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

    // Method to find the maximum width of the binary tree
    public static int widthOfBinaryTree(TreeNode root) {
        // Initialize the max variable for tracking the width of the binary tree
        int maxWidth = 0;

        // Make a queue for the BSF Traversal
        Deque<Tuple> queue = new LinkedList<>();

        // Add root to the Tuple with its index
        queue.offer(new Tuple(root, 0));

        // Make the BSF Traversal of the binary tree
        while (!queue.isEmpty()) {
            // Initialize a variable tracking the current size of the tree
            int size = queue.size();

            maxWidth = Math.max(queue.peekLast().index - queue.peekFirst().index + 1, maxWidth);

            // Loop to process the all nodes of the level order traversal of the binary tree
            for (int i = 0; i < size; i++) {
                // Get the Tuple from the queue
                Tuple tup = queue.poll();

                // If the left node of the tuple dose not contain null then add to the queue
                // withs its index
                if (tup.node.left != null) {
                    queue.offer(new Tuple(tup.node.left, tup.index * 2));
                }

                // If the right node of the tuple dose dose not contain null then add to the
                // queue withs its index
                if (tup.node.right != null) {
                    queue.offer(new Tuple(tup.node.right, tup.index * 2 + 1));
                }
            }
        }

        // Return the maxWidth
        return maxWidth;
    }

    // Helper class to leverage the index system of the binary tree
    private static class Tuple {
        TreeNode node;
        int index;

        public Tuple(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
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

    // Main method to test widthOfBinaryTree
    public static void main(String[] args) {
        int[] root = { 1, 3, 2, 5, Integer.MIN_VALUE, Integer.MIN_VALUE, 9, 6, Integer.MIN_VALUE, 7 };

        int result = widthOfBinaryTree(makeTree(root));

        System.out.println("The maximum width of the binary tree is : " + result);
    }
}