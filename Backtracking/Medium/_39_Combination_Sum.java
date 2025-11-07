/*
LeetCode Problem: https://leetcode.com/problems/combination-sum/

Question: 39. Combination Sum

Problem Statement: Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
Input: candidates = [2], target = 1
Output: []

Constraints:

1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
 */

/*
Approach:
1. We are given a list of distinct integers (candidates) and a target integer.
2. The goal is to find all unique combinations of candidates where the chosen numbers sum up to the target.
3. Each number in candidates can be chosen multiple times.
4. To solve this, we use a **backtracking (DFS)** approach:
   - Start from the first index of the candidates array.
   - At each step, pick a candidate and add it to the current combination.
   - Subtract its value from the target and recursively search for the remaining sum.
   - If the target becomes zero, we have found a valid combination — add it to the result list.
   - If the target becomes negative, stop exploring that path (backtrack).
   - After exploring one candidate, remove it from the combination and continue with the next one.
5. We pass the current index (`start`) in recursion to ensure that we don’t go backward in the array,
   which prevents duplicate combinations.
6. We use a new ArrayList<>(combination) to make a deep copy before adding to the final result.

Time Complexity: O(2^T) approximately, where T = target value divided by the smallest candidate.
Space Complexity: O(T) for recursion depth and current combination list.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39_Combination_Sum {
    // Method to find the list of all unique combinations of candidates where the
    // chosen numbers sum to the target
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Initialize the list of list of integers for storing the result combinations
        List<List<Integer>> result = new ArrayList<>();

        // Call the helper method to find all unique combinations using recursion
        backtrack(result, new ArrayList<>(), candidates, target, 0, candidates.length);

        // Return the result list of combinations
        return result;
    }

    // Helper recursive method to find all valid combinations of numbers that sum to
    // target
    private static void backtrack(List<List<Integer>> result, List<Integer> combination,
            int[] candidates, int target, int start, int length) {
        // Base case: if target becomes zero, add the current combination to the result
        // list
        if (target == 0) {
            // Make a deep copy of the current combination and add it to the result list
            result.add(new ArrayList<>(combination));
            return;
        }

        // Base case: if target becomes negative, return as no valid combination can be
        // formed
        if (target < 0) {
            return;
        }

        // Iterate over the candidates array starting from the given index
        for (int i = start; i < length; i++) {
            // Get the current candidate
            int candidate = candidates[i];

            // Add the current candidate to the ongoing combination list
            combination.add(candidate);

            // Recursive call to find combinations using the remaining target
            // Pass 'i' instead of '0' to allow reuse of the same candidate
            backtrack(result, combination, candidates, target - candidate, i, length);

            // Remove the last added candidate from the combination list to backtrack
            combination.remove(combination.size() - 1);
        }
    }

    // Main method to test combinationSum
    public static void main(String[] args) {
        int[] candidates = { 2, 3, 5 };
        int target = 8;

        List<List<Integer>> result = combinationSum(candidates, target);

        System.out.print("The list of all unique combinations of candidates " + Arrays.toString(candidates)
                + " where the chosen numbers sum to " + target + " is : " + result);
    }
}
