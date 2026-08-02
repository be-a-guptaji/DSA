/*
LeetCode Problem: https://leetcode.com/problems/delete-leaves-with-a-given-value/

Question: 1325. Delete Leaves With a Given Value

Problem Statement: Given a binary tree root and an integer target, delete all the leaf nodes with value target.

Note that once you delete a leaf node with value target, if its parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you cannot).

Example 1:
Input: root = [1,2,3,2,null,2,4], target = 2
Output: [1,null,3,null,4]
Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left). 
After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).

Example 2:
Input: root = [1,3,3,3,2], target = 3
Output: [1,3,null,null,2]

Example 3:
Input: root = [1,2,null,2,null,2], target = 2
Output: [1]
Explanation: Leaf nodes in green with value (target = 2) are removed at each step.

Constraints:
    The number of nodes in the tree is in the range [1, 3000].
    1 <= Node.val, target <= 1000
*/

/*
Approach: Post-order DFS with Leaf Deletion and Re-evaluation
Goal:
- Remove all leaf nodes with value equal to target,
  then repeatedly remove newly created leaf nodes
  with the same value until no such leaves remain.
Core Idea:
- A node can only become a leaf after its children
  are removed; this dependency requires bottom-up
  (post-order) processing.
- Process children before the parent so that by
  the time a node is evaluated, its subtree is
  already fully trimmed and its leaf status is
  final.
Algorithm Steps:
1. If node is null, return null (base case).
2. Recurse on left subtree:
   - root.left = removeLeafNodes(root.left, target)
3. Recurse on right subtree:
   - root.right = removeLeafNodes(root.right, target)
4. After both children are processed, check if the
   current node has become a leaf with the target
   value:
   - If root.left == null && root.right == null
     && root.val == target, return null (delete
     this node).
5. Otherwise return root unchanged.
Why It Works:
- Post-order guarantees children are trimmed before
  the parent is evaluated, so a parent whose
  children were just deleted is correctly identified
  as a new leaf in the same pass.
- Returning null from a recursive call
  automatically detaches the deleted node from its
  parent by assignment (root.left or root.right
  becomes null).
- No repeated passes are needed; the single
  post-order traversal handles cascading deletions
  naturally bottom-up.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
visited exactly once.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack. Worst case O(n) for a skewed tree.
Result:
- Returns the root of the modified tree with all
  target-valued leaf nodes removed, including those
  that became leaves after prior deletions.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the tree after deleting all the leaf nodes with value target
  public TreeNode removeLeafNodes(TreeNode root, int target) {
    // If root is null then return null
    if (root == null) {
      return null;
    }

    // Update the left and right child of the root
    root.left = this.removeLeafNodes(root.left, target);
    root.right = this.removeLeafNodes(root.right, target);

    // If root node is leaf node and value is equal to target then return null
    if (root.left == null && root.right == null && root.val == target) {
      return null;
    }

    // Retrun the root
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
public class _1325_Delete_Leaves_With_a_Given_Value {
  // Main method to test removeLeafNodes
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 2, null, 2, 4 });
    int target = 2;

    TreeNode result = new Solution().removeLeafNodes(root, target);

    System.out.println("The tree after deleting all the leaf nodes with value " + target + " is : " + result);
  }
}
