/*
LeetCode Problem: https://leetcode.com/problems/reorganize-string/

Question: 767. Reorganize String

Problem Statement: Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""

Constraints:
    1 <= s.length <= 500
    s consists of lowercase English letters.
*/

/*
Approach: Greedy Placement with Frequency Array and Max-Character Selection
Goal:
- Rearrange string s so no two adjacent characters
  are the same, returning any valid arrangement or
  "" if impossible.
Core Idea:
- A valid rearrangement exists if and only if no
  character appears more than ceil(n / 2) times.
- Greedily place the most frequent remaining
  character at each position; if it matches the
  previous character, temporarily suppress it and
  place the second most frequent character instead,
  then restore the suppressed frequency.
- This ensures the highest-frequency character is
  always consumed as fast as possible, preventing
  it from exceeding the adjacency constraint.
Algorithm Steps:
1. Build frequencyMap[26] from s.
2. If any character's frequency exceeds
   ceil(n / 2), return "".
3. Place the most frequent character at str[0],
   decrement its count.
4. For each position i from 1 to n - 1:
   a. Find maxIndex = character with highest
      frequency.
   b. If str[i - 1] != char at maxIndex:
      - Place char at maxIndex, decrement its
        count, continue.
   c. Else (maxIndex char matches previous):
      - Temporarily set frequencyMap[maxIndex] = -1
        to suppress it.
      - Find nextMaxIndex = next highest frequency
        character.
      - Place char at nextMaxIndex, decrement its
        count.
      - Restore frequencyMap[maxIndex] to its
        original value.
5. Return new String(str).
Why It Works:
- Placing the most frequent character greedily
  minimizes the risk of it accumulating at the end
  where adjacency violations become unavoidable.
- Temporary suppression cleanly handles the
  collision case without mutating state permanently,
  ensuring the correct max is found for the second
  choice.
- The feasibility check guarantees that whenever a
  collision occurs, a valid second character always
  exists (since no character exceeds ceil(n/2)).
Time Complexity:
- O(n * 26) = O(n)
since findMaxCharacter scans 26 entries per
position and is called at most twice per position.
Space Complexity:
- O(n)
for the char array, plus O(26) = O(1) for the
frequency map.
Result:
- Returns a valid rearrangement of s with no two
  adjacent characters equal, or "" if impossible.
*/

package StacksAndQueues.Medium;

// Solution Class
class Solution {
  // Method to find any possible rearrangement of s
  public String reorganizeString(String s) {
    // Initialize the array for the frequencyMap count
    int[] frequencyMap = new int[26];

    // Convert the s to character string
    char[] str = s.toCharArray();

    // Fill the frequencyMap array
    for (int i = 0; i < str.length; i++) {
      frequencyMap[str[i] - 'a']++;
    }

    // Initialize the maxFrequency
    int maxFrequency = ((str.length + 1) >> 1);

    // Check if we can make the valid string or not
    for (int i = 0; i < 26; i++) {
      if (maxFrequency < frequencyMap[i]) {
        return "";
      }
    }

    // Find the max index
    int maxIndex = this.findMaxChracter(frequencyMap);

    // Set the first index of the str array
    str[0] = (char) (maxIndex + 'a');

    // Decrement the frequencyMap index
    frequencyMap[maxIndex]--;

    // Iterate over the str array
    for (int i = 1; i < str.length; i++) {
      // Find the max index
      maxIndex = this.findMaxChracter(frequencyMap);

      // Check if it is not equal to the last character
      if (str[i - 1] != (char) (maxIndex + 'a')) {
        // Update the str array
        str[i] = (char) (maxIndex + 'a');

        // Decrement the frequencyMap index
        frequencyMap[maxIndex]--;

        // Skip the iteration
        continue;
      }

      // Get the frequency
      int frequency = frequencyMap[maxIndex];

      // Set the new frequency
      frequencyMap[maxIndex] = -1;

      // Find the next max index
      int nextMaxIndex = this.findMaxChracter(frequencyMap);

      // Update the str array
      str[i] = (char) (nextMaxIndex + 'a');

      frequencyMap[maxIndex] = frequency;

      // Decrement the frequencyMap index
      frequencyMap[nextMaxIndex]--;
    }

    // Return the str array
    return new String(str);
  }

  // Helper method to find the first and second most frequencyMap character
  private int findMaxChracter(int[] frequencyMap) {
    // Initialize the maxIndex
    int maxIndex = 0;

    // Iterate over the frequencyMap array to find the maxIndex
    for (int i = 0; i < 26; i++) {
      if (frequencyMap[maxIndex] < frequencyMap[i]) {
        maxIndex = i;
      }
    }

    // Return the maxIndex
    return maxIndex;
  }
}

// Main Class
public class _767_Reorganize_String {
  // Main method to test reorganizeString
  public static void main(String[] args) {
    String s = "aaab";

    String result = new Solution().reorganizeString(s);

    System.out.println("Any possible rearrangement of s is : " + result);
  }
}
