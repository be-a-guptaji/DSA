/*
LeetCode Problem: https://leetcode.com/problems/pascals-triangle-ii/

Question: 119. Pascal's Triangle II

Problem Statement: Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: rowIndex = 3
Output: [1,3,3,1]

Example 2:
Input: rowIndex = 0
Output: [1]

Example 3:
Input: rowIndex = 1
Output: [1,1]

Constraints:

0 <= rowIndex <= 33 

Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
*/

/*
Approach: In-Place Dynamic Programming (Single Row Update)

Goal:
Return the rowIndex-th row of Pascal’s Triangle (0-indexed).

Key Idea:
Each row of Pascal’s Triangle can be built from the previous row.
By updating from right to left, we can reuse the same list in place
without overwriting values needed for computation.

Algorithm:
1. Initialize a list `row` with the first element [1].
2. For each row i from 1 to rowIndex:
   - Update elements from right to left:
       row[j] = row[j] + row[j − 1]
   - Append 1 at the end of the row.
3. After completing all iterations, `row` contains the desired row.

Why It Works:
- Right-to-left updates preserve values from the previous iteration.
- Avoids using a full 2D triangle, saving space.

Time Complexity: O(rowIndex²)  
Space Complexity: O(rowIndex)
*/

package Arrays.Easy;

import java.util.ArrayList;
import java.util.List;

public class _119_Pascals_Triangle_II {
    // Method to return a specific row of Pascal's Triangle
    public static List<Integer> getRow(int rowIndex) {
        // Initialize the list for storing the result row
        List<Integer> row = new ArrayList<>();

        // Add the first element of Pascal's Triangle
        row.add(1);

        // Iterate till the required row index
        for (int i = 1; i <= rowIndex; i++) {
            // Update the elements from right to left
            for (int j = i - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }

            // Add 1 at the end of each row
            row.add(1);
        }

        // Return the required Pascal's Triangle row
        return row;
    }

    // Main method to test getRow
    public static void main(String[] args) {
        int rowIndex = 5;

        List<Integer> result = getRow(rowIndex);

        System.out.println("The pascal's triangle row of index " + rowIndex + " is : " + result);
    }
}
