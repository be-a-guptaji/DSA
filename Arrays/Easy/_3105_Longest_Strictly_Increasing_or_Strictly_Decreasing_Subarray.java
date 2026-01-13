/*
LeetCode Problem: https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/

Question: 3105. Longest Strictly Increasing or Strictly Decreasing Subarray

Problem Statement: You are given an array of integers nums. Return the length of the longest subarray of nums which is either strictly increasing or strictly decreasing.

Example 1:
Input: nums = [1,4,3,3,2]
Output: 2
Explanation:
The strictly increasing subarrays of nums are [1], [2], [3], [3], [4], and [1,4].
The strictly decreasing subarrays of nums are [1], [2], [3], [3], [4], [3,2], and [4,3].
Hence, we return 2.

Example 2:
Input: nums = [3,3,3,3]
Output: 1
Explanation:
The strictly increasing subarrays of nums are [3], [3], [3], and [3].
The strictly decreasing subarrays of nums are [3], [3], [3], and [3].
Hence, we return 1.

Example 3:
Input: nums = [3,2,1]
Output: 3
Explanation:
The strictly increasing subarrays of nums are [3], [2], and [1].
The strictly decreasing subarrays of nums are [3], [2], [1], [3,2], [2,1], and [3,2,1].
Hence, we return 3.

Constraints:

1 <= nums.length <= 50
1 <= nums[i] <= 50
*/

/*
Approach: Single-Pass Streak Tracking

Goal:
Find the length of the longest contiguous subarray that is either
strictly increasing or strictly decreasing.

Key Idea:
Track two running streaks:
- inc: length of current strictly increasing subarray.
- dec: length of current strictly decreasing subarray.

Algorithm:
1. Handle edge case: if array is empty, return 0.
2. Initialize:
   - inc = 1, dec = 1 (a single element counts as length 1)
   - result = 1
3. Traverse the array from index 1:
   - If nums[i] > nums[i − 1]:
       • inc++
       • dec = 1
   - Else if nums[i] < nums[i − 1]:
       • dec++
       • inc = 1
   - Else (equal elements):
       • inc = 1
       • dec = 1
   - Update result = max(result, inc, dec)
4. Return result.

Why It Works:
- Only contiguous comparisons matter.
- Resetting the opposite streak ensures strict monotonicity.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _3105_Longest_Strictly_Increasing_or_Strictly_Decreasing_Subarray {
    // Method to find the maximum number of consecutive elements
    // that are either strictly increasing or strictly decreasing
    public static int longestMonotonicSubarray(int[] nums) {
        // Handle the edge case when the array is empty
        if (nums.length == 0) {
            return 0;
        }

        // Initialize the increasing and decreasing streak counters
        int inc = 1, dec = 1;

        // Initialize the result variable
        int result = 1;

        // Iterate over the nums array starting from index 1
        for (int i = 1; i < nums.length; i++) {
            // If the current element is greater than the previous element
            if (nums[i] > nums[i - 1]) {
                // Increase the increasing streak
                inc++;

                // Reset the decreasing streak
                dec = 1;
            } // If the current element is smaller than the previous element
            else if (nums[i] < nums[i - 1]) {
                // Increase the decreasing streak
                dec++;

                // Reset the increasing streak
                inc = 1;

            } // If both elements are equal
            else {
                // Reset both streaks
                inc = 1;
                dec = 1;
            }

            // Update the result with the maximum streak found so far
            result = Math.max(result, Math.max(inc, dec));
        }

        // Return the result
        return result;
    }

    // Main method to test findMaxConsecutiveOnes
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 4, 3, 3, 2 };

        int result = longestMonotonicSubarray(nums);

        System.out.println("The maximum number of consecutive 1's in the array is : " + result);
    }
}
