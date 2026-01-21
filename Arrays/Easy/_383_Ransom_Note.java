/*
LeetCode Problem: https://leetcode.com/problems/ransom-note/

Question: 383. Ransom Note

Problem Statement: Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.

Example 1:
Input: ransomNote = "a", magazine = "b"
Output: false

Example 2:
Input: ransomNote = "aa", magazine = "ab"
Output: false

Example 3:
Input: ransomNote = "aa", magazine = "aab"
Output: true 

Constraints:

1 <= ransomNote.length, magazine.length <= 10^5
ransomNote and magazine consist of lowercase English letters.
 */

/*
Approach: Character Frequency Decrement

Goal:
Determine whether the ransomNote string can be constructed using
letters from the magazine string.
Each character in magazine can be used at most once.

Key Idea:
Count available characters in magazine, then consume them while
building ransomNote.

Algorithm:
1. Create a frequency array of size 26 for lowercase letters.
2. Traverse magazine and increment frequency for each character.
3. Traverse ransomNote:
   - If frequency of a character is 0, return false (not enough letters).
   - Otherwise, decrement its frequency.
4. If all characters are successfully consumed, return true.

Why It Works:
- Frequency tracking ensures exact availability checks.
- Decrementing simulates using characters once.

Time Complexity: O(n + m)
- n = length of magazine
- m = length of ransomNote

Space Complexity: O(1)
*/

package Arrays.Easy;

public class _383_Ransom_Note {
    // Method to find if the ransomNote can be constructed by using the letters from
    // magazine
    public static boolean canConstruct(String ransomNote, String magazine) {
        // Initialize the frequency array for the magazine
        int[] frequency = new int[26];

        // Fill the frequency array with the magazine charachter frequency
        for (char ch : magazine.toCharArray()) {
            frequency[ch - 'a']++;
        }

        // Iterate over the ransomNote if frequency goes negative then return false
        for (char ch : ransomNote.toCharArray()) {
            if (frequency[ch - 'a'] == 0) {
                return false;
            }

            frequency[ch - 'a']--;
        }

        // Return true in the end
        return true;
    }

    // Main method to test canConstruct
    public static void main(String[] args) {
        String ransomNote = "aa", magazine = "aab";

        boolean result = canConstruct(ransomNote, magazine);

        System.out.println(
                "The ransomNote can" + (result ? " " : " not ") + "be constructed by using the letters from magazine.");
    }
}
