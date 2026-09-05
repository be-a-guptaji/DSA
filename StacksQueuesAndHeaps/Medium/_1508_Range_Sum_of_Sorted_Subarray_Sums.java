/*
LeetCode Problem: https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/

Question: 1508. Range Sum of Sorted Subarray Sums

Problem Statement: You are given the array nums consisting of n positive integers. You computed the sum of all non-empty continuous subarrays from the array and then sorted them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 10^9 + 7.

Example 1:
Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
Output: 13 
Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13. 

Example 2:
Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
Output: 6
Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.

Example 3:
Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
Output: 50

Constraints:
    n == nums.length
    1 <= nums.length <= 1000
    1 <= nums[i] <= 100
    1 <= left <= right <= n * (n + 1) / 2
*/

/*
Approach: Enumerate All Subarray Sums, Sort, and Range Sum
Goal:
- Compute the sum of elements from index left to
  right (1-indexed) in the sorted array of all
  non-empty subarray sums, modulo 1e9 + 7.
Core Idea:
- Generate every non-empty subarray sum explicitly
  using a nested loop, accumulating prefix sums
  from each starting index.
- Sort the resulting array to produce the ordered
  sequence of subarray sums.
- Sum the elements in the [left, right] range of
  the sorted array.
Algorithm Steps:
1. Allocate subArraySum of size n*(n+1)/2 (total
   number of non-empty subarrays).
2. For each start index i from 0 to n-1:
   - Maintain a running currentSum.
   - For each end index j from i to n-1:
     - currentSum = (currentSum + nums[j]) % MOD.
     - Store currentSum in subArraySum[index++].
3. Sort subArraySum in ascending order.
4. Sum elements from index left-1 to right-1
   (0-indexed), applying MOD at each addition.
5. Return the final sum.
Why It Works:
- Every subarray [i, j] is covered exactly once by
  the nested loop (i <= j), so no subarray sum is
  missed or double-counted.
- Applying MOD during accumulation prevents
  integer overflow since individual subarray sums
  can be large.
- Sorting directly gives the required ordered
  sequence without needing a more complex
  data structure.
Time Complexity:
- O(n^2 log n)
where n^2 is the number of subarray sums generated
and log n^2 = 2 log n accounts for sorting.
Space Complexity:
- O(n^2)
for the subArraySum array storing all subarray
sums.
Result:
- Returns the sum of the left-th to right-th
  smallest subarray sums modulo 1e9 + 7.
*/

package StacksQueuesAndHeaps.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Initialize the MOD value
  private static final int MOD = 1_000_000_007;

  // Method to find the sum of the numbers from index left to index right
  public int rangeSum(int[] nums, int n, int left, int right) {
    // Initialize the array for the sub array sum
    int[] subArraySum = new int[(n * (n + 1)) >> 1];

    // Iterate over the nums array to get the total sub array
    for (int i = 0, index = 0; i < n; i++) {
      // Initialize the current sum variable
      int currentSum = 0;

      // Iterate over the j to get the sub array sum
      for (int j = i; j < n; j++) {
        // Update the value of the current sum
        currentSum = (currentSum + nums[j]) % MOD;

        // Add the value to the current sum to the sub array sum
        subArraySum[index++] = currentSum;
      }
    }

    // Sort the array
    Arrays.sort(subArraySum);

    // Initialize the sum variable
    int sum = 0;

    // Iterate over the subArraySum variable to get the sum
    for (int i = left - 1; i < right; i++) {
      // Update the sum
      sum = (sum + subArraySum[i]) % MOD;
    }

    // Return the sum
    return sum;
  }
}

// Main Class
public class _1508_Range_Sum_of_Sorted_Subarray_Sums {
  // Main method to test rangeSum
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3, 4 };
    int n = 4;
    int left = 1;
    int right = 5;

    int result = new Solution().rangeSum(nums, n, left, right);

    System.out.println("The sum of the numbers from index left to index right is : " + result);
  }
}
