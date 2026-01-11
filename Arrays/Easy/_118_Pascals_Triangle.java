/*
LeetCode Problem: https://leetcode.com/problems/pascals-triangle/

Question: 118. Pascal's Triangle

Problem Statement: Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]

Constraints:

1 <= numRows <= 30
*/

/*
Approach: Dynamic Construction Using Previous Row

Goal:
Generate the first numRows of Pascal’s Triangle.

Key Observation:
- Each number is the sum of the two numbers directly above it.
- First and last elements of every row are always 1.

Algorithm:
1. Initialize an empty list to store all rows.
2. Add the first row: [1].
3. For each subsequent row i (from 1 to numRows − 1):
   - Take the previous row.
   - Pad it with 0 at both ends.
   - Generate the current row by summing adjacent elements.
4. Append each generated row to the result list.
5. Return the complete Pascal’s Triangle.

Why It Works:
- Each row depends only on the previous row.
- Padding with zeros simplifies boundary handling.

Time Complexity: O(numRows²)  
Space Complexity: O(numRows²)
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _118_Pascals_Triangle {
    // Method to make the pascal's triangle
    public static List<List<Integer>> generate(int numRows) {
        // Initialize the list of list of integer for result
        List<List<Integer>> pascalTriangle = new ArrayList<>();

        // Add the first row
        pascalTriangle.add(Arrays.asList(1));

        // Iterate over the numRow from zero to generate pascal's triangle
        for (int i = 1; i < numRows; i++) {
            // Get the current list
            List<Integer> row = new ArrayList<>();

            // Add the zero to first and last
            row.add(0);
            row.addAll(pascalTriangle.get(i - 1));
            row.add(0);

            // Initialize the next row of the pascal's triangle
            List<Integer> nextRow = new ArrayList<>();

            // Iterate over the last row of pascal's triangle
            for (int j = 0; j < i + 1; j++) {
                // Fill the next row of the pascal's triangle
                nextRow.add(row.get(j) + row.get(j + 1));
            }

            // Append the next row to the pascal's triangle
            pascalTriangle.add(nextRow);
        }

        // Retrun the list of list of integer for result
        return pascalTriangle;
    }

    // Main method to test generate
    public static void main(String[] args) {
        int numRows = 5;

        List<List<Integer>> result = generate(numRows);

        System.out.println("The pascal's triangle of " + numRows + " is : " + result);
    }
}
