/*
LeetCode Problem: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/

Question: 1658. Minimum Operations to Reduce X to Zero

Problem Statement: You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4
1 <= x <= 10^9
*/

/*
Approach: Sliding Window on Complement Subarray

Goal:
- Find the minimum number of operations needed
  to reduce x to exactly 0 by removing elements
  from either end of the array.

Core Idea:
- Removing elements from the ends is equivalent to
  keeping a middle subarray.
- Let:

      totalSum = sum(nums)

- If removed elements sum to x, then the kept
  subarray must sum to:

      target = totalSum - x

- Therefore the problem becomes:
   Find the longest subarray whose sum equals target.

- If such a subarray has length L:
     operations = n - L

Algorithm Steps:
1. Compute:
      target = totalSum - x
2. Use a sliding window to find the longest
   subarray with sum = target.
3. Expand right pointer:
      currentSum += nums[right]
4. While currentSum > target:
      remove nums[left]
      increment left
5. If currentSum == target:
      update longest window length.
6. Return:
      n - longestWindow
   or -1 if no valid subarray exists.

Why It Works:
- Every operation removes an element from an end.
- The elements not removed form one contiguous
  middle subarray.
- Minimizing removals is equivalent to maximizing
  the length of the kept subarray.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the minimum number of operations needed
  to reduce x to exactly 0, or -1 if impossible.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the minimum number of operations to reduce x to exactly 0
  public int minOperations(int[] nums, int x) {
    // Initialize maxWindow variable
    int maxWindow = -1;

    // Initialize the currentSum variable
    long currentSum = 0;

    // Initialize the target variable
    long target = 0;

    // Get the total sum of the array
    for (int i = 0; i < nums.length; i++) {
      target += nums[i];
    }

    // Subtract x from the target
    target -= x;

    // Iterate over the nums array
    for (int left = 0, right = 0; right < nums.length; right++) {
      // Add the right index value to the currentSum
      currentSum += nums[right];

      // If currentSum is greater than the target then shrik the window
      while (left <= right && currentSum > target) {
        // Remove the left index value to the currentSum
        currentSum -= nums[left];

        // Increment the left index
        left++;
      }

      // If currentSum is equal to the target then update the maxWindow
      if (currentSum == target) {
        maxWindow = Math.max(maxWindow, right - left + 1);
      }
    }

    // Return the maxWindow
    return maxWindow == -1 ? -1 : nums.length - maxWindow;
  }
}

public class _1658_Minimum_Operations_to_Reduce_X_to_Zero {
  // Main method to test minOperations
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 1, 4, 2, 3 };
    int x = 5;

    int result = new Solution().minOperations(nums, x);

    System.out.println("The minimum number of operations to reduce x to exactly 0 is : " + result);
  }
}
