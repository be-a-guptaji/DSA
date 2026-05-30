/*
LeetCode Problem: https://leetcode.com/problems/majority-element-ii/

Question: 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

Problem Statement: Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and average greater than or equal to threshold.

Example 1:
Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
Output: 3
Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).

Example 2:
Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
Output: 6
Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 10^4
1 <= k <= arr.length
0 <= threshold <= 10^4
*/

/*
Approach: Fixed-Size Sliding Window

Goal:
- Count subarrays of size k whose average
  is greater than or equal to threshold.

Core Idea:
- Instead of recomputing sums for every subarray,
  maintain a sliding window sum of size k.
- Average condition:
      sum / k >= threshold
- Equivalent to:
      sum >= k * threshold

Algorithm Steps:
1. Compute sum of first window of size k.
2. Check threshold condition and update result.
3. Slide the window:
   - Remove outgoing element.
   - Add incoming element.
4. For each window:
   - Check if average meets threshold.
5. Return result.

Time Complexity:
- O(n)

Space Complexity:
- O(1)

Optimization Note:
- Avoid repeated division:
    compare sumOfWindow >= k * threshold
  for slightly better performance.

Result:
- Returns count of valid subarrays.
*/

package TwoPointersAndSlidingWindow.Medium;

// Solution Class
class Solution {
  // Method to find the number of sub-arrays of size k and average greater than or
  // equal to threshold
  public int numOfSubarrays(int[] arr, int k, int threshold) {
    // Initialize the result variable
    int result = 0;

    // Initialize the sum of window varible
    int sumOfWindow = 0;

    // Iterate over the window to get the average
    for (int i = 0; i < k; i++) {
      sumOfWindow += arr[i];
    }

    // If average of window is greater than the threshold then increment the result
    if (sumOfWindow / k >= threshold) {
      result++;
    }

    // Iterate over the arr array
    for (int i = k; i < arr.length; i++) {
      sumOfWindow = sumOfWindow - arr[i - k] + arr[i];

      // Update the result if average is greater then the threshold
      if (sumOfWindow / k >= threshold) {
        result++;
      }
    }

    // Return the result
    return result;
  }
}

public class _1343_Number_of_Sub_arrays_of_Size_K_and_Average_Greater_than_or_Equal_to_Threshold {
  // Main method to test numOfSubarrays
  public static void main(String[] args) {
    int[] arr = new int[] { 2, 2, 2, 2, 5, 5, 5, 8 };
    int k = 3;
    int threshold = 4;

    int result = new Solution().numOfSubarrays(arr, k, threshold);

    System.out
        .println("The number of sub-arrays of size k and average greater than or equal to threshold is : " + result);
  }
}
