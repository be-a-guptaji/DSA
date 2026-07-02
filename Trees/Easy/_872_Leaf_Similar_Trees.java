/*
LeetCode Problem: https://leetcode.com/problems/leaf-similar-trees/

Question: 872. Leaf-Similar Trees

Problem Statement: Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Example 1:
Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true

Example 2:
Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false

Constraints:
    The number of nodes in each tree will be in the range [1, 200].
    Both of the given trees will have values in the range [0, 200].
*/

/*
Approach: Depth-First Search + Leaf Sequence Comparison

Goal:
- Determine whether two binary trees are
  leaf-similar.
- Two trees are leaf-similar if their leaf nodes
  appear in the same left-to-right order.

Core Idea:
- Perform a depth-first traversal on each tree.
- Whenever a leaf node is encountered, append its
  value to a list.
- After traversing both trees, compare the two
  leaf sequences element by element.
- The trees are leaf-similar only if both
  sequences are identical.

Algorithm Steps:
1. Initialize two lists to store the leaf
   sequences.
2. Perform DFS on the first tree:
   - If the current node is null, return.
   - If it is a leaf, add its value to the list.
   - Otherwise, recursively traverse its left and
     right subtrees.
3. Repeat the same traversal for the second tree.
4. Compare the sizes of both lists.
5. If the sizes differ, return false.
6. Compare each corresponding element of the two
   lists.
7. If every element matches, return true;
   otherwise, return false.

Why It Works:
- DFS visits every node exactly once.
- Leaf nodes are collected in left-to-right order
  because the traversal always explores the left
  subtree before the right subtree.
- Comparing the resulting sequences determines
  whether the two trees have identical leaf
  traversals.

Time Complexity:
- O(n + m)

where n and m are the number of nodes in the two
trees.

Space Complexity:
- O(l1 + l2 + h)

where:
- l1 is the number of leaves in the first tree.
- l2 is the number of leaves in the second tree.
- h is the maximum recursion depth.

Result:
- Returns true if both binary trees have identical
  leaf sequences; otherwise returns false.
*/

package Trees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to check if the leaf are similar or not
  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    // Initialize the order of the leaf node
    ArrayList<Integer> order1 = new ArrayList<>();
    ArrayList<Integer> order2 = new ArrayList<>();

    // Call the recursive dfs method to find the leaf nodes
    this.dfs(root1, order1);
    this.dfs(root2, order2);

    // If length of the order is not same then return false
    if (order1.size() != order2.size()) {
      return false;
    } else {
      // Iterate over the order list
      for (int i = 0; i < order1.size(); i++) {
        // If order is not same then return false
        if (Integer.compare(order1.get(i), order2.get(i)) != 0) {
          return false;
        }
      }
    }

    // Return true in the end
    return true;
  }

  // Helper method to find the leaf node
  private void dfs(TreeNode root, ArrayList<Integer> order) {
    // If root is null then return
    if (root == null) {
      return;
    }

    // If root is leaf node add it to the order and return
    if (root.left == null && root.right == null) {
      order.add(root.val);
      return;
    }

    // Call the recursive dfs method to find the leaf nodes
    this.dfs(root.left, order);
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
public class _872_Leaf_Similar_Trees {
  // Main method to test leafSimilar
  public static void main(String[] args) {
    TreeNode root1 = TreeNode.makeTree(new Integer[] { 3, 5, 1, 6, 2, 9, 8, null, null, 7, 4 });
    TreeNode root2 = TreeNode.makeTree(new Integer[] { 3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8 });

    boolean result = new Solution().leafSimilar(root1, root2);

    System.out.println("The leafs are" + (result ? " " : " not ") + "similar.");
  }
}
