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
Approach: Single Pass with Last Zero Tracking

Goal:
Find the maximum number of consecutive 1’s in a binary array.

Key Idea:
Track the index of the most recent zero and compute the length of the current
streak of 1’s using index differences.

Algorithm:
1. Initialize:
   - startingIndex = -1 (acts as the index before the array start)
   - maxConsecutiveOnes = 0
2. Traverse the array:
   - If nums[i] == 0:
       startingIndex = i   (reset streak start)
   - Else:
       currentStreak = i - startingIndex
       maxConsecutiveOnes = max(maxConsecutiveOnes, currentStreak)
3. Return maxConsecutiveOnes.

Why It Works:
- The distance from the last zero gives the current run of 1’s.
- A single pass is sufficient.

Time Complexity: O(n)  
Space Complexity: O(1)
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
