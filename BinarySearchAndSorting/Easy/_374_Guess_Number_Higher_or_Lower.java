/*
LeetCode Problem: https://leetcode.com/problems/guess-number-higher-or-lower/

Question: 374. Guess Number Higher or Lower

Problem Statement: We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked (the number I picked stays the same throughout the game).

Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

You call a pre-defined API int guess(int num), which returns three possible results:

-1: Your guess is higher than the number I picked (i.e. num > pick).
1: Your guess is lower than the number I picked (i.e. num < pick).
0: your guess is equal to the number I picked (i.e. num == pick).
Return the number that I picked.

Example 1:
Input: n = 10, pick = 6
Output: 6

Example 2:
Input: n = 1, pick = 1
Output: 1

Example 3:
Input: n = 2, pick = 1
Output: 1

Constraints:

1 <= n <= 2^31 - 1
1 <= pick <= n
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

/**
 * Forward declaration of guess API.
 * 
 * @param num your guess
 * @return -1 if num is higher than the picked number
 *         1 if num is lower than the picked number
 *         otherwise return 0
 *         int guess(int num);
 */

// Solution Class
class GuessGame {
  // Set the pick number
  public int pick = 1;

  public int guess(int num) {
    if (num < this.pick) {
      return 1;
    } else if (num > this.pick) {
      return -1;
    } else {
      return 0;
    }
  }
}

// Solution Class
class Solution extends GuessGame {
  // Method to find the number that I picked
  public int guessNumber(int n) {
    // Initialize the lower bouds and the upper bound
    int lower = 1, upper = n, middle = lower + (upper - lower) / 2;

    // Find the guess number
    while (true) {
      switch (this.guess(middle)) {
        case 1 -> {
          lower = middle + 1;
        }
        case -1 -> {
          upper = middle - 1;
        }
        default -> {
          // Return the middle in the end
          return middle;
        }
      }

      // Update the middle
      middle = lower + (upper - lower) / 2;
    }
  }
}

public class _374_Guess_Number_Higher_or_Lower {
  // Main method to test guessNumber
  public static void main(String[] args) {
    int n = 1;

    int result = new Solution().guessNumber(n);

    System.out.println("The number that I picked is : " + result);
  }
}
