/*
LeetCode Problem: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/

Question: 28. Find the Index of the First Occurrence in a String

Problem Statement: Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.

Example 2:
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.

Constraints:
1 <= haystack.length, needle.length <= 10^4
haystack and needle consist of only lowercase English characters.
*/

/*
Approach: Brute Force (Sliding Window String Matching)

Goal:
- Find the first occurrence of the substring (needle)
  in the main string (haystack).

Core Idea:
- Slide a window of size needle.length across haystack.
- At each position, compare characters one by one.
- If all characters match, return the starting index.

Algorithm Steps:
1. If haystack length < needle length → return -1.
2. For each index i from 0 to (n - m):
   - Compare haystack[i + j] with needle[j].
   - Continue until mismatch or full match.
3. If full match found → return i.
4. If no match found → return -1.

Time Complexity:
- O(n * m)
  n = length of haystack, m = length of needle

Space Complexity:
- O(1)

Result:
- Returns the index of the first occurrence of needle,
  or -1 if not found.
*/

package Arrays.Easy;

// Solution Class
class Solution {
    // Method to find the index of the first occurrence of needle in haystack
    public int strStr(String haystack, String needle) {
        // Get the length of the haystack and needle
        int haystackLength = haystack.length(), needleLength = needle.length();

        // If haystack length is less than the needle then return -1
        if (haystackLength < needleLength) {
            return -1;
        }

        // Iterate over the haystack and needle to find out the index
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            // Get the current index
            int index = 0;

            // Match all the characters
            while (index < needleLength && haystack.charAt(i + index) == needle.charAt(index)) {
                // Increment the index
                index++;
            }

            // If k is equal to the needle length then return the index
            if (index == needleLength) {
                return i;
            }
        }

        // Return -1 in the end
        return -1;
    }
}

public class _28_Find_the_Index_of_the_First_Occurrence_in_a_String {
    // Main method to test strStr
    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";

        int result = new Solution().strStr(haystack, needle);

        System.out.println("The index of the first occurrence of needle in haystack is : " + result);
    }
}
