/*
LeetCode Problem: https://leetcode.com/problems/count-servers-that-communicate/

Question: 1267. Count Servers that Communicate

Problem Statement: You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.

Return the number of servers that communicate with any other server.

Example 1:
Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.

Example 2:
Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.

Example 3:
Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
Output: 4
Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.

Constraints:
m == grid.length
n == grid[i].length
1 <= m <= 250
1 <= n <= 250
grid[i][j] == 0 or 1
*/

/*
Approach: Row/Column DFS with Connected Component Counting
Goal:
- Count servers that can communicate with at least
  one other server, where two servers communicate
  if they share the same row or column.
Core Idea:
- Servers communicate if they share a row or
  column, forming connected components under this
  adjacency rule.
- DFS from any unvisited server explores its entire
  connected component by scanning its full row and
  column for other servers, then recursively doing
  the same from each found server.
- A component contributes to the answer only if it
  contains more than one server.
Algorithm Steps:
1. For each unvisited server (grid[row][col] == 1):
   a. Call dfs(row, col) to explore the full
      connected component and count its servers.
   b. If the count > 1, add it to maxServer.
2. In dfs(row, col):
   a. If out of bounds or grid[row][col] == 0,
      return 0.
   b. Mark grid[row][col] = 0 (visited).
   c. Initialize serverCount = 1.
   d. Scan all rows in column col; recurse into
      any unvisited server found.
   e. Scan all columns in row row; recurse into
      any unvisited server found.
   f. Return serverCount.
3. Return maxServer.
Why It Works:
- Marking cells as 0 after visiting prevents
  double-counting and infinite recursion.
- Scanning the entire row and column at each step
  correctly captures the communication adjacency:
  any server in the same row or column is reachable
  in one hop, and transitive connections are handled
  by recursion.
- Filtering components of size 1 correctly excludes
  isolated servers that cannot communicate.
Time Complexity:
- O(m * n * (m + n))
where m and n are the grid dimensions. Each server
triggers a full row and column scan of O(m + n)
during DFS, and each cell is visited at most once.
Space Complexity:
- O(m * n)
for the recursive call stack in the worst case of
a fully connected grid.
Result:
- Returns the count of servers that communicate
  with at least one other server.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Initialize the max server variable
  private int maxServer = 0;

  // Method to find the number of servers that communicate with any other server
  public int countServers(int[][] grid) {
    // Iterate over the grid
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        // If server is present then do the dfs
        if (grid[row][col] == 1) {
          // Initialize the tempServer
          int tempServer = 0;

          // Call the recursive dfs call
          tempServer = this.dfs(grid, row, col);

          // Update the maxServer only if multiple servers communicate
          if (tempServer > 1) {
            this.maxServer += tempServer;
          }
        }
      }
    }

    // Return the maxServer variable
    return this.maxServer;
  }

  // Helper method for the dfs
  private int dfs(int[][] grid, int row, int col) {
    // If we are out of bound or the cell is not a server then terminate the method
    if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
      return 0;
    }

    // Mark the current server as visited
    grid[row][col] = 0;

    // Initialize the server count
    int serverCount = 1;

    // Iterate over the row
    for (int r = 0; r < grid.length; r++) {
      // If cell is one then call the recursive dfs call
      if (grid[r][col] == 1) {
        // Increment the server count
        serverCount += this.dfs(grid, r, col);
      }
    }

    // Iterate over the col
    for (int c = 0; c < grid[0].length; c++) {
      // If cell is one then call the recursive dfs call
      if (grid[row][c] == 1) {
        // Increment the server count
        serverCount += this.dfs(grid, row, c);
      }
    }

    // Return the number of servers in this component
    return serverCount;
  }
}

public class _1267_Count_Servers_that_Communicate {
  // Main method to test countServers
  public static void main(String[] args) {
    int[][] grid = new int[][] { { 1, 0 }, { 0, 1 } };

    int result = new Solution().countServers(grid);

    System.out.println("The number of servers that communicate with any other server is : " + result);
  }
}
