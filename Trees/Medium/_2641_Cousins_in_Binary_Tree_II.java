/*
LeetCode Problem: https://leetcode.com/problems/cousins-in-binary-tree-ii/

Question: 2641. Cousins in Binary Tree II

Problem Statement: Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Return the root of the modified tree.

Note that the depth of a node is the number of edges in the path from the root node to it.

Example 1:
Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.

Example 2:
Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.

Constraints:
    The number of nodes in the tree is in the range [1, 10^5].
    1 <= Node.val <= 10^4
*/

/*
Approach: Two-pass BFS with Level Sums and Sibling-Sum Staging
Goal:
- Replace every node's value with the sum of the
  values of its cousins (nodes at the same level,
  excluding itself and its siblings).
- The root's cousin sum is 0 by definition.
- Return the modified tree's root.
Core Idea:
- cousinSum(node) = levelSum(node's level)
                     - siblingSum(node's parent)
  where siblingSum(parent) = sum of parent's direct
  children's values (i.e., node's own value plus its
  siblings' values).
- This requires knowing two things for every node:
   1. The total sum of its own level (computed via a
      first BFS pass, independent of the second).
   2. The combined value of its parent's children
      (computed while visiting the parent, one level
      above).
- Data from step 2 must be produced one level before
  it's consumed, so it needs to be staged somewhere
  accessible when processing the child's level.
- The trick: temporarily store siblingSum(parent)
  directly into each child's val field while
  visiting the parent. When that child is later
  visited (next level iteration), its val field
  still holds siblingSum(parent), which is exactly
  the value needed to subtract from levelSum.
Algorithm Steps:
1. First BFS pass:
   a. Traverse level by level.
   b. For each level, sum all node values using the
      original, unmodified tree.
   c. Store each level's sum in levelOrderSum,
      indexed by level number.
2. Second BFS pass:
   a. Re-enqueue the root; initialize level = 0.
   b. For each level:
      - Retrieve sum = levelOrderSum.get(level),
        the true original sum for this level.
      - For each node in the level:
         i.   Overwrite node.val with
              (sum - node.val). At this point,
              node.val still holds whatever was
              staged in it: either its own original
              value (if level == 0), or
              siblingSum(parent) staged during the
              previous level's iteration. Either way,
              sum - node.val correctly yields
              cousinSum(node).
         ii.  Compute childrenSum by adding the
              *original, not-yet-modified* values of
              node.left and node.right (they belong
              to the next level and haven't been
              touched yet).
         iii. Enqueue any non-null children.
         iv.  Stage childrenSum into left.val and
              right.val, overwriting their original
              values. This value will be consumed
              when the children's level is processed
              in the next outer iteration.
      - Increment level.
3. Return root.
Why It Works:
- levelOrderSum is captured before any mutation, so
  it always reflects true original per-level totals,
  regardless of how deep the second pass has
  overwritten node values.
- For the root (level 0), node.val at processing time
  is still its original value, and sum equals that
  same value, so root.val becomes 0, matching the
  required base case (root has no cousins).
- For any non-root node, by the time its level is
  reached in pass two, its val field was overwritten
  by its parent's iteration to hold siblingSum
  (parent), not its own original value. Subtracting
  this from the level's true sum isolates exactly the
  contribution of all other subtrees at that level,
  i.e., the cousins.
- Because childrenSum is always computed from
  not-yet-visited nodes (one level ahead of the
  current overwrite), reading left.val/right.val
  before staging never reads corrupted data.
Time Complexity:
- O(n)
where n is the number of nodes. Each node is visited
a constant number of times across both BFS passes.
Space Complexity:
- O(w)
where w is the maximum level width, for the BFS
queue and the levelOrderSum list (O(h) entries,
h = tree height, each O(1) space).
Result:
- Returns the root of the tree with every node's
  value replaced by its cousin sum.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the root of the modified tree
  public TreeNode replaceValueInTree(TreeNode root) {
    // Initialize the queue for the BFS
    Queue<TreeNode> queue = new LinkedList<>();

    // Initialize the arraylist for the level order sum
    ArrayList<Long> levelOrderSum = new ArrayList<>();

    // Add the root to the queue
    queue.offer(root);

    // Iterate over the queue untill it is not over
    while (!queue.isEmpty()) {
      // Initialize the size of the level
      int size = queue.size();

      // Initialize the sum variable
      long sum = 0L;

      // Iterate over the level of the tree
      for (int i = 0; i < size; i++) {
        // Get the node from the queue
        TreeNode node = queue.poll();

        // Get the left and right child of the node
        TreeNode left = node.left;
        TreeNode right = node.right;

        // Add the value to the sum
        sum += node.val;

        // If left is not null then add the value to the queue
        if (left != null) {
          queue.offer(left);
        }

        // If right is not null then add the value to the queue
        if (right != null) {
          queue.offer(right);
        }
      }

      // Add the sum to the levelOrderSum
      levelOrderSum.add(sum);
    }

    // Add the root to the queue
    queue.offer(root);

    // Initialize the level variable
    int level = 0;

    // Iterate over the queue untill it is not over
    while (!queue.isEmpty()) {
      // Initialize the size of the level
      int size = queue.size();

      // Get the level order sum
      long sum = levelOrderSum.get(level);

      // Iterate over the level of the tree
      for (int i = 0; i < size; i++) {
        // Get the node from the queue
        TreeNode node = queue.poll();

        // Update the node variable
        node.val = (int) (sum - node.val);

        // Get the left and right child of the node
        TreeNode left = node.left;
        TreeNode right = node.right;

        // Initialize the children sum
        int childrenSum = 0;

        // If left is not null then add the value to the queue
        if (left != null) {
          queue.offer(left);
          childrenSum += left.val;
        }

        // If right is not null then add the value to the queue
        if (right != null) {
          queue.offer(right);
          childrenSum += right.val;
        }

        // If left is not null update the left value
        if (left != null) {
          left.val = childrenSum;
        }

        // If right is not null update the right value
        if (right != null) {
          right.val = childrenSum;
        }
      }

      // Increment the level
      level++;
    }

    // Return the root
    return root;
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
public class _2641_Cousins_in_Binary_Tree_II {
  // Main method to test replaceValueInTree
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 5, 4, 9, 1, 10, null, 7 });

    TreeNode result = new Solution().replaceValueInTree(root);

    System.out.println("The root of the modified tree is : " + result);
  }
}
