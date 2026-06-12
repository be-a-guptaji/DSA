/*
LeetCode Problem: https://leetcode.com/problems/count-number-of-nice-subarrays/

Question: 1248. Count Number of Nice Subarrays

Problem Statement: Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:
Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:
Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.

Example 3:
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Constraints:
1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
*/

/*
Approach: Sliding Window with Exactly K Odd Numbers

Goal:
- Count the number of subarrays containing
  exactly k odd numbers.

Core Idea:
- Maintain a window containing exactly k odds.
- When the window has exactly k odd numbers:
   - Every leading even number can be included
     or excluded while preserving k odds.
- Count all such valid starting positions.

Pointer Roles:
--------------
left:
  Start of the valid window.

mid:
  First odd number inside the current window.

right:
  Expands the window.

Algorithm Steps:
1. Expand right pointer.
2. Whenever nums[right] is odd:
      k--
3. If k becomes negative:
   - Move left until one odd number is removed.
   - Reset:
         mid = left
4. If k == 0:
   - Move mid to the first odd number
     in the current window.
   - Number of valid starts:
         mid - left + 1
   - Add this count to the answer.
5. Continue until the array ends.
6. Return the total count.

Why It Works:
- Once a window contains exactly k odd numbers,
  removing any leading even numbers does not
  change the odd count.
- Therefore every start position from:
      left ... mid
  forms a valid subarray ending at right.
- The count of such starts is:
      mid - left + 1

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns the number of subarrays containing
  exactly k odd numbers.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the number of nice sub-arrays
  public int numberOfSubarrays(int[] nums, int k) {
    // Initialize the numberOfSubarrays variable
    int numberOfSubarrays = 0;

    // Iterate over the nums array
    for (int left = 0, mid = 0, right = 0; right < nums.length; right++) {
      // If nums right index is odd then decrement the k
      if ((nums[right] & 1) == 1) {
        k--;
      }

      // While k is negative shift the left pointer
      while (k < 0) {
        // If nums left index is odd then increment the k
        if ((nums[left] & 1) == 1) {
          k++;
        }

        // Increment the left pointer
        left++;

        // Update the mid pointer to the left
        mid = left;
      }

      // If k is zero then increment the numberOfSubarrays
      if (k == 0) {
        // Get the mid to the first odd number
        while ((nums[mid] & 1) == 0) {
          mid++;
        }

        // Update the numberOfSubarrays
        numberOfSubarrays += (mid - left + 1);
      }
    }

    // Return the numberOfSubarrays
    return numberOfSubarrays;
  }
}

public class _1248_Count_Number_of_Nice_Subarrays {
  // Main method to test numberOfSubarrays
  public static void main(String[] args) {
    int[] nums = new int[] { 1, 1, 2, 1, 1 };
    int k = 3;

    int result = new Solution().numberOfSubarrays(nums, k);

    System.out.println("The number of nice sub-arrays is : " + result);
  }
}
