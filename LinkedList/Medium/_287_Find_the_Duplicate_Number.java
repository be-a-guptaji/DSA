/*
LeetCode Problem: https://leetcode.com/problems/find-the-duplicate-number/

Question: 287. Find the Duplicate Number

Problem Statement: Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and using only constant extra space.

Example 1:
Input: nums = [1,3,4,2,2]
Output: 2

Example 2:
Input: nums = [3,1,3,4,2]
Output: 3

Example 3:
Input: nums = [3,3,3,3,3]
Output: 3

Constraints:

1 <= n <= 10^5
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.

Follow up:

How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?
 */

/*
Approach: Floyd’s Tortoise and Hare (Cycle Detection)

This problem is solved by treating the array as a linked list where:
- Each index points to nums[index].
- Since one number is duplicated, a cycle must exist.

We use two pointers:
1. slow – moves one step at a time.
2. fast – moves two steps at a time.

Step 1: Detect the intersection point inside the cycle.
- Move slow = nums[slow]
- Move fast = nums[nums[fast]]
- When slow == fast, a cycle is confirmed.

Step 2: Find the entrance to the cycle (duplicate number).
- Initialize a pointer start = 0.
- Move both slow and start one step at a time.
- When slow == start, that position is the duplicate number.

Why this works:
- The duplicate number creates a cycle.
- The entry point of the cycle is the duplicate value.

Time Complexity: O(n)
Space Complexity: O(1)
*/

package LinkedList.Medium;

public class _287_Find_the_Duplicate_Number {
    // Method to find the number which appears more than ones in the array
    public static int findDuplicate(int[] nums) {
        // Initialize the two pointers one fast and one slow
        int fast = 0, slow = 0;

        // Iterate over the nums array until both pointer meets
        while (true) {
            // Move slow pointer ones
            slow = nums[slow];

            // Move fast pointer twice
            fast = nums[nums[fast]];

            // If both pointers at the same poition then break
            if (slow == fast) {
                break;
            }
        }

        // Initialize the a pointer for the head to travel to the intersection
        int start = 0;

        // Iterate over the nums array until both pointer meets
        while (true) {
            // Move slow pointer ones
            slow = nums[slow];

            // Move start pointer ones
            start = nums[start];

            // If both pointers at the same poition then break
            if (slow == start) {
                // Retrun the start
                return start;
            }
        }
    }

    // Main method to test findDuplicate
    public static void main(String[] args) {
        int[] nums = { 9, 9, 9, 9, 9, 9, 9 };

        int result = findDuplicate(nums);

        System.out.print("The number which appears more than ones in the array is : " + result);
    }
}
