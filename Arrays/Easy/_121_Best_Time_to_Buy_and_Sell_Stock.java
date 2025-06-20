/*
LeetCode Problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock

Question: 121. Best Time to Buy and Sell Stock

Problem Statement: You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
 */

/*
Approach: The solution uses a single pass through the prices array to find the minimum price seen so far and calculates the maximum profit that can be achieved by selling on each day after buying at the minimum price.

Time Complexity: O(n), where n is the number of days (length of the prices array). We traverse the array once to find the minimum price and calculate the maximum profit.
Space Complexity: O(1), as we are using only a constant amount of extra space for variables to track the minimum price and maximum profit.
 */

package Arrays.Easy;

public class _121_Best_Time_to_Buy_and_Sell_Stock {

    // Method to find the maximum profit from stock prices
    public static int maxProfit(int[] prices) {
        // Initialize variables to track minimum price and maximum profit
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        // Iterate through the prices array
        for (int price : prices) {
            // Update minPrice if the current price is lower
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate profit if selling at the current price
            int profit = price - minPrice;

            // Update maxProfit if the calculated profit is higher
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        // Return the maximum profit found
        return maxProfit;
    }

    // Main method to test twoSum
    public static void main(String[] args) {
        int[] prices = { 2, 7, 11, 15 };

        int result = maxProfit(prices);

        if (result != 0) {
            System.out.println("Maximum profit: " + result);
        } else {
            System.out.println("No profit can be made.");
        }
    }
}
