/*
LeetCode Problem: https://leetcode.com/problems/operations-on-tree/

Question: 1993. Operations on Tree

Problem Statement: You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of the ith node. The root of the tree is node 0, so parent[0] = -1 since it has no parent. You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.

The data structure should support the following functions:

    Lock: Locks the given node for the given user and prevents other users from locking the same node. You may only lock a node using this function if the node is unlocked.
    Unlock: Unlocks the given node for the given user. You may only unlock a node using this function if it is currently locked by the same user.
    Upgrade: Locks the given node for the given user and unlocks all of its descendants regardless of who locked it. You may only upgrade a node if all 3 conditions are true:
        The node is unlocked,
        It has at least one locked descendant (by any user), and
        It does not have any locked ancestors.

Implement the LockingTree class:

    LockingTree(int[] parent) initializes the data structure with the parent array.
    lock(int num, int user) returns true if it is possible for the user with id user to lock the node num, or false otherwise. If it is possible, the node num will become locked by the user with id user.
    unlock(int num, int user) returns true if it is possible for the user with id user to unlock the node num, or false otherwise. If it is possible, the node num will become unlocked.
    upgrade(int num, int user) returns true if it is possible for the user with id user to upgrade the node num, or false otherwise. If it is possible, the node num will be upgraded.

Example 1:
Input
["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
[[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
Output
[null, true, false, true, true, true, false]
Explanation
LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
lockingTree.lock(2, 2);    // return true because node 2 is unlocked.
                           // Node 2 will now be locked by user 2.
lockingTree.unlock(2, 3);  // return false because user 3 cannot unlock a node locked by user 2.
lockingTree.unlock(2, 2);  // return true because node 2 was previously locked by user 2.
                           // Node 2 will now be unlocked.
lockingTree.lock(4, 5);    // return true because node 4 is unlocked.
                           // Node 4 will now be locked by user 5.
lockingTree.upgrade(0, 1); // return true because node 0 is unlocked and has at least one locked descendant (node 4).
                           // Node 0 will now be locked by user 1 and node 4 will now be unlocked.
lockingTree.lock(0, 1);    // return false because node 0 is already locked.

Constraints:
    n == parent.length
    2 <= n <= 2000
    0 <= parent[i] <= n - 1 for i != 0
    parent[0] == -1
    0 <= num <= n - 1
    1 <= user <= 10^4
    parent represents a valid tree.
    At most 2000 calls in total will be made to lock, unlock, and upgrade.
*/

/*
Approach: Tree Structure with Lock State Tracking and Ancestor/Descendant Validation
Goal:
- Implement a locking mechanism on a tree where
  nodes can be locked/unlocked by users and
  upgraded with specific constraints.
Core Idea:
- Each node tracks its lock state (0 = unlocked,
  non-zero = user ID holding the lock).
- Lock and unlock operations are simple state
  toggles.
- Upgrade succeeds only if: all ancestors are
  unlocked (no blocking higher up), at least one
  descendant is locked (something to upgrade), and
  the requesting user gains the lock while all
  descendants are unlocked.
- Build an adjacency list of children during
  construction for O(1) child access during DFS.
Algorithm Steps:
1. Constructor LockingTree(int[] parent):
   a. Store the parent array.
   b. Initialize locked[n] with all zeros (all
      nodes unlocked).
   c. Initialize child[n] as an ArrayList array.
   d. For each node i from 1 to n-1, add i to
      child[parent[i]] (build the tree structure).
2. lock(num, user):
   a. If locked[num] != 0, the node is already
      locked, return false.
   b. Set locked[num] = user.
   c. Return true.
3. unlock(num, user):
   a. If locked[num] != user, the lock is held by
      a different user, return false.
   b. Set locked[num] = 0.
   c. Return true.
4. upgrade(num, user):
   a. Walk from num to the root via parent pointers:
      - If any ancestor is locked (locked[node] !=
        0), return false immediately (ancestor
        blocks upgrade).
   b. Call dfs(num) to count and unlock all locked
      descendants:
      - If dfs returns > 0, at least one descendant
        was locked.
      - Set locked[num] = user (claim the lock).
      - Return true.
      - If dfs returns 0, no descendants were
        locked, return false (nothing to upgrade).
5. Helper dfs(node):
   a. Initialize lockedCount = 0.
   b. If locked[node] != 0:
      - Unlock it: locked[node] = 0.
      - Increment lockedCount.
   c. For each child of node:
      - Add dfs(child) to lockedCount (recursively
        unlock and count descendants).
   d. Return lockedCount.
Why It Works:
- Ancestor validation prevents upgrades that would
  create a lock conflict higher in the tree (a
  locked ancestor would block communication).
- Descendant unlocking via DFS ensures all locked
  nodes in the subtree are released, maintaining
  consistency after an upgrade.
- The parent array enables O(depth) ancestor
  traversal; the child array enables O(subtree size)
  descendant traversal.
- Lock state is stored as user ID (0 means
  unlocked), eliminating the need for a separate
  boolean tracking array.
Time Complexity:
- lock(num, user): O(1)
- unlock(num, user): O(1)
- upgrade(num, user): O(depth + subtree size),
  where depth is the height from num to root and
  subtree size is the number of descendants of num.
Space Complexity:
- O(n)
for the locked, parent, and child arrays, plus O(h)
for the DFS call stack, where h is the tree height.
Result:
- Supports efficient lock/unlock on individual
  nodes and conditional upgrades that account for
  ancestor and descendant lock states.
*/

package Trees.Medium;

import java.util.ArrayList;

// LockingTree Class
class LockingTree {
  // Initialize the lock array
  private int[] locked;

  // Initialize the parent array
  private int[] parent;

  // Initialize the child array
  private ArrayList<Integer>[] child;

  public LockingTree(int[] parent) {
    // Initialize the parent array
    this.parent = parent;

    // Initialize the size variable
    int size = this.parent.length;

    // Initialize the lock array
    this.locked = new int[size];

    // Initialize the child array
    this.child = new ArrayList[size];

    // Initialize the array of arraylist
    for (int i = 0; i < size; i++) {
      this.child[i] = new ArrayList<>();
    }

    // Set the child
    for (int i = 1; i < size; i++) {
      child[this.parent[i]].add(i);
    }
  }

  public boolean lock(int num, int user) {
    // If it is already locked then return false
    if (this.locked[num] != 0) {
      return false;
    }

    // Set the lock to user
    this.locked[num] = user;

    // Return the true
    return true;
  }

  public boolean unlock(int num, int user) {
    // If it is not locked by itself then return false
    if (this.locked[num] != user) {
      return false;
    }

    // Set the lock to 0
    this.locked[num] = 0;

    // Return the true
    return true;
  }

  public boolean upgrade(int num, int user) {
    // Initialize the node value
    int node = num;

    // Get to the root of the tree
    while (node != -1) {
      // If locked[node] not equal to zero then return false
      if (this.locked[node] != 0) {
        return false;
      }

      // Update the node
      node = this.parent[node];
    }

    // If one of its child is lock then return true
    if (this.dfs(num) > 0) {
      // Set the node to lock
      this.locked[num] = user;

      // Return false
      return true;
    }

    // Return false
    return false;
  }

  // Helper method to unlock the childrens
  private int dfs(int node) {
    // Initialize the lockedCount
    int lockedCount = 0;

    // If locked[node] is not zero then increment the lockedCount
    if (this.locked[node] != 0) {
      // Unlock the locked
      this.locked[node] = 0;

      // Increment the lockedCount
      lockedCount++;
    }

    // Call the dfs to all its child
    for (int child : this.child[node]) {
      lockedCount += this.dfs(child);
    }

    // Return the lockedCount
    return lockedCount;
  }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree obj = new LockingTree(parent);
 * boolean param_1 = obj.lock(num,user);
 * boolean param_2 = obj.unlock(num,user);
 * boolean param_3 = obj.upgrade(num,user);
 */

// Main Class
public class _1993_Operations_on_Tree {
  // Main method to test LockingTree
  public static void main(String[] args) {
    String[] operations = { "LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock" };

    ArrayList<int[]> values = new ArrayList<>();

    values.add(new int[] { -1, 0, 0, 1, 1, 2, 2 });
    values.add(new int[] { 2, 2 });
    values.add(new int[] { 2, 3 });
    values.add(new int[] { 2, 2 });
    values.add(new int[] { 4, 5 });
    values.add(new int[] { 0, 1 });
    values.add(new int[] { 0, 1 });

    // Create the LockingTree object
    LockingTree lockingTree = new LockingTree(values.get(0));

    // Loop through the operations and values arrays
    for (int i = 0; i < operations.length; i++) {
      String operation = operations[i];

      switch (operation) {
        case "LockingTree" -> {
          lockingTree = new LockingTree(values.get(i));
          System.out.println("null");
        }
        case "lock" -> {
          System.out.println(lockingTree.lock(values.get(i)[0], values.get(i)[1]));
        }
        case "unlock" -> {
          System.out.println(lockingTree.unlock(values.get(i)[0], values.get(i)[1]));
        }
        case "upgrade" -> {
          System.out.println(lockingTree.upgrade(values.get(i)[0], values.get(i)[1]));
        }
      }
    }
  }
}
