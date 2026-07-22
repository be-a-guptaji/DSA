/*
LeetCode Problem: https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/

Question: 1530. Number of Good Leaf Nodes Pairs

Problem Statement: You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.

Example 1:
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].

Constraints:
    The number of nodes in the tree is in the range [1, 2^10].
    1 <= Node.val <= 100
    1 <= distance <= 10
*/

/*
Approach: Post-order DFS with Distance Frequency Tracking
Goal:
- Count pairs of leaf nodes in the tree whose
  distance (number of edges) is at most a given
  distance threshold.
Core Idea:
- For each node, track how many leaf nodes exist at
  each distance in its left and right subtrees.
- At each internal node, pair leaf nodes from the
  left subtree with leaf nodes from the right
  subtree, counting pairs whose combined distance
  does not exceed the threshold.
- Propagate distance information up the tree by
  incrementing distances by 1 (accounting for the
  edge from child to parent).
- A leaf node is at distance 1 from itself
  (conceptually, before climbing up).
Algorithm Steps:
1. Call dfs(root, distance) to begin post-order
   traversal.
2. In dfs(node, distance):
   a. Initialize distanceFrequency[0..distance],
      where distanceFrequency[d] counts leaf nodes
      at distance d from the current node.
   b. If node is null, return an all-zero frequency
      array (no leaves).
   c. If node is a leaf (no left and right child):
      - Set distanceFrequency[1] = 1 (the leaf
        itself is at distance 1).
      - Return distanceFrequency.
   d. Recursively compute:
      - leftNode = dfs(node.left, distance)
      - rightNode = dfs(node.right, distance)
   e. Count good pairs:
      - For each leftDistance from 1 to distance:
        - For each rightDistance from 1 to distance:
          - If leftDistance + rightDistance <=
            distance:
            - Add leftNode[leftDistance] *
              rightNode[rightDistance] to goodPair
              (each combination of a left leaf at
              leftDistance and a right leaf at
              rightDistance forms a valid pair).
   f. Propagate distances upward:
      - For each currentDistance from 1 to
        distance - 1:
        - distanceFrequency[currentDistance + 1] =
          leftNode[currentDistance] +
          rightNode[currentDistance]
        (leaves at distance currentDistance in left
        or right subtree are at distance
        currentDistance + 1 from the current node's
        parent).
   g. Return distanceFrequency.
3. Return goodPair.
Why It Works:
- Post-order ensures that a node's children are
  fully processed before the node itself, so
  distance frequencies are accurate when pairing
  occurs.
- Leaf nodes from different subtrees must pass
  through their common ancestor; their pairwise
  distance is the sum of their distances to that
  ancestor.
- Checking leftDistance + rightDistance <=
  distance at each internal node captures all valid
  pairs in that subtree without missing or
  double-counting.
- Incrementing distances by 1 when propagating up
  correctly accounts for the edge traversed from
  child to parent.
Time Complexity:
- O(n * d^2)
where n is the number of nodes and d is the
distance threshold. Each node performs O(d^2) work
in the pairing loop.
Space Complexity:
- O(n * d)
for the frequency arrays across the recursion tree,
plus O(h) for the recursive call stack, where h is
the tree height.
Result:
- Returns the total count of leaf node pairs whose
  distance does not exceed the given threshold.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the goodPair variable
  private int goodPair;

  // Method to find the number of good leaf node pairs in the tree
  public int countPairs(TreeNode root, int distance) {
    // Initialize the goodPair variable
    this.goodPair = 0;

    // Call the recursive dfs method
    this.dfs(root, distance);

    // Return the goodPair
    return this.goodPair;
  }

  // Helper method to get the number of leaf nodes at each distance
  private int[] dfs(TreeNode root, int distance) {
    // Initialize the distanceFrequency array
    int[] distanceFrequency = new int[distance + 1];

    // If root is null then return the empty frequency array
    if (root == null) {
      return distanceFrequency;
    }

    // If root is a leaf node then store its distance as one
    if (root.left == null && root.right == null) {
      distanceFrequency[1] = 1;

      // Return the distanceFrequency array
      return distanceFrequency;
    }

    // Call the recursive dfs method on left and right node
    int[] leftNode = this.dfs(root.left, distance);
    int[] rightNode = this.dfs(root.right, distance);

    // Traverse through every possible left leaf node distance
    for (int leftDistance = 1; leftDistance <= distance; leftDistance++) {
      // Traverse through every possible right leaf node distance
      for (int rightDistance = 1; rightDistance <= distance; rightDistance++) {
        // Check if the total distance is less than or equal to the given distance
        if (leftDistance + rightDistance <= distance) {
          // Add the number of valid leaf node pairs
          this.goodPair += leftNode[leftDistance] * rightNode[rightDistance];
        }
      }
    }

    // Shift the distance of every leaf node by one level
    for (int currentDistance = 1; currentDistance < distance; currentDistance++) {
      // Add the left and right leaf node frequencies
      distanceFrequency[currentDistance + 1] = leftNode[currentDistance] + rightNode[currentDistance];
    }

    // Return the distanceFrequency array
    return distanceFrequency;
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
public class _1530_Number_of_Good_Leaf_Nodes_Pairs {
  // Main method to test countPairs
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
    int distance = 3;

    int result = new Solution().countPairs(root, distance);

    System.out.println("The number of good leaf node pairs in the tree is : " + result);
  }
}
