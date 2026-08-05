/*
LeetCode Problem: https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/

Question: 2096. Step-By-Step Directions From a Binary Tree Node to Another

Problem Statement: You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

    'L' means to go from a node to its left child node.
    'R' means to go from a node to its right child node.
    'U' means to go from a node to its parent node.

Return the step-by-step directions of the shortest path from node s to node t.

Example 1:
Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:
Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.

Constraints:
    The number of nodes in the tree is n.
    2 <= n <= 10^5
    1 <= Node.val <= n
    All the values in the tree are unique.
    1 <= startValue, destValue <= n
    startValue != destValue
*/

/*
Approach: Simultaneous Root-to-Node Path Finding with LCA Elimination
Goal:
- Find the shortest path directions from node
  startValue to node destValue in a binary tree,
  expressed as a string of 'U' (up), 'L' (left),
  and 'R' (right) moves.
Core Idea:
- The shortest path between any two nodes in a
  tree passes through their Lowest Common Ancestor
  (LCA).
- Find root-to-start and root-to-dest paths
  simultaneously in a single DFS pass.
- Strip the common prefix of both paths (the shared
  root-to-LCA portion); what remains is the
  LCA-to-start suffix and LCA-to-dest suffix.
- Replace every character in the LCA-to-start
  suffix with 'U' (moving up from start to LCA),
  then append the LCA-to-dest suffix directly
  (moving down from LCA to dest).
Algorithm Steps:
1. Initialize paths[0] (root-to-start) and
   paths[1] (root-to-dest) as StringBuilders.
2. Call dfs(root, values, '*', paths), where '*'
   is a sentinel root character discarded later:
   a. If node is null or both values are found,
      return.
   b. Append the current direction character to
      paths[0] if start is not yet found; if
      root.val == startValue, mark start as found
      (values[0] = 0).
   c. Append the current direction character to
      paths[1] if dest is not yet found; if
      root.val == destValue, mark dest as found
      (values[1] = 0).
   d. Recurse into left child with 'L' and right
      child with 'R'.
   e. Backtrack: remove the last appended character
      from paths[0] and paths[1] if their
      respective targets are not yet found.
3. Strip the common prefix from paths[0] and
   paths[1] (the root-to-LCA portion shared by
   both paths).
4. Replace the remaining paths[0] characters with
   an equal count of 'U' moves (ascending from
   start to LCA).
5. Concatenate paths[0] ('U' moves) with paths[1]
   (descent from LCA to dest) and return.
Why It Works:
- Both paths are tracked simultaneously in one DFS
  pass; the backtracking ensures only the actual
  root-to-target path remains in each StringBuilder
  when the target is found.
- The common prefix of both paths is exactly the
  root-to-LCA segment; removing it isolates the
  LCA-relative segments for both nodes.
- Replacing the LCA-to-start path length with 'U'
  characters is correct because every step from
  start to LCA is an upward move regardless of
  whether the original direction was 'L' or 'R'.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
visited at most once during DFS.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack and the path StringBuilders, which are at
most O(h) characters long.
Result:
- Returns the shortest path direction string from
  startValue to destValue.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the step-by-step directions of the shortest path from node s
  // to node t
  public String getDirections(TreeNode root, int startValue, int destValue) {
    // Initialize the paths array of string builder
    StringBuilder[] paths = new StringBuilder[] { new StringBuilder(), new StringBuilder() };

    // Call the recursive dfs method to find the paths
    this.dfs(root, new int[] { startValue, destValue }, '*', paths);

    // Get the minimum length of both the string builders
    int minimumLength = Math.min(paths[0].length(), paths[1].length());

    // Iterate over the string builders and remove the common prefix
    for (int i = 0; i < minimumLength; i++) {
      // If both character missmatch then break out of the loop
      if (paths[0].charAt(0) != paths[1].charAt(0)) {
        break;
      }

      // Delete the first character form both the string builders
      paths[0].deleteCharAt(0);
      paths[1].deleteCharAt(0);
    }

    // Initialize the length of paths[0] string builder
    int length = paths[0].length();

    // Reset the paths[0]
    paths[0] = new StringBuilder();

    // Set all the paths[0] to 'U'
    for (int i = 0; i < length; i++) {
      paths[0].append('U');
    }

    // Return the first string builder after converting it to string
    return paths[0].append(paths[1]).toString();
  }

  // Helper method for find the path for startValue and destValue
  private void dfs(TreeNode root, int[] values, char path, StringBuilder[] paths) {
    // If root is null or both values are found then terminate the method
    if ((root == null) || (values[0] == 0 && values[1] == 0)) {
      return;
    }

    // If values[0] is not zero then add the character to paths[0]
    if (values[0] != 0) {
      paths[0].append(path);

      // If values[0] is equal to root.val then update the values[0] to zero
      if (values[0] == root.val) {
        values[0] = 0;
      }
    }

    // If values[1] is not zero then add the character to paths[1]
    if (values[1] != 0) {
      paths[1].append(path);

      // If values[1] is equal to root.val then update the values[1] to zero
      if (values[1] == root.val) {
        values[1] = 0;
      }
    }

    // Call the dfs method on the left and right child
    this.dfs(root.left, values, 'L', paths);
    this.dfs(root.right, values, 'R', paths);

    // If values[0] is not zero then remove the last character from paths[0] string
    // builder
    if (values[0] != 0) {
      paths[0].deleteCharAt(paths[0].length() - 1);
    }

    // If values[1] is not zero then remove the last character from paths[1] string
    // builder
    if (values[1] != 0) {
      paths[1].deleteCharAt(paths[1].length() - 1);
    }
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
public class _2096_Step_By_Step_Directions_From_a_Binary_Tree_Node_to_Another {
  // Main method to test getDirections
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 5, 1, 2, 3, null, 6, 4 });
    int startValue = 3;
    int destValue = 6;

    String result = new Solution().getDirections(root, startValue, destValue);

    System.out.println("The step-by-step directions of the shortest path from node " + startValue + " to node "
        + destValue + " is : " + result);
  }
}
