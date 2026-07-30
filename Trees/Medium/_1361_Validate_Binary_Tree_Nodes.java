/*
LeetCode Problem: https://leetcode.com/problems/validate-binary-tree-nodes/

Question: 1361. Validate Binary Tree Nodes

Problem Statement: You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.

Example 1:
Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true

Example 2:
Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false

Example 3:
Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false

Constraints:
    n == leftChild.length == rightChild.length
    1 <= n <= 10^4
    -1 <= leftChild[i], rightChild[i] <= n - 1
*/

/*
Approach: Parent Count Validation + DFS Cycle and Connectivity Check
Goal:
- Determine if the given binary tree representation
  forms a valid binary tree: exactly one root, no
  node with more than one parent, no cycles, and
  all nodes connected.
Core Idea:
- A valid binary tree satisfies three structural
  constraints simultaneously:
  1. Exactly one root exists (exactly one node with
     no parent).
  2. No node has more than one parent (each node
     appears as a child at most once).
  3. The tree is connected with no cycles (DFS from
     root visits every node exactly once).
- These three checks together are necessary and
  sufficient to validate the structure.
Algorithm Steps:
1. Build hasParent[]:
   - For each node i, mark leftChild[i] and
     rightChild[i] as having a parent.
   - If any child node is assigned a parent more
     than once, return false immediately (two-parent
     violation).
2. Scan hasParent[] to find the root:
   - Count nodes without a parent (totalParent
     tracks nodes WITH a parent for size check).
   - If all n nodes have parents (totalParent == n),
     no root exists, return false.
   - The last node with no parent is assigned as
     root (exactly one should exist for a valid
     tree; if multiple exist, the connectivity
     check catches the disconnection).
3. Run DFS from root:
   - If a visited node is encountered again, a
     cycle exists, return false.
   - Mark each visited node in visited[].
4. Count visited nodes:
   - If visited count != n, the tree is
     disconnected (some nodes unreachable from
     root), return false.
5. Return true if all checks pass.
Why It Works:
- The two-parent check eliminates cases where a
  node has multiple incoming edges (invalid in a
  tree).
- The root existence check (totalParent == n)
  catches cycles that make every node appear as a
  child (no valid root).
- DFS cycle detection catches structural cycles not
  detectable from parent counts alone.
- The connectivity check ensures no disjoint
  components exist that would pass the earlier
  checks but still not form a single tree.
Time Complexity:
- O(n)
since each node and edge is processed a constant
number of times across all steps.
Space Complexity:
- O(n)
for hasParent[], visited[], and O(n) recursive
call stack in the worst case of a skewed tree.
Result:
- Returns true if the representation forms a valid
  binary tree, false otherwise.
*/

package Trees.Medium;

// Solution Class
class Solution {
  // Method to find if the binary tree is valid or not
  public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
    // Initialize the hasParent array
    boolean[] hasParent = new boolean[n];

    // Initialize the visited array
    boolean[] visited = new boolean[n];

    // Iterate over the left and right child to get the parent
    for (int i = 0; i < n; i++) {
      if (leftChild[i] != -1) {
        if (hasParent[leftChild[i]]) {
          return false;
        }
        hasParent[leftChild[i]] = true;
      }

      if (rightChild[i] != -1) {
        if (hasParent[rightChild[i]]) {
          return false;
        }
        hasParent[rightChild[i]] = true;
      }
    }

    // Initialize the totalParent variable
    int totalParent = 0;

    // Initialize the root variable
    int root = -1;

    // Get the total parents
    for (int i = 0; i < n; i++) {
      if (hasParent[i]) {
        totalParent++;
      }

      if (!hasParent[i]) {
        root = i;
      }
    }

    // If totalParent is equal to n then return false
    if (totalParent == n) {
      return false;
    }

    // Initialize the fullyTraversed variable
    boolean fullyTraversed = this.dfs(root, leftChild, rightChild, visited);

    // If fullyTraversed is false then return false
    if (!fullyTraversed) {
      return false;
    }

    // Initialize the totalNode variable
    int totalNode = 0;

    // Get the total nodes
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        totalNode++;
      }
    }

    // Return the condition
    return totalNode == n;
  }

  // Helper method for the dfs for finding the tree
  private boolean dfs(int root, int[] leftChild, int[] rightChild, boolean[] visited) {
    // If root is -1 then return true
    if (root == -1) {
      return true;
    }

    // If root is already visited then return false else set it true
    if (visited[root]) {
      return false;
    } else {
      visited[root] = true;
    }

    // Return the dfs call
    return this.dfs(leftChild[root], leftChild, rightChild, visited)
        && this.dfs(rightChild[root], leftChild, rightChild, visited);
  }
}

// Main Class
public class _1361_Validate_Binary_Tree_Nodes {
  // Main method to test validateBinaryTreeNodes
  public static void main(String[] args) {
    int n = 2;
    int[] leftChild = new int[] { 1, 0 };
    int[] rightChild = new int[] { -1, -1 };

    boolean result = new Solution().validateBinaryTreeNodes(n, leftChild, rightChild);

    System.out.println("The binary tree is" + (result ? " " : " not ") + "valid.");
  }
}
