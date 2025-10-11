/*
LeetCode Problem: https://leetcode.com/problems/climbing-stairs/

Question: 70. Climbing Stairs

Problem Statement: You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step 

Constraints:

1 <= n <= 45
*/

/*
Approach:
We use a dynamic programming strategy with memoization to solve the problem of finding the number of ways
to climb a staircase with 'n' steps, where at each step, you can climb either 1 or 2 steps.

Steps:
- Initialize a static array `total[]` to store the number of ways to reach each step (up to step 46).
- Use base cases:
    - total[0] = 0 (no step to climb)
    - total[1] = 1 (only one way to climb one step)
- Use a loop to precompute the number of ways to reach steps from 2 to 46:
    - total[i] = total[i - 1] + total[i - 2]
    - This is based on the idea that the number of ways to reach step `i` is the sum of the ways to reach
      steps `i-1` and `i-2`, similar to the Fibonacci sequence.
- Before computing, check if the values are already memoized (i.e., total[n] != 0) to avoid recomputation.
- Return total[n + 1], because we start counting from total[1] to match the step count with the logic.

Time Complexity: O(n) for the first call due to the loop, O(1) for subsequent calls (thanks to memoization).
Space Complexity: O(n), due to the additional array used to store computed values.
*/

package DynamicProgramming.Easy;

public class _70_Climbing_Stairs {
    // Initialize the array to memoization all the possible cases
    private final static int[] total = new int[47];

    // Method to find the total number of ways to climb a staircase by taking 1 or 2
    // steps
    public static int climbStairs(int n) {
        // Check if the nth index is filled in the array or not
        if (total[n] == 0) {
            // If not fill the values of the array

            // Base cases
            total[0] = 0;
            total[1] = 1;

            // Fill the array
            for (int i = 2; i < 47; i++) {
                total[i] = total[i - 1] + total[i - 2];
            }
        }

        // Return the memoized value
        return total[n + 1];
    }

    // Main method to test climbStairs
    public static void main(String[] args) {
        int n = 1;

        int result = climbStairs(n);

        System.out.println(
                "The total number of ways to climb a staircase with " + n
                        + " steps by taking 1 or 2 steps at a time is: " + result);
    }
}
