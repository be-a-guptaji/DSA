/*
LeetCode Problem: https://leetcode.com/problems/course-schedule-iv/

Question: 1462. Course Schedule IV

Problem Statement: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.

Example 2:
Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.

Example 3:
Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]

Constraints:
2 <= numCourses <= 100
0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
prerequisites[i].length == 2
0 <= ai, bi <= numCourses - 1
ai != bi
All the pairs [ai, bi] are unique.
The prerequisites graph has no cycles.
1 <= queries.length <= 10^4
0 <= ui, vi <= numCourses - 1
ui != vi
*/

/*
Approach: DFS with Transitive Reachability Propagation
Goal:
- For each query (u, v), determine if course u is
  a prerequisite (direct or transitive) of course v.
Core Idea:
- Build a reachability matrix isPrereq[u][v] = true
  if v is reachable from u following prerequisite
  edges.
- DFS from each node propagates reachability
  bottom-up: after fully exploring a neighbor,
  merge its reachability row into the source node's
  row (transitive closure).
- Each node is processed at most once via a visited
  array; queries are then answered in O(1) via
  isPrereq lookup.
Algorithm Steps:
1. Build adjacency list from prerequisites.
2. Initialize isPrereq[n][n] = false, visited[n]
   = false.
3. For each unvisited node i, call dfs(i, ...):
   a. Mark visited[i] = true.
   b. For each neighbor of i:
      - Recurse dfs(neighbor, ...) to fully resolve
        neighbor's reachability first.
      - Set isPrereq[i][neighbor] = true (direct
        edge).
      - For each k, if isPrereq[neighbor][k] is
        true, set isPrereq[i][k] = true (transitive
        closure via neighbor).
4. Answer each query (u, v) as isPrereq[u][v].
Why It Works:
- Post-order processing (recurse before merging)
  guarantees that when isPrereq[neighbor] is merged
  into isPrereq[src], neighbor's row already
  contains its full transitive reachability.
- The visited guard prevents reprocessing nodes,
  ensuring each node's reachability row is computed
  exactly once across all DFS calls.
- OR-merging neighbor rows builds the transitive
  closure incrementally without a separate
  Floyd-Warshall pass.
Time Complexity:
- O(V^2 + V * E)
where V is numCourses and E is the number of
prerequisites. Each node merges up to V bits per
neighbor across all DFS calls.
Space Complexity:
- O(V^2)
for the isPrereq matrix, plus O(V) for the call
stack and visited array.
Result:
- Returns a Boolean list where result[i] answers
  queries[i] as true if queries[i][0] is a
  prerequisite of queries[i][1].
*/

package Graphs.Medium;

import java.util.ArrayList;

// Solution Class
class Solution {
  public ArrayList<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
    // Initialize the array of array list
    ArrayList<Integer>[] adj = new ArrayList[numCourses];

    // Initialize all the adj list
    for (int i = 0; i < numCourses; i++) {
      adj[i] = new ArrayList<>();
    }

    // Make the adj list
    for (int[] edge : prerequisites) {
      adj[edge[0]].add(edge[1]);
    }

    // Initialize the isPrereq and visited
    boolean[][] isPrereq = new boolean[numCourses][numCourses];
    boolean[] visited = new boolean[numCourses];

    // Iterate over the courses
    for (int i = 0; i < numCourses; i++) {
      // Call the recursive dfs method
      this.dfs(i, adj, isPrereq, visited);
    }

    // Initialize the result array list
    ArrayList<Boolean> result = new ArrayList<>();

    // Fill the result list
    for (int[] q : queries) {
      result.add(isPrereq[q[0]][q[1]]);
    }

    // Return the result list
    return result;
  }

  // Helper method for the dfs
  private void dfs(int src, ArrayList<Integer>[] adj, boolean[][] isPrereq, boolean[] visited) {
    // If we have seen the node then return
    if (visited[src]) {
      return;
    }

    // Mark the node as true
    visited[src] = true;

    // Iterate over the neighbor
    for (int neighbor : adj[src]) {
      // Call the recursive dfs method
      this.dfs(neighbor, adj, isPrereq, visited);

      // Direct edge
      isPrereq[src][neighbor] = true;

      // Transitive reachability: everything reachable from neighbor is reachable from
      // src
      for (int k = 0; k < isPrereq.length; k++) {
        if (isPrereq[neighbor][k]) {
          isPrereq[src][k] = true;
        }
      }
    }
  }
}

public class _1462_Course_Schedule_IV {
  // Main method to test checkIfPrerequisite
  public static void main(String[] args) {
    int numCourses = 2;
    int[][] prerequisites = new int[][] { { 1, 0 } };
    int[][] queries = new int[][] { { 0, 1 }, { 1, 0 } };

    ArrayList<Boolean> result = new Solution().checkIfPrerequisite(numCourses, prerequisites, queries);

    System.out.println("A boolean array answer is : " + result);
  }
}
