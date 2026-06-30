/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-postorder-traversal/

Question: 108. Convert Sorted Array to Binary Search Tree

Problem Statement: Given an integer array nums where the elements are sorted in ascending order, convert it to a binary search tree.

Example 1:
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.

Constraints:
    1 <= nums.length <= 10^4
    -10^4 <= nums[i] <= 10^4
    nums is sorted in a strictly increasing order.
*/

/*
Approach: Divide and Conquer

Goal:
- Convert a sorted array into a height-balanced
  Binary Search Tree (BST).

Core Idea:
- Since the array is sorted, the middle element
  naturally becomes the root of the current
  subtree.
- Elements to the left of the middle form the left
  subtree.
- Elements to the right of the middle form the
  right subtree.
- Recursively apply the same process to both
  halves until no elements remain.

Algorithm Steps:
1. Start with the entire array.
2. If the current range is empty, return null.
3. Find the middle index of the current range.
4. Create a tree node using the middle element.
5. Recursively construct the left subtree using
   the left half of the array.
6. Recursively construct the right subtree using
   the right half of the array.
7. Return the constructed node as the root of the
   current subtree.

Why It Works:
- Choosing the middle element ensures that the
  left and right subtrees differ in height by at
  most one.
- The left subtree contains only elements smaller
  than the root, while the right subtree contains
  only elements greater than the root, preserving
  the Binary Search Tree property.
- Every element is used exactly once to create one
  tree node.

Time Complexity:
- O(n)

Space Complexity:
- O(log n)

due to the recursive call stack for a balanced
tree.

Result:
- Returns the root of a height-balanced Binary
  Search Tree constructed from the sorted array.
*/

package Trees.Easy;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the height balanced binary tree
  public TreeNode sortedArrayToBST(int[] nums) {
    // Return the recursive call of the helper
    return this.helper(0, nums.length - 1, nums);
  }

  // Helper method to make the binary tree
  private TreeNode helper(int left, int right, int[] nums) {
    // If left is greater than right then return null
    if (left > right) {
      return null;
    }

    // Initialize the mid variable
    int mid = left + right >> 1;

    // Return the recursive call of the helper
    return new TreeNode(nums[mid], this.helper(left, mid - 1, nums), this.helper(mid + 1, right, nums));
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
public class _108_Convert_Sorted_Array_to_Binary_Search_Tree {
  // Main method to test sortedArrayToBST
  public static void main(String[] args) {
    int[] nums = new int[] { -10, -3, 0, 5, 9 };

    TreeNode result = new Solution().sortedArrayToBST(nums);

    System.out.println("The height balanced binary tree is : " + result);
  }
}
