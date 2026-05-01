/*
LeetCode Problem: https://leetcode.com/problems/number-of-zero-filled-subarrays/

Question: 2348. Number of Zero-Filled Subarrays

Problem Statement: Given an integer array nums, return the number of subarrays filled with 0.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,3,0,0,2,0,0,4]
Output: 6
Explanation: 
There are 4 occurrences of [0] as a subarray.
There are 2 occurrences of [0,0] as a subarray.
There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.

Example 2:
Input: nums = [0,0,0,2,0,0]
Output: 9
Explanation:
There are 5 occurrences of [0] as a subarray.
There are 3 occurrences of [0,0] as a subarray.
There is 1 occurrence of [0,0,0] as a subarray.
There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.

Example 3:
Input: nums = [2,10,2019]
Output: 0
Explanation: There is no subarray filled with 0. Therefore, we return 0.

Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/

/*
Approach: Sliding Window / Running Count

Goal:
- Count the number of subarrays consisting only of zeros.

Core Idea:
- For each contiguous block of zeros of length L:
    number of subarrays = L * (L + 1) / 2
- Instead of computing per block, accumulate dynamically:
   - Maintain count of consecutive zeros.
   - Add count to result at each step.

Algorithm Steps:
1. Initialize:
   - count = 0 (current zero streak)
   - result = 0
2. Traverse array:
   - If nums[i] == 0:
       count++
   - Else:
       count = 0
   - Add count to result.
3. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns total number of zero-filled subarrays.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the number of subarrays filled with 0
  public long zeroFilledSubarray(int[] nums) {
    // Initialize the result and count variable
    long result = 0, count = 0;

    // Iterate over the nums array to find the window of the zeros subarray
    for (int i = 0; i < nums.length; i++) {
      // Update the count variable
      if (nums[i] == 0) {
        count++;
      } else {
        count = 0;
      }

      // Update the result varible
      result += count;
    }

    // Return the result
    return result;
  }
}

public class _2348_Number_of_Zero_Filled_Subarrays {
  // Main method to test zeroFilledSubarray
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 3, 0, 0, 2, 0, 0, 4 };

    long result = new Solution().zeroFilledSubarray(nums);

    System.out.println("The number of subarrays filled with 0 is : " + result);
  }
}
