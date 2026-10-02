/*
LeetCode Problem: https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/

Question: 2477. Minimum Fuel Cost to Report to the Capital

Problem Statement: There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.

There is a meeting for the representatives of each city. The meeting is in the capital city.

There is a car in each city. You are given an integer seats that indicates the number of seats in each car.

A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.

Return the minimum number of liters of fuel to reach the capital city.

Example 1:
Input: roads = [[0,1],[0,2],[0,3]], seats = 5
Output: 3
Explanation: 
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative2 goes directly to the capital with 1 liter of fuel.
- Representative3 goes directly to the capital with 1 liter of fuel.
It costs 3 liters of fuel at minimum. 
It can be proven that 3 is the minimum number of liters of fuel needed.

Example 2:
Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
Output: 7
Explanation: 
- Representative2 goes directly to city 3 with 1 liter of fuel.
- Representative2 and representative3 go together to city 1 with 1 liter of fuel.
- Representative2 and representative3 go together to the capital with 1 liter of fuel.
- Representative1 goes directly to the capital with 1 liter of fuel.
- Representative5 goes directly to the capital with 1 liter of fuel.
- Representative6 goes directly to city 4 with 1 liter of fuel.
- Representative4 and representative6 go together to the capital with 1 liter of fuel.
It costs 7 liters of fuel at minimum. 
It can be proven that 7 is the minimum number of liters of fuel needed.

Example 3:
Input: roads = [], seats = 1
Output: 0
Explanation: No representatives need to travel to the capital city.

Constraints:
1 <= n <= 10^5
roads.length == n - 1
roads[i].length == 2
0 <= ai, bi < n
ai != bi
roads represents a valid tree.
1 <= seats <= 10^5
*/

/*
Approach: Post-order DFS with Passenger Accumulation and Edge Cost Counting
Goal:
- Find the minimum fuel to move all representatives
  from every node to node 0, where each road
  segment costs ceil(passengers / seats) fuel.
Core Idea:
- Root the tree at node 0. Each subtree's
  representatives must all travel up through their
  subtree root's edge to eventually reach node 0.
- The fuel cost of each edge equals
  ceil(passengers / seats), where passengers is
  the total number of people traveling across that
  edge (all nodes in the subtree below it,
  including the subtree root's own representative).
- Post-order DFS computes subtree sizes bottom-up,
  accumulating fuel cost at each edge as people
  pass through.
Algorithm Steps:
1. Build an undirected adjacency list from roads.
2. Call dfs(0, -1, seats, adjList).
3. In dfs(node, parent, seats, adjList):
   a. Initialize passengers = 0.
   b. For each child (neighbor != parent):
      - p = dfs(child, node, ...) (people from
        child's full subtree including child itself).
      - passengers += p.
      - result += ceil(p / seats) (fuel for this
        edge).
   c. Return passengers + 1 (include this node's
      own representative).
4. Return result.
Why It Works:
- Each person travels exactly once across every
  edge on their unique path to node 0; summing
  ceil(subtree_size / seats) over all edges
  correctly computes total fuel without simulating
  individual trips.
- Post-order processing ensures the full subtree
  count is known before the edge cost is computed.
- Returning passengers + 1 propagates both the
  subtree's people and the current node's
  representative upward in one value.
Time Complexity:
- O(n)
where n is the number of nodes, since each node
and edge is visited exactly once.
Space Complexity:
- O(n)
for the adjacency list and O(n) recursive call
stack in the worst case of a linear chain.
Result:
- Returns the minimum total fuel to transport all
  representatives to node 0.
*/

package Graphs.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Initialize the result variable
  private long result = 0;

  // Method to find the minimum number of liters of fuel to reach the capital city
  public long minimumFuelCost(int[][] roads, int seats) {
    // Initialize the adj list
    ArrayList<Integer>[] adjList = new ArrayList[roads.length + 1];

    // Make the list for length of roads
    for (int i = 0; i < roads.length + 1; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Fill the adjList
    for (int i = 0; i < roads.length; i++) {
      adjList[roads[i][0]].add(roads[i][1]);
      adjList[roads[i][1]].add(roads[i][0]);
    }

    // Call the recursive dfs method
    this.dfs(0, -1, seats, adjList);

    // Return the result
    return this.result;
  }

  // Helper method for the dfs
  private long dfs(int node, int parent, int seats, ArrayList<Integer>[] adjList) {
    // Initialize the passengers variable
    long passengers = 0;

    // Iterate over the childrens
    for (int child : adjList[node]) {
      // If child and parent are not same then preform a dfs
      if (child != parent) {
        // Get the people on the child node
        long p = this.dfs(child, node, seats, adjList);

        // Update the passengers
        passengers += p;

        // Update the result
        this.result += Math.ceil((double) p / seats);
      }
    }

    // Return the passengers
    return passengers + 1;
  }
}

public class _2477_Minimum_Fuel_Cost_to_Report_to_the_Capital {
  // Main method to test minimumFuelCost
  public static void main(String[] args) {
    int[][] roads = new int[][] { { 3, 1 }, { 3, 2 }, { 1, 0 }, { 0, 4 }, { 0, 5 }, { 4, 6 } };
    int seats = 2;

    long result = new Solution().minimumFuelCost(roads, seats);

    System.out.println("The minimum number of liters of fuel to reach the capital city is : " + result);
  }
}
