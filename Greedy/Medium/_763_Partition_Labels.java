/*
LeetCode Problem: https://leetcode.com/problems/partition-labels/

Question: 763. Partition Labels

Problem Statement: You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

Example 1:
Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

Example 2:
Input: s = "eccbbbbdec"
Output: [10]

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
*/

/*
Approach: Greedy using Last Occurrence Tracking

Goal:
Partition the string into the maximum number of parts such that each character 
appears in at most one part.

Key Idea:
A partition ends when all characters seen so far complete their last occurrence.

Steps:

1. Precompute the last index of each character in the string.
   - Store in an array of size 26.

2. Traverse the string maintaining:
   - maxIndex = farthest last occurrence of characters seen so far.
   - lastIndex = end of previous partition.

3. For each index i:
   - Update maxIndex = max(maxIndex, lastOccurrence[str[i]]).
   - When i == maxIndex:
       • A valid partition ends at i.
       • Record partition size = i - lastIndex.
       • Update lastIndex = i.

4. Continue scanning until string ends.

Why It Works:
- A partition is valid only when every character inside it does not appear later.
- Tracking the maximum last occurrence guarantees correct partition boundaries.

Time Complexity: O(n)
Space Complexity: O(1) extra space (26 array)
*/

package Greedy.Medium;

import java.util.ArrayList;
import java.util.List;

public class _763_Partition_Labels {
    // Method to find the list of integers representing the size of the parts
    public static List<Integer> partitionLabels(String s) {
        // Initialize a array to hold the last index position of the character
        int[] characterIndexMap = new int[26];

        // Convert the string to the character array
        char[] str = s.toCharArray();

        // Initialize the variable to hold the character array
        int length = str.length;

        // Fill the hash map with the character last index position
        for (int i = 0; i < length; i++) {
            // Add the value to the hash map
            characterIndexMap[str[i] - 'a'] = i;
        }

        // Initialize the list of integer to hold the value of the indices
        ArrayList<Integer> indices = new ArrayList<>();

        // Initialize the index for iterating over the character array, initalize the
        // max index variable to get the far index and also initialize the lastIndex for
        // the keeping track where the last string is ended
        int index = 0, maxIndex = 0, lastIndex = -1;

        // Iterate over the character array
        while (index < length) {
            // Upadate the maxIndex for the current character
            maxIndex = Math.max(maxIndex, characterIndexMap[str[index] - 'a']);

            // If maxIndex is equal to the index then add the length of the string to the
            // indices and update the lastIndex
            if (maxIndex == index) {
                // Add the length to the indices
                indices.add(index - lastIndex);

                // Update the lastIndex
                lastIndex = index;
            }

            // Incremenet the index variable
            index++;
        }

        // Return the indices
        return indices;
    }

    // Main method to test partitionLabels
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";

        List<Integer> result = partitionLabels(s);

        System.out.println("The list of integers representing the size of the parts is : " + result);
    }
}
