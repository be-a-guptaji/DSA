/*
LeetCode Problem: https://leetcode.com/problems/decode-string/

Question: 394. Decode String

Problem Statement: Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 10^5.

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 */

/*
Approach: Two Stacks – String + Count (Nested Decoding)

Goal:
- Decode strings of the form:
      k[encoded_string]
- Where:
      k is a positive integer
      encoded_string may contain nested patterns

Example:
"3[a2[c]]" → "accaccacc"

Core Idea:
- Use two stacks:
    1) stringStack → stores previous partial strings
    2) countStack  → stores repetition counts
- Build the current substring using StringBuilder.

Algorithm Steps:

1. Initialize:
   - Stack<String> stringStack
   - Stack<Integer> countStack
   - StringBuilder cur = ""
   - int k = 0

2. Iterate through each character:

   Case 1: Digit
       - Build full number:
         k = k * 10 + digit

   Case 2: '['
       - Push current string into stringStack
       - Push k into countStack
       - Reset cur
       - Reset k

   Case 3: ']'
       - temp = cur (decoded inner substring)
       - Pop previous string from stringStack
       - Pop repetition count from countStack
       - Append temp "count" times to previous string
       - Set cur to updated string

   Case 4: Letter
       - Append to cur

3. Return cur.toString()

Why This Works:
- '[' marks start of a new nested level.
- ']' completes that level.
- Stacks preserve outer context.
- Handles nested patterns naturally.

Example Walkthrough:
Input: "2[ab3[c]]"

Process:
Push "",2
Push "ab",3
Build "c"
Expand → "ccc"
Combine → "abccc"
Repeat 2 times → "abcccabccc"

Time Complexity:
- O(n + decoded_length)

Space Complexity:
- O(n) for stacks and output

Result:
- Returns fully decoded string.
*/

package StacksAndQueues.Medium;

import java.util.Stack;

public class _394_Decode_String {
    // Method to find the decoded string
    public static String decodeString(String s) {
        // Initialize the stack for storing previous strings
        Stack<String> stringStack = new Stack<>();

        // Initialize the stack for storing repetition counts
        Stack<Integer> countStack = new Stack<>();

        // Initialize the current string builder
        StringBuilder cur = new StringBuilder();

        // Initialize the number variable
        int k = 0;

        // Iterate over each character of the string
        for (char c : s.toCharArray()) {
            // If the character is a digit, build the full number
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
                // If opening bracket is found
            } else if (c == '[') {
                // Push the current string into the string stack
                stringStack.push(cur.toString());

                // Push the current number into the count stack
                countStack.push(k);

                // Reset the current string builder
                cur = new StringBuilder();

                // Reset the number
                k = 0;
                // If closing bracket is found
            } else if (c == ']') {
                // Store the current built substring
                String temp = cur.toString();

                // Pop the previous string and make it the base
                cur = new StringBuilder(stringStack.pop());

                // Pop the repetition count
                int count = countStack.pop();

                // Append the substring 'count' times
                for (int i = 0; i < count; i++) {
                    cur.append(temp);
                }
                // If the character is a letter
            } else {
                // Append it to the current string
                cur.append(c);
            }
        }

        // Return the final decoded string
        return cur.toString();
    }

    // Main method to test decodeString
    public static void main(String[] args) {
        String s = "3[a2[c]]";

        String result = decodeString(s);

        System.out.println("The decoded string is : " + result);
    }
}
