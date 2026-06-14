/*
LeetCode Problem: https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/

Question: 2958. Length of Longest Subarray With at Most K Frequency

Problem Statement: You are given an integer array nums and an integer k.

The frequency of an element x is the number of times it occurs in an array.

An array is called good if the frequency of each element in this array is less than or equal to k.

Return the length of the longest good subarray of nums.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,2,3,1,2,3,1,2], k = 2
Output: 6
Explanation: The longest possible good subarray is [1,2,3,1,2,3] since the values 1, 2, and 3 occur at most twice in this subarray. Note that the subarrays [2,3,1,2,3,1] and [3,1,2,3,1,2] are also good.
It can be shown that there are no good subarrays with length more than 6.

Example 2:
Input: nums = [1,2,1,2,1,2,1,2], k = 1
Output: 2
Explanation: The longest possible good subarray is [1,2] since the values 1 and 2 occur at most once in this subarray. Note that the subarray [2,1] is also good.
It can be shown that there are no good subarrays with length more than 2.

Example 3:
Input: nums = [5,5,5,5,5,5,5], k = 4
Output: 4
Explanation: The longest possible good subarray is [5,5,5,5] since the value 5 occurs 4 times in this subarray.
It can be shown that there are no good subarrays with length more than 4.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length
*/

/*
Approach: Sliding Window + Frequency Map

Goal:
- Find the length of the longest subarray such that
  every value appears at most k times.

Core Idea:
- Maintain a sliding window where the frequency of
  each element does not exceed k.
- When adding nums[right] causes its frequency to
  become greater than k:
     shrink the window from the left
     until the constraint is restored.
- Every valid window is a candidate answer.

Algorithm Steps:
1. Maintain:
      frequency map
      left pointer
2. Expand the window:
      frequency[nums[right]]++
3. If frequency of nums[right] exceeds k:
      repeatedly:
          frequency[nums[left]]--
          left++
      until:
          frequency[nums[right]] <= k
4. Update:
      maxLength = max(maxLength,
                      right - left + 1)
5. Continue until all elements are processed.
6. Return maxLength.

Why It Works:
- Only the frequency of nums[right] can violate
  the constraint after insertion.
- Shrinking the window restores validity.
- The window always represents a valid "good"
  subarray.
- Every element enters and leaves the window
  at most once.

Time Complexity:
- O(n)

Space Complexity:
- O(m)
  where m is the number of distinct values
  currently stored in the frequency map.

Result:
- Returns the length of the longest subarray
  in which every element appears at most k times.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.HashMap;

// Solution Class
class Solution {
  // Method to find the length of the longest good subarray of nums
  public int maxSubarrayLength(int[] nums, int k) {
    // Initialize the hashmap for the frequency count
    HashMap<Integer, Integer> frequency = new HashMap<>();

    // Initialize the maxSubarrayLength variable
    int maxSubarrayLength = 0;

    // Iterate over the nums array
    for (int left = 0, right = 0; right < nums.length; right++) {
      // Update the frequency map
      frequency.put(nums[right], frequency.getOrDefault(nums[right], 0) + 1);

      // Get the current index frequency
      int currentIndexFrequency = frequency.get(nums[right]);

      // If currentIndexFrequency is more than the k then update the window
      while (currentIndexFrequency > k) {
        // Remove the left index number from the frequency map
        frequency.put(nums[left], frequency.get(nums[left]) - 1);

        // Update the currentIndexFrequency
        currentIndexFrequency = frequency.get(nums[right]);

        // Increment the left index
        left++;
      }

      // Update the maxSubarrayLength
      maxSubarrayLength = Math.max(maxSubarrayLength, right - left + 1);
    }

    // Return the maxSubarrayLength
    return maxSubarrayLength;
  }
}

public class _2958_Length_of_Longest_Subarray_With_at_Most_K_Frequency {
  // Main method to test maxSubarrayLength
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3, 1, 2, 3, 1, 2 };
    int k = 2;

    int result = new Solution().maxSubarrayLength(nums, k);

    System.out.println("The length of the longest good subarray of nums is : " + result);
  }
}
