/*
LeetCode Problem: https://leetcode.com/problems/all-possible-full-binary-trees/

Question: 894. All Possible Full Binary Trees

Problem Statement: Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.

Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.

A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Example 1:
Input: n = 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

Example 2:
Input: n = 3
Output: [[0,0,0]]

Constraints:
    1 <= n <= 20
*/

/*
Approach: Static DP Precomputation of All Full Binary Trees
Goal:
- Generate all structurally unique full binary trees
  (every node has exactly 0 or 2 children) with
  exactly n nodes.
- Return an empty list if n is even (impossible for
  a full binary tree).
Core Idea:
- A full binary tree with n nodes must have an odd
  number of nodes (root + even split of remaining
  nodes into left and right subtrees).
- For each valid n, split the remaining n - 1 nodes
  into every valid (leftNodes, rightNodes) pair
  where both are odd and sum to n - 1.
- The set of unique trees for n is the cartesian
  product of all trees for leftNodes and all trees
  for rightNodes, with a new root connecting each
  pair.
- Precompute all results at class load time via a
  static initializer so repeated calls to
  allPossibleFBT(n) are O(1) lookups.
Algorithm Steps:
1. Initialize dp as an ArrayList of 20 ArrayLists,
   indexed by (numberOfNodes - 1).
2. Base case: dp[0] holds a single empty TreeNode
   representing a 1-node tree.
3. For each numberOfNodes from 3 to 20 (step 2,
   odd values only):
   a. For each split where leftNodes starts at 1
      and increments by 2, rightNodes =
      numberOfNodes - 1 - leftNodes:
      - For each left tree in dp[leftNodes - 1]:
        - For each right tree in dp[rightNodes - 1]:
          - Create a new root node.
          - Attach left and right trees as children.
          - Add root to dp[numberOfNodes - 1].
4. allPossibleFBT(n): return dp[n - 1] directly.
Why It Works:
- Full binary trees with n nodes only exist for odd
  n; iterating in steps of 2 ensures only valid
  sizes are computed.
- Every unique structural combination is captured
  by the cartesian product of all left and right
  subtree shapes across all valid splits.
- Static precomputation amortizes the cost across
  all calls in the JVM lifetime; each individual
  query is a single ArrayList lookup.
Time Complexity:
- O(2^(n/2)) precomputation (Catalan number growth)
  bounded by the fixed 20-node limit, effectively
  O(1) constant work at class load time.
- O(1) per allPossibleFBT(n) call.
Space Complexity:
- O(2^(n/2)) for the dp table storing all unique
  trees across all odd values up to 20 nodes.
Result:
- Returns the list of all structurally unique full
  binary trees with exactly n nodes, or an empty
  list if n is even.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the dp array
  private static final ArrayList<ArrayList<TreeNode>> dp;

  // Static method to initialize the dp array
  static {
    // Initialize the dp array
    dp = new ArrayList<>(20);

    // Initialize the arraylist
    for (int list = 0; list < 20; list++) {
      dp.add(new ArrayList<>());
    }

    // Initialize the basecase for the list
    dp.get(0).add(new TreeNode());

    // Iterate over the number till 21
    for (int numberOfNodes = 3; numberOfNodes < 21; numberOfNodes += 2) {
      // Build the left and right subtree
      for (int leftNodes = 1,
          rightNodes = numberOfNodes - 1
              - leftNodes; leftNodes < numberOfNodes; leftNodes += 2, rightNodes = numberOfNodes - 1 - leftNodes) {
        // Build the tree for the left and right node
        for (TreeNode left : dp.get(leftNodes - 1)) {
          for (TreeNode right : dp.get(rightNodes - 1)) {
            TreeNode root = new TreeNode(0);
            root.left = left;
            root.right = right;

            dp.get(numberOfNodes - 1).add(root);
          }
        }
      }
    }
  }

  // Method to find the final list of trees in any order
  public ArrayList<TreeNode> allPossibleFBT(int n) {
    // Return the dp node
    return dp.get(n - 1);
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
public class _894_All_Possible_Full_Binary_Trees {
  // Main method to test allPossibleFBT
  public static void main(String[] args) {
    int n = 7;

    ArrayList<TreeNode> result = new Solution().allPossibleFBT(n);

    System.out.println("The final list of trees in any order is : " + result);
  }
}
