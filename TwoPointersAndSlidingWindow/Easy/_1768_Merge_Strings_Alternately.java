/*
LeetCode Problem: https://leetcode.com/problems/merge-strings-alternately/

Question: 1768. Merge Strings Alternately

Problem Statement: You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

Example 1:
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r

Example 2:
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s

Example 3:
Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d

Constraints:

1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
 */

/*
Approach: Two Pointers with Alternating Merge

Goal:
Merge two strings by taking characters alternately from each.
If one string runs out, append the remaining characters of the other.

Steps:
1. Use two pointers:
   - i for word1
   - j for word2
2. Loop while at least one pointer is still within bounds.
3. Append word1[i] if i < word1.length().
4. Append word2[j] if j < word2.length().
5. Continue until both strings are fully consumed.

Why it works:
- Ensures strict alternation when both strings have characters.
- Gracefully handles unequal string lengths.

Time Complexity: O(n + m)  
Space Complexity: O(n + m)  (for the output string)

This is the optimal and clean solution.
*/

package TwoPointersAndSlidingWindow.Easy;

public class _1768_Merge_Strings_Alternately {
    // Method to find the merged string
    public static String mergeAlternately(String word1, String word2) {
        // Initialize the string builder for making the string
        StringBuilder sb = new StringBuilder();

        // Initialize two pointer for each string
        int i = 0, j = 0;

        // Itrate over the words untill both string extinguish
        while (i < word1.length() || j < word2.length()) {
            // If i is less then word1 length then add the word to the string builder
            if (i < word1.length()) {
                sb.append(word1.charAt(i++));
            }
            // If j is less then word2 length then add the word to the string builder
            if (j < word2.length()) {
                sb.append(word2.charAt(j++));
            }
        }

        // Return the string builder string
        return sb.toString();
    }

    // Main method to test mergeAlternately
    public static void main(String[] args) {
        String word1 = "abc", word2 = "xyz";

        String result = mergeAlternately(word1, word2);

        System.out.println("The merged string is : " + result);
    }
}
