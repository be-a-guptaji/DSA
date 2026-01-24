/*
LeetCode Problem: https://leetcode.com/problems/longest-palindrome/

Question: 409. Longest Palindrome

Problem Statement: Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome.

Example 1:
Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

Example 2:
Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.

Constraints:

1 <= s.length <= 2000
s consists of lowercase and/or uppercase English letters only.
*/

/*
Approach: Frequency Counting with Center Allowance

Goal:
Find the maximum length of a palindrome that can be built using
the characters of the given string.

Key Idea:
- A palindrome uses characters in pairs (even counts).
- At most one character with an odd frequency can be placed in the center.

Algorithm:
1. Create a frequency array (size 128 for ASCII characters).
2. Traverse the string and count the frequency of each character.
3. Initialize:
   - longestPalindromeLength = 0
   - singleCharacter = false (tracks if any odd count exists)
4. For each character frequency:
   - If frequency is odd, mark singleCharacter = true.
   - Add the largest even part of frequency to the result:
       • (frequency / 2) * 2
5. If there was any odd frequency, add 1 to allow a center character.
6. Return the final length.

Why It Works:
- Even counts fully contribute to both halves of the palindrome.
- Only one odd-count character can sit in the center.
- Using integer division efficiently extracts the usable pairs.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _409_Longest_Palindrome {
    // Method to find the length of the longest palindrome that can be built with
    // those letters
    public static int longestPalindrome(String s) {
        // Initialize the frequency array for the character
        int[] frequency = new int[128];

        // Iterate over the string array to fill the frequency array
        for (char ch : s.toCharArray()) {
            frequency[ch]++;
        }

        // Initialize the variable to hold the longest palindrome length
        int longestPalindromeLength = 0;

        // Initialize the boolean variable for checking if the any character only occur
        // ones
        boolean singleCharacter = false;

        // Iterate over the frequency array to find out the longest palindrome length
        for (int freq : frequency) {
            if ((freq & 1) == 1) {
                singleCharacter = true;
            }

            freq = freq >> 1;
            longestPalindromeLength += freq << 1;
        }

        // Return longestPalindromeLength
        return longestPalindromeLength + (singleCharacter ? 1 : 0);
    }

    // Main method to test longestPalindrome
    public static void main(String[] args) {
        String s = "abccccdd";

        int result = longestPalindrome(s);

        System.out.println("The length of the longest palindrome that can be built with those letters is : " + result);
    }
}
