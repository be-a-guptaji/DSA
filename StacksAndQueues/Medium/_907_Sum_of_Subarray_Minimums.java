/*
LeetCode Problem: https://leetcode.com/problems/sum-of-subarray-minimums/

Question: 907. Sum of Subarray Minimums

Problem Statement: Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 10^9 + 7.

Example 1:
Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.

Example 2:
Input: arr = [11,81,94,43,3]
Output: 444

Constraints:

1 <= arr.length <= 3 * 10^4
1 <= arr[i] <= 3 * 10^4
 */

/*
Approach: Monotonic Stack via DP (Previous Smaller Element Jumping)

Goal:
- Compute the sum of minimum values of all contiguous subarrays.

Core Idea:
- For each element, determine how many subarrays end at index i
  where it is the minimum.
- Use:
    left[i] → index of previous smaller element.
    sums[i] → sum of subarray minimums ending at i.
- Reuse previously computed results to avoid recomputation.

Algorithm Steps:
1. Use 1-based indexing for easier boundary handling.
2. For each index i:
   - Move left while previous elements >= current.
   - Store index of previous smaller element in left[i].
3. Compute:
   sums[i] = sums[left[i]] + arr[i] * (i - left[i])
4. Add sums[i] to final result with modulo.
5. Return result.

Time Complexity:
- O(n) (each element is processed once via jumps)

Space Complexity:
- O(n)

Result:
- Returns sum of minimums of all contiguous subarrays (mod 1e9+7).
*/

package StacksAndQueues.Medium;

public class _907_Sum_of_Subarray_Minimums {
    // Method to find the sum of min(b), where b ranges over every (contiguous)
    // subarray of arr
    public static int sumSubarrayMins(int[] arr) {
        // Initialize the modulo value
        int mod = (int) 1000000007;

        // Get the length of the array
        int n = arr.length;

        // Initialize the left array to store the index of previous smaller element
        int[] left = new int[n + 1];

        // Initialize the right array to store values (1-based indexing)
        int[] right = new int[n + 1];

        // Initialize the sums array to store
        // sum of subarray minimums ending at index i
        int[] sums = new int[n + 1];

        // Copy arr into right array using 1-based indexing
        for (int i = 0; i < n; ++i) {
            right[i + 1] = arr[i];
        }

        // Initialize the result variable
        int res = 0;

        // Iterate over the array (1-based index)
        for (int i = 1; i < n + 1; i++) {
            // Get the current element
            int cur = right[i];

            // Start checking from the previous index
            int j = i - 1;

            // Move left while previous elements are greater than or equal to current
            while (right[j] >= cur) {
                j = left[j];
            }

            // Store the index of previous smaller element
            left[i] = j;

            // Calculate sum of subarrays ending at i where current element is the minimum
            sums[i] = sums[j] + cur * (i - j);

            // Add to final result with modulo
            res = (res + sums[i]) % mod;
        }

        // Return the final result
        return res;
    }

    // Main method to test sumSubarrayMins
    public static void main(String[] args) {
        int[] arr = new int[] { 3, 1, 2, 4 };

        int result = sumSubarrayMins(arr);

        System.out.println("The sum of min(b), where b ranges over every (contiguous) subarray of arr is : " + result);
    }
}
