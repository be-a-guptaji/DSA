/*
LeetCode Problem: https://leetcode.com/problems/min-cost-to-connect-all-points/

Question: 1584. Min Cost to Connect All Points

Problem Statement: You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

Example 1:
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.

Example 2:
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18

Constraints:

1 <= points.length <= 1000
-10^6 <= xi, yi <= 10^6
All pairs (xi, yi) are distinct.
 */

/*
Approach: Prim’s Algorithm for Minimum Spanning Tree (MST)

Goal:
Connect all points using minimum total cost. Cost between two points =
Manhattan distance. We need the MST cost.

Algorithm (Prim without PQ):
1. Total number of nodes = n.
2. Maintain:
   - visited[]: marks nodes already included in MST.
   - distance[]: minimum cost to connect each node to MST.
   - result: accumulated MST cost.
   - edges: count of selected edges (or included nodes).

3. Initialize:
   - distance[0] = 0  (start MST at any node)
   - all other distances = ∞

4. Repeat n times:
   - Select the unvisited node having minimum distance.
   - Mark it visited.
   - Add its distance to result.
   - Update distance[] for remaining unvisited nodes using:
       currentDistance = manhattan(points[node], points[i])
       distance[i] = min(distance[i], currentDistance)

5. After processing all nodes, result contains MST cost.

Why It Works:
- Prim’s algorithm grows MST one node at a time.
- distance[] always stores the cheapest connection to MST for each node.
- Choosing minimal unvisited node ensures correctness.

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

package Graphs.Medium;

import java.util.Arrays;

public class _1584_Min_Cost_to_Connect_All_Points {
    // Method to find the minimum cost to make all points connected. All points
    // are connected if there is exactly one simple path between any two points
    public static int minCostConnectPoints(int[][] points) {
        // Initialize the length, node, edges and result
        int n = points.length, node, edges = 0, result = 0;

        // Initialize the boolean array to check if the node is visited or not
        boolean[] visited = new boolean[n];

        // Initialize the distance array for the minimum distance
        int[] distance = new int[n];

        // Initialize the distance array with the Integer.MAX_VALUE
        Arrays.fill(distance, Integer.MAX_VALUE);

        // distance for starting node is zero
        distance[0] = 0;

        // Iterate over the nodes untill edges are one less then the total nodes
        // (Prim logic simplified to iterate n times)
        while (edges < n) {

            // Initialize the next node to explore
            int nextNode = -1;

            // Check the minimum distance from the unvisited nodes
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (nextNode == -1 || distance[i] < distance[nextNode])) {
                    nextNode = i;
                }
            }

            // Mark the node as visited
            visited[nextNode] = true;

            // Add the next node distance to the result
            result += distance[nextNode];

            // Upadate the node to the next node
            node = nextNode;

            // Increment the edges
            edges++;

            // Update distances to other unvisited nodes
            for (int i = 0; i < n; i++) {
                // If node is already visited then skip the iteration
                if (visited[i]) {
                    continue;
                }

                // Get the current distance from the node
                int currentDistance = Math.abs(points[node][0] - points[i][0])
                        + Math.abs(points[node][1] - points[i][1]);

                // Update the current node distance
                distance[i] = Math.min(distance[i], currentDistance);
            }
        }

        // Return the result
        return result;
    }

    // Main method to test minCostConnectPoints
    public static void main(String[] args) {
        int[][] points = new int[][] { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 5, 2 }, { 7, 0 } };

        int result = minCostConnectPoints(points);

        System.out.print(
                "The minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points is : "
                        + result);
    }
}
