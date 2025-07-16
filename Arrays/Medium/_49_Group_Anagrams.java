/*
LeetCode Problem: https://leetcode.com/problems/group-anagrams/

Question: 49. Group Anagrams

Problem Statement: Given an array of strings strs, group the anagrams together. You can return the answer in any order.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:
There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:

1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

/*
Approach:
- For each string, convert it into a character array and sort it.
- The sorted version of the string will be the key for grouping anagrams because all anagrams share the same sorted string.
- Use a HashMap where the key is the sorted string, and the value is a list of original strings that match this key.
- Iterate over the input array, build the key for each string, and add the string to the appropriate list in the map.
- Finally, return the collection of lists from the map.

Time Complexity: O(N * K log K) — N is number of strings, K is max string length (sorting each string).
Space Complexity: O(N * K) — to store all strings grouped by their sorted keys.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _49_Group_Anagrams {
    // Method to find grouped anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {
        // Initialize a map to store grouped anagrams
        Map<String, List<String>> map = new HashMap<>();

        // Iterate over the input array
        for (String str : strs) {
            // Convert the string into a character array
            char[] chars = str.toCharArray();
            Arrays.sort(chars); // Sort the string characters to form the key

            String key = new String(chars); // Convert sorted char array back to string as the key

            // If key doesn't exist, create a new list, then add the original string to the
            // list
            map.computeIfAbsent(key, _ -> new ArrayList<>()).add(str);
        }

        // Return all grouped anagrams as a list of lists
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] s = { "eat", "tea", "tan", "ate", "nat", "bat" };

        List<List<String>> result = groupAnagrams(s);

        System.out.println("The list of list of anagrams is : " + result);
    }
}
