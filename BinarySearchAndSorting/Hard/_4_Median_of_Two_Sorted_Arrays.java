/*
LeetCode Problem: https://leetcode.com/problems/median-of-two-sorted-arrays/

Question: 4. Median of Two Sorted Arrays

Problem Statement: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000

Constraints:
- nums1.length == m, nums2.length == n
- 0 <= m <= 1000, 0 <= n <= 1000
- 1 <= m + n <= 2000
- -10^6 <= nums1[i], nums2[i] <= 10^6
*/

/*
Approach: Binary Search on Partition

Key Idea:
- We perform binary search on the smaller array to find a correct partition such that:
  1. All elements on the left side of the partition (from both arrays) are less than or equal to
     all elements on the right side.
  2. The total number of elements on the left side equals (m + n + 1) / 2 (to handle both even and odd cases).

Steps:
- Always binary search on the smaller array to maintain O(log(min(m, n))) time.
- At each step, choose a partition point in nums1 (i), then calculate the corresponding partition
  in nums2 (j = totalLeft - i).
- Use binary search to adjust the partition until you find:
  maxLeft ≤ minRight condition.
- Once found, calculate median based on total array size (odd/even).

Time Complexity: O(log(min(m, n)))
Space Complexity: O(1)
*/

package BinarySearchAndSorting.Hard;

public class _4_Median_of_Two_Sorted_Arrays {
    // Method to find the median of two sorted arrays
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to keep binary search efficient
        if (nums1.length > nums2.length) {
            // Change the position of the array so that the smaller one is always nums1
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        // Half of the total number of elements (left side of the partition)
        int totalLeft = (m + n + 1) / 2;

        int left = 0, right = m;

        // Binary search on the smaller array
        while (left <= right) {
            int i = (left + right) / 2; // Partition index for nums1
            int j = totalLeft - i; // Corresponding partition index for nums2

            // Edge values for partition handling
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Correct partition found
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                if ((m + n) % 2 == 0) {
                    // Even number of elements → average of two middle values
                    return (Math.max(nums1LeftMax, nums2LeftMax) +
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                } else {
                    // Odd number of elements → max of left side
                    return Math.max(nums1LeftMax, nums2LeftMax);
                }
            }
            // Move partition in nums1 to the left
            else if (nums1LeftMax > nums2RightMin) {
                right = i - 1;
            }
            // Move partition in nums1 to the right
            else {
                left = i + 1;
            }
        }

        // Code should never reach here if inputs are valid
        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }

    // Main method to test findMedianSortedArrays
    public static void main(String[] args) {

        int[] nums1 = { 1, 3 }, nums2 = { 2, 4 };

        double result = findMedianSortedArrays(nums1, nums2);

        System.out.println("The median in two sorted arrays is: " + result);
    }
}
