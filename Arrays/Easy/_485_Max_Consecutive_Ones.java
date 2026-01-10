/*
LeetCode Problem: https://leetcode.com/problems/max-consecutive-ones/

Question: 485. Max Consecutive Ones

Problem Statement: Given a binary array nums, return the maximum number of consecutive 1's in the array.

Example 1:
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

Example 2:
Input: nums = [1,0,1,1,0,1]
Output: 2

Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
*/

/*
Approach: Single Pass with Last Zero Tracking

Goal:
Find the maximum number of consecutive 1’s in a binary array.

Key Idea:
Track the index of the most recent zero and compute the length of the current
streak of 1’s using index differences.

Algorithm:
1. Initialize:
   - startingIndex = -1 (acts as the index before the array start)
   - maxConsecutiveOnes = 0
2. Traverse the array:
   - If nums[i] == 0:
       startingIndex = i   (reset streak start)
   - Else:
       currentStreak = i - startingIndex
       maxConsecutiveOnes = max(maxConsecutiveOnes, currentStreak)
3. Return maxConsecutiveOnes.

Why It Works:
- The distance from the last zero gives the current run of 1’s.
- A single pass is sufficient.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package Arrays.Easy;

public class _485_Max_Consecutive_Ones {
    // Method to the maximum number of consecutive 1's in the array
    public static int findMaxConsecutiveOnes(int[] nums) {
        // Initialize the starting index and the variable to hold maximum number of
        // consecutive ones
        int startingIndex = -1, maxConsecutiveOnes = 0;

        // Iterate over the nums array to find the maximum number of consecutive ones
        for (int i = 0; i < nums.length; i++) {
            // If we encounter zero then reset the starting index to i
            if (nums[i] == 0) {
                startingIndex = i;
            } else {
                // Update the maximum number of consecutive ones
                maxConsecutiveOnes = Math.max(maxConsecutiveOnes, i - startingIndex);
            }
        }

        // Return the maximum number of consecutive ones
        return maxConsecutiveOnes;
    }

    // Main method to test findMaxConsecutiveOnes
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 0, 1, 1, 0, 1 };

        int result = findMaxConsecutiveOnes(nums);

        System.out.println("The maximum number of consecutive 1's in the array is : " + result);
    }
}
