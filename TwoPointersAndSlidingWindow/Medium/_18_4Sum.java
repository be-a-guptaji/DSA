/*
LeetCode Problem: https://leetcode.com/problems/4sum/

Question: 18. 4Sum

Problem Statement: Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Example 2:
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]

Constraints:

1 <= nums.length <= 200
-10^9 <= nums[i] <= 10^9
-10^9 <= target <= 10^9
 */

 /*
Approach: Sorting + Two Pointers

1. Sort the array to enable the use of the two-pointer technique.
2. Fix the first number `nums[i]` using a loop from index 0 to n - 4:
   - Skip duplicates for `i` to avoid repeated quadruplets.
3. Fix the second number `nums[j]` using a nested loop from index i + 1 to n - 3:
   - Skip duplicates for `j` to avoid repeated quadruplets.
4. Use two pointers `left = j + 1` and `right = nums.length - 1` to find the remaining two elements.
5. Calculate the total sum of the four numbers:
   - If total equals target, add the quadruplet to the result list.
   - Move `left` and `right` while skipping duplicates.
   - If total < target, move `left++` to increase the sum.
   - If total > target, move `right--` to decrease the sum.
6. Repeat this process to find all unique combinations that sum to the target.

Time Complexity: O(n^3)  
Space Complexity: O(1) (excluding the output list)
 */
package TwoPointersAndSlidingWindow.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18_4Sum {

    // Method to find the 4 sum
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // Sort the array first
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        // Logic to find the 4 sum
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicate values for i
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // Skip duplicate values for j
                }
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    // Use long to avoid integer overflow
                    long total = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    // Move pointers based on comparison with target
                    if (total == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicate values for left and right
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (total < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        // Return the result
        return result;
    }

    // Main method to test fourSum
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> result = fourSum(nums, target);

        System.out.println("The 4 sum to the target " + target + " is : " + result);
    }
}
