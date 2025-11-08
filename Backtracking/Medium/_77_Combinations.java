/*
LeetCode Problem: https://leetcode.com/problems/combinations/

Question: 77. Combinations

Problem Statement: Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

Example 2:
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.

Constraints:

1 <= n <= 20
1 <= k <= n
 */

/*
Approach:
1. We are given two integers `n` and `k`:
   - `n` represents the range of numbers from 1 to n.
   - `k` represents how many numbers we need to select from that range.
   The goal is to generate all possible combinations of `k` distinct numbers chosen from [1, n].

2. We use a **backtracking approach** to explore all possible selections of numbers.

3. The idea:
   - Maintain a current combination (`subset`) and build it incrementally.
   - At each recursive call:
       • Iterate over the available numbers starting from a given index to ensure combinations are generated in increasing order.
       • Add a number to the current subset.
       • Recurse to choose the next number.
       • After recursion, remove the last number (backtrack) to explore other possibilities.
   - When the size of the current subset reaches `k`, add it to the result list.

4. The `frequency` array (size 21 here) is used to simulate the available range [1, n].
   - Only numbers from 1 to n are marked as available (frequency[i] = 1).
   - During recursion, a number’s frequency is temporarily decreased to indicate it has been used.

5. This ensures:
   - No number is repeated in a combination.
   - The order of numbers within each combination is strictly increasing.
   - Each combination of `k` numbers is unique.

6. Optimization:
   - The loop can be limited to `i <= n` since the range ends at `n`.
   - When we call `backtrack(i + 1, ...)` instead of `backtrack(i, ...)`, we avoid reusing the same number.

Time Complexity: O(C(n, k)) → total number of valid combinations.
Space Complexity: O(k) → recursion depth and temporary combination storage.
*/

package Backtracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class _77_Combinations {
    // Method to find all possible combinations of k numbers chosen from the range n
    public static List<List<Integer>> combine(int n, int k) {
        // Initialize a frequency array to store if the
        // number is used or not
        int[] frequency = new int[21];

        // Increment the value of the frequency index to the range n
        for (int i = 1; i <= n; i++) {
            frequency[i]++;
        }

        // Initialize the list to store all valid combinations
        List<List<Integer>> result = new ArrayList<>();

        // Start the recursive backtracking process to generate unique combinations
        backtrack(0, k, frequency, new ArrayList<>(), result);

        // Return the final list of unique combinations
        return result;
    }

    // Helper method to find all unique subset
    private static void backtrack(int index, int k, int[] frequency, List<Integer> subset,
            List<List<Integer>> list) {
        // Base case if index is equal to k then add the subset to the list and return
        if (subset.size() == k) {
            list.add(new ArrayList<>(subset));
            return;
        }

        // Iterate over the nums array to generate subset
        for (int i = index; i < frequency.length; i++) {
            // If current frequency index is not 0 then do the calculation
            if (frequency[i] > 0) {
                // Decrement the frequency index value
                frequency[i]--;

                // Add the current value index to the subset
                subset.add(i);

                // Backtrack when the index is choosen
                backtrack(i, k, frequency, subset, list);

                // Remove the current index form the subset
                subset.removeLast();

                // Reset the frequency index value
                frequency[i]++;
            }
        }
    }

    // Main method to test combine
    public static void main(String[] args) {
        int n = 4, k = 2;

        List<List<Integer>> result = combine(n, k);

        System.out.print(
                "The all possible combinations of " + k + " numbers chosen from the range [1, " + n + "] is : "
                        + result);
    }
}
