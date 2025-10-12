/*
LeetCode Problem: https://leetcode.com/problems/decode-ways/

Question: 91. Decode Ways

Problem Statement: You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.

The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:
Input: s = "12"
Output: 2
Explanation:
"12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: s = "226"
Output: 3
Explanation:
"226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:
Input: s = "06"
Output: 0
Explanation:
"06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
*/

/*
Approach:
We use a dynamic programming strategy with memoization to solve the problem of decoding a string of digits,
where each digit or pair of digits can represent a letter (e.g., '1' -> 'A', ..., '26' -> 'Z').

Steps:
- If the string starts with '0', return 0 because '0' cannot be decoded on its own.
- If the string length is 1 and not '0', return 1 since there's only one way to decode it.

- Initialize an integer array `ways[]` of size equal to the length of the string to store the number of ways
  to decode substrings up to each index.

- Base cases:
    - ways[0] = 1 (there's at least one valid way to decode the first character unless it's '0')
    - For the second character:
        - If the first two characters form a valid 2-digit code (10–26), and the second character is not '0', set ways[1] = 2
        - If the second character is '0', it must be part of a valid two-digit code with the first character (10 or 20),
          so set ways[1] = 1
        - If none of the above apply, set ways[1] = 1

- For each character from index 2 onward:
    - If the current character is not '0', add ways[i - 1] to ways[i]
    - If the 2-digit number formed by the previous character and current character is between 10 and 26, add ways[i - 2]
    - If the current character is '0' and not part of a valid 2-digit number (like '30', '40', etc.), return 0

- Finally, return ways[size - 1], which contains the number of ways to decode the full message.

Time Complexity: O(n), where n is the length of the input string.
Space Complexity: O(n), due to the use of the `ways` array for memoization.
*/

package DynamicProgramming.Medium;

public class _91_Decode_Ways {
    // Method to find the total decoded the message
    public static int numDecodings(String s) {
        // Get the size of the String s
        int size = s.length();

        // If s first index is 0 then return 0
        if (s.charAt(0) == '0') {
            return 0;
        }

        // If size is 1 then return 1
        if (size == 1) {
            return 1;
        }

        // Initialize the array for memoization
        int[] ways = new int[size];

        // Get the character at index i and i - 1
        char[] firstIndices = new char[] { s.charAt(0), s.charAt(1) };

        // Get the firstIndices number representation
        int alphabetCode = (firstIndices[0] - '0') * 10 + (firstIndices[1] - '0');

        // Initialize the ways[0] with the default value
        ways[0] = 1;

        // Check all the cases
        if (alphabetCode >= 10 && alphabetCode <= 26 && firstIndices[1] != '0') {
            ways[1] = 2;
        } else if (firstIndices[1] == '0') {
            if (firstIndices[0] == '1' || firstIndices[0] == '2') {
                ways[1] = 1;
            } else {
                return 0;
            }
        } else {
            ways[1] = 1;
        }

        for (int i = 2; i < size; i++) {
            // Get the character at index i and i - 1
            char[] lastIndices = new char[] { s.charAt(i - 1), s.charAt(i) };

            // Get the lastIndices number representation
            int number = (lastIndices[0] - '0') * 10 + (lastIndices[1] - '0');

            // Check all the cases
            if (lastIndices[1] != '0') {
                ways[i] += ways[i - 1];
            }

            if (number >= 10 && number <= 26) {
                ways[i] += ways[i - 2];
            }

            // If current is '0' and can't be paired with valid previous digit
            if (lastIndices[1] == '0' && (lastIndices[0] != '1' && lastIndices[0] != '2')) {
                return 0;
            }
        }

        // Return the number of ways to decode the message
        return ways[size - 1];
    }

    // Main method to test numDecodings
    public static void main(String[] args) {
        String s = "226";

        int result = numDecodings(s);

        System.out.println("Total decoded message for " + s + " is : " + result);
    }
}
