/*
LeetCode Problem: https://leetcode.com/problems/subarray-sum-equals-k/

Question: 560. Subarray Sum Equals K

Problem Statement: Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2

Constraints:
1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7
*/

/*
Approach: Prefix Sum + HashMap (Subarray Sum Counting)

Goal:
- Count the number of subarrays whose sum equals k.

Core Idea:
- Use prefix sums:
    sum[i] = nums[0] + ... + nums[i]
- A subarray (j+1 → i) has sum k if:
    sum[i] - sum[j] = k
  → sum[j] = sum[i] - k
- Use a HashMap to store frequency of prefix sums.

Algorithm Steps:
1. Initialize prefixMap with {0: 1}.
2. Initialize currentSum = 0, count = 0.
3. Traverse array:
   - currentSum += nums[i]
   - If (currentSum - k) exists in map:
       → add its frequency to count.
   - Update map[currentSum]++
4. Return count.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns total number of subarrays with sum equal to k.
*/

package Arrays.Medium;

import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find the total number of subarrays whose sum equals to k
  public int subarraySum(int[] nums, int k) {
    // Initialize the hash map for the prefix sum
    HashMap<Integer, Integer> prefixMap = new HashMap<>();

    // Map 0 to 1 in the prefixMap
    prefixMap.put(0, 1);

    // Initialize the currentSum and totalNumberOfSubarrays
    int totalNumberOfSubarrays = 0, currentSum = 0;

    // Iterate over the loop for the subarrays equal to k
    for (int i = 0; i < nums.length; i++) {
      // Add the current value to the currentSum
      currentSum += nums[i];

      // Get the difference
      int difference = currentSum - k;

      // Update the totalNumberOfSubarrays
      totalNumberOfSubarrays += prefixMap.getOrDefault(difference, 0);

      // Update the prefixMap
      prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);
    }

    // Return the totalNumberOfSubarrays
    return totalNumberOfSubarrays;
  }
}

public class _560_Subarray_Sum_Equals_K {
  // Main method to test subarraySum
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3 };
    int k = 3;

    int result = new Solution().subarraySum(nums, k);

    System.out.println("The total number of subarrays whose sum equals to " + k + " is : " + result);
  }
}
