/*
LeetCode Problem: https://leetcode.com/problems/merge-sorted-array/

Question: 88. Merge Sorted Array

Problem Statement: You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.

Example 2:
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].

Example 3:
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.

Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-10^9 <= nums1[i], nums2[j] <= 10^9

Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */

/*
Approach: Three Pointers (In-place Merge from Back)

Goal:
Merge nums2 into nums1 so that nums1 becomes a sorted array.
nums1 has enough extra space at the end to hold nums2.

Key Idea:
Merge from the back to avoid overwriting elements in nums1.

Pointers:
- m: last valid index in nums1
- n: last index in nums2
- sortedArrayIndex: last index of nums1 (m + n - 1)

Steps:
1. Compare nums1[m - 1] and nums2[n - 1].
2. Place the larger value at nums1[sortedArrayIndex].
3. Move the corresponding pointer (m or n) and decrement sortedArrayIndex.
4. Repeat until one array is exhausted.
5. If nums2 still has elements, copy them into nums1.
   (Remaining nums1 elements are already in place.)

Why it works:
- Backward merge prevents data loss.
- Uses existing space in nums1 → no extra memory.

Time Complexity: O(m + n)
Space Complexity: O(1) (in-place)

This is the optimal solution for this problem.
*/

package TwoPointersAndSlidingWindow.Easy;

public class _88_Merge_Sorted_Array {
    // Method to merge the arrays
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize the pointer for the sorted nums1 array
        int sortedArrayIndex = nums1.length - 1;

        // Add the numbers in the nums1 array in the sorted array
        while (m != 0 && n != 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[sortedArrayIndex--] = nums1[--m];
            } else {
                nums1[sortedArrayIndex--] = nums2[--n];
            }
        }

        // Add all element of the nums1 to the list
        while (m != 0) {
            nums1[sortedArrayIndex--] = nums1[--m];

        }
        
        // Add all element of the nums2 to the list
        while (n != 0) {
            nums1[sortedArrayIndex--] = nums2[--n];
        }
    }

    // Main method to test merge
    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 2, 3, 0, 0, 0 };
        int m = 3;
        int[] nums2 = new int[] { 2, 5, 6 };
        int n = 3;

        merge(nums1, m, nums2, n);

        System.out.println("The arrays is merged.");
    }
}
