/*
LeetCode Problem: https://leetcode.com/problems/stone-game/

Question: 877. Stone Game

Problem Statement: Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

Example 1:
Input: piles = [5,3,4,5]
Output: true
Explanation: 
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we return true.

Example 2:
Input: piles = [3,7,2,3]
Output: true

Constraints:

2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles[i]) is odd.
*/

/*
Approach:
This solution handles the "Stone Game" problem (LeetCode #877).

Key Idea:
- Two players, Alice and Bob, take turns picking stones from either end of the array `piles`.
- Each pile has a number of stones, and the goal is to maximize the total number of stones collected.
- Both players play optimally.
- The problem can be solved using recursion with memoization (top-down dynamic programming).

Core Logic:
- Use a recursive helper `aliceWins(piles, i, j, AMinusB)` that returns whether Alice can win 
  when the remaining piles are from index `i` to `j`, and `AMinusB` represents the difference 
  between Alice's and Bob's total stones so far.
- At each step:
  - If it’s Alice’s turn, she can choose the left or right pile to maximize her score.
  - If it’s Bob’s turn, he will choose the move that minimizes Alice’s advantage.
- The recursion continues until all piles are taken (`i > j`), and we return `true` if `AMinusB > 0`
  (Alice collected more stones than Bob).

Optimization:
- Use a 2D Boolean array `dp[i][j]` to store whether Alice can win for the range `i..j` to avoid recomputation.

Steps:
1. Initialize a memoization table `dp[n][n]` (where `n = piles.length`).
2. Recursively compute results using `aliceWins`.
3. Base case: if all piles are taken (`i > j`), return whether Alice’s score is greater than Bob’s.
4. Return `true` if Alice can guarantee a win.

Time Complexity: O(n²)
Space Complexity: O(n²) due to the DP table.
*/

package DynamicProgramming.Medium;

public class _877_Stone_Game {
  // Make a private dp matrix for the memoization
  private static Boolean[][] dp;

  // Method to find if Alice can win the game
  public static boolean stoneGame(int[] piles) {
    // Initialzie the length of the piles
    int size = piles.length;

    // Initialzie the dp variable with the matrix of the length of the piles
    dp = new Boolean[size][size];

    // Retrun the recursive method to determine if the alice wins the match
    return aliceWins(piles, 0, size - 1, 0);
  }

  // Helper method to find if alice can win the match
  private static boolean aliceWins(int[] piles, int i, int j, int AMinusB) {
    // If index overlap then return AMinusB > 0
    if (i > j) {
      return AMinusB > 0;
    }

    // If dp[i][j] is not null then return the stored value
    if (dp[i][j] != null) {
      return dp[i][j];
    }

    // Check if its the alice turns
    boolean alicesTurn = (j - i + 1) % 2 == 0;

    if (alicesTurn) {
      dp[i][j] = aliceWins(piles, i + 1, j, AMinusB + piles[i]) || aliceWins(piles, i, j - 1, AMinusB + piles[j]);
    } else {
      dp[i][j] = aliceWins(piles, i + 1, j, AMinusB - piles[i]) || aliceWins(piles, i, j - 1, AMinusB - piles[i]);
    }

    // Return the dp[i][j]
    return dp[i][j];
  }

  // Main method to test stoneGame
  public static void main(String[] args) {
    int[] piles = { 5, 3, 4, 5 };

    boolean result = stoneGame(piles);

    System.out.println("Alice can " + (result ? "" : "not ") + "win the game.");
  }
}
