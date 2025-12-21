/*
LeetCode Problem: https://leetcode.com/problems/valid-parenthesis-string/

Question: 678. Valid Parenthesis String

Problem Statement: Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "(*)"
Output: true

Example 3:
Input: s = "(*))"
Output: true

Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
*/

/*
Approach: Greedy Range of Possible Open Bracket Counts

We validate a parentheses string that contains '(', ')', and '*'. 
The '*' can represent:
- '('
- ')'
- empty

We track the possible range of remaining unmatched '('.

Definitions:
- leftMin = minimum possible number of unmatched '('.
- leftMax = maximum possible number of unmatched '('.

Algorithm:
Traverse characters one by one and update the range:

1. If char is '(':
   leftMin++, leftMax++

2. If char is ')':
   leftMin--, leftMax--

3. If char is '*':
   leftMin--  (treat '*' as ')')
   leftMax++  (treat '*' as '(')

4. If leftMax becomes negative → too many ')' → invalid.

5. If leftMin becomes negative → reset leftMin = 0  
   (this means '*' can act as '(' or empty to compensate)

After the entire scan:
- Valid only if leftMin == 0 (minimum unmatched opens eliminated)

Why It Works:
- Tracks the full feasible range of open parentheses counts.
- Ensures every possibility is kept under constraints without brute force.

Time Complexity: O(n)
Space Complexity: O(1)
*/

package Greedy.Medium;

public class _678_Valid_Parenthesis_String {
    // Method to find the if the string of parenthesis is valid or not
    public static boolean checkValidString(String s) {
        // Initialize the leftMin and leftMax variable
        int leftMin = 0, leftMax = 0;

        // Iterate over the string s to find the valid solution
        for (char ch : s.toCharArray()) {
            // Update leftMin and leftMax based on the charecter ch
            switch (ch) {
                // If character is equal to '(' increment both leftMin and leftMax
                case '(' -> {
                    leftMin++;
                    leftMax++;
                }
                // If character is equal to ')' decrement both leftMin and leftMax
                case ')' -> {
                    leftMin--;
                    leftMax--;
                }
                // If character is equal to '*' decrement leftMin and increment leftMax
                default -> {
                    leftMin--;
                    leftMax++;
                }
            }

            // If leftMax become negative return false
            if (leftMax < 0) {
                return false;
            }

            // If leftMin become negative reset it to zero
            if (leftMin < 0) {
                leftMin = 0;
            }
        }

        // Return condition leftMin == 0
        return leftMin == 0;
    }

    // Main method to test checkValidString
    public static void main(String[] args) {
        String s = "(*))";

        boolean result = checkValidString(s);

        System.out.println("The string of parenthesis is" + (result ? "" : " not") + " valid.");
    }
}
