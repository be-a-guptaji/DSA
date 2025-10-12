/*
LeetCode Problem: https://leetcode.com/problems/unique-paths/

Question: 62. Unique Paths

Problem Statement: There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Constraints:

1 <= m, n <= 100
*/

/*
Approach:
We use a dynamic programming strategy with memoization to solve the problem of decoding a string of digits,
where each digit or pair of digits can represent a letter (e.g., '1' -> 'A', ..., '26' -> 'Z').

Steps:
- If the string starts with '0', return 0 because '0' cannot be decoded on its own.
- If the string length is 1 and not '0', return 1 since there's only one way to decode it.

- Initialize an integer array `ways[]` of size equal to the length of the string to store the number of ways
  to decode substrings up to each index.

- Base cases:
    - ways[0] = 1 (there's at least one valid way to decode the first character unless it's '0')
    - For the second character:
        - If the first two characters form a valid 2-digit code (10–26), and the second character is not '0', set ways[1] = 2
        - If the second character is '0', it must be part of a valid two-digit code with the first character (10 or 20),
          so set ways[1] = 1
        - If none of the above apply, set ways[1] = 1

- For each character from index 2 onward:
    - If the current character is not '0', add ways[i - 1] to ways[i]
    - If the 2-digit number formed by the previous character and current character is between 10 and 26, add ways[i - 2]
    - If the current character is '0' and not part of a valid 2-digit number (like '30', '40', etc.), return 0

- Finally, return ways[size - 1], which contains the number of ways to decode the full message.

Time Complexity: O(n), where n is the length of the input string.
Space Complexity: O(n), due to the use of the `ways` array for memoization.
*/

package DynamicProgramming.Medium;

public class _62_Unique_Paths {
    // Method to find the total unique path for the robot
    public static int uniquePaths(int m, int n) {
        // Create a DP array initialized with -1
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // Return the total number of paths
        return countPaths(m, n, 0, 0, dp);
    }

    // Helper method to find the total number of paths
    private static int countPaths(int m, int n, int row, int column, int[][] dp) {
        // If we reach the bottom right cell return 1
        if (row == m - 1 && column == n - 1) {
            return 1;
        }

        // If we go out of bound return 0
        if (row >= m || column >= n) {
            return 0;
        }

        // If result is already computed
        if (dp[row][column] != -1) {
            return dp[row][column];
        }

        // Calculate paths by moving right and down
        int rightPaths = countPaths(m, n, row, column + 1, dp);
        int downPaths = countPaths(m, n, row + 1, column, dp);

        // Store the result in dp array
        dp[row][column] = rightPaths + downPaths;

        // Return the cell value
        return dp[row][column];
    }

    // Main method to test uniquePaths
    public static void main(String[] args) {
        int m = 19, n = 13;

        int result = uniquePaths(m, n);

        System.out.println("Total unique path for the robot on the " + m + " X " + n + " grid is : " + result);
    }
}
