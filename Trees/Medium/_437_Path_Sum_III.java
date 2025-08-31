/*
LeetCode Problem: https://leetcode.com/problems/path-sum-iii/

Question: 437. Path Sum III

Problem Statement: Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

Example 1:
Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.

Example 2:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3

Constraints:

The number of nodes in the tree is in the range [0, 1000].
-10^9 <= Node.val <= 10^9
-1000 <= targetSum <= 1000
*/

/* 
Approach :
1. Use a HashMap (prefixSumMap) to store the frequency of prefix sums 
   encountered so far during traversal.
   - Key   → prefix sum (cumulative sum from root to current node).
   - Value → count of how many times this prefix sum has appeared.
2. Start DFS from the root with a running cumulative sum (currSum).
3. At each node:
   - Add node.val to currSum.
   - Check if (currSum - targetSum) exists in the map:
     → If yes, it means there is a path (ending at the current node) 
       whose sum equals targetSum, so add its frequency to the result.
   - Add currSum to prefixSumMap (increment its frequency).
   - Recurse into left and right children.
   - Backtrack: decrement the frequency of currSum in prefixSumMap 
     (so it doesn’t affect other paths).
4. Base case: If the current node is null, return 0.
5. Return the total count of valid paths.

Why it works:
- prefixSumMap efficiently keeps track of all possible path sums 
  without recomputing from each node.
- The subtraction (currSum - targetSum) checks if a valid path exists 
  that ends at the current node.
- Backtracking ensures counts are local to the current path.

Time Complexity: O(n), since each node is processed once and HashMap operations are O(1).  
Space Complexity: O(n), for the HashMap storing prefix sums in the worst case (skewed tree).
*/

package Trees.Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _437_Path_Sum_III {
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

    // Method to find the total number of path sum equal to targetSum
    public static int pathSum(TreeNode root, int targetSum) {
        // Map to store prefix sum frequencies
        HashMap<Long, Integer> prefixSumMap = new HashMap<>();

        prefixSumMap.put(0L, 1); // Base case: one way to have sum = 0

        // Return the dfs recursion for the target sum
        return dfs(root, 0L, targetSum, prefixSumMap);
    }

    // Helper function to find the target sum
    private static int dfs(TreeNode node, long currSum, int targetSum, HashMap<Long, Integer> prefixSumMap) {
        // Edge case check
        if (node == null) {
            return 0;
        }

        // Update the running sum
        currSum += node.val;

        // Count paths that end at this node and sum to target
        int count = prefixSumMap.getOrDefault(currSum - targetSum, 0);

        // Update the prefix sum map
        prefixSumMap.put(currSum, prefixSumMap.getOrDefault(currSum, 0) + 1);

        // Recurse into left and right subtrees
        count += dfs(node.left, currSum, targetSum, prefixSumMap);
        count += dfs(node.right, currSum, targetSum, prefixSumMap);

        // Backtrack: remove the current prefix sum before returning
        prefixSumMap.put(currSum, prefixSumMap.get(currSum) - 1);

        // Return the count
        return count;
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
        int[] root = { 10, 5, -3, 3, 2, Integer.MIN_VALUE, 11, 3, -2, Integer.MIN_VALUE, 1 };
        int targetSum = 8;

        int result = pathSum(makeTree(root), targetSum);

        System.out.println("The total number of path sum equal to " + targetSum + " is : " + result);
    }
}