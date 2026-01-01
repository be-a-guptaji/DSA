/*
LeetCode Problem: https://leetcode.com/problems/rotate-image/

Question: 48. Rotate Image

Problem Statement: You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
*/

/*
Approach: Transpose + Reverse Rows (In-Place)

Goal:
Rotate an n × n matrix 90 degrees clockwise in-place.

Key Insight:
A 90° clockwise rotation can be achieved by:
1) Transposing the matrix.
2) Reversing each row.

Algorithm:
1. Transpose the matrix:
   - Swap matrix[i][j] with matrix[j][i] for j < i.
   - Converts rows into columns.

2. Reverse each row:
   - Swap elements symmetrically from left to right.
   - Completes the clockwise rotation.

Why It Works:
- Transpose aligns elements correctly across the diagonal.
- Row reversal produces the required orientation.

Time Complexity: O(n²)  
Space Complexity: O(1) (in-place)
*/

package Maths.Medium;

import java.util.Arrays;

public class _48_Rotate_Image {
    // Method to rotate the matrix
    public static void rotate(int[][] matrix) {
        // Get the length of the matrix
        int n = matrix.length;

        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Swap the variables
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                matrix[j][i] = matrix[i][j] ^ matrix[j][i];
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
            }
        }

        // Iterate over the half of the length of the matrix
        for (int i = 0; i < n; i++) {
            // Get the matrix row
            int[] row = matrix[i];

            // Swap the values
            for (int j = 0; j < n / 2; j++) {
                row[j] = row[j] ^ row[n - j - 1];
                row[n - j - 1] = row[j] ^ row[n - j - 1];
                row[j] = row[j] ^ row[n - j - 1];
            }
        }
    }

    // Main method to test isHappy
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 5, 1, 9, 11 },
                { 2, 4, 8, 10 },
                { 13, 3, 6, 7 },
                { 15, 14, 12, 16 }
        };

        rotate(matrix);

        System.out.println("The rotated matrix is : " + Arrays.deepToString(matrix));
    }
}
