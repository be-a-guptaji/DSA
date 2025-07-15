/*
LeetCode Problem: https://leetcode.com/problems/string-to-integer-atoi/

Question: 8. String to Integer (atoi)

Problem Statement: Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.

The algorithm for myAtoi(string s) is as follows:

Whitespace: Ignore any leading whitespace (" ").
Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 2^31 - 1 should be rounded to 2^31 - 1.
Return the integer as the final result.

Example 1:
Input: s = "42"
Output: 42
Explanation:
The underlined characters are what is read in and the caret is the current reader position.
Step 1: "42" (no characters read because there is no leading whitespace)
         ^
Step 2: "42" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "42" ("42" is read in)
           ^

Example 2:
Input: s = " -042"
Output: -42
Explanation:
Step 1: "   -042" (leading whitespace is read and ignored)
            ^
Step 2: "   -042" ('-' is read, so the result should be negative)
             ^
Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
               ^

Example 3:
Input: s = "1337c0d3"
Output: 1337
Explanation:
Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
         ^
Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
             ^

Example 4:
Input: s = "0-1"
Output: 0
Explanation:
Step 1: "0-1" (no characters read because there is no leading whitespace)
         ^
Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
          ^

Example 5:
Input: s = "words and 987"
Output: 0
Explanation:
Reading stops at the first non-digit character 'w'.

Constraints:

0 <= s.length <= 200
s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 */

/*
Approach: The myAtoi() function simulates the behavior of the C-style atoi function by carefully parsing a string to extract a valid 32-bit signed integer. The approach begins by skipping any leading whitespace characters. After trimming spaces, the function checks if the next character is a sign ('+' or '-') and records whether the final result should be negative. It then iterates through the string and processes characters as long as they are digits, converting each character to its numeric value. To prevent integer overflow, the function checks before every multiplication and addition whether the result would exceed Integer.MAX_VALUE or fall below Integer.MIN_VALUE, and clamps the value accordingly if an overflow is detected. If no valid digits are found during parsing, or if the string is empty or contains only whitespace or non-digit characters, the function returns 0. This approach ensures correct parsing and adheres strictly to the 32-bit signed integer range, with a time complexity of O(n) and space complexity of O(1).

Time Complexity:  O(n), where n = length of string.
Space Complexity: O(1).
 */

package Arrays.Medium;

public class _8_String_to_Integer_atoi {
    // Method to find the number in the string
    public static int myAtoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int index = 0, n = s.length();
        int num = 0;
        boolean isNegative = false;

        // 1. Skip leading whitespaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // 2. Check for sign
        if (index < n) {
            if (s.charAt(index) == '-') {
                isNegative = true;
                index++;
            } else if (s.charAt(index) == '+') {
                index++;
            }
        }

        // 3. Convert digits and handle overflow
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            // Check for overflow before adding digit
            if (num > (Integer.MAX_VALUE - digit) / 10) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            num = num * 10 + digit;
            index++;
        }

        return isNegative ? -num : num;
    }

    // Main method to test myAtoi
    public static void main(String[] args) {
        String s = "42";

        int result = myAtoi(s);

        System.out.println("The number in the string is : " + result);
    }
}
