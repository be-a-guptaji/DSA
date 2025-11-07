/*
LeetCode Problem: https://leetcode.com/problems/permutations/

Question: 46. Permutations

Problem Statement: Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]

Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */

/*
Approach:
1. We are given an integer array `nums` and need to generate all possible unique permutations.
2. Each number must appear exactly once in every permutation.
3. To track which numbers are already used in the current permutation, we use a **boolean array `seen`**:
   - The array is offset by +10 to handle possible negative numbers in the range [-10, 10].
   - `seen[i + 10] = true` means the number `i` is available for use.
4. We apply a **backtracking (DFS)** approach:
   - At each step, iterate through all numbers in `nums`.
   - If a number is still available (`seen[index] == true`):
       • Mark it as used (`seen[index] = false`).
       • Add it to the current permutation (`combination`).
       • Recursively call the function to build the rest of the permutation.
       • After returning, remove the number and mark it available again (`seen[index] = true`).
5. The recursion stops when the current permutation’s length equals the input array length.
   - At that point, a valid permutation is found and added to the result list.
6. This process explores all possible orderings without repetition.

Time Complexity: O(N!) where N = length of nums (total permutations).
Space Complexity: O(N) for recursion depth and temporary combination storage.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _46_Permutations {
    // Method to find all the list of all unique combinations of numbers
    public static List<List<Integer>> permute(int[] nums) {
        // Initialize a frequency array to store if the number is used or not
        boolean[] seen = new boolean[21];

        // Set the seen index to true if it is in the nums
        for (int value : nums) {
            seen[value + 10] = true;
        }

        // Initialize the list to store all valid combinations
        List<List<Integer>> result = new ArrayList<>();

        // Start the recursive backtracking process to generate unique combinations
        backtrack(seen, nums, result, new ArrayList<>());

        // Return the final list of unique combinations
        return result;
    }

    // Helper method to find all permutation
    private static void backtrack(boolean[] seen, int[] nums, List<List<Integer>> list,
            List<Integer> combination) {
        // Base case if the combination length is equal to the nums length then add the
        // combination to the list and return
        if (combination.size() == nums.length) {
            list.add(new ArrayList<>(combination));
            return;
        }

        // Iterate over the nums array to make the combinations
        for (int num : nums) {
            // Get the index offset
            int index = num + 10;

            // If the seen is true then do a recursive backtrack
            if (seen[index]) {
                // Set the seen index to false
                seen[index] = false;

                // Add the number to the combination list
                combination.add(num);

                // Make the recursive call to make the combination
                backtrack(seen, nums, list, combination);

                // Remove the last number to the combination list
                combination.removeLast();

                // Reset the seen index to true
                seen[index] = true;
            }
        }
    }

    // Main method to test permute
    public static void main(String[] args) {
        int[] nums = { 10, 1, 2, 7, 6, 1, 5 };

        List<List<Integer>> result = permute(nums);

        System.out.print("The list of all unique combinations of numbers " + Arrays.toString(nums) + " is : "
                + result);
    }
}
