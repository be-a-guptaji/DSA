/*
LeetCode Problem: https://leetcode.com/problems/longest-consecutive-sequence/

Question: 128. Longest Consecutive Sequence

Problem Statement: Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Example 3:
Input: nums = [1,0,1,2]
Output: 3

Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/

/*
Approach: This solution uses a HashSet to efficiently track and build consecutive sequences.
- All elements from the array are added into a HashSet for O(1) lookup.
- We iterate through the set instead of the array to avoid duplicate processing.
- A number is considered the start of a sequence only if (num - 1) is not present in the set.
- From each valid starting point, we expand forward to count consecutive elements.
- The maximum length found during traversal is stored as the result.

Time Complexity: O(n), where n = number of elements in the array.
Each element is visited at most twice (once for start check, once during sequence expansion).
Space Complexity: O(n), due to the HashSet used to store all elements.
*/

package Arrays.Medium;

import java.util.HashSet;

public class _128_Longest_Consecutive_Sequence {
    // Method to find the longest consecutive elements sequence
    public static int longestConsecutive(int[] nums) {

        // Edge case if nums length is zero
        if (nums.length == 0) {
            return 0;
        }

        // Convert the array into the set to remove duplicates and allow O(1) lookups
        HashSet<Integer> set = new HashSet<>();

        // Add all the elements into the set
        for (int num : nums) {
            set.add(num);
        }

        // Initialize the variable to store the longest consecutive sequence length
        int longestConsecutiveSequence = 0;

        // Iterate over each element of the set
        for (int num : set) {

            // Check if the current number is the start of a new sequence
            if (!set.contains(num - 1)) {

                // Initialize the current number for sequence traversal
                int currentNum = num;

                // Initialize the sequence length counter
                int currentStreak = 1;

                // Keep checking for the next consecutive numbers
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // Update the longest consecutive sequence found so far
                longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currentStreak);
            }
        }

        // Return the final longest consecutive sequence length
        return longestConsecutiveSequence;
    }

    // Main method to test longestConsecutive
    public static void main(String[] args) {
        int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };

        int result = longestConsecutive(nums);

        System.out.println("The longest consecutive elements sequence is of : " + result);
    }
}
