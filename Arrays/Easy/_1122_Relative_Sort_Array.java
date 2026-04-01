/*
LeetCode Problem: https://leetcode.com/problems/relative-sort-array/

Question: 1122. Relative Sort Array

Problem Statement: Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.

Example 1:
Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]

Example 2:
Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
Output: [22,28,8,6,17,44]

Constraints:

1 <= arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
All the elements of arr2 are distinct.
Each arr2[i] is in arr1.
 */

/*
Approach: Counting Sort (Frequency Array)

Goal:
- Sort arr1 such that:
  1. Elements in arr2 appear first in the same order.
  2. Remaining elements appear in ascending order.

Core Idea:
- Use a frequency array (bucket) since values are bounded.
- First place elements based on arr2 order using frequencies.
- Then append remaining elements in sorted order.

Algorithm Steps:
1. Initialize a frequency array (cache) for values in arr1.
2. Count occurrences of each element in arr1.
3. Traverse arr2:
   - For each value, append it cache[value] times to result.
   - Decrease frequency accordingly.
4. Traverse remaining values (0 → max):
   - Append elements still present in cache in ascending order.
5. Return the result array.

Time Complexity:
- O(n + k)
  n = size of arr1
  k = range of values (constant 1001)

Space Complexity:
- O(k)

Result:
- Returns arr1 sorted according to arr2 and remaining elements sorted ascending.
*/

package Arrays.Easy;

import java.util.Arrays;

// Solution Class
class Solution {
    // Method to sort the array customly
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Initialize the cache array for the bucket sort
        int[] cache = new int[1001];

        // Fill the cache array
        for (int i = 0; i < arr1.length; i++) {
            cache[arr1[i]]++;
        }

        // Initialize the result array
        int[] result = new int[arr1.length];

        // Initialize the index variable
        int index = 0;

        // Relatively sort the array
        for (int i = 0; i < arr2.length; i++) {
            // Get the arr2[i] value
            int value = arr2[i];

            // Fill the result array untill the bucket cache is empty
            while (cache[value] != 0) {
                // Add the value to the result array
                result[index++] = value;

                // Decrement the cache value
                cache[value]--;
            }
        }

        // Fill the rest of the array in sorted manner
        for (int i = 0; i < cache.length; i++) {
            // Fill the result array untill the bucket cache is empty
            while (cache[i] != 0) {
                // Add the value to the result array
                result[index++] = i;

                // Decrement the cache value
                cache[i]--;
            }
        }

        // Return the result array
        return result;
    }
}

public class _1122_Relative_Sort_Array {
    // Main method to test relativeSortArray
    public static void main(String[] args) {
        int[] arr1 = new int[] { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 };
        int[] arr2 = new int[] { 2, 1, 4, 3, 9, 6 };

        int[] result = new Solution().relativeSortArray(arr1, arr2);

        System.out.println("The custom sorted array is : " + Arrays.toString(result));
    }
}
