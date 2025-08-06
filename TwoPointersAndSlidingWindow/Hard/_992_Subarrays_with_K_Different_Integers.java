/*
LeetCode Problem: https://leetcode.com/problems/subarrays-with-k-different-integers/

Question: 992. Subarrays with K Different Integers

Problem Statement: Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

Example 2:
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i], k <= nums.length
 */

 /*
Approach: This solution uses the sliding window technique to count:
 1. The number of subarrays with at most K distinct integers.
 2. The number of subarrays with at most (K - 1) distinct integers.
 
 The difference between these two gives the count of subarrays with exactly K distinct integers.
 
 - The 'atMostK' method uses a sliding window and a HashMap to track the frequency of elements in the current window.
 - Whenever the number of unique elements in the window exceeds K, we shrink the window from the left.
 - For each position of the right pointer, we add the number of valid subarrays ending at that position to the result.
 
This approach avoids brute-force checking of all subarrays and runs efficiently.
 
Time Complexity: O(n)
Space Complexity: O(k)
 */
package TwoPointersAndSlidingWindow.Hard;

import java.util.HashMap;
import java.util.Map;

public class _992_Subarrays_with_K_Different_Integers {

    // Method to find the subarrays with K distinct element
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    // Helper method to find number of subarrays with at most K distinct elements
    private static int atMostK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, result = 0;

        for (int right = 0; right < nums.length; right++) {
            int rightNum = nums[right];
            map.put(rightNum, map.getOrDefault(rightNum, 0) + 1);

            if (map.get(rightNum) == 1) {
                k--; // new unique element
            }

            while (k < 0) {
                int leftNum = nums[left];
                map.put(leftNum, map.get(leftNum) - 1);
                if (map.get(leftNum) == 0) {
                    map.remove(leftNum);
                    k++;
                }
                left++;
            }

            result += right - left + 1;
        }

        // Return the result
        return result;
    }

    // Main method to test subarraysWithKDistinct
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;

        int result = subarraysWithKDistinct(nums, k);

        System.out.println("The subarrays with K distinct elements are : " + result);
    }
}
