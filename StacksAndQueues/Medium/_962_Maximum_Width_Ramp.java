/*
LeetCode Problem: https://leetcode.com/problems/maximum-width-ramp/

Question: 962. Maximum Width Ramp

Problem Statement: A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.

Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.

Example 1:
Input: nums = [6,0,8,2,1,5]
Output: 4
Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.

Example 2:
Input: nums = [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.

Constraints:

2 <= nums.length <= 5 * 10^4
0 <= nums[i] <= 5 * 10^4
 */

/*
Approach: Two-Pointer with Right-Max Preprocessing

Goal:
- Find the maximum width ramp (i < j and nums[i] <= nums[j]).

Core Idea:
- Precompute maxRight[i] = maximum value from i to end.
- Use two pointers:
  - left tracks smallest valid index.
  - right expands forward.
- If nums[left] > maxRight[right], move left forward.

Algorithm Steps:
1. Create maxRight array.
2. Traverse from right to left:
   - maxRight[i] = max(nums[i], maxRight[i + 1]).
3. Initialize left = 0 and maxRampWidth = 0.
4. Traverse right from 0 to n-1:
   - While left < right and nums[left] > maxRight[right], increment left.
   - Update maxRampWidth = max(maxRampWidth, right - left).
5. Return maxRampWidth.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Maximum width ramp satisfying nums[i] <= nums[j].
*/

package StacksAndQueues.Medium;

public class _962_Maximum_Width_Ramp {
    // Method to find the maximum width of a ramp in nums
    public static int maxWidthRamp(int[] nums) {
        // Initialize the array to find the maximum of right of the value
        int[] maxRight = new int[nums.length];

        // Initialize the previous max variable
        int previousMax = 0;

        // Iterate over the nums array to find the right max value
        for (int i = nums.length - 1; i >= 0; i--) {
            maxRight[i] = Math.max(previousMax, nums[i]);
            previousMax = maxRight[i];
        }

        // Initialize the variable for the maximum width ramp
        int maxRampWidth = 0;

        // Initialize the left and right pointer
        int left = 0;

        // Iterate over the nums array to find maximum width ramp
        for (int right = 0; right < nums.length; right++) {
            // If nums[left] is greater than the nums[right] then increment the left pointer
            while (left < right && nums[left] > maxRight[right]) {
                left++;
            }

            // Update the maximum width ramp
            maxRampWidth = Math.max(maxRampWidth, right - left);
        }

        // Return the maximum width ramp
        return maxRampWidth;
    }

    // Main method to test maxWidthRamp
    public static void main(String[] args) {
        int[] nums = new int[] { 9, 8, 1, 0, 1, 9, 4, 0, 4, 1 };

        int result = maxWidthRamp(nums);

        System.out.println("The maximum width of a ramp in nums is : " + result);
    }
}