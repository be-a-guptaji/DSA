/*
LeetCode Problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

Question: 122. Best Time to Buy and Sell Stock II

Problem Statement: You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.

Constraints:
1 <= prices.length <= 3 * 10^4
0 <= prices[i] <= 10^4
*/

/*
Approach: Greedy (Peak-Valley Accumulation)

Goal:
- Maximize profit by performing as many buy-sell transactions
  as needed (cannot hold multiple stocks at once).

Core Idea:
- Capture every increasing price difference.
- Equivalent to buying at every local minimum
  and selling at every local maximum.
- Instead of explicitly tracking valleys and peaks,
  sum all positive consecutive differences.

Algorithm Steps:
1. Initialize totalProfit = 0.
2. Traverse prices array:
   - If prices[i] < prices[i+1]:
       → add (prices[i+1] - prices[i]) to profit.
3. Return totalProfit.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns maximum achievable profit with unlimited transactions.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the maximum profit you can achieve
  public int maxProfit(int[] prices) {
    // Initialize the total profit variable
    int totalProfit = 0;

    // Iterate over the prices array to find out the maximum profit
    for (int index = 0; index < prices.length - 1; index++) {
      // If holding is -1 then update it to the current index
      if (prices[index] < prices[index + 1]) {
        totalProfit += prices[index + 1] - prices[index];
      }
    }

    // Return the total profit
    return totalProfit;
  }
}

public class _122_Best_Time_to_Buy_and_Sell_Stock_II {
  // Main method to test maxProfit
  public static void main(String[] args) {
    int[] prices = new int[] { 1, 2, 3, 4, 5 };

    int result = new Solution().maxProfit(prices);

    System.out.println("The maximum profit you can achieve is : " + result);
  }
}
