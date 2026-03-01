/*
LeetCode Problem: https://leetcode.com/problems/132-pattern/

Question: 456. 132 Pattern

Problem Statement: Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.

Example 1:
Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.

Example 2:
Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].

Example 3:
Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

Constraints:

n == nums.length
1 <= n <= 2 * 10^5
-10^9 <= nums[i] <= 10^9
*/

/*
Approach: Monotonic Stack (Reverse Traversal)

Goal:
- Determine whether there exists a 132 pattern:
  nums[i] < nums[k] < nums[j] where i < j < k.

Core Idea:
- Traverse from right to left.
- Use the array itself as a decreasing monotonic stack.
- Track:
    minimumValue → potential "2" in the 132 pattern.
- If current value becomes smaller than minimumValue,
  a valid 132 pattern exists.

Algorithm Steps:
1. Initialize stackPointer = nums.length.
2. Initialize minimumValue = Integer.MIN_VALUE.
3. Traverse from right to left:
   - If nums[i] < minimumValue → return true.
   - While stack not empty AND stack top < nums[i]:
       → update minimumValue with popped value.
   - Push nums[i] into stack.
4. If no pattern found, return false.

Time Complexity:
- O(n)

Space Complexity:
- O(1) (in-place stack simulation)

Result:
- Returns true if a 132 pattern exists, otherwise false.
*/

package StacksAndQueues.Medium;

public class _456_132_Pattern {
    // Method to find there is a 132 pattern in nums
    public static boolean find132pattern(int[] nums) {
        // Initialize the stack pointer
        int stackPointer = nums.length;

        // Initailize the minimum value seen so far
        int minimumValue = Integer.MIN_VALUE;

        // Iterate over the nums array
        for (int i = nums.length - 1; i >= 0; i--) {
            // If nums[i] is less than the minimum value then return true
            if (nums[i] < minimumValue) {
                return true;
            }

            // Remove the value form the stack if nums[i] is greater than the stack top
            // value
            while (stackPointer < nums.length && nums[stackPointer] < nums[i]) {
                // Upadate the minimum value
                minimumValue = nums[stackPointer++];
            }

            // Push the current value to the stack
            nums[--stackPointer] = nums[i];
        }

        // Return false in the end
        return false;
    }

    // Main method to test find132pattern
    public static void main(String[] args) {
        int[] nums = { 3, 1, 4, 2 };

        boolean result = find132pattern(nums);

        System.out.println("There is" + (result ? " a " : " no ") + "132 pattern in nums");
    }
}
