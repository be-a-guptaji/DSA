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
Approach: This solution uses a greedy strategy combined with sorting. 
First, the input intervals are sorted based on their starting points. 
Then, we iterate through the sorted intervals and merge overlapping ones. 
Two intervals overlap if the start of the current interval is less than or equal 
to the end of the previous (last merged) interval. 
If they overlap, we update the end of the last interval with the maximum of both ends. 
Otherwise, we add the current interval to the result list as a new non-overlapping interval.

Time Complexity: O(n log n), where n is the number of intervals. 
We spend O(n log n) time to sort the intervals, and then O(n) time to iterate and merge.

Space Complexity: O(n), for the output list of merged intervals. 
No extra data structures are used beyond the result.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _57_Insert_Interval {
    // Method to insert the new interval in the intervals
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> mergedList = new ArrayList<>();

        for (int[] currentInterval : intervals) {
            int lastIntervalIndex = mergedList.size() - 1;
            if (newInterval[1] < currentInterval[0]) {
                System.out.println("If");
                if (currentInterval[0] > mergedList.get(lastIntervalIndex)[0]) {
                    int[] lastInterval = mergedList.removeLast();
                    mergedList.add(new int[] { Math.min(lastInterval[0], currentInterval[0]),
                            Math.max(lastInterval[1], currentInterval[1]) });
                } else {
                    mergedList.add(new int[] { currentInterval[0], Math.max(newInterval[1], currentInterval[1]) });
                }

                // newInterval = mergedList.removeLast();
            } else if (currentInterval[1] > newInterval[0]) {
                System.out.println("Else If");
                if (currentInterval[0] > mergedList.get(lastIntervalIndex)[0]) {
                    int[] lastInterval = mergedList.removeLast();
                    mergedList.add(new int[] { Math.min(lastInterval[0], currentInterval[0]),
                            Math.max(lastInterval[1], currentInterval[1]) });
                } else {
                    mergedList.add(new int[] { currentInterval[0], Math.max(newInterval[1], currentInterval[1]) });
                }
                // newInterval = mergedList.removeLast();
            } else {
                System.out.println("Else");
                mergedList.add(currentInterval);
            }
            System.out.println(newInterval[0] + " " + newInterval[1]);
        }

        // Convert the List to array of arrays
        int[][] result = new int[mergedList.size()][];
        for (int i = 0; i < mergedList.size(); i++) {
            result[i] = mergedList.get(i);
        }

        // Return the result=-
        return result;
    }

    // Main method to test insert
    public static void main(String[] args) {
        int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };

        int[] newInterval = { 4, 8 };

        int[][] result = insert(intervals, newInterval);

        System.out.println("The merge intervals are : " + Arrays.deepToString(result));
    }
}
