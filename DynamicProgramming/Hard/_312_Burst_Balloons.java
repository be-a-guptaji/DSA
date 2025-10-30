/*
LeetCode Problem: https://leetcode.com/problems/burst-balloons/

Question: 312. Burst Balloons

Problem Statement: You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

Example 1:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

Example 2:
Input: nums = [1,5]
Output: 10

Constraints:

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100
 */

/*
Approach:
This problem is solved using recursion with memoization (top-down dynamic programming).

We use the idea that bursting the last balloon in a subarray [l, r] divides the problem
into two independent subproblems: [l, i-1] and [i+1, r].

Key ideas:
1. Add virtual balloons with value 1 at both ends of the array for easier boundary handling.
2. Define `dfs(l, r)` as the maximum coins obtainable by bursting all balloons
   between indices `l` and `r` (inclusive) in the new array.
3. For each balloon `i` between `l` and `r`, assume it’s the last one to burst:
   - Coins gained = nums[l - 1] * nums[i] * nums[r + 1]
   - Plus coins from subproblems: dfs(l, i - 1) and dfs(i + 1, r)
4. Use memoization (`dp[l][r]`) to store already computed results to avoid redundant recursion.

Recurrence relation:
    dp[l][r] = max(
        dp[l][r],
        dfs(l, i - 1) + nums[l - 1] * nums[i] * nums[r + 1] + dfs(i + 1, r)
    ) for all i in [l, r]

Base case:
    If l > r → return 0

Time Complexity: O(n³)
Space Complexity: O(n²)
where n = number of balloons
*/

package DynamicProgramming.Hard;

import java.util.Arrays;

public class _312_Burst_Balloons {
    // Method to find the maximum coins you can collect by bursting the balloons
    // wisely
    public static int maxCoins(int[] nums) {
        // Update the nums array to be 1 at the beginning and 1 at the end
        int[] newNums = new int[nums.length + 2];

        newNums[0] = 1;
        System.arraycopy(nums, 0, newNums, 1, nums.length);
        newNums[newNums.length - 1] = 1;

        // Make a dp matrix for the memoization
        int[][] dp = new int[nums.length + 2][nums.length + 2];

        // Fill the dp array with the -1
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        // Return the dfs recursive function
        return dfs(1, nums.length, newNums, dp);
    }

    // Helper method to find the maximum coins
    private static int dfs(int l, int r, int[] nums, int[][] dp) {
        // Edge case if left is greater than right then return 0
        if (l > r) {
            return 0;
        }

        // If we already computed the value then return the chache value
        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        // Initialize the result variable
        int result = 0;

        // Iterate over the nums array and get the coins
        for (int i = l; i <= r; i++) {
            result = Math.max(result,
                    dfs(l, i - 1, nums, dp) + nums[l - 1] * nums[i] * nums[r + 1] + dfs(i + 1, r, nums, dp));
        }

        // Return the dp[l][r] value
        return dp[l][r] = result;
    }

    // Main method to test maxCoins
    public static void main(String[] args) {
        int[] nums = { 3, 1, 5, 8 };

        int result = maxCoins(nums);

        System.out.println("The maximum coins you can collect by bursting the balloons wisely is : " + result);
    }
}
