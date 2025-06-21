/*
LeetCode Problem: https://leetcode.com/problems/valid-palindrome/description/

Question: 125. Valid Palindrome

Problem Statement: A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.



Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
*/

/*
Approach: We use the two-pointer technique to solve the problem efficiently. Starting from both ends of the string, we move inward while skipping non-alphanumeric characters. At each step, we compare the lowercase form of the characters. If any pair doesn't match, the string is not a palindrome. If all valid characters match, the string is a valid palindrome.

Time Complexity: O(n), You scan each character at most once.
Space Complexity: O(1), You're not using extra space apart from variables (left, right), and the input is processed in-place.
*/

package Arrays.Easy;

public class _125_Valid_Palindrome {

    // Method to check if a string is a palindrome
    public static boolean isPalindrome(String s) {
        // Make Character Array for the string
        char[] chars = s.toCharArray();

        // Initialize two pointer
        int left = 0, right = chars.length - 1;

        // Iterate all the character from both the side to find palindrome
        while (left < right) {
            // Get the next valid letter from the array at the left side
            while (left < right && !Character.isLetterOrDigit(chars[left])) {
                left++;
            }

            // Get the next valid letter from the array at the right side
            while (left < right && !Character.isLetterOrDigit(chars[right])) {
                right--;
            }

            // Compare the letters and return false if they are not equal
            if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])) {
                return false;
            }

            left++; // Increment the left pointer
            right--; // Decrement the right pointer
        }

        // Return true if it is end of the array
        return true;
    }

    // Main method to test isPalindrome
    public static void main(String[] args) {
        String s = "race a car";

        boolean result = isPalindrome(s);

        if (result) {
            System.out.println("The given string is a valid palindrome.");
        } else {
            System.out.println("The given string is not a valid palindrome.");
        }
    }
}
