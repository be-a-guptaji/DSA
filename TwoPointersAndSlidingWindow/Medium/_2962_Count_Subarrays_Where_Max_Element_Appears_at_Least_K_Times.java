/*
LeetCode Problem: https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/

Question: 2962. Count Subarrays Where Max Element Appears at Least K Times

Problem Statement: You are given an integer array nums and a positive integer k.

Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.

A subarray is a contiguous sequence of elements within an array.

Example 1:
Input: nums = [1,3,2,3,3], k = 2
Output: 6
Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].

Example 2:
Input: nums = [1,4,2,1], k = 3
Output: 0
Explanation: No subarray contains the element 4 at least 3 times.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
1 <= k <= 10^5
*/

/*
Approach: Sliding Window + Counting Valid Extensions

Goal:
- Count the number of subarrays in which the
  maximum element of the entire array appears
  at least k times.

Core Idea:
1. Find the global maximum element:
      maxNumber
2. Maintain a sliding window and track how many
   times maxNumber appears inside it.
3. Once the window contains at least k occurrences
   of maxNumber:
      every extension of this window to the right
      is also valid.
4. Count all such subarrays in O(1).

Algorithm Steps:
1. Find the maximum element in nums.
2. Maintain:
      left pointer
      frequency of maxNumber in current window
3. Expand right pointer.
4. If nums[right] == maxNumber:
      frequency++
5. While frequency >= k:
      - Current window is valid.
      - Every subarray ending at:
            right, right+1, ..., n-1
        remains valid.
      - Add:
            n - right
        to the answer.
      - Shrink from the left.
      - If nums[left] == maxNumber:
            frequency--
6. Continue until all elements are processed.
7. Return the accumulated count.

Why It Works:
- When a window contains at least k occurrences
  of maxNumber, adding more elements to the right
  cannot reduce that count.
- Therefore:
      [left, right]
      [left, right+1]
      ...
      [left, n-1]
  are all valid.
- Instead of counting individually, add:
      n - right
  in one operation.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the number of subarrays where the
  maximum element appears at least k times.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the number of subarrays where the maximum element of nums
  // appears at least k times in that subarray
  public long countSubarrays(int[] nums, int k) {
    // Initialize the maxNumber variable
    int maxNumber = Integer.MIN_VALUE;

    // Get the maximum number of element in the array
    for (int i = 0; i < nums.length; i++) {
      maxNumber = Math.max(maxNumber, nums[i]);
    }

    // Initialize the totalNumberOfSubarray varaible
    long totalNumberOfSubarray = 0;

    // Iterate over the nums to find the totalNumberOfSubarray
    for (int left = 0, right = 0, frequency = 0; right < nums.length; right++) {
      // If nums[right] is equal to maxNumber then increment the frequency
      if (maxNumber == nums[right]) {
        frequency++;
      }

      // If frequency is more then equal to k then update the pointers
      while (frequency >= k) {
        // If nums[left] is equal to maxNumber then decrement the frequency
        if (maxNumber == nums[left]) {
          frequency--;
        }

        // Increment the left index
        left++;

        // Update the totalNumberOfSubarray
        totalNumberOfSubarray += (nums.length - right);
      }
    }

    // Return the totalNumberOfSubarray
    return totalNumberOfSubarray;
  }
}

public class _2962_Count_Subarrays_Where_Max_Element_Appears_at_Least_K_Times {
  // Main method to test maxSubarrayLength
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 3, 2, 3, 3 };
    int k = 2;

    long result = new Solution().countSubarrays(nums, k);

    System.out.println(
        "The number of subarrays where the maximum element of nums appears at least k times in that subarray is : "
            + result);
  }
}
