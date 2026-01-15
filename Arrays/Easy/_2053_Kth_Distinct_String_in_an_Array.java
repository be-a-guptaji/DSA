/*
LeetCode Problem: https://leetcode.com/problems/kth-distinct-string-in-an-array/

Question: 2053. Kth Distinct String in an Array

Problem Statement: A distinct string is a string that is present only once in an array.

Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".

Note that the strings are considered in the order in which they appear in the array.

Example 1:
Input: arr = ["d","b","c","b","c","a"], k = 2
Output: "a"
Explanation:
The only distinct strings in arr are "d" and "a".
"d" appears 1st, so it is the 1st distinct string.
"a" appears 2nd, so it is the 2nd distinct string.
Since k == 2, "a" is returned. 

Example 2:
Input: arr = ["aaa","aa","a"], k = 1
Output: "aaa"
Explanation:
All strings in arr are distinct, so the 1st string "aaa" is returned.

Example 3:
Input: arr = ["a","b","a"], k = 3
Output: ""
Explanation:
The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".

Constraints:

1 <= k <= arr.length <= 1000
1 <= arr[i].length <= 5
arr[i] consists of lowercase English letters.
*/

/*
Approach: Frequency Tracking with Order Preservation

Goal:
Return the k-th distinct string in the array.
A string is distinct if it appears exactly once.

Key Idea:
- Track which strings appear more than once.
- Preserve the original order to find the k-th distinct string.

Algorithm:
1. Use two sets:
   - seen: tracks strings already encountered.
   - distinct: tracks strings that currently appear exactly once.
2. Traverse the array:
   - If a string is already in seen:
       • Remove it from distinct (it is no longer unique).
   - Else:
       • Add it to both seen and distinct.
3. Traverse the array again in original order:
   - For each string that exists in distinct:
       • Decrement k.
       • If k becomes 0, return the string.
4. If fewer than k distinct strings exist, return "".

Why It Works:
- Sets efficiently track uniqueness.
- Second traversal ensures correct ordering.

Time Complexity: O(n)  
Space Complexity: O(n)
*/

package Arrays.Easy;

import java.util.HashSet;

public class _2053_Kth_Distinct_String_in_an_Array {
    // Method to find kth distinct string in array
    public static String kthDistinct(String[] arr, int k) {
        // Initialize two hash set for the seen and distinct strings
        HashSet<String> seen = new HashSet<>(), distinct = new HashSet<>();

        // Add all the string to the seen and distinct set and if they are already in
        // the seen remove it from the distinct set
        for (String str : arr) {
            // If in seen then remove it from the distinct set else add to the seen and the
            // distinct set
            if (seen.contains(str)) {
                distinct.remove(str);
            } else {
                seen.add(str);
                distinct.add(str);
            }
        }

        // Iterate over the arr to find the kth distinct string
        for (String str : arr) {
            // If str is in distinct set then decrement the value of the k
            if (distinct.contains(str)) {
                k--; // Decrement the k
            }

            // If k is zero then return the current str
            if (k == 0) {
                return str;
            }
        }

        // Return the empty string in the end
        return "";
    }

    // Main method to test kthDistinct
    public static void main(String[] args) {
        String[] arr = new String[] { "d", "b", "c", "b", "c", "a" };
        int k = 2;

        String result = kthDistinct(arr, k);

        System.out.println("The " + k + "th distinct string in array is : " + result);
    }
}
