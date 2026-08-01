/*
LeetCode Problem: https://leetcode.com/problems/smallest-string-starting-from-leaf/

Question: 988. Smallest String Starting From Leaf

Problem Statement: You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.

Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

As a reminder, any shorter prefix of a string is lexicographically smaller.

    For example, "ab" is lexicographically smaller than "aba".

A leaf of a node is a node that has no children.

Example 1:
Input: root = [0,1,2,3,4,3,4]
Output: "dba"

Example 2:
Input: root = [25,1,3,1,3,0,2]
Output: "adz"

Example 3:
Input: root = [2,2,1,null,1,0,null,0]
Output: "abc"

Constraints:
    The number of nodes in the tree is in the range [1, 8500].
    0 <= Node.val <= 25
*/

/*
Approach: Pre-order DFS with Path Backtracking and Reverse Comparison
Goal:
- Find the lexicographically smallest string formed
  by reading node values from any leaf to the root,
  where each node value maps to a character
  ('a' + val).
Core Idea:
- DFS naturally builds the root-to-leaf path as it
  descends; the required leaf-to-root string is
  simply the reverse of the current path.
- Use a shared StringBuilder to accumulate the
  path, appending on the way down and removing the
  last character on the way back up (backtracking).
- At each leaf, reverse the StringBuilder in-place
  to get the leaf-to-root string, compare with the
  current best, then reverse back to restore the
  path state for continued traversal.
Algorithm Steps:
1. Initialize sb (shared path buffer) and res
   (current best result, null initially).
2. Call dfs(root).
3. In dfs(node):
   a. If node is null, return.
   b. Append (char)('a' + node.val) to sb.
   c. If node is a leaf (no left and right child):
      - Reverse sb in-place to get leaf-to-root
        string s.
      - If res is null or res.compareTo(s) > 0,
        update res = s.
      - Reverse sb back to restore path order.
   d. Recurse dfs(node.left) and dfs(node.right).
   e. Remove the last character from sb
      (backtrack).
4. Return res.
Why It Works:
- Appending on descent and removing on ascent
  ensures sb always reflects exactly the characters
  on the path from root to the current node.
- Reversing sb in-place at leaves avoids allocating
  a new path buffer per leaf, keeping extra
  allocations to one String per candidate result
  update.
- Comparing with res.compareTo(s) > 0 selects the
  lexicographically smaller string correctly,
  consistent with Java's natural string ordering.
Time Complexity:
- O(n * h)
where n is the number of nodes and h is the tree
height. Each leaf triggers an O(h) reverse and
string construction; there are O(n) nodes visited
overall.
Space Complexity:
- O(h)
for the StringBuilder path buffer and the recursive
call stack, where h is the tree height.
Result:
- Returns the lexicographically smallest
  leaf-to-root string across all root-to-leaf paths
  in the tree.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the stringbuilder for string formation
  private StringBuilder sb = new StringBuilder();

  // Initialize the res string
  private String res = null;

  // Method to find the lexicographically smallest string that starts at a leaf of
  // this tree and ends at the root
  public String smallestFromLeaf(TreeNode root) {
    // Call the dfs method for finding the lexicographically smallest string
    this.dfs(root);

    // Return the res string
    return this.res;
  }

  // Helper method for the dfs
  private void dfs(TreeNode root) {
    // If root is null then return terminate the method
    if (root == null) {
      return;
    }

    // Add the character to the string
    this.sb.append((char) ('a' + root.val));

    // If we are at the leaf node then find the lexicographically smallest string
    if (root.left == null && root.right == null) {
      // Get the string
      String s = this.sb.reverse().toString();

      // Update the lexicographically smaller result
      if (this.res == null ||this.res.compareTo(s) > 0) {
        this.res = s;
      }

      // Reverse the string
      this.sb.reverse();
    }

    // Call the dfs method on the left and right child
    this.dfs(root.left);
    this.dfs(root.right);

    // Delete the character from the end of the string
    this.sb.deleteCharAt(this.sb.length() - 1);
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
public class _988_Smallest_String_Starting_From_Leaf {
  // Main method to test smallestFromLeaf
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 0, 1, 2, 3, 4, 3, 4 });

    String result = new Solution().smallestFromLeaf(root);

    System.out.println(
        "The lexicographically smallest string that starts at a leaf of this tree and ends at the root is : " + result);
  }
}
