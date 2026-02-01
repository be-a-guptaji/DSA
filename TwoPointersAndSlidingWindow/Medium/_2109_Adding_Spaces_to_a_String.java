/*
LeetCode Problem: https://leetcode.com/problems/adding-spaces-to-a-string/

Question: 2109. Adding Spaces to a String

Problem Statement: You are given a 0-indexed string s and a 0-indexed integer array spaces that describes the indices in the original string where spaces will be added. Each space should be inserted before the character at the given index.

For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], we place spaces before 'Y' and 'C', which are at indices 5 and 9 respectively. Thus, we obtain "Enjoy Your Coffee".
Return the modified string after the spaces have been added.

Example 1:
Input: s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
Output: "Leetcode Helps Me Learn"
Explanation: 
The indices 8, 13, and 15 correspond to the underlined characters in "LeetcodeHelpsMeLearn".
We then place spaces before those characters.

Example 2:
Input: s = "icodeinpython", spaces = [1,5,7,9]
Output: "i code in py thon"
Explanation:
The indices 1, 5, 7, and 9 correspond to the underlined characters in "icodeinpython".
We then place spaces before those characters.

Example 3:
Input: s = "spacing", spaces = [0,1,2,3,4,5,6]
Output: " s p a c i n g"
Explanation:
We are also able to place spaces before the first character of the string.

Constraints:

1 <= s.length <= 3 * 10^5
s consists only of lowercase and uppercase English letters.
1 <= spaces.length <= 3 * 10^5
0 <= spaces[i] <= s.length - 1
All the values of spaces are strictly increasing.
 */

/*
Approach: Two-Pointer + StringBuilder

Problem:
Given a string s and an array spaces[], insert a space character ' ' at each index
specified in spaces. Indices are in ascending order.

Core Idea:
- Traverse the string using a moving pointer.
- For each index in spaces[], append the substring from the last position up to
  the current space index, then append a space.
- Finally, append the remaining part of the string.

Algorithm:
1. Initialize a StringBuilder to build the result efficiently.
2. Maintain a pointer `start` that tracks the last copied position in s.
3. For each index `i` in spaces:
   - Append substring s[start : i] to StringBuilder.
   - Append a space character.
   - Update start = i.
4. After processing all spaces, append the remaining substring s[start : end].
5. Convert StringBuilder to string and return.

Why It Works:
- spaces[] is sorted, so substrings are appended in correct order.
- Each character of s is copied exactly once.
- StringBuilder avoids costly string concatenations.

Time Complexity:
- O(n), where n = length of s

Space Complexity:
- O(n), for the output StringBuilder
*/

package TwoPointersAndSlidingWindow.Medium;

public class _2109_Adding_Spaces_to_a_String {
    // Method to find the modified string after the spaces have been added
    public static String addSpaces(String s, int[] spaces) {
        // Initialize the string builder for the result
        StringBuilder sb = new StringBuilder();

        // Initialize the start for the spaces
        int start = 0;

        // Iterate over the spaces to add spaces
        for (int i : spaces) {
            // Add the sub string to the string builder
            sb.append(s, start, i);

            // Add space to the string builder
            sb.append(' ');

            // Update the start vairable
            start = i;
        }

        // Add the redundant string to the string builder
        sb.append(s.substring(start));

        // Return the string builder by converting it to the string
        return sb.toString();
    }

    // Main method to test addSpaces
    public static void main(String[] args) {
        String s = "icodeinpython";
        int[] spaces = new int[] { 1, 5, 7, 9 };

        String result = addSpaces(s, spaces);

        System.out.println("The modified string after the spaces have been added is : " + result);
    }
}
