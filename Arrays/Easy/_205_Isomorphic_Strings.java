/*
LeetCode Problem: https://leetcode.com/problems/isomorphic-strings/

Question: 205. Isomorphic Strings

Problem Statement: Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true
Explanation:
The strings s and t can be made identical by:
Mapping 'e' to 'a'.
Mapping 'g' to 'd'.

Example 2:
Input: s = "foo", t = "bar"
Output: false
Explanation:
The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
*/

/*
Approach: Bidirectional Character Mapping

Goal:
Determine whether two strings s and t are isomorphic.
Two strings are isomorphic if characters in s can be replaced to get t,
with a one-to-one mapping.

Key Idea:
Maintain a consistent mapping in both directions:
- s → t
- t → s

Algorithm:
1. Convert both strings to character arrays.
2. Use two fixed-size arrays (ASCII):
   - mapST[ch] stores the mapped character from s to t.
   - mapTS[ch] stores the mapped character from t to s.
3. Traverse both strings index by index:
   - If an existing mapping conflicts in either direction, return false.
   - Otherwise, record the mapping in both arrays.
4. If no conflicts are found, return true.

Why It Works:
- Bidirectional mapping ensures one-to-one correspondence.
- Prevents multiple characters mapping to the same character.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _205_Isomorphic_Strings {
    // Method to determine if strings are isomorphic or not
    public static boolean isIsomorphic(String s, String t) {
        // Convert strings into character array
        char[] str1 = s.toCharArray(), str2 = t.toCharArray();

        // Initialize the hash maps for the ismorphic string
        int[] mapST = new int[128], mapTS = new int[128];

        // Iterate over the str's to determine if strings are isomorphic or not
        for (int i = 0; i < str1.length; i++) {
            // Get the character
            int ch1 = str1[i], ch2 = str2[i];

            // If maps is not null and value dosen't match to the other string then retrun
            // false
            if ((mapST[ch1] != 0 && mapST[ch1] != ch2) || (mapTS[ch2] != 0 && mapTS[ch2] != ch1)) {
                return false;
            }

            // Add the values to the map
            mapST[ch1] = ch2;
            mapTS[ch2] = ch1;
        }

        // Retrun true in the end
        return true;
    }

    // Main method to test isIsomorphic
    public static void main(String[] args) {
        String s = "foo", t = "bar";

        boolean result = isIsomorphic(s, t);

        System.out.println("The string " + s + " and " + t + " are" + (result ? " " : " not ") + "isomorphic.");
    }
}
