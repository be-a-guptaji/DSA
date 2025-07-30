/*
LeetCode Problem: https://leetcode.com/problems/median-of-two-sorted-arrays/

Question: 4. Median of Two Sorted Arrays

Problem Statement: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-10^6 <= nums1[i], nums2[i] <= 10^6
*/

/*
Approach: We use a **stack-based iterative approach** to simulate evaluation of the expression.

Key ideas:
1. Maintain a `result` which accumulates the running total.
2. Use a `sign` variable to track whether the current number is positive or negative.
3. Use a `stack` to store previous results and signs when we encounter `(`.
4. On encountering `)`, we complete the sub-expression by popping sign and previous result from the stack.
5. We handle multi-digit numbers by accumulating them as we iterate.
6. Spaces are skipped.

Steps:
- If current char is a digit → build the number.
- If '+' or '-' → finalize the previous number with its sign, update sign.
- If '(' → push result and sign to stack, reset result and sign.
- If ')' → compute subexpression and apply the sign and result from stack.

Time Complexity: O(n)
- Each character is visited once.

Space Complexity: O(n)
- Stack stores signs and results during nested expressions.

This approach avoids recursion and uses constant-time operations with each character.
*/

package BinarySearchAndSorting.Hard;

public class _4_Median_of_Two_Sorted_Arrays {
    // Method to find median in two sorted array
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Pointer = 0, nums2Pointer = 0, mergedArrayPointer = 0;
        int[] mergedArray = new int[nums1.length + nums2.length];

        while (nums1Pointer < nums1.length && nums2Pointer < nums2.length) {
            if (nums1[nums1Pointer] < nums2[nums2Pointer]) {
                mergedArray[mergedArrayPointer++] = nums1[nums1Pointer++];
            } else {
                mergedArray[mergedArrayPointer++] = nums2[nums2Pointer++];
            }
        }

        while (nums1Pointer < nums1.length) {
            mergedArray[mergedArrayPointer++] = nums1[nums1Pointer++];
        }

        while (nums2Pointer < nums2.length) {
            mergedArray[mergedArrayPointer++] = nums2[nums2Pointer++];
        }

        mergedArrayPointer--;

        if ((mergedArrayPointer & 1) == 1) {
            return (double) (mergedArray[mergedArrayPointer / 2]
                    + mergedArray[(mergedArrayPointer / 2) + 1]) / 2;
        } else {
            return (double) mergedArray[mergedArrayPointer / 2];
        }

    }

    // Main method to test the findMedianSortedArrays function
    public static void main(String[] args) {
        int[] nums1 = { 1, 3 }, nums2 = { 2, 4 };

        double result = findMedianSortedArrays(nums1, nums2);

        System.out.println("The median in two sorted array is : " + result);
    }
}
