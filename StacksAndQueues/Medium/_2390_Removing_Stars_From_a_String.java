/*
LeetCode Problem: https://leetcode.com/problems/removing-stars-from-a-string/

Question: 2390. Removing Stars From a String

Problem Statement: You are given a string s, which contains stars *.

In one operation, you can:

Choose a star in s.
Remove the closest non-star character to its left, as well as remove the star itself.
Return the string after all stars have been removed.

Note:

The input will be generated such that the operation is always possible.
It can be shown that the resulting string will always be unique.

Example 1:
Input: s = "leet**cod*e"
Output: "lecoe"
Explanation: Performing the removals from left to right:
- The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
- The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
- The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
There are no more stars, so we return "lecoe".

Example 2:
Input: s = "erase*****"
Output: ""
Explanation: The entire string is removed, so we return an empty string.

Constraints:

1 <= s.length <= 10^5
s consists of lowercase English letters and stars *.
The operation above can be performed on s.
 */

/*
Approach: Stack Simulation (Character Removal)

Goal:
- Each '*' removes the closest non-star character to its left.
- Return the final string after processing all stars.

Key Idea:
- Use a stack-like structure.
- When encountering:
    - Normal character → push to stack
    - '*' → pop last character from stack

Since every '*' guarantees a character to remove,
we don't need extra validation.

Algorithm:

1. Convert string to char array.
2. Create a char[] stack of same size.
3. Maintain stackPointer = 0.

4. Iterate over characters:
   - If current char == '*'
        → stackPointer--
   - Else
        → stack[stackPointer++] = char

5. Build result from stack[0...stackPointer-1].

Why This Works:
- Each character is pushed once and popped at most once.
- Simulates removal in O(1) per operation.
- Avoids expensive string deletions.

Example:
Input: "leet**cod*e"

Process:
l → l
e → le
e → lee
t → leet
* → lee
* → le
c → lec
o → leco
d → lecod
* → leco
e → lecoe

Output: "lecoe"

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns final string after removing stars and their preceding characters.
*/

package StacksAndQueues.Medium;

public class _2390_Removing_Stars_From_a_String {
    // Method to find the string after all stars have been removed
    public static String removeStars(String s) {
        // Convert the string into character array
        char[] str = s.toCharArray();

        // Initialize the character array as a stack
        char[] stack = new char[str.length];

        // Initialize the stack pointer
        int stackPointer = 0;

        // Iterate over the string
        for (int i = 0; i < str.length; i++) {
            // Update the stack according to the condition
            if (str[i] == '*') {
                stackPointer--;
            } else {
                stack[stackPointer++] = str[i];
            }
        }

        // Initialize the string builder to act as a stack
        StringBuilder sb = new StringBuilder();

        // Build the valid string
        for (int i = 0; i < stackPointer; i++) {
            sb.append(stack[i]);
        }

        // Return the string builder as a string
        return sb.toString();
    }

    // Main method to test removeStars
    public static void main(String[] args) {
        String s = "leet**cod*e";

        String result = removeStars(s);

        System.out.println("The string after all stars have been removed is : " + result);
    }
}
