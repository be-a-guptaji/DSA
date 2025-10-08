/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

Question: 452. Minimum Number of Arrows to Burst Balloons

Problem Statement: There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

Example 2:
Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

Example 3:
Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

Constraints:

1 <= points.length <= 10^5
points[i].length == 2
-23^1 <= xstart < xend <= 23^1 - 1
*/

/*
Approach:
This solution uses a greedy strategy to minimize the number of arrows required to burst all balloons.
- Each balloon is represented as an interval [start, end].
- We sort all balloon intervals based on their end coordinate in ascending order.
- The idea is to always shoot an arrow at the end of the current interval, as this maximizes the number of overlapping balloons burst with a single arrow.
- We initialize the number of arrows to 1 (since at least one arrow is needed) and set the initial end to the end of the first balloon.
- As we iterate through the balloons:
    - If a balloon starts after the current end, it means it cannot be burst by the current arrow.
      So, we need to shoot a new arrow and update the end to this balloon’s end.
    - If the balloon starts before or at the current end, it means it's already burst by the current arrow.
- In the end, the number of arrows represents the minimum number required to burst all balloons.

Time Complexity: O(n log n), due to sorting the balloon intervals.
Space Complexity: O(1), as only a few variables are used.
*/

package Greedy.Medium;

import java.util.Arrays;

public class _452_Minimum_Number_of_Arrows_to_Burst_Balloons {
    // Method to find the minimum number of arrows needed to pop all the balloons
    public static int findMinArrowShots(int[][] points) {
        // Sort balloons by their end point
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        // Initialize the arrows and end variable
        int arrows = 1;
        int end = points[0][1];

        for (int i = 1; i < points.length; i++) {
            // If the current balloon starts after the end of the last burst range
            if (points[i][0] > end) {
                arrows++; // Need a new arrow
                end = points[i][1]; // Update end to new balloon's end
            }
        }

        // Retrun the number of arraows
        return arrows;
    }

    // Main method to test findMinArrowShots
    public static void main(String[] args) {
        int[][] points = { { 3, 9 }, { 7, 12 }, { 3, 8 }, { 6, 8 }, { 9, 10 }, { 2, 9 }, { 0, 9 }, { 3, 9 }, { 0, 6 },
                { 2, 8 } };

        int result = findMinArrowShots(points);

        System.out.println("The minimum number of arrows needed to pop all the balloons is : " + result);
    }
}
