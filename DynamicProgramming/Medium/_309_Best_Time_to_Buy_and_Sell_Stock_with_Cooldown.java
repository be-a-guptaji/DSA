/*
LeetCode Problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

Question: 309. Best Time to Buy and Sell Stock with Cooldown

Problem Statement: You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:
Input: prices = [1]
Output: 0 

Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
*/

/*
Approach: Dynamic Programming with Recursion and Memoization

Problem Summary: You can buy and sell a stock, but after selling, you have to wait 1 day (cooldown) before buying again.

States:
- At any day, you have two main choices:
  1. If you're allowed to buy → Either buy or skip.
  2. If you’ve already bought → Either sell (and cooldown next day) or skip.

We define:
- A recursive function with memoization to store results:
    dp[index][buying] → max profit from index with current buying status.
    - `buying == true` → we can buy at this index.
    - `buying == false` → we must sell or cooldown.

Transition:
- If buying:
    - Buy: profit = -prices[i] + dp[i+1][false]
    - Skip: profit = dp[i+1][true]
- If selling:
    - Sell: profit = prices[i] + dp[i+2][true] (skip next day)
    - Skip: profit = dp[i+1][false]

Base Case:
- If index >= prices.length → return 0

Time Complexity: O(n), where n is the number of days (since we memoize each state).
Space Complexity: O(n), for memoization.
*/

package DynamicProgramming.Medium;

public class _309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
  // Method to find the maximum profit
  public static int maxProfit(int[] prices) {
    // Make a dp matrix to memoize the values
    int[][] dp = new int[prices.length][2];

    // Initialize the dp matrix with the Integer.MIN_VALUE
    for (int[] arr : dp) {
      arr[0] = Integer.MIN_VALUE;
      arr[1] = Integer.MIN_VALUE;
    }

    // Make a recursive call and return the value
    return findMaxProfit(prices, dp, 0, true);
  }

  // Helper funtion to find the maximum profit
  private static int findMaxProfit(int[] prices, int[][] dp, int index, boolean buying) {
    // Handle the edge case
    if (index >= prices.length) {
      return 0;
    }

    // Get the index of the inner array
    int action = buying ? 0 : 1;

    // Check if the value is present in the dp matrix
    if (dp[index][action] != Integer.MIN_VALUE) {
      return dp[index][action];
    }

    // Find the cooldown value
    int cooldown = findMaxProfit(prices, dp, index + 1, buying);

    // Fill the dp matrix according to the buying value
    if (buying) {
      dp[index][action] = Math.max(findMaxProfit(prices, dp, index + 1, !buying) - prices[index],
          cooldown);
    } else {
      dp[index][action] = Math.max(findMaxProfit(prices, dp, index + 2, !buying) + prices[index],
          cooldown);
    }

    // Return the dp value
    return dp[index][action];
  }

  // Main method to test maxProfit
  public static void main(String[] args) {
    int[] prices = { 1, 2, 3, 0, 2 };

    int result = maxProfit(prices);

    System.out.println("The maximum profit is : " + result);
  }
}
