/*
LeetCode Problem: https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/

Question: 2966. Divide Array Into Arrays With Max Difference

Problem Statement: You are given an integer array nums of size n where n is a multiple of 3 and a positive integer k.

Divide the array nums into n / 3 arrays of size 3 satisfying the following condition:

The difference between any two elements in one array is less than or equal to k.
Return a 2D array containing the arrays. If it is impossible to satisfy the conditions, return an empty array. And if there are multiple answers, return any of them.

Example 1:
Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
Output: [[1,1,3],[3,4,5],[7,8,9]]
Explanation:
The difference between any two elements in each array is less than or equal to 2.
Example 2:
Input: nums = [2,4,2,2,5,2], k = 2
Output: []
Explanation:
Different ways to divide nums into 2 arrays of size 3 are:
[[2,2,2],[2,4,5]] (and its permutations)
[[2,2,4],[2,2,5]] (and its permutations)
Because there are four 2s there will be an array with the elements 2 and 5 no matter how we divide it. since 5 - 2 = 3 > k, the condition is not satisfied and so there is no valid division.

Example 3:
Input: nums = [4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11], k = 14
Output: [[2,2,2],[4,5,5],[5,5,7],[7,8,8],[9,9,10],[11,12,12]]
Explanation:
The difference between any two elements in each array is less than or equal to 14.

Constraints:
n == nums.length
1 <= n <= 10^5
n is a multiple of 3
1 <= nums[i] <= 10^5
1 <= k <= 10^5
*/

/*
Approach: Sorting + Greedy Group Formation

Goal:
- Divide the array into groups of size 3 such that
  the difference between maximum and minimum element
  in each group is at most k.

Core Idea:
- Sort the array so close values stay adjacent.
- Form groups greedily using consecutive elements.
- In a sorted group of 3:
    max difference = nums[i+2] - nums[i]
- If this exceeds k, valid grouping is impossible.

Algorithm Steps:
1. Sort nums array.
2. Initialize result matrix of size n / 3.
3. Traverse array in steps of 3:
   - Check:
       nums[i+2] - nums[i] <= k
   - If false:
       return empty matrix.
   - Else:
       store the 3 elements in result.
4. Return result matrix.

Time Complexity:
- O(n log n)
  due to sorting

Space Complexity:
- O(n)
  for result matrix

Result:
- Returns grouped 2D array if possible,
  otherwise returns empty array.
*/

package Arrays.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the 2D array containing the arrays
  public int[][] divideArray(int[] nums, int k) {
    // Sort the array
    Arrays.sort(nums);

    // Initialize the result matrix
    int[][] result = new int[nums.length / 3][3];

    // Iterate over the nums array
    for (int i = 0, index = 0; i < nums.length; i += 3, index++) {
      // If difference more than k then return empty matrix
      if ((nums[i + 2] - nums[i]) > k) {
        return new int[0][];
      }

      // Update the result matrix
      result[index][0] = nums[i];
      result[index][1] = nums[i + 1];
      result[index][2] = nums[i + 2];
    }

    // Return the result
    return result;
  }
}

public class _2966_Divide_Array_Into_Arrays_With_Max_Difference {
  // Main method to test divideArray
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 3, 4, 8, 7, 9, 3, 5, 1 };
    int k = 2;

    int[][] result = new Solution().divideArray(nums, k);

    System.out.println("A 2D array containing the arrays is : " + Arrays.deepToString(result));
  }
}
