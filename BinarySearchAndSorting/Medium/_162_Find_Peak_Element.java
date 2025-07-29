/*
LeetCode Problem: https://leetcode.com/problems/find-peak-element/

Question: 162. Find Peak Element

Problem Statement: A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.

Constraints:

1 <= nums.length <= 1000
-2^31 <= nums[i] <= 2^31 - 1
nums[i] != nums[i + 1] for all valid i.
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

package BinarySearchAndSorting.Medium;

public class _162_Find_Peak_Element {
    // Method to find the index of the peak element
    public static int findPeakElement(int[] nums) {
        // Initialize the left, right
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Check if mid is less than mid + 1
            if (nums[mid] < nums[mid + 1]) {
                // Peak is on the right
                left = mid + 1;
            } else {
                // Peak is on the left (including mid)
                right = mid;
            }
        }

        // left == right is the peak
        return left;
    }

    // Main method to test findPeakElement
    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 3, 5, 6, 4 };

        int result = findPeakElement(nums);

        System.out.println("The peak element index is : " + result);
    }
}
