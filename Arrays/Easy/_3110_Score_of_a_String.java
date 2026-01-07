/*
LeetCode Problem: https://leetcode.com/problems/score-of-a-string/

Question: 3110. Score of a String

Problem Statement: You are given a string s. The score of a string is defined as the sum of the absolute difference between the ASCII values of adjacent characters.

Return the score of s.

Example 1:
Input: s = "hello"
Output: 13
Explanation:
The ASCII values of the characters in s are: 'h' = 104, 'e' = 101, 'l' = 108, 'o' = 111. So, the score of s would be |104 - 101| + |101 - 108| + |108 - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13.

Example 2:
Input: s = "zaz"
Output: 50
Explanation:
The ASCII values of the characters in s are: 'z' = 122, 'a' = 97. So, the score of s would be |122 - 97| + |97 - 122| = 25 + 25 = 50.

Constraints:

2 <= s.length <= 100
s consists only of lowercase English letters.
 */

/*
Approach: Single Pass Character Difference Accumulation

Goal:
Compute the score of a string as the sum of absolute differences between
the ASCII values of consecutive characters.

Algorithm:
1. Convert the string into a character array.
2. Initialize score = 0.
3. Iterate from index 0 to length − 2:
   - Add |str[i] − str[i + 1]| to score.
4. Return the final score.

Why It Works:
- Each adjacent character pair contributes independently to the total score.
- A single traversal is sufficient.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _3110_Score_of_a_String {
    // Method to find the score of a string
    public static int scoreOfString(String s) {
        // Convert the string into character array
        char[] str = s.toCharArray();

        // Initialize the length of index
        int index = str.length;

        // Initialize the score variable to accumulate the result
        int score = 0;

        // Iterate over the character array to find the absolute difference
        for (int i = 0; i < index - 1; i++) {
            score += Math.abs(str[i] - str[i + 1]);
        }

        // Retrun the score
        return score;
    }

    // Main method to test scoreOfString
    public static void main(String[] args) {
        String s = "hello";

        int result = scoreOfString(s);

        System.out.println("The score of a string " + s + " is : " + result);
    }
}
