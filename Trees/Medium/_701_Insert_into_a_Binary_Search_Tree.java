/*
LeetCode Problem: https://leetcode.com/problems/insert-into-a-binary-search-tree/

Question: 701. Insert into a Binary Search Tree

Problem Statement: You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

Example 1:
Input: root = [4,2,7,1,3], val = 5
Output: [4,2,7,1,3,5]
Explanation: Another accepted tree is:

Example 2:
Input: root = [40,20,60,10,30,50,70], val = 25
Output: [40,20,60,10,30,50,70,null,null,25]

Example 3:
Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
Output: [4,2,7,1,3,5]

Constraints:
    The number of nodes in the tree will be in the range [0, 10^4].
    -10^8 <= Node.val <= 10^8
    All the values Node.val are unique.
    -10^8 <= val <= 10^8
    It's guaranteed that val does not exist in the original BST.
*/

/*
Approach: Iterative BST Insertion

Goal:
- Insert a new value into a Binary Search Tree
  while preserving the BST property.
- Return the root of the updated tree.

Core Idea:
- In a BST:
   - Values smaller than the current node go to
     the left subtree.
   - Values greater than the current node go to
     the right subtree.
- Traverse the tree until the correct null child
  position is found.
- Insert the new node at that position.

Algorithm Steps:
1. If the root is null, return a new node with
   the given value.
2. Start traversal from the root.
3. Compare val with the current node value.
4. If val is smaller:
   - Move to the left child if it exists.
   - Otherwise insert the new node as the left
     child and stop.
5. If val is greater:
   - Move to the right child if it exists.
   - Otherwise insert the new node as the right
     child and stop.
6. Return the original root.

Why It Works:
- Each comparison determines which subtree can
  legally contain the new value.
- The traversal follows the same ordering rule
  used by the BST structure.
- Inserting at the first valid null position keeps
  all nodes in the left subtree smaller and all
  nodes in the right subtree greater.

Time Complexity:
- O(h)

where h is the height of the tree.

Space Complexity:
- O(1)

Result:
- Returns the root of the BST after inserting the
  new value.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the root node of the BST after the insertion
  public TreeNode insertIntoBST(TreeNode root, int val) {
    // If root is null then return the new node of val
    if (root == null) {
      return new TreeNode(val);
    }

    // Initialize the dummy node
    TreeNode dummy = root;

    // Iterate over the dummy node
    while (true) {
      // Update the dummy value according to the val
      if (dummy.val > val) {
        // If dummy.left is null add the tree node and break out of the loop
        if (dummy.left == null) {
          dummy.left = new TreeNode(val);
          break;
        } else {
          dummy = dummy.left;
        }
      } else {
        // If dummy.right is null add the tree node and break out of the loop
        if (dummy.right == null) {
          dummy.right = new TreeNode(val);
          break;
        } else {
          dummy = dummy.right;
        }
      }
    }

    // Return the dummy node
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
public class _701_Insert_into_a_Binary_Search_Tree {
  // Main method to test insertIntoBST
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 40, 20, 60, 10, 30, 50, 70 });
    int val = 25;

    TreeNode result = new Solution().insertIntoBST(root, val);

    System.out.println("The root node of the BST after the insertion is : " + result);
  }
}
