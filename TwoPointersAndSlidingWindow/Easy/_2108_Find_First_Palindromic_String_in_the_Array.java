/*
LeetCode Problem: https://leetcode.com/problems/find-first-palindromic-string-in-the-array/

Question: 2108. Find First Palindromic String in the Array

Problem Statement: Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".

A string is palindromic if it reads the same forward and backward.

Example 1:
Input: words = ["abc","car","ada","racecar","cool"]
Output: "ada"
Explanation: The first string that is palindromic is "ada".
Note that "racecar" is also palindromic, but it is not the first.

Example 2:
Input: words = ["notapalindrome","racecar"]
Output: "racecar"
Explanation: The first and only string that is palindromic is "racecar".

Example 3:
Input: words = ["def","ghi"]
Output: ""
Explanation: There are no palindromic strings, so the empty string is returned.

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists only of lowercase English letters.
 */

/*
Approach: Linear Scan with Two-Pointer Palindrome Check

Goal:
Return the first string in the array that is a palindrome.
If no palindromic string exists, return an empty string.

Key Idea:
- Check each word in order.
- As soon as a palindrome is found, return it.
- Use a two-pointer technique to verify palindrome efficiently.

Algorithm:
1. Iterate through each word in the array:
   - Call a helper method to check if the word is a palindrome.
2. Palindrome check (helper method):
   - Use two pointers: left at start, right at end.
   - While left < right:
       • If characters mismatch, return false.
       • Move left++, right--.
   - If loop completes, return true.
3. Return the first word that passes the palindrome check.
4. If none pass, return an empty string.

Why It Works:
- Order matters: the first valid palindrome is required.
- Two-pointer comparison checks palindrome in linear time.
- Early return avoids unnecessary checks.

Time Complexity:
- Worst case: O(n × m)
  where n = number of words, m = average word length.

Space Complexity:
- O(1)
  (constant extra space, ignoring input)
*/

package TwoPointersAndSlidingWindow.Easy;

public class _2108_Find_First_Palindromic_String_in_the_Array {
    // Method to find the first palindromic string in the array
    public static String firstPalindrome(String[] words) {
        // Iterate over the words array
        for (String word : words) {
            // If word is palindromic then return the string
            if (isPalindromicString(word)) {
                return word;
            }
        }

        // Retrun empty string in the end
        return "";
    }

    // Helper method to check if the given substring is palindromic or not
    private static boolean isPalindromicString(String word) {
        // Initialzie the left and right pointers
        int left = 0, right = word.length() - 1;

        // Traverse inward and compare characters to check for palindrome property
        while (left < right) {
            // If mismatch occurs, return false as it’s not a palindrome
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }

            left++; // Increment left pointer
            right--; // Decrement right pointer
        }

        // Return true if the entire substring is palindromic
        return true;
    }

    // Main method to test firstPalindrome
    public static void main(String[] args) {
        String[] words = new String[] { "abc", "car", "ada", "racecar", "cool" };

        String result = firstPalindrome(words);

        System.out.println("The first palindromic string in the array is : " + result);
    }
}
