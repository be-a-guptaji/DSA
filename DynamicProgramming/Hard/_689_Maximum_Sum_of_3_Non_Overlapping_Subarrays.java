/*
LeetCode Problem: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/

Question: 689. Maximum Sum of 3 Non-Overlapping Subarrays

Problem Statement: Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example 1:
Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.

Example 2:
Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]

Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i] < 2^16
1 <= k <= floor(nums.length / 3)
 */

/*
Approach: Dynamic Programming + Prefix Sum + Greedy Construction

Key Idea:
- We need to find three non-overlapping subarrays of length k with the maximum total sum.
- We can precompute the sum of every subarray of size k, then use dynamic programming
  (or three helper arrays) to find the best combination of left, middle, and right subarrays.

Main Observations:
1. We can represent each k-length subarray starting at index i by its sum, using prefix sums
   for O(1) range sum computation.
   → kSum[i] = sum(nums[i] ... nums[i + k - 1])

2. We can precompute:
   - left[i]  = starting index of the maximum-sum subarray on the left side (≤ i)
   - right[i] = starting index of the maximum-sum subarray on the right side (≥ i)

3. Then, iterate through each possible middle subarray starting index `mid`
   (valid range: k ≤ mid ≤ n - 2k):
   - leftStart  = left[mid - k]
   - rightStart = right[mid + k]
   - total = kSum[leftStart] + kSum[mid] + kSum[rightStart]
   Keep track of the maximum total and the corresponding indices.

4. This method ensures all three subarrays are non-overlapping and gives the lexicographically
   smallest starting indices if multiple results have the same sum.

Steps:
1. Compute prefix sums to get all k-length subarray sums (kSum).
2. Build `left[]` and `right[]` helper arrays.
3. Iterate possible middle windows to find the max sum combination.
4. Return the starting indices of those three subarrays.

Time Complexity: O(n)
Space Complexity: O(n)
*/

package DynamicProgramming.Hard;

import java.util.Arrays;

public class _689_Maximum_Sum_of_3_Non_Overlapping_Subarrays {
    // Method to find the list of indices representing the starting position of each
    // interval
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // Get the length of the nums array
        int n = nums.length;

        // Create an array to store the sum of subarrays of length k
        int[] kSums = new int[n];

        // Get sum of first k elements
        for (int i = 0; i < k; i++) {
            kSums[0] += nums[i];
        }

        // Get all subarray sums of length k using a sliding window
        for (int i = k; i < n; i++) {
            kSums[i - k + 1] = kSums[i - k] + nums[i] - nums[i - k];
        }

        // Initialize the dp matrix for memoization
        int[][] dp = new int[n + 1][3];

        // Fill the dp matrix with -1
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        // Initialize the return array
        int[] result = new int[3];

        // Initialize the index variable
        int index = 0;

        // Initialize the i variable for tracking result array
        int i = 0;

        // Iterate until we fill the result array or run out of valid indices
        while ((index <= n - k) && (result[2] == 0)) {
            // Include the current index
            int include = kSums[index] + getMaxSum(index + k, i + 1, kSums, dp, n, k);

            // Skip the current index
            int skip = getMaxSum(index + 1, i, kSums, dp, n, k);

            // If including gives better result, update result index and move by k
            if (include >= skip) {
                result[i] = index;
                index += k;
                i++;
            } else {
                // Otherwise move to next index
                index++;
            }
        }

        // Return the result array
        return result;
    }

    // Helper method to get the max sum recursively
    private static int getMaxSum(int i, int count, int[] kSums, int[][] dp, int n, int k) {
        // Base case if count is 3 or index is out of bound
        if (count == 3 || i > n - k) {
            return 0;
        }

        // If already computed, return the cached result
        if (dp[i][count] != -1) {
            return dp[i][count];
        }

        // Include the current index
        int include = kSums[i] + getMaxSum(i + k, count + 1, kSums, dp, n, k);

        // Skip the current index
        int skip = getMaxSum(i + 1, count, kSums, dp, n, k);

        // Store and return the maximum of include and skip
        return dp[i][count] = Math.max(include, skip);
    }

    // Main method to test maxSumOfThreeSubarrays
    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 2, 6, 7, 5, 1 };
        int k = 2;

        int[] result = maxSumOfThreeSubarrays(nums, k);

        System.out.println("The list of indices representing the starting position of each interval are : "
                + Arrays.toString(result));
    }
}
