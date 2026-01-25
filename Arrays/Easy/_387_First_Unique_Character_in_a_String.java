/*
LeetCode Problem: https://leetcode.com/problems/first-unique-character-in-a-string/

Question: 387. First Unique Character in a String

Problem Statement: Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

Example 1:
Input: s = "leetcode"
Output: 0
Explanation:
The character 'l' at index 0 is the first character that does not occur at any other index.

Example 2:
Input: s = "loveleetcode"
Output: 2

Example 3:
Input: s = "aabb"
Output: -1

Constraints:

1 <= s.length <= 10^5
s consists of only lowercase English letters.
 */

/*
Approach: Frequency Counting + First Index Scan

Goal:
Find the index of the first non-repeating character in a string.
If none exists, return −1.

Key Idea:
A character is non-repeating if its frequency is exactly 1.
Use two passes:
- First to count frequencies.
- Second to find the earliest index with frequency 1.

Algorithm:
1. Convert the string to a character array.
2. Create a frequency array of size 26 for lowercase letters.
3. First pass:
   - Increment frequency[ch − 'a'] for each character.
4. Second pass:
   - Traverse the string in order.
   - Return the first index i where frequency[str[i] − 'a'] == 1.
5. If no such character exists, return −1.

Why It Works:
- Frequency counting identifies unique characters.
- Second pass preserves the original order to find the first occurrence.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _387_First_Unique_Character_in_a_String {
    // Method to find the first non-repeating character in string
    public static int firstUniqChar(String s) {
        // Convert string into character array
        char[] str = s.toCharArray();

        // Initialize the frequency array for holding the character frequency
        int[] frequency = new int[26];

        // Fill the frequency array
        for (char ch : str) {
            frequency[ch - 'a']++;
        }

        // Iterate over the character array to find the first index of non-repeating
        // character in string
        for (int i = 0; i < str.length; i++) {
            if (frequency[str[i] - 'a'] == 1) {
                return i;
            }
        }
        
        // Return -1 if no non-repeating character is found
        return -1;
    }

    // Main method to test firstUniqChar
    public static void main(String[] args) {
        String s = "loveleetcode";

        int result = firstUniqChar(s);

        System.out.println("The first index of non-repeating character in " + s + " is : " + result);
    }
}
