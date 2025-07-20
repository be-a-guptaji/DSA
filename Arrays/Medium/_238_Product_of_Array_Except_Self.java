/*
LeetCode Problem: https://leetcode.com/problems/product-of-array-except-self/

Question: 238. Product of Array Except Self

Problem Statement: Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Constraints:

2 <= nums.length <= 10^5
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
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

public class _238_Product_of_Array_Except_Self {
    // Method to find all triplets that sum to zero
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

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

    // Main method to test productExceptSelf
    public static void main(String[] args) {
        int[] nums = { -1, 1, 0, -3, 3 };

        int[] result = productExceptSelf(nums);

        System.out.println("The product of all the elements of nums except nums[i] is : " + Arrays.toString(result));
    }
}
