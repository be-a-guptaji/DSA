/*
LeetCode Problem: https://leetcode.com/problems/minimum-window-substring/

Question: 76. Minimum Window Substring

Problem Statement: Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:

m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

/*
Approach: This solution uses a greedy strategy combined with sorting. 
First, the input intervals are sorted based on their starting points. 
Then, we iterate through the sorted intervals and merge overlapping ones. 
Two intervals overlap if the start of the current interval is less than or equal 
to the end of the previous (last merged) interval. 
If they overlap, we update the end of the last interval with the maximum of both ends. 
Otherwise, we add the current interval to the result list as a new non-overlapping interval.

Time Complexity: O(n log n), where n is the number of intervals. 
We spend O(n log n) time to sort the intervals, and then O(n) time to iterate and merge.

Space Complexity: O(n), for the output list of merged intervals. 
No extra data structures are used beyond the result.
*/

package Arrays.Hard;

public class _76_Minimum_Window_Substring {
    // Method to find the minimum window of substring
    public static String minWindow(String s, String t) {

        // Return the minimum window of substring
        return s + t;
    }

    // Main method to test minWindow
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";

        String result = minWindow(s, t);

        System.out.println("The minimum window of substring is : " + result);
    }
}
