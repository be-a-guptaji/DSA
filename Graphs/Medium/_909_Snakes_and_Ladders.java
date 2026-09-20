/*
LeetCode Problem: https://leetcode.com/problems/snakes-and-ladders/

Question: 909. Snakes and Ladders

Problem Statement: You are given an n x n integer matrix board where the cells are labeled from 1 to n^2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.

You start on square 1 of the board. In each move, starting from square curr, do the following:

Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n^2)].
This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
The game ends when you reach the square n^2.
A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n^2 are not the starting points of any snake or ladder.

Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.

For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
Return the least number of dice rolls required to reach the square n^2. If it is not possible to reach the square, return -1.

Example 1:
Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation: 
In the beginning, you start at square 1 (at row 5, column 0).
You decide to move to square 2 and must take the ladder to square 15.
You then decide to move to square 17 and must take the snake to square 13.
You then decide to move to square 14 and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
This is the lowest possible number of moves to reach the last square, so return 4.

Example 2:
Input: board = [[-1,-1],[-1,3]]
Output: 1

Constraints:
n == board.length == board[i].length
2 <= n <= 20
board[i][j] is either -1 or in the range [1, n^2].
The squares labeled 1 and n^2 are not the starting points of any snake or ladder.
*/

/*
Approach: BFS on Square Numbers with Board Coordinate Mapping
Goal:
- Find the minimum number of dice rolls to reach
  square n^2 from square 1 on a Snakes and Ladders
  board, where snakes and ladders teleport the
  player to a different square.
Core Idea:
- Model each board square as a BFS node; each dice
  roll (1-6) produces up to 6 neighbor squares.
- After landing on a square, apply any snake or
  ladder at that position before enqueuing.
- BFS guarantees the first time square n^2 is
  reached, it is via the minimum number of rolls.
- Convert linear square numbers to 2D board
  coordinates using Boustrophedon (alternating
  left-right row) indexing.
Algorithm Steps:
1. Initialize queue with {square=1, moves=0} and
   mark square 1 as visited.
2. While queue is not empty:
   a. Poll (square, moves).
   b. For each dice roll i from 1 to 6:
      - nextSquare = square + i.
      - If nextSquare > n^2, break.
      - Convert nextSquare to (r, c) via intToPos.
      - If board[r][c] != -1, apply teleport:
        nextSquare = board[r][c].
      - If nextSquare == n^2, return moves + 1.
      - If not visited, mark and enqueue
        {nextSquare, moves + 1}.
3. Return -1 if n^2 is unreachable.
4. In intToPos(square, n):
   a. row = (square - 1) / n, col = (square-1) % n.
   b. If row is odd (right-to-left row), mirror col:
      col = n - 1 - col.
   c. Flip row from bottom: row = n - 1 - row.
   d. Return {row, col}.
Why It Works:
- BFS on square numbers rather than (r, c) pairs
  avoids coordinate confusion after teleportation,
  since snakes and ladders map to square numbers
  directly.
- Applying teleportation before enqueuing means
  the visited set correctly deduplicates by final
  landing position, not intermediate squares.
- The Boustrophedon mapping correctly handles the
  alternating row direction inherent in the board
  layout.
Time Complexity:
- O(n^2)
where n is the board dimension, since each of the
n^2 squares is enqueued and processed at most once.
Space Complexity:
- O(n^2)
for the visited set and BFS queue.
Result:
- Returns the minimum number of dice rolls to
  reach square n^2, or -1 if unreachable.
*/

package Graphs.Medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// Solution Class
class Solution {
  // Method to find the least number of dice rolls required to reach the square
  public int snakesAndLadders(int[][] board) {
    // Initialize the length of the matirx
    int length = board.length;

    // Initialize the seen set
    HashSet<Integer> visit = new HashSet<>();

    // Initialize the queue for the bfs
    Queue<int[]> queue = new LinkedList<>();

    // Add first position to the queue
    queue.add(new int[] { 1, 0 });

    // Iterate untill queue is not empty
    while (!queue.isEmpty()) {
      // Get the element from the queue
      int[] current = queue.poll();

      // Get the square and the moves
      int square = current[0], moves = current[1];

      // Iterate till 1 to 6
      for (int i = 1; i <= 6; i++) {
        // Get the nextSquare
        int nextSquare = square + i;

        // Get the position
        int[] pos = intToPos(nextSquare, length);

        // Get the row and col
        int r = pos[0], c = pos[1];

        // If it is not -1 then update the nextSquare
        if (board[r][c] != -1) {
          nextSquare = board[r][c];
        }

        // If we are at the end then return the moves
        if (nextSquare == length << 1) {
          return moves + 1;
        }

        // If we have already visited the position then do not add it to the queue
        if (!visit.contains(nextSquare)) {
          visit.add(nextSquare);
          queue.offer(new int[] { nextSquare, moves + 1 });
        }
      }
    }

    // Return -1 in the end
    return -1;
  }

  // Helper method for the int to position
  private int[] intToPos(int square, int n) {
    // Initialize the row and col
    int r = (square - 1) / n;
    int c = (square - 1) % n;

    // Set the col if row is odd
    if (r % 2 == 1) {
      c = n - 1 - c;
    }

    // Set the row
    r = n - 1 - r;

    // Return the position
    return new int[] { r, c };
  }
}

public class _909_Snakes_and_Ladders {
  // Main method to test snakesAndLadders
  public static void main(String[] args) {
    int[][] board = new int[][] {
        { -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1 },
        { -1, 35, -1, -1, 13, -1 },
        { -1, -1, -1, -1, -1, -1 },
        { -1, 15, -1, -1, -1, -1 }
    };

    int result = new Solution().snakesAndLadders(board);

    System.out.println("The least number of dice rolls required to reach the square is : " + result);
  }
}
