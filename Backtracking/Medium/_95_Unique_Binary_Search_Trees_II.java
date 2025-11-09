/*
LeetCode Problem: https://leetcode.com/problems/unique-binary-search-trees-ii/

Question: 95. Unique Binary Search Trees II

Problem Statement: Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:

1 <= n <= 8
 */

/*
Approach:
1. We are given an integer `n` and need to generate all structurally unique Binary Search Trees (BSTs) 
   that can store values from 1 to n.

2. The key observation:
   - Any number `i` in the range [1, n] can be chosen as the root of a BST.
   - Numbers smaller than `i` (1 to i-1) form the left subtree.
   - Numbers greater than `i` (i+1 to n) form the right subtree.
   - Recursively generate all valid left and right subtrees, and then combine them with the current root `i`.

3. Recursive Strategy:
   • Use a helper function `backtrack(left, right)` to build all trees for a given range [left, right].
   • Base case:
       - If `left > right`, there are no numbers available → return a list containing `null`
         (this represents an empty subtree).
   • Recursive case:
       - Iterate through each value `i` between `left` and `right`.
       - Generate all possible left subtrees from [left, i - 1].
       - Generate all possible right subtrees from [i + 1, right].
       - Combine every pair of left and right subtrees with `i` as the root.
       - Add each valid tree to the result list.

4. This ensures:
   - Every unique structural variation of BST is constructed.
   - Each recursive branch builds distinct left and right subtree combinations.
   - All trees follow BST properties automatically because of the value ranges.

5. The recursion terminates when the range is invalid (`left > right`), ensuring no infinite calls.

Time Complexity: Catalan Number C_n ≈ O(4^n / n^(3/2))
   - Each unique BST structure corresponds to a Catalan number.
Space Complexity: O(4^n / n^(3/2)) for storing all generated trees and recursion depth.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class _95_Unique_Binary_Search_Trees_II {
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

   // Method to generate all structurally unique BSTs that store values 1 to n
   public static List<TreeNode> generateTrees(int n) {
      // Call the recursive helper method to generate all BSTs
      return backtrack(1, n);
   }

   // Helper method to generate all unique BSTs for the given range [left, right]
   private static List<TreeNode> backtrack(int left, int right) {
      // Initialize the list to store all valid trees for this range
      List<TreeNode> allTrees = new ArrayList<>();

      // Base case: when left > right, add null to represent an empty subtree
      if (left > right) {
         allTrees.add(null);
         return allTrees;
      }

      // Iterate through all possible root values from left to right
      for (int i = left; i <= right; i++) {
         // Generate all possible left subtrees for numbers smaller than i
         List<TreeNode> leftTrees = backtrack(left, i - 1);

         // Generate all possible right subtrees for numbers greater than i
         List<TreeNode> rightTrees = backtrack(i + 1, right);

         // Combine each left and right subtree with the current root i
         for (TreeNode l : leftTrees) {
            for (TreeNode r : rightTrees) {
               // Create a new root node with value i
               TreeNode root = new TreeNode(i);

               // Assign left and right subtrees
               root.left = l;
               root.right = r;

               // Add the root to the list of valid BSTs
               allTrees.add(root);
            }
         }
      }

      // Return all possible trees for this range
      return allTrees;
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

   // Main method to test generateTrees
   public static void main(String[] args) {
      int n = 5;

      List<TreeNode> result = generateTrees(n);

      System.out.print("The all the structurally unique BST's (binary search trees) is : " + result);
   }
}
