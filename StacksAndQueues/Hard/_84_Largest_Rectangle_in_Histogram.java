/*
LeetCode Problem: https://leetcode.com/problems/largest-rectangle-in-histogram/

Question: 84. Largest Rectangle in Histogram

Problem Statement: Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:
Input: heights = [2,4]
Output: 4

Constraints:

1 <= heights.length <= 10^5
0 <= heights[i] <= 10^4
 */

/*
Approach: Monotonic Increasing Stack

1. Use a stack to store indices of histogram bars in strictly increasing height order.
   - This helps identify the nearest smaller bar on the left and right for each bar.

2. Iterate through all indices from 0 to heights.length,
   and include one extra iteration where the height is treated as 0.
   - This ensures all remaining bars in the stack are processed.

3. For each index `i`, get the currentHeight:
   - If i == heights.length, currentHeight = 0 (forces cleanup)
   - Else currentHeight = heights[i]

4. While the stack is not empty and the bar at the top has height greater than currentHeight:
   - Pop the top index → this is the height that will form a rectangle.
   - Let poppedHeight = heights[poppedIndex].

5. Compute the width for poppedHeight:
   - If the stack becomes empty after popping:
       width = i  (extends from 0 to i - 1)
   - Otherwise:
       width = i - stack.peek() - 1  
       (distance between the next smaller element on left and current position)

6. Calculate area = poppedHeight * width and update largestArea.

7. Push the current index `i` onto the stack to maintain the monotonic increasing order.

8. After completing the loop, largestArea will hold the maximum rectangle size.

Time Complexity: O(n)  
Space Complexity: O(n)  
*/

package StacksAndQueues.Hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class _84_Largest_Rectangle_in_Histogram {
    // Method to find the largest rectangle in the histogram
    public static int largestRectangleArea(int[] heights) {
        // Initialize variable to store the maximum area
        int largestArea = 0;

        // Initialize the stack for holding the indices of monotonic increasing heights
        Deque<Integer> stack = new ArrayDeque<>();

        // Iterate over all heights including one extra iteration for final cleanup
        for (int i = 0; i <= heights.length; i++) {

            // Get the current height or 0 for the final cleanup step
            int currentHeight = (i == heights.length) ? 0 : heights[i];

            // Pop until the stack maintains a strictly increasing height order
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                // Get the height at that index which is at the top of the stack
                int poppedHeight = heights[stack.pop()];

                // Determine the width of the rectangle: If the stack is empty, width extends
                // from 0 to i Else width is between the current index `i` and new stack top
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                // Update the largest area using the popped height and calculated width
                largestArea = Math.max(largestArea, poppedHeight * width);
            }

            // Add the current index to the stack to maintain the monotonic increasing order
            stack.push(i);
        }

        // Return the largest rectangle area found
        return largestArea;
    }

    // Main method to test largestRectangleArea
    public static void main(String[] args) {
        int[] heights = { 2, 1, 5, 6, 2, 3 };

        int result = largestRectangleArea(heights);

        System.out.println("The largest rectangle in the histogram is : " + result);
    }
}
