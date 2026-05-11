/*
LeetCode Problem: https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/

Question: 2610. Convert an Array Into a 2D Array With Conditions

Problem Statement: You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:

The 2D array should contain only the elements of the array nums.
Each row in the 2D array contains distinct integers.
The number of rows in the 2D array should be minimal.
Return the resulting array. If there are multiple answers, return any of them.

Note that the 2D array can have a different number of elements on each row.

Example 1:
Input: nums = [1,3,4,1,2,3,1]
Output: [[1,3,4,2],[1,3],[1]]
Explanation: We can create a 2D array that contains the following rows:
- 1,3,4,2
- 1,3
- 1
All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
It can be shown that we cannot have less than 3 rows in a valid array.

Example 2:
Input: nums = [1,2,3,4]
Output: [[4,3,2,1]]
Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= nums.length
*/

/*
Approach: Frequency Counting + Greedy Row Construction

Goal:
- Construct a 2D array such that:
   - Each row contains distinct integers.
   - All elements from nums are used.
   - Number of rows is minimized.

Core Idea:
- The minimum number of rows required equals
  the maximum frequency of any number.
- Build rows greedily:
   - In each row, place one occurrence of every
     available number.

Algorithm Steps:
1. Count frequency of each number.
2. Track maximum frequency.
3. While maxFrequency > 0:
   - Create a new row.
   - Traverse all possible values:
       - If frequency[value] > 0:
           add value to row
           decrement frequency
   - Add row to result.
4. Return result.

Time Complexity:
- O(n²) in worst case

Space Complexity:
- O(n)

Result:
- Returns a 2D array where each row contains unique elements.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
  // Method to find the resulting array
  public List<List<Integer>> findMatrix(int[] nums) {
    // Initialize the length of the the nums array
    int length = nums.length;

    // Initialize the frequencyArray for the num count
    int[] frequency = new int[length + 1];

    // Initialize the maxFrequency variable
    int maxFrequency = 0;

    // Fill the frequency array
    for (int i = 0; i < length; i++) {
      frequency[nums[i]]++;

      // Update the maxFrequency
      maxFrequency = Math.max(maxFrequency, frequency[nums[i]]);
    }

    // Initialize the result list
    ArrayList<List<Integer>> result = new ArrayList<>();

    // Iterate over the maxFrequency untill it become zero
    while (maxFrequency-- != 0) {
      // Initialize the list for the result
      ArrayList<Integer> list = new ArrayList<>();

      // Iterate over the frequency to form a row
      for (int i = 1; i <= length; i++) {
        // If frequency[i] is not zero then add the index to the list and decrement the
        // index
        if (frequency[i] != 0) {
          // Decrement the frequency[i]
          frequency[i]--;

          // Add the number to the list
          list.add(i);
        }
      }

      // Add the list to the result list
      result.add(list);
    }

    // Return the result list
    return result;
  }
}

public class _2610_Convert_an_Array_Into_a_2D_Array_With_Conditions {
  // Main method to test findMatrix
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 3, 4, 1, 2, 3, 1 };

    List<List<Integer>> result = new Solution().findMatrix(nums);

    System.out.println("The resulting array is : " + result);
  }
}
