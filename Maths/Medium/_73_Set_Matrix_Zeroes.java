/*
LeetCode Problem: https://leetcode.com/problems/set-matrix-zeroes/

Question: 73. Set Matrix Zeroes

Problem Statement: Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-2^31 <= matrix[i][j] <= 2^31 - 1

Follow up:

A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

/*
Approach: In-Place Matrix Marking Using First Row and First Column

Goal:
If any cell in the matrix is 0, set its entire row and column to 0,
while using constant extra space.

Key Idea:
Reuse the first row and first column as marker storage instead of using
extra arrays.

Algorithm:

1. First Pass (Marking):
   - Traverse the entire matrix.
   - When matrix[i][j] == 0:
     • Mark column j by setting matrix[0][j] = 0.
     • If i == 0, record that the first row needs to be zeroed using rowZero.
     • Otherwise, mark row i by setting matrix[i][0] = 0.

2. Second Pass (Zero Inner Matrix):
   - Traverse from row 1 and column 1 onward.
   - If matrix[i][0] == 0 OR matrix[0][j] == 0:
     → set matrix[i][j] = 0.

3. Handle First Column:
   - If matrix[0][0] == 0, set the entire first column to zero.

4. Handle First Row:
   - If rowZero is true, set the entire first row to zero.

Why It Works:
- The first row and column efficiently store which rows/columns must be zeroed.
- Separate handling avoids overwriting marker information prematurely.

Time Complexity: O(m × n)  
Space Complexity: O(1)
*/

package Maths.Medium;

import java.util.Arrays;

public class _73_Set_Matrix_Zeroes {
    // Method to set entire row and column to zero if an element is zero
    public static void setZeroes(int[][] matrix) {

        // Get the number of rows and columns in the matrix
        final int ROWS = matrix.length, COLUMNS = matrix[0].length;

        // Flag to track whether the first row needs to be set to zero
        boolean rowZero = false;

        // First pass: use first row and first column as markers
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {

                // If a zero is found in the matrix
                if (matrix[i][j] == 0) {

                    // Mark the column in the first row
                    matrix[0][j] = 0;

                    // If the zero is in the first row, remember it separately
                    if (i == 0) {
                        rowZero = true;
                    }
                    // Otherwise, mark the row in the first column
                    else {
                        matrix[i][0] = 0;
                    }
                }
            }
        }

        // Second pass: update inner matrix using row and column markers
        for (int i = 1; i < ROWS; i++) {
            for (int j = 1; j < COLUMNS; j++) {

                // If the row or column is marked, set the cell to zero
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // If the first column was marked (via matrix[0][0]),
        // set the entire first column to zero
        if (matrix[0][0] == 0) {
            for (int i = 0; i < ROWS; i++) {
                matrix[i][0] = 0;
            }
        }

        // If the first row originally contained a zero,
        // set the entire first row to zero
        if (rowZero) {
            for (int j = 0; j < COLUMNS; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    // Main method to test setZeroes
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 0, 1, 2, 0 },
                { 3, 4, 5, 2 },
                { 1, 3, 1, 5 }
        };

        setZeroes(matrix);

        System.out.println("The matrix row and columns is seted to zero : " + Arrays.deepToString(matrix));
    }
}
