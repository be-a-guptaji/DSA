/*
LeetCode Problem: https://leetcode.com/problems/find-k-th-smallest-pair-distance/

Question: 719. Find K-th Smallest Pair Distance

Problem Statement: The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.

Example 1:
Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.

Example 2:
Input: nums = [1,1,1], k = 2
Output: 0

Example 3:
Input: nums = [1,6,1], k = 3
Output: 5

Constraints:

n == nums.length
2 <= n <= 10^4
0 <= nums[i] <= 10^6
1 <= k <= n * (n - 1) / 2
*/

/*
Approach: Binary Search on Answer (Two-Pointer Pair Counting)

Goal:
- Find the k-th smallest absolute difference among all pairs
  (i, j) where i < j.

Core Idea:
- Sort the array so pair differences are ordered.
- Binary search on possible distance values.
- For a given distance d, count how many pairs have
  difference ≤ d using a sliding window (two pointers).
- Use this count to decide the search direction.

Algorithm Steps:
1. Sort nums.
2. Initialize bounds:
   - left = 0
   - right = max(nums) - min(nums)
3. Binary search on distance:
   - mid = candidate distance.
4. Count pairs with difference ≤ mid:
   - Use two pointers:
       for each right, move left until
       nums[right] - nums[left] ≤ mid.
       Add (right - left) to count.
5. If count ≥ k:
   - Search smaller distances (right = mid).
6. Else:
   - Search larger distances (left = mid + 1).
7. Return left (or right).

Time Complexity:
- O(n log n + n log D)
  n = array size, D = value range

Space Complexity:
- O(1) (excluding sorting)

Result:
- Returns the k-th smallest pair distance.
*/

package BinarySearchAndSorting.Hard;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the kth smallest distance among all the pairs nums[i] and
    // nums[j] where 0 <= i < j < nums.length
    public int smallestDistancePair(int[] nums, int k) {
        // Sort the nums array
        Arrays.sort(nums);

        // Initialize the left and right pointer
        int left = 0, right = nums[nums.length - 1];

        // Iterate over the bound to find the smallest distance pair
        while (left < right) {
            // Find the mid element
            int mid = (left + right) >> 1;

            // Update the left and right pointer accrodingly
            if (this.helper(nums, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Return the right pointer
        return right;
    }

    // Helper method to find the pairs
    private int helper(int[] nums, int distance) {
        // Initialize the left pointer
        int left = 0;

        // Initialize the result variable
        int result = 0;

        // Find the paris
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > distance) {
                left++;
            }

            result += right - left;
        }

        // Return the result
        return result;
    }
}

public class _719_Find_K_th_Smallest_Pair_Distance {
    // Main method to test smallestDistancePair
    public static void main(String[] args) {
        int[] nums = new int[] { 7, 2, 5, 10, 8 };
        int k = 2;

        int result = new Solution().smallestDistancePair(nums, k);

        System.out.println(
                "The kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length is : "
                        + result);
    }
}
