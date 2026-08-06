/*
LeetCode Problem: https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/

Question: 1028. Recover a Tree From Preorder Traversal

Problem Statement: We run a preorder depth-first search (DFS) on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.

If a node has only one child, that child is guaranteed to be the left child.

Given the output traversal of this traversal, recover the tree and return its root.

Example 1:
Input: traversal = "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

Example 2:
Input: traversal = "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]

Example 3:
Input: traversal = "1-401--349---90--88"
Output: [1,401,null,349,88,90]

Constraints:
    The number of nodes in the original tree is in the range [1, 1000].
    1 <= Node.val <= 10^9
*/

/*
Approach: Recursive Pre-order Reconstruction with Depth-Dash Matching
Goal:
- Reconstruct a binary tree from its pre-order
  depth-first traversal string, where each node's
  depth is indicated by the number of leading '-'
  characters before its value.
Core Idea:
- In the traversal string, a node at depth d is
  preceded by exactly d dashes.
- Pre-order visits root first, then left subtree,
  then right subtree; so reading the string left
  to right naturally follows pre-order sequence.
- At each recursive call, peek ahead to count
  dashes without advancing the global index; if
  the dash count matches the expected depth,
  consume the dashes and value and recurse deeper.
  If not, the current position belongs to an
  ancestor's subtree, so return null without
  consuming anything.
Algorithm Steps:
1. Convert traversal string to a char array for
   O(1) indexed access.
2. Call dfs(str, 0) for the root at depth 0.
3. In dfs(str, depth):
   a. Save current index as tempIndex.
   b. Count consecutive '-' characters from
      tempIndex without advancing this.index.
   c. If dash count != depth, this position does
      not belong to a node at this depth; return
      null without advancing this.index.
   d. Advance this.index past the dashes.
   e. Parse the integer value digit by digit,
      advancing this.index past the digits.
   f. Create a new TreeNode with the parsed value.
   g. Recurse: node.left = dfs(str, depth + 1).
   h. Recurse: node.right = dfs(str, depth + 1).
   i. Return the constructed node.
4. Return the node from the initial call as root.
Why It Works:
- Peeking ahead (using tempIndex) before committing
  to consume allows the recursion to correctly
  determine subtree boundaries without a separate
  lookahead pass.
- If the dash count at the current position is less
  than expected depth, the position belongs to a
  shallower ancestor's right subtree; returning
  null signals the parent to stop recursing deeper.
- The global index advances only when a valid node
  is confirmed at the correct depth, ensuring no
  characters are skipped or double-consumed.
Time Complexity:
- O(n)
where n is the length of the traversal string,
since each character is read a constant number of
times (once for peeking, once for consuming).
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack.
Result:
- Returns the root of the reconstructed binary
  tree matching the given pre-order traversal.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the index variable
  private int index = 0;

  // Method to find the root of the binary tree
  public TreeNode recoverFromPreorder(String traversal) {
    // Convert the string into character array
    char[] str = traversal.toCharArray();

    // Call the recursive dfs method
    return this.dfs(str, 0);
  }

  // Helper method to perform the dfs search
  private TreeNode dfs(char[] str, int depth) {
    // Store the current index
    int tempIndex = this.index;

    // Initialize the dash variable
    int dash = 0;

    // Count the '-' in the character array
    while (tempIndex < str.length && str[tempIndex] == '-') {
      dash++;
      tempIndex++;
    }

    // Return null if the depth does not match
    if (dash != depth) {
      return null;
    }

    // Move the index to the start of the value
    this.index = tempIndex;

    // Initialize the value variable
    int value = 0;

    // Get the node value
    while (index < str.length && Character.isDigit(str[this.index])) {
      value = value * 10 + (str[this.index] - '0');
      this.index++;
    }

    // Initialize the TreeNode
    TreeNode node = new TreeNode(value);

    // Find the left subtree
    node.left = this.dfs(str, depth + 1);

    // Find the right subtree
    node.right = this.dfs(str, depth + 1);

    // Return the current node
    return node;
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
public class _1028_Recover_a_Tree_From_Preorder_Traversal {
  // Main method to test recoverFromPreorder
  public static void main(String[] args) {
    String traversal = "1-2--3--4-5--6--7";

    TreeNode result = new Solution().recoverFromPreorder(traversal);

    System.out.println("The root of the binary tree is : " + result);
  }
}
