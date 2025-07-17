/*
LeetCode Problem: https://leetcode.com/problems/container-with-most-water/

Question: 11. Container With Most Water

Problem Statement: You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:

n == height.length
2 <= n <= 10^5
0 <= height[i] <= 10^4
*/

/*
Approach: 
Use the two-pointer technique:
- Start with one pointer at the beginning (`left`) and one at the end (`right`) of the array.
- Calculate the area between the two lines pointed to by `left` and `right`.
- Move the pointer with the shorter height inward, since the area is limited by the shorter line.
- Repeat until both pointers meet.
This guarantees we explore all widest containers first and try to find taller lines as we move inward.

Time Complexity: O(n), where n is the number of elements in the array.
Space Complexity: O(1), no extra space is used apart from a few variables.
*/

package Arrays.Medium;

public class _11_Container_With_Most_Water {
    // Method to find the maximum area of water
    public static int maxArea(int[] height) {
        // Initilize Variables
        int maximumAreaOfWater = 0;
        int left = 0, right = height.length - 1;

        // Logic to find the maximum area of water
        while (left < right) {
            maximumAreaOfWater = Math.max(maximumAreaOfWater, Math.min(height[left], height[right]) * (right - left));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Return the maximumAreaOfWater
        return maximumAreaOfWater;
    }

    // Main method to test maxArea
    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };

        int result = maxArea(height);

        System.out.println("The maximum area of water is: " + result);
    }
}
