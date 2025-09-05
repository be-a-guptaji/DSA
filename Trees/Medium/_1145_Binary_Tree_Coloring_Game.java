/*
LeetCode Problem: https://leetcode.com/problems/binary-tree-coloring-game/

Question: 1145. Binary Tree Coloring Game

Problem Statement: Two players play a turn based game on a binary tree. We are given the root of this binary tree, and the number of nodes n in the tree. n is odd, and each node has a distinct value from 1 to n.

Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x. The first player colors the node with value x red, and the second player colors the node with value y blue.

Then, the players take turns starting with the first player. In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)

If (and only if) a player cannot choose such a node in this way, they must pass their turn. If both players pass their turn, the game ends, and the winner is the player that colored more nodes.

You are the second player. If it is possible to choose such a y to ensure you win the game, return true. If it is not possible, return false.

Example 1:
Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
Output: true
Explanation: The second player can choose the node with value 2.

Example 2:
Input: root = [1,2,3], n = 3, x = 1
Output: false

Constraints:

The number of nodes in the tree is n.
1 <= x <= n <= 100
n is odd.
1 <= Node.val <= n
All the values of the tree are unique.
*/

/*
Approach:
1. Identify the problem split
   - When Player 1 colors node x, the tree splits into 3 independent regions:
     a. Left subtree of x
     b. Right subtree of x
     c. The "parent side" (all nodes not in x’s subtree)
2. Count sizes of regions
   - Use DFS (or recursion) to count the number of nodes in each subtree.
   - Let:
       leftCount  = number of nodes in left subtree of x
       rightCount = number of nodes in right subtree of x
       parentCount = total nodes - (leftCount + rightCount + 1)
3. Winning condition
   - Player 2 can win if they pick a node y inside one region 
     that has more than half of the nodes.
   - Mathematically:
       if max(leftCount, rightCount, parentCount) > n / 2
       then Player 2 can guarantee a win.
4. Return result
   - Return true if Player 2 has a winning move, otherwise false.

Time Complexity: O(n),  (we visit each node once to count sizes)
Space Complexity: O(h), (recursion stack where h = height of tree)
*/

package Trees.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class _1145_Binary_Tree_Coloring_Game {
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

    // Private varible for tracking the left and right nodes of the subtree
    private static int leftNodes, rightNodes;

    // Method to find if we can win the coloring game of the binary tree
    public static boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        // Find the nodes of the left and right sub tree of the target node
        count(root, x);

        // Parent node is left and right subtree nodes - 1 less than the total nodes
        int parentNode = n - leftNodes - rightNodes - 1;

        // Return true if any any sub tree is more than n/2 of the total nodes
        return Math.max(parentNode, Math.max(leftNodes, rightNodes)) > n / 2;
    }

    // Helper function to find the number of nodes in the tree
    private static int count(TreeNode root, int x) {
        // If the root is null then return
        if (root == null) {
            return 0;
        }

        // Get the number of nodes of left and right subtree of the root node
        int l = count(root.left, x);
        int r = count(root.right, x);

        // If root node is equal to x then update the global variable
        if (root.val == x) {
            leftNodes = l;
            rightNodes = r;
        }

        // Return the sum of the left and right subtree
        return l + r + 1;
    }

    // Build tree from int[] (no nulls, complete binary tree)
    public static TreeNode makeTree(int[] val) {
        if (val == null || val.length == 0) {
            return null;
        }

        // Convert int[] → Integer[] (not strictly needed here, but keeps consistent
        // with logic)
        Integer[] arr = new Integer[val.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = val[i];
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (i < arr.length) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < arr.length) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // Mock class for makeing the TreeNode Class
    public static class TreeNode {
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
    }

    // Main method to test btreeGameWinningMove
    public static void main(String[] args) {
        int[] root = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        int n = 11;
        int x = 3;

        boolean result = btreeGameWinningMove(makeTree(root), n, x);

        if (!result) {
            System.out.println("Player 1 won.");
        } else {
            System.out.println("Player 2 won.");
        }
    }
}