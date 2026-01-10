/*
LeetCode Problem: https://leetcode.com/problems/longest-common-prefix/

Question: 14. Longest Common Prefix

Problem Statement: Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters if it is non-empty.
*/

/*
Approach: Incremental Prefix Comparison (Greedy)

Goal:
Find the longest common prefix shared by all strings in the array.

Key Idea:
Use the first string as the initial prefix and iteratively trim it by comparing
with each subsequent string.

Algorithm:
1. Initialize a StringBuilder with the first string as the candidate prefix.
2. For each remaining string:
   - Compare characters from the beginning with the current prefix.
   - Stop when characters differ or either string ends.
   - Delete the unmatched suffix from the prefix.
3. If the prefix becomes empty, stop early.
4. Return the remaining prefix.

Why It Works:
- The longest common prefix can only decrease as more strings are compared.
- Greedy shrinking ensures correctness and simplicity.

Time Complexity: O(n × m)
- n = number of strings
- m = length of the shortest string

Space Complexity: O(1) extra space
*/

package Arrays.Easy;

import java.util.Arrays;

public class _14_Longest_Common_Prefix {
    // Method to find the longest common prefix
    public static String longestCommonPrefix(String[] strs) {
        // Convert the first word to the string builder
        StringBuilder sb = new StringBuilder(strs[0]);

        // Iterate over the strs array to get the common prefix
        for (int i = 1; i < strs.length; i++) {
            // Get the length of the string builder
            int length = sb.length();
            // If stringbuilder has zero then break the loop
            if (length == 0) {
                break;
            }

            // Initialize the index variable for the string builder
            int index = 0;

            // Convert the strs[i] to the character array
            char[] str = strs[i].toCharArray();

            // Iterate over the str array
            for (int j = 0; j < str.length && index < length; j++, index++) {
                // If indices miss match then break out of the loop
                if (sb.charAt(index) != str[j]) {
                    break;
                }
            }

            // Update the string builder
            sb.delete(index, length);
        }

        // Retrun the stringbuilder string
        return sb.toString();
    }

    // Main method to test longestCommonPrefix
    public static void main(String[] args) {
        String[] strs = new String[] { "flower", "flow", "flight" };

        String result = longestCommonPrefix(strs);

        System.out.println("The longest common prefix in the array " + Arrays.toString(strs) + " is : " + result);
    }
}
