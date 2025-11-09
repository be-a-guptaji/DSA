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
1. We are given an integer `n`, and we need to count the number of possible permutations 
   (arrangements) of numbers from 1 to n such that for every position `i` (1-indexed):
      • nums[i] % i == 0   OR   i % nums[i] == 0
   This property defines a "beautiful arrangement."

2. The problem is solved using **backtracking** (depth-first search with pruning).

3. Core Idea:
   - At each recursive step, we try to place available numbers into the current position.
   - Only numbers that satisfy the "beautiful" divisibility condition for that position are allowed.
   - Once a valid number is placed, we move to the next position.
   - When all positions are filled, we have found a valid beautiful arrangement.

4. Implementation Details:
   • We use an integer array `nums` containing numbers from 1 to n.
   • The recursive method `backtrack(nums, value)`:
        - `value` represents the current position being filled.
        - If `value == 0`, all positions are filled → increment `arrangements`.
        - Otherwise, iterate through numbers [1...value].
            - Swap each number into the `value`th position.
            - If it satisfies the divisibility condition, recurse to the next smaller position.
            - Backtrack by swapping the elements back to restore the array.
   • This swapping-based backtracking ensures all unique permutations are explored without extra space.

5. The algorithm prunes invalid paths early using the divisibility condition, 
   which significantly reduces unnecessary recursion.

6. This ensures:
   - Every possible valid permutation is counted exactly once.
   - Invalid arrangements are skipped efficiently.

Time Complexity: O(N!) in the worst case, but pruning reduces actual runtime drastically.
Space Complexity: O(N) for the recursion stack and array storage.
*/

package Backtracking.Medium;

public class _95_Unique_Binary_Search_Trees_II {
   // Initialize the arrangements to return the value
   private static int arrangements;

   // Method to find the number of the beautiful arrangements
   public static int countArrangement(int n) {
      // Initialize the array of the size n
      int[] nums = new int[n + 1];

      // Fill the array form 0 to n - 1
      for (int i = 1; i <= n; i++) {
         nums[i] = i;
      }

      // Initialize the arrangements as the zero
      arrangements = 0;

      // Call the recursive backtrack method to find the arrangments
      backtrack(nums, n);

      // Return the result
      return arrangements;
   }

   // Helper method to find the arrangements
   private static void backtrack(int[] nums, int value) {
      // Edge case if value is equal to zero then increment the arrangements value and
      // return
      if (value == 0) {
         arrangements++;
         return;
      }

      // Iterate over all the values
      for (int i = value; i > 0; i--) {
         // Swap the numbers in the nums array
         int temp = nums[i];
         nums[i] = nums[value];
         nums[value] = temp;

         // Make a recursive backtrack call if i % value == 0 or value % i == 0
         if (i % value == 0 || value % i == 0) {
            backtrack(nums, value - 1);
         }

         // Restore the value of the nums array before swapping
         nums[value] = nums[i];
         nums[i] = temp;
      }
   }

   // Main method to test countArrangement
   public static void main(String[] args) {
      int n = 5;

      int result = countArrangement(n);

      System.out.print("The number of the beautiful arrangements is : " + result);
   }
}
