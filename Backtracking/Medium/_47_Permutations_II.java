/*
LeetCode Problem: https://leetcode.com/problems/permutations-ii/

Question: 47. Permutations II

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
-10 <=  <= 10
All the integers of nums are unique.
 */

/*
Approach:
1. We are given an integer array `nums` that may contain duplicate elements, and we need to generate 
   all unique permutations of these numbers in any order.

2. To efficiently handle duplicates, we use a **frequency array** of fixed size (21 elements):
   - The index represents a number in the range [-10, 10].
   - Each index stores how many times that number appears in the input array.

3. The algorithm follows a **backtracking (DFS)** pattern:
   - At each recursion level, iterate through all possible numbers (from -10 to 10).
   - For every number that still has a positive frequency count:
       • Decrease its frequency (mark as used once).
       • Add the number to the current permutation.
       • Recursively call the function to continue building the permutation.
       • After recursion, remove the number (backtrack) and restore its frequency.

4. The recursion terminates when the current permutation’s size equals `nums.length`.
   - At that point, we’ve formed one valid unique permutation.
   - Add a deep copy of it to the result list.

5. This approach ensures uniqueness automatically because:
   - The same number is not reused beyond its actual count.
   - We never generate duplicate orderings from repeated elements.

6. Frequency-based recursion avoids redundant checks, sorting, or hash sets.

Time Complexity: O(N!) in the worst case, where N = length of `nums` (though less with duplicates).
Space Complexity: O(N) for recursion depth and current permutation list.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _47_Permutations_II {
    // Method to find all possible unique permutations in any order
    public static List<List<Integer>> permuteUnique(int[] nums) {
        // Initialize a frequency array to store if the number is used or not
        int[] frequency = new int[21];

        // Increment the value of the frequency index if the number is present in the
        // nums array
        for (int value : nums) {
            frequency[value + 10]++;
        }

        // Initialize the list to store all valid combinations
        List<List<Integer>> result = new ArrayList<>();

        // Start the recursive backtracking process to generate unique combinations
        backtrack(frequency, nums, result, new ArrayList<>());

        // Return the final list of unique combinations
        return result;
    }

    // Helper method to find all permutation
    private static void backtrack(int[] frequency, int[] nums, List<List<Integer>> list,
            List<Integer> currentPermutation) {
        // Base case if the combination length is equal to the nums length then add the
        // combination to the list and return
        if (currentPermutation.size() == nums.length) {
            list.add(new ArrayList<>(currentPermutation));
            return;
        }

        // Iterate over the nums array to make the currentPermutation
        for (int i = 0; i < frequency.length; i++) {
            // If the seen is true then do a recursive backtrack
            if (frequency[i] > 0) {
                // Get the number
                int num = i - 10;

                // Decrement the frequency index value
                frequency[i]--;

                // Add the number to the currentPermutation list
                currentPermutation.add(num);

                // Make the recursive call to make the currentPermutation
                backtrack(frequency, nums, list, currentPermutation);

                // Remove the last number to the currentPermutation list
                currentPermutation.removeLast();

                // Reset the frequency index value
                frequency[i]++;
            }
        }
    }

    // Main method to test permuteUnique
    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 };

        List<List<Integer>> result = permuteUnique(nums);

        System.out.print("The list of all possible unique permutations in any order of numbers" + Arrays.toString(nums)
                + " is : " + result);
    }
}
