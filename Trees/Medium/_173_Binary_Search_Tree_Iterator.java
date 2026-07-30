/*
LeetCode Problem: https://leetcode.com/problems/binary-search-tree-iterator/

Question: 173. Binary Search Tree Iterator

Problem Statement: Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.

Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.

Example 1:
Input: root = [1,0,2], low = 1, high = 2
Output: [1,null,2]

Example 2:
Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
Output: [3,2,null,1]

Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    0 <= Node.val <= 10^4
    The value of each node in the tree is unique.
    root is guaranteed to be a valid binary search tree.
    0 <= low <= high <= 10^4
*/

/*
Approach: Eager In-order Traversal into Static Array
Goal:
- Implement an iterator over a BST that returns
  node values in ascending order via next(), and
  reports whether more values remain via hasNext().
Core Idea:
- BST in-order traversal (left, root, right)
  produces node values in strictly ascending order.
- Precompute the entire traversal at construction
  time into a static array, reducing next() and
  hasNext() to O(1) index operations.
- A write index (index) tracks where the next value
  is stored during traversal; a read index
  (iterator) tracks the next value to return.
Algorithm Steps:
1. In the constructor:
   a. Initialize index = 0 (write cursor) and
      iterator = 0 (read cursor).
   b. Call inOrder(root) to populate nodeValues[].
2. In inOrder(node):
   a. If node is null, return.
   b. Recurse inOrder(node.left).
   c. Store node.val at nodeValues[index++].
   d. Recurse inOrder(node.right).
3. next():
   a. Return nodeValues[iterator++], advancing the
      read cursor.
4. hasNext():
   a. Return iterator != index (read cursor has not
      reached the write cursor).
Why It Works:
- In-order traversal of a BST visits nodes in
  ascending value order by definition.
- Storing results eagerly at construction time
  decouples traversal logic from iteration, making
  both next() and hasNext() constant time.
- The gap between iterator and index always
  represents the number of values not yet consumed.
Time Complexity:
- Constructor: O(n) for full in-order traversal.
- next(): O(1) per call.
- hasNext(): O(1) per call.
Space Complexity:
- O(n) for the nodeValues array storing all node
  values, plus O(h) for the recursive call stack
  during construction, where h is the tree height.
Result:
- Supports O(1) next() and hasNext() operations
  after O(n) construction, at the cost of O(n)
  upfront space.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

// BSTIterator Class
class BSTIterator {
  // Initialize the nodeValue array
  private static final int[] nodeValues = new int[10000];

  // Initialize the index variable
  private int index;

  // Initialize the iterator variable
  private int iterator;

  public BSTIterator(TreeNode root) {
    // Initialize the index variable
    this.index = 0;

    // Initialize the iterator variable
    this.iterator = 0;

    // Find the inorder of the tree
    this.inOrder(root);
  }

  public int next() {
    return nodeValues[this.iterator++];
  }

  public boolean hasNext() {
    return iterator != index;
  }

  // Helper method to find the inorder of a tree
  private void inOrder(TreeNode root) {
    // If root is null then return
    if (root == null) {
      return;
    }

    // Iterate over the left subtree
    this.inOrder(root.left);

    // Add the value to the nodeValues array
    nodeValues[this.index++] = root.val;

    // Iterate over the right subtree
    this.inOrder(root.right);
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
public class _173_Binary_Search_Tree_Iterator {
  // Main method to test BSTIterator
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 7, 3, 15, null, null, 9, 20 });
    String[] operations = new String[] { "BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext",
        "next", "hasNext" };

    BSTIterator result = new BSTIterator(root);

    for (String operation : operations) {
      switch (operation) {
        case "BSTIterator" -> {
          result = new BSTIterator(root);
          System.out.println("null");
        }
        case "next" -> {
          System.out.println(result.next());
        }
        case "hasNext" -> {
          System.out.println(result.hasNext());
        }
      }
    }
  }
}
