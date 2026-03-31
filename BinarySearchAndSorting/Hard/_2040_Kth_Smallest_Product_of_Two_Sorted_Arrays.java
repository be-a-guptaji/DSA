/*
LeetCode Problem: https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/

Question: 2040. Kth Smallest Product of Two Sorted Arrays

Problem Statement: Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.

Example 1:
Input: nums1 = [2,5], nums2 = [3,4], k = 2
Output: 8
Explanation: The 2 smallest products are:
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
The 2nd smallest product is 8.

Example 2:
Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
Output: 0
Explanation: The 6 smallest products are:
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
The 6th smallest product is 0.

Example 3:
Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
Output: -6
Explanation: The 3 smallest products are:
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
The 3rd smallest product is -6.

Constraints:

1 <= nums1.length, nums2.length <= 5 * 10^4
-10^5 <= nums1[i], nums2[j] <= 10^5
1 <= k <= nums1.length * nums2.length
nums1 and nums2 are sorted.
*/

/*
Approach: Binary Search on Answer (Counting with Sign-Based Partitioning)

Goal:
- Find the k-th smallest product among all pairs
  nums1[i] * nums2[j].

Core Idea:
- The product space includes negative, zero, and positive values.
- Use binary search over possible product values.
- For a candidate value x, count how many pairs have product ≤ x.
- Handle three cases for nums1[i]:
    1. Positive → use upper bound on nums2.
    2. Negative → use lower bound (reverse inequality).
    3. Zero → contributes all pairs if x ≥ 0.

Algorithm Steps:
1. Define search range:
   - left = very small value (e.g., -1e10)
   - right = very large value (e.g., 1e10)
2. Binary search:
   - mid = candidate product.
3. Count pairs ≤ mid:
   - For each element in nums1:
       a. If > 0 → count nums2[j] ≤ mid / nums1[i].
       b. If < 0 → count nums2[j] ≥ ceil(mid / nums1[i]).
       c. If == 0 → if mid ≥ 0, count all nums2.
4. If count < k:
   - Move left = mid + 1.
5. Else:
   - Move right = mid - 1.
6. Return left.

Time Complexity:
- O(n log m log R)
  n = size of nums1, m = size of nums2,
  R = value range of products

Space Complexity:
- O(1)

Result:
- Returns the k-th smallest product.
*/

package BinarySearchAndSorting.Hard;

// Solution Class
class Solution {
    // Method to find the kth (1-based) smallest product of nums1[i] * nums2[j]
    // where 0 <= i < nums1.length and 0 <= j < nums2.length
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // Initialize the left and right bound
        long left = -1_00_00_00_00_00l, right = 1_00_00_00_00_00l;

        // Iterate over the bound
        while (left <= right) {
            // Get the middle element
            long mid = left + (right - left) / 2;

            // Update the pointers accordingly
            if (this.count(nums1, nums2, mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Return left in the end
        return left;
    }

    // Helper method to find the total count of the products
    private long count(int[] nums1, int[] nums2, long k) {
        // Initialize the count variable
        long count = 0;

        // Iterate over the nums1 array for finding the count
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] > 0) {
                count += this.upperBound(nums2, Math.floorDiv(k, nums1[i]));
            } else if (nums1[i] < 0) {
                count += nums2.length - this.lowerBound(nums2, Math.ceilDiv(k, nums1[i]));
            } else if (k >= 0) {
                count += nums2.length;
            }
        }

        // Return the count
        return count;
    }

    // Helper method to find the upper bound of the product
    private long upperBound(int[] arr, long target) {
        // Initialize the left and right bound
        long left = 0, right = arr.length;

        // Iterate over the bound to find the middle element
        while (left < right) {
            // Get the middle element
            long mid = left + (right - left) / 2;

            // Update the bound accordingly
            if (arr[(int) mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Return the left in the end
        return left;
    }

    // Helper method to find the total count of the products
    private long lowerBound(int[] arr, long target) {
        // Initialize the left and right bound
        long left = 0, right = arr.length;

        // Iterate over the bound to find the middle element
        while (left < right) {
            // Get the middle element
            long mid = left + (right - left) / 2;

            // Update the bound accordingly
            if (arr[(int) mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Return the left in the end
        return left;
    }
}

public class _2040_Kth_Smallest_Product_of_Two_Sorted_Arrays {
    // Main method to test kthSmallestProduct
    public static void main(String[] args) {
        int[] nums1 = new int[] { -2, -1, 0, 1, 2 };
        int[] nums2 = new int[] { -3, -1, 2, 4, 5 };
        long k = 3;

        long result = new Solution().kthSmallestProduct(nums1, nums2, k);

        System.out.println(
                "The kth (1-based) smallest product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length is : "
                        + result);
    }
}
