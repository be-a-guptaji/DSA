/*
LeetCode Problem: https://leetcode.com/problems/find-champion-ii/description/

Question: 2924. Find Champion II

Problem Statement: There are n teams numbered from 0 to n - 1 in a tournament; each team is also a node in a DAG.

You are given the integer n and a 0-indexed 2D integer array edges of length m representing the DAG, where edges[i] = [ui, vi] indicates that there is a directed edge from team ui to team vi in the graph.

A directed edge from a to b in the graph means that team a is stronger than team b and team b is weaker than team a.

Team a will be the champion of the tournament if there is no team b that is stronger than team a.

Return the team that will be the champion of the tournament if there is a unique champion, otherwise, return -1.

Notes

A cycle is a series of nodes a1, a2, ..., an, an+1 such that node a1 is the same node as node an+1, the nodes a1, a2, ..., an are distinct, and there is a directed edge from the node ai to node ai+1 for every i in the range [1, n].
A DAG is a directed graph that does not have any cycle.

Example 1:
Input: n = 3, edges = [[0,1],[1,2]]
Output: 0
Explanation: Team 1 is weaker than team 0. Team 2 is weaker than team 1. So the champion is team 0.

Example 2:
Input: n = 4, edges = [[0,2],[1,3],[1,2]]
Output: -1
Explanation: Team 2 is weaker than team 0 and team 1. Team 3 is weaker than team 1. But team 1 and team 0 are not weaker than any other teams. So the answer is -1.

Constraints:
1 <= n <= 100
m == edges.length
0 <= m <= n * (n - 1) / 2
edges[i].length == 2
0 <= edge[i][j] <= n - 1
edges[i][0] != edges[i][1]
The input is generated such that if team a is stronger than team b, team b is not stronger than team a.
The input is generated such that if team a is stronger than team b and team b is stronger than team c, then team a is stronger than team c.
*/

/*
Approach: In-degree Zero Node Detection
Goal:
- Find the unique champion: the team with no losses
  (no incoming edges in the DAG), or return -1 if
  no unique champion exists.
Core Idea:
- In the tournament DAG, an edge (u, v) means u
  beats v. A team can only be champion if it was
  never beaten, meaning it has no incoming edges
  (in-degree zero).
- Mark every team that appears as a loser (edge
  destination); the champion must be the sole
  unmarked team.
Algorithm Steps:
1. Build hasParent[n]: mark hasParent[v] = true
   for every edge (u, v).
2. Scan hasParent[]:
   - Track the first team with hasParent[i] == false
     as the candidate result.
   - If a second such team is found, return -1
     (no unique champion).
3. Return result, or -1 if no team was found
   with in-degree zero.
Time Complexity:
- O(n + e)
where n is the number of teams and e is the number
of edges, for marking and scanning.
Space Complexity:
- O(n)
for the hasParent array.
Result:
- Returns the index of the unique champion, or -1
  if none exists.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Method to find the team that will be the champion of the tournament if there
  // is a unique champion
  public int findChampion(int n, int[][] edges) {
    // Initialize the boolean array of size n
    boolean[] hasParent = new boolean[n];

    // Iterate over the edges grid
    for (int i = 0; i < edges.length; i++) {
      hasParent[edges[i][1]] = true;
    }

    // Initialize the result variable
    int result = Integer.MIN_VALUE;

    // Iterate over the hasParent array
    for (int i = 0; i < n; i++) {
      if (result == Integer.MIN_VALUE && !hasParent[i]) {
        result = i;
      } else if (result != Integer.MIN_VALUE && !hasParent[i]) {
        return -1;
      }
    }

    // Return the result
    return result == Integer.MIN_VALUE ? -1 : result;
  }
}

public class _2924_Find_Champion_II {
  // Main method to test findChampion
  public static void main(String[] args) {
    int n = 4;
    int[][] edges = new int[][] { { 0, 2 }, { 1, 3 }, { 1, 2 } };

    int result = new Solution().findChampion(n, edges);

    System.out
        .println("The team that will be the champion of the tournament if there is a unique champion is : " + result);
  }
}
