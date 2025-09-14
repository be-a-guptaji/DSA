/*
LeetCode Problem: https://leetcode.com/problems/palindrome-number/

Question: 9. Palindrome Number

Problem Statement: Given an integer x, return true if x is a palindrome, and false otherwise.

Example 1:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

Example 2:
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Constraints:

-2^31 <= x <= 2^31 - 1

Follow up: Could you solve it without converting the integer to a string?
*/

/*
Approach:
1. If the input number is negative, immediately return false 
   because negative numbers cannot be palindromes (e.g., -121).   
2. Store the original number in a variable for later comparison.
3. Initialize a variable reversedNumber = 0.
   - Extract digits one by one from the original number using modulo (% 10).
   - Build the reversed number by multiplying reversedNumber by 10 and adding the extracted digit.
   - Divide the original number by 10 to remove the last digit.   
4. After the loop ends, compare originalNumber with reversedNumber.
   - If both are equal, return true.
   - Otherwise, return false.

Time Complexity: O(log10(n)), because we process each digit once.  
Space Complexity: O(1), since we use only a few extra variables.
*/

package Maths.Easy;

public class _9_Palindrome_Number {
    // Method to check whether given number is palindrome or not
    public static boolean isPalindrome(int x) {
        // Check if input number is negative
        if (x < 0) {
            return false;
        }

        // Initialize variables
        int originalNumber = x;
        int reversedNumber = 0;

        while (x != 0) {
            // Get the last number from the number
            int mod = x % 10;

            // Add the number to the last of the reversed number
            reversedNumber = reversedNumber * 10 + mod;

            // Remove the last number form the number
            x /= 10;
        }

        // Return true if the number is same as the original number else false
        return originalNumber == reversedNumber;
    }

    // Main method to test isPalindrome
    public static void main(String[] args) {
        int x = -121;

        boolean result = isPalindrome(x);

        if (result)
            System.out.println("The number " + x + " is a palindrome.");
        else
            System.out.println("The number " + x + " is not a palindrome.");
    }
}
