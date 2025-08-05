/*
LeetCode Problem: https://leetcode.com/problems/max-consecutive-ones-iii/

Question: 1004. Max Consecutive Ones III

Problem Statement: Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
0 <= k <= nums.length
 */

 /*
Approach: Sliding Window

Key Idea:
- Maintain a window [left, right] such that the number of 0s in this window is at most k.
- Expand the window by moving `right`.
- If the number of 0s exceeds `k`, shrink the window from the left until it becomes valid again.
- At each step, keep track of the maximum window size encountered.

Time Complexity: O(n), where n = length of the array.
Space Complexity: O(1)
 */
package TwoPointersAndSlidingWindow.Medium;

public class _1004_Max_Consecutive_Ones_III {

    // Method to find the maximum consecutive ones by replacing k 0's
    public static int longestOnes(int[] nums, int k) {
        // Initialize the variable 
        int left = 0, zeroCount = 0, maxConsecutiveOnes = 0;

        // Logic to find the maximum consecutive ones by replacing k 0's
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Shrinking the window from the left
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }

                left++;
            }

            // Updating the max consecutive count for the one
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, right - left + 1);
        }

        // Return maxConsecutiveOnes
        return maxConsecutiveOnes;
    }

    // Main method to test longestOnes
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;

        int result = longestOnes(nums, k);

        System.out.println("The maximum consecutive ones by replacing " + k + " 0's is : " + result);
    }
}
