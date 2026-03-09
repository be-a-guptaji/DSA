/*
LeetCode Problem: https://leetcode.com/problems/arranging-coins/

Question: 441. Arranging Coins

Problem Statement: You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins. The last row of the staircase may be incomplete.

Given the integer n, return the number of complete rows of the staircase you will build.

Example 1:
Input: n = 5
Output: 2
Explanation: Because the 3rd row is incomplete, we return 2.

Example 2:
Input: n = 8
Output: 3
Explanation: Because the 4th row is incomplete, we return 3.

Constraints:

1 <= n <= 2^31 - 1
 */

/*
Approach: Binary Search

Goal:
- Find the number picked by the game using the provided guess API.

Core Idea:
- Apply binary search on the range [1, n].
- Use the guess API feedback:
    -1 → guessed number is too high
     1 → guessed number is too low
     0 → correct guess

Algorithm Steps:
1. Initialize lower = 1 and upper = n.
2. Compute middle = lower + (upper - lower) / 2.
3. Call guess(middle):
   - If result == 1 → search right half.
   - If result == -1 → search left half.
   - If result == 0 → return middle.
4. Repeat until the correct number is found.

Time Complexity:
- O(log n)

Space Complexity:
- O(1)

Result:
- Returns the number picked by the game.
*/

package BinarySearchAndSorting.Easy;

// Solution Class
class Solution {
  // Method to find the number of complete rows of the staircase you will build
  public int arrangeCoins(int n) {
    // Edge case if n is less than 4
    if (n < 4) {
      return n == 1 ? 1 : n - 1;
    }

    // Initialize the lower bouds and the upper bound
    int lower = 1, upper = (n >> 1) + 1;

    // Find the guess number
    while (lower < upper) {
      // Update the middle
      int middle = lower + ((upper - lower) >> 1);

      // Check if all the coins are used
      long coins = ((long) middle * (middle + 1)) >> 1;

      if (coins <= n) {
        lower = middle + 1;
      } else {
        upper = middle;
      }
    }

    // Return lower
    return lower - 1;
  }
}

public class _441_Arranging_Coins {
  // Main method to test arrangeCoins
  public static void main(String[] args) {
    int n = 5;

    int result = new Solution().arrangeCoins(n);

    System.out.println("The number of complete rows of the staircase you will build is : " + result);
  }
}
