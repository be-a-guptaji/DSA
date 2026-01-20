/*
LeetCode Problem: https://leetcode.com/problems/count-the-number-of-consistent-strings/

Question: 1684. Count the Number of Consistent Strings

Problem Statement: You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once for each word in words).

Return the sum of lengths of all good strings in words.

Example 1:
Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

Example 2:
Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.

Constraints:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.
 */

/*
Approach: Character Allow-List Validation

Goal:
Count how many strings in `words` are consistent.
A string is consistent if all of its characters appear in the `allowed` string.

Key Idea:
Pre-mark all allowed characters, then verify each word contains
only those characters.

Algorithm:
1. Create a boolean array `isAllowed[26]` to mark characters present in `allowed`.
2. Traverse the `allowed` string and mark corresponding indices as true.
3. Initialize a counter `consistentStrings = 0`.
4. For each word in `words`:
   - Assume the word is valid.
   - Traverse its characters:
       • If any character is not marked in `isAllowed`, mark invalid and break.
   - If valid, increment `consistentStrings`.
5. Return `consistentStrings`.

Why It Works:
- Constant-time checks for character validity using a boolean array.
- Each word is checked independently.

Time Complexity: O(n × m)
- n = number of words
- m = average word length

Space Complexity: O(1)
- Fixed-size array of 26 characters
*/

package Arrays.Easy;

public class _1684_Count_the_Number_of_Consistent_Strings {
    // Method to find the number of consistent strings in the array words
    public static int countConsistentStrings(String allowed, String[] words) {
        // Initialize the consistent strings variable for keeping track of the
        // consistent string
        int consistentStrings = 0;

        // Initialize the boolean array to keep track of the allowed character
        boolean[] isAllowed = new boolean[26];

        // Initiate the boolean array
        for (char ch : allowed.toCharArray()) {
            isAllowed[ch - 'a'] = true;
        }

        // Iterate over the words for finding out the words
        for (String word : words) {
            // Initialize the boolean variable for allowed ot not
            boolean allowedCharactersOnly = true;

            // Iterate over the word array for finding if the word is allowed or not
            for (char ch : word.toCharArray()) {
                // If character is not allowed then break the loop and mark the
                // allowedCharactersOnly to false
                if (!isAllowed[ch - 'a']) {
                    allowedCharactersOnly = false;
                    break;
                }
            }

            // If allowedCharactersOnly is true then increment the consistentStrings
            if (allowedCharactersOnly) {
                consistentStrings++;
            }
        }

        // Return the consistentStrings
        return consistentStrings;
    }

    // Main method to test countConsistentStrings
    public static void main(String[] args) {
        String[] words = new String[] { "ad", "bd", "aaab", "baa", "badab" };
        String allowed = "ab";

        int result = countConsistentStrings(allowed, words);

        System.out.println("The number of consistent strings in the array words is : " + result);
    }
}
