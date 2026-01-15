/*
LeetCode Problem: https://leetcode.com/problems/find-missing-and-repeated-values/

Question: 2965. Find Missing and Repeated Values

Problem Statement: You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n2]. Each integer appears exactly once except a which appears twice and b which is missing. The task is to find the repeating and missing numbers a and b.

Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.

Example 1:
Input: grid = [[1,3],[2,2]]
Output: [2,4]
Explanation: Number 2 is repeated and number 4 is missing so the answer is [2,4].

Example 2:
Input: grid = [[9,1,7],[8,9,2],[3,4,6]]
Output: [9,5]
Explanation: Number 9 is repeated and number 5 is missing so the answer is [9,5].

Constraints:

2 <= n == grid.length == grid[i].length <= 50
1 <= grid[i][j] <= n * n
For all x that 1 <= x <= n * n there is exactly one x that is not equal to any of the grid members.
For all x that 1 <= x <= n * n there is exactly one x that is equal to exactly two of the grid members.
For all x that 1 <= x <= n * n except two of them there is exactly one pair of i, j that 0 <= i, j <= n - 1 and grid[i][j] == x.
 */

/*
Approach: Frequency Tracking with Boolean Array

Goal:
Given an n × n grid containing numbers from 1 to n²,
find:
- the repeated number, and
- the missing number.

Key Idea:
Use a boolean array to track whether each number from 1 to n²
has been seen.

Algorithm:
1. Let total numbers = n².
2. Create a boolean array seen[] of size n².
3. Traverse the grid:
   - For each number num:
       • If seen[num − 1] is already true → num is the repeated value.
       • Else mark seen[num − 1] = true.
4. Traverse seen[]:
   - The index i where seen[i] is false corresponds to the missing number (i + 1).
5. Return [repeated, missing].

Why It Works:
- Exactly one number is duplicated and one is missing.
- Boolean array allows O(1) presence checks.

Time Complexity: O(n²)  
Space Complexity: O(n²)
*/

package Arrays.Easy;

public class _2965_Find_Missing_and_Repeated_Values {
    // Method to find the repeated and missing value in the grid
    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        // Get the grid length
        int length = grid.length * grid.length;

        // Initialize the frequency set for boolean
        boolean[] seen = new boolean[length];

        // Initialize the doubleValue and index for the seen array
        int doubleValue = 0;

        // Iterate over the grid for finding the double value
        for (int[] row : grid) {
            for (int num : row) {
                // If value is seen then update the double value else mark the index to true
                if (seen[num - 1]) {
                    doubleValue = num;
                } else {
                    seen[num - 1] = true;
                }
            }
        }

        // Iterate over the length to find out the missing value
        for (int i = 0; i < length; i++) {
            // If value is not in the seen then return the array of double and missing value
            if (!seen[i]) {
                return new int[] { doubleValue, i + 1 };
            }
        }

        // Return null in the end
        return null;
    }

    // Main method to test findMissingAndRepeatedValues
    public static void main(String[] args) {
        int[][] grid = new int[][] { { 9, 1, 7 }, { 8, 9, 2 }, { 3, 4, 6 } };

        int[] result = findMissingAndRepeatedValues(grid);

        System.out.println("The repeated value in the grid is " + result[0] + " and the missing value in the grid is : "
                + result[1]);
    }
}
