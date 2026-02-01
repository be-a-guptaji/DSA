/*
LeetCode Problem: https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/

Problem: 1662. Check If Two String Arrays are Equivalent

Problem Statement: Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

A string is represented by an array if the array elements concatenated in order forms the string.

Example 1:
Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.

Example 2:
Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
Output: false

Example 3:
Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
Output: true

Constraints:

1 <= word1.length, word2.length <= 10^3
1 <= word1[i].length, word2[i].length <= 10^3
1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
word1[i] and word2[i] consist of lowercase letters.
*/

/*
Approach: String Concatenation and Comparison

Problem:
Check whether two string arrays represent the same string
after concatenating their elements in order.

Core Idea:
- Concatenate all strings in `word1` into a single string.
- Concatenate all strings in `word2` into another single string.
- Compare the two final strings for equality.

Algorithm:
1. Initialize two StringBuilders: sb1 and sb2.
2. Append each string from `word1` into sb1.
3. Append each string from `word2` into sb2.
4. Convert both StringBuilders to strings.
5. Return true if both strings are equal, otherwise false.

Why It Works:
- The problem only cares about the final formed string, not how it is split.
- StringBuilder is efficient for repeated string concatenation.

Time Complexity:
- O(n + m), where n and m are total characters in `word1` and `word2`.

Space Complexity:
- O(n + m), for storing the concatenated strings.

Note:
- This is clear and readable.
- Can be optimized to O(1) extra space using two pointers without building strings.
*/

package TwoPointersAndSlidingWindow.Easy;

public class _1662_Check_If_Two_String_Arrays_are_Equivalent {
    // Method to determine if two arrays are equal or not
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // Initialize two string builder for the checking
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();

        // Add all the word to the string builder1
        for (String word : word1) {
            sb1.append(word);
        }

        // Add all the word to the string builder2
        for (String word : word2) {
            sb2.append(word);
        }

        // Return the function which give true or false according to the string
        return sb1.toString().equals(sb2.toString());
    }

    // Main method to test arrayStringsAreEqual
    public static void main(String[] args) {
        String[] word1 = new String[] { "abc", "d", "defg" };
        String[] word2 = new String[] { "abcddefg" };

        boolean result = arrayStringsAreEqual(word1, word2);

        System.out.println("The two arrays" + (result ? " " : " not ") + "are equal.");
    }
}
