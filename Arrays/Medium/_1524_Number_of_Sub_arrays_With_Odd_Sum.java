/*
LeetCode Problem: https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/

Question: 1524. Number of Sub-arrays With Odd Sum

Problem Statement: Given an array of integers arr, return the number of subarrays with an odd sum.

Since the answer can be very large, return it modulo 109 + 7.

Example 1:
Input: arr = [1,3,5]
Output: 4
Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.

Example 2:
Input: arr = [2,4,6]
Output: 0
Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have even sum and the answer is 0.

Example 3:
Input: arr = [1,2,3,4,5,6,7]
Output: 16

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 100
*/

/*
Approach: Prefix Sum Parity Counting

Goal:
- Count the number of subarrays whose sum is odd.

Core Idea:
- A subarray sum is odd if:
    even prefix + odd prefix
    or
    odd prefix + even prefix
- Track how many even and odd prefix sums have appeared.
- For current prefix parity:
   - If current prefix is even, pair with previous odd prefixes.
   - If current prefix is odd, pair with previous even prefixes.

Algorithm Steps:
1. Initialize:
   - count[0] = 1 (empty prefix is even)
   - count[1] = 0
2. Traverse array:
   - Update prefix parity:
       prefix = (prefix + arr[i]) % 2
   - Add count[1 - prefix] to result.
   - Increment count[prefix].
3. Return result modulo 1e9+7.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the number of subarrays with odd sum.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the number of subarrays with an odd sum
  public int numOfSubarrays(int[] arr) {
    // Initialize the mod
    final int MOD = (int) 1e9 + 7;

    // Initialize the count for even odd
    int[] count = new int[] { 1, 0 };

    // Initialize the prefix and the result variable
    int prefix = 0, result = 0;

    // Find the sub array of odd sum
    for (int i = 0; i < arr.length; i++) {
      prefix = (prefix + arr[i]) % 2;
      result = (result + count[1 - prefix]) % MOD;
      count[prefix]++;
    }

    // Return the result
    return result;
  }
}

public class _1524_Number_of_Sub_arrays_With_Odd_Sum {
  // Main method to test numOfSubarrays
  public static void main(String[] args) {
    int[] arr = new int[] { 1, 3, 5 };

    int result = new Solution().numOfSubarrays(arr);

    System.out.println("The number of subarrays with an odd sum is : " + result);
  }
}
