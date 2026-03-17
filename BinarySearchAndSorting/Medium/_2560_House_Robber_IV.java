/*
LeetCode Problem: https://leetcode.com/problems/house-robber-iv/

Question: 2560. House Robber IV

Problem Statement: There are several consecutive houses along a street, each of which has some money inside. There is also a robber, who wants to steal money from the homes, but he refuses to steal from adjacent homes.

The capability of the robber is the maximum amount of money he steals from one house of all the houses he robbed.

You are given an integer array nums representing how much money is stashed in each house. More formally, the ith house from the left has nums[i] dollars.

You are also given an integer k, representing the minimum number of houses the robber will steal from. It is always possible to steal at least k houses.

Return the minimum capability of the robber out of all the possible ways to steal at least k houses.

Example 1:
Input: nums = [2,3,5,9], k = 2
Output: 5
Explanation: 
There are three ways to rob at least 2 houses:
- Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) = 5.
- Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) = 9.
- Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) = 9.
Therefore, we return min(5, 9, 9) = 5.

Example 2:
Input: nums = [2,7,9,3,1], k = 2
Output: 2
Explanation: There are 7 ways to rob the houses. The way which leads to minimum capability is to rob the house at index 0 and 4. Return max(nums[0], nums[4]) = 2.

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= (nums.length + 1)/2
*/

/*
Approach: Binary Search on Answer (Greedy Feasibility Check)

Goal:
- Find the minimum capability (maximum value among chosen houses)
  such that at least k non-adjacent houses can be robbed.

Core Idea:
- The capability defines a threshold:
  only houses with value ≤ capability can be selected.
- Use binary search over possible capability values.
- For a given capability, greedily select non-adjacent houses
  to check if at least k houses can be robbed.

Algorithm Steps:
1. Determine bounds:
   - left  = minimum value in nums.
   - right = maximum value in nums.
2. Perform binary search on capability:
   - mid = candidate capability.
3. Check feasibility:
   - Traverse array greedily.
   - If nums[i] ≤ mid → pick it and skip next index.
   - Count selected houses.
4. If count ≥ k:
   - Update result and search smaller capability.
5. Else:
   - Increase capability.
6. Return the minimum valid capability.

Time Complexity:
- O(n log M)
  n = number of houses
  M = range of values in nums

Space Complexity:
- O(1)

Result:
- Returns the minimum capability required to rob at least k houses.
*/

package BinarySearchAndSorting.Medium;

// Solution Class
class Solution {
    // Method to find the minimum capability of the robber out of all the possible
    // ways to steal at least k houses
    public int minCapability(int[] nums, int k) {
        // Initialize the left and right bound
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;

        // Get the left and right bound
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        // Initialize the result variable
        int result = 0;

        // Iterate over the bound
        while (left <= right) {
            // Get the middle element
            int mid = (left + right) >> 1;

            if (this.isValid(nums, k, mid)) {
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
    private boolean isValid(int[] nums, int k, int capability) {
        // Initialize the index and the count
        int index = 0, count = 0;

        // Iterate over the nums array
        while (index < nums.length) {
            if (nums[index] <= capability) {
                index += 2;
                count++;
            } else {
                index++;
            }

            // If count is equal to k then break
            if (count == k) {
                break;
            }
        }

        // Return the condition in the end
        return count == k;
    }
}

public class _2560_House_Robber_IV {
    // Main method to test minCapability
    public static void main(String[] args) {
        int[] candies = new int[] { 2, 3, 5, 9 };
        int k = 2;

        int result = new Solution().minCapability(candies, k);

        System.out.println(
                "The minimum capability of the robber out of all the possible ways to steal at least k houses is : "
                        + result);
    }
}