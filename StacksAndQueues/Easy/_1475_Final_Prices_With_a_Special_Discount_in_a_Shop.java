/*
LeetCode Problem: https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/

Question: 1475. Final Prices With a Special Discount in a Shop

Problem Statement: You are given an integer array prices where prices[i] is the price of the ith item in a shop.

There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.

Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop, considering the special discount.

Example 1:
Input: prices = [8,4,6,2,3]
Output: [4,2,4,2,3]
Explanation: 
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
For items 3 and 4 you will not receive any discount at all.

Example 2:
Input: prices = [1,2,3,4,5]
Output: [1,2,3,4,5]
Explanation: In this case, for all items, you will not receive any discount at all.

Example 3:
Input: prices = [10,1,1,6]
Output: [9,0,1,6]

Constraints:

1 <= prices.length <= 500
1 <= prices[i] <= 1000
 */

/*
Approach: Monotonic Stack (Next Smaller or Equal Element to the Right)

Goal:
- For each price[i], find the first price[j] such that:
      j > i and prices[j] <= prices[i]
- Subtract prices[j] from prices[i].
- If no such j exists, price remains unchanged.

Key Idea:
- Use a monotonic increasing stack storing indices.
- The stack keeps indices whose discount has not yet been found.
- When we find a smaller or equal price, we resolve discounts.

Algorithm:

1. Initialize:
   - stack[] to store indices
   - stackPointer = 0
   - discountPrices[] result array

2. Iterate from left to right:

   For each index:
   - While stack not empty AND
       prices[stackTop] >= prices[current]:
       → discountPrices[stackTop] =
             prices[stackTop] - prices[current]
       → pop stack

   - Push current index onto stack

3. After traversal:
   - Remaining indices in stack have no discount.
   - Their final price remains unchanged.

Why This Works:
- Each element is pushed once and popped once.
- Stack ensures we always match the nearest smaller element.
- Efficient next-smaller-element pattern.

Important Note:
- stackPointer should start at 0 (not 1).

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns array of final discounted prices.
*/

package StacksAndQueues.Easy;

import java.util.Arrays;

public class _1475_Final_Prices_With_a_Special_Discount_in_a_Shop {
    // Method to find the discounted prices of all the items in the shop
    public static int[] finalPrices(int[] prices) {
        // Initialize the lenght of the prices
        int length = prices.length;

        // Initialize the stack of the same size of the prices
        int[] stack = new int[length];

        // Initialize the discount prices of the same size of the prices
        int[] discountPrices = new int[length];

        // Initialize the stack pointer
        int stackPointer = 1;

        // Iterate over the prices to find the discount prices
        for (int index = 1; index < length; index++) {
            while (stackPointer > 0 && prices[stack[stackPointer - 1]] >= prices[index]) {
                // Update the discounted price
                discountPrices[stack[stackPointer - 1]] = prices[stack[stackPointer - 1]] - prices[index];

                // Decrement the stack pointer
                stackPointer--;
            }

            // Add the current index to the stack
            stack[stackPointer++] = index;
        }

        // Fill the remaining index form the stack to the discounted prices
        while (stackPointer > 0) {
            // Update the discounted price
            discountPrices[stack[stackPointer - 1]] = prices[stack[stackPointer - 1]];

            // Decrement the stack pointer
            stackPointer--;
        }

        // Return the discount prices
        return discountPrices;
    }

    // Main method to test finalPrices
    public static void main(String[] args) {
        int[] prices = new int[] { 8, 4, 6, 2, 3 };

        int[] result = finalPrices(prices);

        System.out.println("The discounted prices of all the items in the shop is : " + Arrays.toString(result));
    }
}
