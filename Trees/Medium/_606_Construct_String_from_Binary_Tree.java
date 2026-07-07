/*
LeetCode Problem: https://leetcode.com/problems/construct-string-from-binary-tree/

Question: 606. Construct String from Binary Tree

Problem Statement: Given the root node of a binary tree, your task is to create a string representation of the tree following a specific set of formatting rules. The representation should be based on a preorder traversal of the binary tree and must adhere to the following guidelines:

    Node Representation: Each node in the tree should be represented by its integer value.

    Parentheses for Children: If a node has at least one child (either left or right), its children should be represented inside parentheses. Specifically:
        If a node has a left child, the value of the left child should be enclosed in parentheses immediately following the node's value.
        If a node has a right child, the value of the right child should also be enclosed in parentheses. The parentheses for the right child should follow those of the left child.

    Omitting Empty Parentheses: Any empty parentheses pairs (i.e., ()) should be omitted from the final string representation of the tree, with one specific exception: when a node has a right child but no left child. In such cases, you must include an empty pair of parentheses to indicate the absence of the left child. This ensures that the one-to-one mapping between the string representation and the original binary tree structure is maintained.

    In summary, empty parentheses pairs should be omitted when a node has only a left child or no children. However, when a node has a right child but no left child, an empty pair of parentheses must precede the representation of the right child to reflect the tree's structure accurately.

Example 1:
Input: root = [1,2,3,4]
Output: "1(2(4))(3)"
Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the empty parenthesis pairs. And it will be "1(2(4))(3)".

Example 2:
Input: root = [1,2,3,null,4]
Output: "1(2()(4))(3)"
Explanation: Almost the same as the first example, except the () after 2 is necessary to indicate the absence of a left child for 2 and the presence of a right child.

Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -1000 <= Node.val <= 1000
*/

/*
Approach: Recursive Depth-First Search with Preorder Traversal

Goal:
- Construct the string representation of a binary
  tree using preorder traversal.
- Omit unnecessary empty parentheses while
  preserving the one-to-one mapping between the
  string and the original tree.

Core Idea:
- Traverse the tree in preorder:
      Root -> Left -> Right
- Append each node's value to the string.
- Enclose each existing child subtree within
  parentheses.
- If a node has a right child but no left child,
  append an empty pair of parentheses "()" for the
  missing left child to preserve the tree
  structure.

Algorithm Steps:
1. If the current node is null, return.
2. Append the current node's value to the string.
3. If the left child exists:
   - Append '('.
   - Recursively process the left subtree.
   - Append ')'.
4. If the right child exists:
   - If the left child is missing, append "()".
   - Append '('.
   - Recursively process the right subtree.
   - Append ')'.
5. Continue until all nodes have been processed.
6. Return the constructed string.

Why It Works:
- Preorder traversal ensures every parent node is
  processed before its children.
- Parentheses uniquely identify the boundaries of
  each subtree.
- The empty parentheses for a missing left child
  are included only when a right child exists,
  preventing ambiguity while avoiding unnecessary
  characters.
- Every node is visited exactly once.

Time Complexity:
- O(n)

Space Complexity:
- O(h)

where h is the height of the tree due to the
recursive call stack.

Result:
- Returns the preorder string representation of
  the binary tree with the minimum required
  parentheses.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the string builder variable
  private final StringBuilder sb = new StringBuilder();

  // Method to find the string representation of the tree
  public String tree2str(TreeNode root) {
    // Call the dfs helper method
    this.dfs(root);

    // Return the sb by converting it to string
    return sb.toString();
  }

  // Helper method to find the child string
  private void dfs(TreeNode root) {
    // If root is null then return
    if (root == null) {
      return;
    }

    // Add the value to the string builder
    sb.append(root.val);

    // If left child is not null then update the string builder accordingly
    if (root.left != null) {
      // Update the string builder
      sb.append('(');

      // Call the dfs helper method on the left child
      this.dfs(root.left);

      // Update the string builder
      sb.append(')');
    }

    // If right child is not null then update the string builder accordingly
    if (root.right != null) {
      // If root.left is null then append "()" to string builder
      if (root.left == null) {
        sb.append("()");
      }

      // Update the string builder
      sb.append('(');

      // Call the dfs helper method on the right child
      this.dfs(root.right);

      // Update the string builder
      sb.append(')');
    }
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
public class _606_Construct_String_from_Binary_Tree {
  // Main method to test tree2str
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, null, 4 });

    String result = new Solution().tree2str(root);

    System.out.println("The string representation of the tree is : " + result);
  }
}
