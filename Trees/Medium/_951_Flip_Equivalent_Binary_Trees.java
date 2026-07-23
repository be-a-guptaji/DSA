/*
LeetCode Problem: https://leetcode.com/problems/flip-equivalent-binary-trees/

Question: 951. Flip Equivalent Binary Trees

Problem Statement: For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.

A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.

Given the roots of two binary trees root1 and root2, return true if the two trees are flip equivalent or false otherwise.

Example 1:
Flipped Trees Diagram
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.

Example 2:
Input: root1 = [], root2 = []
Output: true

Example 3:
Input: root1 = [], root2 = [1]
Output: false

Constraints:
    The number of nodes in each tree is in the range [0, 100].
    Each tree will have unique node values in the range [0, 99].
*/

/*
Approach: Recursive Structural Equivalence with Child Flip Options
Goal:
- Determine if two binary trees are flip-equivalent:
  trees that can be made identical by flipping
  (swapping left and right children) zero or more
  times at any nodes.
Core Idea:
- Two trees are flip-equivalent if their root values
  match and their subtrees can be recursively made
  equivalent via selective flips.
- At each node, there are two possible alignments:
  match left-to-left and right-to-right (no flip),
  or match left-to-right and right-to-left (flip).
- If either alignment leads to flip-equivalence in
  both subtrees, the trees are flip-equivalent.
Algorithm Steps:
1. Call flipEquiv(root1, root2) on both tree roots.
2. In flipEquiv(node1, node2):
   a. If both nodes are null, return true (empty
      subtrees are equivalent).
   b. If exactly one node is null, return false
      (one tree is empty, the other is not).
   c. If node1.val != node2.val, return false
      (root values must match).
   d. Otherwise, check both possible alignments:
      - Alignment 1 (no flip):
        flipEquiv(node1.left, node2.left) &&
        flipEquiv(node1.right, node2.right)
      - Alignment 2 (flip):
        flipEquiv(node1.left, node2.right) &&
        flipEquiv(node1.right, node2.left)
      - Return true if either alignment succeeds.
3. Return the result from the initial call.
Why It Works:
- The recursive structure directly mirrors the
  definition of flip-equivalence: two trees are
  equivalent if their roots match and their
  subtrees can be aligned (possibly after flipping).
- At each internal node, exploring both alignment
  options (flipped and non-flipped) exhaustively
  covers all possible flip configurations.
- Memoization is implicit in the tree structure:
  identical subtrees rooted at the same value are
  compared only once (the recursion naturally
  avoids redundant work for distinct nodes).
- The base case (both null) correctly identifies
  equivalent empty subtrees; the early termination
  when one is null or values differ avoids
  unnecessary recursion.
Time Complexity:
- O(min(n1, n2))
where n1 and n2 are the number of nodes in the two
trees. In the worst case, every node in the smaller
tree is visited once. If trees differ early, the
traversal terminates sooner.
Space Complexity:
- O(h)
where h is the maximum height of the two trees, for
the recursive call stack. Worst case O(n) for a
skewed tree.
Result:
- Returns true if the two trees are flip-equivalent,
  false otherwise.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find if the two tree are equivalent or not
  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null || root2 == null) {
      return root1 == null && root2 == null;
    } else if (root1.val != root2.val) {
      return false;
    } else {
      return (this.flipEquiv(root1.left, root2.left) && this.flipEquiv(root1.right, root2.right))
          || (this.flipEquiv(root1.left, root2.right) && this.flipEquiv(root1.right, root2.left));
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
public class _951_Flip_Equivalent_Binary_Trees {
  // Main method to test flipEquiv
  public static void main(String[] args) {
    TreeNode root1 = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, 5, 6, null, null, null, 7, 8 });
    TreeNode root2 = TreeNode.makeTree(new Integer[] { 1, 3, 2, null, 6, 4, 5, null, null, null, null, 8, 7 });

    boolean result = new Solution().flipEquiv(root1, root2);

    System.out.println("The two tree are" + (result ? " " : " not ") + "equivalent.");
  }
}
