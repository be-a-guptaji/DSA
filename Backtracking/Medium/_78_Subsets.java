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
1. We are given an integer array `nums` that contains unique elements.
   The goal is to find all possible subsets (the power set).
2. This problem can be solved efficiently using **backtracking**.
3. The core idea:
   - At each recursion level, decide whether to include or exclude each element starting from a given index.
   - Each recursive call builds upon the current subset and explores all future possibilities.
4. Algorithm steps:
   • Start with an empty subset.
   • Add the current subset to the result list (since every subset—including empty—must be included).
   • Iterate from the current index `i` to the end of the array:
       - Add `nums[i]` to the current subset.
       - Recursively call the function with `i + 1` to explore subsets that include `nums[i]`.
       - Remove `nums[i]` after the recursive call (backtrack) to explore subsets without it.
5. This ensures that:
   - All unique subsets are generated.
   - The order of elements is maintained.
   - No duplicate subsets are formed since `nums` contains unique elements.

Time Complexity: O(2^N), where N = length of `nums`, since each element can either be included or excluded.
Space Complexity: O(N) for the recursion stack and temporary subset storage.
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
        list.add(new ArrayList<>(subset));

        // Iterate over the nums array to generate subset
        for (int i = index; i < nums.length; i++) {
            // Add the current value index to the subset
            subset.add(nums[i]);

            // Backtrack when the index is choosen
            backtrack(nums, i + 1, subset, list);

            // Remove the current index form the subset
            subset.removeLast();
        }
    }

    // Main method to test subsets
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };

        List<List<Integer>> result = subsets(nums);

        System.out.print("The all possible subset of " + Arrays.toString(nums) + " is : " + result);
    }
}
