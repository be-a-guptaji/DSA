/*
LeetCode Problem: https://leetcode.com/problems/subarray-sums-divisible-by-k/

Question: 974. Subarray Sums Divisible by K

Problem Statement: Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:
Input: nums = [5], k = 9
Output: 0

Constraints:
1 <= nums.length <= 3 * 10^4
-10^4 <= nums[i] <= 10^4
2 <= k <= 10^4
*/

/*
Approach: Prefix Sum + Modulo Frequency Counting

Goal:
- Count the number of subarrays whose sum is divisible by k.

Core Idea:
- If two prefix sums have the same remainder modulo k,
  their difference is divisible by k.
- Maintain frequency of each remainder.
- For each prefix, add how many times the same remainder
  has appeared before.

Algorithm Steps:
1. Initialize frequency array of size k.
2. Set frequency[0] = 1 (handles subarrays starting at index 0).
3. Initialize prefix = 0, count = 0.
4. Traverse array:
   - Update prefix:
       prefix = (prefix + nums[i]) % k
       ensure non-negative using (prefix + k) % k
   - Add frequency[prefix] to count.
   - Increment frequency[prefix].
5. Return count.

Time Complexity:
- O(n)

Space Complexity:
- O(k)

Result:
- Returns number of subarrays with sum divisible by k.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the number of non-empty subarrays that have a sum divisible by
  // k
  public int subarraysDivByK(int[] nums, int k) {
    // Initialize the array of length equal to k
    int[] frequencyMap = new int[k];

    // Initialize frequencyMap[0] to 1
    frequencyMap[0] = 1;

    // Initialize the prefix and totalNumberOfSubarrays variable
    int prefix = 0, totalNumberOfSubarrays = 0;

    // Iterate over the nums array for the sum
    for (int i = 0; i < nums.length; i++) {
      prefix = (prefix + nums[i] % k + k) % k;
      totalNumberOfSubarrays += frequencyMap[prefix];
      frequencyMap[prefix]++;
    }

    // Return the totalNumberOfSubarrays
    return totalNumberOfSubarrays;
  }
}

public class _974_Subarray_Sums_Divisible_by_K {
  // Main method to test subarraysDivByK
  public static void main(String[] args) {
    int[] nums = new int[] { 4, 5, 0, -2, -3, 1 };
    int k = 5;

    int result = new Solution().subarraysDivByK(nums, k);

    System.out.println("The number of non-empty subarrays that have a sum divisible by " + k + " is : " + result);
  }
}
