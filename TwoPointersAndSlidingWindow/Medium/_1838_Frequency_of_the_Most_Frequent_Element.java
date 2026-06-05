/*
LeetCode Problem: https://leetcode.com/problems/frequency-of-the-most-frequent-element/

Question: 1838. Frequency of the Most Frequent Element

Problem Statement: The frequency of an element is the number of times it occurs in an array.

You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.

Return the maximum possible frequency of an element after performing at most k operations.

Example 1:
Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.

Example 2:
Input: nums = [1,4,8,13], k = 5
Output: 2
Explanation: There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.

Example 3:
Input: nums = [3,9,6], k = 2
Output: 1

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
1 <= k <= 10^5
*/

/*
Approach: Sorting + Sliding Window

Goal:
- Find the maximum frequency of an element after
  performing at most k increment operations.

Core Idea:
- After sorting, choose nums[right] as the target value.
- To make every element in the current window equal to
  nums[right], the required operations are:

      nums[right] * windowSize - windowSum

- If required operations exceed k,
  shrink the window from the left.
- The largest valid window size is the answer.

Algorithm Steps:
1. Sort nums.
2. Maintain:
   - left pointer
   - running window sum
3. Expand right pointer:
   - Add nums[right] to window sum.
4. Check validity:
      nums[right] * windowSize
      <= windowSum + k
5. If invalid:
   - Remove nums[left] from sum.
   - Increment left.
6. Continue until all elements processed.
7. Return maximum valid window size.

Note:
- In this implementation, returning
      nums.length - left
  works because the window size never decreases
  after reaching the optimal final state.
- More commonly, solutions explicitly track:
      maxFrequency = max(maxFrequency, windowSize)

Time Complexity:
- O(n log n)
  due to sorting

Space Complexity:
- O(1)

Result:
- Returns the maximum achievable frequency.
*/

package TwoPointersAndSlidingWindow.Medium;

import java.util.Arrays;

// Solution Class
class Solution {
  // Method to find the maximum possible frequency of an element after performing
  // at most k operations
  public int maxFrequency(int[] nums, int k) {
    // Sort the array
    Arrays.sort(nums);

    // Initialize the totalSum varaible
    long totalSum = 0;

    // Initialzie the left variable
    int left = 0;

    // Iterate over the nums array
    for (int right = 0; right < nums.length; right++) {
      // Update the totalSum
      totalSum += nums[right];

      // Shink the window if k cannot make the current frequency
      if ((right - left + 1) * 1L * nums[right] > totalSum + k) {
        totalSum -= nums[left];
        left++;
      }
    }

    // Return the nums.length - left
    return nums.length - left;
  }
}

public class _1838_Frequency_of_the_Most_Frequent_Element {
  // Main method to test maxFrequency
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 4, 8, 13 };
    int k = 3;

    int result = new Solution().maxFrequency(nums, k);

    System.out
        .println("The maximum possible frequency of an element after performing at most k operations is : " + result);
  }
}
