/*
LeetCode Problem: https://leetcode.com/problems/jump-game/

Question: 55. Jump Game

Problem Statement: You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5
*/

/*
Approach:
We solve the "Jump Game" problem using a Depth-First Search (DFS) approach with memoization.

Steps:
1. We initialize a boolean array `dp[]` of the same size as `nums`, with all values set to false.
   - Each index in `dp` keeps track of whether that index has been visited or leads to the end.
   
2. We use a recursive helper method `canReachEndOfArray(index, size, nums, dp)` that tries to explore all reachable indices from the current position:
   - Base Case:
     - If the current index is out of bounds, or already visited, or if the end is already reachable, we return early.
   - Mark the current index as visited by setting `dp[index] = true`.
   - Calculate the maximum reachable index from the current position: `maxJump = nums[index] + index`.
     - If `maxJump` is beyond or equal to the last index, we mark `dp[size - 1] = true` and return.
   - Otherwise, we iterate from the current index up to `maxJump`, recursively trying to reach the end from each of those indices (skipping those already visited).

3. After the DFS completes, we return the value of `dp[size - 1]`, which tells whether the last index is reachable.

Time Complexity: O(n^2) in the worst case (due to nested recursive calls), but often much better with memoization.
Space Complexity: O(n) for the `dp` array and recursion stack.
*/

package DynamicProgramming.Medium;

public class _55_Jump_Game {
    // Method to find if we can reach the end index of the array
    public static boolean canJump(int[] nums) {
        // Get the size of the nums array
        int size = nums.length;

        // Create a DP array initialized with false
        boolean[] dp = new boolean[size];

        // Make a call to find if we can reach the end index of the array
        canReachEndOfArray(0, size, nums, dp);

        // Return the last index of the dp
        return dp[size - 1];
    }

    // Helper method to find if we can reach the end index of the array
    private static void canReachEndOfArray(int index, int size, int[] nums, boolean[] dp) {
        // Edge case if the index is not out of bound or index is already visited
        if (index >= size || dp[index] || dp[size - 1]) {
            return;
        }

        // Make the dp index mark as true
        dp[index] = true;

        // Find the maxJump for the for loop
        int maxJump = nums[index] + index;

        // If maxJump is more than the size then return true
        if (maxJump >= size - 1) {
            dp[size - 1] = true;
            return;
        }

        // Find the jump for each index to the next index
        for (int i = index; i <= maxJump; i++) {
            if (!dp[Math.min(i, size - 1)]) {
                canReachEndOfArray(i, size, nums, dp);
            }
        }
    }

    // Main method to test canJump
    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };

        boolean result = canJump(nums);

        System.out.println("We can" + (result ? "" : "not") + " reach the end index of the array.");
    }
}
