/*
LeetCode Problem: https://leetcode.com/problems/non-decreasing-array/

Question: 665. Non-decreasing Array

Problem Statement: Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.

We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).

Example 1:
Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:
Input: nums = [4,2,1]
Output: false
Explanation: You cannot get a non-decreasing array by modifying at most one element.

Constraints:
n == nums.length
1 <= n <= 10^4
-10^5 <= nums[i] <= 10^5
*/

/*
Approach: Greedy (Single Modification Check)

Goal:
- Determine if the array can be made non-decreasing
  by modifying at most one element.

Core Idea:
- Traverse the array and detect violations where:
    nums[i] > nums[i+1]
- Allow only one such modification.
- Fix locally in a way that preserves global ordering:
   - Prefer lowering nums[i] if safe.
   - Otherwise, raise nums[i+1].

Algorithm Steps:
1. Initialize changed = false.
2. Traverse array:
   - If nums[i] ≤ nums[i+1] → continue.
   - If already changed → return false.
   - Else:
       a. If i == 0 or nums[i+1] ≥ nums[i-1]:
            → nums[i] = nums[i+1]
       b. Else:
            → nums[i+1] = nums[i]
   - Mark changed = true.
3. Return true.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Result:
- Returns true if array can be made non-decreasing
  with at most one modification, else false.
*/

package Arrays.Medium;

// Solution Class
class Solution {
  // Method to determine if array is strictly non-decreasing after modifying
  // atmost one element
  public boolean checkPossibility(int[] nums) {
    // Initialize the boolean array to check if we modified the array or not
    boolean changed = false;

    // Itreate over the nums array to make it non-decreasing
    for (int i = 0; i < nums.length - 1; i++) {
      // If adjacent element is in non-decreasing order then skip the iteration
      if (nums[i] <= nums[i + 1]) {
        continue;
      }

      // If changed is true the return false
      if (changed) {
        return false;
      }

      // Update the current index
      if (i == 0 || nums[i + 1] >= nums[i - 1]) {
        nums[i] = nums[i + 1];
      } else {
        nums[i + 1] = nums[i];
      }

      // Update the changed to true
      changed = true;
    }

    // Return true in the end
    return true;
  }
}

public class _665_Non_decreasing_Array {
  // Main method to test checkPossibility
  public static void main(String[] args) {
    int[] nums = new int[] { 4, 2, 3 };

    boolean result = new Solution().checkPossibility(nums);

    System.out.println(
        "The array is" + (result ? " " : " not ") + "strictly non-decreasing modifying after atmost one element.");
  }
}
