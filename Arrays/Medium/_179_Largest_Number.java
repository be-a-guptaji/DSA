/*
LeetCode Problem: https://leetcode.com/problems/largest-number/

Question: 179. Largest Number

Problem Statement: Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

Example 1:
Input: nums = [10,2]
Output: "210"

Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 10^9
*/

/*
Approach: Custom Sorting (Greedy String Concatenation)

Goal:
- Arrange numbers to form the largest possible number.

Core Idea:
- Convert numbers to strings.
- Sort based on concatenation order:
    For two strings a and b:
      if (b + a) > (a + b) → b should come before a.
- This ensures globally optimal ordering.

Algorithm Steps:
1. Convert integers to string array.
2. Sort using custom comparator:
   - Compare (b + a) with (a + b).
3. Concatenate all strings.
4. Handle edge case:
   - If first character is '0', return "0".

Time Complexity:
- O(n log n * k)
  n = number of elements, k = average string length

Space Complexity:
- O(n)

Result:
- Returns the largest possible number as a string.
*/

package Arrays.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to form the largest number
  public String largestNumber(int[] nums) {
    // Convert the numbers into strings array
    String[] arr = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);

    // Sort them by comparing them
    Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

    // Join the array to make the number
    String res = String.join("", arr);

    // If res is zero then return 0 else return the res
    return res.charAt(0) == '0' ? "0" : res;
  }
}

public class _179_Largest_Number {
  // Main method to test largestNumber
  public static void main(String[] args) {
    int[] nums = { 3, 30, 34, 5, 9 };

    String result = new Solution().largestNumber(nums);

    System.out
        .println("The largest number form is : " + result);
  }
}
