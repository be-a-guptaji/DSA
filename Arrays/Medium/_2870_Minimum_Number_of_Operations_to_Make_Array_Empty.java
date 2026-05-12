/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-operations-to-make-array-empty/

Question: 2870. Minimum Number of Operations to Make Array Empty

Problem Statement: You are given a 0-indexed array nums consisting of positive integers.

There are two types of operations that you can apply on the array any number of times:

Choose two elements with equal values and delete them from the array.
Choose three elements with equal values and delete them from the array.
Return the minimum number of operations required to make the array empty, or -1 if it is not possible.

Example 1:
Input: nums = [2,3,3,2,2,4,2,3,4]
Output: 4
Explanation: We can apply the following operations to make the array empty:
- Apply the first operation on the elements at indices 0 and 3. The resulting array is nums = [3,3,2,4,2,3,4].
- Apply the first operation on the elements at indices 2 and 4. The resulting array is nums = [3,3,4,3,4].
- Apply the second operation on the elements at indices 0, 1, and 3. The resulting array is nums = [4,4].
- Apply the first operation on the elements at indices 0 and 1. The resulting array is nums = [].
It can be shown that we cannot make the array empty in less than 4 operations.

Example 2:
Input: nums = [2,1,2,2,3,3]
Output: -1
Explanation: It is impossible to empty the array.

Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] <= 10^6

Note: This question is the same as 2244: Minimum Rounds to Complete All Tasks.
*/

/*
Approach: Frequency Grouping + Greedy Batch Removal

Goal:
- Find the minimum operations needed to remove all elements,
  where each operation removes either:
    - 2 equal elements
    - 3 equal elements

Core Idea:
- Process each distinct number independently.
- For a frequency count:
   - If count == 1 → impossible.
   - Prefer removing groups of 3 since they minimize operations.
   - Remaining 1 or 2 elements require one extra operation.

Algorithm Steps:
1. Sort the array.
2. Group identical elements using two pointers.
3. For each frequency count:
   - If count == 1 → return -1.
   - Else:
       operations += ceil(count / 3)
       equivalent to:
         count / 3 + (count % 3 != 0 ? 1 : 0)
4. Return total operations.

Time Complexity:
- O(n log n)
  due to sorting

Space Complexity:
- O(1)
  excluding sorting space

Result:
- Returns minimum operations required to empty the array,
  or -1 if impossible.
*/

package Arrays.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the minimum number of operations required to make the array
  // empty
  public int minOperations(int[] nums) {
    // Sort the array
    Arrays.sort(nums);

    // Initialize the left and right pointer
    int left = 0, right = 0;

    // Initialize the minOperations varaible
    int minOperations = 0;

    // Iterate over the nums array
    while (right < nums.length) {
      // Initialize the count variable
      int count = 0;

      // Find the window of the same value
      while (right < nums.length && nums[left] == nums[right]) {
        count++;
        right++;
      }

      // Update the left value
      left = right;

      // If count is one then return -1
      if (count == 1) {
        return -1;
      }

      // Update the minOperations
      if (count % 3 == 0) {
        minOperations += count / 3;
      } else {
        minOperations += count / 3 + 1;
      }
    }

    // Return minOperations
    return minOperations;
  }
}

public class _2870_Minimum_Number_of_Operations_to_Make_Array_Empty {
  // Main method to test minOperations
  public static void main(String[] args) {
    int[] nums = new int[] { 2, 3, 3, 2, 2, 4, 2, 3, 4 };

    int result = new Solution().minOperations(nums);

    System.out.println("The minimum number of operations required to make the array empty is : " + result);
  }
}
