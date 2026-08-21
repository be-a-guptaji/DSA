/*
LeetCode Problem: https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/

Question: 1985. Find the Kth Largest Integer in the Array

Problem Statement: You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.

Return the string that represents the kth largest integer in nums.

Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.

Example 1:
Input: nums = ["3","6","7","10"], k = 4
Output: "3"
Explanation:
The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
The 4th largest integer in nums is "3".

Example 2:
Input: nums = ["2","21","12","1"], k = 3
Output: "2"
Explanation:
The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
The 3rd largest integer in nums is "2".

Example 3:
Input: nums = ["0","0"], k = 2
Output: "0"
Explanation:
The numbers in nums sorted in non-decreasing order are ["0","0"].
The 2nd largest integer in nums is "0".

Constraints:
    1 <= k <= nums.length <= 10^4
    1 <= nums[i].length <= 100
    nums[i] consists of only digits.
    nums[i] will not have any leading zeros.
*/

/*
Approach: Custom Sort by Numeric Value via Length then Lexicographic Order
Goal:
- Find the kth largest integer among strings
  representing non-negative integers without
  leading zeros, returning the result as a string.
Core Idea:
- A longer string always represents a larger
  integer (no leading zeros guaranteed).
- Two strings of equal length compare numerically
  in the same order as lexicographically, so
  standard string comparison suffices for equal
  length strings.
- Sorting descending by length first, then
  lexicographically descending for equal lengths,
  produces a fully numerically sorted order.
Algorithm Steps:
1. Sort nums with a custom comparator:
   - If lengths differ, sort by length descending.
   - If lengths are equal, sort lexicographically
     descending (b.compareTo(a)).
2. Return nums[k - 1].
Time Complexity:
- O(n log n * m)
where n is the number of strings and m is the
average string length, since each comparator
invocation costs O(m) for lexicographic comparison.
Space Complexity:
- O(log n)
for the sort's internal call stack.
Result:
- Returns the string representing the kth largest
  integer in nums.
*/

package StacksAndQueues.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the string that represents the kth largest integer in nums
  public String kthLargestNumber(String[] nums, int k) {
    // Sort the array
    Arrays.sort(nums, (a, b) -> a.length() == b.length() ? b.compareTo(a) : b.length() - a.length());

    // Return k - 1th element
    return nums[k - 1];
  }
}

// Main Class
public class _1985_Find_the_Kth_Largest_Integer_in_the_Array {
  // Main method to test kthLargestNumber
  public static void main(String[] args) {
    String[] nums = new String[] { "3", "6", "7", "10" };
    int k = 2;

    String result = new Solution().kthLargestNumber(nums, k);

    System.out.println("The string that represents the kth largest integer in nums is : " + result);
  }
}
