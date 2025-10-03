/*
LeetCode Problem: https://leetcode.com/problems/k-closest-points-to-origin/

Question: 973. K Closest Points to Origin

Problem Statement: Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

Example 1:
Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.

Constraints:

1 <= k <= points.length <= 10^4
-10^4 <= xi, yi <= 10^4
*/

/*
Approach:
We sort the array based on the squared Euclidean distance of each point from the origin (0, 0).
We use squared distance to avoid unnecessary square root computations, as relative distances remain the same.
After sorting, we return the first k elements from the array, which are the k closest points.

Time Complexity: O(n log n), Sorting the array takes O(n log n) time, where n is the number of points.
Space Complexity: O(log n), Due to recursion stack space used by the sorting algorithm (TimSort).
*/

package HeapsAndPriorityQueues.Medium;

import java.util.Arrays;

public class _973_K_Closest_Points_to_Origin {
    // Method to find the k closest point from the origin
    public static int[][] kClosest(int[][] points, int k) {
        // Sort on the basis of distance from the origin
        Arrays.sort(points, (a, b) -> (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1]));

        // Retrun the result points from 0 to k
        return Arrays.copyOfRange(points, 0, k);
    }

    // Main method to test kClosest
    public static void main(String[] args) {
        int[][] matrix = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        int k = 2;

        int[][] result = kClosest(matrix, k);

        System.out.println("The k closest point from the origin are : " + Arrays.deepToString(result));
    }
}
