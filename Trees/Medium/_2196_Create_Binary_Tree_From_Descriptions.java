/*
LeetCode Problem: https://leetcode.com/problems/create-binary-tree-from-descriptions/

Question: 2196. Create Binary Tree From Descriptions

Problem Statement: You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

    If isLefti == 1, then childi is the left child of parenti.
    If isLefti == 0, then childi is the right child of parenti.

Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

Example 1:
Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.

Example 2:
Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.

Constraints:
    1 <= descriptions.length <= 10^4
    descriptions[i].length == 3
    1 <= parenti, childi <= 10^5
    0 <= isLefti <= 1
    The binary tree described by descriptions is valid.
*/

/*
Approach: Node Mapping + Child Tracking

Goal:
- Construct a binary tree from the given parent-
  child descriptions.
- Return the root of the constructed binary tree.

Core Idea:
- Maintain a mapping from node values to their
  corresponding TreeNode objects so that each
  value is represented by exactly one node.
- Track every node that appears as a child.
- After constructing all parent-child
  relationships, the root is the only node that
  never appears as a child.

Algorithm Steps:
1. Initialize a node map to store the TreeNode
   corresponding to each value.
2. Initialize a boolean array to record every node
   that appears as a child.
3. Traverse each description:
   - Retrieve or create the parent node.
   - Retrieve or create the child node.
   - Connect the child as the left or right child
     according to the direction.
   - Mark the child value as present in the child
     set.
4. Traverse the descriptions again.
5. Return the parent node whose value never
   appears in the child set.
6. If no such node exists, return null.

Why It Works:
- Every node value is mapped to a unique TreeNode,
  preventing duplicate node creation.
- Each description establishes exactly one
  parent-child relationship.
- Since every node except the root appears exactly
  once as a child, the node that is never marked
  as a child must be the root.

Time Complexity:
- O(n)

where n is the number of descriptions.

Space Complexity:
- O(n)

for storing the node map and child tracking
structure.

Result:
- Returns the root of the binary tree constructed
  from the given descriptions.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the root of the binary tree
  public TreeNode createBinaryTree(int[][] descriptions) {
    // Initialize the hashmap for the holding the tree node
    TreeNode[] nodeMap = new TreeNode[100001];

    // Initialize the hashset for the holding the tree node value
    boolean[] childSet = new boolean[100001];

    // Iterate over the descriptions to make the binary tree
    for (int i = 0; i < descriptions.length; i++) {
      // Get the current description
      int[] description = descriptions[i];

      // Get Parent, child and its direction
      int parent = description[0];
      int child = description[1];
      int direction = description[2];

      // Add the value to the hash set
      childSet[child] = true;

      // Get the parent node from the nodeMap
      TreeNode parentNode = nodeMap[parent] = nodeMap[parent] == null ? new TreeNode(parent) : nodeMap[parent];

      // Get the child node from the nodeMap
      TreeNode childNode = nodeMap[child] = nodeMap[child] == null ? new TreeNode(child) : nodeMap[child];

      // Update the node accordingly
      if (direction == 0) {
        parentNode.right = childNode;
      } else {
        parentNode.left = childNode;
      }
    }

    // If node is not in the value set then return that node
    for (int i = 0; i < descriptions.length; i++) {
      if (!childSet[descriptions[i][0]]) {
        return nodeMap[descriptions[i][0]];
      }
    }

    // Return null in the end
    return null;
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
public class _2196_Create_Binary_Tree_From_Descriptions {
  // Main method to test createBinaryTree
  public static void main(String[] args) {
    int[][] descriptions = new int[][] { { 20, 15, 1 }, { 20, 17, 0 }, { 50, 20, 1 }, { 50, 80, 0 }, { 80, 19, 1 } };

    TreeNode result = new Solution().createBinaryTree(descriptions);

    System.out
        .println("The root of the binary tree is : " + result);
  }
}
