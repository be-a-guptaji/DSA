/*
LeetCode Problem: https://leetcode.com/problems/daily-temperatures/

Question: 739. Daily Temperatures

Problem Statement: Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]

Constraints:

1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100
*/

/*
  Approach:
  The problem is solved using a Monotonic Stack (a stack that stores indices of temperatures 
  in decreasing order of their values).

  - We traverse the temperatures array from left to right.
  - For each day (index `i`), we check if the current temperature is greater than the temperature 
    at the index stored at the top of the stack.
  - If it is, it means we've found a warmer day for the day at the top of the stack:
      - Pop that index from the stack.
      - Calculate the difference between current day and that day: (i - popped index).
      - Store this difference in the result array.
  - After processing all warmer days for the current temperature, push the current index `i` onto the stack.
  - At the end of the loop, all indices left in the stack will have no warmer future day, so their result remains 0.

  This approach ensures each index is pushed and popped at most once, leading to an efficient linear time solution.

  Time Complexity:  O(n), where n is the length of the input array.
  Space Complexity: O(n), due to the stack and result array.
*/

package Arrays.Medium;

import java.util.Arrays;

public class _739_Daily_Temperatures {
  // Method to compute number of days to wait for a warmer temperature
  public static int[] dailyTemperatures(int[] temperatures) {
    // Initialize variables
    int n = temperatures.length, stack_pointer = -1;
    int[] result = new int[n], stack = new int[n];

    // Iterate over the temprature array
    for (int i = 0; i < n; i++) {
      // Check if the last value has the lower temprature than the current temprature
      while (stack_pointer != -1 && temperatures[stack[stack_pointer]] < temperatures[i]) {
        result[stack[stack_pointer]] = i - stack[stack_pointer];
        stack_pointer--;
      }
      stack_pointer++;
      stack[stack_pointer] = i;
    }

    // Return the result
    return result;
  }

  // Main method to test dailyTemperatures
  public static void main(String[] args) {
    int[] nums = { 73, 74, 75, 71, 69, 72, 76, 73 };

    int[] result = dailyTemperatures(nums);

    System.out
        .println("The days of wait for the warmer day with respect to their index are : " + Arrays.toString(result));
  }
}
