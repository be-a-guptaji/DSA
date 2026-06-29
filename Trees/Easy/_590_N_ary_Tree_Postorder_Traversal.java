/*
LeetCode Problem: https://leetcode.com/problems/n-ary-tree-postorder-traversal/

Question: 590. N-ary Tree Postorder Traversal

Problem Statement: Given the root of an n-ary tree, return the postorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]

Constraints:
    The number of nodes in the tree is in the range [0, 10^4].
    0 <= Node.val <= 10^4
    The height of the n-ary tree is less than or equal to 1000.

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*
Approach: Recursive Depth-First Search (Postorder Traversal)

Goal:
- Return the postorder traversal of an N-ary tree.
- Postorder traversal visits nodes in the order:
      Children -> Root

Core Idea:
- Use recursion to traverse every child of a node
  before processing the node itself.
- Since each node may have multiple children,
  recursively visit each child in their given
  order.
- After all children have been completely
  traversed, add the current node's value to the
  result list.

Algorithm Steps:
1. If the current node is null, return.
2. Iterate through each child of the current node.
3. Recursively perform postorder traversal on each
   child.
4. After all children have been processed, add the
   current node's value to the result list.
5. Return the result list after the traversal is
   complete.

Why It Works:
- The recursive call ensures every subtree is
  fully traversed before returning to its parent.
- A node is added to the result only after all of
  its children have been processed, satisfying the
  postorder traversal order.
- Every node is visited exactly once.

Time Complexity:
- O(n)

Space Complexity:
- O(h)

where h is the height of the N-ary tree due to the
recursion stack. In the worst case, h = n.

Result:
- Returns a list containing the postorder
  traversal of the N-ary tree.
*/

package Trees.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the order array list for the result
  private final ArrayList<Integer> order = new ArrayList<>();

  // Method to find the postorder traversal of its nodes' values
  public List<Integer> postorder(Node root) {
    // Call the recursive function
    this.dfs(root);

    // Return order array list
    return this.order;
  }

  // Helper method to get the postorder
  private void dfs(Node root) {
    // If root is null then return
    if (root == null) {
      return;
    }

    // If root has children then call the recursive helper method
    if (!root.children.isEmpty()) {
      // Iterate over the root's children
      for (Node node : root.children) {
        // Call the recursive function
        this.dfs(node);
      }
    }

    // Add the value to the order list
    this.order.add(root.val);
  }
}

/*
 * // Definition for a Node.
 * class Node {
 * public int val;
 * public List<Node> children;
 * 
 * public Node() {}
 * 
 * public Node(int _val) {
 * val = _val;
 * }
 * 
 * public Node(int _val, List<Node> _children) {
 * val = _val;
 * children = _children;
 * }
 * }
 */

// Mock class for making the N-ary Tree
class Node {
  int val;
  List<Node> children;

  public Node() {
    children = new ArrayList<>();
  }

  public Node(int val) {
    this.val = val;
    this.children = new ArrayList<>();
  }

  public Node(int val, List<Node> children) {
    this.val = val;
    this.children = children;
  }

  // Helper method to make the N-ary tree from the LeetCode array representation
  public static Node makeTree(Integer[] arr) {
    if (arr == null || arr.length == 0 || arr[0] == null) {
      return null;
    }

    Node root = new Node(arr[0]);
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    int i = 2; // Skip root and the first null separator

    while (!queue.isEmpty() && i < arr.length) {
      Node parent = queue.poll();

      while (i < arr.length && arr[i] != null) {
        Node child = new Node(arr[i]);
        parent.children.add(child);
        queue.offer(child);
        i++;
      }

      // Skip the null separator
      i++;
    }

    return root;
  }
}

// Main Class
public class _590_N_ary_Tree_Postorder_Traversal {
  // Main method to test postorder
  public static void main(String[] args) {
    Node root = Node.makeTree(new Integer[] { 1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null,
        11, null, 12, null, 13, null, null, 14 });

    List<Integer> result = new Solution().postorder(root);

    System.out.println("The postorder traversal of its nodes' values is : " + result);
  }
}
