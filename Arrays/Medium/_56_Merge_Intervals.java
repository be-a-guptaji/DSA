/*
LeetCode Problem: https://leetcode.com/problems/merge-intervals/

Question: 56. Merge Intervals

Problem Statement: Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:

1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4
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

public class _56_Merge_Intervals {
    // Method to find the merge intervals
    public static int[][] merge(int[][] intervals) {
        // Make the merged array for the merge intervals
        List<List<Integer>> merged = new ArrayList<>();

        // Sort by first element of each row
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Add first interval to the result array
        merged.add(Arrays.asList(intervals[0][0], intervals[0][1]));

        // Logic to find the merge intervals
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            List<Integer> lastInterval = merged.get(merged.size() - 1);

            // If the last element of the last array element overlap with the current
            // interval the update the last interval of the result else add it to the result
            // list
            if (currentInterval[0] <= lastInterval.get(1)) {
                lastInterval.set(1, Math.max(lastInterval.get(1), currentInterval[1]));
            } else {
                merged.add(Arrays.asList(currentInterval[0], currentInterval[1]));
            }
        }

        // Convert List<List<Integer>> to int[][]
        int[][] result = new int[merged.size()][2];
        for (int i = 0; i < merged.size(); i++) {
            result[i][0] = merged.get(i).get(0);
            result[i][1] = merged.get(i).get(1);
        }

        // Return the merge intervals
        return result;
    }

    // Main method to test merge
    public static void main(String[] args) {
        int[][] intervals = { { 1, 4 }, { 4, 5 } };

        int[][] result = merge(intervals);

        System.out.println("The merge intervals are : " + Arrays.deepToString(result));
    }
}
