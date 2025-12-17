/*
LeetCode Problem: https://leetcode.com/problems/non-overlapping-intervals/

Question: 435. Non-overlapping Intervals

Problem Statement: Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Constraints:

1 <= intervals.length <= 10^5
intervals[i].length == 2
-5 * 10^4 <= starti < endi <= 5 * 10^4
*/

/*
Approach: Greedy Interval Scheduling

Goal:
Remove the minimum number of intervals so that the remaining intervals do not overlap.

Key Idea:
To keep as many non-overlapping intervals as possible, always choose the interval
that finishes earliest. This leaves maximum room for future intervals.

Algorithm:
1. If no intervals exist, return 0.
2. Sort all intervals by their end time.
3. Initialize:
   - previousEnd = end time of the first interval
   - removals = 0
4. Traverse intervals from the second one:
   - If current.start < previousEnd → overlap detected
     → remove the current interval (increment removals).
   - Else → no overlap
     → update previousEnd = current.end.
5. Return removals.

Why It Works:
- Selecting intervals with the earliest end time minimizes future conflicts.
- Greedy choice is optimal for interval scheduling problems.

Time Complexity: O(n log n)  (due to sorting)
Space Complexity: O(1)       (ignoring sort overhead)
*/

package DynamicProgramming.Medium;

import java.util.Arrays;

public class _435_Non_overlapping_Intervals {
    // Method to find the minimum number of intervals to remove
    // so that the remaining intervals do not overlap
    public static int eraseOverlapIntervals(int[][] intervals) {

        // If there are no intervals, no removals are needed
        if (intervals.length == 0) {
            return 0;
        }

        // Sort the intervals based on their ending time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        // Variable to store the count of intervals to remove
        int removals = 0;

        // Store the end time of the last non-overlapping interval
        int previousEnd = intervals[0][1];

        // Iterate through the intervals starting from the second one
        for (int i = 1; i < intervals.length; i++) {

            // If the current interval overlaps with the previous one
            if (intervals[i][0] < previousEnd) {

                // Increment the removal count
                removals++;
            } else {

                // Update the previous end time as there is no overlap
                previousEnd = intervals[i][1];
            }
        }

        // Return the minimum number of intervals to remove
        return removals;
    }

    // Main method to test eraseOverlapIntervals
    public static void main(String[] args) {
        int[][] intervals = new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };

        int result = eraseOverlapIntervals(intervals);

        System.out.println(
                "The minimum number of intervals you need to remove to make the rest of the intervals non-overlapping is : "
                        + result);
    }
}
