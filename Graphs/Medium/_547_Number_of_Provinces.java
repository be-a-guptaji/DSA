/*
LeetCode Problem: https://leetcode.com/problems/number-of-provinces/

Question: 547. Number of Provinces

Problem Statement: There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

Constraints:
1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
*/

/*
Approach: DFS Connected Component Count on Adjacency Matrix
Goal:
- Count the number of provinces (connected
  components) in an undirected graph represented
  as an adjacency matrix.
Core Idea:
- Each unvisited city starts a new province. DFS
  from it marks all directly and transitively
  connected cities as visited, collapsing the
  entire component in one traversal.
Algorithm Steps:
1. For each unvisited city i, increment result and
   call dfs(i, ...) to mark its full component.
2. In dfs(city, ...):
   - Mark city visited.
   - For each neighbor j where isConnected[city][j]
     == 1 and not visited, recurse.
3. Return result.
Time Complexity:
- O(n^2) — full adjacency matrix scan per node.
Space Complexity:
- O(n) for visited array and call stack.
Result:
- Returns the total number of provinces.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Method to find the total number of provinces
  public int findCircleNum(int[][] isConnected) {
    // Initialize the result variable
    int result = 0;

    // Initialize the isVisited variable
    boolean[] isVisited = new boolean[isConnected.length];

    // Iterate over the grid
    for (int i = 0; i < isConnected.length; i++) {
      // If row is not visited then call the dfs method
      if (!isVisited[i]) {
        // Increment the result variable
        result++;

        // Call the dfs method
        this.dfs(i, isConnected, isVisited);
      }
    }

    // Return the result
    return result;
  }

  // Helper method for the dfs
  private void dfs(int city, int[][] isConnected, boolean[] isVisited) {
    // Set the row visited to true
    isVisited[city] = true;

    // Get the row
    int[] arr = isConnected[city];

    // Iterate over the row
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 1 && !isVisited[i]) {
        this.dfs(i, isConnected, isVisited);
      }
    }
  }
}

public class _547_Number_of_Provinces {
  // Main method to test findCircleNum
  public static void main(String[] args) {
    int[][] isConnected = new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };

    int result = new Solution().findCircleNum(isConnected);

    System.out.println("The total number of provinces is : " + result);
  }
}
