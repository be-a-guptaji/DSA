/*
LeetCode Problem: https://leetcode.com/problems/insert-interval/

Question: 57. Insert Interval

Problem Statement: You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return it.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Constraints:

0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^5
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 10^5
*/

/*
Approach: This solution uses a greedy strategy. 
Since the input intervals are already sorted based on their starting points, 
we iterate through the intervals and do the following:
1. Add all intervals ending before the start of newInterval to the result.
2. Merge all intervals that overlap with newInterval.
3. Add all remaining intervals to the result.

Time Complexity: O(n), where n is the number of intervals. 
We iterate through the intervals once.

Space Complexity: O(n), for the output list of merged intervals.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _57_Insert_Interval {
    // Method to insert the new interval in the intervals
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // Make the merged array list for the intervals
        List<int[]> mergedList = new ArrayList<>();

        // Initialize the variable for index and length
        int index = 0;
        int length = intervals.length;

        // Add first non overlapping intervals
        while (index < length && intervals[index][1] < newInterval[0]) {
            mergedList.add(intervals[index]);
            index++;
        }

        // Merge the newInterval and the overlapping intervals
        while (index < length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            index++;
        }

        // Add the newInterval to the mergedList
        mergedList.add(newInterval);

        // Add remaining intervals
        while (index < length) {
            mergedList.add(intervals[index]);
            index++;
        }

        // Return the result
        return mergedList.toArray(new int[mergedList.size()][2]);
    }

    // Main method to test insert
    public static void main(String[] args) {
        int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };

        int[] newInterval = { 4, 8 };

        int[][] result = insert(intervals, newInterval);

        System.out.println("The merge intervals are : " + Arrays.deepToString(result));
    }
}
