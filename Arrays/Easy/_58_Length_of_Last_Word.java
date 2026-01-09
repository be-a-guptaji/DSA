/*
LeetCode Problem: https://leetcode.com/problems/length-of-last-word/

Question: 58. Length of Last Word

Problem Statement: Given a string s consisting of words and spaces, return the length of the last word in the string.

A word is a maximal substring consisting of non-space characters only.

Example 1:
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.

Example 2:
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.

Example 3:
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.

Constraints:

1 <= s.length <= 10^4
s consists of only English letters and spaces ' '.
There will be at least one word in s.
*/

/*
Approach: Reverse Traversal with Space Skipping

Goal:
Find the length of the last word in a given string.
A word is defined as a sequence of non-space characters.

Algorithm:
1. Traverse the string from right to left.
2. Skip any trailing spaces.
3. Once a non-space character is found:
   - Start counting characters.
4. Stop counting when a space is encountered after counting has started.
5. Return the counted length.

Why It Works:
- The last word is located at the end of the string, ignoring trailing spaces.
- A single backward traversal is sufficient.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _58_Length_of_Last_Word {
    // Method to find the length of the last word
    public static int lengthOfLastWord(String s) {
        // Convert the string s into the character array
        char[] str = s.toCharArray();

        // Initialize the length of the array
        int length = str.length;

        // Initialize the variable to hold the length of the last word
        int wordLength = 0;

        // Iterate over the str array in right to left manner
        for (int i = length - 1; i > -1; i--) {
            // If word length is not zero and we encounter space then break
            if (wordLength != 0 && str[i] == ' ') {
                break;
            }

            // If we encounter character then increase the word length
            if (str[i] != ' ') {
                wordLength++;
            }
        }

        // Return the word length
        return wordLength;
    }

    // Main method to test lengthOfLastWord
    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";

        int result = lengthOfLastWord(s);

        System.out.println("The length of the last word in the string " + s + " is : " + result);
    }
}
