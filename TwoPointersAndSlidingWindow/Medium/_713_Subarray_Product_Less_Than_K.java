/*
LeetCode Problem: https://leetcode.com/problems/subarray-product-less-than-k/

Question: 713. Subarray Product Less Than K

Problem Statement: Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Example 2:
Input: nums = [1,2,3], k = 0
Output: 0

Constraints:
1 <= nums.length <= 3 * 10^4
1 <= nums[i] <= 1000
0 <= k <= 10^6
*/

/*
Approach: Sliding Window

Goal:
- Count the number of contiguous subarrays whose
  product is strictly less than k.

Core Idea:
- Since all nums[i] are positive:
   - Expanding the window increases (or keeps)
     the product.
   - Shrinking the window decreases the product.
- This monotonic property allows a sliding window.

Key Observation:
- When window [left, right] has:
      product < k
  then every subarray ending at right and starting
  from any index in:
      [left, right]
  also has product < k.

- Number of such subarrays:
      right - left + 1

Algorithm Steps:
1. Maintain:
      currentProduct
      left pointer
2. Expand right:
      currentProduct *= nums[right]
3. While:
      currentProduct >= k
   shrink window:
      currentProduct /= nums[left]
      left++
4. Window is now valid.
5. Add:
      right - left + 1
   to the answer.
6. Continue until the array ends.
7. Return the total count.

Why It Works:
- After shrinking, every subarray ending at right
  and beginning within the current window has
  product < k.
- Counting them in bulk avoids enumerating all
  subarrays individually.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Important Edge Case:
- If k <= 1:
      return 0
  because all numbers are positive and no product
  can be strictly less than 1.

Result:
- Returns the number of subarrays whose product
  is strictly less than k.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the elements in the subarray is strictly less than k
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    // Initialize the numSubarrayProductLessThanK variable
    int numSubarrayProductLessThanK = 0;

    // Initialize the currentProduct variable
    long currentProduct = 1;

    // Iterate over the nums array
    for (int left = 0, right = 0; right < nums.length; right++) {
      // Update the currentProduct
      currentProduct *= nums[right];

      // While currentProduct is greater than k then remove the left index
      while (currentProduct >= k && left <= right) {
        // Update the currentProduct
        currentProduct /= nums[left];

        // Increment the left pointer
        left++;
      }

      // Update the numSubarrayProductLessThanK
      numSubarrayProductLessThanK += (right - left + 1);
    }

    // Return the numSubarrayProductLessThanK
    return numSubarrayProductLessThanK;
  }
}

public class _713_Subarray_Product_Less_Than_K {
  // Main method to test numSubarrayProductLessThanK
  public static void main(String[] args) {
    int[] nums = new int[] { 10, 5, 2, 6 };
    int k = 100;

    int result = new Solution().numSubarrayProductLessThanK(nums, k);

    System.out.println("The elements in the subarray is strictly less than k is : " + result);
  }
}
