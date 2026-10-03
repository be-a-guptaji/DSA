/*
LeetCode Problem: https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/

Question: 2492. Minimum Score of a Path Between Two Cities

Problem Statement: You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.

The score of a path between two cities is defined as the minimum distance of a road in this path.

Return the minimum possible score of a path between cities 1 and n.

Note:
A path is a sequence of roads between two cities.
It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
The test cases are generated such that there is at least one path between 1 and n.

Example 1:
Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
Output: 5
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.

Example 2:
Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
Output: 2
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.

Constraints:
2 <= n <= 10^5
1 <= roads.length <= 10^5
roads[i].length == 3
1 <= ai, bi <= n
ai != bi
1 <= distancei <= 10^4
There are no repeated edges.
There is at least one path between 1 and n.
*/

/*
Approach: DFS Connected Component Minimum Edge Weight
Goal:
- Find the minimum edge weight on any path between
  city 1 and city n, considering all edges in the
  same connected component as city 1.
Core Idea:
- Any path between city 1 and city n can be
  extended or rerouted through any edge in the
  same connected component, so the answer is simply
  the minimum edge weight in the entire connected
  component containing city 1.
- DFS from node 0 (city 1) visits every reachable
  node and tracks the minimum edge weight
  encountered across all traversed edges.
Algorithm Steps:
1. Build an undirected adjacency list storing
   {neighbor, distance} pairs (0-indexed).
2. Call dfs(0, adjList, seen).
3. In dfs(node, adjList, seen):
   a. If seen[node], return.
   b. Mark seen[node] = true.
   c. For each {neighbor, weight} in adjList[node]:
      - Update result = min(result, weight).
      - Recurse dfs(neighbor, adjList, seen).
4. Return result.
Why It Works:
- Since any edge in the connected component can be
  included in some valid path from city 1 to city n
  (by extending the path to traverse that edge),
  the minimum score achievable is the global
  minimum edge weight in the component.
- DFS naturally visits all nodes and edges in the
  component exactly once, collecting the minimum
  weight without needing explicit path tracking.
Time Complexity:
- O(n + e)
where n is the number of cities and e is the number
of roads, since each node and edge is visited once.
Space Complexity:
- O(n + e)
for the adjacency list, seen array, and O(n)
recursive call stack in the worst case.
Result:
- Returns the minimum possible score of any path
  between city 1 and city n.
*/

package Graphs.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Initialize the result variable
  private int result = Integer.MAX_VALUE;

  // Method to find the minimum possible score of a path between cities 1 and n
  public int minScore(int n, int[][] roads) {
    // Initialize the Array list of the roads and distance
    ArrayList<int[]>[] adjList = new ArrayList[n];

    // Fill the ArrayList
    for (int i = 0; i < n; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Fill the adjList
    for (int[] arr : roads) {
      adjList[arr[0] - 1].add(new int[] { arr[1] - 1, arr[2] });
      adjList[arr[1] - 1].add(new int[] { arr[0] - 1, arr[2] });
    }

    // Call the recursive dfs method
    this.dfs(0, adjList, new boolean[n]);

    // Return the result
    return this.result;
  }

  // Helper method for the dfs
  private void dfs(int node, ArrayList<int[]>[] adjList, boolean[] seen) {
    // If we already seen the node then return
    if (seen[node]) {
      return;
    }

    // Mark the node seen to true
    seen[node] = true;

    // Iterate over the adjacent node
    for (int[] arr : adjList[node]) {
      // Update the result
      this.result = Math.min(this.result, arr[1]);

      // Call the recursive dfs method
      this.dfs(arr[0], adjList, seen);
    }
  }
}

public class _2492_Minimum_Score_of_a_Path_Between_Two_Cities {
  // Main method to test minScore
  public static void main(String[] args) {
    int n = 2;
    int[][] roads = new int[][] { { 1, 2, 2 }, { 1, 3, 4 }, { 3, 4, 7 } };

    int result = new Solution().minScore(n, roads);

    System.out.println("The minimum possible score of a path between cities 1 and n is : " + result);
  }
}
