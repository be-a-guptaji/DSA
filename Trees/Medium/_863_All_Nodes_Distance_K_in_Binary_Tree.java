/*
LeetCode Problem: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

Question: 863. All Nodes Distance K in Binary Tree

Problem Statement: Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:
Input: root = [1], target = 1, k = 3
Output: []

Constraints:

The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
*/

/*
Approach:
1. Handle the edge case → if root is null then return empty list.
2. First build a Parent Map using BFS:
   - Traverse the tree level by level.
   - Store each node’s parent in a map for upward traversal.
3. Use a queue for BFS starting from the target node.
4. Maintain a visited set to avoid revisiting nodes.
5. Perform BFS level by level:
   - At each step, expand to left child, right child, and parent (if not visited).
   - Track the current distance.
6. Stop when distance == k.
   - All nodes remaining in the queue are exactly k distance away.
7. Collect their values into the result list and return it.

Time Complexity: O(N), Each node is visited at most once.  
Space Complexity: O(N), For parent map, visited set, and queue in BFS.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _863_All_Nodes_Distance_K_in_Binary_Tree {
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

    // Method to all the nodes at K distance from the target node
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Initialize a Map for the parents of the node in the tree
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // Initialize a queue for traversing the tree
        Queue<TreeNode> queue = new LinkedList<>();

        // Add root of the tree to the queue
        queue.offer(root);

        // Add root to the parent map
        parentMap.put(root, null);

        // Traverse BSF for making the parent map for the tree
        while (!queue.isEmpty()) {
            // Get the first node of the queue
            TreeNode node = queue.poll();

            // If left node is not null then add to the queue and build the parent Map
            if (node.left != null) {
                parentMap.put(node.left, node);
                queue.offer(node.left);
            }

            // If right node is not null then add to the queue and build the parent Map
            if (node.right != null) {
                parentMap.put(node.right, node);
                queue.offer(node.right);
            }
        }

        // Initialize a List of integer for returning
        List<Integer> nodesAtDistanceK = new ArrayList<>();

        // Initialize a map for the visited nodes
        Set<TreeNode> visitedNodesSet = new HashSet<>();

        // Add the target node to the queue
        queue.offer(target);

        // Add the target node to the vistited set
        visitedNodesSet.add(target);

        // Initialize a variable for tracking the distance
        int distance = 0;

        // From the targeted node move radialy outwared till the K distance
        while (!queue.isEmpty() && distance < k) {
            // Get the size of the queue to process all the level simutaneously
            int size = queue.size();

            // Process all the same distance node simutaneously
            for (int i = 0; i < size; i++) {
                // Get the first node of the queue
                TreeNode node = queue.poll();

                // Get the parent node from the parent map
                TreeNode parentNode = parentMap.get(node);

                // Add parent node of the node to the queue if it is not null and has not been
                // visited then add it to the queue
                if (parentNode != null && !visitedNodesSet.contains(parentNode)) {
                    visitedNodesSet.add(parentNode);
                    queue.offer(parentNode);
                }

                // If left node is not null and has not been visited then add it to the queue
                if (node.left != null && !visitedNodesSet.contains(node.left)) {
                    visitedNodesSet.add(node.left);
                    queue.offer(node.left);
                }

                // If right node is not null and has not been visited then add it to the queue
                if (node.right != null && !visitedNodesSet.contains(node.right)) {
                    visitedNodesSet.add(node.right);
                    queue.offer(node.right);
                }
            }

            // Increment the distance
            distance++;
        }

        // After BFS, all remaining nodes in the queue are exactly at distance K
        while (!queue.isEmpty()) {
            // Get the first node of the queue
            TreeNode node = queue.poll();

            // Add the node value to the list
            nodesAtDistanceK.add(node.val);
        }

        // Return the node at distance k as result
        return nodesAtDistanceK;
    }

    // Get the target Node from the tree
    public static TreeNode getTargetNode(TreeNode root, int target) {
        // Return null if root is null
        if (root == null) {
            return null;
        }

        // Make queue for the BSF
        Queue<TreeNode> queue = new LinkedList<>();

        // Add root of the tree to the queue
        queue.offer(root);

        while (!queue.isEmpty()) {
            // Get the first node of the queue
            TreeNode node = queue.poll();

            // If node value is equal to the target value then return the node
            if (node.val == target) {
                return node;
            }

            // If left node is not null then add to the queue
            if (node.left != null) {
                queue.offer(node.left);
            }

            // If right node is not null then add to the queue
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // Return null beacuse no value in the tree has matched
        return null;
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

    // Main method to test distanceK
    public static void main(String[] args) {
        int[] value = { 3, 5, 1, 6, 2, 0, 8, Integer.MIN_VALUE, Integer.MIN_VALUE, 7, 4 };
        int targetNode = 5;
        int k = 2;

        TreeNode root = makeTree(value);

        List<Integer> result = distanceK(root, getTargetNode(root, targetNode), k);

        System.out.println("The nodes at distance " + k + " form the target node " + targetNode + " are : " + result);
    }
}