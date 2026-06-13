/*
LeetCode Problem: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/

Question: 2461. Maximum Sum of Distinct Subarrays With Length K

Problem Statement: You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

Example 2:
Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.

Constraints:
1 <= k <= nums.length <= 10^5
1 <= nums[i] <= 10^5
*/

/*
Approach: Sliding Window + Distinct Element Constraint

Goal:
- Find the maximum sum among all subarrays of
  length exactly k whose elements are all distinct.

Core Idea:
- Maintain a sliding window of size at most k.
- Track:
    1. Current window sum.
    2. Whether an element already exists in the window.
- If a duplicate appears:
    shrink the window until the duplicate is removed.
- When the window size becomes exactly k,
  all elements are distinct and the window is valid.

Data Structures:
- seen[value][0]:
      whether value is currently in the window.
- seen[value][1]:
      last occurrence index + 1.

Algorithm Steps:
1. Expand the window using right.
2. If nums[right] already exists:
   - Remove elements from the left until the
     previous occurrence is excluded.
3. Add nums[right] to:
   - current sum
   - seen structure
4. If window size exceeds k:
   - Remove nums[left]
   - Move left forward.
5. When window size equals k:
   - Update maximum sum.
6. Return the maximum sum found.

Why It Works:
- The window always contains distinct elements.
- Every valid subarray of length k is examined
  exactly once.
- The running sum allows O(1) window updates.

Time Complexity:
- O(n)

Space Complexity:
- O(M)
  where M is the maximum value in nums
  due to the seen array.

Note:
- This implementation assumes nums[i] >= 0 and
  uses direct indexing by value.
- A HashMap/HashSet solution provides the same
  O(n) complexity while supporting larger value
  ranges.

Result:
- Returns the maximum sum of any length-k subarray
  containing only distinct elements.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the maximum subarray sum of all the subarrays that meet the
  // conditions
  public long maximumSubarraySum(int[] nums, int k) {
    // Initialize the maxValue variable
    int maxValue = 0;

    // Iterate over the nums array to find the maxValue
    for (int i = 0; i < nums.length; i++) {
      if (maxValue < nums[i]) {
        maxValue = nums[i];
      }
    }
    // Initialize the seen variable
    int[][] seen = new int[maxValue + 1][2];

    // Initialize the result variable
    long result = 0;

    // Initialize the current variable
    long current = 0;

    // Iterate over the nums array
    for (int left = 0, right = 0; right < nums.length; right++) {
      // If we have already seen the number then update the current and left variable
      if (seen[nums[right]][0] == 1) {
        // Reset all the value till the seen variable first index
        while (left < seen[nums[right]][1]) {
          seen[nums[left]][0] = 0;
          seen[nums[left]][1] = 0;

          // Update the current variable
          current -= nums[left];

          // Increment the left index
          left++;
        }
      }

      // Add value to the seen matrix
      seen[nums[right]][0] = 1;
      seen[nums[right]][1] = right + 1;

      // Update the current variable
      current += nums[right];

      // If window size is more than k then update the left index
      if ((right - left + 1) > k) {
        seen[nums[left]][0] = 0;
        seen[nums[left]][1] = 0;

        // Update the current variable
        current -= nums[left];

        // Increment the left index
        left++;
      }

      // If window is equal to size of k then update the result variable
      if ((right - left + 1) == k) {
        result = Math.max(result, current);
      }
    }

    // Return the result variable
    return result;
  }
}

public class _2461_Maximum_Sum_of_Distinct_Subarrays_With_Length_K {
  // Main method to test maximumSubarraySum
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 1, 1, 7, 8, 9 };
    int k = 3;

    long result = new Solution().maximumSubarraySum(nums, k);

    System.out.println("The maximum subarray sum of all the subarrays that meet the conditions is : " + result);
  }
}
