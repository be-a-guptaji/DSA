/*
LeetCode Problem: https://leetcode.com/problems/contains-duplicate-ii/

Question: 219. Contains Duplicate II

Problem Statement: Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:
Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false

Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
0 <= k <= 10^5
*/

/*
Approach: Sliding Window + HashSet

Goal:
- Determine whether there exist two distinct indices
  i and j such that:
      nums[i] == nums[j]
  and:
      |i - j| <= k

Core Idea:
- Maintain a sliding window of size at most k.
- Use a HashSet to track elements currently
  inside the window.
- If a number already exists in the window,
  a valid duplicate is found.

Algorithm Steps:
1. Initialize:
   - HashSet window
   - left pointer = 0
2. Traverse nums using right pointer:
   - If window size exceeds k:
       remove nums[left]
       increment left
   - If nums[right] already exists in window:
       return true
   - Add nums[right] to window
3. If traversal completes:
   return false

Time Complexity:
- O(n)

Space Complexity:
- O(min(n, k))

Result:
- Returns true if a nearby duplicate exists,
  otherwise false.
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.HashSet;

// Solution Class
class Solution {
    // Method to find if there are two distinct indices
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Initialize the HashSet for the window
        HashSet<Integer> window = new HashSet<>();

        // Initialize the left variable
        int left = 0;

        // Iterate over the nums array
        for (int right = 0; right < nums.length; right++) {
            // If window is bigger then the k then update the set and the left variable
            if (right - left > k) {
                // If window is bigger than the k then remove the nums[left] from the window
                window.remove(nums[left]);

                // Increment the left variable
                left++;
            }

            // If window contain nums[right] then return true
            if (window.contains(nums[right])) {
                return true;
            }

            // Add the nums[right] to the set
            window.add(nums[right]);
        }

        // Retrun the false in the end
        return false;
    }
}

public class _219_Contains_Duplicate_II {
    // Main method to test the containsNearbyDuplicate
    public static void main(String[] args) {
        int[] nums = new int[] {};
        int k = 3;

        boolean result = new Solution().containsNearbyDuplicate(nums, k);

        System.out.println("There are " + (result ? "no" : "") + " two indices which satisfies the condition.");
    }
}
