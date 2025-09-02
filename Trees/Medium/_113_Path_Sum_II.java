/*
LeetCode Problem: https://leetcode.com/problems/path-sum-ii/

Question: 113. Path Sum II

Problem Statement: Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: []

Example 3:
Input: root = [1,2], targetSum = 0
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
*/

/*
Approach:
1. Handle the edge case → if root is null then return empty list.
2. Use recursion (DFS) to explore each root-to-leaf path.
3. Maintain a current path list and keep adding node values while traversing.
4. When a leaf node is reached:
   - Check if targetSum equals the current node value.
   - If yes, add the current path to the result (make a copy).
5. Otherwise:
   - Recurse left and right with the remaining target sum (targetSum - node.val).
6. After recursion, backtrack by removing the last added node 
   to correctly build other paths.
7. Finally, return the result list containing all valid paths.

Time Complexity: O(N²) in the worst case  
  (Each node is visited once → O(N), but for every valid path we may copy up to O(N) nodes).
Space Complexity: O(N) for recursion stack + O(N) for current path list.  
  In the worst case (skewed tree), recursion depth = N.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _113_Path_Sum_II {
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

    // Method to find all the path sum of the tree
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        // Initialize the List of List of integer of path sum
        List<List<Integer>> result = new ArrayList<>();

        // Check the edge case
        if (root == null) {
            return result;
        }

        // Find each path of the target sum
        findPath(root, targetSum, new ArrayList<>(), result);

        // Return the result
        return result;
    }

    // Helper function to find the path sum
    private static void findPath(TreeNode root, int targetSum, List<Integer> current, List<List<Integer>> result) {
        // Check the edge case
        if (root == null) {
            return;
        }

        // Add the current value to the list
        current.add(root.val);

        // Check if it's a leaf and sum matches
        if (root.left == null && root.right == null && targetSum == root.val) {
            result.add(new ArrayList<>(current)); // add a copy of the current path
        } else {
            // Recurse left and right
            findPath(root.left, targetSum - root.val, current, result);
            findPath(root.right, targetSum - root.val, current, result);
        }

        // Backtrack
        current.remove(current.size() - 1);
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

    // Main method to test pathSum
    public static void main(String[] args) {
        int[] root = { 5, 4, 8, 11, -1, 13, 4, 7, 2, -1, -1, 5, 1 };
        int targetSum = 22;

        List<List<Integer>> result = pathSum(makeTree(root), targetSum);

        System.out.println("The path sum which has target sum equal to " + targetSum + " is : " + result);
    }
}