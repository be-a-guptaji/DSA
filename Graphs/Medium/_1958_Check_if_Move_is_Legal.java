/*
LeetCode Problem: https://leetcode.com/problems/check-if-move-is-legal/

Question: 1958. Check if Move is Legal

Problem Statement: You are given a 0-indexed 8 x 8 grid board, where board[r][c] represents the cell (r, c) on a game board. On the board, free cells are represented by '.', white cells are represented by 'W', and black cells are represented by 'B'.

Each move in this game consists of choosing a free cell and changing it to the color you are playing as (either white or black). However, a move is only legal if, after changing it, the cell becomes the endpoint of a good line (horizontal, vertical, or diagonal).

A good line is a line of three or more cells (including the endpoints) where the endpoints of the line are one color, and the remaining cells in the middle are the opposite color (no cells in the line are free). You can find examples for good lines in the figure below:

Given two integers rMove and cMove and a character color representing the color you are playing as (white or black), return true if changing cell (rMove, cMove) to color color is a legal move, or false if it is not legal.

Example 1:
Input: board = [[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],["W","B","B",".","W","W","W","B"],[".",".",".","B",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."]], rMove = 4, cMove = 3, color = "B"
Output: true
Explanation: '.', 'W', and 'B' are represented by the colors blue, white, and black respectively, and cell (rMove, cMove) is marked with an 'X'.
The two good lines with the chosen cell as an endpoint are annotated above with the red rectangles.

Example 2:
Input: board = [[".",".",".",".",".",".",".","."],[".","B",".",".","W",".",".","."],[".",".","W",".",".",".",".","."],[".",".",".","W","B",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".","B","W",".","."],[".",".",".",".",".",".","W","."],[".",".",".",".",".",".",".","B"]], rMove = 4, cMove = 4, color = "W"
Output: false
Explanation: While there are good lines with the chosen cell as a middle cell, there are no good lines with the chosen cell as an endpoint.

Constraints:
board.length == board[r].length == 8
0 <= rMove, cMove < 8
board[rMove][cMove] == '.'
color is either 'B' or 'W'.
*/

/*
Approach: Directional DFS Line Validity Check
Goal:
- Determine if placing a piece of the given color
  at (rMove, cMove) creates at least one "good
  line": a straight line with at least one
  opponent piece sandwiched between the placed
  piece and another piece of the same color.
Core Idea:
- For each of the 8 directions, walk from the
  cell adjacent to (rMove, cMove) and count
  consecutive opponent-colored pieces.
- A good line exists if the walk ends on a cell
  of the same color after passing at least one
  opponent piece.
Algorithm Steps:
1. Reject if board[rMove][cMove] != '.'.
2. For each direction dir in all 8 directions:
   - Call isGoodLine starting one step from
     (rMove, cMove) with move count 0.
   - Return true immediately if any direction
     yields a good line.
3. In isGoodLine(row, col, color, dir, move):
   a. Return false if out of bounds or cell is '.'.
   b. If cell matches color, return move >= 1
      (valid only if at least one opponent piece
      was passed).
   c. Otherwise (opponent piece), recurse one step
      further in dir with move + 1.
4. Return false if no direction produces a good
   line.
Why It Works:
- Recursing only on opponent-colored cells ensures
  the walk only continues through a contiguous
  sequence of opponent pieces.
- The move >= 1 check at the terminating same-color
  cell enforces that at least one opponent piece
  was sandwiched.
- Out-of-bounds and empty-cell base cases correctly
  short-circuit invalid lines.
Time Complexity:
- O(1)
the board is fixed at 8x8 and each of the 8
directions walks at most 7 steps.
Space Complexity:
- O(1)
call stack depth bounded by 7 recursive calls per
direction.
Result:
- Returns true if the move creates at least one
  valid good line, false otherwise.
*/

package Graphs.Medium;

// Solution Class
class Solution {
  // Initialize the direction matrix
  private static final int[][] direction = {
      { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 }
  };

  // Method to find the good line
  public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
    // The move must be made on an empty cell
    if (board[rMove][cMove] != '.') {
      return false;
    }

    // Iterate over all direction
    for (int[] dir : direction) {
      if (this.isGoodLine(board, rMove + dir[0], cMove + dir[1], color, dir, 0)) {
        return true;
      }
    }

    // Return the false in the end
    return false;
  }

  // Helper method to find if the line is good or not
  private boolean isGoodLine(char[][] board, int row, int col, char color, int[] dir, int move) {
    // Out of bounds
    if (row < 0 || row >= 8 || col < 0 || col >= 8) {
      return false;
    }

    // If empty cell breaks the line return false
    if (board[row][col] == '.') {
      return false;
    }

    // If we found our color then return condition
    if (board[row][col] == color) {
      return move >= 1;
    }

    // Call the recursive call
    return this.isGoodLine(board, row + dir[0], col + dir[1], color, dir, move + 1);
  }
}

public class _1958_Check_if_Move_is_Legal {
  // Main method to test checkMove
  public static void main(String[] args) {
    char[][] board = new char[][] {
        { '.', '.', '.', 'B', '.', '.', '.', '.' },
        { '.', '.', '.', 'W', '.', '.', '.', '.' },
        { '.', '.', '.', 'W', '.', '.', '.', '.' },
        { '.', '.', '.', 'W', '.', '.', '.', '.' },
        { 'W', 'B', 'B', '.', 'W', 'W', 'W', 'B' },
        { '.', '.', '.', 'B', '.', '.', '.', '.' },
        { '.', '.', '.', 'B', '.', '.', '.', '.' },
        { '.', '.', '.', 'W', '.', '.', '.', '.' }
    };
    int rMove = 4;
    int cMove = 3;
    char color = 'B';

    boolean result = new Solution().checkMove(board, rMove, cMove, color);

    System.out.println("There is good" + (result ? " " : " not ") + "line in the board.");
  }
}
