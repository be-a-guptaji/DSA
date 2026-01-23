/*
LeetCode Problem: https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/

Question: 1897. Redistribute Characters to Make All Strings Equal

Problem Statement: You are given an array of strings words (0-indexed).

In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any character from words[i] to any position in words[j].

Return true if you can make every string in words equal using any number of operations, and false otherwise.

Example 1:
Input: words = ["abc","aabc","bc"]
Output: true
Explanation: Move the first 'a' in words[1] to the front of words[2],
to make words[1] = "abc" and words[2] = "abc".
All the strings are now equal to "abc", so return true.

Example 2:
Input: words = ["ab","a"]
Output: false
Explanation: It is impossible to make all the strings equal using the operation.

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
*/

/*
Approach: Frequency Divisibility Check

Goal:
Determine whether characters from all strings can be redistributed
so that every string becomes identical.

Key Idea:
For redistribution to work, the total frequency of each character
must be divisible by the number of strings.

Algorithm:
1. Create a frequency array of size 26 to count all characters
   across all words.
2. Traverse each word and update character frequencies.
3. Let k = number of words.
4. For each character frequency:
   - If frequency > 0 and frequency % k ≠ 0, return false.
5. If all characters satisfy the condition, return true.

Why It Works:
- Each character must be evenly split among all strings.
- Divisibility ensures equal distribution is possible.

Time Complexity: O(n × m)
- n = number of words
- m = average word length

Space Complexity: O(1)
*/

package Arrays.Easy;

public class _1897_Redistribute_Characters_to_Make_All_Strings_Equal {
    // Method to determine if the characters can be distributed to make all strings
    // equal or not
    public static boolean makeEqual(String[] words) {
        // Initialize the frequency array to hold the character frequency
        int[] frequency = new int[26];

        // Iterate over the words array
        for (String word : words) {
            // Fill the frequency array
            for (char ch : word.toCharArray()) {
                frequency[ch - 'a']++;
            }
        }

        // Initialize the length of the words array
        int minimumFrequency = words.length;

        // Iterate over the frequency array if it is not in multiple of minimumFrequency
        // then return false
        for (int freq : frequency) {
            // If freq is not zero then check the validation
            if (freq != 0 && freq % minimumFrequency != 0) {
                return false;
            }
        }

        // Retrun true in the end
        return true;
    }

    // Main method to test makeEqual
    public static void main(String[] args) {
        String[] words = new String[] { "abc", "aabc", "bc" };

        boolean result = makeEqual(words);

        System.out
                .println("The characters can" + (result ? " " : " not ") + "be distributed to make all strings equal.");
    }
}
