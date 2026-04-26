/*
LeetCode Problem: https://leetcode.com/problems/repeated-dna-sequences/

Question: 187. Repeated DNA Sequences

Problem Statement: The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

Example 1:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]

Example 2:
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]

Constraints:
1 <= s.length <= 10^5
s[i] is either 'A', 'C', 'G', or 'T'.
*/

/*
Approach: Sliding Window + HashMap (Fixed-Length Substring Counting)

Goal:
- Find all 10-letter-long DNA sequences that occur more than once.

Core Idea:
- Use a sliding window of size 10.
- Maintain a rolling substring using StringBuilder.
- Count occurrences of each substring using a HashMap.
- Collect substrings with frequency > 1.

Algorithm Steps:
1. Initialize a HashMap<String, Integer>.
2. Use a sliding window of size 10:
   - Append current character.
   - If size exceeds 10 → remove first character.
3. When window size == 10:
   - Convert to string and update frequency.
4. After traversal:
   - Collect all substrings with frequency > 1.
5. Return result list.

Time Complexity:
- O(n * 10) ≈ O(n)

Space Complexity:
- O(n)

Result:
- Returns all repeated 10-letter DNA sequences.

Note:
- Can be optimized to O(n) with O(1) space using
  bitmask (2-bit encoding per character).
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the repeated sequences in the string
  public List<String> findRepeatedDnaSequences(String s) {
    // Convert the string into the character array
    char[] str = s.toCharArray();

    // Initialize the hash map for the substrings
    HashMap<String, Integer> frequencyMap = new HashMap<>();

    // Initialize the string builder for the string formation
    StringBuilder sb = new StringBuilder();

    // Make the stings of the size of 10
    for (int i = 0; i < str.length; i++) {
      // Append the current character to the string builder
      sb.append(str[i]);

      // If string builder length is 11 then remove the first character
      if (sb.length() == 11) {
        sb.deleteCharAt(0);
      }

      // If string builder length is 10 then add it to the hash map
      if (sb.length() == 10) {
        // Get the key form the string builder
        String key = sb.toString();

        // Update the hash map
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
      }
    }

    // Initialize the Array list for result
    ArrayList<String> result = new ArrayList<>();

    // If hash map frequency is more then 1 then add it to the result
    for (HashMap.Entry<String, Integer> val : frequencyMap.entrySet()) {
      // If value of is more than 1 then add the key to the result
      if (val.getValue() > 1) {
        result.add(val.getKey());
      }
    }

    // Return the result
    return result;
  }
}

public class _187_Repeated_DNA_Sequences {
  // Main method to test findRepeatedDnaSequences
  public static void main(String[] args) {
    String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

    List<String> result = new Solution().findRepeatedDnaSequences(s);

    System.out.println("The repeated sequences in the string is : " + result);
  }
}
