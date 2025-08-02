/*
LeetCode Problem: https://leetcode.com/problems/remove-duplicates-from-sorted-array/

Question: 26. Remove Duplicates from Sorted Array

Problem Statement: Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.
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
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Constraints:

1 <= nums.length <= 3 * 10^4
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
*/

/*
Approach: This problem can be solved efficiently using **binary search** since the array is sorted.

- We initialize two pointers: left = 0, right = nums.length - 1
- We perform binary search to find the target:
    - If nums[mid] == target: return mid
    - If nums[mid] < target: search the right half
    - If nums[mid] > target: search the left half
- If the target is not found, left will represent the index where the target should be inserted.

This works because binary search helps us narrow down the position quickly in O(log n) time.

Time Complexity: O(log n) — because binary search divides the array in half each iteration
Space Complexity: O(1) — no additional space is used
*/

package TwoPointersAndSlidingWindow.Easy;

public class _26_Remove_Duplicates_from_Sorted_Array {
    // Method to remove duplicates in a sorted array
    public static int removeDuplicates(int[] nums) {
        // If the array is empty, there are no elements to process
        if (nums.length == 0) {
            return 0;
        }

        // Start from 1 since the first element is always unique
        int index = 1;

        // Traverse the array and overwrite duplicates
        for (int i = 1; i < nums.length; i++) {
            // If the current element is different from the previous one
            if (nums[i] != nums[i - 1]) {
                nums[index++] = nums[i]; // Place unique element at the current index
            }
        }

        // 'index' now represents the count of unique elements
        return index;
    }

    // Main method to test removeDuplicates
    public static void main(String[] args) {
        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };

        int result = removeDuplicates(nums);

        System.out.println("The duplicates in the array is : " + result);
    }
}
