/*
LeetCode Problem: https://leetcode.com/problems/find-all-anagrams-in-a-string/

Question: 438. Find All Anagrams in a String

Problem Statement: Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:

1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.
*/

/*
Approach: This problem can be solved efficiently using **binary search** since the array is sorted.

   - We initialize two pointers: left = 0, right = nums.length - 1
   - We perform binary search to find the target:
   - If nums[mid] == target: return mid
   - If nums[mid] < target: search the right half
   - If nums[mid] > target: search the left half
   - If the target is not found, left will represent the index where the target should be inserted.

   This works because binary search helps us narrow down the position quickly in O(log n) time.
    
Time Complexity: O(log n) — because binary search divides the array in half each iteration
Space Complexity: O(1) — no additional space is used
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _438_Find_All_Anagrams_in_a_String {
    // Method to find the index of the anagrams
    public static List<Integer> findAnagrams(String s, String p) {
        // Make the list for the result
        List<Integer> result = new ArrayList<>();

        // Initialize the lenght of both the string
        int slen = s.length(), plen = p.length();

        // If the length of the s is less than the p the return the empty list
        if (slen < plen)
            return result;

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Initialize the array with the first plen characters
        for (int i = 0; i < plen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // If the arrays are equal than add the first index to the list
        if (Arrays.equals(pCount, sCount)) {
            result.add(0);
        }
        
        // Logic to find the anagarams index
        for (int i = plen; i < slen; i++) {
            sCount[s.charAt(i) - 'a']++; // Add new char
            sCount[s.charAt(i - plen) - 'a']--; // Remove old char

            // If the arrays are equal than add the current adjusted index to the list
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - plen + 1);
            }
        }

        // Return the result
        return result;
    }

    // Main method to test findAnagrams
    public static void main(String[] args) {
        String s = "abab", p = "ab";

        List<Integer> result = findAnagrams(s, p);

        System.out.println("The index of the anagrams are : " + result);
    }
}
