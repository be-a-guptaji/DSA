/*
LeetCode Problem: https://leetcode.com/problems/binary-subarrays-with-sum/

Question: 930. Binary Subarrays With Sum

Problem Statement: Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.

Example 1:
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

Example 2:
Input: nums = [0,0,0,0,0], goal = 0
Output: 15

Constraints:
1 <= nums.length <= 3 * 10^4
nums[i] is either 0 or 1.
0 <= goal <= nums.length
*/

/*
Approach: Sliding Window + At-Most Transformation

Goal:
- Count the number of non-empty subarrays whose
  sum equals goal.

Core Idea:
- For a binary array:
      count(sum == goal)
    = count(sum <= goal)
      - count(sum <= goal - 1)

- Define helper(goal):
      number of subarrays with sum <= goal

- Since nums contains only 0s and 1s,
  a sliding window can efficiently count
  subarrays whose sum is at most a given value.

Algorithm Steps:
1. Compute:
      helper(goal)
2. Compute:
      helper(goal - 1)
3. Return:
      helper(goal) - helper(goal - 1)

Helper Function:
----------------
1. Maintain:
      left pointer
      current window sum
2. Expand right pointer:
      current += nums[right]
3. While current > goal:
      remove nums[left]
      left++
4. At this point every subarray ending at right
   and starting from:
      left ... right
   is valid.
5. Add:
      right - left + 1
   to the answer.
6. Return total count.

Why It Works:
- For binary arrays, window sums are monotonic
  when moving pointers.
- The helper function counts all subarrays with
  sum at most goal.
- Subtracting:
      atMost(goal - 1)
  removes every subarray whose sum is smaller,
  leaving only those with sum exactly goal.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the number of subarrays whose sum is
  exactly equal to goal.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the number of non-empty subarrays with a sum goal
  public int numSubarraysWithSum(int[] nums, int goal) {
    // Return the method call
    return this.helper(nums, goal) - this.helper(nums, goal - 1);
  }

  // Helper method to find the goal sub array
  private int helper(int[] nums, int goal) {
    // If goal is negative then return zero
    if (goal < 0) {
      return 0;
    }

    // Initailize the result variable
    int result = 0;

    // Iterate over the nums array
    for (int left = 0, right = 0, current = 0; right < nums.length; right++) {
      // Update the current variable
      current += nums[right];

      // If current is greater than goal then increment the left pointer
      while (current > goal) {
        current -= nums[left];
        left++;
      }

      // Update the result variable
      result += (right - left + 1);
    }

    // Return the result
    return result;
  }
}

public class _930_Binary_Subarrays_With_Sum {
  // Main method to test numSubarraysWithSum
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 0, 1, 0, 1 };
    int goal = 2;

    int result = new Solution().numSubarraysWithSum(nums, goal);

    System.out.println("The number of non-empty subarrays with a sum goal is : " + result);
  }
}
