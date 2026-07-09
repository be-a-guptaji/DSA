/*
LeetCode Problem: https://leetcode.com/problems/delete-node-in-a-bst/

Question: 450. Delete Node in a BST

Problem Statement: Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

    Search for a node to remove.
    If the node is found, delete the node.

Example 1:
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

Example 2:
Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.

Example 3:
Input: root = [], key = 0
Output: []

Constraints:
    The number of nodes in the tree is in the range [0, 10^4].
    -10^5 <= Node.val <= 10^5
    Each node has a unique value.
    root is a valid binary search tree.
    -10^5 <= key <= 10^5

Follow up: Could you solve it with time complexity O(height of tree)?
*/

/*
Approach: Iterative BST Search + In-Order Successor Deletion

Goal:
- Find the node containing the given key using
  the Binary Search Tree ordering property.
- Delete the node while correctly handling all
  possible child configurations.
- Return the root of the updated BST.

Core Idea:
- Use the BST property to locate the target node
  and its parent iteratively.
- BST deletion has three structural cases:

   1. Leaf Node:
      - The target has no children.
      - Remove it by setting the corresponding
        parent child pointer to null.

   2. One Child:
      - The target has exactly one child.
      - Connect the target's parent directly to
        the target's child.

   3. Two Children:
      - Find the in-order successor, which is the
        smallest node in the right subtree.
      - Copy the successor's value into the target.
      - Delete the successor instead.
      - The successor has at most one child because
        it cannot have a left child.

- After handling the two-child case, the node that
  must be physically removed has at most one child.
- Leaf-node and one-child deletion can therefore be
  handled using the same parent-pointer update.

Algorithm Steps:
1. Initialize parent as null and current as root.

2. Search for the node containing key:
   - If key is smaller than current.val, move to
     the left child.
   - If key is greater than current.val, move to
     the right child.
   - Before moving, store current as parent.

3. If current becomes null:
   - The key does not exist in the BST.
   - Return the original root.

4. If current has both left and right children:
   - Store current as the node whose value will
     be replaced.
   - Move to the right child.
   - Continue moving left to find the in-order
     successor.
   - Track the successor's parent during traversal.
   - Copy the successor's value into the original
     target node.
   - Set current to the successor so it can be
     physically removed.

5. Determine the surviving child:
   - If current.left is not null, use current.left.
   - Otherwise use current.right.
   - The child may also be null when current is a
     leaf node.

6. Remove current from the tree:
   - If parent is null, current is the root, so
     update root to child.
   - If parent.left equals current, set parent.left
     to child.
   - Otherwise set parent.right to child.

7. Return the updated root.

Why It Works:
- The search follows the BST ordering invariant, so
  each comparison eliminates one entire subtree.
- A node with zero or one child can be removed by
  directly connecting its parent to its surviving
  child.
- For a node with two children, the in-order
  successor is the smallest value greater than the
  target value.
- Replacing the target value with the successor
  value preserves the BST ordering.
- The successor has no left child, so its physical
  deletion reduces to the zero-or-one-child case.

Time Complexity:
- O(h)

where h is the height of the BST.

Space Complexity:
- O(1)

Result:
- Returns the root of the BST after deleting the
  node containing the given key.
- Returns the original root when the key is not
  present.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the root node reference (possibly updated) of the BST
  public TreeNode deleteNode(TreeNode root, int key) {
    // If root is null then return null
    if (root == null) {
      return null;
    }

    // Initialize the parent node
    TreeNode parent = null;

    // Initialize the current node with root
    TreeNode current = root;

    // Iterate till current is not null and key is not found
    while (current != null && current.val != key) {
      // Update the parent to current
      parent = current;

      // If key is smaller than current value then move to the left
      if (key < current.val) {
        current = current.left;
      }
      // Otherwise move to the right
      else {
        current = current.right;
      }
    }

    // If current is null then key is not present in the BST
    if (current == null) {
      return root;
    }

    // If current has both left and right children
    if (current.left != null && current.right != null) {
      // Initialize the successor parent with current
      TreeNode successorParent = current;

      // Initialize the successor with the right child
      TreeNode successor = current.right;

      // Iterate till the leftmost node is found
      while (successor.left != null) {
        // Update the successor parent
        successorParent = successor;

        // Move successor to the left
        successor = successor.left;
      }

      // Copy the successor value into the current node
      current.val = successor.val;

      // Update the parent to successor parent
      parent = successorParent;

      // Update the current to successor
      current = successor;
    }

    // Initialize the child of the current node
    TreeNode child;

    // If the left child is not null then use the left child
    if (current.left != null) {
      child = current.left;
    }
    // Otherwise use the right child
    else {
      child = current.right;
    }

    // If parent is null then the root node needs to be deleted
    if (parent == null) {
      return child;
    }

    // If current is the left child of the parent then update the left child
    if (parent.left == current) {
      parent.left = child;
    }
    // Otherwise update the right child
    else {
      parent.right = child;
    }

    // Return the updated root in the end
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
public class _450_Delete_Node_in_a_BST{
  // Main method to test deleteNode
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 5, 3, 6, 2, 4, null, 7 });
    int key = 3;

    TreeNode result = new Solution().deleteNode(root, key);

    System.out.println("The root node reference (possibly updated) of the BST is : " + result);
  }
}
