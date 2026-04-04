/*
LeetCode Problem: https://leetcode.com/problems/uncommon-words-from-two-sentences/

Question: 884. Uncommon Words from Two Sentences

Problem Statement: A sentence is a string of single-space separated words where each word consists only of lowercase letters.

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.

Example 1:
Input: s1 = "this apple is sweet", s2 = "this apple is sour"
Output: ["sweet","sour"]
Explanation:
The word "sweet" appears only in s1, while the word "sour" appears only in s2.

Example 2:
Input: s1 = "apple apple", s2 = "banana"
Output: ["banana"]

Constraints:
1 <= s1.length, s2.length <= 200
s1 and s2 consist of lowercase English letters and spaces.
s1 and s2 do not have leading or trailing spaces.
All the words in s1 and s2 are separated by a single space.
 */

/*
Approach: HashMap Frequency Counting

Goal:
- Find all words that appear exactly once across both sentences.

Core Idea:
- Count frequency of each word from both sentences combined.
- Words with frequency == 1 are uncommon.

Algorithm Steps:
1. Initialize a HashMap<String, Integer>.
2. Split s1 into words and update frequency map.
3. Split s2 into words and update frequency map.
4. Filter entries with frequency == 1.
5. Collect keys into a result array.

Time Complexity:
- O(n + m)
  n = number of words in s1
  m = number of words in s2

Space Complexity:
- O(u)
  u = number of unique words

Result:
- Returns an array of words that appear exactly once
  across both sentences.
*/

package Arrays.Easy;

import java.util.Arrays;
import java.util.HashMap;

// Solution Class
class Solution {
    // Method to find a list of all the uncommon words
    public String[] uncommonFromSentences(String s1, String s2) {
        // Initialize the hash map for the frequency
        HashMap<String, Integer> freqMap = new HashMap<>();

        // Fill the hash map with the frequency s1
        for (String s : s1.split(" ")) {
            freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
        }

        // Fill the hash map with the frequency s2
        for (String s : s2.split(" ")) {
            freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
        }

        // Return the string of unique words
        return freqMap.entrySet().stream().filter(e -> e.getValue() == 1).map(HashMap.Entry::getKey)
                .toArray(String[]::new);
    }
}

public class _884_Uncommon_Words_from_Two_Sentences {
    // Main method to test uncommonFromSentences
    public static void main(String[] args) {
        String s1 = "this apple is sweet";
        String s2 = "this apple is sour";

        String[] result = new Solution().uncommonFromSentences(s1, s2);

        System.out.println("A list of all the uncommon words is : " + Arrays.toString(result));
    }
}
