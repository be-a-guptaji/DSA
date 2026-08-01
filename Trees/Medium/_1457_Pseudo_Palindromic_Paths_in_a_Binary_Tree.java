/*
LeetCode Problem: https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/

Question: 1457. Pseudo-Palindromic Paths in a Binary Tree

Problem Statement: Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.

Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

Example 1:
Input: root = [2,3,1,3,1,null,1]
Output: 2 
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).

Example 2:
Input: root = [2,1,1,1,3,null,null,null,null,null,1]
Output: 1 
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).

Example 3:
Input: root = [9]
Output: 1

Constraints:
    The number of nodes in the tree is in the range [1, 10^5].
    1 <= Node.val <= 9
*/

/*
Approach: Pre-order DFS with Bitmask Frequency Tracking
Goal:
- Count all root-to-leaf paths whose node values
  can be rearranged to form a palindrome
  (pseudo-palindromic paths).
Core Idea:
- A sequence of digits can form a palindrome if and
  only if at most one digit has an odd frequency.
- Track digit frequencies using a bitmask where
  bit i represents the parity (odd/even count) of
  digit i along the current root-to-leaf path.
- XOR-ing bit i toggles its parity each time digit
  i is encountered; after visiting all nodes on a
  path, the bitmask has a 1 for each digit with odd
  frequency.
- A bitmask with at most one bit set satisfies the
  palindrome condition, checked via the standard
  power-of-two test: (path & (path - 1)) == 0.
Algorithm Steps:
1. Call dfs(root, 0) with an initial bitmask of 0.
2. In dfs(node, path):
   a. If node is null, return 0.
   b. Toggle bit node.val in path:
      - path ^= (1 << node.val)
   c. If node is a leaf (no left and right child):
      - Check (path & (path - 1)) == 0:
        - True: at most one digit has odd frequency,
          the path is pseudo-palindromic, return 1.
        - False: more than one digit has odd
          frequency, return 0.
   d. Return dfs(node.left, path) +
      dfs(node.right, path) to accumulate valid
      paths from both subtrees.
3. Return the total count from the initial call.
Why It Works:
- XOR toggling on bit node.val correctly tracks
  parity: even occurrences cancel out to 0, odd
  occurrences leave a 1.
- (path & (path - 1)) == 0 is true if and only if
  path is 0 (all even frequencies) or exactly one
  bit is set (one odd frequency), both valid for
  palindrome construction.
- Passing path by value through recursion ensures
  each root-to-leaf path has its own independent
  bitmask state without backtracking overhead.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
visited exactly once.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack. Worst case O(n) for a skewed tree.
Result:
- Returns the count of root-to-leaf paths that are
  pseudo-palindromic.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the number of pseudo-palindromic paths going from the root
  // node to leaf nodes
  public int pseudoPalindromicPaths(TreeNode root) {
    // Call the recursive dfs method for finding pseudo-palindromic paths
    return this.dfs(root, 0);
  }

  // Helper method for finding pseudo-palindromic paths
  private int dfs(TreeNode root, int path) {
    // If root is null then return 0
    if (root == null) {
      return 0;
    }

    // Set the XOR bit of the path
    path ^= (1 << root.val);

    // If it is a leaf node then return the pseudo-palindromic paths
    if (root.left == null && root.right == null) {
      return (path & (path - 1)) == 0 ? 1 : 0;
    }

    // Return the dfs call on the left and right child
    return this.dfs(root.left, path) + this.dfs(root.right, path);
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
public class _1457_Pseudo_Palindromic_Paths_in_a_Binary_Tree {
  // Main method to test pseudoPalindromicPaths
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 2, 3, 1, 3, 1, null, 1 });

    int result = new Solution().pseudoPalindromicPaths(root);

    System.out.println("The number of pseudo-palindromic paths going from the root node to leaf nodes is : " + result);
  }
}
