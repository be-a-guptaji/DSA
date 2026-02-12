/*
LeetCode Problem: https://leetcode.com/problems/sentence-similarity-iii/

Question: 1813. Sentence Similarity III

Problem Statement: You are given two strings sentence1 and sentence2, each representing a sentence composed of words. A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of only uppercase and lowercase English characters.

Two sentences s1 and s2 are considered similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. Note that the inserted sentence must be separated from existing words by spaces.

For example,

s1 = "Hello Jane" and s2 = "Hello my name is Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in s1.
s1 = "Frog cool" and s2 = "Frogs are cool" are not similar, since although there is a sentence "s are" inserted into s1, it is not separated from "Frog" by a space.
Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.

Example 1:
Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
Output: true
Explanation:
sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".

Example 2:
Input: sentence1 = "of", sentence2 = "A lot of words"
Output: false
Explanation:
No single sentence can be inserted inside one of the sentences to make it equal to the other.

Example 3:
Input: sentence1 = "Eating right now", sentence2 = "Eating"
Output: true
Explanation:
sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.

Constraints:

1 <= sentence1.length, sentence2.length <= 100
sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
The words in sentence1 and sentence2 are separated by a single space.
 */

/*
Approach: Prefix + Suffix Matching (Two Pointers)

Goal:
- Determine if one sentence can become the other by inserting
  an arbitrary (possibly empty) sentence in the middle.

Key Idea:
- The shorter sentence must match:
    1) A prefix of the longer sentence
    2) A suffix of the longer sentence
- Any unmatched portion in the longer sentence must lie entirely in the middle.

Steps Explained:

1. Split both sentences into word arrays.

2. Ensure words1 is the shorter sentence.
   - If not, swap arrays and lengths.
   - This simplifies logic.

3. Find Longest Common Prefix:
   - Move left pointer while words match from the beginning.
   - left1 counts matched prefix words.

4. Find Longest Common Suffix:
   - Start from the end of both arrays.
   - Move right pointers inward while words match.
   - Stop when overlap occurs.

5. Final Condition:
   - If all words in the shorter sentence are matched
     between prefix and suffix,
     then left1 > right1.

Why This Works:
- Valid transformation means:
  shorter = prefix + suffix
  longer  = prefix + middle + suffix
- No unmatched words should remain in the shorter sentence.

Time Complexity:
- O(n + m)  (n, m = number of words)

Space Complexity:
- O(n + m)  (due to split arrays)

Result:
- Returns true if transformation is possible.
*/

package TwoPointersAndSlidingWindow.Medium;

public class _1813_Sentence_Similarity_III {
    // Method to if two sentences sentence1 and sentence2 are similar or not
    public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        // Convert the string into the string array of words
        String[] words1 = sentence1.split(" "), words2 = sentence2.split(" ");

        // Get the length of the sentences
        int length1 = words1.length, length2 = words2.length;

        // Swap the smaller string to one
        if (length2 < length1) {
            // Swap string
            String[] temp = words2;
            words2 = words1;
            words1 = temp;

            // Swap length
            int t = length1;
            length1 = length2;
            length2 = t;
        }

        // Intialize two left varible for both the words array
        int left1 = 0;

        // Find the longest common prefix
        while (left1 < length1 && words1[left1].equals(words2[left1])) {
            // Increment both the index
            left1++;
        }

        // Intialize two right varible for both the words array
        int right1 = length1 - 1, right2 = length2 - 1;

        // Find the longest common suffix
        while (right1 >= left1 && right2 >= 0 && words1[right1].equals(words2[right2])) {
            // Decrement both the index
            right1--;
            right2--;
        }

        // Return the conditon
        return left1 > right1;
    }

    // Main method to test areSentencesSimilar
    public static void main(String[] args) {
        String sentence1 = "Eating right now";
        String sentence2 = "Eating";

        boolean result = areSentencesSimilar(sentence1, sentence2);

        System.out.println(
                "The two sentences " + sentence1 + " and " + sentence2 + " are" + (result ? " " : " not ")
                        + "similar.");
    }
}
