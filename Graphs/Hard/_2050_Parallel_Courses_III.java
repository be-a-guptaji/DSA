/*
LeetCode Problem: https://leetcode.com/problems/parallel-courses-iii/

Question: 2050. Parallel Courses III

Problem Statement: You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship). Furthermore, you are given a 0-indexed integer array time where time[i] denotes how many months it takes to complete the (i+1)th course.

You must find the minimum number of months needed to complete all the courses following these rules:

You may start taking a course at any time if the prerequisites are met.
Any number of courses can be taken at the same time.
Return the minimum number of months needed to complete all the courses.

Note: The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph).

Example 1:
Input: n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
Output: 8
Explanation: The figure above represents the given graph and the time required to complete each course. 
We start course 1 and course 2 simultaneously at month 0.
Course 1 takes 3 months and course 2 takes 2 months to complete respectively.
Thus, the earliest time we can start course 3 is at month 3, and the total time required is 3 + 5 = 8 months.

Example 2:
Input: n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
Output: 12
Explanation: The figure above represents the given graph and the time required to complete each course.
You can start courses 1, 2, and 3 at month 0.
You can complete them after 1, 2, and 3 months respectively.
Course 4 can be taken only after course 3 is completed, i.e., after 3 months. It is completed after 3 + 4 = 7 months.
Course 5 can be taken only after courses 1, 2, 3, and 4 have been completed, i.e., after max(1,2,3,7) = 7 months.
Thus, the minimum time needed to complete all the courses is 7 + 5 = 12 months.

Constraints:
1 <= n <= 5 * 10^4
0 <= relations.length <= min(n * (n - 1) / 2, 5 * 10^4)
relations[j].length == 2
1 <= prevCoursej, nextCoursej <= n
prevCoursej != nextCoursej
All the pairs [prevCoursej, nextCoursej] are unique.
time.length == n
1 <= time[i] <= 10^4
The given graph is a directed acyclic graph.
*/

/*
Approach: Top-down Memoized DFS for Longest Weighted Path in DAG
Goal:
- Find the minimum number of months to complete
  all courses, where courses have prerequisites
  and each takes a given number of months.
  Courses without unmet prerequisites can be taken
  in parallel.
Core Idea:
- The answer is the longest weighted path in the
  DAG, where path weight is the sum of time[] for
  all courses on the path.
- dp[node] = maximum time to complete node and all
  its prerequisites = time[node] + max(dp[neighbor])
  over all neighbors (courses node unlocks).
- Memoize dp[node] after first computation;
  dp[node] != 0 serves as the computed sentinel
  since time[i] >= 1 by problem constraints.
Algorithm Steps:
1. Build directed adjacency list from relations
   (prerequisite -> course, 0-indexed).
2. For each node, call dfs(node) if not yet
   computed.
3. In dfs(node):
   a. If dp[node] != 0, return cached result.
   b. Initialize result = time[node].
   c. For each neighbor, result = max(result,
      dfs(neighbor) + time[node]).
   d. Cache and return dp[node] = result.
4. Return max(dp[]) as the answer.
Why It Works:
- The DAG structure (guaranteed by problem
  constraints) means DFS terminates without cycle
  checking.
- dp[node] accumulates the longest prerequisite
  chain ending at node; the global max over all
  dp values is the critical path, which determines
  the minimum completion time when parallelism is
  maximized.
- dp[node] != 0 as a cache sentinel is valid since
  all time values are >= 1, making 0 an impossible
  computed result.
Time Complexity:
- O(n + e) where n is the number of courses and e
  is the number of prerequisite relations; each
  node and edge is processed once via memoization.
Space Complexity:
- O(n + e) for the adjacency list, dp array, and
  call stack.
Result:
- Returns the minimum months needed to complete
  all courses with optimal parallelism.
*/

package Graphs.Hard;

import java.util.ArrayList;

// Solution Class
class Solution {
  // Method to find the minimum number of months needed to complete all the
  // courses
  public int minimumTime(int n, int[][] relations, int[] time) {
    // Initialize the dp array and seen array
    int[] dp = new int[n];

    // Initialize the arraylist for the adjlist
    ArrayList<Integer>[] adjList = new ArrayList[n];

    // Initialize the array list for all nodes
    for (int i = 0; i < n; i++) {
      adjList[i] = new ArrayList<>();
    }

    // Make the adjList
    for (int i = 0; i < relations.length; i++) {
      adjList[relations[i][0] - 1].add(relations[i][1] - 1);
    }

    // Initialize the result variable
    int result = Integer.MIN_VALUE;

    // Iterate over all the nodes
    for (int i = 0; i < n; i++) {
      // Make the dfs call and update the result
      this.dfs(i, adjList, dp, time);
    }

    // Iterate over the dp array to get the result
    for (int i = 0; i < n; i++) {
      result = Math.max(result, dp[i]);
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private int dfs(int node, ArrayList<Integer>[] adjList, int[] dp, int[] time) {
    // If dp[node] is not zero then return the result
    if (dp[node] != 0) {
      return dp[node];
    }

    // Initialize the result variable
    int result = time[node];

    // Iterate over all the neighbour
    for (int neighbour : adjList[node]) {
      // Make the dfs call and update the result
      result = Math.max(result, this.dfs(neighbour, adjList, dp, time) + time[node]);
    }

    // Update the dp array
    return dp[node] = result;
  }
}

public class _2050_Parallel_Courses_III {
  // Main method to test minimumTime
  public static void main(String[] args) {
    int n = 3;
    int[][] relations = new int[][] { { 1, 3 }, { 2, 3 } };
    int[] time = new int[] { 3, 2, 5 };

    int result = new Solution().minimumTime(n, relations, time);

    System.out.println("The minimum number of months needed to complete all the courses is : " + result);
  }
}