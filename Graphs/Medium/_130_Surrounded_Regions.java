/*
LeetCode Problem: https://leetcode.com/problems/surrounded-regions/

Question: 130. Surrounded Regions

Problem Statement: You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.

Example 1:
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation:
In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:
Input: board = [["X"]]
Output: [["X"]]

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
 */

/*
Approach:
1. The main idea is that only the 'O' regions fully surrounded by 'X' should be flipped.
   Any 'O' connected to the border (directly or indirectly) must remain as 'O'.
2. Traverse all border cells of the board.
   - If a border cell contains 'O', run DFS from that cell.
   - Mark all connected 'O' cells as safe by temporarily changing them to '#'.
3. After DFS marking is complete, scan the entire board again.
   - If a cell contains 'O', it means it was not connected to the border,
     so flip it to 'X'.
   - If a cell contains '#', restore it back to 'O' since it was safe.
4. By this process, only truly surrounded regions are flipped.

Time Complexity: O(m * n), where m and n are the dimensions of the board (each cell is visited at most once).  
Space Complexity: O(m * n) in worst case due to DFS recursion stack (or O(1) if iterative BFS is used).
*/

package Graphs.Medium;

public class _130_Surrounded_Regions {
    // Method to capture surrounded regions
    public static void solve(char[][] board) {
        // If board is empty the return
        if (board == null || board.length == 0) {
            return;
        }

        // Get the rows and column of the board
        int rows = board.length;
        int cols = board[0].length;

        // Mark all 'O' connected to the border as safe ('#')
        for (int row = 0; row < rows; row++) {
            dfs(board, row, 0); // left border
            dfs(board, row, cols - 1); // right border
        }
        for (int column = 0; column < cols; column++) {
            dfs(board, 0, column); // top border
            dfs(board, rows - 1, column); // bottom border
        }

        // Flip the rest 'O' to 'X', and safe '#' back to 'O'
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                if (board[row][column] == 'O') {
                    board[row][column] = 'X'; // surrounded region
                } else if (board[row][column] == '#') {
                    board[row][column] = 'O'; // restore safe region
                }
            }
        }
    }

    // DFS to mark safe cells
    private static void dfs(char[][] board, int row, int col) {
        // Out of bounds or not 'O'
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 'O') {
            return;
        }

        // Mark current cell as safe
        board[row][col] = '#';

        // Explore all 4 directions
        dfs(board, row + 1, col); // down
        dfs(board, row - 1, col); // up
        dfs(board, row, col + 1); // right
        dfs(board, row, col - 1); // left
    }

    // Main method to test solve
    public static void main(String[] args) {
        char[][] grid = {
                { 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'X' }
        };

        solve(grid);

        System.out.print("The sourrounded region has been captured.");
    }
}
