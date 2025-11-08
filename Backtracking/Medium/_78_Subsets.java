/*
LeetCode Problem: https://leetcode.com/problems/subsets/

Question: 78. Subsets

Problem Statement: Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */

/*
Approach:
1. We are given an integer array `nums` containing **unique elements**, and we must find all possible 
   subsets (the power set).

2. This is a **classic backtracking problem** where at each step we have two choices:
   - Include the current element in the subset.
   - Exclude the current element and move to the next.

3. The algorithm uses **recursive DFS (backtracking)** to explore all inclusion/exclusion possibilities:
   - Start from index `0` with an empty subset.
   - For every element `nums[index]`, make two recursive calls:
       • One including `nums[index]` in the current subset.
       • One excluding it.
   - When we reach the end of the array (`index == nums.length`), we add the formed subset to the result.

4. This ensures all possible subsets are generated, including the empty set `[]`.

5. Since all numbers in `nums` are unique, there is no need for duplicate handling (no sorting or frequency map needed).

Time Complexity: O(2^N), where N = number of elements in `nums`, 
                 since each element can either be included or excluded.
Space Complexity: O(N) for recursion depth and temporary subset storage.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _78_Subsets {
    // Method to find all possible subset
    public static List<List<Integer>> subsets(int[] nums) {
        // Initialize the list to store all valid combinations
        List<List<Integer>> result = new ArrayList<>();

        // Start the recursive backtracking process to generate unique combinations
        backtrack(nums, 0, new ArrayList<>(), result);

        // Return the final list of unique combinations
        return result;
    }

    // Helper method to find all subset
    private static void backtrack(int[] nums, int index, List<Integer> subset, List<List<Integer>> list) {
        // Base case if index is greater then the nums length then add the subset to the
        // list and return
        if (nums.length == index) {
            list.add(new ArrayList<>(subset));
            return;
        }

        // Add the current value index to the subset
        subset.add(nums[index]);

        // Backtrack when the index is choosen
        backtrack(nums, index + 1, subset, list);

        // Remove the current index form the subset
        subset.removeLast();

        // Backtrack when the index is not choosen
        backtrack(nums, index + 1, subset, list);
    }

    // Main method to test subsets
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };

        List<List<Integer>> result = subsets(nums);

        System.out.print("The all possible subset of " + Arrays.toString(nums) + " is : " + result);
    }
}
