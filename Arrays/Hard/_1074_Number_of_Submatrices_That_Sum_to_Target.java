/*
LeetCode Problem: https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/

Question: 1074. Number of Submatrices That Sum to Target

Problem Statement: Given a matrix and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

Example 1:
Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.

Example 2:
Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.

Example 3:
Input: matrix = [[904]], target = 0
Output: 0

Constraints:
1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i][j] <= 1000
-10^8 <= target <= 10^8
*/

/*
Approach: 2D Prefix Sum + Subarray Sum Counting

Goal:
- Count the number of non-empty submatrices
  whose sum equals target.

Core Idea:
- Fix two rows:
    row1 and row2
- Compress the matrix between these rows into
  a 1D array of column sums.
- Problem reduces to:
    "Count subarrays with sum = target"
- Use prefix sums + HashMap frequency counting.

Algorithm Steps:
1. Build 2D prefix sum matrix.
2. Fix top row row1.
3. Fix bottom row row2.
4. For each column:
   - Compute compressed column sum between row1 and row2.
5. Use HashMap:
   - key   = prefix sum
   - value = frequency
6. Count subarrays whose sum equals target:
   currentSum - previousSum = target
7. Accumulate result.
8. Return total count.

Time Complexity:
- O(ROWS² * COLS)

Space Complexity:
- O(COLS)

Result:
- Returns total number of submatrices
  with sum equal to target.
*/

package Arrays.Hard;

import java.util.HashMap;

// Solution Class
class Solution {
    // Method to find the number of non-empty submatrices that sum to target
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        // Initialize the ROWS and COLS of the matrix
        final int ROWS = matrix.length, COLS = matrix[0].length;

        // Initialize the prefixSum matrix
        int[][] prefixSum = new int[ROWS][COLS];

        // Fill the prefixSum matrix
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int top = (row > 0) ? prefixSum[row - 1][col] : 0;
                int left = (col > 0) ? prefixSum[row][col - 1] : 0;
                int topLeft = (Math.min(row, col) > 0) ? prefixSum[row - 1][col - 1] : 0;

                prefixSum[row][col] = matrix[row][col] + top + left - topLeft;
            }
        }

        // Initialize the result varaible
        int result = 0;

        // Initialize the hashmap for the difference
        HashMap<Integer, Integer> count = new HashMap<>();

        // Get the matrix which sums to the target
        for (int row1 = 0; row1 < ROWS; row1++) {
            for (int row2 = row1; row2 < ROWS; row2++) {
                // Clear the hashmap
                count.clear();

                // Set the zero to one
                count.put(0, 1);

                // Iterate over the cols
                for (int col = 0; col < COLS; col++) {
                    // Get the current sum
                    int currentSum = prefixSum[row2][col] - ((row1 > 0) ? prefixSum[row1 - 1][col] : 0);

                    // Update the result
                    result += count.getOrDefault(currentSum - target, 0);

                    // Update the hashmap
                    count.put(currentSum, count.getOrDefault(currentSum, 0) + 1);
                }
            }
        }

        // Return the result
        return result;
    }
}

public class _1074_Number_of_Submatrices_That_Sum_to_Target {
    // Main method to test the numSubmatrixSumTarget
    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } };
        int target = 3;

        int result = new Solution().numSubmatrixSumTarget(matrix, target);

        System.out.println("The number of non-empty submatrices that sum to target is : " + result);
    }
}
