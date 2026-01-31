/*
LeetCode Problem: https://leetcode.com/problems/reverse-words-in-a-string-iii/

Question: 557. Reverse Words in a String III

Problem Statement: Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Example 2:
Input: s = "Mr Ding"
Output: "rM gniD"

Constraints:

1 <= s.length <= 5 * 10^4
s contains printable ASCII characters.
s does not contain any leading or trailing spaces.
There is at least one word in s.
All the words in s are separated by a single space.
 */

/*
Approach: In-place Word Reversal Using Two Pointers

Goal:
Reverse the characters of each word in a sentence while:
- Preserving word order
- Preserving spaces and their positions

Example:
Input : "Let's code"
Output: "s'teL edoc"

Key Idea:
- Process the string word by word.
- Reverse characters within each word independently.
- Do not move spaces.

Algorithm:
1. Convert the string to a character array (mutable).
2. Use two pointers:
   - start → beginning of a word
   - end   → scans forward to find space or end of string
3. For each word:
   - Move `end` until a space or string end is reached.
   - Reverse characters between `start` and `end - 1`.
4. Move `start` to the character after the space.
5. Repeat until the entire array is processed.
6. Convert the character array back to a string.

Why It Works:
- Each word is reversed in isolation.
- Spaces are untouched, so formatting is preserved.
- In-place swaps avoid extra memory usage.

Time Complexity:
- O(n), where n is the length of the string.

Space Complexity:
- O(1) extra space (in-place, excluding output string).
*/

package TwoPointersAndSlidingWindow.Easy;

public class _557_Reverse_Words_in_a_String_III {
    // Method to find he order of characters in each word within a sentence while
    // still preserving whitespace and initial word order
    public static String reverseWords(String s) {
        // Convert the string into the character array
        char[] str = s.toCharArray();

        // Intialize the start and end pointer
        int start = 0, end = 0;

        // Iterate over the nums array untill the end is less then the str length
        while (end < str.length) {
            // Update the end variable
            end = start;

            // Find the length of the word
            while (end < str.length && str[end] != ' ') {
                // Increment the end variable
                end++;
            }

            // Intialize the left and right pointer
            int left = start, right = end - 1;

            // Iterate over the the string untill pointer crosses each other
            while (left < right) {
                // Swap the characters
                char temp = str[left];
                str[left] = str[right];
                str[right] = temp;

                // Update the pointer
                left++;
                right--;
            }

            // Update the start and end pointer
            end++;
            start = end;
        }

        // Return the str by converting it to string
        return new String(str);
    }

    // Main method to test reverseWords
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";

        String result = reverseWords(s);

        System.out.println(
                "The reverse order of characters in each word within a sentence while still preserving whitespace and initial word order is : "
                        + result);
    }
}
