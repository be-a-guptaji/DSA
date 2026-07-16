/*
LeetCode Problem: https://leetcode.com/problems/find-duplicate-subtrees/

Question: 652. Find Duplicate Subtrees

Problem Statement: Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]

Example 3:
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]

Constraints:
    The number of the nodes in the tree will be in the range [1, 5000]
    -200 <= Node.val <= 200
*/

/*
Approach: Post-order Serialization with Frequency Tracking
Goal:
- Find all duplicate subtrees within the binary tree.
- Return one representative TreeNode for each unique
  subtree that appears more than once.
Core Idea:
- Two subtrees are identical if and only if they have
  the same structure and the same node values at
  corresponding positions.
- Serialize each subtree via post-order DFS into a
  canonical string encoding its shape and values.
- Assign each unique serialization a unique integer
  ID to reduce memory overhead.
- Track how many times each ID appears; when a
  subtree's ID count reaches exactly 2 (marking the
  second occurrence), add its root node to the
  result list.
Algorithm Steps:
1. Initialize subtreeMap (String to unique ID),
   mapId (frequency counter indexed by ID), and
   subtrees (result list).
2. Call dfs(root) on the tree root.
3. In dfs(node):
   a. If node is null, return -1 (sentinel value
      representing empty subtree).
   b. Recursively serialize left and right subtrees:
      - leftId = dfs(node.left)
      - rightId = dfs(node.right)
   c. Build a canonical key by concatenating:
      leftId, a space delimiter, node.val, a space
      delimiter, rightId.
   d. Query subtreeMap: if key is new, assign it the
      next available ID (subtreeMap.size()); if key
      exists, retrieve its existing ID.
   e. Increment mapId[id], tracking how many times
      this serialization has been seen.
   f. If mapId[id] == 2 (exactly the second
      occurrence), add node to subtrees.
   g. Return id.
4. After dfs completes, return subtrees.
Why It Works:
- Post-order processing ensures that a node's ID is
  computed only after both its children's IDs are
  known, so the serialization key always reflects
  the complete subtree rooted at that node.
- Identical subtrees produce identical keys, so they
  map to the same ID.
- Tracking frequency per ID ensures each duplicate
  subtree is added to the result exactly once (on
  the second occurrence), avoiding duplicates in the
  output.
- Using integer IDs instead of raw serialization
  strings reduces string allocation and comparison
  overhead.
- The sentinel value -1 for null nodes ensures no
  collisions: a leaf's serialization differs from any
  internal node's (they will have different delimited
  integer sequences).
Time Complexity:
- O(n)
where n is the number of nodes, since each node's
serialization key is constructed in O(1) time
(concatenating two integers and one delimiter).
Space Complexity:
- O(n)
for the subtreeMap (up to n unique serializations),
mapId array (up to n unique IDs), and the recursive
call stack O(h), where h is the tree height.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Initialize the map for the sub tree
  private HashMap<String, Integer> subtreeMap;

  // Initialize the array for the value map
  private int[] mapId;

  // Initialize the arraylist to hold the subtrees
  private ArrayList<TreeNode> subtrees;

  // Method to find the all duplicate subtrees
  public ArrayList<TreeNode> findDuplicateSubtrees(TreeNode root) {
    // Initialize the map for the sub tree
    this.subtreeMap = new HashMap<>();

    // Initialize the array for the value map
    this.mapId = new int[1804];

    // Initialize the arraylist to hold the subtrees
    this.subtrees = new ArrayList<>();

    // Call the dfs function for finding the similar subtrees
    this.dfs(root);

    // Return the subtrees arraylist
    return this.subtrees;
  }

  // Helper method to find the similar subtrees
  private int dfs(TreeNode root) {
    // If root is null then return the -1
    if (root == null) {
      return -1;
    }

    // Get the key and call the recursive function at the same time
    String key = new StringBuilder().append(this.dfs(root.left)).append(' ').append(root.val).append(' ')
        .append(this.dfs(root.right)).toString();

    // Add value to the map if absent
    Integer id = this.subtreeMap.putIfAbsent(key, this.subtreeMap.size());

    // If id is null then get it again
    if (id == null) {
      id = this.subtreeMap.get(key);
    }

    // Put the value in the mapId
    this.mapId[id]++;

    // If this.mapId[id] is 2 then add the value to the arraylist
    if (this.mapId[id] == 2) {
      this.subtrees.add(root);
    }

    // Return the id
    return id;
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
public class _652_Find_Duplicate_Subtrees {
  // Main method to test findDuplicateSubtrees
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 4, null, 2, 4, null, null, 4 });

    ArrayList<TreeNode> result = new Solution().findDuplicateSubtrees(root);

    System.out.println("The all duplicate subtrees is : " + result);
  }
}
