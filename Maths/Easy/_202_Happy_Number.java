/*
LeetCode Problem: https://leetcode.com/problems/happy-number/

Question: 202. Happy Number

Problem Statement: Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

Example 1:
Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

Example 2:
Input: n = 2
Output: false

Constraints:

1 <= n <= 2^31 - 1
*/

/*
Approach: Floyd’s Cycle Detection (Two Pointers)

Goal:
Determine whether a number is a "happy number".
A number is happy if repeatedly replacing it with the sum of the squares of its digits
eventually results in 1.

Key Insight:
- The sequence of transformations will either reach 1 or enter a cycle.
- Cycle detection can be done using slow and fast pointers.

Algorithm:
1. Initialize:
   - slow = n
   - fast = sumOfSquares(n)

2. While slow != fast:
   - slow moves one step: slow = sumOfSquares(slow)
   - fast moves two steps: fast = sumOfSquares(sumOfSquares(fast))

3. When slow == fast:
   - If slow == 1 → happy number.
   - Else → cycle detected → not happy.

Why It Works:
- Floyd’s algorithm detects cycles without extra space.
- If the cycle includes 1, the number is happy.

Time Complexity: O(log n)  
Space Complexity: O(1)
*/

package Maths.Easy;

public class _202_Happy_Number {
    // Method to find if the number is happy or not
    public static boolean isHappy(int n) {
        // Initialize two variable one slow pointer and one fast pointer
        int slow = n, fast = sumOfSquares(n);

        // Iterate over the loop untill the slow and fast pointer converges
        while (slow != fast) {
            // Move the fast pointer two time
            fast = sumOfSquares(sumOfSquares(fast));

            // Move the slow pointer ones
            slow = sumOfSquares(slow);
        }

        // Return a contition if slow is equal to the one
        return slow == 1;
    }

    // Helper method to find the squares of the digits in the number
    private static int sumOfSquares(int n) {
        // Initialize the result variable
        int result = 0;

        // Loop over the n untill it become zero
        while (n != 0) {
            // Get the digit and add to the result variable
            result += (int) Math.pow(n % 10, 2);

            // Remove the last part of the number
            n /= 10;
        }

        // Retrun the result
        return result;
    }

    // Main method to test isHappy
    public static void main(String[] args) {
        int n = 19;

        boolean result = isHappy(n);

        System.out.println("The number " + n + " is " + (result ? "" : "not ") + "a happy number.");
    }
}
