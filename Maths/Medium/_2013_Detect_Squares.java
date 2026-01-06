/*
LeetCode Problem: https://leetcode.com/problems/detect-squares/

Question: 2013. Detect Squares

Problem Statement: You are given a stream of points on the X-Y plane. Design an algorithm that:

Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.

Implement the DetectSquares class:

DetectSquares() Initializes the object with an empty data structure.
void add(int[] point) Adds a new point point = [x, y] to the data structure.
int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.

Example 1:
Input
["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
Output
[null, null, null, null, 1, 0, null, 2]

Explanation
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]); // return 1. You can choose:
detectSquares.add([11, 2]); //   - The first, second, and third points
detectSquares.add([3, 2]); // return 0. The query point cannot form a square with any points in the data structure.
detectSquares.count([11, 10]); // Adding duplicate points is allowed.
detectSquares.count([14, 8]); // return 2. You can choose:
detectSquares.add([11, 2]); //   - The first, second, and third points
detectSquares.count([11, 10]); //   - The first, third, and fourth points

Constraints:

point.length == 2
0 <= x, y <= 1000
At most 3000 calls in total will be made to add and count.
*/

/*
Approach: HashMap Counting + Diagonal Enumeration

Goal:
Support two operations:
- add(point): store a point on a 2D plane.
- count(point): count the number of axis-aligned squares
  that can be formed using the given point as one corner.

Key Observations:
- For an axis-aligned square with (px, py) as one corner:
  • The opposite corner (x, y) must satisfy:
      |px − x| == |py − y|
      x != px and y != py
  • The other two required corners are:
      (x, py) and (px, y)

Data Structures:
- ptsCount: HashMap<point, frequency> to count duplicate points.
- pts: list of all added points for iteration.

Algorithm:
1. add(point):
   - Convert point to (x, y) list.
   - Increment its frequency in ptsCount.
   - Append it to pts.

2. count(point):
   - For each stored point (x, y):
     • Check if it forms a valid diagonal with (px, py).
     • If valid:
         result += count(x, py) × count(px, y)
   - Return result.

Why It Works:
- Each valid diagonal uniquely determines a square.
- Frequency map handles duplicate points correctly.

Time Complexity:
- add(): O(1)
- count(): O(n)

Space Complexity: O(n)
*/

package Maths.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _2013_Detect_Squares {
    /**
     * Your DetectSquares object will be instantiated and called as such:
     * DetectSquares obj = new DetectSquares();
     * obj.add(point);
     * int param_2 = obj.count(point);
     */

    // Class to make the DetectSquares
    private static class DetectSquares {
        // Initialize the hash map for the points count
        private final HashMap<List<Integer>, Integer> ptsCount;

        // Initialize the Array list of list for the points
        private final List<List<Integer>> pts;

        public DetectSquares() {
            // Initialize the hash map for the points count
            this.ptsCount = new HashMap<>();

            // Initialize the Array list of list for the points
            this.pts = new ArrayList<>();
        }

        public void add(int[] point) {
            // Initialize the list of the points
            List<Integer> p = Arrays.asList(point[0], point[1]);

            // Add the points count to the hash map
            this.ptsCount.put(p, this.ptsCount.getOrDefault(p, 0) + 1);

            // Add the points to the pts
            this.pts.add(p);
        }

        public int count(int[] point) {
            // Initialize the result variable
            int result = 0;

            // Get the points
            int px = point[0], py = point[1];

            // Iterate over the pts list to get all the squares
            for (List<Integer> p : this.pts) {
                // Get the points in x and y
                int x = p.get(0), y = p.get(1);

                // If distance of x and y from px and py respectivly is not same or x is equal
                // to px or y is equal to py then skip the iteration
                if (Math.abs(px - x) != Math.abs(py - y) || x == px || y == py) {
                    continue;
                }

                // Get the points related to x and y
                result += ptsCount.getOrDefault(Arrays.asList(x, py), 0)
                        * ptsCount.getOrDefault(Arrays.asList(px, y), 0);
            }

            // Return the result variable
            return result;
        }
    }

    // Main method to test DetectSquares
    public static void main(String[] args) {
        String[] operations = {
                "DetectSquares", "add", "add", "add", "count", "count", "add", "count"
        };

        List<int[][]> values = new ArrayList<>();

        values.add(new int[][] {}); // DetectSquares
        values.add(new int[][] { { 3, 10 } }); // add
        values.add(new int[][] { { 11, 2 } }); // add
        values.add(new int[][] { { 3, 2 } }); // add
        values.add(new int[][] { { 11, 10 } }); // count
        values.add(new int[][] { { 14, 8 } }); // count
        values.add(new int[][] { { 11, 2 } }); // add
        values.add(new int[][] { { 11, 10 } }); // count

        DetectSquares detectSquares = new DetectSquares();

        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            if (op.equals("DetectSquares")) {
                detectSquares = new DetectSquares();
                System.out.println("null");
            }
            if (op.equals("add")) {
                detectSquares.add(values.get(i)[0]);
                System.out.println("null");
            }
            if (op.equals("count")) {
                System.out.println(detectSquares.count(values.get(i)[0]));
            }
        }
    }

}
