/*
LeetCode Problem: https://leetcode.com/problems/online-stock-span/

Question: 901. Online Stock Span

Problem Statement: Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.

Example 1:
Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]
Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6

Constraints:

1 <= price <= 10^5
At most 10^4 calls will be made to next.
 */

/*
Approach: Backward Jump Using Stored Spans (Optimized Traversal)

1. Data Structure:
   - Maintain an ArrayList of PriceSpan objects.
   - Each PriceSpan stores:
        • price  → stock price of that day
        • span   → number of consecutive previous days 
                   (including itself) where price <= current price

2. Core Idea:
   - For each new price:
        a) Start checking from the last inserted price (end of list).
        b) Move backward while previous prices are <= current price.
        c) Instead of moving one step at a time,
           jump backward using the stored span:
              index = index - previousSpan
        d) This skips already validated blocks in O(1) time.
        e) Compute span as:
              span = currentSize - index
        f) Store (price, span) in the list.

3. Optimization Insight:
   - Previously computed spans allow skipping multiple indices at once.
   - Each element contributes to at most one backward jump.
   - This avoids repeated comparisons.

4. Complexity:
   - Time: O(1) amortized per next() call
   - Space: O(n) for storing price-span pairs
*/

package StacksAndQueues.Medium;

import java.util.ArrayList;

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

class StockSpanner {
    // Initialize the new class for holding the price and the span for that price
    class PriceSpan {
        // Initialize the price and the span variable
        final int price, span;

        public PriceSpan(int price, int span) {
            // Initialize the price and the span
            this.price = price;
            this.span = span;
        }
    }

    // Initialize the array list for the list of prices
    final ArrayList<PriceSpan> pricesSpans;

    public StockSpanner() {
        // Initialize the array list
        this.pricesSpans = new ArrayList<>();
    }

    public int next(int price) {
        // Initialize the index variable
        int index;

        // Iterate over the array list to find the span
        for (index = this.pricesSpans.size() - 1; index >= 0; index--) {
            // Get the PriceSpan of the index
            PriceSpan currentPriceSpan = this.pricesSpans.get(index);

            // If current price span's price is less than or equal to the price then update
            // the index else break out of the loop
            if (currentPriceSpan.price <= price) {
                index = index - currentPriceSpan.span + 1;
            } else {
                break;
            }
        }

        // Get the span for the current price
        int span = this.pricesSpans.size() - index;

        // Add PriceSpan class to the array list
        this.pricesSpans.add(new PriceSpan(price, span));

        // Return the span
        return span;
    }
}

// Driver Class for the testing the StockSpanner Class
public class _901_Online_Stock_Span {
    // Main method to test the StockSpanner
    public static void main(String[] args) {
        String[] operations = { "StockSpanner", "next", "next", "next", "next", "next", "next", "next" };

        int[][] value = { {}, { 100 }, { 80 }, { 60 }, { 70 }, { 60 }, { 75 }, { 85 } };

        // Instantiate the StockSpanner Class
        StockSpanner obj = new StockSpanner();

        System.out.println("null");

        for (int i = 1; i < operations.length; i++) {
            System.out.println(obj.next(value[i][0]));
        }
    }
}