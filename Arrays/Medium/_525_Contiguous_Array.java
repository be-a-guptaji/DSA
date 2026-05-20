/*
LeetCode Problem: https://leetcode.com/problems/contiguous-array/

Question: 525. Contiguous Array

Problem Statement: Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Example 3:
Input: nums = [0,1,1,1,1,1,0,0,0]
Output: 6
Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
*/

/*
Approach: Prefix Difference Tracking

Goal:
- Find the maximum length subarray containing
  equal numbers of 0 and 1.

Core Idea:
- Treat:
    1 → +1
    0 → -1
- If the same cumulative count appears again,
  the subarray between those indices has equal
  numbers of 0 and 1.
- Store the first occurrence of each count.

Algorithm Steps:
1. Initialize:
   - count = 0
   - result = 0
2. Use array to store first occurrence of each count:
   - Offset by length to handle negative indices.
3. Set:
   - differenceArray[length] = -1
4. Traverse nums:
   - If nums[i] == 1 → count++
   - Else → count--
5. If count seen before:
   - Update result using distance between indices.
6. Else:
   - Store first occurrence index.
7. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(n)

Result:
- Returns maximum length of contiguous subarray
  with equal number of 0s and 1s.
*/

package Arrays.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to find the maximum length of a contiguous subarray with an equal
    // number of 0 and 1
    public int findMaxLength(int[] nums) {
        // Get the length of the nums array
        int length = nums.length;

        // Initialize the result and count variable
        int result = 0, count = 0;

        // Initialize the array of 2 * length + 1 size
        int[] differenceArray = new int[2 * length + 1];

        // Fill the array with -2
        Arrays.fill(differenceArray, -2);

        // Set the differenceArray[length] to -1
        differenceArray[length] = -1;

        // Iterate over nums to find the maximum length of a contiguous subarray with an
        // equal number of 0 and 1
        for (int i = 0; i < length; i++) {
            // Update the count variable
            count += nums[i] == 1 ? 1 : -1;

            // Update the result and differenceArray
            if (differenceArray[count + length] != -2) {
                result = Math.max(result, i - differenceArray[count + length]);
            } else {
                differenceArray[count + length] = i;
            }
        }

        // Return the result variable
        return result;
    }
}

public class _525_Contiguous_Array {
    // Main method to test findMaxLength
    public static void main(String[] args) {
        int[] nums = new int[] { 0, 1, 1, 1, 1, 1, 0, 0, 0 };

        int result = new Solution().findMaxLength(nums);

        System.out
                .println("The maximum length of a contiguous subarray with an equal number of 0 and 1 is : " + result);
    }
}
