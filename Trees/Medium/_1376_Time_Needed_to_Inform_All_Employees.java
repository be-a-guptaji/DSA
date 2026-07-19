/*
LeetCode Problem: https://leetcode.com/problems/time-needed-to-inform-all-employees/

Question: 1376. Time Needed to Inform All Employees

Problem Statement: A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.

Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.

The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.

The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).

Return the number of minutes needed to inform all the employees about the urgent news.

Example 1:
Input: n = 1, headID = 0, manager = [-1], informTime = [0]
Output: 0
Explanation: The head of the company is the only employee in the company.

Example 2:
Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
Output: 1
Explanation: The head of the company with id = 2 is the direct manager of all the employees in the company and needs 1 minute to inform them all.
The tree structure of the employees in the company is shown.

Constraints:
    1 <= n <= 10^5
    0 <= headID < n
    manager.length == n
    0 <= manager[i] < n
    manager[headID] == -1
    informTime.length == n
    0 <= informTime[i] <= 1000
    informTime[i] == 0 if employee i has no subordinates.
    It is guaranteed that all the employees can be informed.
*/

/*
Approach: DFS with Memoized Path Accumulation
Goal:
- Find the maximum time needed to inform all
  employees, starting from the head.
Core Idea:
- The time for any employee to receive news equals
  the time from the head down the management chain
  plus their own inform time.
- Use DFS to traverse upward from each employee to
  the head, accumulating inform times along the way.
- Memoize results by overwriting informTime[node]
  with the total accumulated time from head to that
  node, and mark visited nodes by setting
  manager[node] = -1 to avoid recomputation.
Algorithm Steps:
1. For each employee node 0 to n-1:
   - Call dfs(node) to compute the time from head
     to that node.
   - Track the maximum time across all employees.
2. In dfs(node):
   a. If manager[node] == -1, the node has already
      been computed, return informTime[node].
   b. Otherwise, recursively compute the time from
      the head to manager[node]:
      - time_to_manager = dfs(manager[node])
   c. Add the current node's inform time to the
      accumulated path time:
      - informTime[node] += time_to_manager
   d. Mark the node as visited:
      - manager[node] = -1
   e. Return informTime[node].
3. Return the maximum time computed across all
   nodes.
Why It Works:
- Each node's informTime value is overwritten with
  the cumulative time from head to that node,
  effectively memoizing the result.
- The manager[node] = -1 flag prevents redundant
  recomputation of the same path.
- The maximum time across all employees represents
  the slowest propagation path from head to any
  leaf in the management tree.
Time Complexity:
- O(n)
where n is the number of employees, since each node
is visited once for DFS and once in the iteration.
Space Complexity:
- O(h)
where h is the depth of the management tree
(recursive call stack), worst case O(n) for a
linear chain.
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

// Solution Class 
class Solution {
  // Method to find the number of minutes needed to inform all the employees about
  // the urgent news
  public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
    // Initialize the result variable
    int result = 0;

    // Iterate over the managers
    for (int node = 0; node < n; node++) {
      // Update the result
      result = Math.max(result, this.dfs(node, manager, informTime));
    }

    // Return the result variable
    return result;
  }

  // Helper method for finding the max time for the node
  private int dfs(int node, int[] manager, int[] informTime) {
    // If it is not a head the manager then find the time for the subordinates
    if (manager[node] != -1) {
      // Get the inform time for the node
      informTime[node] += this.dfs(manager[node], manager, informTime);

      // Update the manager node to -1
      manager[node] = -1;
    }

    // Return the inform time for the node
    return informTime[node];
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
public class _1376_Time_Needed_to_Inform_All_Employees {
  // Main method to test numOfMinutes
  public static void main(String[] args) {
    int n = 15;
    int headID = 0;
    int[] manager = new int[] { -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6 };
    int[] informTime = new int[] { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };

    int result = new Solution().numOfMinutes(n, headID, manager, informTime);

    System.out.println("The number of minutes needed to inform all the employees about the urgent news is : " + result);
  }
}
