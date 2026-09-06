/*
LeetCode Problem: https://leetcode.com/problems/sum-of-all-subset-xor-totals/

Question: 1863. Sum of All Subset XOR Totals

Problem Statement: The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.

For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
Given an array nums, return the sum of all XOR totals for every subset of nums. 

Note: Subsets with the same elements should be counted multiple times.

An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.

Example 1:
Input: nums = [1,3]
Output: 6
Explanation: The 4 subsets of [1,3] are:
- The empty subset has an XOR total of 0.
- [1] has an XOR total of 1.
- [3] has an XOR total of 3.
- [1,3] has an XOR total of 1 XOR 3 = 2.
0 + 1 + 3 + 2 = 6

Example 2:
Input: nums = [5,1,6]
Output: 28
Explanation: The 8 subsets of [5,1,6] are:
- The empty subset has an XOR total of 0.
- [5] has an XOR total of 5.
- [1] has an XOR total of 1.
- [6] has an XOR total of 6.
- [5,1] has an XOR total of 5 XOR 1 = 4.
- [5,6] has an XOR total of 5 XOR 6 = 3.
- [1,6] has an XOR total of 1 XOR 6 = 7.
- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28

Example 3:
Input: nums = [3,4,5,6,7,8]
Output: 480
Explanation: The sum of all XOR totals for every subset is 480.

Constraints:
1 <= nums.length <= 12
1 <= nums[i] <= 20
*/

/*
Approach: Recursive Subset Enumeration with XOR Accumulation
Goal:
- Compute the sum of XOR totals across all 2^n
  subsets of nums, where the XOR total of a subset
  is the XOR of all its elements.
Core Idea:
- For each element, branch into two recursive
  paths: exclude it (XOR total unchanged) or
  include it (XOR total updated with nums[index]).
- When all elements are processed (index ==
  nums.length), the current total is the XOR of
  one complete subset; return it to be summed.
Algorithm Steps:
1. Call dfs(nums, 0, 0).
2. In dfs(index, total):
   a. If index == nums.length, return total (base
      case: one subset's XOR total).
   b. Return dfs(index + 1, total) (exclude
      nums[index]) + dfs(index + 1, total ^
      nums[index]) (include nums[index]).
3. Return the result from the initial call.
Why It Works:
- The two-branch recursion generates all 2^n
  subsets exactly once (each element is either
  included or excluded at each level).
- XOR is accumulated incrementally along each
  path, so each leaf holds the correct XOR total
  for its corresponding subset.
- Summing the return values bottom-up aggregates
  all subset XOR totals without explicit subset
  storage.
Time Complexity:
- O(2^n)
since the recursion tree has 2^n leaves, one per
subset, with O(1) work per node.
Space Complexity:
- O(n)
for the recursive call stack depth equal to the
number of elements.
Result:
- Returns the sum of XOR totals across all subsets
  of nums.
*/

package Backtracking.Easy;

// Solution Class
class Solution {
  // Method to find the sum of all XOR totals for every subset of nums
  public int subsetXORSum(int[] nums) {
    // Call the recurive dfs method
    return this.dfs(nums, 0, 0);
  }

  // Helper method to get the totalSum
  private int dfs(int[] nums, int index, int total) {
    // If index is equal to the length of the nums.length then return the total
    if (index == nums.length) {
      return total;
    }

    // Call the recurive dfs method
    return this.dfs(nums, index + 1, total) + this.dfs(nums, index + 1, total ^ nums[index]);
  }
}

public class _1863_Sum_of_All_Subset_XOR_Totals {
  // Main method to test subsetXORSum
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 3 };

    int result = new Solution().subsetXORSum(nums);

    System.out.println("The sum of all XOR totals for every subset of nums is : " + result);
  }
}
