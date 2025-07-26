/*
LeetCode Problem: https://leetcode.com/problems/trapping-rain-water/

Question: 42. Trapping Rain Water

Problem Statement:
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
n == height.length
1 <= n <= 2 * 10^4
0 <= height[i] <= 10^5
*/

/*
Approach: Two-Pointer Technique (Space Optimized)

We use two pointers (left and right) starting from both ends of the array.
At each step, we move the pointer that has the smaller height because
the amount of water trapped depends on the shorter boundary.

We also maintain:
- left_max: the maximum height encountered from the left
- right_max: the maximum height encountered from the right

At each step, we calculate trapped water as:
    trapped_water += max_height_so_far - current_height

Steps:
1. Initialize two pointers: left = 0, right = n - 1
2. Traverse the array while left < right:
    - If height[left] < height[right], process left side:
        - Update left_max
        - Add trapped water at left index if any
        - Move left pointer to the right
    - Else, process right side:
        - Update right_max
        - Add trapped water at right index if any
        - Move right pointer to the left

Time Complexity: O(n) — single pass through the array
Space Complexity: O(1) — constant space, no extra arrays used
*/

package Arrays.Hard;

public class _42_Trapping_Rain_Water {
    // Method to compute how much water can be trapped
    public static int trap(int[] height) {
        // Initialize pointers and tracking variables
        int left = 0, right = height.length - 1, left_max = 0, right_max = 0, result = 0;

        // Traverse from both ends toward the center
        while (left < right) {
            // If left bar is shorter, process from left side
            if (height[left] < height[right]) {
                // Update left_max or accumulate trapped water
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    result += left_max - height[left];
                }
                left++;
            } else {
                // Process from right side
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    result += right_max - height[right];
                }
                right--;
            }
        }

        // Return the result
        return result;
    }

    // Main method to test the trap function
    public static void main(String[] args) {
        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        int result = trap(height);

        System.out.println("The trapped water is: " + result);
    }
}
