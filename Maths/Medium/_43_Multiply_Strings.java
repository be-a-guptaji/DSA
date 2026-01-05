/*
LeetCode Problem: https://leetcode.com/problems/multiply-strings/

Question: 43. Multiply Strings

Problem Statement: Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

Constraints:

1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
*/

/*
Approach: Grade-School Multiplication (Digit-by-Digit)

Goal:
Multiply two non-negative integers represented as strings and return the result
as a string, without using built-in big integer libraries.

Key Idea:
Simulate the traditional grade-school multiplication method using an integer array
to store intermediate results.

Algorithm:
1. Handle edge case:
   - If either number is "0", return "0".

2. Let:
   - len1 = length of num1
   - len2 = length of num2
   - result array size = len1 + len2
     (maximum possible digits in the product)

3. Traverse both strings from right to left:
   - Convert characters to digits.
   - Multiply digits and add to the appropriate position in result:
       sum = result[i + j + 1] + digit1 * digit2
       result[i + j + 1] = sum % 10
       result[i + j]    += sum / 10   (carry)

4. After processing all digit pairs:
   - Skip leading zeros in the result array.
   - Convert remaining digits to a string.

Why It Works:
- Each digit multiplication is placed at the correct positional index.
- Carry handling is done immediately, avoiding extra passes.
- Mimics manual multiplication accurately.

Time Complexity: O(n × m)
- n = length of num1
- m = length of num2

Space Complexity: O(n + m)
*/

package Maths.Medium;

public class _43_Multiply_Strings {
    // Method to multiply two number strings
    public static String multiply(String num1, String num2) {
        // If one of the string is zero then return the result zero
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // Get the length of the strings
        int len1 = num1.length(), len2 = num2.length();

        // Initialize the character array with both of the string
        char[] s1 = num1.toCharArray(), s2 = num2.toCharArray();

        // Initialize the result array with length equal to length of both string
        int[] result = new int[len1 + len2];

        // Iterate over the character array to find the multiplication result
        // Traverse from right to left to match grade-school multiplication
        for (int i = len1 - 1; i >= 0; i--) {
            // Get the current character of the index and convert into the number
            int number1 = s1[i] - '0';

            for (int j = len2 - 1; j >= 0; j--) {
                // Get the current character of the index and convert into the number
                int number2 = s2[j] - '0';

                // Fill the result array
                int sum = result[i + j + 1] + number1 * number2;
                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        // Initialize the string builder
        StringBuilder multiplicationResult = new StringBuilder();

        // Get the result length
        int index = 0;

        // Remove the left leading zero
        while (index < result.length && result[index] == 0) {
            // Increment the index
            index++;
        }

        // Aggregate the string
        while (index < result.length) {
            multiplicationResult.append(result[index++]);
        }

        // Return the result
        return multiplicationResult.toString();
    }

    // Main method to test multiply
    public static void main(String[] args) {
        String num1 = "123", num2 = "456";

        String result = multiply(num1, num2);

        System.out.println("The multiplication result of " + num1 + " and " + num2 + " is : " + result);
    }
}
