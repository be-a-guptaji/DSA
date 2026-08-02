/*
LeetCode Problem: https://leetcode.com/problems/delete-nodes-and-return-forest/

Question: 1110. Delete Nodes And Return Forest

Problem Statement: Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.

Example 1:
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

Example 2:
Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]

Constraints:
    The number of nodes in the given tree is at most 1000.
    Each node has a distinct value between 1 and 1000.
    to_delete.length <= 1000
    to_delete contains distinct values between 1 and 1000.
*/

/*
Approach: Post-order DFS with Deletion and Orphan Root Collection
Goal:
- Delete all nodes in to_delete from the binary
  tree and return the roots of all remaining
  disjoint subtrees forming the resulting forest.
Core Idea:
- Deleting a node severs its connection to the
  tree, making its non-null children new roots of
  independent subtrees.
- Post-order processing ensures children are
  evaluated before their parent, so by the time a
  node is deleted, its children have already been
  fully processed and can be safely promoted to
  new roots.
- The original root becomes a forest root only if
  it is not itself deleted.
Algorithm Steps:
1. Build a boolean delete[1001] array marking all
   nodes in to_delete for O(1) lookup.
2. Call dfs(root, delete, roots) and capture the
   returned node as newRoot.
3. If newRoot != null (original root was not
   deleted), add it to roots.
4. In dfs(node, delete, roots):
   a. If node is null, return null.
   b. Recurse post-order:
      - node.left = dfs(node.left, delete, roots)
      - node.right = dfs(node.right, delete, roots)
   c. If delete[node.val] is true:
      - If node.left != null, add node.left to
        roots (left child becomes a new root).
      - If node.right != null, add node.right to
        roots (right child becomes a new root).
      - Return null (detach this node from its
        parent).
   d. Otherwise return node unchanged.
Why It Works:
- Post-order ensures children are trimmed and
  re-linked before the parent checks its own
  deletion status, so node.left and node.right
  reflect the already-processed subtree roots at
  the time of deletion.
- Returning null automatically detaches the deleted
  node from its parent via the left/right
  assignment in the recursive call.
- The original root is handled outside the DFS
  since it has no parent to perform the null
  assignment for it.
Time Complexity:
- O(n)
where n is the number of nodes, since each node is
visited exactly once.
Space Complexity:
- O(n)
for the delete array and O(h) for the recursive
call stack, where h is the tree height.
Result:
- Returns a list of TreeNode roots representing all
  remaining subtrees after the specified nodes are
  deleted.
*/

package Trees.Medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the roots of the trees in the remaining forest
  public ArrayList<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    // Initialize the array list
    ArrayList<TreeNode> roots = new ArrayList<>();

    // Initialize the hash set
    HashSet<Integer> set = new HashSet<>();

    // Add all the values to the hash set
    for (int i = 0; i < to_delete.length; i++) {
      set.add(to_delete[i]);
    }

    // Call the dfs method on the root
    TreeNode newRoot = this.dfs(root, set, roots);

    // If the root is not deleted then add it to the roots array list
    if (newRoot != null) {
      roots.add(newRoot);
    }

    // Return the roots variable
    return roots;
  }

  // Helper method to find the disjoint set of trees
  private TreeNode dfs(TreeNode root, HashSet<Integer> set, ArrayList<TreeNode> roots) {
    // If root is null then terminate the method
    if (root == null) {
      return null;
    }

    // Call the recursive method on left and right child
    root.left = this.dfs(root.left, set, roots);
    root.right = this.dfs(root.right, set, roots);

    // If root.val is in the hash set then update the roots array list
    if (set.contains(root.val)) {
      // Update the roots array list with the left child
      if (root.left != null) {
        roots.add(root.left);
      }

      // Update the roots array list with the right child
      if (root.right != null) {
        roots.add(root.right);
      }

      // Delete the current node
      return null;
    }

    // Return the current root in the end
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
public class _1110_Delete_Nodes_And_Return_Forest {
  // Main method to test delNodes
  public static void main(String[] args) {
    TreeNode root = TreeNode.makeTree(new Integer[] { 1, 2, 3, 2, null, 2, 4 });
    int[] to_delete = new int[] { 3, 5 };

    ArrayList<TreeNode> result = new Solution().delNodes(root, to_delete);

    System.out.println("The roots of the trees in the remaining forest is : " + result);
  }
}
