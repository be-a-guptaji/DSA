/*
LeetCode Problem: https://leetcode.com/problems/linked-list-in-binary-tree/

Question: 1367. Linked List in Binary Tree

Problem Statement: Given a binary tree root and a linked list with head as the first node. 

Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

In this context downward path means a path that starts at some node and goes downwards.

Example 1:
Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
Explanation: Nodes in blue form a subpath in the binary Tree.  

Example 2:
Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true

Example 3:
Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: false
Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.

Constraints:
    The number of nodes in the tree will be in the range [1, 2500].
    The number of nodes in the list will be in the range [1, 100].
    1 <= Node.val <= 100 for each node in the linked list and binary tree.
*/

/*
Approach: DFS Root-anchored Matching over All Tree Nodes
Goal:
- Determine if the linked list sequence appears as a
  contiguous top-to-bottom path anywhere in the tree.
Core Idea:
- Try matching the linked list starting at every
  tree node (via outer DFS over the whole tree).
- At each candidate start node, use an inner DFS
  (dfs helper) that walks the list and tree in
  lockstep, following either the left or right child,
  requiring exact value matches at every step.
- Short-circuit further search once a match is found
  using the isFound flag.
Algorithm Steps:
1. In isSubPath, if root is null, no match is
   possible from here, return false.
2. Try dfs(head, root): does the list match starting
   exactly at this node.
3. If not, recurse isSubPath on root.left and
   root.right to try other starting points.
4. In dfs, if head is null, the entire list has been
   matched, return true.
5. If root is null (tree ran out) or values mismatch,
   return false.
6. Otherwise, advance the list pointer and try both
   root.left and root.right.
Time Complexity:
- O(n * m)
where n is the number of tree nodes and m is the
list length, since dfs may be attempted from every
node.
Space Complexity:
- O(h)
where h is the tree height, for the recursive call
stack.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the variable to check if the linkedlist is found
  private boolean isFound;

  // Method to determine if linkedlist is in the binary tree or not
  public boolean isSubPath(ListNode head, TreeNode root) {
    // If we found the linkedlist then return true
    if (this.isFound) {
      return true;
    }

    // Initialize the variable to check if the linkedlist is found
    this.isFound = false;

    // If root is null then return false
    if (root == null) {
      return false;
    }

    // Call the recursive fuction on left and right child
    return this.dfs(head, root) || this.isSubPath(head, root.left) || this.isSubPath(head, root.right);
  }

  // Helper method to find if the value is equal to the root. value
  private boolean dfs(ListNode head, TreeNode root) {
    // If we found the linkedlist then return true
    if (this.isFound) {
      return true;
    }

    // If head is null then return true
    if (head == null) {
      // Set the isFount to true
      this.isFound = true;
      return true;
    }

    // If root is null then return false
    if (root == null) {
      return false;
    }

    // If the values does not match then return false
    if (head.val != root.val) {
      return false;
    }

    // Call the recursive fuction on left and right child
    return this.dfs(head.next, root.left) || this.dfs(head.next, root.right);
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

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// Mock class for makeing the ListNode Class
class ListNode {
  public int val;
  public ListNode next;

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  // Helper method to make the linked list from the array
  public static ListNode makelist(int[] list) {
    if (list.length == 0) {
      return null; // Handle empty array
    }

    ListNode head = new ListNode(list[0]); // First element as head
    ListNode current = head;

    for (int i = 1; i < list.length; i++) {
      current.next = new ListNode(list[i]);
      current = current.next;
    }

    return head;
  }
}

// Main Class
public class _1367_Linked_List_in_Binary_Tree {
  // Main method to test isSubPath
  public static void main(String[] args) {
    ListNode head = ListNode.makelist(new int[] { 4, 2, 8 });
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null,
        1, 3 });

    boolean result = new Solution().isSubPath(head, root);

    System.out.println("Linkedlist is" + (result ? "" : " not ") + "in the binary tree.");
  }
}
