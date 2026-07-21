/*
LeetCode Problem: https://leetcode.com/problems/unique-binary-search-trees/

Question: 96. Unique Binary Search Trees

Problem Statement: Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

Example 1:
Input: n = 3
Output: 5

Example 2:
Input: n = 1
Output: 1

Constraints:
    1 <= n <= 19
*/

/*
Approach: Dynamic Programming with Catalan Number Recurrence
Goal:
- Count the number of structurally unique binary
  search trees (BSTs) that can be constructed using
  n nodes with distinct values 1 to n.
Core Idea:
- For any set of n nodes, each node can serve as
  the root.
- If node k is the root, all nodes with values less
  than k must be in the left subtree, and all nodes
  with values greater than k must be in the right
  subtree.
- The number of unique BSTs with n nodes is the sum
  over all possible roots of: (unique BSTs with
  left subtree nodes) * (unique BSTs with right
  subtree nodes).
- This recurrence relation generates the Catalan
  numbers, which can be computed via dynamic
  programming bottom-up.
Algorithm Steps:
1. Initialize dp array of size n + 1, where
   dp[i] represents the count of unique BSTs with
   i nodes.
2. Set dp[0] = 1 and dp[1] = 1 (base cases: one
   way to arrange 0 nodes, one way to arrange
   1 node).
3. For each number of nodes from 2 to n:
   a. Initialize totalSum = 0.
   b. For each possible root position root from
      1 to node:
      - left_nodes = root - 1 (nodes smaller
        than root).
      - right_nodes = node - root (nodes larger
        than root).
      - Add dp[left_nodes] * dp[right_nodes] to
        totalSum.
   c. Set dp[node] = totalSum.
4. Return dp[n].
Why It Works:
- The recurrence relation dp[n] = sum over all
  roots of (dp[left_subtree_size] *
  dp[right_subtree_size]) directly models the
  combinatorial fact that unique BST structures
  depend only on the number of nodes in each
  subtree, not their actual values.
- Values 1 to n can be permuted within any fixed
  structure without changing the BST property or
  the count of structurally distinct trees.
- Bottom-up DP avoids redundant recomputation by
  solving all subproblems of size 2, 3, ..., n
  before tackling size n.
Time Complexity:
- O(n^2)
since the outer loop runs n times and the inner
loop runs up to n times, performing constant work
per iteration.
Space Complexity:
- O(n)
for the dp array.
Result:
- Returns the count of structurally unique BSTs
  with n nodes, which is the nth Catalan number.
*/

package Trees.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the number of structurally unique BST's (binary search trees)
  // which has exactly n nodes of unique values from 1 to n
  public int numTrees(int n) {
    // Initialize the dp array of size n + 1
    int[] dp = new int[n + 1];

    // Initialize the dp base value
    Arrays.fill(dp, 1);

    // Iterate over the nodes
    for (int node = 2; node <= n; node++) {
      // Initialize the total sum
      int totalSum = 0;

      // Iterate over all the root 1 to node
      for (int root = 1; root <= node; root++) {
        totalSum += dp[root - 1] * dp[node - root];
      }

      // Set the dp value
      dp[node] = totalSum;
    }

    // Return the dp[n]
    return dp[n];
  }
}

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

// Mock class for makeing the TreeNode Class
class TreeNode {
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

  // Helper method to make the binary tree from the array
  public static TreeNode makeTree(Integer[] arr) {
    if (arr == null || arr.length == 0 || arr[0] == null) {
      return null;
    }

    TreeNode root = new TreeNode(arr[0]);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    int i = 1;

    while (!queue.isEmpty() && i < arr.length) {
      TreeNode current = queue.poll();

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

    return root;
  }
}

// Main Class
public class _96_Unique_Binary_Search_Trees {
  // Main method to test numTrees
  public static void main(String[] args) {
    int n = 5;

    int result = new Solution().numTrees(n);

    System.out.println(
        "The number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to "
            + n + " is : " + result);
  }
}
