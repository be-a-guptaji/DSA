/*
LeetCode Problem: https://leetcode.com/problems/continuous-subarray-sum/

Question: 523. Continuous Subarray Sum

Problem Statement: Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.

A good subarray is a subarray where:

its length is at least two, and
the sum of the elements of the subarray is a multiple of k.
Note that:

A subarray is a contiguous part of the array.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Example 1:
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false

Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
0 <= sum(nums[i]) <= 2^31 - 1
1 <= k <= 2^31 - 1
*/

/*
Approach: Prefix Sum + Modulo HashMap

Goal:
- Determine if there exists a subarray of length ≥ 2
  whose sum is a multiple of k.

Core Idea:
- If two prefix sums have the same remainder modulo k,
  their difference is divisible by k.
- Store first occurrence of each remainder.
- If the same remainder appears again with distance > 1,
  a valid subarray exists.

Algorithm Steps:
1. Initialize HashMap:
   - key = prefixSum % k
   - value = earliest index
2. Put (0 → -1) to handle subarrays starting at index 0.
3. Traverse array:
   - prefixSum += nums[i]
   - mod = prefixSum % k
4. If mod not in map:
   - store index.
5. Else:
   - if (i - previousIndex > 1) → return true.
6. If no valid subarray found → return false.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns true if such subarray exists, otherwise false.
*/

package Arrays.Medium;

import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find if nums has a good subarray
  public boolean checkSubarraySum(int[] nums, int k) {
    // Initialize the hash map
    HashMap<Integer, Integer> modMap = new HashMap<>();

    // Set zero to negative one
    modMap.put(0, -1);

    // Initialize the prefixSum variable for the sum
    int prefixSum = 0;

    // Fill the hash map and check if has a good subarray
    for (int i = 0; i < nums.length; i++) {
      // Update the prefixSum
      prefixSum += nums[i];

      // Find the mod of the prefixSum
      int mod = prefixSum % k;

      // Update the hashmap
      if (!modMap.containsKey(mod)) {
        modMap.put(mod, i);
      } else if (i - modMap.get(mod) > 1) {
        return true;
      }
    }

    // Return false in the end
    return false;
  }
}

public class _523_Continuous_Subarray_Sum {
  // Main method to test checkSubarraySum
  public static void main(String[] args) {
    int[] nums = { 3, 30, 34, 5, 9 };
    int k = 3;

    boolean result = new Solution().checkSubarraySum(nums, k);

    System.out
        .println("The nums array has" + (result ? " " : " not ") + "a good subarray.");
  }
}
