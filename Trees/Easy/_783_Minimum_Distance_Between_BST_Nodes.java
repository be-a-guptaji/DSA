/*
LeetCode Problem: https://leetcode.com/problems/minimum-distance-between-bst-nodes/

Question: 783. Minimum Distance Between BST Nodes

Problem Statement: Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

Example 1:
Input: root = [4,2,6,1,3]
Output: 1

Example 2:
Input: root = [1,0,48,null,null,12,49]
Output: 1

Constraints:
    The number of nodes in the tree is in the range [2, 100].
    0 <= Node.val <= 10^5

Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
*/

/*
Approach: Inorder Traversal + Adjacent Difference

Goal:
- Find the minimum absolute difference between the
  values of any two different nodes in a Binary
  Search Tree (BST).

Core Idea:
- Perform an inorder traversal of the BST.
- Inorder traversal visits the nodes in ascending
  order.
- In a sorted sequence, the minimum absolute
  difference can only occur between two adjacent
  elements.
- Store the inorder traversal in a list and
  compute the minimum difference between every
  pair of consecutive values.

Algorithm Steps:
1. Initialize an empty list to store the inorder
   traversal.
2. Perform an inorder DFS:
   - Traverse the left subtree.
   - Add the current node's value to the list.
   - Traverse the right subtree.
3. Traverse the inorder list.
4. Compute the difference between every pair of
   consecutive elements.
5. Maintain the minimum difference encountered.
6. Return the minimum difference.

Why It Works:
- The Binary Search Tree property guarantees that
  an inorder traversal produces values in sorted
  order.
- In a sorted sequence, the closest pair of values
  must be adjacent.
- Therefore, checking only consecutive elements is
  sufficient to find the minimum difference.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

for storing the inorder traversal.

Result:
- Returns the minimum absolute difference between
  the values of any two different nodes in the
  Binary Search Tree.
*/

package Trees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the minimum difference between the values of any two different
  // nodes in the tree
  public int minDiffInBST(TreeNode root) {
    // Initialize the order list variable
    ArrayList<Integer> order = new ArrayList<>();

    // Call the recursive dfs function
    this.dfs(root, order);

    // Initialize the minimumDifferenceInBST variable
    int minimumDifferenceInBST = 1000000;

    // Find the minimum difference between the values of any two different nodes in
    // the tree
    for (int i = 0; i < order.size() - 1; i++) {
      // Update the minimum difference variable
      minimumDifferenceInBST = Math.min(minimumDifferenceInBST, order.get(i + 1) - order.get(i));
    }

    // Return the minimumDifferenceInBST variable
    return minimumDifferenceInBST;
  }

  // Helper method for the dfs serach of the tree
  private void dfs(TreeNode root, ArrayList<Integer> order) {
    // If root is null then return
    if (root == null) {
      return;
    }

    // Call the recursive dfs function
    this.dfs(root.left, order);

    // Add the value to the order
    order.add(root.val);

    // Call the recursive dfs function
    this.dfs(root.right, order);
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
public class _783_Minimum_Distance_Between_BST_Nodes {
  // Main method to test minDiffInBST
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 0, 48, null, null, 12, 49 });

    int result = new Solution().minDiffInBST(root);

    System.out
        .println("The minimum difference between the values of any two different nodes in the tree is : " + result);
  }
}
