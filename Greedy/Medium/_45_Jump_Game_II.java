/*
LeetCode Problem: https://leetcode.com/problems/jump-game-ii/

Question: 45. Jump Game II

Problem Statement: You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:

0 <= j <= nums[i] and
i + j < n

Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
*/

/*
Approach: This solution uses a greedy strategy to find the minimum number of jumps needed to reach the end of the array.
- We iterate through the array while keeping track of two main variables:
  1. 'farthestEnd' → the farthest index we can currently reach from the visited elements.
  2. 'currentEnd' → the end of the current jump range.
- As we iterate, we continuously update 'farthestEnd' to be the farthest position reachable from any index within the current jump range.
- When we reach the end of the current range ('i == currentEnd'), it means we must make a jump:
  - We increment the 'jumps' counter.
  - We update 'currentEnd' to be 'farthestEnd', which defines the range for the next jump.
- We stop iterating at the second-to-last index (i < nums.length - 1), since we don't need to jump from the last element.
- At the end, the 'jumps' variable contains the minimum number of jumps required to reach the last index.

Time Complexity: O(n), where n = number of elements in the array. Each element is visited once.
Space Complexity: O(1), as we only use a few variables.
*/

package Greedy.Medium;

public class _45_Jump_Game_II {
    // Method to find minimum number of jumps form start to the end of the array
    public static int jump(int[] nums) {
        // Initialize the variable for jump, current end and the furthest end
        int jumps = 0, currentEnd = 0, furthestEnd = 0;

        // Iterate over the nums array
        for (int i = 0; i < nums.length - 1; i++) {
            // Get the furthest point form the current point
            furthestEnd = Math.max(furthestEnd, i + nums[i]);

            // When we reach the end of the current jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = furthestEnd;

                // If we can already reach or exceed the end
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        // Return the jumps
        return jumps;
    }

    // Main method to test jump
    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };

        int result = jump(nums);

        System.out.println("The minimum number of jumps form start to the end of the array is : " + result);
    }
}
