/*
LeetCode Problem: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

Question: 116. Populating Next Right Pointers in Each Node

Problem Statement: You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example 1:
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:
Input: root = []
Output: []

Constraints:
    The number of nodes in the tree is in the range [0, 2^12 - 1].
    -1000 <= Node.val <= 1000

Follow-up:
    You may only use constant extra space.
    The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
*/

/*
/*
Approach: Level-by-Level Traversal Using Established Next Pointers

Goal:
- Populate each next pointer so that it points to
  its next right node.
- If there is no next right node, the next pointer
  should remain null.

Core Idea:
- Since the tree is a perfect binary tree, every
  internal node has both left and right children.
- Use the next pointers established at the current
  level to traverse horizontally without using a
  queue.
- While traversing a level:
  - Connect each node's left child to its right
    child.
  - Connect each node's right child to the left
    child of its adjacent node, if one exists.

Algorithm Steps:
1. If the root is null, return null.
2. Initialize the current node as the root.
3. Initialize the first node of the next level as
   the root's left child.
4. While another level exists:
   - Connect the left child to the right child.
   - If the current node has a next neighbor,
     connect the right child to the neighbor's
     left child.
   - Move horizontally using the current node's
     next pointer.
   - After finishing the current level, move to
     the first node of the next level.
5. Repeat until all levels are processed.
6. Return the root.

Why It Works:
- The perfect binary tree property guarantees that
  each internal node has both left and right
  children.
- The next pointers created for one level allow
  traversal of that level without additional data
  structures.
- Every child connection is established exactly
  once, producing the required horizontal links.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the root of the perfect binary tree with
  every next pointer connected to its next right
  node.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to populate each next pointer to point to its next right node
  public Node connect(Node root) {
    // If root is null then return root
    if (root == null) {
      return root;
    }

    // Initialize the current and next pointer
    Node current = root;
    Node next = root.left;

    // Iterate untill the next pointer is not null
    while (next != null) {
      // Connet left node to the right node
      current.left.next = current.right;

      // If current.next is not null then connect the right node to the left node of
      // current.next
      if (current.next != null) {
        current.right.next = current.next.left;
      }

      // Update the current to current.next
      current = current.next;

      // If current is null then go to the next level
      if (current == null) {
        current = next;
        next = current.left;
      }
    }

    // Return the root node
    return root;
  }
}

/*
 * // Definition for a Node.
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node next;
 * 
 * public Node() {}
 * 
 * public Node(int _val) {
 * val = _val;
 * }
 * 
 * public Node(int _val, Node _left, Node _right, Node _next) {
 * val = _val;
 * left = _left;
 * right = _right;
 * next = _next;
 * }
 * };
 */

// Mock class for makeing the Node Class
class Node {
  public int val;
  public Node left;
  public Node right;
  public Node next;

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, Node _left, Node _right, Node _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }

  // Helper method to make the binary tree from the array
  public static Node makeTree(Integer[] arr) {
    if (arr == null || arr.length == 0 || arr[0] == null) {
      return null;
    }

    Node root = new Node(arr[0]);
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    int i = 1;

    while (!queue.isEmpty() && i < arr.length) {
      Node current = queue.poll();

      // Left child
      if (i < arr.length && arr[i] != null) {
        current.left = new Node(arr[i]);
        queue.offer(current.left);
      }
      i++;

      // Right child
      if (i < arr.length && arr[i] != null) {
        current.right = new Node(arr[i]);
        queue.offer(current.right);
      }
      i++;
    }

    return root;
  }
}

// Main Class
public class _116_Populating_Next_Right_Pointers_in_Each_Node {
  // Main method to test createBinaryTree
  public static void main(String[] args) {
    Node root = Node.makeTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });

    Node result = new Solution().connect(root);

    System.out
        .println("The populated next pointer to point to its next right node is : " + result);
  }
}
