/*
LeetCode Problem: https://leetcode.com/problems/sliding-window-maximum/

Question: 239. Sliding Window Maximum

Problem Statement: You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:
Input: nums = [1], k = 1
Output: [1]

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
 */

 /*
Approach: This approach maintains the current maximum and its index while sliding the window.
- It avoids unnecessary full rescans of the window unless the current max falls outside the new window.
- It tries to update the max using just the new incoming value (or left boundary) when possible.
- If no valid max is found, it scans the current window to recompute the maximum.

Time Complexity: O(n * k)
Space Complexity: O(1)
 */
package TwoPointersAndSlidingWindow.Hard;

import java.util.Arrays;

public class _239_Sliding_Window_Maximum {

    // Method to find the maximum in each sliding window
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // Special case: if window size is 1, each element is the max of its own window
        if (k == 1) {
            return nums;
        }

        // Initialize variable for length and result
        int len = nums.length;
        int[] result = new int[len - k + 1];
        int maxValue = Integer.MIN_VALUE; // Stores the current maximum value in the window
        int index = 0; // Index of the current max
        int end; // Right boundary of the current window

        // Initialize the first window (first k elements)
        for (int i = 0; i < k; i++) {
            int n = nums[i];

            // Update the max value and its index
            if (n >= maxValue) {
                index = i;
                maxValue = n;
            }
        }

        // Store max of the first window
        result[0] = maxValue;

        // Logic to find maximum element for each of the window
        // Process the rest of the windows
        for (int i = 1; i < result.length; i++) {
            end = i + k - 1; // End index of the current window

            // Case 1: current max is still inside the window
            if (i <= index) {
                if (nums[end] >= maxValue) {
                    maxValue = nums[end];
                    index = end;
                }
            } // Case 2: current max has slid out of the window
            else {
                // Try to avoid full re-scan by checking if new or starting element is a good candidate
                if (nums[end] >= maxValue - 1) {
                    maxValue = nums[end];
                    index = end;
                } else if (nums[i] >= maxValue - 1) {
                    maxValue = nums[i];
                    index = i;
                } // Neither are good, so do full scan of the window to find new max
                else {
                    maxValue = Integer.MIN_VALUE;
                    for (int j = i; j <= end; j++) {
                        int n = nums[j];

                        // Update the max value and its index
                        if (n >= maxValue) {
                            maxValue = n;
                            index = j;
                        }
                    }
                }
            }

            // Store the max of the current window
            result[i] = maxValue;
        }

        //Return the result
        return result;
    }

    // Main method to test maxSlidingWindow
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] result = maxSlidingWindow(nums, k);

        System.out.println("The maximum in each sliding window is: " + Arrays.toString(result));
    }
}
