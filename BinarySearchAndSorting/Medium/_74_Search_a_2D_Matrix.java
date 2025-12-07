/*
LeetCode Problem: https://leetcode.com/problems/search-a-2d-matrix/

Question: 74. Search a 2D Matrix

Problem Statement: You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-10^4 <= matrix[i][j], target <= 10^4
*/

/*
Approach: Two-Level Binary Search

This problem is solved using two separate binary searches:

Step 1: Binary Search on Rows
- Each row is sorted and the first element of each row is also sorted.
- We search on the first column to find the row that may contain the target.
- If matrix[mid][0] == target → return true.
- If matrix[mid][0] < target → search downward.
- If matrix[mid][0] > target → search upward.

Step 2: Binary Search on the Selected Row
- Once the correct row is identified, perform standard binary search on that row.
- If the target is found → return true.
- Otherwise → return false.

Edge Handling:
- If the selected row’s first element is greater than target, we move one row up.

Time Complexity: O(log m + log n)
Space Complexity: O(1)
*/

package BinarySearchAndSorting.Medium;

public class _74_Search_a_2D_Matrix {
    // Method to find the target in the matrix
    public static boolean searchMatrix(int[][] matrix, int target) {
        // Get row and the column of the matrix
        int row = matrix.length, column = matrix[0].length;

        // Initialize the start and end variable
        int start = 0, end = row - 1;

        // Check the value in the rows
        while (start <= end) {
            // Get the middle row of the matrix
            int middle = (start + end) / 2;

            // Get the current row
            row = middle;

            // Update the variable according the middle variable
            if (matrix[middle][0] == target) {
                return true;
            } else if (matrix[middle][0] < target) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        // Update the start and end variable
        start = 0;
        end = column - 1;

        // Decrement the row value if the first index is greater than the target and row
        // is not equal to the zero
        if (matrix[row][0] > target && row != 0) {
            row--;
        }

        // Initialize the array variable to get the row of the matrix
        int[] array = matrix[row];

        // Check the value in the rows
        while (start <= end) {
            // Get the middle row of the matrix
            int middle = (start + end) / 2;

            // Update the variable according the middle variable
            if (array[middle] == target) {
                return true;
            } else if (array[middle] < target) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        // Return false in the end
        return false;
    }

    // Main method to test searchMatrix
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };
        int target = 11;

        boolean result = searchMatrix(matrix, target);

        System.out.println("The target " + target + (result ? " is " : " is not ") + "present in the matrix.");
    }
}
