/*
LeetCode Problem: https://leetcode.com/problems/sliding-puzzle/

Question: 773. Sliding Puzzle

Problem Statement: On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Example 1:
Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.

Example 2:
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.

Example 3:
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Constraints:
board.length == 2
board[i].length == 3
0 <= board[i][j] <= 5
Each value board[i][j] is unique.
*/

/*
Approach: BFS on Board State with Integer Signature Deduplication
Goal:
- Find the minimum number of moves to solve the
  2x3 sliding puzzle (reach state [[1,2,3],[4,5,0]])
  or return -1 if unsolvable.
Core Idea:
- Each board configuration is a unique BFS node.
- Encode each board as a 6-digit integer signature
  (row-major concatenation) for O(1) visited
  lookup in a HashSet.
- BFS explores all reachable states level by level;
  the first time the solved state (123450) is
  reached, the current level count is the minimum
  moves.
Algorithm Steps:
1. Compute initial board signature; return 0 if
   already solved.
2. Enqueue the initial board; mark its signature
   as seen.
3. BFS level by level:
   a. For each board in the current level, find
      the zero cell position.
   b. Try all 4 directional swaps via addToQueue:
      - Clone the board, perform the swap.
      - If the new signature == 123450, return
        result + 1.
      - If unseen, mark and enqueue.
   c. Increment result after each level.
4. Return -1 if queue empties without solution.
Why It Works:
- Integer signature encoding collapses the 2D
  board into a hashable scalar, making state
  comparison and deduplication O(1).
- BFS guarantees the first path to the solved
  state is the shortest.
- Cloning each board before mutation ensures BFS
  levels remain independent.
Time Complexity:
- O(1) bounded by the fixed state space of 6!
  = 720 possible board permutations, each visited
  at most once.
Space Complexity:
- O(1) same bound: at most 720 states in the
  queue and seen set simultaneously.
Result:
- Returns the minimum moves to solve the puzzle,
  or -1 if unsolvable.
*/

package Graphs.Hard;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

// Solution Class
class Solution {
  // Initialize the hashset
  private HashSet<Integer> seenSet;

  // Method to find the least number of moves required so that the state of the
  // board is solved
  public int slidingPuzzle(int[][] board) {
    // Initialize the seenSet
    this.seenSet = new HashSet<>();

    // Initialize the result variable
    int result = 0;

    // Get the signature
    int signature = this.getSignature(board);

    // Check if board is solved
    if (signature == 123450) {
      return result;
    }

    // Add value to the seen set
    this.seenSet.add(signature);

    // Initialize the queue variable
    Queue<int[][]> queue = new ArrayDeque<>();

    // Add the board to the queue
    queue.offer(board);

    // Iterate over the queue untill it is not empty
    while (!queue.isEmpty()) {
      // Initialize the size of the queue
      int size = queue.size();

      // Iterate over the level
      for (int i = 0; i < size; i++) {
        // Get the board from the queue
        board = queue.poll();

        // Get the cell of 0
        int[] cell = this.getCell(board);

        // Get the row and col
        int row = cell[0];
        int col = cell[1];

        // Add the new board to the queue
        if (this.addToQueue(board, row, col, 1, 0, queue) || this.addToQueue(board, row, col, -1, 0, queue)
            || this.addToQueue(board, row, col, 0, 1, queue) || this.addToQueue(board, row, col, 0, -1, queue)) {
          return result + 1;
        }
      }

      // Increment the result variable
      result++;
    }

    // Return -1 in the end
    return -1;
  }

  // Helper method for getting the board signature
  private int getSignature(int[][] board) {
    // Initialize the signature variable
    int signature = 0;

    // Make the signature
    for (int[] arr : board) {
      for (int num : arr) {
        signature = (signature * 10 + num);
      }
    }

    // Return the signature
    return signature;
  }

  // Helper method getting the cell of zero
  private int[] getCell(int[][] board) {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == 0) {
          return new int[] { i, j };
        }
      }
    }

    // Return empty array in the end
    return new int[] {};
  }

  // Helper method for adding to the queue
  private boolean addToQueue(int[][] board, int row, int col, int rowMove, int colMove, Queue<int[][]> queue) {
    // If row and col is out of board then terminate the method
    if (0 > row + rowMove || 0 > col + colMove || 2 == row + rowMove || 3 == col + colMove) {
      return false;
    }

    // Initialize the new board
    int[][] newBoard = new int[2][];

    // Clone the board
    newBoard[0] = board[0].clone();
    newBoard[1] = board[1].clone();

    // Make the new board
    newBoard[row][col] = newBoard[row + rowMove][col + colMove];
    newBoard[row + rowMove][col + colMove] = 0;

    // Get the signature
    int signature = this.getSignature(newBoard);

    // Check if board is solved
    if (signature == 123450) {
      return true;
    }

    // If we have not seen the signature then add it the queue and set
    if (this.seenSet.add(signature)) {
      queue.offer(newBoard);
    }

    // Return false in the end
    return false;
  }
}

public class _773_Sliding_Puzzle {
  // Main method to test slidingPuzzle
  public static void main(String[] args) {
    int[][] board = new int[][] { { 1, 2, 3 }, { 4, 0, 5 } };

    int result = new Solution().slidingPuzzle(board);

    System.out.println("The least number of moves required so that the state of the board is solved is : " + result);
  }
}
