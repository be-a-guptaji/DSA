/*
LeetCode Problem: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

Question: 297. Serialize and Deserialize Binary Tree

Problem Statement: Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Example 1:
Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]

Example 2:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-1000 <= Node.val <= 1000
*/

/*
Approach:
1. Serialization (tree → string):
   - Use BFS level-order traversal.
   - Initialize a queue with the root node.
   - For each node:
     • If it is not null → append its value to the string and enqueue its children.
     • If it is null → append "null".
   - Join all values with commas and return as a string.
2. Deserialization (string → tree):
   - If input string is empty → return null.
   - Split the string by commas to get node values.
   - Create the root node from the first value and add it to a queue.
   - Process the queue:
     • For each node, read the next two values as left and right children.
     • If the value is not "null" → create a child node and enqueue it.
     • If the value is "null" → leave the child as null.
   - Continue until all values are processed.

Time Complexity: O(n)   (each node is visited once during serialization and deserialization)
Space Complexity: O(n)  (queue + output string storage)
*/

package Trees.Hard;

import java.util.LinkedList;
import java.util.Queue;

public class _297_Serialize_and_Deserialize_Binary_Tree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    // Given Class to Implement serialize and deserialize
    public static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // If root is null then return empty string
            if (root == null) {
                return "";
            }

            // Initialize the variable for the storage of the tree
            StringBuilder data = new StringBuilder();

            // Initialize a queue for the BSF Traversal
            Queue<TreeNode> queue = new LinkedList<>();

            // Offer root to the queue
            queue.offer(root);

            // Traverse the tree until the stack is empty
            while (!queue.isEmpty()) {
                // Get the node form the queue
                TreeNode node = queue.poll();

                // If node is null then continue
                if (node == null) {
                    data.append("null,");
                    continue;
                } else {
                    // Add the node value to the data
                    data.append(node.val).append(",");
                }

                // Offer the left and right to the queue
                queue.offer(node.left);
                queue.offer(node.right);
            }

            // Return the data
            return data.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            // If data is empty or null return null
            if (data == null || data.isBlank()) {
                return null;
            }

            // Split the data by the commas
            String[] value = data.split(",");

            // Initialize a queue for the Traversal
            Queue<TreeNode> queue = new LinkedList<>();

            // Make the root node
            TreeNode root = new TreeNode(Integer.parseInt(value[0]));

            // Offer first value to the queue
            queue.offer(root);

            // Start from the next element
            int index = 1;

            // Traverse the whole queue
            while (!queue.isEmpty() && index < value.length) {
                TreeNode node = queue.poll();

                // Left child
                if (!value[index].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(value[index]));
                    queue.offer(node.left);
                }
                index++;

                // Right child
                if (index < value.length && !value[index].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(value[index]));
                    queue.offer(node.right);
                }
                index++;
            }

            // Return the root node
            return root;
        }
    }

    // Build tree from int[] (treat Integer.MIN_VALUE as null)
    public static TreeNode makeTree(int[] val) {
        if (val == null || val.length == 0) {
            return null;
        }

        // Convert int[] → Integer[] (so we can store nulls)
        Integer[] arr = new Integer[val.length];
        for (int i = 0; i < arr.length; i++) {
            // if val[i] == Integer.MIN_VALUE → treat as null
            arr[i] = (val[i] == Integer.MIN_VALUE) ? null : val[i];
        }

        if (arr[0] == null)
            return null; // root can't be null

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (current != null) {
                // Left child
                if (i < arr.length && arr[i] != null) {
                    current.left = new TreeNode(arr[i]);
                    queue.offer(current.left);
                }
                i++;

                // Right child
                if (i < arr.length && arr[i] != null) {
                    current.right = new TreeNode(arr[i]);
                    queue.offer(current.right);
                }
                i++;
            }
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

    // Main method to test maxPathSum
    public static void main(String[] args) {
        int[] root = { 1, 2, 3, Integer.MIN_VALUE, Integer.MIN_VALUE, 4, 5 };

        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode ans = deser.deserialize(ser.serialize(makeTree(root)));

        System.out.println("The root of a binary tree is: " + ans);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));