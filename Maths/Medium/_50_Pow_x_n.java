/*
LeetCode Problem: https://leetcode.com/problems/powx-n/

Question: 50. Pow(x, n)

Problem Statement: Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:
Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25

Constraints:

-100.0 < x < 100.0
-23^1 <= n <= 2^31-1
n is an integer.
Either x is not zero or n > 0.
-10^4 <= xn <= 10^4
*/

/*
Approach: Fast Exponentiation (Binary Exponentiation)

Goal:
Compute xⁿ efficiently, handling both positive and negative exponents.

Key Idea:
Instead of multiplying x repeatedly (O(n)), use divide-and-conquer to
reduce the problem size by half at each step.

Algorithm:
1. Handle base cases:
   - If x == 0 → return 0.
   - If n == 0 → return 1.

2. Compute power using recursion:
   - helper(x, n) returns xⁿ.
   - Recursively compute:
       half = helper(x, n / 2)
   - If n is even:
       xⁿ = half × half
     Else:
       xⁿ = x × half × half

3. If n is negative:
   - Return 1 / xⁿ.

Why It Works:
- Each recursive call halves the exponent.
- Reduces time complexity drastically compared to naive multiplication.

Time Complexity: O(log n)  
Space Complexity: O(log n) (recursion stack)
*/

package Maths.Medium;

public class _50_Pow_x_n {
    // Method to find the Pow(x, n)
    public static double myPow(double x, int n) {
        // If x is zero then return zero
        if (x == 0) {
            return 0;
        }

        // If n is zero then return one
        if (n == 0) {
            return 1;
        }

        // Initialize the result and find the power using recursive helper method
        double result = helper(x, Math.abs(n));

        // Return result if n is positive else return inverse of result
        return n > 0 ? result : 1 / result;
    }

    // Helper method to find the power
    private static double helper(double x, int n) {
        // If n is zero then return one
        if (n == 0) {
            return 1;
        }

        // Initialize the half variable and find the result
        double half = helper(x, n / 2);

        // Return half * half if n is even else return x * half * half
        return n % 2 == 0 ? half * half : x * half * half;
    }

    // Main method to test myPow
    public static void main(String[] args) {
        double x = 2.1;
        int n = -5;

        double result = myPow(x, n);

        System.out.println("The result of " + x + " to the power of " + n + " is : " + result);
    }
}
