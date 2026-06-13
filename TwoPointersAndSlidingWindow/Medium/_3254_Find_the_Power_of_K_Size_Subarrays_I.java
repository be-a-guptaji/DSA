/*
LeetCode Problem: https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/

Question: 3254. Find the Power of K-Size Subarrays I

Problem Statement: You are given an array of integers nums of length n and a positive integer k.

The power of an array is defined as:

Its maximum element if all of its elements are consecutive and sorted in ascending order.
-1 otherwise.
You need to find the power of all subarrays of nums of size k.

Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].

Example 1:
Input: nums = [1,2,3,4,3,2,5], k = 3
Output: [3,4,-1,-1,-1]
Explanation:
There are 5 subarrays of nums of size 3:
[1, 2, 3] with the maximum element 3.
[2, 3, 4] with the maximum element 4.
[3, 4, 3] whose elements are not consecutive.
[4, 3, 2] whose elements are not sorted.
[3, 2, 5] whose elements are not consecutive.

Example 2:
Input: nums = [2,2,2,2,2], k = 4
Output: [-1,-1]

Example 3:
Input: nums = [3,2,3,2,3,2], k = 2
Output: [-1,3,-1,3,-1]

Constraints:
1 <= n == nums.length <= 500
1 <= nums[i] <= 10^5
1 <= k <= n
*/

/*
Approach: Sliding Window on Consecutive Increasing Sequence

Goal:
- For every subarray of length k:
   - If elements are strictly consecutive
         nums[i+1] = nums[i] + 1
     throughout the window,
     result[i] = maximum element of the window.
   - Otherwise,
     result[i] = -1.

Core Idea:
- Maintain the longest current segment where
  consecutive elements differ by exactly 1.
- Let left denote the start of the current
  valid consecutive segment.
- Whenever the consecutive property breaks:
     all windows starting before the break
     become invalid.
- Mark those windows as -1.
- A window of size k is valid iff it lies
  completely inside a consecutive segment.

Algorithm Steps:
1. Initialize:
      left = 0
2. Traverse with right pointer:
   - If:
         nums[right] != nums[right-1] + 1
     then:
         mark pending windows as -1
         move left to right
3. Maintain window size ≤ k.
4. When window size becomes exactly k:
   - The window is fully consecutive.
   - Store:
         nums[right]
     (largest element in the window).
5. Continue until all elements are processed.
6. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(1)
  excluding output array

Result:
- Returns an array where each position contains:
   - the power of the corresponding length-k subarray
   - or -1 if the subarray is not strictly consecutive.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find an integer array results of size n - k + 1, where results[i]
  // is the power of nums[i..(i + k - 1)]
  public int[] resultsArray(int[] nums, int k) {
    // If nums length is equal to or k equal to 1 then return
    if (nums.length == 1 || k == 1) {
      return nums;
    }

    // Initialize the result array
    int[] result = new int[nums.length - k + 1];

    // Iterate over the nums array
    for (int left = 0, right = 1; right < nums.length; right++) {
      // If the current integer is not one more than the previous one than update the
      // nums value
      if ((nums[right - 1] + 1) != nums[right]) {
        // Update the nums array untill the left is less than right
        while (left < right && left < result.length) {
          result[left++] = -1;
        }
      }

      // If window length is more than k then update the left index
      if ((right - left + 1) > k) {
        left++;
      }

      // If window length is equal to k then update the nums array
      if ((right - left + 1) == k) {
        if (left < result.length) {
          result[left++] = nums[right];
        }
      }
    }

    // Return the result array
    return result;
  }
}

public class _3254_Find_the_Power_of_K_Size_Subarrays_I {
  // Main method to test resultsArray
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3, 4, 3, 2, 5 };
    int k = 3;

    int[] result = new Solution().resultsArray(nums, k);

    System.out.println(
        "An integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)] is : "
            + Arrays.toString(result));
  }
}
