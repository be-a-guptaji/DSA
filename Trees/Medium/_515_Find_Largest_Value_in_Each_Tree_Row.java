/*
LeetCode Problem: https://leetcode.com/problems/find-largest-value-in-each-tree-row/

Question: 515. Find Largest Value in Each Tree Row

Problem Statement: Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Example 1:
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]

Example 2:
Input: root = [1,2,3]
Output: [1,3]

Constraints:
    The number of nodes in the tree will be in the range [0, 10^4].
    -2^31 <= Node.val <= 2^31 - 1
*/

/*
Approach: Pre-order DFS with Per-depth Maximum Tracking
Goal:
- Find the maximum value at each level of the
  binary tree and return them as a list indexed
  by level.
Core Idea:
- Pre-order DFS naturally visits every node exactly
  once while carrying the current depth as a
  parameter.
- A result list indexed by depth tracks the running
  maximum per level; when a depth is encountered
  for the first time, extend the list with
  Integer.MIN_VALUE as the initial sentinel before
  comparing.
Algorithm Steps:
1. Initialize an empty maxList.
2. Call dfs(root, 0, maxList).
3. In dfs(node, depth, maxList):
   a. If node is null, return.
   b. If depth >= maxList.size(), append
      Integer.MIN_VALUE to extend the list to
      cover this depth for the first time.
   c. Update maxList.set(depth, Math.max(node.val,
      maxList.get(depth))) to track the running
      maximum at this depth.
   d. Recurse dfs(node.left, depth + 1, maxList).
   e. Recurse dfs(node.right, depth + 1, maxList).
4. Return maxList.
Why It Works:
- Pre-order traversal guarantees a depth is first
  encountered via the leftmost node at that level,
  after which all subsequent nodes at the same
  depth update the running maximum correctly.
- Extending the list lazily (only when a new depth
  is first seen) avoids pre-allocating for an
  unknown tree height.
- Integer.MIN_VALUE as the sentinel ensures the
  first real value at any depth always replaces it
  regardless of sign.
Time Complexity:
- O(n)
where n is the number of nodes, since each node
is visited exactly once.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack. The result list uses O(h) additional space
for one entry per level. Worst case O(n) for a
skewed tree.
Result:
- Returns a list where index i holds the maximum
  node value at depth i of the binary tree.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find an array of the largest value in each row of the tree
  public ArrayList<Integer> largestValues(TreeNode root) {
    // Initialize the array list
    ArrayList<Integer> maxList = new ArrayList<>();

    // Call the recursive dfs method
    this.dfs(root, 0, maxList);

    // Return the maxList
    return maxList;
  }

  // Helper method for the dfs
  private void dfs(TreeNode root, int depth, ArrayList<Integer> maxList) {
    // If root is null then return
    if (root == null) {
      return;
    }

    // If depth size is more then the array size then increase the array list
    if (depth >= maxList.size()) {
      maxList.add(Integer.MIN_VALUE);
    }

    // Update the maxList for the depth
    maxList.set(depth, Math.max(root.val, maxList.get(depth)));

    // Call the dfs method on the left and right node
    this.dfs(root.left, depth + 1, maxList);
    this.dfs(root.right, depth + 1, maxList);
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
public class _515_Find_Largest_Value_in_Each_Tree_Row {
  // Main method to test largestValues
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 3, 2, 5, 3, null, 9 });

    ArrayList<Integer> result = new Solution().largestValues(root);

    System.out.println("An array of the largest value in each row of the tree is : " + result);
  }
}
