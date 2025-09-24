/*
LeetCode Problem: https://leetcode.com/problems/cheapest-flights-within-k-stops/

Question: 787. Cheapest Flights Within K Stops

Problem Statement: There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

Example 1:
Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

Example 2:
Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.

Example 3:
Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.

Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
 */

/*
Approach:
1. Represent flights as an adjacency list, where each entry stores [neighbor, cost].
2. Use a priority queue (min-heap) that stores [totalCost, currentNode, stopsUsed].
3. Start with source node at cost 0 and 0 stops.
4. At each step:
   - Poll the cheapest state (cost so far).
   - If currentNode == destination, return the cost.
   - If stopsUsed > k, skip this path.
   - For each neighbor, calculate newCost = cost + price.
   - If newCost is better for that neighbor with (stops+1), push it into the heap.
5. If the queue empties without reaching the destination, return -1.

Time Complexity: O(E * logV), where E = flights.length, V = number of cities.
Space Complexity: O(V * K) for storing best costs with respect to stops.
*/

package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _787_Cheapest_Flights_Within_K_Stops {
   // Method to find the cheapest price with at most K stops
   public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
      // Initialize adjacency list for the graph
      ArrayList<ArrayList<int[]>> adjacencyList = new ArrayList<>();

      // Add number of lists equal to the number of nodes
      for (int i = 0; i < n; i++) {
         adjacencyList.add(new ArrayList<>());
      }

      // Build adjacency list according to [neighbor, cost]
      for (int[] flight : flights) {
         adjacencyList.get(flight[0]).add(new int[] { flight[1], flight[2] });
      }

      // Priority queue: [cost, node, stops]
      PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

      // Offer source node src to the queue
      minHeap.offer(new int[] { 0, src, 0 }); // cost = 0, start at src, 0 stops

      // Track best cost with given stops
      int[][] best = new int[n][k + 2];

      // Fill the array
      for (int i = 0; i < n; i++) {
         Arrays.fill(best[i], Integer.MAX_VALUE);
      }

      // Make the start of the src 0
      best[src][0] = 0;

      // Traverse the graph until the queue is empty
      while (!minHeap.isEmpty()) {
         // Get the current array
         int[] current = minHeap.poll();

         // Get all the value of the array
         int cost = current[0], node = current[1], stops = current[2];

         // If destination reached → return cost
         if (node == dst) {
            return cost;
         }

         // If we used more than k stops, skip
         if (stops > k) {
            continue;
         }

         // Traverse neighbors
         for (int[] neighbor : adjacencyList.get(node)) {
            int nextNode = neighbor[0];
            int price = neighbor[1];
            int newCost = cost + price;

            // If cheaper than previously found with same stops
            if (newCost < best[nextNode][stops + 1]) {
               best[nextNode][stops + 1] = newCost;
               minHeap.offer(new int[] { newCost, nextNode, stops + 1 });
            }
         }
      }

      // No path found within k stops
      return -1;
   }

   // Main method to test findCheapestPrice
   public static void main(String[] args) {
      int n = 4;
      int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
      int src = 0;
      int dst = 3;
      int k = 1;

      int result = findCheapestPrice(n, flights, src, dst, k);

      System.out.println("The cheapest flight from source to destination is : " + result);
   }
}