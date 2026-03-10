/*
LeetCode Problem: https://leetcode.com/problems/valid-perfect-square/

Question: 367. Valid Perfect Square

Problem Statement: Given a positive integer num, return true if num is a perfect square or false otherwise.

A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.

You must not use any built-in library function, such as sqrt.

Example 1:
Input: num = 16
Output: true
Explanation: We return true because 4 * 4 = 16 and 4 is an integer.

Example 2:
Input: num = 14
Output: false
Explanation: We return false because 3.742 * 3.742 = 14 and 3.742 is not an integer.

Constraints:

1 <= num <= 2^31 - 1
 */

/*
Approach: Binary Search

Goal:
- Determine whether a given number is a perfect square
  without using built-in square root functions.

Core Idea:
- Search for an integer whose square equals the given number.
- Use binary search within the range [1, num].
- Compare mid * mid with num to adjust the search space.

Algorithm Steps:
1. Initialize left = 1 and right = num.
2. While left <= right:
   - Compute mid = left + (right - left) / 2.
   - Compute square = mid * mid.
3. If square == num → return true.
4. If square < num → move left to mid + 1.
5. If square > num → move right to mid - 1.
6. If loop ends → return false.

Time Complexity:
- O(log n)

Space Complexity:
- O(1)

Result:
- Returns true if num is a perfect square, otherwise false.
*/

package BinarySearchAndSorting.Easy;

// Solution Class
class Solution {
  // Method to find if the num is a perfect square or not
  public boolean isPerfectSquare(int num) {
    // Initialize the left bouds and the right bound
    long left = 1, right = num;

    while (left <= right) {
      // Get the mid of the bounds
      long mid = left + (right - left) / 2;

      // Get the square of mid
      long square = mid * mid;

      // Update the bounds correctly
      if (square < num) {
        left = mid + 1;
      } else if (square > num) {
        right = mid - 1;
      } else {
        return true;
      }
    }

    // Return false in the end
    return false;
  }
}

public class _367_Valid_Perfect_Square {
  // Main method to test isPerfectSquare
  public static void main(String[] args) {
    int num = 2147483647;

    boolean result = new Solution().isPerfectSquare(num);

    System.out.println("The number is" + (result ? " " : " not ") + "a perfect square.");
  }
}
