/*
LeetCode Problem: https://leetcode.com/problems/subsets-ii/

Question: 90. Subsets II

Problem Statement: Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */

/*
Approach:
1. We are given an integer array `nums` that may contain duplicate elements.
   The goal is to generate all unique subsets (the power set) without repetition.

2. To efficiently handle duplicates, we use a **frequency array**:
   - Each index represents a number in the range [-10, 10].
   - The value at each index represents how many times that number appears in `nums`.

3. The algorithm uses **backtracking** (DFS) to explore all combinations:
   - Start with an empty subset and move through the frequency array.
   - For each number that has a nonzero frequency:
       • Choose it (decrement its count and add it to the current subset).
       • Recursively continue exploring with the same index to allow duplicate usage.
       • After recursion, remove it and restore its frequency (backtrack).
   - Add every subset formed at any recursion depth to the result list.

4. By using frequency tracking and controlled recursion:
   - Each subset is unique.
   - Duplicate subsets are automatically avoided.
   - The order of elements within each subset remains consistent.

5. This approach effectively generates all unique subsets even when duplicates exist.

Time Complexity: O(2^N), where N = length of `nums`.
Space Complexity: O(N) for recursion depth and temporary subset storage.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _90_Subsets_II {
    // Method to find all possible unique subset
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        // Initialize a frequency array to store if the
        // number is used or not
        int[] frequency = new int[21];

        // Increment the value of the frequency index if the number is present in the
        // nums array
        for (int value : nums) {
            frequency[value + 10]++;
        }

        // Initialize the list to store all valid combinations
        List<List<Integer>> result = new ArrayList<>();

        // Start the recursive backtracking process to generate unique combinations
        backtrack(0, frequency, new ArrayList<>(), result);

        // Return the final list of unique combinations
        return result;
    }

    // Helper method to find all unique subset
    private static void backtrack(int index, int[] frequency, List<Integer> subset,
            List<List<Integer>> list) {
        // Base case if index is greater then the nums length then add the subset to the
        // list and return
        list.add(new ArrayList<>(subset));

        // Iterate over the nums array to generate subset
        for (int i = index; i < frequency.length; i++) {
            // If current frequency index is not 0 then do the calculation
            if (frequency[i] > 0) {
                // Get the number
                int num = i - 10;

                // Decrement the frequency index value
                frequency[i]--;

                // Add the current value index to the subset
                subset.add(num);

                // Backtrack when the index is choosen
                backtrack(i, frequency, subset, list);

                // Remove the current index form the subset
                subset.removeLast();

                // Reset the frequency index value
                frequency[i]++;
            }
        }
    }

    // Main method to test subsetsWithDup
    public static void main(String[] args) {
        int[] nums = { 1, 2, 2 };

        List<List<Integer>> result = subsetsWithDup(nums);

        System.out.print("The all possible unique subset of " + Arrays.toString(nums) + " is : " + result);
    }
}
