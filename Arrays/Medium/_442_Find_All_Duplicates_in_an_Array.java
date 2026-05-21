/*
LeetCode Problem: https://leetcode.com/problems/find-all-duplicates-in-an-array/

Question: 442. Find All Duplicates in an Array

Problem Statement: Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears at most twice, return an array of all the integers that appears twice.

You must write an algorithm that runs in O(n) time and uses only constant auxiliary space, excluding the space needed to store the output

Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]

Example 2:
Input: nums = [1,1,2]
Output: [1]

Example 3:
Input: nums = [1]
Output: []

Constraints:
n == nums.length
1 <= n <= 10^5
1 <= nums[i] <= n
Each element in nums appears once or twice.
*/

/*
Approach: In-Place Negative Marking

Goal:
- Find all elements that appear exactly twice in the array.

Core Idea:
- Values are in range [1, n].
- Use array indices as markers:
   - For number x:
       use index = x - 1
- First visit:
   - Mark nums[index] negative.
- Second visit:
   - nums[index] already negative,
     so x is a duplicate.

Algorithm Steps:
1. Traverse nums array.
2. For each element:
   - index = abs(nums[i]) - 1
3. If nums[index] is negative:
   - Add abs(nums[i]) to result.
4. Otherwise:
   - Negate nums[index].
5. Return result list.

Time Complexity:
- O(n)

Space Complexity:
- O(1)
  excluding output list

Result:
- Returns all numbers appearing twice.
*/

package Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

// Solution Class
class Solution {
    // Method to find an array of all the integers that appears twice
    public List<Integer> findDuplicates(int[] nums) {
        // Initialize the arraylist
        ArrayList<Integer> result = new ArrayList<>();

        // Update the nums array and add value to the result list
        for (int i = 0; i < nums.length; i++) {
            // Get the current number offset to index
            int index = Math.abs(nums[i]) - 1;

            // If nums[index] array is negative then add to teh result array
            if (nums[index] < 0) {
                result.add(Math.abs(nums[i]));
            }

            // Negatively mark the index
            nums[index] *= -1;
        }

        // Return the result list
        return result;
    }
}

public class _442_Find_All_Duplicates_in_an_Array {
    // Main method to test countBadPairs
    public static void main(String[] args) {
        int[] nums = new int[] { 4, 3, 2, 7, 8, 2, 3, 1 };

        List<Integer> result = new Solution().findDuplicates(nums);

        System.out.println("An array of all the integers that appears twice is : " + result);
    }
}
