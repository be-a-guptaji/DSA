/*
LeetCode Problem: https://leetcode.com/problems/circular-sentence/

Question: 2490. Circular Sentence

Problem Statement: A sentence is a list of words that are separated by a single space with no leading or trailing spaces.

For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
Words consist of only uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.

A sentence is circular if:

The last character of each word in the sentence is equal to the first character of its next word.
The last character of the last word is equal to the first character of the first word.
For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.

Given a string sentence, return true if it is circular. Otherwise, return false.

Example 1:
Input: sentence = "leetcode exercises sound delightful"
Output: true
Explanation: The words in sentence are ["leetcode", "exercises", "sound", "delightful"].
- leetcode's last character is equal to exercises's first character.
- exercises's last character is equal to sound's first character.
- sound's last character is equal to delightful's first character.
- delightful's last character is equal to leetcode's first character.
The sentence is circular.

Example 2:
Input: sentence = "eetcode"
Output: true
Explanation: The words in sentence are ["eetcode"].
- eetcode's last character is equal to eetcode's first character.
The sentence is circular.

Example 3:
Input: sentence = "Leetcode is cool"
Output: false
Explanation: The words in sentence are ["Leetcode", "is", "cool"].
- Leetcode's last character is not equal to is's first character.
The sentence is not circular.

Constraints:

1 <= sentence.length <= 500
sentence consist of only lowercase and uppercase English letters and spaces.
The words in sentence are separated by a single space.
There are no leading or trailing spaces.
 */

/*
Approach: Adjacent Word Boundary Validation

Goal:
Determine whether a sentence is circular.
A sentence is circular if:
- The last character of each word matches the first character of the next word.
- The last character of the last word matches the first character of the first word.

Key Idea:
Check character transitions at word boundaries and also compare
the first and last characters of the sentence.

Algorithm:
1. Convert the sentence to a character array.
2. If the first character is not equal to the last character, return false.
3. Traverse the character array:
   - When a space ' ' is encountered at index i:
       • Compare str[i − 1] (end of previous word)
         with str[i + 1] (start of next word).
       • If they differ, return false.
4. If all checks pass, return true.

Why It Works:
- Spaces uniquely identify word boundaries.
- Direct character comparison avoids splitting the string.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _2490_Circular_Sentence {
    // Method to find if the string is circular or not
    public static boolean isCircularSentence(String sentence) {
        // Convert the string into character array
        char[] str = sentence.toCharArray();

        // If ends are different then return false
        if (str[0] != str[str.length - 1]) {
            return false;
        }

        // Iterate over the str array
        for (int i = 0; i < str.length; i++) {
            // If we encounter space and last value and first value are different then
            // return false
            if (str[i] == ' ') {
                if (str[i - 1] != str[i + 1]) {
                    return false;
                }
            }
        }

        // Retrun true in the end
        return true;
    }

    // Main method to test isCircularSentence
    public static void main(String[] args) {
        String sentence = "leetcode exercises sound delightful";

        boolean result = isCircularSentence(sentence);

        System.out.println("The string is" + (result ? " " : " not ") + "a circular string.");
    }
}
