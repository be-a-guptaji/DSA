/*
LeetCode Problem: https://leetcode.com/problems/range-sum-query-2d-immutable/

Question: 304. Range Sum Query 2D - Immutable

Problem Statement: Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
You must design an algorithm where sumRegion works on O(1) time complexity.

Example 1:
Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]
Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-10^4 <= matrix[i][j] <= 10^4
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 10^4 calls will be made to sumRegion.
 */

/*
Approach: 2D Prefix Sum (Integral Matrix)

Goal:
- Compute sum of elements in any submatrix in O(1) time.

Core Idea:
- Build a 2D prefix sum matrix where:
    prefixSum[i][j] represents sum of all elements
    in rectangle (0,0) → (i-1, j-1).
- Use inclusion-exclusion principle to answer queries.

Algorithm Steps:
1. Preprocessing:
   - Create prefixSum of size (m+1) x (n+1).
   - For each cell:
       prefixSum[i+1][j+1] =
           matrix[i][j]
         + prefixSum[i][j+1]
         + prefixSum[i+1][j]
         - prefixSum[i][j]
2. Query:
   - sumRegion(row1, col1, row2, col2) =
       prefixSum[row2+1][col2+1]
     - prefixSum[row1][col2+1]
     - prefixSum[row2+1][col1]
     + prefixSum[row1][col1]

Time Complexity:
- Preprocessing: O(m * n)
- Query: O(1)

Space Complexity:
- O(m * n)

Result:
- Returns sum of elements in the given submatrix efficiently.
*/

package Arrays.Medium;

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

// NumMatrix Class
class NumMatrix {
    // Initialize the prefix sum variable
    final private int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        // Initialize the prefix sum
        this.prefixSum = new int[matrix.length + 1][matrix[0].length + 1];

        // Fill the prefix sum
        for (int i = 0; i < matrix.length; i++) {
            // Initialize the sum variable
            int sum = 0;

            // Find the prefix sum
            for (int j = 0; j < matrix[0].length; j++) {
                sum += matrix[i][j];
                this.prefixSum[i + 1][j + 1] = sum + this.prefixSum[i][j + 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return this.prefixSum[row2 + 1][col2 + 1] - this.prefixSum[row1][col2 + 1] - this.prefixSum[row2 + 1][col1]
                + this.prefixSum[row1][col1];
    }
}

public class _304_Range_Sum_Query_2D_Immutable {
    // Main method to test customSortString
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 3, 0, 1, 4, 2 },
                { 5, 6, 3, 2, 1 },
                { 1, 2, 0, 1, 5 },
                { 4, 1, 0, 1, 7 },
                { 1, 0, 3, 0, 5 }
        };

        NumMatrix result = new NumMatrix(matrix);

        int[][] operations = new int[][] { { 2, 1, 4, 3 }, { 1, 1, 2, 2 }, { 1, 2, 2, 4 } };

        for (int[] arr : operations) {
            System.out.print(result.sumRegion(arr[0], arr[1], arr[2], arr[3]) + " ");
        }
    }
}
