/*
LeetCode Problem: https://leetcode.com/problems/string-compression/

Question: 443. String Compression

Problem Statement: Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Note: The characters in the array beyond the returned length do not matter and should be ignored.

Example 1:
Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

Example 2:
Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.

Example 3:
Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

Constraints:

1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 */

/*
Approach: Two Pointers + In-place Compression

Problem:
Compress the character array in-place such that consecutive repeating characters
are replaced by the character followed by the count (only if count > 1).

Core Idea:
- Traverse the array while counting consecutive identical characters.
- Write the compressed output back into the same array using a write pointer.
- Convert counts to characters when needed.

Algorithm:
1. Use `currIndex` as a write pointer for the compressed result.
2. Use `count` to track consecutive occurrences of the same character.
3. Traverse the array from index 1:
   - If current char equals previous, increment count.
   - Otherwise:
     - If count > 1, write digits of count after the character.
     - Write the new character.
     - Reset count to 1.
4. After the loop, handle the final group similarly.
5. Return `currIndex + 1` as the new length.

Why It Works:
- Each character group is processed exactly once.
- Compression is done directly in the input array (no extra array used).
- Count digits are handled correctly even for multi-digit counts.

Time Complexity:
- O(n), where n is the length of chars.

Space Complexity:
- O(1) extra space (in-place, excluding temporary count string).
*/

package TwoPointersAndSlidingWindow.Medium;

public class _443_String_Compression {
    // Method to compress the character array in-place
    public static int compress(char[] chars) {
        // If array has 0 or 1 character, no compression needed
        if (chars.length <= 1) {
            return chars.length;
        }

        // Index to write the compressed result
        int currIndex = 0;

        // Counter to track consecutive characters
        int count = 1;

        // Iterate through the character array starting from index 1
        for (int i = 1; i < chars.length; i++) {
            // If current character is same as previous, increment count
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                // If count is more than 1, write the count digits
                if (count != 1) {
                    // Convert count to string
                    String nums = String.valueOf(count);

                    // Convert string to character array
                    char[] numsChars = nums.toCharArray();

                    // Write each digit into chars array
                    for (char ch : numsChars) {
                        chars[++currIndex] = ch;
                    }
                }

                // Write the new character
                chars[++currIndex] = chars[i];

                // Reset count for new character
                count = 1;
            }
        }

        // Handle the last group of characters
        if (count != 1) {
            // Convert count to string
            String nums = String.valueOf(count);

            // Convert string to character array
            char[] numsChars = nums.toCharArray();

            // Write each digit into chars array
            for (char ch : numsChars) {
                chars[++currIndex] = ch;
            }
        }

        // Return the length of the compressed array
        return currIndex + 1;
    }

    // Main method to test compress
    public static void main(String[] args) {
        char[] chars = new char[] { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };

        int result = compress(chars);

        System.out.println("The length compressed string is : " + result);
    }
}
