/*
LeetCode Problem: https://leetcode.com/problems/word-search/

Question: 79. Word Search

Problem Statement: Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.

Follow up: Could you use search pruning to make your solution faster with a larger board?
 */

/*
Approach:
1. We are given a matrix of characters and a word. The goal is to check if the
   word exists in the grid by moving only in the four adjacent directions:
   up, down, left, and right.

2. For every cell in the board, try to match the word starting from that cell.

3. We use backtracking to explore all possible paths:
   - At each step, compare the current board character with the word character.
   - Maintain a 'seen' matrix to mark visited cells in the current path.
   - Move to the four neighbouring cells.
   - Backtrack by unmarking the cell after exploring.

4. Important checks:
   - Stop when index == word length (found complete word).
   - Reject paths that go out of bounds.
   - Reject already visited cells.
   - Reject mismatched characters.

5. If any path matches the entire word, return true.

Time Complexity: O(n * m * 4^k)
   where k = length of the word.
Space Complexity: O(n * m) for the seen matrix + recursion stack.
*/

package Backtracking.Medium;

public class _79_Word_Search {
    // Method to find word in the board
    public static boolean exist(char[][] board, String word) {
        // Get the rows and column board
        int row = board.length, column = board[0].length;

        // Convert the word into character array
        char[] str = word.toCharArray();

        // Get the length of the str
        int length = str.length;

        // Iterate over the board matrix to find the word
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // Initialize the boolean matrix for seen the character
                boolean[][] seen = new boolean[row][column];

                // If we find the match then rerutn true
                if (backtrack(i, j, row, column, 0, str, length, board, seen)) {
                    return true;
                }
            }
        }

        // Return false if no match is found
        return false;
    }

    // Helper method to find the word
    private static boolean backtrack(int i, int j, int row, int column, int index, char[] str, int length,
            char[][] board, boolean[][] seen) {
        // If index length is equal to length then return true
        if (index == length) {
            return true;
        }

        // Edge case if the i and j is out of matrix then return false
        if (i < 0 || j < 0 || i == row || j == column) {
            return false;
        }

        // If character is not match the cell value then return
        if (board[i][j] != str[index]) {
            return false;
        }

        // If we have already seen the cell then return
        if (seen[i][j]) {
            return false;
        }

        // Mark the cell as seen
        seen[i][j] = true;

        // Make a recursive call in all direction
        boolean top = backtrack(i + 1, j, row, column, index + 1, str, length, board, seen); // TOP
        boolean bottom = backtrack(i - 1, j, row, column, index + 1, str, length, board, seen); // BOTTOM
        boolean right = backtrack(i, j + 1, row, column, index + 1, str, length, board, seen); // RIGHT
        boolean left = backtrack(i, j - 1, row, column, index + 1, str, length, board, seen); // LEFT

        // Mark the cell as unseen
        seen[i][j] = false;

        // Return if true is found
        return top || bottom || right || left;
    }

    // Main method to test exist
    public static void main(String[] args) {
        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word = "ABCCED";

        boolean result = exist(board, word);

        System.out.println("The word " + word + (result ? "" : " does not") + " exits in the board.");
    }
}
