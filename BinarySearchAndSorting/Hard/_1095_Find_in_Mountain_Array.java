/*
LeetCode Problem: https://leetcode.com/problems/find-in-mountain-array/

Question: 1095. Find in Mountain Array

Problem Statement: (This problem is an interactive problem.)

You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.

You cannot access the mountain array directly. You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

Example 1:
Input: mountainArr = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:
Input: mountainArr = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.

Constraints:

3 <= mountainArr.length() <= 10^4
0 <= target <= 10^9
0 <= mountainArr.get(index) <= 10^9
*/

/*
Approach: Binary Search (Peak Finding + Order-Agnostic Search)

Goal:
- Find the index of target in a mountain array using minimum API calls.

Core Idea:
- A mountain array has:
    strictly increasing part → peak → strictly decreasing part.
- First, locate the peak using binary search.
- Then perform:
    1. Binary search on increasing (left) part.
    2. Binary search on decreasing (right) part (reverse logic).

Algorithm Steps:
1. Find peak index:
   - Compare arr[mid] and arr[mid + 1].
   - If increasing → move right.
   - Else → move left.
2. Search in left (ascending) part:
   - Standard binary search.
3. If not found, search in right (descending) part:
   - Reverse comparison logic.
4. Return index if found, else -1.

Time Complexity:
- O(log n)

Space Complexity:
- O(1)

Result:
- Returns the index of target in the mountain array,
  or -1 if not found.
*/

package BinarySearchAndSorting.Hard;

/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 * public int get(int index) {}
 * public int length() {}
 * }
 */

// MountainArray Class
class MountainArray {
    private final int[] arr;

    public MountainArray(int[] arr) {
        this.arr = arr;
    }

    public int get(int index) {
        return this.arr[index];
    }

    public int length() {
        return this.arr.length;
    }
}

// Solution Class
class Solution {
    // Method to find the first index of the target
    public int findInMountainArray(int target, MountainArray mountainArr) {
        // Get the length of the MountainArray array
        int length = mountainArr.length();

        // Initialize the left and right pointer
        int left = 0, right = length - 1;

        // Initialize the peak element
        int peakElement;

        // Iterate over the bound
        while (left < right) {
            // Get the mid value
            int mid = left + (right - left) / 2;

            // Get the two values
            int second = mountainArr.get(mid);
            int third = mountainArr.get(mid + 1);

            if (second < third) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        peakElement = left;

        // Update the left and right varaible
        left = 0;
        right = peakElement;

        // Find the element in the left array
        while (left <= right) {
            // Get the mid value
            int mid = left + (right - left) / 2;

            // Get the mid value of the array
            int middle = mountainArr.get(mid);

            if (middle < target) {
                left = mid + 1;
            } else if (middle > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        // Update the left and right varaible
        left = peakElement;
        right = length - 1;

        // Find the element in the left array
        while (left <= right) {
            // Get the mid value
            int mid = left + (right - left) / 2;

            // Get the mid value of the array
            int middle = mountainArr.get(mid);

            if (middle < target) {
                right = mid - 1;
            } else if (middle > target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        // Return -1 in the end
        return -1;
    }
}

public class _1095_Find_in_Mountain_Array {
    // Main method to test findInMountainArray
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 3, 1 };

        int target = 3;
        MountainArray mountainArr = new MountainArray(arr);

        int result = new Solution().findInMountainArray(target, mountainArr);

        System.out.println("The first index of the target" + target + " is : " + result);
    }
}
