/*
LeetCode Problem: https://leetcode.com/problems/spiral-matrix/

Question: 54. Spiral Matrix

Problem Statement: Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
*/

/*
Approach: Boundary Simulation (Layer-by-Layer Traversal)

Goal:
Return all elements of a matrix in spiral order.

Key Idea:
Traverse the matrix layer by layer while shrinking the boundaries after
each direction is processed.

Boundaries:
- top    → starting row
- bottom → ending row (exclusive)
- left   → starting column
- right  → ending column (exclusive)

Algorithm:
1. Initialize boundaries:
   left = 0, right = number of columns
   top = 0, bottom = number of rows

2. While left < right AND top < bottom:
   a) Traverse top row from left → right
      - Increment top
   b) Traverse right column from top → bottom
      - Decrement right
   c) If boundaries still valid:
      - Traverse bottom row from right → left
        Decrement bottom
      - Traverse left column from bottom → top
        Increment left

3. Continue until all layers are processed.

Why It Works:
- Each iteration processes one outer “ring” of the matrix.
- Boundary checks prevent duplicate traversal when rows/columns collapse.

Time Complexity: O(m × n)  
Space Complexity: O(1) extra space (excluding output list)
*/

package Maths.Medium;

import java.util.ArrayList;
import java.util.List;

public class _54_Spiral_Matrix {
    // Method to find the sprial order of the matrix
    public static List<Integer> spiralOrder(int[][] matrix) {
        // Handle empty matrix case
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }

        // Get the row and columns of the matrix
        int left = 0, right = matrix[0].length, top = 0, bottom = matrix.length;

        // Initialize the list of the interger for the result
        ArrayList<Integer> result = new ArrayList<>();

        // Iterate over the matrix
        while (left < right && top < bottom) {

            // Iterate over the top row left to right
            for (int i = left; i < right; i++) {
                // Fill the result to the matrix values
                result.add(matrix[top][i]);
            }

            // Increment the top value
            top++;

            // Iterate over the right side column
            for (int i = top; i < bottom; i++) {
                // Fill the result to the matrix values
                result.add(matrix[i][right - 1]);
            }

            // Decrement the right value
            right--;

            // If we iterate over the all the value then return
            if (!(left < right && top < bottom)) {
                break;
            }

            // Iterate over the bottom row right to left
            for (int i = right - 1; i >= left; i--) {
                // Fill the result to the matrix values
                result.add(matrix[bottom - 1][i]);
            }

            // Decrement the bottom value
            bottom--;

            // Iterate over the left side column
            for (int i = bottom - 1; i >= top; i--) {
                // Fill the result to the matrix values
                result.add(matrix[i][left]);
            }

            // Increment the left value
            left++;
        }

        // Return the arraylist
        return result;
    }

    // Main method to test spiralOrder
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 5, 1, 9, 11 },
                { 2, 4, 8, 10 },
                { 13, 3, 6, 7 },
                { 15, 14, 12, 16 }
        };

        List<Integer> result = spiralOrder(matrix);

        System.out.println("The sprial order of the matrix is : " + result);
    }
}
