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
Approach: This problem can be solved efficiently using **binary search** since we are guaranteed 
that a peak element exists and adjacent elements are not equal.

   - Initialize two pointers: left = 0, right = nums.length - 1
   - While left < right:
       - Compute mid = left + (right - left) / 2
       - If nums[mid] < nums[mid + 1], then the peak must be on the right side (mid + 1 to right)
       - Otherwise, the peak is on the left side (left to mid)
   - When left == right, we have found a peak element

   This works because at least one peak always exists, and the binary search narrows down 
   the range by comparing the mid element with its neighbor.

Time Complexity: O(log n) — binary search cuts the search space in half at each step  
Space Complexity: O(1) — no extra space is used
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
