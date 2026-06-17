/*
LeetCode Problem: https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/

Question: 2009. Minimum Number of Operations to Make Array Continuous

Problem Statement: You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

nums is considered continuous if both of the following conditions are fulfilled:

  All elements in nums are unique.
  The difference between the maximum element and the minimum element in nums equals nums.length - 1.

For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

Return the minimum number of operations to make nums continuous.

Example 1:
Input: nums = [4,2,5,3]
Output: 0
Explanation: nums is already continuous.

Example 2:
Input: nums = [1,2,3,5,6]
Output: 1
Explanation: One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.

Example 3:
Input: nums = [1,10,100,1000]
Output: 3
Explanation: One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/

/*
Approach: Sorting Unique Values + Sliding Window

Goal:
- Find the minimum number of operations required
  to make the array continuous.

Continuous Array Properties:
1. All elements are unique.
2. max(nums) - min(nums) = n - 1

where n = nums.length.

Core Idea:
- Duplicate values cannot all remain in the final
  continuous array, so remove duplicates first.
- Let n be the original array length.
- For any chosen starting value x, a valid
  continuous range is:

      [x, x + n - 1]

- We want to keep as many distinct numbers already
  lying inside such a range as possible.
- Every remaining element must be modified.

Therefore:

      operations =
      n - (maximum number of unique values
           already inside a valid range)

Algorithm Steps:
1. Insert all numbers into a TreeSet to:
   - remove duplicates
   - obtain sorted unique values
2. Convert the set into a sorted array/list.
3. Use a sliding window:
   - left = start of range
   - right = first value outside:

         sorted[left] + n

4. For each left:
   - Expand right while:

         sorted[right] < sorted[left] + n

   - Window size:

         right - left

     equals the number of unique values already
     fitting inside a valid continuous interval.
5. Update:

      result = min(result,
                   n - windowSize)

6. Return result.

Why It Works:
- Any continuous array of length n occupies exactly
  n consecutive integer values.
- After removing duplicates, the largest group of
  unique numbers already fitting into one such
  interval can remain unchanged.
- Every other element must be modified.

Time Complexity:
- O(n log n)
  - TreeSet insertion: O(n log n)
  - Sliding window: O(n)

Space Complexity:
- O(n)

Result:
- Returns the minimum number of operations needed
  to make the array continuous.
*/

package TwoPointersAndSlidingWindow.Hard;

import java.util.ArrayList;
import java.util.TreeSet;

// Solution Class
class Solution {
  // Method to find the minimum number of operations to make nums continuous
  public int minOperations(int[] nums) {
    // Initialize the length variable
    final int length = nums.length;

    // Initialize the tree set for the nums array
    TreeSet<Integer> set = new TreeSet<>();

    // Iterate over the nums array and add the value to the set
    for (int i = 0; i < length; i++) {
      set.add(nums[i]);
    }

    // Initialize the result variable
    int result = length;

    // Initialize the array list
    ArrayList<Integer> sortedNums = new ArrayList<>(set);

    // Iterate over the nums array to find missing value
    for (int left = 0, right = 0; left < length; left++) {
      // Update the right pointer untill the condition is true
      while (right < sortedNums.size() && sortedNums.get(right) < sortedNums.get(left) + length) {
        right++;
      }

      // Update the result
      result = Math.min(result, length - (right - left));
    }

    // Return the result
    return result;
  }
}

public class _2009_Minimum_Number_of_Operations_to_Make_Array_Continuous {
  // Main method to test minOperations
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 2, 3, 5, 6 };

    int result = new Solution().minOperations(nums);

    System.out.println("The minimum number of operations to make nums continuous is : " + result);
  }
}
