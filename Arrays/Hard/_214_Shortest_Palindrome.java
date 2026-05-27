/*
LeetCode Problem: https://leetcode.com/problems/shortest-palindrome/

Question: 214. Shortest Palindrome

Problem Statement: You are given a string s. You can convert s to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"

Example 2:
Input: s = "abcd"
Output: "dcbabcd"

Constraints:
0 <= s.length <= 5 * 10^4
s consists of lowercase English letters only.
*/

/*
Approach: KMP Prefix Function (LPS Array)

Goal:
- Construct the shortest palindrome by adding characters
  in front of the string.

Core Idea:
- Find the longest palindromic prefix of the string.
- Only the remaining suffix needs to be reversed
  and added in front.
- Use KMP LPS computation on:
    original + '#' + reversed
- The final LPS value gives:
    length of longest palindromic prefix.

Algorithm Steps:
1. Reverse the original string.
2. Build combined string:
   original + '#' + reversed
3. Construct LPS array:
   - Standard KMP prefix-function computation.
4. Last LPS value:
   palindromeLength
5. Reverse the remaining suffix:
   original[palindromeLength ... end]
6. Add reversed suffix in front of original string.
7. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns the shortest palindrome obtainable
  by adding characters at the beginning.
*/

package Arrays.Hard;

// Solution Class
class Solution {
    // Method to find the shortest palindrome
    public String shortestPalindrome(String s) {
        // Convert the string into character array
        char[] original = s.toCharArray();

        // Initialize the reversed character array
        char[] reversed = new char[original.length];

        // Reverse the original array
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        // Initialize the combined array
        // combined = original + '#' + reversed
        char[] combined = new char[original.length + reversed.length + 1];

        // Initialize the index variable
        int index = 0;

        // Add the original array into combined array
        for (char ch : original) {
            combined[index++] = ch;
        }

        // Add the separator character
        combined[index++] = '#';

        // Add the reversed array into combined array
        for (char ch : reversed) {
            combined[index++] = ch;
        }

        // Initialize the lps array
        int[] lps = new int[combined.length];

        // Build the lps array
        for (int i = 1; i < combined.length; i++) {

            // Initialize the previous matched prefix length
            int j = lps[i - 1];

            // Find the longest valid prefix
            while (j > 0 && combined[i] != combined[j]) {
                j = lps[j - 1];
            }

            // If characters match then increase the prefix length
            if (combined[i] == combined[j]) {
                j++;
            }

            // Store the lps value
            lps[i] = j;
        }

        // Get the length of longest palindromic prefix
        int palindromeLength = lps[combined.length - 1];

        // Initialize the string builder
        StringBuilder sb = new StringBuilder();

        // Add the remaining suffix in reverse order
        for (int i = original.length - 1; i >= palindromeLength; i--) {
            sb.append(original[i]);
        }

        // Add the original string
        sb.append(s);

        // Return the shortest palindrome
        return sb.toString();
    }
}

public class _214_Shortest_Palindrome {
    // Main method to test the shortestPalindrome
    public static void main(String[] args) {
        String s = "aacecaaa";

        String result = new Solution().shortestPalindrome(s);

        System.out.println("The shortest palindrome you can find by performing this transformation is : " + result);
    }
}
