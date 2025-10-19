/*
LeetCode Problem: https://leetcode.com/problems/coin-change/

Question: 322. Coin Change

Problem Statement: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4
*/

/*
Approach: We use **Bottom-Up Dynamic Programming (Tabulation)** to find the minimum number of coins needed to make up a given amount.

1. Create a `dp` array of size `amount + 1` where `dp[i]` represents the fewest number of coins needed to make amount `i`.
2. Initialize all values in `dp` with a large number (`amount + 1`) to represent an unreachable state.
3. Set `dp[0] = 0`, since 0 coins are needed to make amount 0.
4. For each amount `i` from 1 to `amount`, iterate through each coin in `coins`:
   - If `coin <= i`, update `dp[i] = min(dp[i], dp[i - coin] + 1)`
   - This means we are trying to make amount `i` by adding one coin to a smaller sub-amount `i - coin`.
5. After filling the dp array, if `dp[amount]` is still greater than `amount`, return -1 (amount cannot be formed with given coins).
6. Otherwise, return `dp[amount]`.

Time Complexity: O(n * amount), where `n` is the number of coins.
Space Complexity: O(amount), for the dp array.
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

public class _322_Coin_Change {
  // Method to find the fewest number of coins that you need to make up that
  // amount
  public static int coinChange(int[] coins, int amount) {
    // Initialize the size of the amount for the unreachable state
    int size = amount + 1;

    // Initialize a dp array for the combination values
    int[] dp = new int[size];

    // Fill with a high value to represent unreachable states
    Arrays.fill(dp, size);

    // Set dp[0] to 0
    dp[0] = 0;

    // Do a bottom up Breadth First Search
    for (int i = 1; i <= amount; i++) {
      // Make a BFS on all the possible combinations
      for (int coin : coins) {
        // Get the dummyAmount
        int dummyAmount = i - coin;

        // Update the dp table if the dummyAmount is not negative
        if (dummyAmount >= 0) {
          dp[i] = Math.min(dp[dummyAmount] + 1, dp[i]);
        }
      }
    }

    // If dp[amount] wasn't updated, return -1
    return dp[amount] > amount ? -1 : dp[amount];
  }

  // Main method to test coinChange
  public static void main(String[] args) {
    int[] coins = { 5, 2, 1 };
    int amount = 11;

    int result = coinChange(coins, amount);

    System.out.println("The fewest number of coins that you need to make " + amount + " is : " + result);
  }
}
