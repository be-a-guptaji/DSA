/*
LeetCode Problem: https://leetcode.com/problems/distribute-coins-in-binary-tree/

Question: 979. Distribute Coins in Binary Tree

Problem Statement: You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.

In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.

Return the minimum number of moves required to make every node have exactly one coin.

Example 1:
Input: root = [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

Example 2:
Input: root = [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move one coin from the root of the tree to the right child.

Constraints:
    The number of nodes in the tree is n.
    1 <= n <= 100
    0 <= Node.val <= n
    The sum of all Node.val is n.
*/

/*
Approach: Post-order DFS with Excess Coin Flow Accumulation
Goal:
- Find the minimum number of moves to distribute
  coins so every node has exactly one coin, where
  one move transfers one coin across one edge.
Core Idea:
- Each node computes its net excess: coins it holds
  minus the one it needs, plus any excess flowing
  up from its subtree.
- A positive excess means the subtree has surplus
  coins that must flow upward through this node's
  edge to the parent; a negative excess means the
  subtree is deficient and coins must flow downward
  through the same edge.
- In both cases, the number of moves across the
  edge between a node and its parent equals the
  absolute value of that node's excess, since every
  surplus or deficit coin must cross that edge
  exactly once.
Algorithm Steps:
1. Call dfs(root) to begin post-order traversal.
2. In dfs(node):
   a. If node is null, return 0 (no excess from
      empty subtree).
   b. Recursively compute:
      - leftExtraCoin = dfs(node.left)
      - rightExtraCoin = dfs(node.right)
   c. Compute totalExtraCoin = node.val - 1 +
      leftExtraCoin + rightExtraCoin:
      - node.val - 1 is the excess at this node
        after keeping one coin for itself.
      - Adding left and right excess aggregates the
        entire subtree's net surplus or deficit.
   d. Add Math.abs(totalExtraCoin) to result,
      accounting for the moves across the edge
      between this node and its parent.
   e. Return totalExtraCoin to propagate the net
      flow upward.
3. Return result.
Why It Works:
- Post-order ensures the full subtree excess is
  known before the parent aggregates it.
- The absolute value of excess at each node
  directly equals the number of coins crossing
  that node's parent edge, since each coin must
  travel one edge at a time and this is the
  bottleneck edge for all coins moving between
  the subtree and the rest of the tree.
- Summing absolute excesses over all edges gives
  the total minimum moves, as each edge's
  contribution is independent and optimal by
  definition (no coin crosses the same edge more
  than once in an optimal solution).
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
visited exactly once.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack. Worst case O(n) for a skewed tree.
Result:
- Returns the minimum number of moves to give
  every node exactly one coin.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the result variable
  private int result = 0;

  // Method to find the minimum number of moves required to make every node have
  // exactly one coin
  public int distributeCoins(TreeNode root) {
    // Call the recursive dfs method
    this.dfs(root);

    // Return the result variable
    return this.result;
  }

  // Helper method to preform the dfs
  private int dfs(TreeNode root) {
    // If root is null then return zero
    if (root == null) {
      return 0;
    }

    // Get the left and right extra coin
    int leftExtraCoin = this.dfs(root.left);
    int rightExtraCoin = this.dfs(root.right);

    // Get the total extra coin
    int totalExtraCoin = root.val - 1 + leftExtraCoin + rightExtraCoin;

    // Update the result varaible
    this.result += Math.abs(totalExtraCoin);

    // Return the totalExtraCoin
    return totalExtraCoin;
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
public class _979_Distribute_Coins_in_Binary_Tree {
  // Main method to test distributeCoins
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 0, 3, 0 });

    int result = new Solution().distributeCoins(root);

    System.out.println("The minimum number of moves required to make every node have exactly one coin is : " + result);
  }
}
