/*
LeetCode Problem: https://leetcode.com/problems/maximal-rectangle/

Question: 85. Maximal Rectangle

Problem Statement: Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:
Input: matrix = [["0"]]
Output: 0

Example 3:
Input: matrix = [["1"]]
Output: 1

Constraints:

rows == matrix.length
cols == matrix[i].length
1 <= rows, cols <= 200
matrix[i][j] is '0' or '1'.
 */

/*
Approach:
This problem is solved using the concept of histograms and stacks.

We treat each row of the matrix as the base of a histogram:
- Each element in the histogram represents the height of consecutive '1's in that column up to the current row.
- For each row, we calculate the "Largest Rectangle in Histogram" (LeetCode #84) and take the maximum area.

Key Ideas:
1. If matrix[i][j] == '1', increment histogram[j] by 1 (height increases).
   Otherwise, reset histogram[j] = 0.
2. For each updated histogram row, use a stack-based approach to find the
   maximal rectangular area (similar to Largest Rectangle in Histogram).
3. Maintain and update a global maximum area.

Algorithm for `maximalAreaOfHistogram`:
- Use a stack to store indices of histogram bars.
- When the current bar is smaller than the bar at the top of the stack,
  calculate the area with the height of the popped bar as the smallest bar.
- The width is determined by the difference between the current index
  and the index on top of the stack after popping (or the start if the stack is empty).

Base Case:
If the matrix is empty, return 0.

Time Complexity: O(rows × cols)
Space Complexity: O(cols)
where rows = number of rows in the matrix, cols = number of columns.
*/

package DynamicProgramming.Hard;

import java.util.Stack;

public class _85_Maximal_Rectangle {
    // Method to find the largest area rectangle containing only 1's
    public static int maximalRectangle(char[][] matrix) {
        // Initialize the max area
        int maxArea = 0;

        // Get the size of the inner array of the matrix
        int size = matrix[0].length;

        // Initialize the new histograms array
        int[] histograms = new int[size];

        // Get the max area for all the rows treating each row as a histograms
        for (char[] arr : matrix) {
            for (int i = 0; i < size; i++) {
                // If the array has the value '1' then increase the height of the histogram else
                // set it to 0
                if (arr[i] == '1') {
                    histograms[i]++;
                } else {
                    histograms[i] = 0;
                }
            }

            // Upadate the max area to the max value for the area
            maxArea = Math.max(maxArea, maximalAreaOfHistogram(histograms));
        }

        // Return the max area
        return maxArea;
    }

    // Helper method to find the area of the histogram
    private static int maximalAreaOfHistogram(int[] histograms) {
        // Initialize the max area and the length of the histograms
        int maxArea = 0;
        int n = histograms.length;

        // Intialize the stack for holding the positions of the histograms
        Stack<Integer> stack = new Stack<>();

        // Iterate over the histograms to get the maximal area
        for (int i = 0; i <= n; i++) {
            // Get the area of the histogram from the remaing stack
            while (!stack.isEmpty() && (i == n || histograms[stack.peek()] >= histograms[i])) {
                // Get the smallest height of the histogram
                int height = histograms[stack.peek()];

                // Pop the top postion form the stack
                stack.pop();

                // Intialize the width of the rectangle
                int width;

                // Initilize the width of the reactangle
                if (stack.isEmpty()) {
                    width = 1;
                } else {
                    width = i - stack.size() - 1;
                }

                // Upadate the max area to the max value for the area
                maxArea = Math.max(maxArea, height * width);
            }

            // Push the current position of to the stack
            stack.push(i);
        }

        // Return the max area
        return maxArea;
    }

    // Main method to test maximalRectangle
    public static void main(String[] args) {
        char[][] matrix = {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' }
        };

        int result = maximalRectangle(matrix);

        System.out.println("The largest area rectangle containing only 1's is : " + result);
    }
}
