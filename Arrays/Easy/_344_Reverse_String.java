/*
LeetCode Problem: https://leetcode.com/problems/reverse-string/

Question: 344. Reverse String

Problem Statement: Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.



Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]


Constraints:

1 <= s.length <= 105
s[i] is a printable ascii character.
 */

/*
Approach: To reverse the string in-place, we use the two-pointer technique. We initialize one pointer at the beginning of the character array and another at the end. Then, we repeatedly swap the characters at these two pointers while moving them toward each other—incrementing the left pointer and decrementing the right—until they meet or cross.

Time Complexity: O(n), where n = length of the strings.
Space Complexity: O(1), as no extra space is needed on run time.
 */

package Arrays.Easy;

public class _344_Reverse_String {
    // Method to reverse the string
    public static void reverseString(char[] s) {
        // Initialize two pointer
        int left = 0, right = s.length - 1;

        // Initialize a temporary character variable
        char temp;

        // Iterate all the character from both the side to reverse the string
        while (left < right) {
            // Swap the characters
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++; // Increment the left pointer
            right--; // Decrement the right pointer
        }
    }

    // Main method to test reverseString
    public static void main(String[] args) {
        char[] s = { 'h', 'e', 'l', 'l', 'o' };

        reverseString(s);

        System.out.println(s);
    }
}
