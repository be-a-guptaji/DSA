/*
LeetCode Problem: https://leetcode.com/problems/maximum-product-difference-between-two-pairs/

Question: 1913. Maximum Product Difference Between Two Pairs

Problem Statement: The product difference between two pairs (a, b) and (c, d) is defined as (a * b) - (c * d).

For example, the product difference between (5, 6) and (2, 7) is (5 * 6) - (2 * 7) = 16.
Given an integer array nums, choose four distinct indices w, x, y, and z such that the product difference between pairs (nums[w], nums[x]) and (nums[y], nums[z]) is maximized.

Return the maximum such product difference.

Example 1:
Input: nums = [5,6,2,7,4]
Output: 34
Explanation: We can choose indices 1 and 3 for the first pair (6, 7) and indices 2 and 4 for the second pair (2, 4).
The product difference is (6 * 7) - (2 * 4) = 34.

Example 2:
Input: nums = [4,2,5,9,7,4,8]
Output: 64
Explanation: We can choose indices 3 and 6 for the first pair (9, 8) and indices 1 and 5 for the second pair (2, 4).
The product difference is (9 * 8) - (2 * 4) = 64.

Constraints:

4 <= nums.length <= 10^4
1 <= nums[i] <= 10^4
 */

/*
Approach: Single-Pass Extreme Value Tracking

Goal:
Compute the maximum product difference:
(max1 × max2) − (min1 × min2)

Key Idea:
Track the two largest and two smallest numbers in a single traversal.

Algorithm:
1. Initialize:
   - min1, min2 = +∞
   - max1, max2 = −∞
2. Traverse the array:
   - Update min1 and min2 when a smaller number is found.
   - Update max1 and max2 when a larger number is found.
3. Compute and return:
   (max1 × max2) − (min1 × min2)

Why It Works:
- The maximum product comes from the two largest numbers.
- The minimum product comes from the two smallest numbers.
- A single pass is sufficient to identify all four values.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1913_Maximum_Product_Difference_Between_Two_Pairs {
    // Method to find the maximum product difference
    public static int maxProductDifference(int[] nums) {
        // Initialize the four varibale for getting the values
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, second_min = Integer.MAX_VALUE,
                second_max = Integer.MIN_VALUE;

        // Iterate over the nums array
        for (int num : nums) {
            // Find the minimum value
            if (num < min) {
                second_min = min;
                min = num;
            } else if (num < second_min) {
                second_min = num;
            }

            // Find the maximum value
            if (num > max) {
                second_max = max;
                max = num;
            } else if (num > second_max) {
                second_max = num;
            }
        }

        // Return the product difference max and min values
        return max * second_max - min * second_min;
    }

    // Main method to test maxProductDifference
    public static void main(String[] args) {
        int[] nums = new int[] { 4, 2, 5, 9, 7, 4, 8 };

        int result = maxProductDifference(nums);

        System.out.println("The maximum product difference is : " + result);
    }
}
