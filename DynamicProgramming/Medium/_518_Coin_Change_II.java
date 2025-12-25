/*
LeetCode Problem: https://leetcode.com/problems/coin-change-ii/

Question: 518. Coin Change II

Problem Statement: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
*/

/*
Approach: Dynamic Programming – Unbounded Knapsack (Coin Change II)

Goal:
Find the number of combinations that make up a given amount using unlimited coins.
Order of coins does NOT matter.

DP Definition:
- dp[x] = number of ways to make amount x.

Initialization:
- dp[0] = 1
  (There is exactly one way to make amount 0: choose no coins.)

Algorithm:
1. Iterate over each coin:
   - This ensures combinations are counted (not permutations).
2. For each coin, iterate amount from 1 to target:
   - If coin ≤ current amount:
       dp[j] += dp[j - coin]

Why It Works:
- Processing coins one by one avoids duplicate ordering.
- dp[j - coin] represents all ways to form the remaining amount after choosing the coin.

Time Complexity: O(n × amount)
Space Complexity: O(amount)
*/

package DynamicProgramming.Medium;

public class _518_Coin_Change_II {
  // Method to find the number of combinations that make up that amount
  public static int change(int amount, int[] coins) {
    // Initalize the dp array of size amount + 1
    int[] dp = new int[amount + 1];

    // Set the dp[0] to 1
    dp[0] = 1;

    // Iterate over the coins
    for (int i = 0; i < coins.length; i++) {
      // Fill the dp array to how many ways it can be filled
      for (int j = 1; j <= amount; j++) {
        // Fill the dp array
        dp[j] = dp[j] + (coins[i] <= j ? dp[j - coins[i]] : 0);
      }
    }

    // Return the dp[amount] as result
    return dp[amount];
  }

  // Main method to test change
  public static void main(String[] args) {
    int amount = 5;
    int[] coins = { 1, 2, 5 };

    int result = change(amount, coins);

    System.out.println("The number of combinations that make up " + amount + " is : " + result);
  }
}
