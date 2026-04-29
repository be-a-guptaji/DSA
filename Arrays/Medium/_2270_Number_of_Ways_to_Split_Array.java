/*
LeetCode Problem: https://leetcode.com/problems/number-of-ways-to-split-array/

Question: 2270. Number of Ways to Split Array

Problem Statement: You are given a 0-indexed integer array nums of length n.

nums contains a valid split at index i if the following are true:

The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
There is at least one element to the right of i. That is, 0 <= i < n - 1.
Return the number of valid splits in nums.

Example 1:
Input: nums = [10,4,-8,7]
Output: 2
Explanation: 
There are three ways of splitting nums into two non-empty parts:
- Split nums at index 0. Then, the first part is [10], and its sum is 10. The second part is [4,-8,7], and its sum is 3. Since 10 >= 3, i = 0 is a valid split.
- Split nums at index 1. Then, the first part is [10,4], and its sum is 14. The second part is [-8,7], and its sum is -1. Since 14 >= -1, i = 1 is a valid split.
- Split nums at index 2. Then, the first part is [10,4,-8], and its sum is 6. The second part is [7], and its sum is 7. Since 6 < 7, i = 2 is not a valid split.
Thus, the number of valid splits in nums is 2.

Example 2:
Input: nums = [2,3,1,0]
Output: 2
Explanation: 
There are two valid splits in nums:
- Split nums at index 1. Then, the first part is [2,3], and its sum is 5. The second part is [1,0], and its sum is 1. Since 5 >= 1, i = 1 is a valid split. 
- Split nums at index 2. Then, the first part is [2,3,1], and its sum is 6. The second part is [0], and its sum is 0. Since 6 >= 0, i = 2 is a valid split.

Constraints:
2 <= nums.length <= 10^5
-10^5 <= nums[i] <= 10^5

*/

/*
Approach: Prefix Sum (Single Pass Split Evaluation)

Goal:
- Count the number of indices where the array can be split
  such that left sum ≥ right sum.

Core Idea:
- Compute total sum (rightSum initially).
- Traverse array while maintaining leftSum.
- At each split index i:
    leftSum = sum[0..i]
    rightSum = sum[i+1..n-1]
- Check condition:
    leftSum ≥ rightSum

Algorithm Steps:
1. Compute total sum → rightSum.
2. Initialize leftSum = 0.
3. Iterate from i = 0 to n-2:
   - leftSum += nums[i]
   - rightSum -= nums[i]
   - If leftSum ≥ rightSum → increment count.
4. Return count.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns number of valid split positions.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to find the number of valid splits in nums
  public int waysToSplitArray(int[] nums) {
    // Initialize the left and right sum of the array
    long leftSum = 0, rightSum = 0;

    // Get the right sum of the nums
    for (int i = 0; i < nums.length; i++) {
      rightSum += nums[i];
    }

    // Initialize the variable for the numbersOfWaysToSplitArray
    int numbersOfWaysToSplitArray = 0;

    // Iterate over the nums array to find the numbersOfWaysToSplitArray
    for (int i = 0; i < nums.length - 1; i++) {
      // Get the current nums[i]
      int num = nums[i];

      // Update the left and right sum
      leftSum += num;
      rightSum -= num;

      // Update numbersOfWaysToSplitArray if left sum is greater than or equal to
      // right sum
      if (leftSum >= rightSum) {
        numbersOfWaysToSplitArray++;
      }
    }

    // Return numbersOfWaysToSplitArray
    return numbersOfWaysToSplitArray;
  }
}

public class _2270_Number_of_Ways_to_Split_Array {
  // Main method to test waysToSplitArray
  public static void main(String[] args) {
    int[] nums = new int[] { 10, 4, -8, 7 };

    int result = new Solution().waysToSplitArray(nums);

    System.out.println("The number of valid splits in nums is : " + result);
  }
}
