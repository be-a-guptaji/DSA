/*
LeetCode Problem: https://leetcode.com/problems/daily-temperatures/

Question: 739. Daily Temperatures

Problem Statement: Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]

Constraints:

1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100
 */

/*
  Approach: In the given solution, the approach computes the product of all elements in the array 
  except the current one by performing two linear passes without using division.

  The method initializes an output array `result`, where:
  - The first pass calculates the **prefix product** for each index — that is, the product of 
    all elements to the left of the current index. This is stored in the `result` array.

  - The second pass traverses the array from right to left, maintaining a running **suffix product** 
    of elements to the right of the current index. During this traversal, each `result[i]` is updated 
    by multiplying it with the current suffix product.

  This technique ensures that for every index `i`, result[i] contains the product of all elements 
  in the array except `nums[i]`.

  The solution avoids using division and extra data structures (like additional arrays or maps),
  making it efficient and space-optimized.

  Time Complexity:  O(n), where n is the length of the input array.
  Space Complexity: O(1), excluding the output array (as allowed by the problem constraints).
*/

package Arrays.Medium;

import java.util.Arrays;

public class _739_Daily_Temperatures {
    // Method to find all triplets that sum to zero
    public static int[] dailyTemperatures(int[] nums) {
        int n = nums.length;
        int[] result = new int[n],stack=new int[n];

        // Step 1: Calculate prefix products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Step 2: Multiply with suffix products
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        // Return the result
        return result;
    }

    // Main method to test dailyTemperatures
    public static void main(String[] args) {
        int[] nums = { -1, 1, 0, -3, 3 };

        int[] result = dailyTemperatures(nums);

        System.out.println("The product of all the elements of nums except nums[i] is : " + Arrays.toString(result));
    }
}
