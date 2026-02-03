/*
LeetCode Problem: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

Question: 80. Remove Duplicates from Sorted Array II

Problem Statement: Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

Example 1:
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:
Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Constraints:

1 <= nums.length <= 3 * 10^4
-10^4 <= nums[i] <= 10^4
nums is sorted in non-decreasing order.
 */

/*
Approach: Two Pointers (Overwrite In-Place)

Problem:
Remove duplicates from a sorted array in-place such that each element
appears at most twice. Return the new length.

Core Idea:
- Since the array is sorted, duplicates are adjacent.
- Allow an element to be written only if it does not create more than
  two occurrences of the same value.

Algorithm:
1. Use `index` as the write pointer.
2. Iterate through each `num` in the array:
   - Always allow the first two elements (`index < 2`).
   - For later elements, allow `num` only if it is different from
     `nums[index - 2]`.
3. Write valid elements at `nums[index]` and increment `index`.
4. Return `index` as the new array length.

Why It Works:
- `nums[index - 2]` represents the element two positions before the
  current write position.
- If `num == nums[index - 2]`, writing it would create more than two
  duplicates, so it is skipped.
- This guarantees each value appears at most twice.

Time Complexity:
- O(n), single pass through the array.

Space Complexity:
- O(1), in-place modification with constant extra space.
*/

package TwoPointersAndSlidingWindow.Medium;

public class _80_Remove_Duplicates_from_Sorted_Array_II {
    // Method to find the length of the array remove some duplicates in-place such
    // that each unique element appears at most twice
    public static int removeDuplicates(int[] nums) {
        // Initialize pointers for the traversal and removing the duplicates
        int index = 0;

        // Iterate over the nums array
        for (int num : nums) {
            // Fill the index untill index i is less then 2 or num is not equal to index - 2
            if (index < 2 || num != nums[index - 2]) {
                nums[index++] = num;
            }
        }

        // Return the index
        return index;
    }

    // Main method to test removeDuplicates
    public static void main(String[] args) {
        int[] nums = new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 };

        int result = removeDuplicates(nums);

        System.out.println(
                "The length of the array after remove some duplicates in-place such that each unique element appears at most twice is : "
                        + result);
    }
}
