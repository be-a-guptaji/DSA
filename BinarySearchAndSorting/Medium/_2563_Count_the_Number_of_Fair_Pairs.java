/*
LeetCode Problem: https://leetcode.com/problems/count-the-number-of-fair-pairs/

Question: 2563. Count the Number of Fair Pairs

Problem Statement: Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper

Example 1:
Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).

Example 2:
Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).

Constraints:

1 <= nums.length <= 10^5
nums.length == n
-10^9 <= nums[i] <= 10^9
-10^9 <= lower <= upper <= 10^9
*/

/*
Approach: Sorting + Binary Search (Range Counting)

Goal:
- Count the number of index pairs (i, j) such that:
    lower ≤ nums[i] + nums[j] ≤ upper, with i < j.

Core Idea:
- Sort the array.
- Fix nums[i], and transform the condition into:
    lower - nums[i] ≤ nums[j] ≤ upper - nums[i]
- Use binary search to count how many nums[j] satisfy this range.
- Count = upperBound - lowerBound.

Algorithm Steps:
1. Sort nums array.
2. For each index i:
   - Compute:
       l = lower - nums[i]
       u = upper - nums[i]
3. Use binary search to find:
   - First index where nums[j] ≥ l
   - First index where nums[j] > u
4. Add the difference to total pairs.
5. Repeat for all i.

Time Complexity:
- O(n log n)
  n = size of array

Space Complexity:
- O(1) (excluding sorting overhead)

Result:
- Returns total number of fair pairs satisfying the condition.
*/

package BinarySearchAndSorting.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the number of fair pairs
    public long countFairPairs(int[] nums, int lower, int upper) {
        // Sort the array
        Arrays.sort(nums);

        // Initialize the pairs variable
        long pairs = 0;

        // Iterate over the nums array for finding the pairs
        for (int i = 0; i < nums.length - 1; i++) {
            // Find the current lower and upper bound
            long l = lower - nums[i];
            long u = upper - nums[i];

            // Update the pairs variable
            pairs += (this.findPairs(nums, i + 1, nums.length - 1, u + 1)
                    - this.findPairs(nums, i + 1, nums.length - 1, l));
        }

        // Return the pairs
        return pairs;
    }

    // Helper method to find the pairs for a specific value
    private long findPairs(int[] nums, int left, int right, long target) {
        /// Initialize the left and right pointer
        int l = left, r = right;

        // Find the target
        while (l <= r) {
            // Find the middle element
            int mid = l + (r - l) / 2;

            // Update the bound correctly
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        // Return the right pointer
        return l - left;
    }
}

public class _2563_Count_the_Number_of_Fair_Pairs {
    // Main method to test countFairPairs
    public static void main(String[] args) {
        int[] nums = new int[] { 0, 1, 7, 4, 4, 5 };
        int lower = 3;
        int upper = 6;

        long result = new Solution().countFairPairs(nums, lower, upper);

        System.out.println("The number of fair pairs is : " + result);
    }
}