/*
LeetCode Problem: https://leetcode.com/problems/split-array-largest-sum/

Question: 410. Split Array Largest Sum

Problem Statement: Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.

Example 1:
Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.

Example 2:
Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 10^6
1 <= k <= min(50, nums.length)
*/

/*
Approach: Binary Search on Answer (Greedy Partitioning)

Goal:
- Split the array into k subarrays such that the maximum
  subarray sum is minimized.

Core Idea:
- The answer lies between:
    max(nums) → minimum feasible largest sum
    sum(nums) → maximum possible largest sum
- Use binary search to find the smallest value of largest sum
  such that the array can be split into at most k subarrays.
- Use a greedy approach to validate feasibility.

Algorithm Steps:
1. Compute bounds:
   - left  = max(nums)
   - right = sum(nums)
2. Perform binary search:
   - mid = candidate largest subarray sum.
3. Check feasibility:
   - Traverse array and accumulate sum.
   - If sum exceeds mid → create new subarray.
   - Count number of subarrays formed.
4. If subarrays ≤ k:
   - Update result and search smaller value.
5. Else:
   - Increase lower bound.
6. Return the minimum valid largest sum.

Time Complexity:
- O(n log S)
  n = size of array
  S = sum of elements

Space Complexity:
- O(1)

Result:
- Returns the minimized largest subarray sum after splitting into k parts.
*/

package BinarySearchAndSorting.Hard;

// Solution Class
class Solution {
    // Method to find the minimized largest sum of the split
    public int splitArray(int[] nums, int k) {
        // Initialize the left and right bound
        int left = Integer.MIN_VALUE, right = 0;

        // Get the left and right bound
        for (int i = 0; i < nums.length; i++) {
            left = Math.max(left, nums[i]);
            right += nums[i];
        }

        // Initialize the result variable
        int result = right;

        // Iterate over the bound
        while (left <= right) {
            // Get the mid value
            int mid = left + (right - left) / 2;

            // Update the pointers
            if (this.canSplit(nums, k, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // Return the result variable
        return result;
    }

    // Helper method to check if we can split the array
    private boolean canSplit(int[] nums, int k, int largest) {
        // Initialize the subarray and currentSum variable
        int subarray = 0, currentSum = 0;

        // Find the total number of splits
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];

            if (currentSum > largest) {
                subarray++;
                currentSum = nums[i];
            }
        }

        // Return the condition
        return subarray + 1 <= k;
    }
}

public class _410_Split_Array_Largest_Sum {
    // Main method to test splitArray
    public static void main(String[] args) {
        int[] nums = new int[] { 7, 2, 5, 10, 8 };
        int k = 2;

        int result = new Solution().splitArray(nums, k);

        System.out.println("The minimized largest sum of the split is : " + result);
    }
}
