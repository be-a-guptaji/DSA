/*
LeetCode Problem: https://leetcode.com/problems/remove-element/

Question: 27. Remove Element

Problem Statement: Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

Example 1:
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100
*/

/*
Approach: Two-Pointer Technique (In-Place Filtering)

We remove all occurrences of a given value from the array without using extra space
and return the count of remaining valid elements.

We use two pointers:
1. slowIndex – tracks the position to place the next valid element.
2. fastIndex – scans through the entire array.

Algorithm:
- Traverse the array using fastIndex.
- If nums[fastIndex] != val:
  - Copy nums[fastIndex] to nums[slowIndex].
  - Increment slowIndex.
- Always move fastIndex forward.

At the end:
- slowIndex represents the new length of the array after removal.
- The first slowIndex elements contain the valid result.

Time Complexity: O(n)  
Space Complexity: O(1)
*/

package TwoPointersAndSlidingWindow.Easy;

import java.util.Arrays;

public class _27_Remove_Element {
    // Method to remove the val from the array
    public static int removeElement(int[] nums, int val) {
        // Initialize two pointers for traversal
        int slowIndex = 0, fastIndex = 0;

        // Iterate over the nums array to remove nums[i] which is equal to val
        for (int i = 0; i < nums.length; i++) {
            // If nums[fastIndex] is equal to val then skip the index else fill the
            // nums[slowIndex] value to nums[fastIndex] value
            if (nums[i] != val) {
                nums[slowIndex] = nums[fastIndex];

                // Increment the slowIndex value
                slowIndex++;
            }

            // Increment the fastIndex value
            fastIndex++;
        }

        // Return the slowIndex
        return slowIndex;
    }

    // Main method to test removeElement
    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val = 2;

        int result = removeElement(nums, val);

        System.out.println("The number of elements in " + Arrays.toString(nums) + " which are not equal to " + val
                + " is : " + result);
    }
}
