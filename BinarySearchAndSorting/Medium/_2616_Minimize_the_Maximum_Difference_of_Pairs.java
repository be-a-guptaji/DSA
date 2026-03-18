/*
LeetCode Problem: https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/

Question: 2616. Minimize the Maximum Difference of Pairs

Problem Statement: You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the p pairs.

Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.

Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.

Example 1:
Input: nums = [10,1,2,7,1,3], p = 2
Output: 1
Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5. 
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

Example 2:
Input: nums = [4,2,1,2], p = 1
Output: 0
Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
0 <= p <= (nums.length)/2
*/

/*
Approach: Binary Search on Answer (Greedy Pairing)

Goal:
- Minimize the maximum difference among all selected p pairs.

Core Idea:
- Sort the array to bring close values together.
- The answer lies in the range [0, max(nums) - min(nums)].
- Use binary search to find the smallest threshold such that
  at least p valid pairs can be formed.
- Greedily form pairs using adjacent elements to minimize differences.

Algorithm Steps:
1. Sort the nums array.
2. Initialize bounds:
   - left = 0
   - right = nums[n-1] - nums[0]
3. Binary search on threshold:
   - mid = candidate maximum difference.
4. Check feasibility:
   - Traverse array greedily.
   - If nums[i+1] - nums[i] ≤ mid → form a pair and skip both.
   - Else → move to next index.
   - Count formed pairs.
5. If count ≥ p:
   - Update result and search smaller threshold.
6. Else:
   - Increase threshold.
7. Return the minimum valid threshold.

Time Complexity:
- O(n log n + n log D)
  n = array size, D = value range

Space Complexity:
- O(1) (excluding sorting overhead)

Result:
- Returns the minimized maximum difference among p pairs.
*/

package BinarySearchAndSorting.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the minimum maximum difference among all p pairs
    public int minimizeMax(int[] nums, int p) {
        // Initialize the left and right bound
        int left = 0, right;

        // Sort the nums array
        Arrays.sort(nums);

        // Update the rigth variable
        right = nums[nums.length - 1] - nums[0];

        // Initialize the result variable
        int result = right;

        // Iterate over the bound
        while (left <= right) {
            // Get the middle element
            int mid = (left + ((right - left) >> 1));

            if (this.isValid(nums, p, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // Return the result variable
        return result;
    }

    // Helper method to find if the nums is valid or not
    private boolean isValid(int[] nums, int p, int threshold) {
        // Initialize the index and the count
        int index = 0, count = 0;

        // Iterate over the nums array
        while (index < nums.length - 1) {
            if (Math.abs(nums[index] - nums[index + 1]) <= threshold) {
                index += 2;
                count++;
            } else {
                index++;
            }

            // If count is equal to p then return true
            if (count >= p) {
                return true;
            }
        }

        // Return the false in the end
        return false;
    }
}

public class _2616_Minimize_the_Maximum_Difference_of_Pairs {
    // Main method to test minimizeMax
    public static void main(String[] args) {
        int[] nums = new int[] { 10, 1, 2, 7, 1, 3 };
        int p = 2;

        int result = new Solution().minimizeMax(nums, p);

        System.out.println("The minimum maximum difference among all p pairs is : " + result);
    }
}