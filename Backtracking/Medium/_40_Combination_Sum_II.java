/*
LeetCode Problem: https://leetcode.com/problems/combination-sum-ii/

Question: 40. Combination Sum II

Problem Statement: Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]

Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 */

/*
Approach:
1. We are given an array of integers (candidates) and a target integer.
2. The goal is to find all unique combinations where the chosen numbers sum up to the target.
   - Each candidate can be used only as many times as it appears in the input array (no repetition beyond frequency).
3. To efficiently handle duplicate values, we use a **frequency array** to record how many times each number occurs.
4. We use a **backtracking (DFS)** approach to explore all valid combinations:
   - Start from a given index in the frequency array.
   - For each number `i` that has a non-zero frequency:
       • Decrease its frequency count.
       • Add it to the current combination.
       • Recursively explore further with the reduced target (`target - i`).
       • After recursion, remove the number and restore its frequency to backtrack.
   - This ensures that each combination uses numbers in non-decreasing order, avoiding duplicates.
5. The recursion stops when:
   - The target becomes zero → valid combination found (add to result list).
   - The target becomes negative → invalid path, return immediately.
6. Frequency-based representation eliminates the need for sorting or duplicate checking using sets.

Time Complexity: O(2^N) in the worst case, where N = number of unique candidate values.
Space Complexity: O(T) for recursion depth and temporary combination storage.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _40_Combination_Sum_II {
    // Method to find all unique combinations of candidates where the chosen numbers
    // sum up to the target, with no repetition of candidates allowed
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // Initialize the list to store all valid combinations
        List<List<Integer>> res = new ArrayList<>();

        // Initialize a frequency array to store the count of each candidate
        int[] frequency = new int[51];

        // Fill the frequency array
        for (int num : candidates) {
            frequency[num]++;
        }

        // Start the recursive backtracking process to generate combinations
        backtrack1(frequency, target, res, new ArrayList<>(), 0);

        // Return the final list of unique combinations
        return res;
    }

    // Helper recursive method to generate valid combinations that sum to the target
    private static void backtrack1(int[] frequency, int target, List<List<Integer>> res, List<Integer> currComb,
            int idx) {
        // Base case: if the remaining target becomes negative, stop further recursion
        if (target < 0)
            return;

        // Base case: if the remaining target becomes zero, a valid combination is found
        if (target == 0) {
            // Add a deep copy of the current combination to the result list
            res.add(new ArrayList<>(currComb));
            return;
        }

        // Iterate over all possible numbers starting from the current index
        for (int i = idx; i < frequency.length && i <= target; i++) {

            // Check if the current number is available (frequency > 0)
            if (frequency[i] > 0) {

                // Decrease the frequency count for the current number
                frequency[i]--;

                // Include the current number in the ongoing combination
                currComb.add(i);

                // Recursive call with reduced target and same index
                // (allows use of the same number until frequency is exhausted)
                backtrack1(frequency, target - i, res, currComb, i);

                // Remove the last added number to backtrack and explore other possibilities
                currComb.removeLast();

                // Restore the frequency count for the current number
                frequency[i]++;
            }
        }
    }

    // Main method to test combinationSum2
    public static void main(String[] args) {
        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;

        List<List<Integer>> result = combinationSum2(candidates, target);

        System.out.print("The list of all unique combinations of candidates " + Arrays.toString(candidates)
                + " where the chosen numbers sum to " + target + " is and no repetation of candidates are allowed is : "
                + result);
    }
}
