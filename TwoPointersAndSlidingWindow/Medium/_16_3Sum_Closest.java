/*
LeetCode Problem: https://leetcode.com/problems/3sum-closest/

Question: 16. 3Sum Closest

Problem Statement: Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Example 2:
Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).

Constraints:

3 <= nums.length <= 500
-1000 <= nums[i] <= 1000
-10^4 <= target <= 10^4
 */

/*
Approach: Two Pointers after Sorting

1. Sort the array to enable the use of the two-pointer technique.
2. For each element `nums[i]` in the array (from 0 to length - 3):
  - Initialize two pointers:
    - `left = i + 1`
    - `right = nums.length - 1`
  - Calculate the sum: `currentSum = nums[i] + nums[left] + nums[right]`
  - Compare this sum with the target:
    - If the difference between currentSum and target is smaller than the difference between result and target, update result.
  - Move pointers based on how `currentSum` compares to `target`:
    - If `currentSum < target`, move `left++` to increase the sum.
    - If `currentSum > target`, move `right--` to decrease the sum.
    - If `currentSum == target`, it's the closest possible, return immediately.

Time Complexity: O(n^2)  
Space Complexity: O(1)  
*/
package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

public class _16_3Sum_Closest {

    // Method to find the 3 sum closest
    public static int threeSumClosest(int[] nums, int target) {
        // Sort the array first
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];

        // Logic to find the closest three sum
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];

                // Update result if this total is closer to target
                if (Math.abs(result - target) > Math.abs(total - target)) {
                    result = total;
                }

                // Move pointers based on comparison with target
                if (total == target) {
                    return target; // Best possible case
                } else if (total < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        // Return the closest result
        return result;
    }

    // Main method to test threeSumClosest
    public static void main(String[] args) {
        int[] nums = { 13, -8, 23, -44, 60, -29, 17, -33, 5, 1, -4, 99, 2, -100, 50, -50, 34, -20 };
        int target = 3;

        int result = threeSumClosest(nums, target);

        System.out.println("The closest 3 sum to the target " + target + " is : " + result);
    }
}
