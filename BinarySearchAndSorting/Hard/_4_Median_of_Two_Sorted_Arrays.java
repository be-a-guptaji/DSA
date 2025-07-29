/*
LeetCode Problem: https://leetcode.com/problems/median-of-two-sorted-arrays/

Question: 4. Median of Two Sorted Arrays

Problem Statement: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-10^6 <= nums1[i], nums2[i] <= 10^6
*/

/*
Approach: We use a **stack-based iterative approach** to simulate evaluation of the expression.

Key ideas:
1. Maintain a `result` which accumulates the running total.
2. Use a `sign` variable to track whether the current number is positive or negative.
3. Use a `stack` to store previous results and signs when we encounter `(`.
4. On encountering `)`, we complete the sub-expression by popping sign and previous result from the stack.
5. We handle multi-digit numbers by accumulating them as we iterate.
6. Spaces are skipped.

Steps:
- If current char is a digit → build the number.
- If '+' or '-' → finalize the previous number with its sign, update sign.
- If '(' → push result and sign to stack, reset result and sign.
- If ')' → compute subexpression and apply the sign and result from stack.

Time Complexity: O(n)
- Each character is visited once.

Space Complexity: O(n)
- Stack stores signs and results during nested expressions.

This approach avoids recursion and uses constant-time operations with each character.
*/

package BinarySearchAndSorting.Hard;

import java.util.Stack;

public class _4_Median_of_Two_Sorted_Arrays {
    // Method to find result form a valid expression written in string
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0; // running result
        int sign = 1; // current sign (+1 or -1)
        int num = 0; // current number being built

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // Build number character by character
                num = num * 10 + (ch - '0');
            } else if (ch == '+') {
                // Finalize the previous number
                result += sign * num;
                sign = 1;
                num = 0;
            } else if (ch == '-') {
                result += sign * num;
                sign = -1;
                num = 0;
            } else if (ch == '(') {
                // Push current result and sign to stack before new sub-expression
                stack.push(result);
                stack.push(sign);
                // Reset for new subexpression
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                // Complete current subexpression
                result += sign * num;
                num = 0;
                result *= stack.pop(); // Apply the sign before '('
                result += stack.pop(); // Add the result before '('
            }
            // Ignore spaces
        }

        // Add any leftover number after the last character
        result += sign * num;
        return result;
    }

    // Main method to test the calculate function
    public static void main(String[] args) {
        String s = "( 1 + ( 4+ 5+ 2) -3)+( 6+8)";

        int result = calculate(s);

        System.out.println("The result of the valid expression in string is : " + result);
    }
}
