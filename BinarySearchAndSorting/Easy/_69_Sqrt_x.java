/*
LeetCode Problem: https://leetcode.com/problems/sqrtx/

Question: 69. Sqrt(x)

Problem Statement: Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

Example 1:
Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.

Constraints:

0 <= x <= 2^31 - 1
 */

/*
Approach: Binary Search

Goal:
- Compute the integer square root of x (⌊√x⌋), i.e., the largest integer
  whose square is less than or equal to x.

Core Idea:
- Use binary search over the range [1, x].
- Compare mid * mid with x to narrow the search space.
- When the loop finishes, the right boundary represents the floor of √x.

Algorithm Steps:
1. Initialize left = 1 and right = x.
2. While left <= right:
   - Compute mid = left + (right - left) / 2.
   - Compute square = mid * mid.
3. If square == x → return mid.
4. If square < x → move left to mid + 1.
5. If square > x → move right to mid - 1.
6. When the loop ends, return left - 1 (floor value of √x).

Time Complexity:
- O(log x)

Space Complexity:
- O(1)

Result:
- Returns the integer square root of x rounded down.
*/

package BinarySearchAndSorting.Easy;

// Solution Class
class Solution {
  // Method to find the square root of x rounded down to the nearest integer
  public int mySqrt(int x) {
    // Initialize the left bouds and the right bound
    long left = 1, right = x;

    while (left <= right) {
      // Get the mid of the bounds
      long mid = left + (right - left) / 2;

      // Get the square of mid
      long square = mid * mid;

      // Update the bounds correctly
      if (square < x) {
        left = mid + 1;
      } else if (square > x) {
        right = mid - 1;
      } else {
        return (int) mid;
      }
    }

    // Return left bound
    return (int) left - 1;
  }
}

public class _69_Sqrt_x {
  // Main method to test mySqrt
  public static void main(String[] args) {
    int x = 8;

    int result = new Solution().mySqrt(x);

    System.out.println("The square root of x rounded down to the nearest integer is : " + result);
  }
}
