/*
LeetCode Problem: https://leetcode.com/problems/custom-sort-string/

Question: 791. Custom Sort String

Problem Statement: You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

Example 1:
Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:
Input: order = "bcafg", s = "abcd"
Output: "bcad"
Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.

Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other arrangements like "dbca" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.

Constraints:
1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
 */

/*
Approach: Frequency Counting + Custom Order Mapping

Goal:
- Rearrange string s such that characters follow the order defined in "order".
- Characters not present in "order" can appear in any order after.

Core Idea:
- Count frequency of each character in s.
- First, place characters following the given custom order.
- Then append remaining characters.

Algorithm Steps:
1. Initialize frequency array of size 26.
2. Count occurrences of each character in s.
3. Traverse "order":
   - For each character, append it frequency times.
   - Decrease frequency accordingly.
4. Traverse remaining characters (a → z):
   - Append any leftover characters.
5. Return the constructed string.

Time Complexity:
- O(n + 26)
  n = length of s

Space Complexity:
- O(1)

Result:
- Returns a permutation of s that respects the given custom order.
*/

package Arrays.Medium;

// Solution Class
class Solution {
    // Method to find any permutation of s that satisfies this property
    public String customSortString(String order, String s) {
        // Initialize the frequency array for the s
        int[] frequency = new int[26];

        // Fill the frequency array
        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
        }

        // Initialize the String builder for the result
        StringBuilder sb = new StringBuilder();

        // Build the string
        for (int i = 0; i < order.length(); i++) {
            // Get the character
            char ch = order.charAt(i);

            // Get the index of the character
            int index = ch - 'a';

            // Append the frequency of the character to the string builder
            while (frequency[index] != 0) {
                // Append the character
                sb.append(ch);

                // Decrement the frequency of the index
                frequency[index]--;
            }
        }

        // Fill the rest of the string
        for (int i = 0; i < 26; i++) {
            // Get the character
            char ch = (char) (i + 'a');

            while (frequency[i] != 0) {
                // Append the character
                sb.append(ch);

                // Decrement the frequency of the index
                frequency[i]--;
            }
        }

        // Return the sb by converting it into string
        return sb.toString();
    }
}

public class _791_Custom_Sort_String {
    // Main method to test customSortString
    public static void main(String[] args) {
        String order = "bcafg";
        String s = "abcd";

        String result = new Solution().customSortString(order, s);

        System.out.println("Any permutation of " + s + " that satisfies this property is : " + result);
    }
}
