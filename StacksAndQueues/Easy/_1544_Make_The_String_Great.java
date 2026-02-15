/*
LeetCode Problem: https://leetcode.com/problems/make-the-string-great/

Question: 1544. Make The String Great

Problem Statement: Given a string s of lower and upper case English letters.

A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:

0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.

Notice that an empty string is also good.

Example 1:
Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".

Example 2:
Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""

Example 3:
Input: s = "s"
Output: "s"

Constraints:

1 <= s.length <= 100
s contains only lower and upper case English letters.
 */

/*
Approach: Stack Simulation Using StringBuilder

Goal:
- Remove adjacent characters where:
      same letter but different case
      (e.g., 'a' and 'A')
- Continue removing until no such adjacent pair exists.
- The final result is guaranteed unique.

Key Observation:
- ASCII difference between lowercase and uppercase
  versions of the same letter is 32.
    Example:
        'a' - 'A' = 32

Core Idea:
- Use StringBuilder as a stack.
- Compare current character with the top of the stack.
- If they form a "bad pair", remove the top.
- Otherwise, push current character.

Algorithm:

1. Initialize empty StringBuilder (acts like stack).

2. For each character ch in string:
   - If stack is not empty AND
     abs(top - ch) == 32:
         → remove top (delete bad pair)
   - Else:
         → append ch to stack

3. Return stack as string.

Why This Works:
- Removing a bad pair may create a new bad pair,
  but stack structure naturally handles that.
- Each character is added and removed at most once.

Example:
Input: "leEeetcode"

Process:
l → l
e → le
E → l      (eE removed)
e → le
e → lee
t → leet
...

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns the cleaned string after removing all bad pairs.
*/

package StacksAndQueues.Easy;

public class _1544_Make_The_String_Great {
    // Method to find the string after making it good. The answer is guaranteed to
    // be unique under the given constraints
    public static String makeGood(String s) {
        // Initialize the string builder to act as a stack
        StringBuilder sb = new StringBuilder();

        // Iterate over the string
        for (char ch : s.toCharArray()) {
            // Get the length
            int length = sb.length();

            // Update the string builder according to the condition
            if (length > 0 && Math.abs(sb.charAt(length - 1) - ch) == 32) {
                sb.deleteCharAt(length - 1);
            } else {
                sb.append(ch);
            }
        }

        // Return the string builder as a string
        return sb.toString();
    }

    // Main method to test makeGood
    public static void main(String[] args) {
        String s = "leEeetcode";

        String result = makeGood(s);

        System.out.println(
                "The string after making it good. The answer is guaranteed to be unique under the given constraints is : "
                        + result);
    }
}
