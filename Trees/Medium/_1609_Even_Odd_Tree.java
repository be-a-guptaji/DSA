/*
LeetCode Problem: https://leetcode.com/problems/even-odd-tree/

Question: 1609. Even Odd Tree

Problem Statement: A binary tree is named Even-Odd if it meets the following conditions:

    The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
    For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
    For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).

Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

Example 1:
Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
Output: true
Explanation: The node values on each level are:
Level 0: [1]
Level 1: [10,4]
Level 2: [3,7,9]
Level 3: [12,8,6,2]
Since levels 0 and 2 are all odd and increasing and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.

Example 2:
Input: root = [5,4,2,3,3,7]
Output: false
Explanation: The node values on each level are:
Level 0: [5]
Level 1: [4,2]
Level 2: [3,3,7]
Node values in level 2 must be in strictly increasing order, so the tree is not Even-Odd.

Example 3:
Input: root = [5,9,1,3,5,7]
Output: false
Explanation: Node values in the level 1 should be even integers.

Constraints:
    The number of nodes in the tree is in the range [1, 10^5].
    1 <= Node.val <= 10^6
*/

/*
Approach: Level-order BFS with Per-level Parity and Order Validation
Goal:
- Verify that the binary tree satisfies the
  Even-Odd tree property:
  - Even-indexed levels (0, 2, 4...): all values
    must be odd integers in strictly increasing
    order left to right.
  - Odd-indexed levels (1, 3, 5...): all values
    must be even integers in strictly decreasing
    order left to right.
Core Idea:
- BFS processes nodes level by level, providing
  natural access to all nodes at a given depth
  before moving to the next.
- For each level, maintain a previous value
  sentinel initialized to Integer.MIN_VALUE for
  even levels (any valid odd value exceeds it) and
  Integer.MAX_VALUE for odd levels (any valid even
  value is less than it).
- For each node in the level, check both the parity
  constraint and the ordering constraint against
  the previous value.
Algorithm Steps:
1. Initialize a queue with the root and a boolean
   isEvenLevel = true.
2. While the queue is not empty:
   a. Record the current level size.
   b. Set previous = Integer.MIN_VALUE if
      isEvenLevel, else Integer.MAX_VALUE.
   c. For each node in the current level:
      - Enqueue non-null left and right children.
      - If isEvenLevel:
        - Fail if node.val is even (parity check).
        - Fail if node.val <= previous (order check).
      - If odd level:
        - Fail if node.val is odd (parity check).
        - Fail if node.val >= previous (order check).
      - Update previous = node.val.
   d. Flip isEvenLevel.
3. Return true if all levels pass.
Why It Works:
- Initializing previous to MIN_VALUE or MAX_VALUE
  ensures the first node in any level always passes
  the ordering check, since no valid integer
  violates the initial boundary.
- Checking parity via (node.val & 1) is a branchless
  O(1) operation: result 1 means odd, 0 means even.
- Flipping isEvenLevel after each level correctly
  alternates constraints without needing an explicit
  level counter.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
enqueued and dequeued exactly once.
Space Complexity:
- O(w)
where w is the maximum level width, for the BFS
queue. Worst case O(n) for a perfect binary tree's
last level.
Result:
- Returns true if the tree satisfies the Even-Odd
  property at every level, false otherwise.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find if the binary tree is Even-Odd
  public boolean isEvenOddTree(TreeNode root) {
    // Initialize the queue for the bfs
    Queue<TreeNode> queue = new LinkedList<>();

    // Add the root node
    queue.offer(root);

    // Initialize the boolean variable for the level
    boolean isEvenLevel = true;

    // Iterate over the queue untill it is not empty
    while (!queue.isEmpty()) {
      // Initialize the size variable
      int size = queue.size();

      // Get the previous node val
      int previous = isEvenLevel ? Integer.MIN_VALUE : Integer.MAX_VALUE;

      // Iterate over the queue
      for (int i = 0; i < size; i++) {
        // Initialize the node from the queue
        TreeNode node = queue.poll();

        // If the left node dose not contain null then add to the queue
        if (node.left != null) {
          queue.offer(node.left);
        }

        // If the right node dose not contain null then add to the queue
        if (node.right != null) {
          queue.offer(node.right);
        }

        // Check the level accordingly
        if (isEvenLevel) {
          if ((node.val & 1) == 0 || node.val <= previous) {
            return false;
          }
        } else {
          if ((node.val & 1) == 1 || node.val >= previous) {
            return false;
          }
        }

        // Update the previous node.val
        previous = node.val;
      }

      // Flip the isEvenLevel variable
      isEvenLevel = !isEvenLevel;
    }

    // Return true in the end
    return true;
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
public class _1609_Even_Odd_Tree {
  // Main method to test isEvenOddTree
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 10, 4, 3, null, 7, 9, 12, 8, 6, null, null, 2 });

    boolean result = new Solution().isEvenOddTree(root);

    System.out.println("The binary tree is" + (result ? " " : " not ") + "Even-Odd tree.");
  }
}
